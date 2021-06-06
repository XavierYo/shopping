package com.xavier.web.servlet;

import com.xavier.domain.Log;
import com.xavier.domain.User;
import com.xavier.service.LogService;
import com.xavier.service.impl.LogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //销毁session
        session.invalidate();
        String path = request.getContextPath();
        response.sendRedirect("index.jsp"); //返回主页
        Log log = new Log();
        log.setOperate("注销");
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
