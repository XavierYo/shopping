package com.xavier.web.servlet;
import com.xavier.domain.User;
import com.xavier.service.CartService;
import com.xavier.service.impl.CartServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/DelCartServlet")
public class DelCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User existUser=(User)request.getSession().getAttribute("existUser");
        CartService cs=new CartServiceImpl();
        cs.delCart(existUser.getUserID());
        response.sendRedirect("cart.jsp"); //重定向至主页
    }
}