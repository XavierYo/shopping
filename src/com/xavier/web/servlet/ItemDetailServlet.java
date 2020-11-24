package com.xavier.web.servlet;

import com.xavier.domain.Item;
import com.xavier.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ItemDetailServlet")
public class ItemDetailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String temp_id = req.getParameter("item_id");
        int item_id;
        if(temp_id == null || temp_id.equals("")) {
            item_id = 0;
        } else {
            item_id = Integer.parseInt(temp_id);
        }
        ItemServiceImpl itemService=new ItemServiceImpl();
        Item item=itemService.getItemByID(item_id);
        req.setAttribute("item_detail",item);
        req.getRequestDispatcher("/item_detail.jsp").forward(req, resp);
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
