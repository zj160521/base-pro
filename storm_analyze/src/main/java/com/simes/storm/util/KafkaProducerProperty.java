package com.simes.storm.util;

import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class KafkaProducerProperty {
    public static Properties getProducerProps(){
        Properties props = new Properties();
        props.put("bootstrap.servers", ConfigMsg.HOST.concat(":").concat(ConfigMsg.KAFKA_PORT));
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        return props;
    }
}
