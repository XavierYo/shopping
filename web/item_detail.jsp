<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.xavier.service.RecordService" %>
<%@ page import="com.xavier.service.impl.RecordServiceImpl" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.xavier.domain.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情</title>
</head>
<body>
<%@include file="header.jsp"%>
<script type="text/javascript">
    function addToCart(item_id){
        location.href = "${pageContext.request.contextPath}/AddToCartServlet?item_id="+item_id;
    }
</script>
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
    <tr>
    <%
        Item item=(Item)request.getSession().getAttribute("item_detail");
    %>
        <td name="<%=item.getItem_id()%>>"><%=item.getItem_id()%></td>
        <td><%=item.getItem_name()%></td>
        <td><%=item.getPrice()%></td>
        <td><%=item.getNum()%></td>
        <td><%=item.getCategory_id()%></td>
<%--    todo 添加类别名称显示--%>
        <td><%=item.getCategory_id()%></td>
        <td><%=item.getDescription()%></td>
    </tr>
    <%
        RecordService recordService=new RecordServiceImpl();
        int temp_user_id=-1;
        if(existUser!=null){
            temp_user_id=existUser.getUser_id();
        }
        if(item!=null){
            try {
                recordService.record(temp_user_id,item.getItem_id());
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }

    %>


</table>
<button onclick="addToCart(<%=item.getItem_id()%>)">加入购物车</button>
</body>
</html>
