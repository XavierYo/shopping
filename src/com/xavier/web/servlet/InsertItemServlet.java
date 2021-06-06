package com.xavier.web.servlet;

import com.xavier.domain.Item;
import com.xavier.domain.Log;
import com.xavier.domain.User;
import com.xavier.service.ItemService;
import com.xavier.service.LogService;
import com.xavier.service.impl.CategoryServiceImpl;
import com.xavier.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.xavier.service.impl.LogServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/InsertItemServlet")
public class InsertItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 权限判断
        User seller = (User)request.getSession().getAttribute("existUser");
        if(seller.getRole()==0)
            return;
        // 接收数据:
        Item item = new Item();
        try {
            BeanUtils.populate(item, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        CategoryServiceImpl categoryService=new CategoryServiceImpl();
        // 判断是否具有该类别的权限
        try{
            if(categoryService.isInCharge(seller.getUser_id(),item.getCategory_id())==false){
                request.setAttribute("msg","你没有该类别的权限！");
                request.getRequestDispatcher("/add_item.jsp").forward(request, response);
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        // 处理数据:完成插入
        ItemService itemService = new ItemServiceImpl();
        boolean flag= false;
        try {
            flag = itemService.ins(item);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        // 显示结果:
        if(flag)
            request.setAttribute("msg", "添加成功！");
        else
            request.setAttribute("msg","添加失败！");
        request.getRequestDispatcher("/add_item.jsp").forward(request, response);

        Log log = new Log();
        log.setOperate("新增商品："+item.getItem_name());
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
