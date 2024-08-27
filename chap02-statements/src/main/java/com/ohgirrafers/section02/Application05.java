package com.ohgirrafers.section02;

import com.ohgirrafers.DTO.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgirrafers.common.JDBCTemplate.close;
import static com.ohgirrafers.common.JDBCTemplate.getConnection;

public class Application05 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();
        EmployeeDTO selectEmp = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgirrafers/mapper/Employee-quey.xml"));
            pstmt = con.prepareStatement(prop.getProperty("selectEmpByMoney")); // 키값으로불러오는것
            rset = pstmt.executeQuery();

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
                System.out.println("selectEmp = " + selectEmp);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            close(con);
            close(pstmt);
            close(rset);
        }
        //파일불러오기

    }
}
