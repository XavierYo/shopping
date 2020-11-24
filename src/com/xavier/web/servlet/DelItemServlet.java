package com.xavier.web.servlet;

import com.xavier.service.ItemService;
import com.xavier.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DelItemServlet")
public class DelItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收数据:
        request.setCharacterEncoding("utf-8");
        String item_name = request.getParameter("item_name");//商品名字
        ItemService itemService=new ItemServiceImpl();
        boolean flag = itemService.delByName(item_name);
        // 显示结果:
        if(flag)
            request.setAttribute("msg", "删除成功！");
        else
            request.setAttribute("msg","删除失败！");
        request.getRequestDispatcher("/del_item.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
