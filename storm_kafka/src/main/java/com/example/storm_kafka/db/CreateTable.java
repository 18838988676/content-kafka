package com.example.storm_kafka.db;

import com.example.storm_kafka.centos.UserLog;

import java.sql.*;

public class CreateTable {
    public static Connection connection;
    public static PreparedStatement ps;
    public static ResultSet resultSet;
    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
      /*  init();
        createTable();*/
//        addData();
//        //deleteData();
//        searchData();
//        closeAll();
    }
    //JDBC的开发流程
    //创建连接对象
    public static void init()  {
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
    //关闭资源
    public static void closeAll() throws SQLException {
        if (resultSet!=null){
            resultSet.close();
        }
        if (ps!=null){
            ps.close();
        }
        if (connection!=null){
            connection.close();
        }
    }
    //创建表
    public static void createTable() throws SQLException {
        String sql001="create table \"orderinfo\" (\n" +
                "id  bigint not null primary key,\n" +
                "userid integer  ,\n" +
                "ordreid integer ,\n" +
                "username varchar(50),\n" +
                "goodsname varchar(50),\n" +
                "ordertime date,\n" +
                "allprice double ,\n" +
                "time_stap date\n" +
                ")";
        String sql02="create sequence \"my_sequence\" start with 1 incremented by 1;";

        String sql="create table DOG(ID bigint primary key,NAME varchar)";
        ps=connection.prepareStatement(sql02);
        int i=ps.executeUpdate();
        connection.commit();
        if (i==0){
            System.out.println("表创建成功！");
        }
    }
    //向表中存入数据
    public  void addData( UserLog userLog) throws SQLException {
        init();
        String sql="upsert  into \"orderinfo\" (id,userid,ordreid,username,goodsname,ordertime,allprice,time_stap)  values( next value for my_sequence,?, ?,?,?,now(),?,now());";
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
    //删除数据
    public static void deleteData() throws SQLException {
        String sql="delete from US_POP where CITY=?";
        ps=connection.prepareStatement(sql);
        ps.setString(1,"New York");
        int i = ps.executeUpdate();
        connection.commit();
        if (i>0){
            System.out.println("数据删除成功！");
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
}

