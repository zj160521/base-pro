package com.simes.sys.controller.dept;

import com.github.pagehelper.PageInfo;
import com.simes.core.controller.BaseController;
import com.simes.core.domain.Ids;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.simes.sys.domain.DeptDO;
import com.simes.sys.service.department.DeptService;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/09/23 14:33:28
 */
@RestController
@RequestMapping("/dept")
@Api(tags = "dept",description = "部门相关接口")
public class DeptController extends BaseController {
    @Autowired
    private DeptService sysDeptService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "插入部门", notes = "参数tenantId、deptName不能为空")
    public BaseResponse add(@RequestBody SystemRequestParam<DeptDO> requestParam) {
        sysDeptService.add(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "获取部门信息", notes = "按传入参数获取部门信息列表")
    public BaseResponse<PageInfo<List<DeptDO>>> list(@RequestBody SystemRequestParam<DeptDO> requestParam) {
        DeptDO para = requestParam.getBody();
        page(requestParam);
        List<DeptDO> list = sysDeptService.list(para);
        PageInfo<List<DeptDO>> pageInfo = new PageInfo(list);
        return BaseResponse.success(pageInfo);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改部门信息", notes = "参数id为必要条件")
    public BaseResponse update(@RequestBody SystemRequestParam<DeptDO> requestParam) {
        sysDeptService.update(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除部门", notes = "传入id数组")
    public BaseResponse delete(@RequestBody SystemRequestParam<Ids> requestParam) {
        sysDeptService.delete(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/selectOne")
    @ApiOperation(value = "查找单条记录", notes = "传入条件字段")
    public BaseResponse<DeptDO> selectOne(@RequestBody SystemRequestParam<DeptDO> requestParam) {
        DeptDO record = sysDeptService.selectOne(requestParam.getBody());
        return BaseResponse.success(record);
    }
}