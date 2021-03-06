package com.example.storm_kafka.centos;

import com.alibaba.fastjson.JSON;
import com.example.storm_kafka.db.CreateTable;
import lombok.extern.slf4j.Slf4j;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.util.concurrent.TimeUnit;
@Slf4j
public class PrintBolt extends BaseBasicBolt {

/* *//**//*     private CreateTable createTable=null;
    PrintBolt(){
        log.info("createTable 初始化");
        createTable=new CreateTable();
      }*/
    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
      /*  try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        String mes=tuple.getStringByField("value");
        if(mes.startsWith("{")){
            System.out.println("" +tuple.getStringByField("value"));
        }
        UserLog userLog= JSON.parseObject(mes,UserLog.class);
        try {
            System.out.println("=="+userLog);
//            createTable.addData(userLog);
        }catch (Exception e){
            log.error(e.getMessage());
        }
*/
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("" +tuple.getStringByField("value"));
        //进行传递给下一个bolt
        //collector.emit(new Values(print));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //declarer.declare(new Fields("write"));
    }
}