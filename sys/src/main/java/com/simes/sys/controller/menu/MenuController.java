package com.simes.sys.controller.menu;

import com.simes.core.controller.BaseController;
import com.simes.core.domain.Id;
import com.simes.core.domain.Ids;
import com.simes.core.domain.sys.menu.SysMenuVO;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import com.simes.sys.domain.MenuDO;
import com.simes.sys.service.menu.MenuService;
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
 * @Date: 2019/9/16 14:17
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "menu", description = "菜单相关接口")
public class MenuController extends BaseController {
    @Autowired
    private MenuService service;

    @PostMapping(value = "/add")
    @ApiOperation(value = "插入菜单", notes = "参数code、name、sort必须传")
    public BaseResponse add(@RequestBody SystemRequestParam<MenuDO> requestParam) {
        service.addMenu(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/listByUser")
    @ApiOperation(value = "通过登录账号获取菜单", notes = "不传参")
    public BaseResponse<List<SysMenuVO>> listByUser(@RequestBody SystemRequestParam requestParam) {
        String userId = requestParam.getHeaders().getUserId();
        List<SysMenuVO> menuTree = service.listMenuByUserId(userId);
        return BaseResponse.success(menuTree);
    }

    @PostMapping(value = "/listByRoleId")
    @ApiOperation(value = "通过角色id获取菜单列表", notes = "参数传角色id")
    public BaseResponse<List<SysMenuVO>> listByRoleId(@RequestBody SystemRequestParam<Id> requestParam) {
        String roleId = requestParam.getBody().getId();
        List<SysMenuVO> menuTree = service.listMenuByRoleId(roleId);
        return BaseResponse.success(menuTree);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改菜单信息", notes = "参数id为必要条件")
    public BaseResponse update(@RequestBody SystemRequestParam<MenuDO> requestParam) {
        service.updateMenu(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除菜单", notes = "传入id数组")
    public BaseResponse delete(@RequestBody SystemRequestParam<Ids> requestParam) {
        service.delete(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/selectOne")
    @ApiOperation(value = "查找单条记录", notes = "传入条件字段")
    public BaseResponse<MenuDO> selectOne(@RequestBody SystemRequestParam<MenuDO> requestParam) {
        MenuDO record = service.selectOne(requestParam.getBody());
        return BaseResponse.success(record);
    }
}
