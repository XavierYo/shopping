package com.xavier.service.impl;
import com.xavier.service.CategoryService;
import com.xavier.utils.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public int getCatID(String name) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM category where category_name='"+name+"'";
        ResultSet rs=dbc.query(sql);
        try{
            if(rs.next()){
                return rs.getInt("category_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;//查找失败
    }

    @Override
    public void insCategory(String name) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="INSERT INTO category (category_name) VALUES('"+name+"')";
        int rs_update=dbc.update(sql);
    }

    @Override
    public String getCatName(int id) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM category where category_id="+id;
        ResultSet rs=dbc.query(sql);
        try{
            if(rs.next()){
                return rs.getString("category_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
