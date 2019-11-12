package com.simes.sys.controller.role;

import com.github.pagehelper.PageInfo;
import com.simes.core.controller.BaseController;
import com.simes.core.domain.Ids;
import com.simes.core.domain.sys.role.SysRoleDO;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import com.simes.sys.domain.RoleMenuReq;
import com.simes.sys.service.role.RoleService;
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
 * @Date: 2019/9/12 16:17
 */
@RestController
@RequestMapping("/role")
@Api(tags = "role", description = "角色相关接口")
public class RoleController extends BaseController {
    @Autowired
    private RoleService service;

    @PostMapping(value = "/add")
    @ApiOperation(value = "插入角色", notes = "参数传account(登录账户，必传)、passwoed(密码，必传)")
    public BaseResponse add(@RequestBody SystemRequestParam<SysRoleDO> requestParam) {
        String createrId = requestParam.getHeaders().getUserId();
        service.addRole(requestParam.getBody(), createrId);
        return BaseResponse.success();
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "获取角色", notes = "按传入参数获取角色信息列表")
    public BaseResponse<PageInfo<List<SysRoleDO>>> list(@RequestBody SystemRequestParam<SysRoleDO> requestParam) {
        SysRoleDO para = requestParam.getBody();
        page(requestParam);
        List<SysRoleDO> userList = service.list(para);
        PageInfo<List<SysRoleDO>> pageInfo = new PageInfo(userList);
        return BaseResponse.success(pageInfo);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改角色信息", notes = "参数id为必要条件")
    public BaseResponse update(@RequestBody SystemRequestParam<SysRoleDO> requestParam) {
        service.updateRole(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除角色", notes = "传入id数组")
    public BaseResponse delete(@RequestBody SystemRequestParam<Ids> requestParam) {
        service.delete(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/setMenu")
    @ApiOperation(value = "角色菜单配置", notes = "参数传roleId（角色id）和menuIds（menu的id数组）")
    public BaseResponse setMenu(@RequestBody SystemRequestParam<RoleMenuReq> requestParam) {
        service.setMenu(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/selectOne")
    @ApiOperation(value = "查找单条记录", notes = "传入条件字段")
    public BaseResponse<SysRoleDO> selectOne(@RequestBody SystemRequestParam<SysRoleDO> requestParam) {
        SysRoleDO record = service.selectOne(requestParam.getBody());
        return BaseResponse.success(record);
    }

}
