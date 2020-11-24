package com.xavier.web.servlet;

import com.xavier.domain.Order;
import com.xavier.service.OrderService;
import com.xavier.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String temp_id = request.getParameter("selected_id");
        int order_id;
        if(temp_id == null || temp_id.equals("")) {
            order_id = 0;
        } else {
            order_id = Integer.parseInt(temp_id);
        }
        OrderService orderService=new OrderServiceImpl();
        Order order=orderService.getOrder(order_id);
        request.getSession().setAttribute("order_detail",order);
        request.getRequestDispatcher("order_detail.jsp").forward(request,response);
    }
}
