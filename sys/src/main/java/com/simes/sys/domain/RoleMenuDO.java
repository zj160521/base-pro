package com.simes.sys.domain;

import com.simes.core.domain.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/12 15:46
 */
@Data
@Table(name = "sys_role_menu")
@ApiModel(value = "角色菜单类")
public class RoleMenuDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 7300661322529740841L;
    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "菜单id")
    private String menuId;

}
