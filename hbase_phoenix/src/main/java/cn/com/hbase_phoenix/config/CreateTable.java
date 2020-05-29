package cn.com.hbase_phoenix.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateTable {
    public static Connection connection;
    public static PreparedStatement ps;
    public static ResultSet resultSet;
    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
        init();
        index();
    }
    //JDBC的开发流程
    //创建连接对象
    public static void init()  {

        ini2t();
        try {
            System.out.println("1");
            //加载驱动类
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
            //编写连接字符串
            System.out.println("2");
//            String url="jdbc:phoenix:129.28.155.7:2181/hbase";
            String url="jdbc:phoenix:172.17.0.2:2181/hbase";
            connection=DriverManager.getConnection(url);
            System.out.println("3");
            System.out.println("连接成功！");
        }catch (Exception e){
            System.out.println("4");
            System.out.println("连接失败！");
            System.out.println(e);
        }

    }
    private static ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),new ThreadFactoryBuilder()
            .setNameFormat("logread-%d").build());

    public static String  index(){
        threadPoolExecutor.submit(()->{sendLog1();});
        return "ok";
    }
    //水果
    private static ArrayList<String> fult=new ArrayList<>(22);
    private static AtomicInteger orderId=new AtomicInteger();
    // 名字
    private static ArrayList<String> namesList=new ArrayList();
    public static void sendLog1(){
        while (true) {
            Random random = new Random();
            int nameId = random.nextInt(50);
            UserLog userLog = new UserLog().setUserid(nameId).setUsername(namesList.get(nameId)).setOrderId(orderId.getAndIncrement()).setAllPrice(String.format("%.3f", random.nextDouble() * random.nextInt(500))).setGoodsName(fult.get(random.nextInt(21))).setOrderTime(System.currentTimeMillis());
            try {
                addData2( userLog);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            }catch (Exception e){

            }
        }
    }
    //向表中存入数据
    public static void addData2( UserLog userLog) throws SQLException {
        String sql="upsert  into \"orderinfo\" (id,userid,ordreid,username,goodsname,ordertime,allprice,time_stap)  values( next value for my_sequence,?, ?,?,?,now(),?,now())";
        ps=connection.prepareStatement(sql);
        ps.setLong(1,userLog.getUserid());
        ps.setLong(2,userLog.getOrderId());
        ps.setString(3,userLog.getUsername());
        ps.setString(4,userLog.getGoodsName());
        ps.setDouble(5,Double.valueOf(userLog.getAllPrice()));
        int i=ps.executeUpdate();
        //提交事务
        connection.commit();
        if (i>0){
            System.out.println("数据插入成功！");
        }
    }


    //查看数据
    public static void searchData() throws SQLException {
        String sql="select * from DOG where ID>=?";
        ps=connection.prepareStatement(sql);
        ps.setInt(1,0);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            System.out.print("国家名称："+resultSet.getString("NAME"));
            System.out.print(";\t人口："+resultSet.getInt("ID"));
            System.out.println();
        }
    }
    public static void ini2t(){
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

