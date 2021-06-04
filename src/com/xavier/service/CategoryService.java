package com.xavier.service;

import java.sql.SQLException;

public interface CategoryService {
    int getCatID(String name) throws SQLException;
    boolean insCategory(String name) throws SQLException;
    String getCatName(int id) throws SQLException;
}
