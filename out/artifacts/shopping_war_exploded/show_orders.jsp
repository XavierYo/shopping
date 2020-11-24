<%@ page import="com.xavier.domain.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div>
    <table border="1" class="table table-hover table-striped">
        <thead>
        <tr>
            <th>订单号</th>
            <th>总金额</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Order> show_orders = (List<Order>) request.getAttribute("show_orders");
            if(show_orders !=null){
                for (Order order: show_orders) {
                    out.println("<tr>");
                    out.println("<td>"+order.getOrder_id()+"</td>");
                    out.println("<td>"+order.getTotal_amount()+"</td>");
                    out.println("<td>"+order.getCreate_time()+"</td>");
                    out.println("<td>"+"<form  action='OrderDetailServlet' method = 'post'>"+
                            "<input type ='hidden' name='selected_id' value= "+order.getOrder_id()+">"+
                            "<input type ='submit'  value='查看详情' ></form>"+"</td>");
                    out.println("</tr>");

                }
            }
        %>
        </tbody>
    </table>
</div>
