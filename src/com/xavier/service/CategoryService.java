package com.xavier.service;

import com.xavier.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    int getCatID(String name) throws SQLException;
    boolean insCategory(String name,int seller) throws SQLException;
    String getCatName(int id) throws SQLException;
    Category getCategory(String name) throws SQLException;
    Category getCategory(int category_id) throws SQLException;
    List<Category> getCategoriesBySeller(int seller_id) throws SQLException;
    boolean isInCharge(int seller,String category_name) throws SQLException;
    boolean isInCharge(int seller,int category_id) throws SQLException;
}
