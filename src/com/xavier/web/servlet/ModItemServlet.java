package com.xavier.web.servlet;

import com.xavier.domain.Item;
import com.xavier.service.ItemService;
import com.xavier.service.impl.CategoryServiceImpl;
import com.xavier.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ModItemServlet")
public class ModItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收数据:
        request.setCharacterEncoding("utf-8");
        String item_name = request.getParameter("item_name");//商品名字

        String temp_price = request.getParameter("price");//商品价格，需转换为double
        double price=0;
        if(temp_price == null || temp_price.equals("")) {
            price = 0;
        } else {
            price = Double.parseDouble(temp_price);
        }

        String  temp_number = request.getParameter("number");
        int number=0;
        if(temp_number==null||temp_number.equals("")){
            number=0;
        }else{
            number = Integer.parseInt(temp_number);
        }
        //商品类别id
        String temp_CatName = request.getParameter("category_name");
        CategoryServiceImpl categoryService=new CategoryServiceImpl();
        int category_id=categoryService.getCatID(temp_CatName);
        //-1即表示类别不存在，需创建
        if(category_id==-1){
            categoryService.insCategory(temp_CatName);
            category_id=categoryService.getCatID(temp_CatName);
        }
        String desc = request.getParameter("description");//商品描述
        // 封装数据:
        Item new_item = new Item();
        new_item.setItemName(item_name);
        new_item.setPrice(price);
        new_item.setCategoryID(category_id);
        new_item.setItemDesc(desc);
        new_item.setItemNumber(number);

        // 处理数据:完成插入
        ItemService itemService = new ItemServiceImpl();
        int item_id=itemService.getItemIDByName(item_name);
        boolean flag=itemService.modItem(item_id,new_item);
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
