package com.xavier.web.servlet;

import com.xavier.domain.Item;
import com.xavier.domain.Log;
import com.xavier.domain.Order;
import com.xavier.domain.User;
import com.xavier.service.LogService;
import com.xavier.service.OrderService;
import com.xavier.service.impl.LogServiceImpl;
import com.xavier.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

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
        Map<Item,Integer> order_detail=null;
        Order order = null;
        try {
            order = orderService.getOrder(order_id);
            order_detail=orderService.getOrderDetail(order_id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getSession().setAttribute("order",order);
        request.getSession().setAttribute("order_detail",order_detail);
        request.getRequestDispatcher("order_detail.jsp").forward(request,response);
        Log log = new Log();
        log.setOperate("查看订单详情："+order_id);
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
}
