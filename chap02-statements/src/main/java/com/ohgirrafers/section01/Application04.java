package com.ohgirrafers.section01;

import com.ohgirrafers.DTO.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgirrafers.common.JDBCTemplate.*;

public class Application04 {
    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;
        EmployeeDTO selectEmp = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("조회하실 사번을 입력 해주세요");
        String empId = sc.nextLine();

        String query = "select * from employee where emp_id = '" + empId + "'";
        //System.out.println(query);
        try {
            stmt = con.createStatement(); // 이건 만드는것
            rset = stmt.executeQuery(query);

           while(rset.next()) {
                selectEmp = new EmployeeDTO();

                selectEmp.setEmpId(rset.getString("EMP_ID"));
                selectEmp.setEmpName(rset.getString("EMP_NAME"));
                selectEmp.setEmpNo(rset.getString("EMP_NO"));
                selectEmp.setEmail(rset.getString("EMAIL"));
                selectEmp.setPhone(rset.getString("PHONE"));
                selectEmp.setDeptCode(rset.getString("DEPT_CODE"));
                selectEmp.setJobCode(rset.getString("JOB_CODE"));
                selectEmp.setSalLevel(rset.getString("SAL_LEVEL"));
                selectEmp.setSalary(rset.getInt("SALARY"));
                selectEmp.setBonus(rset.getDouble("BONUS"));
                selectEmp.setManagerId(rset.getString("MANAGER_ID"));
                selectEmp.setHireDate(rset.getDate("HIRE_DATE"));
                selectEmp.setEntDate(rset.getDate("ENT_DATE"));
                selectEmp.setEntYn(rset.getString("ENT_YN"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            close(con);
            close(rset);
            close(stmt);
        }
        System.out.println("selectEmp = " + selectEmp);

    }
}
