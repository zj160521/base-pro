package com.simes.crontab.enums;

/**
 * @Description: 用户操作枚举类
 * @Author: zhouj
 * @Date: 2019/10/23 13:57:07
 */
public enum JobStatusEnum {

    /**
     * 暂停job状态
     */
    STOP("1", "停止"),
    /**
     * 可运行job状态
     */
    RUNNING("0", "运行");

    private String key;
    private String value;

    private JobStatusEnum(String key, String value) {
        this.value = value;
        this.key = key;
    }

    public static JobStatusEnum getEnumByKey(String key) {
        for (JobStatusEnum e : JobStatusEnum.values()) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
