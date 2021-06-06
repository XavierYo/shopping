package com.xavier.service.impl;
import com.xavier.dao.CategoryDao;
import com.xavier.domain.Category;
import com.xavier.service.CategoryService;
import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public int getCatID(String name) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getCategoryID(name);
    }

    @Override
    public boolean insCategory(String name,int seller) throws SQLException{
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.insCategory(name,seller);
    }

    @Override
    public String getCatName(int id) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getCategoryName(id);
    }

    @Override
    public Category getCategory(String name) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getCategory(name);
    }

    @Override
    public Category getCategory(int category_id) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getCategory(category_id);
    }

    @Override
    public List<Category> getCategoriesBySeller(int seller_id) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getCategoriesBySeller(seller_id);
    }

    @Override
    public boolean isInCharge(int seller, String category_name) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        for(Category category:categoryDao.getCategoriesBySeller(seller)){
            if(category.getCategory_name().equals(category_name))
                return true;
        }
        return false;
    }

    @Override
    public boolean isInCharge(int seller, int category_id) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        for(Category category:categoryDao.getCategoriesBySeller(seller)){
            if(category.getCategory_id()==category_id)
                return true;
        }
        return false;
    }

}
