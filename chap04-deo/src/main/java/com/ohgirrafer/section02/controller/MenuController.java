package com.ohgirrafer.section02.controller;

import com.ohgirrafer.section02.dao.MenuDAO;
import com.ohgirrafer.section02.dto.MenuDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ohgirrafer.common.JDBCTemplate.*;
import static com.ohgirrafer.common.JDBCTemplate.getConnection2;

public class MenuController {


    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml");


    public void findMaxCode()
    {
        int result = menuDAO.selectLastMenuCode(getConnection2());
        System.out.println("최신 메뉴 코드 : "+result );
    }
    public void findAllCategoryList()
    {
        List<Map<Integer, String>> categoryList = menuDAO.selectAllCategoryList(getConnection2());
        System.out.println("카테고리 리스트 : " + categoryList);
    }
    public void findinsertMenu()
    {
        MenuDTO result = menuDAO.insertMenu(getConnection2());
        System.out.println("성공 : " + result);
    }

}
