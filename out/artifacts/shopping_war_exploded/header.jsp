<%@ page import="com.xavier.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    User existUser=(User)request.getSession().getAttribute("existUser");
    String username;
    if(existUser==null){
        username="未登录";
    }else if(existUser.getUsername().equals("admin")){
        username="<A href=\"admin.jsp\">管理商品</A>";
    }else {
        username=existUser.getUsername();
    }

%>
<div>
    <H3>XAVIER的在线商城</H3>
    <table border="1"  cellSpacing="1" cellPadding="1" width="660" border="0">
        <tr valign="bottom">
            <td><A href="index.jsp">主页</A></td>
            <td><A href="register.jsp">注册</A></td>
            <td><A href="login.jsp">登录</A></td>
            <td><A href="browser.jsp">浏览商品</A></td>
            <td><A href="search.jsp">搜索</A></td>
            <td><A href="cart.jsp">购物车</A></td>
            <td><A href="order.jsp">查看订单</A></td>
            <td><A href="LogoutServlet">登出</A></td>
            <td><%out.print("("+username+")");%></td>
        </tr>
    </table>
</div>



