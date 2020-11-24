<%@ page import="java.util.List" %>
<%@ page import="com.xavier.domain.Cart" %>
<%@ page import="com.xavier.service.impl.CartServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<%@ include file ="header.jsp"%>
<div>
    <table border="1" class="table table-hover table-striped">
        <thead>
        <tr>
            <th>商品名</th>
            <th>价格</th>
            <th>数量</th>
        </tr>
        </thead>
        <tbody>
        <%
            existUser=(User)request.getSession().getAttribute("existUser");
            if(existUser==null){
                response.sendRedirect(request.getContextPath()+"/login.jsp");
                return;
            }

            CartServiceImpl cs=new CartServiceImpl();
            List<Cart> carts=cs.getCartsByID(existUser.getUserID());
            if(carts!=null){
                for (Cart cart:carts) {
                    out.println("<tr>");
                    out.println("<td>"+cart.getItem().getItemName()+"</td>");
                    out.println("<td>"+cart.getItem().getPrice()+"</td>");
                    out.println("<td>"+cart.getNumber()+"</td>");
                    out.println("</tr>");
                }
            }
        %>
        </tbody>
    </table>
        <form method="post" name="button_form" action="${pageContext.request.contextPath }/DelCartServlet" >
            <input type="submit" value="清空"/>
        </form>
    <form method="post" name="button_form" action="${pageContext.request.contextPath }/MakeOrderServlet" >
        <input type="submit"  value="下单"/>
    </form>
    <%
        String msg=(String)request.getAttribute("msg");
        if(msg!=null)
            out.println("msg:"+request.getAttribute("msg"));
    %>
</div>
</body>
</html>
