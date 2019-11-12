package com.simes.core.domain.sys.user;

import com.simes.core.domain.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/6/21 17:56
 */
@Data
@Table(name = "sys_user")
@ApiModel(value = "用户类")
public class SysUserDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -1882779532005732594L;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "昵称")
    private String name;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;
    @ApiModelProperty(value = "性别，0男，1女")
    private String sex;
    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "部门id")
    private String deptId;

}
