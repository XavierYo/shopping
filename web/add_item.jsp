<%--
  Created by IntelliJ IDEA.
  User: Xavier
  Date: 2020/11/21
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
</head>
<script>
    function checkForm(){
        const item_name = document.getElementById("item_name").value;
        if(item_name==null||item_name==""){
            alert("商品名不能为空");
            return false;
        }
        const price = document.getElementById("price").value;
        if(price==null||price==""){
            alert("价格不能为空");
            return false;
        }
        const number = document.getElementById("num").value;
        if(number==null||number==""){
            alert("库存不能为空");
            return false;
        }
        const category_name = document.getElementById("category_id").value;
        if(category_name==null||category_name==""){
            alert("类别不能为空");
            return false;
        }
        const desc = document.getElementById("description").value;
        if(desc==null||desc==""){
            alert("描述不能为空");
            return false;
        }

        return true;
    }
</script>
<body>
<%@include file="header.jsp"%>

<form  onsubmit="return checkForm()" method="post" action="${pageContext.request.contextPath }/InsertItemServlet">
    <fieldset style="display:inline-block;*display:inline;*zoom:1;">
        <legend>添加商品</legend>
        商品名称: <input type="text" name="item_name" value=""><br>
        商品价格: <input type="number" name="price" value=""><br>
        类别ID: <input type="number" name="category_id" value=""><br>
        商品描述: <input type="text" name="description" value=""><br>
        商品数目: <input type="number" name="num" value=""><br>
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
