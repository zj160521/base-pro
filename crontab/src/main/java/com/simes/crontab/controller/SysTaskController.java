package com.simes.crontab.controller;

import com.github.pagehelper.PageInfo;
import com.simes.core.controller.BaseController;
import com.simes.core.domain.Ids;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import com.simes.crontab.domain.JobDO;
import com.simes.crontab.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/10/24 11:34:26
 */
@RestController
@RequestMapping("/sysTask")
@Api(tags="sysTask",description = "定时任务相关接口")
public class SysTaskController extends BaseController {
    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "插入定时任务", notes = "参数传jobName、cronExpression、beanClass、jobGroup为必要条件")
    public BaseResponse add(@RequestBody SystemRequestParam<JobDO> requestParam) {
        taskService.save(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "获取定时任务列表", notes = "根据参数字段获取定时任务列表")
    public BaseResponse<PageInfo<List<JobDO>>> list(@RequestBody SystemRequestParam<JobDO> requestParam) {
        JobDO para = requestParam.getBody();
        page(requestParam);
        List<JobDO> list = taskService.list(para);
        PageInfo<List<JobDO>> pageInfo = new PageInfo(list);
        return BaseResponse.success(pageInfo);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改定时任务信息", notes = "参数id为必要条件,参数可传description、cronExpression、beanClass")
    public BaseResponse update(@RequestBody SystemRequestParam<JobDO> requestParam) {
        taskService.update(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除定时任务", notes = "传入id数组")
    public BaseResponse delete(@RequestBody SystemRequestParam<Ids> requestParam) {
        taskService.delete(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/selectOne")
    @ApiOperation(value = "查找单条记录", notes = "传入条件字段")
    public BaseResponse<JobDO> selectOne(@RequestBody SystemRequestParam<JobDO> requestParam) {
        JobDO record = taskService.selectOne(requestParam.getBody());
        return BaseResponse.success(record);
    }

    @PostMapping(value = "/changeStatus")
    @ApiOperation(value = "停止定时任务", notes = "传入id,jobStatus（0开，1关）")
    public BaseResponse changeStatus(@RequestBody SystemRequestParam<JobDO> requestParam) {
        JobDO paramBody = requestParam.getBody();
        taskService.changeStatus(paramBody);
        return BaseResponse.success();
    }
}