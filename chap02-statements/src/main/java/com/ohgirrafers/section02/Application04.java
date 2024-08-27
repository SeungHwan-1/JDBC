package com.ohgirrafers.section02;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgirrafers.common.JDBCTemplate.close;
import static com.ohgirrafers.common.JDBCTemplate.getConnection;

public class Application04 {
    public static void main(String[] args) {


        //xml 파일을 이용한 조회
        //xml - 특수한 목적을 가진 마크업 언어
        // 여기있는걸 불러올거다
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("성씨를 입력해주세요");
        String empName = sc.nextLine();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgirrafers/mapper/Employee-quey.xml"));
            //파일불러오기
            pstmt = con.prepareStatement(prop.getProperty("selectEmpByName")); // 키값으로불러오는것
            pstmt.setString(1, empName);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString(1)+ " " + rset.getString(2)+
                        " " + rset.getString(3));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            close(con);
            close(pstmt);
            close(rset);
        }


    }
}
