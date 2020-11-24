<%@ page import="com.xavier.service.impl.OrderServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>用户购买记录</title>
</head>
<body>
<%@ include file ="header.jsp"%>
<%
    OrderServiceImpl os=new OrderServiceImpl();
    List<Order> orders =os.getAllOrders();
    request.setAttribute("show_orders",orders);
%>
<%@include file="show_orders.jsp"%>
</body>
</html>
