package cn.com.testorm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Demo03 {
    public static void test01() {
        //使用Javabean对象来封装一条记录
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Emp emp = null;
        try {
            coon = JDBCUtils.getMySqlCoon();
            ps = coon.prepareStatement("select empname,salary,age from emp where id = ?");
            ps.setObject(1,1);
            rs = ps.executeQuery();
            while(rs.next()) {
                emp = new Emp(rs.getString(1),rs.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,ps,coon);
        }
        System.out.println(emp.getEmpname()+"--"+emp.getSalary());
    }
    public static void test02() {
        //使用List<Javabean>存储多条记录
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Emp> list = new ArrayList<>();
        try {
            coon = JDBCUtils.getMySqlCoon();
            ps = coon.prepareStatement("select empname,salary,age from emp where id > ?");
            ps.setObject(1,1);
            rs = ps.executeQuery();
            while(rs.next()) {
                Emp emp = new Emp(rs.getString(1),rs.getDouble(2));
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,ps,coon);
        }
        for (Emp emp:list) {
            System.out.println(emp.getEmpname()+"--"+emp.getSalary());
        }
    }


    public static void main(String[] args) {
//      test01();
        test02();
    }
}
