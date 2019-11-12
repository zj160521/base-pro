package com.simes.storm.util;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;

/**
 * @Description: 数据库连接工具类
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class MongoUtil {
    public static MongoDatabase getConnect(){
        MongoClientURI uri = new MongoClientURI(ConfigMsg.MONGO_ULI);
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());
        return db;
    }
}


