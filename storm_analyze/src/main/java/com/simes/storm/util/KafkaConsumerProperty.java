package com.simes.storm.util;

import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class KafkaConsumerProperty {
    public static Properties getConsumerProps(){
        Properties props = new Properties();
        props.put("bootstrap.servers", ConfigMsg.HOST.concat(":").concat(ConfigMsg.KAFKA_PORT));
        props.put("enable.auto.commit", "false");
        props.put("max.poll.records", ConfigMsg.KAFKA_MAX_POLLRECORDS);
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        return props;
    }
}
