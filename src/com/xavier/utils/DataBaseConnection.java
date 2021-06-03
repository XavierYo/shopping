package com.xavier.utils;

import java.sql.*;

public class DataBaseConnection {
    Connection conn=null;
    Statement st=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    int rs_update;

    //驱动名称
    private static final String driverName="com.mysql.cj.jdbc.Driver";
    //数据库的地址(通过端口和SID找到对应的数据库)
    private static final String url="jdbc:mysql://47.115.63.32:3306/shopdb?&useSSL=false&serverTimezone=UTC&characterEncoding=utf-8";
    //数据库登录用户名
    private static final String userDB="manager";
    //数据库登录密码
    private static final String pwdDB="6789";

    public Connection getConn(){
        try {
            Class.forName(driverName);
            conn= DriverManager.getConnection(url,userDB,pwdDB);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet query(String sql){
        try{
            conn=getConn();
            st=conn.createStatement();
            System.out.println("执行sql:"+sql);
            rs=st.executeQuery(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rs;
    }

    public int update(String sql){
        try {
            conn = getConn();
            st = conn.createStatement();
            System.out.println("执行sql:"+sql);
            rs_update = st.executeUpdate(sql);
            return rs_update;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //替换
    public void update(String sql, String[] args){
        //TODO 复杂update语句
    }

    public int delete(String sql){
        try {
            conn = getConn();
            st = conn.createStatement();
            rs_update = st.executeUpdate(sql);
            return rs_update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void close(){

        try {
            if(rs!=null) {
                rs.close();
            }
            if(st!=null) {
                st.close();
            }
            if(ps!=null) {
                ps.close();
            }
            if(conn!=null) {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
