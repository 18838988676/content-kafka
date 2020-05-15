package com.cn.kafka01.testconsume;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumeaa01 {
    //指定监听的ordertopic，当前消费者组id
    @KafkaListener(topics = {"ordertopic","usertopic"},groupId = "aa")
    public void receiveMess(ConsumerRecord<String,String> messs){
        log.info("aa:01"+messs.value());
    }

}/**/
