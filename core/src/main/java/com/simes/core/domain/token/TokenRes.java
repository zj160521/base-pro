package com.simes.core.domain.token;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/6/13 17:50
 */
@Data
public class TokenRes implements Serializable {

    private static final long serialVersionUID = 5750529054697510095L;
    private String token;

    public TokenRes() {
    }

    public TokenRes(String token) {
        this.token = token;
    }

}
