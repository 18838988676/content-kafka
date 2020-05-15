package com.cn.kafka01.testproduce;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Produce {


    //注入kafkatemplete，这个由spring boot自动创建33554432
    @Resource
    KafkaTemplate kafkaTemplate;

    @Test
    public void testKafkaSendMsg() {
        int i=0;
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (Exception e){
                    log.error(e.getMessage());
                }
                i++;
                String msg = "半缘修道半缘君：" + i;
                //发送消息
                kafkaTemplate.send("ordertopic", i + "", msg);
                System.out.println("发送："+i);
            }
    }

}
