<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<%@ include file ="header.jsp"%>
<script>

    function checkForm(){
        const username = document.getElementById("username").value;
        if(username==null||username===""){
            alert("用户名不能为空");
            return false;
        }
        const password = document.getElementById("password").value;
        if(password==null||password===""){
            alert("密码不能为空");
            return false;
        }
        const repassword = document.getElementById("repassword").value;
        if(repassword!==password){
            alert("两次密码输入不一致");
            return false;
        }
        const email = document.getElementById("email").value;
        if(email==null||email===""){
            alert("邮箱不能为空");
            return false;
        }
        return true;
    }

</script>

<form id="loginForm" onsubmit="return checkForm()" class="login-table" method="post" action="${pageContext.request.contextPath }/RegistServlet">
    <label for="username">姓名:</label>
    <input type="text"  name="username" id="username" value="" /><br>
    <label for="password">密码:</label>
    <input type="password"  name="password" id="password" value="" /><br>
    <label for="repassword">重复密码:</label>
    <input type="password"  name="repassword" id="repassword" value="" /><br>
    <label for="email">邮箱:</label>
    <input type="text"  name="email" id="email" value="" /><br>
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
