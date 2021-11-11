package com.example.springboot1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class test {

    public static void main(String args[]) {
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=springboot-vue;user=dxm;password=123";//sa身份连接

        // Declare the JDBC objects.
        Connection con = null;  //会话连接
        Statement stmt = null;  //用于执行静态SQL语句并返回其生成的结果的对象。
        ResultSet rs = null;  //数据库结果集的数据表

        try {
            //1-注册驱动器,驱动管理器类加载SQLServerDriver类的静态方法，如果没有添加这个驱动，则创建这个驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //2-与数据源获得连接
            con = DriverManager.getConnection(url);

            //3-创建一个Statement对象，用于将SQL语句发送到数据库
            stmt = con.createStatement();

            //4- SQL语句
            String SQL = "SELECT  * FROM springboot-vue";

            //5-执行SQL,返回数据
            rs = stmt.executeQuery(SQL);

            //6-遍历
            while (rs.next()) {

                System.out.println(rs.getString(1) + "," + rs.getString(2).trim()+"," + rs.getString(3));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception e) {
                }
        }
    }
}
