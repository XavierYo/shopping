<%@ page import="com.xavier.service.ItemService" %>
<%@ page import="com.xavier.service.impl.ItemServiceImpl" %>
<%@ page import="com.xavier.domain.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>xavier小店</title>
</head>
<body>
<%@ include file ="header.jsp"%>
【商品推荐】<br/>

<%
    ItemService itemService = new ItemServiceImpl();
    List<Item> recommend = itemService.getRecommend();
    request.getSession().setAttribute("items_searchRes",recommend);
%>
<%@include file="showitems.jsp"%>
</body>
</html>
