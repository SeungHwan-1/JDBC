package com.ohgirrafers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgirrafers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        //ResultSet rset = null; // 인서트업데이트딜리트는 1또는0으로만 반환해서 필요없음 그래서 int로
        int result = 0;

        Properties prop = new Properties(); // 파일객체
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml")); //파일넣기??
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));
            pstmt.setString(1,"쌀국수");
            pstmt.setInt(2,11900);
            pstmt.setInt(3, 4);
            pstmt.setString(4,"Y");

        result = pstmt.executeUpdate(); // 이거질문하기셀렉ㅃㅐ고다 업데이트?정수반환

            System.out.println("결과 : " + result); // 데이터베이스에 갔는데 값이 안들어갔을때.


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            close(pstmt);
            close(con);
        }


    }
}
