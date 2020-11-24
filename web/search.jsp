<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>搜索</title>
</head>
<body>
<%@ include file ="header.jsp"%>
<form id="searchForm"  method="post" action="${pageContext.request.contextPath }/SearchServlet">
    <label for="keyWord">关键词:</label>
    <input type="text"  name="keyWord" id="keyWord" value="" />
    <input type="submit" value="确定"  name="submit" />
    <input type="reset" value="重置" name="reset" />
</form>
<%@include file="showitems.jsp"%>
</body>
</html>
