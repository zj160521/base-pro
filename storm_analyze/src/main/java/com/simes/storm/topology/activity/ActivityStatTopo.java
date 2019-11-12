package com.simes.storm.topology.activity;

import com.simes.storm.bolt.activitystat.ActivityStatBolt;
import com.simes.storm.domain.Topology;
import com.simes.storm.spout.activity.stat.ActivityStatSpout;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class ActivityStatTopo {

	public static Topology getTopology(String[] args) {
	    //初始化配置文件
		String spoutId = "ActivityStatSpout";

		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout(spoutId, new ActivityStatSpout(),1);
        builder.setBolt("preBolt", new ActivityStatBolt(),1).shuffleGrouping(spoutId);

		Config conf = new Config();
		conf.setNumWorkers(1);

		Topology topology = new Topology();
		topology.setConf(conf);
		topology.setStormTopology(builder.createTopology());
		topology.setTopologyName("ActivityStatement");
		return topology;
	}

	public static void main(String[] args) {
		//活动统计topo
		Topology activityStat = ActivityStatTopo.getTopology(args);
		try {
			StormSubmitter.submitTopology(activityStat.getTopologyName(), activityStat.getConf(), activityStat.getStormTopology());
		} catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
			e.printStackTrace();
		}
	}
	
}
