package com.xavier.web.servlet;

import com.xavier.domain.Item;
import com.xavier.domain.User;
import com.xavier.service.ItemService;
import com.xavier.service.impl.CategoryServiceImpl;
import com.xavier.service.impl.ItemServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/ModItemServlet")
public class ModItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收数据:
        request.setCharacterEncoding("utf-8");
        Item item = new Item();
        try {
            BeanUtils.populate(item, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        CategoryServiceImpl categoryService=new CategoryServiceImpl();
        User seller=(User)request.getSession().getAttribute("existUser");
        // 判断是否具有该类别的权限
        try{
            if(categoryService.isInCharge(seller.getUser_id(),item.getCategory_id())==false){
                request.setAttribute("msg","你没有该类别的权限！");
                request.getRequestDispatcher("/mod_item.jsp").forward(request, response);
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        // 处理数据:完成插入
        ItemService itemService = new ItemServiceImpl();
        boolean flag= false;
        try {
            flag = itemService.modItem(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 显示结果:
        if(flag)
            request.setAttribute("msg", "修改成功！");
        else
            request.setAttribute("msg","修改失败！");
        request.getRequestDispatcher("/add_item.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
