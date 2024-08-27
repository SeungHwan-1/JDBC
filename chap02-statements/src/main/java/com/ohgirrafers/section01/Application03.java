package com.ohgirrafers.section01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgirrafers.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {
        // 이름을 입력받아서 해당 사원 아이디와 이름 조회
        //쿼리문도 변수로 따로 만들어서 넣어주세요

        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();


        Statement stmt = null;
        ResultSet rset = null;

        try {
            stmt = con.createStatement();
            String nameEmp = sc.nextLine();
            rset = stmt.executeQuery("SELECT * FROM EMPLOYEE" +
                    " WHERE EMP_NAME = '"+ nameEmp+"'" );

            while (rset.next()) {
                System.out.println(rset.getString("EMP_NAME")+
                        " "+rset.getString("EMP_ID"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
