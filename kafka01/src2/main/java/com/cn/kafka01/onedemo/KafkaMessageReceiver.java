package com.cn.kafka01.onedemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@Slf4j
public class KafkaMessageReceiver {



/*

上面的消费者配置配置了一个bean，@Bean(“listenerContainerFactory”)，这个bean可以指定为消费者，注解方式中是如下的使用方式。

containerFactory = "listenerContainerFactory"指定了使用listenerContainerFactory作为消费者。

注意registryReceiver中的参数，ConsumerRecord对比之前的消费者，因为设置listenerContainerFactory是批量消费，因此ConsumerRecord是一个List，如果不是批量消费的话，相对应就是一个对象。
注意第二个参数Acknowledgment，这个参数只有在设置消费者的ack应答模式为AckMode.MANUAL_IMMEDIATE才能注入，意思是需要手动ack。
*
* */

    /**
     * listenerContainerFactory设置了批量拉取消息，因此参数是List<ConsumerRecord<Integer, String>>，否则是ConsumerRecord
     * @param msgs
     * @param acknowledgment
     */
    @KafkaListener(topics = {"test"}, containerFactory = "listenerContainerFactory")
    public void registryReceiver(List<ConsumerRecord<Integer, String>> msgs, Acknowledgment acknowledgment) {
        Iterator<ConsumerRecord<Integer, String>> it = msgs.iterator();
        while (it.hasNext()){
            ConsumerRecord<Integer, String> consumerRecords = it.next();
            //dosome
            acknowledgment.acknowledge();
        }
    }

    @Bean("listenerContainerFactory2")
    //个性化定义消费者
    public ConcurrentKafkaListenerContainerFactory listenerContainerFactory2(DefaultKafkaConsumerFactory consumerFactory) {
        //指定使用DefaultKafkaConsumerFactory
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);

        //设置消费者ack模式为手动，看需求设置
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }

}

