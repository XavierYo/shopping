<%@ page import="java.sql.*" %>
<%@ page import="com.xavier.utils.DataBaseConnection" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>浏览</title>
</head>
<body>
<%@ include file ="header.jsp"%>
<%
    DataBaseConnection dbc=new DataBaseConnection();
    String sql="SELECT * FROM category";
    ResultSet rs=dbc.query(sql);
    out.print("<form action='BrowserServlet' method ='post'>");
    out.print("<select name='selected_id'>");
    try{
        while (rs.next()) {
        int category_id = rs.getInt("category_id");
        String category_name = rs.getString("category_name");
        out.print("<option value =" + category_id + ">" + category_name + "</option>");
        }
    }catch (SQLException e){
        e.printStackTrace();
    }

    out.print("</select>");
    out.print("<input type ='submit' value ='提交'>");
    out.print("</form>");
%>
<%@ include file="showitems.jsp"%>
</body>
</html>
