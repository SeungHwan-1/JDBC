package com.ohgirrafers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgirrafers.common.JDBCTemplate.close;
import static com.ohgirrafers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {
        /*
        * 사용자가 원하는 메뉴를 등록할 수 있도록 만들어 주세요.
        * 등록이 완료되면 성공, 실패하면 실패라고 출력해주세요.
         */
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        //ResultSet rset = null; // 인서트업데이트딜리트는 1또는0으로만 반환해서 필요없음 그래서 int로
        int result = 0;
        Properties prop = new Properties(); // 파일객체
        Scanner sc = new Scanner(System.in);
        System.out.println("이름");
        String name = sc.nextLine();
        System.out.println("가격");
        int price = sc.nextInt();
        System.out.println("1. 양식 2. 일식 3. 한식 4. 똠얌꿍");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("파는중이면 Y 아니면 N");
        String sYn = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml")); //파일넣기??
            pstmt = con.prepareStatement(prop.getProperty("insertMenu")); // 키값으로 조회해서 그안에있는거 생성
            pstmt.setString(1,name);
            pstmt.setInt(2,price);
            pstmt.setInt(3, choice);
            pstmt.setString(4,sYn);

            result = pstmt.executeUpdate(); // 이거질문하기셀렉ㅃㅐ고다 업데이트?정수반환

            if(result == 1)
                System.out.println("성공"); // 데이터베이스에 갔는데 값이 안들어갔을때.
            else
                System.out.println("실패");

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
