package com.simes.storm.topology;

import com.simes.storm.topology.activity.ActivityStatTopo;
import com.simes.storm.domain.Topology;

import org.apache.storm.LocalCluster;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class Topo {

	public static void main(String[] args) {
		Topology act = ActivityStatTopo.getTopology(args);
		//LocalCluster用来将topology提交到本地模拟器运行，方便开发调试
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology(act.getTopologyName(), act.getConf(), act.getStormTopology());
//		try {
//			StormSubmitter.submitTopology(act.getTopologyName(), act.getConf(), act.getStormTopology());
//		} catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
//			e.printStackTrace();
//		}
	}

}
