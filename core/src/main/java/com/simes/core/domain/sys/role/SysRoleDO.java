package com.simes.core.domain.sys.role;

import com.simes.core.domain.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/12 13:35
 */
@Data
@Table(name = "sys_role")
@ApiModel(value = "角色类")
public class SysRoleDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 7047925449501934351L;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "父主键")
    private String parentId;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "角色别名")
    private String roleAlias;
    @ApiModelProperty(value = "排序")
    private String sort;

}
