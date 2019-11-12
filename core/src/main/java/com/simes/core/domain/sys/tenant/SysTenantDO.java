package com.simes.core.domain.sys.tenant;

import com.simes.core.domain.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/12 11:46
 */
@Data
@Table(name = "sys_tenant")
@ApiModel(value = "菜单类")
public class SysTenantDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -3548243654195179678L;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "租户名称")
    private String tenantName;
    @ApiModelProperty(value = "联系人")
    private String linkman;
    @ApiModelProperty(value = "联系电话")
    private String contactNumber;
    @ApiModelProperty(value = "联系地址")
    private String address;

}
