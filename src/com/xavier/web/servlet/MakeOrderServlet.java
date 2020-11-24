package com.xavier.web.servlet;

import com.xavier.domain.Cart;
import com.xavier.domain.Order;
import com.xavier.domain.User;
import com.xavier.service.OrderService;
import com.xavier.service.impl.CartServiceImpl;
import com.xavier.service.impl.OrderServiceImpl;
import com.xavier.utils.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MakeOrderServlet")
public class MakeOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User existUser=(User)request.getSession().getAttribute("existUser");
        CartServiceImpl cs=new CartServiceImpl();
        List<Cart> carts=cs.getCartsByID(existUser.getUserID());
        //检查库存是否满足
        boolean flag=cs.checkCart(carts);
        //下订单函数
        if(flag){
            OrderService os=new OrderServiceImpl();
            Order order=os.makeOrder(carts);
            cs.delCart(carts.get(0).getUser_id());
            request.setAttribute("msg","下单成功！");
            //发邮件
            String emailMsg="订单号："+order.getOrder_id()
                    +"<br>创建时间："+order.getCreate_time().toString()
                    +"<br>用户ID："+existUser.getUserID()
                    +"<br>用户名称："+existUser.getUsername()
                    +"<br>金额："+order.getTotal_amount();
            //读取邮箱地址
            String email_address=request.getParameter("email_address");
            //若邮箱栏留空，则将从用户信息中获取邮箱地址
            if(email_address==null||email_address.equals("")){
                email_address=existUser.getEmail();
            }
            //已获取到邮箱信息
            if(email_address!=null&&!email_address.equals("")){
                SendMail.sendMail(email_address,emailMsg);
                request.setAttribute("msg","电子订单将发送到你的邮箱");
            }
            //重定向至订单页面
            request.getRequestDispatcher("order.jsp").forward(request,response);
        }
        else{
            request.setAttribute("msg","库存不足！");
            request.getRequestDispatcher("cart.jsp").forward(request,response);
        }
    }
}