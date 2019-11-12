package com.simes.sys.controller.user;

import com.github.pagehelper.PageInfo;
import com.simes.core.controller.BaseController;
import com.simes.core.domain.Ids;
import com.simes.core.domain.token.TokenRes;
import com.simes.core.domain.sys.user.SysUserDO;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import com.simes.sys.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
@RestController
@RequestMapping("/user")
@Api(tags = "user",description = "用户相关接口")
public class UserController extends BaseController {
    @Autowired
    private UserService service;

    @PostMapping(value = "/add")
    @ApiOperation(value = "插入管理员", notes = "参数传account(登录账户，必传)、passwoed(密码，必传)")
    public BaseResponse add(@RequestBody SystemRequestParam<SysUserDO> requestParam) {
        SysUserDO userDO = requestParam.getBody();
        service.addUser(userDO);
        return BaseResponse.success();
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "获取管理员", notes = "按传入参数获取用户信息列表")
    public BaseResponse<PageInfo<List<SysUserDO>>> list(@RequestBody SystemRequestParam<SysUserDO> requestParam) {
        SysUserDO para = requestParam.getBody();
        page(requestParam);
        List<SysUserDO> userList = service.list(para);
        PageInfo<List<SysUserDO>> pageInfo = new PageInfo(userList);
        return BaseResponse.success(pageInfo);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改管理员信息", notes = "参数id为必要条件")
    public BaseResponse update(@RequestBody SystemRequestParam<SysUserDO> requestParam) {
        service.updateUser(requestParam);
        return BaseResponse.success();
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除管理员", notes = "传入id数组")
    public BaseResponse delete(@RequestBody SystemRequestParam<Ids> requestParam) {
        service.delete(requestParam.getBody());
        return BaseResponse.success();
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "管理员登录", notes = "参数传account(登录账户，必传)、passwoed(密码，必传)")
    public BaseResponse login(@RequestBody SystemRequestParam<SysUserDO> requestParam) {
        TokenRes tokenRes = service.login(requestParam);
        return BaseResponse.success(tokenRes);
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "管理员登出", notes = "登出")
    public BaseResponse logout(@RequestBody SystemRequestParam requestParam) {
        service.logout(requestParam);
        return BaseResponse.success();
    }

    @PostMapping(value = "/selectOne")
    @ApiOperation(value = "查找单条记录", notes = "传入条件字段")
    public BaseResponse<SysUserDO> selectOne(@RequestBody SystemRequestParam<SysUserDO> requestParam) {
        SysUserDO record = service.selectOne(requestParam.getBody());
        return BaseResponse.success(record);
    }

}
