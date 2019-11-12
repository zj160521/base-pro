package com.simes.core.token;

import com.alibaba.fastjson.JSON;
import com.simes.core.domain.token.*;
import com.simes.core.exception.BizException;
import com.simes.core.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/11 10:02
 */
@Service
public class TokenService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    // client 设备（1:PC, 2：H5, 3: ANDROID, 4: IOS ）
    /**
     * pc和h5前缀
     */
    private static final String PC_PREFIX = "pcLogin:";
    private static final String H5_PREFIX = "h5Login:";
    private static final int H5_STATE = 2;
    private static final int PC_STATE = 1;
    @Value("${tokenAliveTime}")
    private int tokenAliveTime;
    /**
     * 登录获取token
     * @param loginReq
     * @return
     */
    public TokenRes getToken(LoginReq loginReq) {
        String userIdToken = getUserIdKey(loginReq.getClient(), loginReq.getUserid());
        String obj = redisTemplate.opsForValue().get(userIdToken);
        if(obj != null) {
            TokenObj tokenRes = JSON.parseObject(obj, TokenObj.class);
            redisTemplate.delete(getTokenKey(loginReq.getClient(), tokenRes.getToken()));
        }
        String token = IDGenerator.uuid();
        TokenObj tokenObj = new TokenObj(loginReq.getUserid(), loginReq.getUsername(), loginReq.getClient(), token);
        redisTemplate.opsForValue().set(userIdToken, JSON.toJSONString(tokenObj), tokenAliveTime, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(getTokenKey(loginReq.getClient(), token), JSON.toJSONString(tokenObj),tokenAliveTime, TimeUnit.MINUTES);
        return new TokenRes(token);
    }

    /**
     * 清除token
     * @param userid
     */
    public void clearToken(String userid, Integer client) {
        String userIdToken = getUserIdKey(client, userid);
        String obj = redisTemplate.opsForValue().get(userIdToken);
        if(obj != null) {
            TokenObj tokenObj = JSON.parseObject(obj, TokenObj.class);
            redisTemplate.delete(userIdToken);
            redisTemplate.delete(getTokenKey(client, tokenObj.getToken()));
        }
    }

    /**
     * 刷新token
     * @param tokenReq
     * @return
     * @throws Exception
     */
    public UserCache flushToken(TokenReq tokenReq){
        String token = tokenReq.getToken();
        Integer client = tokenReq.getClient();
        if(token == null) {
            throw new BizException("请登录！", 9999);
        }
        if (client == null ){
            throw new BizException("client不能为空！");
        }
        String tokenKey = getTokenKey(client, token);
        String obj = redisTemplate.opsForValue().get(tokenKey);
        if(obj != null) {
            TokenObj tokenObj = JSON.parseObject(obj, TokenObj.class);
            UserCache userCache = new TokenObj();
            userCache.setUserid(tokenObj.getUserid());
            userCache.setUsername(tokenObj.getUsername());
            redisTemplate.expire(getUserIdKey(client, tokenObj.getUserid()), tokenAliveTime, TimeUnit.MINUTES);
            redisTemplate.expire(tokenKey, tokenAliveTime, TimeUnit.MINUTES);
            return userCache;
        } else {
            throw new BizException("请重新登录！", 9999);
        }
    }

    private String getPrefix(Integer source) {
        if(PC_STATE == source.intValue()) {
            return PC_PREFIX;
        } else if(H5_STATE == source.intValue()) {
            return H5_PREFIX;
        }
        return null;
    }

    private String getUserIdKey(Integer client, String userid) {
        return getPrefix(client) + userid;
    }

    private String getTokenKey(Integer client, String token) {
        return getPrefix(client) + token;
    }

}
