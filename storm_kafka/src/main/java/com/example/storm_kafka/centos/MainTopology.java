package com.example.storm_kafka.centos;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;

public class MainTopology {
    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        KafkaSpoutConfig.Builder<String, String> kafkaBuilder = KafkaSpoutConfig.builder("192.168.1.117:9092", "flumetokafka");
        //设置kafka属于哪个组
        kafkaBuilder.setGroupId("strom_group");
        //创建kafkaspoutConfig
        KafkaSpoutConfig<String, String> build = kafkaBuilder.build();
        //通过kafkaspoutConfig获得kafkaspout
        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<String,String>(build);
        //设置5个线程接收数据
        builder.setSpout("kafkaSpout",kafkaSpout,2);
        //设置2个线程处理数据
        builder.setBolt("printBolt",new PrintBolt(),2).localOrShuffleGrouping("kafkaSpout");
        Config config = new Config();
        config.setDebug(false);
        if (args.length>0){
            //集群提交模式
            StormSubmitter.submitTopology(args[0],config,builder.createTopology());
        } else{
            //本地测试模式
            //设置2个进程
            config.setNumWorkers(1);
            config.setNumEventLoggers(0);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("kafkaSpout",config,builder.createTopology());
        }
    }
}