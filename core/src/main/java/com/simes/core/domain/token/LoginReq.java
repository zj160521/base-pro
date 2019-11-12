package com.simes.core.domain.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/6/13 17:56
 */
@Data
@ApiModel(value = "登录请求")
public class LoginReq extends UserCache{

    @ApiModelProperty(value = "客户端类型:1:PC, 2：H5, 3: ANDROID, 4: IOS")
    private Integer client;

}
