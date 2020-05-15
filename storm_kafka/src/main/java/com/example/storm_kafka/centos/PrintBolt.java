package com.example.storm_kafka.centos;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

public class PrintBolt extends BaseBasicBolt {

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
//        System.out.println(tuple);
        //获取上一个组件所声明的Field
        String print = tuple.getString(0);
//        log.info("message： " + print);
        System.out.println("message is : " + tuple.getStringByField("value"));

        //进行传递给下一个bolt
        //collector.emit(new Values(print));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //declarer.declare(new Fields("write"));
    }
}