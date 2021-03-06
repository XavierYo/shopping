package com.xavier.web.servlet;

import com.xavier.domain.Log;
import com.xavier.domain.User;
import com.xavier.service.LogService;
import com.xavier.service.UserService;
import com.xavier.service.impl.LogServiceImpl;
import com.xavier.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //接收数据
        request.setCharacterEncoding("utf-8");
        String username =request.getParameter("username");
        String password =request.getParameter("password");
        String email =request.getParameter("email");
        //封装数据
        User user =new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        //处理数据
        UserService userService = new UserServiceImpl();
        boolean flag = false;
        try {
            flag = userService.regist(user);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        // 显示结果:
        if(!flag){
            // 注册失败
            request.setAttribute("msg", "注册失败，请确认注册信息");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }else{
            // 注册成功
            request.setAttribute("msg", "注册成功！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        Log log = new Log();
        log.setOperate("注册新用户："+username);
        if(request.getRemoteAddr()!=null){
            log.setIp(request.getRemoteAddr());
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
