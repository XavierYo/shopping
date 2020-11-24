<%@ page import="com.xavier.service.RecordService" %>
<%@ page import="com.xavier.service.impl.RecordServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xavier.domain.Record" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Xavier
  Date: 2020/11/22
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浏览记录</title>
</head>
<body>
<%@include file="header.jsp"%>
<div>
    <table border="1" class="table table-hover table-striped">
        <thead>
        <tr>
            <th>记录号</th>
            <th>用户ID</th>
            <th>商品ID</th>
            <th>浏览时间</th>
        </tr>
        </thead>
        <tbody>
        <%
            RecordService recordService=new RecordServiceImpl();
            List<Record> records=recordService.getRecords();
            if(records !=null){
                for (Record record: records) {
                    out.println("<tr>");
                    out.println("<td>"+record.getRecord_id()+"</td>");
                    out.println("<td>"+record.getUser_id()+"</td>");
                    out.println("<td>"+record.getItem_id()+"</td>");
                    out.println("<td>"+record.getBrowser_time()+"</td>");
                    out.println("</tr>");

                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
