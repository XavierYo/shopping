package com.xavier.web.servlet;

import com.xavier.domain.Log;
import com.xavier.domain.User;
import com.xavier.service.ItemService;
import com.xavier.service.LogService;
import com.xavier.service.impl.ItemServiceImpl;
import com.xavier.service.impl.LogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DelItemServlet")
public class DelItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收数据:
        request.setCharacterEncoding("utf-8");
        int item_id = Integer.parseInt(request.getParameter("item_id"));//商品id
        ItemService itemService=new ItemServiceImpl();
        boolean flag = false;
        try {
            flag = itemService.delByID(item_id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        Log log = new Log();
        log.setOperate("删除商品:"+item_id);
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

        // 显示结果:
        if(flag)
            request.setAttribute("msg", "删除成功！");
        else
            request.setAttribute("msg","删除失败！");
        request.getRequestDispatcher("/del_item.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
