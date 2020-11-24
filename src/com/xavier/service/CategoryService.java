package com.xavier.service;

public interface CategoryService {
    int getCatID(String name);
    void insCategory(String name);
    String getCatName(int id);
}
