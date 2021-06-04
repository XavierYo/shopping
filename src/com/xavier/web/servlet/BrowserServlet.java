package com.xavier.web.servlet;

import com.xavier.domain.Item;
import com.xavier.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/BrowserServlet")
public class BrowserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String temp_id = request.getParameter("selected_id");
        int category_id;
        if(temp_id == null || temp_id.equals("")) {
            category_id = 0;
        } else {
            category_id = Integer.parseInt(temp_id);
        }
        ItemServiceImpl itemService=new ItemServiceImpl();
        List<Item> items= null;
        try {
            items = itemService.getItemsByCatID(category_id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getSession().setAttribute("items_searchRes", items);

            //跳转到查找结果的页面
        request.getRequestDispatcher("browser.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
