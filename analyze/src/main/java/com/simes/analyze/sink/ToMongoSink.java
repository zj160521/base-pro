package com.simes.analyze.sink;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.simes.analyze.domain.KafkaData;
import com.simes.analyze.util.MongoUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.bson.Document;


/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/29 15:38
 */
public class ToMongoSink extends RichSinkFunction<KafkaData> {
    private MongoCollection<Document>  connect;
    @Override
    public void invoke(KafkaData value, Context context) throws Exception {
        String t = value.getMessage();
        connect.insertOne(new Document().append("value", t));
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connect = MongoUtils.getConnect();
    }

    @Override
    public void close() throws Exception {
        super.close();
    }
}
