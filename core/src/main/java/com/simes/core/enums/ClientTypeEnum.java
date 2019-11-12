package com.simes.core.enums;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/6/25 17:17
 */
public enum ClientTypeEnum {
    PC(1),H5(2),ANDROID(3),IOS(4);
    private int value;
    ClientTypeEnum(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
