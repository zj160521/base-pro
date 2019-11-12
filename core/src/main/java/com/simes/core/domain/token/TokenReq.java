package com.simes.core.domain.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/6/13 17:51
 */
@Data
@ApiModel(value = "token请求")
public class TokenReq {

    @ApiModelProperty(value="token")
    private String token;

    @ApiModelProperty(value = "客户端类型:1:PC, 2：H5, 3: ANDROID, 4: IOS")
    private Integer client;

    @ApiModelProperty(value="请求地址")
    private String reqUrl;

}
