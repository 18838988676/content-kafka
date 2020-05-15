package com.cn.kafka01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class Kafka01Application {

    public static void main(String[] args) {
        String mes=null;
        Optional kafkaMsg=  Optional.ofNullable(mes);
        System.out.println(kafkaMsg);
        SpringApplication.run(Kafka01Application.class, args);
    }

}
