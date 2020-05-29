/*
package com.example.storm_kafka.send;


import lombok.extern.slf4j.Slf4j;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
public class Topohogy {

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, InterruptedException {

        String topic = "hehe";
        String zkRoot = "/kafka-storm";
        String id = "old";
        BrokerHosts brokerHosts = new ZkHosts("h40:2181,h41:2181,h42:2181");
        KafkaSpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, zkRoot, id);
        spoutConfig.forceFromStart = true;
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        TopologyBuilder builder = new TopologyBuilder();
        //设置一个spout用来从kaflka消息队列中读取数据并发送给下一级的bolt组件，此处用的spout组件并非自定义的，而是storm中已经开发好的KafkaSpout
        builder.setSpout("KafkaSpout", new KafkaSpout(spoutConfig));
        builder.setBolt("word-spilter", new SpliterBolt()).shuffleGrouping("KafkaSpout");
        builder.setBolt("writer", new CountBolt(), 3).fieldsGrouping("word-spilter", new Fields("word"));

        SimpleHBaseMapper mapper = new SimpleHBaseMapper();
        //wordcount为表名
        HBaseBolt hBaseBolt = new HBaseBolt("wordcount", mapper).withConfigKey("hbase.conf");
        //result为列族名
        mapper.withColumnFamily("result");
        mapper.withColumnFields(new Fields("count"));
        mapper.withRowKeyField("word");
        Config conf = new Config();
        conf.setNumWorkers(4);
        conf.setNumAckers(0);
        conf.setDebug(false);

        Map<String, Object> hbConf = new HashMap<String, Object>();
        hbConf.put("hbase.rootdir", "hdfs://h40:9000/hbase");
        hbConf.put("hbase.zookeeper.quorum", "h40:2181");
        conf.put("hbase.conf", hbConf);

        // hbase-bolt
        builder.setBolt("hbase", hBaseBolt, 3).shuffleGrouping("writer");

        if (args != null && args.length > 0) {
            //提交topology到storm集群中运行
            StormSubmitter.submitTopology("sufei-topo", conf, builder.createTopology());
        } else {
            //LocalCluster用来将topology提交到本地模拟器运行，方便开发调试
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("WordCount", conf, builder.createTopology());
        }
    }
}
*/
