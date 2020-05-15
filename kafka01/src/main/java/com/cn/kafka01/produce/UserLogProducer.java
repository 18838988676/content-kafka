package com.cn.kafka01.produce;

import com.alibaba.fastjson.JSON;
import com.cn.kafka01.bean.UserLog;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.internals.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Controller("/st")
@Slf4j
public class UserLogProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    // 名字
    private static ArrayList<String> namesList=new ArrayList();
    //水果
    private static ArrayList<String> fult=new ArrayList<>(22);
    private static AtomicInteger orderId=new AtomicInteger();
    private static AtomicBoolean isRun=new AtomicBoolean(true);

    private static ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),new ThreadFactoryBuilder()
                .setNameFormat("logread-%d").build());

    @ResponseBody
    @RequestMapping("/st")
    public String  index(){
        isRun.getAndSet(true);
        log.info("start........................");
        threadPoolExecutor.submit(()->{sendLog1();});
        threadPoolExecutor.submit(()->{sendLog2();});
        threadPoolExecutor.submit(()->{sendLog3();});
        threadPoolExecutor.submit(()->{sendLog4();});
       return "ok";
    }
    @ResponseBody
    @RequestMapping("/end")
    public String end(){
        boolean os=isRun.getAndSet(false);
        log.info("old:{}",os);
        return "ok";
    }


    public void sendLog1(){
        while (isRun.get()) {
            Random random = new Random();
            int nameId = random.nextInt(50);
            UserLog userLog = new UserLog().setUserid(nameId).setUsername(namesList.get(nameId)).setOrderId(orderId.getAndIncrement()).setAllPrice(String.format("%.3f", random.nextDouble() * random.nextInt(500))).setGoodsName(fult.get(random.nextInt(21))).setOrderTime(System.currentTimeMillis());
//        kafkaTemplate.send("ordertopic",JSON.toJSONString(userLog));
            log.info("{}", JSON.toJSONString(userLog));
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            }catch (Exception e){

            }
        }
    }
    public void sendLog2(){
        while (isRun.get()) {
            Random random = new Random();
            int nameId = random.nextInt(50);
            UserLog userLog = new UserLog().setUserid(nameId).setUsername(namesList.get(nameId)).setOrderId(orderId.getAndIncrement()).setAllPrice(String.format("%.3f", random.nextDouble() * random.nextInt(500))).setGoodsName(fult.get(random.nextInt(21))).setOrderTime(System.currentTimeMillis());
//        kafkaTemplate.send("ordertopic",JSON.toJSONString(userLog));
            log.info("{}", JSON.toJSONString(userLog));
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (Exception e){

            }
        }
    }

    public void sendLog3(){
        while (isRun.get()) {
            Random random = new Random();
            int nameId = random.nextInt(50);
            UserLog userLog = new UserLog().setUserid(nameId).setUsername(namesList.get(nameId)).setOrderId(orderId.getAndIncrement()).setAllPrice(String.format("%.3f", random.nextDouble() * random.nextInt(500))).setGoodsName(fult.get(random.nextInt(21))).setOrderTime(System.currentTimeMillis());
//        kafkaTemplate.send("ordertopic",JSON.toJSONString(userLog));
            log.info("{}", JSON.toJSONString(userLog));
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            }catch (Exception e){

            }
        }
    }

    public void sendLog4(){
        while (isRun.get()) {
            Random random = new Random();
            int nameId = random.nextInt(50);
            UserLog userLog = new UserLog().setUserid(nameId).setUsername(namesList.get(nameId)).setOrderId(orderId.getAndIncrement()).setAllPrice(String.format("%.3f", random.nextDouble() * random.nextInt(500))).setGoodsName(fult.get(random.nextInt(21))).setOrderTime(System.currentTimeMillis());
//        kafkaTemplate.send("ordertopic",JSON.toJSONString(userLog));
            log.info("{}", JSON.toJSONString(userLog));
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            }catch (Exception e){

            }
        }
    }

    @PostConstruct
    public void init(){
        namesList.add("刘亦菲");
        namesList.add("舒畅");
        namesList.add("佟丽娅");
        namesList.add("毛晓彤");
        namesList.add("唐艺昕");
        namesList.add("郭珍霓");
        namesList.add("百万茜");
        namesList.add("韩雪");
        namesList.add("李沁");
        namesList.add("金晨");
        namesList.add("陈瑶");
        namesList.add("娜扎");
        namesList.add("郭晓婷");
        namesList.add("苏青");
        namesList.add("程媛媛");
        namesList.add("高洋");
        namesList.add("刘诗诗");
        namesList.add("景甜");
        namesList.add("张钧甯");
        namesList.add("桂纶镁");
        namesList.add("杜若溪");
        namesList.add("韩瑜");
        namesList.add("李彩桦");
        namesList.add("度林允儿");
        namesList.add("申珉熙");
        namesList.add("甘婷婷");
        namesList.add("何琢言");
        namesList.add("张萌");
        namesList.add("张嘉倪");
        namesList.add("白冰");
        namesList.add("胡冰卿");
        namesList.add("copy黎姿");
        namesList.add("王祖贤");
        namesList.add("张敏");
        namesList.add("李若彤");
        namesList.add("朱茵");
        namesList.add("范文芳");
        namesList.add("温碧霞");
        namesList.add("邱淑贞");
        namesList.add("梁小冰");
        namesList.add("周慧敏");
        namesList.add("戚美珍");
        namesList.add("黎美娴");
        namesList.add("曾华倩");
        namesList.add("赵雅芝");
        namesList.add("张玉嬿");
        namesList.add("谢宁");
        namesList.add("袁洁莹");
        namesList.add("叶全真");
        namesList.add("梅艳芳");


        fult.add(" 西瓜 ");
        fult.add(" 葡萄 ");
        fult.add(" 芒果 ");
        fult.add(" 提子 ");
        fult.add(" 柚子 ");
        fult.add(" 香蕉 ");
        fult.add(" 山楂 ");
        fult.add(" 苹果 ");
        fult.add(" 柠檬 ");
        fult.add(" 金桔 ");
        fult.add(" 毛桃 ");
        fult.add(" 猕猴桃 ");
        fult.add(" 无花果 ");
        fult.add(" 椰子 ");
        fult.add(" 杨桃 ");
        fult.add(" 杏子 ");
        fult.add(" 橙子 ");
        fult.add(" 沃柑 ");
        fult.add(" 释迦果 ");
        fult.add(" 蓝莓 ");
        fult.add(" 荔枝");
    }




}
