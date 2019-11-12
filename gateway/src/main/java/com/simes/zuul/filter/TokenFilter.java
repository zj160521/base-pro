package com.simes.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.simes.core.domain.token.TokenReq;
import com.simes.core.domain.token.UserCache;
import com.simes.core.exception.BizException;
import com.simes.core.token.TokenService;
import com.simes.core.util.request.SystemRequestHeader;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/8/5 11:08
 */
@Component
public class TokenFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    private final List<String> noneAuthPaths = Arrays.asList("/api/sys/test/get","/api/sys/user/login","/api/sys/user/add");

    @Autowired
    private TokenService tokenService;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestUri = request.getRequestURI();
        for (String path : noneAuthPaths) {
            if (requestUri.contains(path)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        try {
            HttpServletRequest request = requestContext.getRequest();
            String bodyData = getBody(request);
            SystemRequestParam systemRequestParam = JSON.parseObject(bodyData, SystemRequestParam.class);
            logger.info("请求参数：" + JSON.toJSONString(systemRequestParam));
            SystemRequestHeader headers = systemRequestParam.getHeaders();
            TokenReq tokenReq = new TokenReq();
            tokenReq.setToken(headers.getToken());
            tokenReq.setClient(headers.getClient());
            UserCache userCache = tokenService.flushToken(tokenReq);
            if (null == userCache) {
                throw new BizException("请登录！", 9999);
            }
            headers.setUserId(userCache.getUserid());
            headers.setUsername(userCache.getUsername());
            systemRequestParam.setHeaders(headers);
            byte[] reqBodyBytes = JSON.toJSONString(systemRequestParam).getBytes();
            requestContext.setRequest(new HttpServletRequestWrapper(request) {
                @Override
                public ServletInputStream getInputStream() throws IOException {
                    return new ServletInputStreamWrapper(reqBodyBytes);
                }

                @Override
                public int getContentLength() {
                    return reqBodyBytes.length;
                }

                @Override
                public long getContentLengthLong() {
                    return reqBodyBytes.length;
                }
            });
        } catch (BizException e) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(200);
            requestContext.setResponseBody(JSON.toJSONString(BaseResponse.failed(e.getCode(), e.getMessage(),null)));
            requestContext.getResponse().setContentType("application/json;charset=UTF-8");
        }
        return null;
    }

    public static String getBody(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
