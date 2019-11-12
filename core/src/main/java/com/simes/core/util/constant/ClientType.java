package com.simes.core.util.constant;

import io.swagger.annotations.ApiModel;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
@ApiModel(value = "客户端类型")
public interface ClientType {
    int PC = 1;
    int H5 = 2;
    int ANDROID = 3;
    int IOS = 4;
}
