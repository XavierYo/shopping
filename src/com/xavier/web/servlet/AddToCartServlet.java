package com.xavier.web.servlet;

import com.xavier.domain.Cart;
import com.xavier.domain.Item;
import com.xavier.domain.User;
import com.xavier.service.impl.CartServiceImpl;
import com.xavier.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //接收数据
        //TODO 可以选择加入购物车的数量
        int number=1;
        request.setCharacterEncoding("utf-8");
        String temp_id = request.getParameter("item_id");
        int item_id;
        if(temp_id == null || temp_id.equals("")) {
            item_id = 0;
        } else {
            item_id = Integer.parseInt(temp_id);
        }
        ItemServiceImpl itemService=new ItemServiceImpl();
        Item item=itemService.getItemByID(item_id);

        //处理数据

        //先判断是否登录
        boolean isLogin=false;
        User user=(User)request.getSession().getAttribute("existUser");
        if(user!=null){
            isLogin=true;
        }
        if(isLogin){
            User existUser=(User)request.getSession().getAttribute("existUser");
            CartServiceImpl cartService=new CartServiceImpl();
            //判断是否已存在购物车
            Cart cart=cartService.getCart(existUser.getUserID(),item_id);
            if(cart!=null){
                System.out.println("AddToCartServlet: cart_id="+cart.getCart_id());
                cartService.updateCart(cart.getCart_id(),number+cart.getNumber());
            }else {
                cart =new Cart();
                cart.setUser_id(user.getUserID());
                cart.setNumber(number);
                cart.setItem(item);
                cartService.insertCart(cart);
            }
            request.setAttribute("msg","添加成功！");
            //重定向至购物车页面
            request.getRequestDispatcher("cart.jsp").forward(request,response);
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }



    }
}
