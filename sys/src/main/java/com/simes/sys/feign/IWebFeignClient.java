package com.simes.sys.feign;

import com.github.pagehelper.PageInfo;
import com.simes.core.domain.test.TestPara;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import com.simes.sys.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 11:28
 */
@FeignClient(value = "activiti-service", fallbackFactory = UserFallback.class)
public interface IWebFeignClient {
    /**
     * 获取测试信息
     * @param requestParam
     * @return
     */
    @PostMapping(value = "/test/get")
    BaseResponse<PageInfo> get(SystemRequestParam<TestPara> requestParam);
}
