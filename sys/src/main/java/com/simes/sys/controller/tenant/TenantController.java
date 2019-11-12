package com.simes.sys.controller.tenant;

import com.github.pagehelper.PageInfo;
import com.simes.core.controller.BaseController;
import com.simes.core.domain.Ids;
import com.simes.core.domain.sys.tenant.SysTenantDO;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import com.simes.sys.service.tenant.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
@RestController
@RequestMapping("/tenant")
@Api(tags = "tenant", description = "租户相关接口")
public class TenantController extends BaseController {
    @Autowired
    private TenantService service;

    @PostMapping(value = "/add")
    @ApiOperation(value = "插入租户", notes = "参数传account(登录账户，必传)、passwoed(密码，必传)")
    public BaseResponse add(@RequestBody SystemRequestParam<SysTenantDO> requestParam) {
        service.addTenant(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "获取租户", notes = "按传入参数获取租户信息列表")
    public BaseResponse<PageInfo<List<SysTenantDO>>> list(@RequestBody SystemRequestParam<SysTenantDO> requestParam) {
        SysTenantDO para = requestParam.getBody();
        page(requestParam);
        List<SysTenantDO> tenantList = service.list(para);
        PageInfo<List<SysTenantDO>> pageInfo = new PageInfo(tenantList);
        return BaseResponse.success(pageInfo);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改租户信息", notes = "参数id为必要条件")
    public BaseResponse update(@RequestBody SystemRequestParam<SysTenantDO> requestParam) {
        service.updateUser(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除租户", notes = "传入id数组")
    public BaseResponse delete(@RequestBody SystemRequestParam<Ids> requestParam) {
        service.delete(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/selectOne")
    @ApiOperation(value = "查找单条记录", notes = "传入条件字段")
    public BaseResponse<SysTenantDO> selectOne(@RequestBody SystemRequestParam<SysTenantDO> requestParam) {
        SysTenantDO record = service.selectOne(requestParam.getBody());
        return BaseResponse.success(record);
    }
}
