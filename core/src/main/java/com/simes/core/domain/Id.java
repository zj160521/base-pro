package com.simes.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/19 15:56
 */
@Data
@ApiModel(value = "id类")
public class Id {
    @ApiModelProperty(value = "id")
    private String id;

}
