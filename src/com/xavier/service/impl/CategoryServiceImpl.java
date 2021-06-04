package com.xavier.service.impl;
import com.xavier.dao.CategoryDao;
import com.xavier.service.CategoryService;
import java.sql.SQLException;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public int getCatID(String name) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getCategoryID(name);
    }

    @Override
    public boolean insCategory(String name) throws SQLException{
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.insCategory(name);
    }

    @Override
    public String getCatName(int id) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getCategoryName(id);
    }
}
