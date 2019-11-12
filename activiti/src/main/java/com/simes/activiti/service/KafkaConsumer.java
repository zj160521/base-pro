package com.simes.activiti.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/10/9 14:11
 */
@Component
public class KafkaConsumer {
    protected final static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    /*@KafkaListener(topics = "#{'${kafka.listener.topics}'.split(',')}")*/
    @KafkaListener(topicPartitions ={@TopicPartition(topic = "boot", partitions = {"0"})})
    public void consumer(ConsumerRecord<?, ?> record) {
        logger.info("offset: " + record.offset());
        logger.info("topic: " + record.topic());
        logger.info("value: " + record.value().toString());
    }
}
