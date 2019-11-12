package com.simes.sys.domain;


import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Table;
import com.simes.core.domain.BaseDO;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/09/23 14:33:28
 */
@Data
@Table(name = "sys_dept")
@ApiModel(value = "部门表实体类")
public class DeptDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -70335157130593086L;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "父主键")
    private String parentId;
    @ApiModelProperty(value = "部门名")
    private String deptName;
    @ApiModelProperty(value = "部门全称")
    private String fullName;
    @ApiModelProperty(value = "排序")
    private Integer sort;

}