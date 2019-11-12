package com.simes.core.domain.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/6/13 17:56
 */
@Data
@ApiModel(value = "用户缓存信息")
public class UserCache implements Serializable {
    private static final long serialVersionUID = -5301782497989466118L;
    @ApiModelProperty(value = "用户id")
    private String userid;
    @ApiModelProperty(value = "用户名称")
    private String username;

}
