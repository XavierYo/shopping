<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<script type="text/javascript">
    // 清空购物车
    function delCart() {
        if (window.confirm("是否确认清空购物车?")) {
            location.href = "${pageContext.request.contextPath}/DelCartServlet";
        }
    }

</script>
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
            <c:forEach items="${cart}" var="c">
                <tr>
                    <td>${c.key.item_name}</td>
                    <td>${c.key.price}</td>
                    <td>${c.value}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <form method="post" name="button_form" action="${pageContext.request.contextPath }/MakeOrderServlet" >
        邮箱：<input type="text" value="" name="email_address"/>
        <input type="submit"  value="下单"/>
    </form>
    <button onclick="delCart()">清空购物车</button>
</div>
</body>
</html>
