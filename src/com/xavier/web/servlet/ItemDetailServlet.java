package com.xavier.web.servlet;

import com.xavier.domain.Item;
import com.xavier.domain.Log;
import com.xavier.domain.User;
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

@WebServlet("/ItemDetailServlet")
public class ItemDetailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String temp_id = req.getParameter("item_id");
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
        req.getSession().setAttribute("item_detail",item);
        req.getRequestDispatcher("/item_detail.jsp").forward(req, resp);
        Log log = new Log();
        log.setOperate("查看商品详情:"+item.getItem_name());
        if(req.getRemoteAddr()!=null){
            log.setIp(req.getRemoteAddr());
        }
        User existUser = (User)req.getSession().getAttribute("existUser");
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


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
