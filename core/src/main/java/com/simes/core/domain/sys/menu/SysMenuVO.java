package com.simes.core.domain.sys.menu;

import com.simes.core.domain.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/19 11:03
 */
@Data
@ApiModel(value = "菜单VO类")
public class SysMenuVO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 128335847763515339L;
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
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "子菜单")
    private List<SysMenuVO> children;

}
