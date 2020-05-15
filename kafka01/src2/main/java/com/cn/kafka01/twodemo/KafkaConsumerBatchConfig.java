//package com.cn.kafka01.twodemo;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.AbstractMessageListenerContainer;
//import org.springframework.kafka.support.Acknowledgment;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
///*
//* ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG //由于此处批量我们用手动提交,所以该配置改为false
//ConsumerConfig.MAX_POLL_RECORDS_CONFIG //每次批量消费最大数
//factory.setBatchListener(true); //注意把批量消费开启
//* */
////消费者代码和配置:
//@Configuration
//@EnableKafka
//public class KafkaConsumerBatchConfig {
//
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String servers;
//
//    @Value("${spring.kafka.consumer.enable-auto-commit}")
//    private boolean auto;
//
//    @Value("${spring.kafka.consumer.auto-commit-interval}")
//    private int interval;
//
//    @Value("${spring.kafka.consumer.group-id}")
//    private String group;
//
//    @Value("${spring.kafka.consumer.auto-offset-reset}")
//    private String reset;
//
//    @Value("${spring.kafka.consumer.key-deserializer}")
//    private String keyDeserializer;
//
//    @Value("${spring.kafka.consumer.value-deserializer}")
//    private String valueDeserializer;
//
//    @Value("${spring.kafka.consumer.max-poll-records:100}")
//    private String maxPollRecords;
//
//    @Value("${spring.kafka.consumer.max-poll-interval:1000000}")
//    private String maxPollInterval;
//
//
//
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> properties = new HashMap<String, Object>();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);//注意这里修改为kafka的具体配置项目，我这里只是为了开发演示方便
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, auto);
//        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, interval);
//        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, group);
//        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, reset);
//        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, maxPollInterval);
//        return new DefaultKafkaConsumerFactory<String, String>(properties);
//    }
//
//
//    @Bean
//    public KafkaListenerContainerFactory<?> batchFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setConcurrency(1);
//        factory.setBatchListener(true);//设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
//        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);//设置提交偏移量的方式
//        return factory;
//    }
//
//    @Bean
//    public void batchConsumer(List<ConsumerRecord<?, ?>> records, Acknowledgment ack){
//        for (ConsumerRecord<?, ?> record : records) {
//            try {
//                Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//                if (kafkaMessage.isPresent()) {
//                    Object message = kafkaMessage.get();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                continue;
//            }
//
//        }
//        ack.acknowledge();//手动提交偏移量
//    }
///*
//* 性能调优
//
//　　kafka生产和消费要注意几个关键点:
//
//　　1.kafka生产者异步:
//
//pool.execute(()->{kafkaTemplate.send(topic, 0, gson.toJson(Object));});
//　　比如此处可以改为线程池
//
//　　2.批量写入,可以更改生产者的批量发送值和缓存值,加大该值将大幅提升性能
//
//　　3.消费者分区监听,并开启批量消费,提升性能
//* */
//
//}