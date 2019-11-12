package com.simes.activiti.controller;

import com.simes.core.controller.BaseController;
import com.simes.core.domain.test.TestDO;
import com.simes.core.domain.test.TestPara;
import com.simes.core.util.IDGenerator;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import com.github.pagehelper.PageInfo;
import com.simes.activiti.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
@RestController
@RequestMapping("/test")
@Api(tags = "test",description = "测试接口")
public class TestController extends BaseController {
    @Autowired
    private TestService service;
    @RequestMapping(value = "/getMsg", method = RequestMethod.POST)
    @ApiOperation(value = "获取测消息")
    public Object getMsg() {
        String msg = "测试消息！";
        LOGGER.info(msg);
        return msg;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "插入测试记录", notes = "参数传name(测试名称)、logTime(记录时间)")
    public BaseResponse insert(@RequestBody SystemRequestParam<TestPara> requestParam) {
        TestDO testDO = new TestDO();
        testDO.setId(IDGenerator.uuid());
        testDO.setName(requestParam.getBody().getName());
        testDO.setLogTime(LocalDateTime.now());
        service.insert(testDO);
        return BaseResponse.success();
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ApiOperation(value = "获取测试记录")
    public BaseResponse<PageInfo> get(@RequestBody SystemRequestParam requestParam) {
        page(requestParam);
        List<TestDO> testList = service.get();
        PageInfo pageInfo = new PageInfo(testList);
        return BaseResponse.success(pageInfo);
    }

    @PostMapping(value = "/kafka")
    @ApiOperation(value = "获取测试记录", notes = "参数传name(测试名称)、logTime(记录时间)")
    public BaseResponse<PageInfo> kafka(@RequestBody SystemRequestParam<TestPara> requestParam) {
        boolean send = service.kafkaSend(requestParam.getBody());
        return send == true ? BaseResponse.success() : BaseResponse.failed("发送消息失败！");
    }

}
