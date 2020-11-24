<%@ page import="java.util.List" %>
<%@ page import="com.xavier.domain.Order" %>
<%@ page import="com.xavier.service.impl.OrderServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>查看订单</title>
</head>
<body>
<%@ include file ="header.jsp"%>
<%  existUser=(User)request.getSession().getAttribute("existUser");
    if(existUser==null){
        response.sendRedirect(request.getContextPath()+"/login.jsp");
        return;
    }
    OrderServiceImpl os=new OrderServiceImpl();
    List<Order> orders =os.getOrders(existUser.getUserID());
    request.setAttribute("show_orders",orders);
    String temp_msg=(String)request.getAttribute("msg");
    if(temp_msg!=null){
        out.println(temp_msg);
    }
%>
<%@ include file="show_orders.jsp"%>
</body>
</html>
