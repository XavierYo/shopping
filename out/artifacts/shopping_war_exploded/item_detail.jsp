<%@ page import="com.xavier.domain.Item" %>
<%@ page import="com.xavier.service.CategoryService" %>
<%@ page import="com.xavier.service.impl.CategoryServiceImpl" %>
<%@ page import="com.xavier.service.RecordService" %>
<%@ page import="com.xavier.service.impl.RecordServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情</title>
</head>
<body>
<%@include file="header.jsp"%>
<table border="1">
    <tr>
        <th>商品ID</th>
        <th>商品名</th>
        <th>价格</th>
        <th>库存</th>
        <th>类别ID</th>
        <th>类别名称</th>
        <th>描述详情</th>
    </tr>
    <tr>
<%
    Item item=(Item)request.getAttribute("item_detail");
    out.print("<td name='item_id'>"+item.getItemID()+"</td>");
    out.print("<td>"+item.getItemName()+"</td>");
    out.print("<td>"+item.getPrice()+"</td>");
    out.print("<td>"+item.getItemNumber()+"</td>");
    out.print("<td>"+item.getCategoryID()+"</td>");
    CategoryService categoryService=new CategoryServiceImpl();
    out.print("<td>"+categoryService.getCatName(item.getCategoryID())+"</td>");
    out.print("<td>"+item.getItemDesc()+"</td>");
    RecordService recordService=new RecordServiceImpl();
    int temp_user_id=-1;
    if(existUser!=null){
        temp_user_id=existUser.getUserID();
    }
    recordService.record(temp_user_id,item.getItemID());
%>
    </tr>
<%--    <input type ='hidden' name='item_id' value="<%out.print(item.getItemID());%>">--%>
<%--    <input type="submit" value="加入购物车">--%>

</table>
<%
    out.print("<form  action='AddToCartServlet' method = 'post'>"+
            "<input type ='hidden' name='item_id' value= "+item.getItemID()+">"+
            "<input type ='submit'  value='加入购物车' ></form>");
%>
</body>
</html>
