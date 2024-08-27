package com.ohgirrafers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgirrafers.common.JDBCTemplate.close;
import static com.ohgirrafers.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {
        // 성씨를 입력받아 해당성을 가진 사원조회
        // SELECT EMP_ID,EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?,'%');

       // String Ssc = sc.nextLine();
        Connection con = getConnection();
        PreparedStatement prstmt = null;
        ResultSet rset = null;
                        // 성씨받고 퍼센트
        Scanner sc = new Scanner(System.in);
        System.out.println(" ");
        String Ssc = sc.nextLine();
       // String Ssc = sc.nextLine();
        try {
            prstmt = con.prepareStatement("SELECT EMP_ID,EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?,'%')");
            prstmt.setString(1,Ssc); // 무 ㄹ음표가 없으면 셋스트링따위필요없다
            rset = prstmt.executeQuery();

            while(rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       finally {
            close(con);
            close(prstmt);
            close(rset);
        }


    }
}
