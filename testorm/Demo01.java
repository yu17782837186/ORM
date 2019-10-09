package cn.com.testorm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Demo01 {
    public static void test01() {
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object[] objs = null;
        try {
            coon = JDBCUtils.getMySqlCoon();
            ps = coon.prepareStatement("select empname,salary,age from emp where id = ?");
            ps.setObject(1,1);

            rs = ps.executeQuery();
            objs = new Object[3];//一个Objcet数组封装了一条记录的信息
            while(rs.next()) {
//                System.out.println(rs.getString(1)+"--"+rs.getDouble(2)+"--"+rs.getInt(3));
                objs[0] = rs.getString(1);
                objs[1] = rs.getDouble(2);
                objs[2] = rs.getInt(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,ps,coon);
        }
        System.out.println(objs[0]+"--"+objs[1]+"--"+objs[2]);
    }
    public static void test02() {
        //使用List<Object[]>存储多条记录
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object[]> list = new ArrayList<>();
        try {
            coon = JDBCUtils.getMySqlCoon();
            ps = coon.prepareStatement("select empname,salary,age from emp where id > ?");
            ps.setObject(1,1);

            rs = ps.executeQuery();
            Object[] objs = new Object[3];//一个Objcet数组封装了一条记录的信息
            while(rs.next()) {
//                System.out.println(rs.getString(1)+"--"+rs.getDouble(2)+"--"+rs.getInt(3));
                objs[0] = rs.getString(1);
                objs[1] = rs.getDouble(2);
                objs[2] = rs.getInt(3);
                list.add(objs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,ps,coon);
        }
        for (Object[] objs:list) {
            System.out.println(objs[0]+"--"+objs[1]+"--"+objs[2]);
        }
    }

    public static void main(String[] args) {
//      test01();
        test02();
    }
}
