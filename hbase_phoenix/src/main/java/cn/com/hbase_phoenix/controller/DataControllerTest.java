//package cn.com.hbase_phoenix.controller;
//
//import cn.com.hbase_phoenix.service.DataService;
//import cn.com.hbase_phoenix.vo.ResultVO;
//import org.junit.CreateTable;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @autor kevin.dai
// * @Date 2017/12/26
// */
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class DataControllerTest {
//
//    @Autowired
//    private DataService dataService;
//
//
//
//   @CreateTable
//    public void query(){
//       List<Map<String, Object>> query = dataService.query();
//
//   }
//
//
//    @CreateTable
//    public void count(){
//        int i = dataService.countDept();
//    }
//
//
//    @CreateTable
//    public void update(){
//        ResultVO update = dataService.update();
//    }
//
//
//    @CreateTable
//    public void add(){
//        ResultVO add = dataService.add();
//    }
//
//
//    @CreateTable
//    public void delete(){
//         dataService.delete();
//    }
//
//
//
//
//
//}
