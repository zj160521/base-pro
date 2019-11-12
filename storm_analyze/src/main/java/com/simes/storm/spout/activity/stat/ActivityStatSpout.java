package com.simes.storm.spout.activity.stat;

import com.simes.storm.util.KafkaConsumerProperty;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class ActivityStatSpout extends BaseRichSpout {
    private KafkaConsumer consumer;
    private ConsumerRecords<String, String> msgList;
    private static final String TOPIC = "activity_stat";
    private static final String GROUPID = "ActivityStatGroup";
    SpoutOutputCollector collector;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        Properties props = KafkaConsumerProperty.getConsumerProps();
        props.setProperty("group.id", GROUPID);
        this.collector = collector;
        this.consumer = new KafkaConsumer<String, String>(props);
        this.consumer.subscribe(Arrays.asList(TOPIC));
    }

    @Override
    public void nextTuple() {
        msgList = consumer.poll(Duration.ofMillis(1000));
        if(null != msgList && msgList.count() > 0){
            for (ConsumerRecord<String, String> record : msgList) {
                // 以key作为msgID
                collector.emit(new Values(record.value()), record.key());
            }
            consumer.commitSync();
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("value"));
    }

    @Override
    public void ack(Object msgId) {
        System.out.println("处理成功！");
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("处理失败！");
    }
}
