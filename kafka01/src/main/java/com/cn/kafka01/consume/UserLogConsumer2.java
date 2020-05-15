package com.cn.kafka01.consume;

import com.alibaba.fastjson.JSON;
import com.cn.kafka01.bean.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class UserLogConsumer2 {
    private static int a=0;
    @KafkaListener(topics = {"haha"},groupId = "test212")
    public void consumer(ConsumerRecord<?,?> consumerRecord){
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if(kafkaMessage.isPresent()){
            //得到Optional实例中的值
            String message = (String)kafkaMessage.get();
            UserLog userLog= JSON.parseObject(message,UserLog.class);
            System.err.println(a+++"test2消费消息:"+message);
        }
    }
}