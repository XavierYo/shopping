<%@ page import="com.xavier.domain.Category" %>
<%@ page import="com.xavier.dao.CategoryDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>浏览</title>
</head>
<body>
<%@ include file ="header.jsp"%>
<%
    CategoryDao categoryDao = new CategoryDao();
    List<Category> categories = categoryDao.getAllCategory();
    out.print("<form action='BrowserServlet' method ='post'>");
    out.print("<select name='selected_id'>");
    for(int i=0;i<categories.size();i++){
        out.print("<option value ="+ categories.get(i).getCategory_id()+">"
                +categories.get(i).getCategory_name()+"</option>");
    }
    out.print("</select>");
    out.print("<input type ='submit' value ='提交'>");
    out.print("</form>");
%>
<%@ include file="showitems.jsp"%>
</body>
</html>
