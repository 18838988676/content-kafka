package com.example.storm_kafka.thre;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;

public class Topology {
    public static void main(String[] args) throws Exception {
        KafkaSpoutConfig.Builder<String,String> builder =  KafkaSpoutConfig.builder("192.168.1.120:9092", "haha");
        builder.setGroupId("test_storm_wc");
        KafkaSpoutConfig<String, String> kafkaSpoutConfig= builder.build();
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("WordCountFileSpout",new KafkaSpout<>(kafkaSpoutConfig), 1);
        topologyBuilder.setBolt("readKafkaBolt",new KafkaBolt()).shuffleGrouping("WordCountFileSpout");
        Config config = new Config();
        if(args !=null && args.length > 0){
            config.setDebug(false);
            StormSubmitter.submitTopology("kafkaStromTopo", config, topologyBuilder.createTopology());
        }else{
            config.setDebug(true);
            LocalCluster cluster= new LocalCluster();
            cluster.submitTopology("kafkaStromTopo", config, topologyBuilder.createTopology());
        }
    }
}