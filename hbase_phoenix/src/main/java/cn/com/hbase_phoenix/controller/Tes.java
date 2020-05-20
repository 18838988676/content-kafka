package cn.com.hbase_phoenix.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Tes {

    public static void main(String args[]) {

        Connection connection = null;

        Statement statement = null;

        try {

            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");

            connection = DriverManager.getConnection("jdbc:phoenix:192.168.1.120:2171", "", "");

            statement = connection.createStatement();

            statement.execute("upsert into ORG_DEPT_NC (id,NAME ) VALUES ('8888888888888','dktest')");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                connection.close();

                statement.close();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}
