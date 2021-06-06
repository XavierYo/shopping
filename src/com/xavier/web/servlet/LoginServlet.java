package com.xavier.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xavier.domain.Log;
import com.xavier.domain.User;
import com.xavier.service.LogService;
import com.xavier.service.UserService;
import com.xavier.service.impl.LogServiceImpl;
import com.xavier.service.impl.UserServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 接收数据:
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 处理数据:完成登陆.
        UserService userService = new UserServiceImpl();
        try {
            User existUser = userService.login(username, password);
            // 显示结果:
            if(existUser == null){
                // 登录失败
                request.setAttribute("msg", "用户名或密码错误!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }else{
                // 登录成功
                // 将用户信息保存:
                request.getSession().setAttribute("existUser", existUser);
                if(existUser.getRole()==2){
                    // 管理员界面
                    response.sendRedirect(request.getContextPath()+"/admin.jsp");
                }else if(existUser.getRole()==1){
                    // 销售人员界面
                    response.sendRedirect(request.getContextPath()+"/manage.jsp");
                }else {
                    // 普通用户：转到首页
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                }

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        Log log = new Log();
        log.setOperate("用户登录");
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