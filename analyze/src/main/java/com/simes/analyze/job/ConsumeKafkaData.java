package com.simes.analyze.job;

import com.alibaba.fastjson.JSON;
import com.simes.analyze.enums.ConfigEnum;
import com.simes.analyze.sink.ToMongoSink;
import com.simes.analyze.domain.KafkaData;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.StringUtils;

import java.util.Properties;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/29 10:35
 */
public class ConsumeKafkaData {
    private static final String TOPIC = "flink_test";
    private static final String CONSUMER_GROUP = "flink_test";
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", ConfigEnum.HOST.getValue() + ":" + ConfigEnum.KAFKA_PORT.getValue());
        properties.setProperty("zookeeper.connect", ConfigEnum.HOST.getValue() + ":2181");
        properties.setProperty("group.id", CONSUMER_GROUP);
//        properties.setProperty("auto.offset.reset", "latest");

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer(TOPIC, new SimpleStringSchema(), properties);
        DataStream<String> stream = env
                .addSource(consumer);
        DataStream<KafkaData> map = stream
                .filter((value) -> !StringUtils.isNullOrWhitespaceOnly(value))
                .map((value) -> JSON.parseObject(value, KafkaData.class));
        map.addSink(new ToMongoSink());
        env.execute("consume kafka data");
    }
}
