package com.simes.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/11 13:56
 */
@Data
@ApiModel(value = "id集合类")
public class Ids {
    @ApiModelProperty(value = "id集合")
    private List<String> ids;

}
