<%@ page import="com.xavier.domain.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
        <%
            List<Item> items;
            items=(List<Item>)request.getSession().getAttribute("items_searchRes");
            if(items!=null)
            for (Item item:items) {
                out.println("<tr>");
                out.println("<td>"+item.getItemID()+"</td>");
                out.println("<td>"+item.getCategoryID()+"</td>");
                out.println("<td>"+item.getItemName()+"</td>");
                out.println("<td>"+item.getPrice()+"</td>");
                out.println("<td>"+item.getItemNumber()+"</td>");
                out.println("<td>"+item.getItemDesc()+"</td>");
                out.println("<td>"+"<form  action='ItemDetailServlet' method = 'post'>"+
                        "<input type ='hidden' name='item_id' value= "+item.getItemID()+">"+
                        "<input type ='submit'  value='查看详情' ></form>"+
                        "<form  action='AddToCartServlet' method = 'post'>"+
                        "<input type ='hidden' name='item_id' value= "+item.getItemID()+">"+
                        "<input type ='submit'  value='加入购物车' ></form>"+"</td>");
                out.println("</tr>");
            }
        %>
        </tbody>
    </table>
</div>