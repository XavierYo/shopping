package com.xavier.web.servlet;

import com.xavier.domain.Item;
import com.xavier.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //接收数据
        //TODO 可以选择加入购物车的数量
        Integer number=1;
//        request.setCharacterEncoding("utf-8");
        String temp_id = request.getParameter("item_id");
        int item_id;
        if(temp_id == null || temp_id.equals("")) {
            item_id = 0;
        } else {
            item_id = Integer.parseInt(temp_id);
        }
        ItemServiceImpl itemService=new ItemServiceImpl();
        Item item= null;
        try {
            item = itemService.getItemByID(item_id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        //判断是否已存在购物车
        Map<Item,Integer> cart = (HashMap<Item,Integer>)request.getSession().getAttribute("cart");
        if (cart == null) {
            // 创建出购物车
            cart = new HashMap<Item, Integer>();
        }
        // 判断购物车是否已有该商品，有则+1
        number = cart.get(item);
        if (number == null) {
            System.out.println("number is null");
            number = 1;
        } else {
            number += 1;
        }
        // 加入购物车
        cart.put(item,number);
        request.getSession().setAttribute("cart",cart);
        request.setAttribute("msg","添加成功！");
        //重定向至购物车页面
        request.getRequestDispatcher("/cart.jsp").forward(request,response);


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request,response);
    }
}
