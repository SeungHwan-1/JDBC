package com.ohgirrafers.section03.sqlinjection;

import java.sql.*;

import static com.ohgirrafers.common.JDBCTemplate.close;
import static com.ohgirrafers.common.JDBCTemplate.getConnection;

//단점이 뭐라고?? 단점이 머라궈???
//단점이 무ㅕㅓ라고?? 단순조회면 상관없는데
//where을 하면 특정 뭐를 조횔핟거나 문자열로
//다 문자열로 ㅇ릭어서 공격을
public class Application {
    private static String empId = "210";
    private static String empName = "'OR 1=1 AND EMP_ID = '201"; // 완전한 커리문일경우 어떤값을 입력받든 트루

    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '"+ empId + "' AND EMP_NAME = '"+empName+"'";

        System.out.println(query);

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if (rset.next()) {
                System.out.println(rset.getString("EMP_NAME") + "님을 환영합니다");

            }
            else
                System.out.println("there is no 회원");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            close(con);
            close(rset);
            close(stmt);
        }


    }
}
