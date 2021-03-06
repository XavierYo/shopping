package com.xavier.web.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xavier.domain.Item;
import com.xavier.domain.Log;
import com.xavier.domain.User;
import com.xavier.service.LogService;
import com.xavier.service.impl.ItemServiceImpl;
import com.xavier.service.impl.LogServiceImpl;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 接收数据:
        request.setCharacterEncoding("utf-8");
        String keyword = request.getParameter("keyWord");
        ItemServiceImpl itemService=new ItemServiceImpl();
        List<Item> searchRes= null;
        try {
            searchRes = itemService.getItemsByKeyword(keyword);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        // 显示结果:
        if(searchRes == null){
            request.setAttribute("msg", "查询结果为0");
            request.getRequestDispatcher("/search.jsp").forward(request, response);
        }else{
            // 保存查找结果
            request.getSession().setAttribute("items_searchRes", searchRes);
            //跳转到查找结果的页面
            request.getRequestDispatcher("search.jsp").forward(request,response);
        }
        Log log = new Log();
        log.setOperate("搜索商品,关键词："+keyword);
        if(request.getRemoteAddr()!=null){
            log.setIp(request.getRemoteAddr());
        }
        User existUser = (User)request.getSession().getAttribute("existUser");
        if(existUser!=null){
            log.setUser(existUser.getUser_id());
        }
        LogService logService = new LogServiceImpl();
        try {
            logService.writeLog(log);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}