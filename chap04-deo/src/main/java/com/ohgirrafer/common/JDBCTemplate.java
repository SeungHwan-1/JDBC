package com.ohgirrafer.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {
    /*
    singleton관리
    1. jDBC 커넥션은 리소스를 많이 소모하는 객체이다.
    2. 싱글톤 패턴을 적용하면 커넥션을 재사용 할 수 있어
    연결과 해제에 드는 리소스 소모를 줄일 수 있다,.!!
    사람많이쓰면 동시성문제 등 소규모서비스때만사용한다. 거의 안쓴다는느낌이네.
    단점 성능 커넥션 관리 문제ㅐ
    트랜잭션 상호 간섭 문제 등 동시성문제 나중에 제이피이라는걸 배운다한다 한 11월 쯤에 스프링배우면서한데요.
     */
    private static Connection con;

    public static Connection getConnection() {
        Properties prop = new Properties();
        try {
            if (con == null) {
                prop.load(new FileReader("src/main/resources/config/connection-info.properties")); //읽으려고
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                con = DriverManager.getConnection(url,user,password);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
    public static Connection getConnection2()
    {
        Connection con = null;
        Properties prop = new Properties();
        try {

                prop.load(new FileReader("src/main/resources/config/connection-info.properties"));
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                con = DriverManager.getConnection(url,user,password);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }


    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(Statement stmt) {
        try {
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(ResultSet Rset) {
        try {
            Rset.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}