package com.ohgirrafer.section02.dao;

import com.ohgirrafer.section02.dto.MenuDTO;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;

import static com.ohgirrafer.common.JDBCTemplate.*;


public class MenuDAO {
    //데이터 엑세스 오브젝트 - 데이터베이스와 상호작용을 할 클래스
    //쿼리문을 다 여기다 작성할거임그런거

    private Properties prop = new Properties(); //파일 불러오기위한 객체?메뉴디비접근

    public MenuDAO(String url) {

        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public int selectLastMenuCode(Connection con)
    {
        Statement stmt = null;
        ResultSet rset = null;
        int maxCode = 0;

        String query = prop.getProperty("selectLastMenuCode"); //그쿼리문가져오는거

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next())
            {
                maxCode = rset.getInt("MAX(MENU_CODE)");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeConnection(con);
            closeConnection(stmt);
            closeConnection(rset);
        }
        return maxCode;
    }
    public List<Map<Integer, String>> selectAllCategoryList(Connection con)
    {
        List<Map<Integer,String>> categoryList = null;
        Statement stmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectAllCategoryList");

        try {
            stmt =con.createStatement();
            rset = stmt.executeQuery(query);
            categoryList = new ArrayList<>();
            while (rset.next()) {
                Map<Integer,String> category = new HashMap<>();
                category.put(rset.getInt("CATEGORY_CODE"), rset.getString("CATEGORY_NAME"));
                categoryList.add(category);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeConnection(con);
            closeConnection(stmt);
            closeConnection(rset);
        }
        return categoryList;

    }
    public MenuDTO insertMenu(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        MenuDTO menuDTO = new MenuDTO();
        int result = 0;
        PreparedStatement stmt = null;
        String query = prop.getProperty("insertMenu");
        try {

            stmt = con.prepareStatement(query);
            System.out.println("이름입력 :");
            String st = sc.nextLine();
            System.out.println("돈입력 :");
            int i = sc.nextInt();
            System.out.println("번호입력 :");
            int j = sc.nextInt();
            sc.nextLine();
            System.out.println("Y or N :");
            String st3 = sc.nextLine();

            stmt.setString(1, st);
            stmt.setInt(2, i);
            stmt.setInt(3, j);
            stmt.setString(4,st3);
            menuDTO.setCategoryCode(j);
            menuDTO.setMenuName(st);
            menuDTO.setPrice(i);
            menuDTO.setStatus(st3);
            result = stmt.executeUpdate();
            System.out.println("결과 : " + result);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeConnection(con);
            closeConnection(stmt);

        }

        return menuDTO;
    }
}
