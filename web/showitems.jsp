<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
    function checkItemDetail(item_id){
        location.href = "${pageContext.request.contextPath}/ItemDetailServlet?item_id="+item_id;
    }
    function addToCart(item_id){
        location.href = "${pageContext.request.contextPath}/AddToCartServlet?item_id="+item_id;
    }
</script>
<div>
    <table border="1" class="table table-hover table-striped">
        <thead>
        <tr>
            <th>id</th>
            <th>商品类别</th>
            <th>商品名</th>
            <th>价格</th>
            <th>库存</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${items_searchRes}" var="c">
            <tr>
                <td>${c.item_id}</td>
                <td>${c.category_id}</td>
                <td>${c.item_name}</td>
                <td>${c.price}</td>
                <td>${c.num}</td>
                <td>${c.descption}</td>
                <td>
                    <a href="javascript:void(0)" onclick="checkItemDetail('${c.item_id}')">查看详情</a>
                    <a href="javascript:void(0)" onclick="addToCart('${c.item_id}')">加入购物车</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>