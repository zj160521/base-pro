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
 * @Date: 2019/9/12 11:46
 */
@Data
@Table(name = "sys_menu")
@ApiModel(value = "菜单类")
public class MenuDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -1808485357272783522L;
    @ApiModelProperty(value = "父级菜单")
    private String parentId;
    @ApiModelProperty(value = "菜单编号")
    private String code;
    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "菜单别名")
    private String alias;
    @ApiModelProperty(value = "请求地址")
    private String path;
    @ApiModelProperty(value = "菜单资源")
    private String source;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "菜单类型")
    private Integer category;
    @ApiModelProperty(value = "操作按钮类型")
    private Integer action;
    @ApiModelProperty(value = "是否打开新页面")
    private Integer isOpen;

}
