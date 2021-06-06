<%@ page import="com.xavier.service.impl.ItemServiceImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除商品</title>
</head>
<body>
<%@include file="header.jsp"%>
<form method="post" action="${pageContext.request.contextPath }/DelItemServlet">
    输入待删除的商品id: <input type="number" name="item_id" value=""><br>
    <input type="submit" value="提交">
    <%
        ItemServiceImpl itemService=new ItemServiceImpl();
        request.getSession().setAttribute("items_searchRes",itemService.getAllItems());
    %>
</form>
<%@include file="showitems.jsp"%>
<%
    String msg=(String)request.getAttribute("msg");
    if(msg!=null)
        out.println("msg:"+request.getAttribute("msg"));
%>
</body>
</html>
