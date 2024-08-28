package com.ohgirrafer.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgirrafer.common.JDBCTemplate.*;



public class Application {
    public static void main(String[] args) {
        Connection con = getConnection();
        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null; // 실행할 쿼리가 다르기떄문에
        PreparedStatement pstmt3 = null;
        ResultSet rset1 = null; // 셀렉트니까 필요함 결과 출력해야하니까.
        ResultSet rset2 = null;
      //  ResultSet rset3 = null;
        List<Map<Integer,String>> categoryList = null;
        int result = 0 ;
        int  result1 = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query = prop.getProperty("selectLastMenuCode");
            String query2 = prop.getProperty("selectAllCategoryList");
            String query3 = prop.getProperty("insertMenu");


            pstmt1 = con.prepareStatement(query);
            pstmt2 = con.prepareStatement(query2);
            pstmt3 = con.prepareStatement(query3);
            //pstmt3.setInt(1, 1);
            System.out.println("이름입력 :");
            String st = sc.nextLine();
            System.out.println("돈입력 :");
            int i = sc.nextInt();
            System.out.println("번호입력 :");
            int j = sc.nextInt();
            sc.nextLine();
            System.out.println("Y or N :");
            String st3 = sc.nextLine();

            pstmt3.setString(1, st);
            pstmt3.setInt(2, i);
            pstmt3.setInt(3, j);
            pstmt3.setString(4,st3);

            result1 = pstmt3.executeUpdate();
            System.out.println("결과 : " + result1);

            //-------------------------------------------------------------
            rset1 = pstmt1.executeQuery(); // Tq
            if (rset1.next()) {
                result = rset1.getInt("MAX(MENU_CODE)");
            }
            System.out.println("최신 메뉴 코드 : " + result);

            rset2 = pstmt2.executeQuery();
            categoryList = new ArrayList<>();
            while (rset2.next()) {
                Map<Integer,String> category = new HashMap<>();
                category.put(rset2.getInt("CATEGORY_CODE"), rset2.getString("CATEGORY_NAME"));
                categoryList.add(category);

            }
            System.out.println("카테고리 리스트 : " + categoryList);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeConnection(con);
            closeConnection(pstmt1);
            closeConnection(rset1);
            closeConnection(pstmt2);
            closeConnection(rset2);
            closeConnection(pstmt3);
           // closeConnection(rset3);
        }


    }
}
