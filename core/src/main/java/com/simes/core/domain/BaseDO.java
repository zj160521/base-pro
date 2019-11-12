package com.simes.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/7/4 16:42
 */
@Data
@ApiModel(value = "数据库对象基类")
public class BaseDO {
    @Id
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "创建人")
    private String createUser;
    @ApiModelProperty(value = "修改人")
    private String updateUser;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "备注")
    private String note;
    @ApiModelProperty(value = "是否已删除,0未删除，1已删除")
    private Integer isDeleted;

}
