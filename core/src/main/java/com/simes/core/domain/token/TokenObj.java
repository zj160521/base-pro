package com.simes.core.domain.token;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/6/13 17:56
 */
@Data
public class TokenObj extends UserCache implements Serializable {

    private static final long serialVersionUID = 6037113402766551599L;
    @ApiModelProperty(value = "客户端类型:1:PC, 2：H5, 3: ANDROID, 4: IOS")
    private Integer client;

    private String token;

    public TokenObj() {
    }

    public TokenObj(String userId, String username, Integer client, String token) {
        super.setUserid(userId);
        super.setUsername(username);
        this.client = client;
        this.token = token;
    }

}
