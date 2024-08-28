package com.ohgirrafer.section02;

import com.ohgirrafer.section02.controller.MenuController;

import java.util.Scanner;

public class Apllication {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MenuController menuController = new MenuController();

        while (true) {
            System.out.println("사용할 기능을 선택해주세요");
            System.out.println("1. 가장 최신 메뉴 코드 조회");
            System.out.println("2. 모든 카테고리 목록 조회");
            System.out.println("3. 메뉴 등록");
            System.out.println("9. 프로그램 종료");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: menuController.findMaxCode(); break;
                case 2: menuController.findAllCategoryList(); break;
                case 3: menuController.findinsertMenu();break;
                case 9: break;
                default:

                    break;
            }
        }
    }
}
