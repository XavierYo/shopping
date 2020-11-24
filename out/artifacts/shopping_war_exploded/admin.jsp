<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>管理商品</title>
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
<%--        <td><A href="statistics.jsp">统计数据</A></td>--%>
    </tr>
    <tr>
        <td><A href="show_all_order.jsp">销售记录</A> </td>
        <td><A href="browser_record.jsp">浏览记录</A> </td>
    </tr>
</table>
</body>
</html>
