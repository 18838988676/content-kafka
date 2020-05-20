package com.example.storm_kafka.send;


import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.StringTokenizer;



public class SpliterBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector){

        String sentence = tuple.getString(0);

        StringTokenizer iter = new StringTokenizer(sentence);

        while(iter.hasMoreElements()){
            collector.emit(new Values(iter.nextToken()));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer){

        declarer.declare(new Fields("word"));
    }
}
