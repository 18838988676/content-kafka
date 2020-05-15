package com.cn.kafka01.testconsume;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumebb03 {
    //指定监听的ordertopic，当前消费者组id
    @KafkaListener(topics = {"ordertopic"},groupId = "bb")
    public void receiveMess(ConsumerRecord<String,String> messs){
        log.info("bb:03"+messs.value());
    }

}/**/
