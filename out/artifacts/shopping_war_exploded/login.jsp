<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%@ include file ="header.jsp"%>
<form id="loginForm"  class="login-table" method="post" action="${pageContext.request.contextPath }/LoginServlet">
    <label for="username">姓名:</label>
    <input type="text"  name="username" id="username" value="" /><br>
    <label for="password">密码:</label>
    <input type="password"  name="password" id="password" value="" /><br>
    <input type="submit" value="确定"  name="submit" />
    <input type="reset" value="重置" name="reset" />
</form>
<%
    String temp_msg=(String)request.getAttribute("msg");
    if(temp_msg!=null&&!temp_msg.equals("")){
        out.println(temp_msg);
    }
%>
</body>
</html>
