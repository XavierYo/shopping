<%--
  Created by IntelliJ IDEA.
  User: Xavier
  Date: 2021/6/5
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售人员密码重置</title>
</head>
<body>
<%@ include file ="header.jsp"%>
<form  onsubmit="return checkForm()" method="post" action="${pageContext.request.contextPath }/ModItemServlet">
    <fieldset style="display:inline-block;*display:inline;*zoom:1;">
        <legend>重置密码</legend>
        销售人员ID: <input type="number" name="user_id" value=""><br>
        密码: <input type="password" name="password" value=""><br>
        <input type="submit" value="提交">
    </fieldset>
</form>
</body>
</html>
