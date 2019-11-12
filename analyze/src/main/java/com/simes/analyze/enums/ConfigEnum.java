package com.simes.analyze.enums;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/11/01 17:17
 */
public enum ConfigEnum {
    HOST("192.168.3.85"),
    KAFKA_OFFSET_COMMIT_PERIOD_MS("10000"),
    KAFKA_MAX_POLLRECORDS("1000"),
    KAFKA_PORT("9092"),
    REDIS_PORT("6379"),
    REDIS_PWD("123456"),
    MYSQL_URL("jdbc:mysql://192.168.3.85:3306/simes?useServerPrepStmts=false&rewriteBatchedStatements=true"),
    MYSQL_USERNAME("root"),
    MYSQL_PWD("123qwe!@#"),
    MYSQL_DRIVER("com.mysql.cj.jdbc.Driver"),
    MONGO_ULI("mongodb://admin:123qwe@192.168.3.85:27017/simes?authSource=admin&authMechanism=SCRAM-SHA-1");
    private String value;
    ConfigEnum(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
