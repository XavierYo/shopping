<%@ page import="com.xavier.domain.Order" %>
<%@ page import="com.xavier.domain.Item" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>订单详情</title>
</head>
<body>
<%@include file="header.jsp"%>
<form>
    <fieldset style="display:inline-block;*display:inline;*zoom:1;">
        <legend>订单详情</legend>
        <%
            Map<Item,Integer> order_detail=(Map<Item,Integer>)request.getSession().getAttribute("order_detail");
            Order order=(Order)request.getSession().getAttribute("order");
//            List<Item> items=order.getItems();
            out.println("订单号："+order.getOrder_id()+"<br />");
            out.println("创建时间："+order.getCreate_time()+"<br />");
            out.println("价格总和:"+order.getTotal_amount()+"<br />");
            out.println("用户ID："+existUser.getUser_id()+"<br />");
            out.println("用户名称："+existUser.getUsername()+"<br />");
        %>
    </fieldset>
</form>
<table border="1">
    <thead>
    <tr>
        <th>商品</th>
        <th>单价</th>
        <th>数量</th>
    </tr>
    </thead>
    <tbody>
    <%
        // map遍历
        Set<Map.Entry<Item,Integer>> entries = order_detail.entrySet();
        for (Map.Entry<Item,Integer> entry : entries){
            out.println("<tr>");
            out.println("<td>"+entry.getKey().getItem_name()+"</td>");
            out.println("<td>"+entry.getKey().getPrice()+"</td>");
            out.println("<td>"+entry.getValue()+"</td>");
            out.println("</tr>");
        }
    %>
    </tbody>
</table>
</body>
</html>
