<%--
  Created by IntelliJ IDEA.
  User: Xavier
  Date: 2021/6/5
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>销售人员界面</title>
</head>
<body>
<%@ include file ="header.jsp"%>
<table border="1">
    <tr >
        <td><A href="add_item.jsp">添加商品</A></td>
        <td><A href="del_item.jsp">删除商品</A></td>
    </tr>
    <tr>
        <td><A href="mod_item.jsp">修改商品</A></td>
    </tr>
    <tr>
        <td><A href="show_all_order.jsp">销售记录</A> </td>
        <td><A href="browser_record.jsp">浏览记录</A> </td>
    </tr>
</table>
</body>
</html>
