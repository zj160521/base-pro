package com.simes.sys.fallback;

import com.simes.core.domain.test.TestPara;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import com.simes.sys.feign.IWebFeignClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 10:21
 */
@Component
public class UserFallback implements FallbackFactory<IWebFeignClient> {
    @Override
    public IWebFeignClient create(Throwable throwable) {

        return new IWebFeignClient() {
            @Override
            public BaseResponse get(SystemRequestParam<TestPara> requestParam) {
                return BaseResponse.failed();
            }
        };
    }
}
