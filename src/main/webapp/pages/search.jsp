<%--
  Created by IntelliJ IDEA.
  User: 张浩
  Date: 2019/5/19
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/search/find" method="get" >
    <input  id="desc" name="desc" type="text">
    <input type="submit" value="搜索">${result}
    <%--<form action="${pageContext.request.contextPath}/search/find" th:object="${goodsFrom}" method="post">--%>
        <%--<li>--%>
            <%--<span><input name="commodityName" type="text" /></span>--%>
            <%--<span><button type="submit" name="sousuo">搜索</button></span>--%>
        <%--</li>--%>
    <%--</form>--%>
</body>
</html>
