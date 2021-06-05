<%@ page import="com.xavier.service.impl.ItemServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>修改商品</title>
</head>
<body>
<%@include file="header.jsp"%>
<%
    ItemServiceImpl itemService=new ItemServiceImpl();
    request.getSession().setAttribute("items_searchRes",itemService.getAllItems());
%>
<form  onsubmit="return checkForm()" method="post" action="${pageContext.request.contextPath }/ModItemServlet">
    <fieldset style="display:inline-block;*display:inline;*zoom:1;">
        <legend>修改商品-（商品id是唯一标识）</legend>
        商品ID: <input type="number" name="item_id" value=""><br>
        商品名称: <input type="text" name="item_name" value=""><br>
        商品价格: <input type="number" name="price" value=""><br>
        商品数目: <input type="number" name="num" value=""><br>
        类别ID: <input type="text" name="category_id" value=""><br>
        商品描述: <input type="text" name="description" value=""><br>
    <input type="submit" value="提交">
    </fieldset>
</form>
<%@include file="showitems.jsp"%>
<%
    String msg=(String)request.getAttribute("msg");
    if(msg!=null)
        out.println("msg:"+request.getAttribute("msg"));
%>
</body>
</html>
