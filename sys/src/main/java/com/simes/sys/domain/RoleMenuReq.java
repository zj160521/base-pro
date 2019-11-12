package com.simes.sys.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/16 15:14
 */
@Data
@ApiModel(value = "角色菜单设置请求")
public class RoleMenuReq {
    @ApiModelProperty(value = "角色ID")
    private String roleId;
    @ApiModelProperty(value = "角色的所有菜单ID")
    private List<String> menuIds;

}
