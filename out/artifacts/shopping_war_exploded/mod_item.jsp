<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>修改商品</title>
</head>
<body>
<%@include file="header.jsp"%>
<form  onsubmit="return checkForm()" method="post" action="${pageContext.request.contextPath }/ModItemServlet">
    <fieldset style="display:inline-block;*display:inline;*zoom:1;">
        <legend>修改商品</legend>
    商品名称: <input type="text" name="item_name" value=""><br>
    商品价格: <input type="number" name="price" value="Mouse"><br>
    商品数目: <input type="number" name="number" value=""><br>
    商品类别: <input type="text" name="category_name" value=""><br>
    商品描述: <input type="text" name="description" value=""><br>
    <input type="submit" value="提交">
    </fieldset>
</form>
<%
    String msg=(String)request.getAttribute("msg");
    if(msg!=null)
        out.println("msg:"+request.getAttribute("msg"));
%>
</body>
</html>
