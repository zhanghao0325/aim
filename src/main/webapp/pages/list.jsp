<%--
  Created by IntelliJ IDEA.
  User: 张浩
  Date: 2019/5/19
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>展示页</title>
</head>
<body>
<c:forEach items="${fileList}" var="file">
 <h3>文件名:</h3> ${file.title}<br>
 <h3>条例:</h3>   ${file.dsc}
</c:forEach>
</body>
</html>
