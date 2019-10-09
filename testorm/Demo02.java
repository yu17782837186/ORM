package cn.com.testorm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class Demo02 {
    public static void test01() {
        //使用Map来封装一条记录
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String,Object> row = new HashMap<>();//使用一个map封装一条记录
        try {
            coon = JDBCUtils.getMySqlCoon();
            ps = coon.prepareStatement("select empname,salary,age from emp where id = ?");
            ps.setObject(1,1);
            rs = ps.executeQuery();
            while(rs.next()) {
                row.put("empname",rs.getString(1));
                row.put("salary",rs.getDouble(2));
                row.put("age",rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,ps,coon);
        }
        //遍历map,就是遍历这一行的多列的信息
        for (String key:row.keySet()) {
            System.out.print(key+"--"+row.get(key)+"\t");
        }
    }
    public static void test02() {
        //使用List<Map>存储多条记录
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String,Object>> list = new ArrayList<>();//使用list存放多个map
        try {
            coon = JDBCUtils.getMySqlCoon();
            ps = coon.prepareStatement("select empname,salary,age from emp where id > ?");
            ps.setObject(1,1);
            rs = ps.executeQuery();
            Map<String,Object> row = new HashMap<>();
            while(rs.next()) {
                row.put("empname",rs.getString(1));
                row.put("salary",rs.getDouble(2));
                row.put("age",rs.getInt(3));
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,ps,coon);
        }
        //遍历list,就是遍历多个map
        for (Map<String,Object> row : list) {
            //遍历map
            for (String key : row.keySet()) {
                System.out.print(key + "--" + row.get(key) + "\t");
            }
            System.out.println();
        }
    }
    public static void test03() {

        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String,Map<String,Object>> maps = new HashMap<>();
        try {
            coon = JDBCUtils.getMySqlCoon();
            ps = coon.prepareStatement("select empname,salary,age from emp where id > ?");
            ps.setObject(1,1);
            rs = ps.executeQuery();
            Map<String,Object> row = new HashMap<>();
            while(rs.next()) {
                row.put("empname",rs.getString(1));
                row.put("salary",rs.getDouble(2));
                row.put("age",rs.getInt(3));
                maps.put(rs.getString(1),row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,ps,coon);
        }
        for (String empname:maps.keySet()) {
            Map<String,Object> row = maps.get(empname);
            for (String key:row.keySet()) {
                System.out.print(key+"--"+row.get(key)+"\t");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
//       test01();
//       test02();
        test03();
    }
}
