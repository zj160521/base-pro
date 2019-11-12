package com.simes.storm.bolt.activitystat;

import com.mongodb.client.MongoCollection;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class ActivityStatBolt extends BaseRichBolt {

    protected OutputCollector collector;
    protected final String mongoTableName = "activity_stat";
    protected MongoCollection mongoCollection;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
//        mongoCollection = MongoUtil.getConnect().getCollection(mongoTableName);
    }

    @Override
    public void execute(Tuple input) {
        try {
            String msg = input.getString(0);
            System.out.println(">>>>>>>>"+msg);
        } catch (Exception e) {
            collector.fail(input);
            return;
        }
        // 继续发射或者应答成功
        collector.emit(new Values(input.getString(0)));
//        collector.ack(input);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("data"));
    }
}
