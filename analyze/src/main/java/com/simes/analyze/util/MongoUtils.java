package com.simes.analyze.util;


import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.simes.analyze.enums.ConfigEnum;
import org.bson.Document;

import java.util.Arrays;

/**
 * @Description: 数据库连接工具类
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class MongoUtils {
    public static MongoClient client;
    public static MongoClientURI uri;
    static {
        uri = new MongoClientURI(ConfigEnum.MONGO_ULI.getValue());

        MongoCredential credential = MongoCredential.createCredential("admin", "simes", "123qwe".toCharArray());

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyToSslSettings(builder -> builder.enabled(true))
                .applyToClusterSettings(builder ->
                        builder.hosts(Arrays.asList(
                                new ServerAddress("192.168.3.85", 27017),
                                new ServerAddress()
                        )))
                .build();
//        MongoClientOptions options = MongoClientOptions.builder().build();
        client = MongoClients.create(settings);
    }

    public static MongoCollection<Document> getConnect(){
        MongoDatabase db = client.getDatabase(uri.getDatabase());
        MongoCollection<Document> collection = db.getCollection("test_mongo");
        return collection;
    }

    public static void close(MongoClient client) {
        client.close();
    }
}


