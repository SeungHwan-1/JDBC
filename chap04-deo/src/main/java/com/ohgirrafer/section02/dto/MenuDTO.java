package com.ohgirrafer.section02.dto;

public class MenuDTO {
    private String menuName;
    private int price;
    private int categoryCode;
    private String status;

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuName='" + menuName + '\'' +
                ", price=" + price +
                ", categoryCode='" + categoryCode + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
       if(price <= 0)
           System.out.println("음수가입력됨..");
        else
         this.price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public MenuDTO menuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

}
