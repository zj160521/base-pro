package com.simes.sys.service.user;

import com.simes.core.domain.Ids;
import com.simes.core.domain.test.TestPara;
import com.simes.core.domain.token.LoginReq;
import com.simes.core.domain.token.TokenRes;
import com.simes.core.domain.sys.user.SysUserDO;
import com.simes.core.exception.BizException;
import com.simes.core.service.BaseService;
import com.simes.core.token.TokenService;
import com.simes.core.util.IDGenerator;
import com.simes.core.util.crypto.Md5Util;
import com.simes.core.util.request.SystemRequestHeader;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.sys.dao.UserDao;
import com.simes.sys.feign.IWebFeignClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 11:34
 */
@Service
public class UserService extends BaseService {
    @Autowired
    private UserDao dao;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IWebFeignClient webFeignClient;

    public List getInfo(SystemRequestParam<TestPara> requestParam) {
        return webFeignClient.get(requestParam).getData().getList();
    }

    /**
     * 登录
     * @param requestParam
     * @return
     */
    public TokenRes login(SystemRequestParam<SysUserDO> requestParam){
        SysUserDO userDO = requestParam.getBody();
        if (StringUtils.isEmpty(userDO.getAccount()) || StringUtils.isEmpty(userDO.getPassword())) {
            throw new BizException("账号密码不能为空！");
        }
        Example example = new Example(SysUserDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account", userDO.getAccount());
        SysUserDO user = dao.selectOneByExample(example);
        if (user == null) {
            throw new BizException("未发现该用户！");
        }
        String pwd = Md5Util.getMd5(userDO.getPassword());
        if (!pwd.equals(user.getPassword())) {
            throw new BizException("密码错误！");
        }
        SystemRequestHeader headers = requestParam.getHeaders();
        LoginReq loginReq = new LoginReq();
        loginReq.setClient(headers.getClient());
        loginReq.setUserid(user.getId());
        loginReq.setUsername(user.getAccount());
        TokenRes token = tokenService.getToken(loginReq);
        return token;
    }

    /**
     * 登出
     * @param requestParam
     */
    public void logout(SystemRequestParam requestParam) {
        SystemRequestHeader header = requestParam.getHeaders();
        tokenService.clearToken(header.getUserId(), header.getClient());
    }

    public void addUser(SysUserDO userDO){
        if (StringUtils.isEmpty(userDO.getAccount()) || StringUtils.isEmpty(userDO.getPassword())) {
            throw new BizException("账号密码不能为空！");
        }
        userDO.setId(IDGenerator.uuid());
        String md5 = Md5Util.getMd5(userDO.getPassword());
        userDO.setPassword(md5);
        dao.insertSelective(userDO);
    }

    public void updateUser(SystemRequestParam<SysUserDO> requestParam){
        String userId = requestParam.getHeaders().getUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BizException("账号未登陆！");
        }
        SysUserDO userDO = requestParam.getBody();
        userDO.setId(userId);
        if (!StringUtils.isEmpty(userDO.getPassword())) {
            String md5 = Md5Util.getMd5(userDO.getPassword());
            userDO.setPassword(md5);
        }
        dao.updateByPrimaryKeySelective(userDO);
    }

    public List<SysUserDO> list(SysUserDO userDO){
        return dao.select(userDO);
    }

    public SysUserDO selectOne(SysUserDO userDO){
        return dao.selectOne(userDO);
    }

    public void delete(Ids ids){
        this.baseDao = dao;
        deleteByIds(ids);
    }
}
