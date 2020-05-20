package com.example.hbase_crud.service;

import com.example.hbase_crud.config.HbaseTemplateService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.hadoop.hbase.HbaseOperations;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {


    //在需要的地方注入HBaseOperations接口，该接口的实现类是HBaseTemplate，通过这个类来操作HBase。
    @Autowired
    private HbaseTemplateService hbaseTemplateService;
    @org.junit.Test
    public  void test() {
        System.out.println(hbaseTemplateService);
        try {
            hbaseTemplateService.createTable("stu2","name");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(hbaseTemplateService);
    }

}
