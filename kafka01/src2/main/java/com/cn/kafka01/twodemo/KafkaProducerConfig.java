package com.cn.kafka01.twodemo;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/*
ProducerConfig.BOOTSTRAP_SERVERS_CONFIG  //kafka地址
ProducerConfig.BATCH_SIZE_CONFIG //批量发送配置,单位字节 当多个数据同时发往一个分区时,将被批量控制,减少对服务端的请求
ProducerConfig.BUFFER_MEMORY_CONFIG //生产者缓存,单位字节 生产者对发送数据的缓存总数
*/
//实现生产者消费者需要实现几个关键bean
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Bean("kafkaTemplate")
    public KafkaTemplate<String, String> kafkaTemplate() {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<String, String>(producerFactory());
        return kafkaTemplate;
    }

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServers;

    @Value("${spring.kafka.producer.retries}")
    private String retry;

    @Value("${spring.kafka.producer.batch-size}")
    private String batch;

    @Value("${spring.kafka.producer.buffer-memory}")
    private String mem;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaServers);
        properties.put(ProducerConfig.RETRIES_CONFIG, retry);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, batch);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, mem);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return new DefaultKafkaProducerFactory<String, String>(properties);
    }
}