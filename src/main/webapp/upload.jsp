<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/10 0010
  Time: 下午 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<script type="text/javascript" src="./upload.js"></script>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<body>
<tr>
    <td>选择导入数据源：</td>
    <td><input id="path" type="text" name="path" size="30"></td>
    <td><input type=button value="选择" onclick="browseFolder('path')"></td>
</tr>
<h2>使用JSP+Servlet实现文件的上传下载</h2>
<form action="uploadServlet" method="post" enctype="multipart/form-data">
    请选择文件：<input id="file" name="file" type="file"/>
    <input type="submit" value="上传"/>${result}
</body>
</html>
