package com.ohgirrafers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgirrafers.common.JDBCTemplate.close;
import static com.ohgirrafers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        /*
        preparedstatement
        - 완성된 커리문과 미완성된 쿼리문을 모두 사용할 수 있다. 그냥 Statement 미완성쿼리문을 사용할 수없다.
        - 미완성 쿼리 = 위치폴더를 사용한 쿼리문 (9) 공격에 취약하다.
         */

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?");
            pstmt.setString(1,"200"); // ?첫번째라 1번
            rset = pstmt.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getString(1) + " " + rset.getString(2)); // 전부출력하는것
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            close(con);
            close(pstmt);
            close(rset);
        }
    }
}
