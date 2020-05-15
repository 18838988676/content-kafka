//package com.cn.kafka01.twodemo;
//
//import com.cn.kafka01.onedemo.KafkaReceiverBatch;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Slf4j
//public class MyListener {
//
//    @Autowired
//    private KafkaReceiverBatch kafkaReceiverBatch;
//
///**/
//    @KafkaListener(id = "id0",containerFactory = "batchFactory", topicPartitions = { @TopicPartition(topic = "${consumer.log.topic:log.business}", partitions = { "0" }) })
//    public void listenPartition0(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
//        log.info("partition:0, size " + records.size());
//        kafkaReceiverBatch.batchConsumer(records,ack);
//        a1 = printNum("0",a += records.size(),a1);
//    }
//    @KafkaListener(id = "id1",containerFactory = "batchFactory", topicPartitions = { @TopicPartition(topic = "${consumer.log.topic:log.business}", partitions = { "1" }) })
//    public void listenPartition1(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
//        log.info("partition:1, size " + records.size());
//        kafkaReceiverBatch.batchConsumer(records,ack);
//        b1 = printNum("1",b += records.size(),b1);
//    }
//    @KafkaListener(id = "id2",containerFactory = "batchFactory", topicPartitions = { @TopicPartition(topic = "${consumer.log.topic:log.business}", partitions = { "2" }) })
//    public void listenPartition2(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
//        log.info("partition:2, size " + records.size());
//        kafkaReceiverBatch.batchConsumer(records,ack);
//        c1 = printNum("2",c += records.size(),c1);
//    }
//
//
//
//    static Integer a = 0,b = 0,c = 0;
//    static Integer a1 = 0,b1 = 0 ,c1 = 0 ;
//    private Integer printNum(String threadTag, Integer num, Integer printTimes){
//        if( num/100000 > printTimes ){
//            System.out.println("partition:" + threadTag + ",consumer num:" + num);
//            printTimes ++;
//        }
//        return printTimes;
//    }
//}