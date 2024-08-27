package com.ohgirrafers.common;

import com.mysql.cj.protocol.Resultset;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {
        Connection con = null;
        Properties props = new Properties();
        try {
            props.load(new FileReader("src/main/resources/connection-info.properties"));//c이파일안에있는것 참조

            String url = props.getProperty("url"); // 꺼내오는것 가져와서 변수에 저장
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            con = DriverManager.getConnection(url,user,password); // 겟커넥션해서 설정정보를 입력한것 토대로 입력


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void close(Statement stmt) {
        try {
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void close(ResultSet rset) {
        try {
            rset.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
