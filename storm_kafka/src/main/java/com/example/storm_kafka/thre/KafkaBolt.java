package com.example.storm_kafka.thre;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

public class KafkaBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        if(input.getValues().size()==0){return;}
        // 存入redis
        // 2索引代表kafka的偏移量
        String key=input.getValues().get(2).toString();
        //4索引代表kafka的value值
        String value=input.getValues().get(4).toString();

        //存入hbase
        UserLog userLog= JSON.parseObject(input.getValues().get(4).toString(),UserLog.class);


    }
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer){
    }
}

/*0索引代表kafka的topic
1索引代表kafka的分区
2索引代表kafka的偏移量
3索引代表kafka的key值
4索引代表kafka的value值*/