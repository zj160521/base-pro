package com.simes.storm.util;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class ConfigMsg {
	public static final String HOST = "192.168.3.85";
	public static final String KAFKA_OFFSET_COMMIT_PERIOD_MS = "10000";
	public static final String KAFKA_MAX_POLLRECORDS = "100";
	public static final String KAFKA_PORT = "9092";
	public static final String REDIS_PORT = "6379";
	public static final String REDIS_PWD = "123456";
	public static final String MYSQL_URL = "jdbc:mysql://192.169.7.20:8066/GM?useServerPrepStmts=false&rewriteBatchedStatements=true";
	public static final String MYSQL_USERNAME = "root";
	public static final String MYSQL_PWD ="123456";
	public static final String MYSQL_DRIVER ="com.mysql.cj.jdbc.Driver";
	public static final String MONGO_ULI = "mongodb://root:root@192.169.7.20:27017/simes";

}
