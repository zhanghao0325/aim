<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>数据 - AdminLTE2定制版</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">

<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">


</head>

<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper">
				<div class="box-body">

					<!-- 数据表格 -->
					<div class="table-box">

							<c:forEach items="${fileList.list}" var="file">
								<tr>
									<td>${file.title}</td>
									<td>${file.dsc}</td>
								</tr>
							</c:forEach>
					</div>

				</div>
				<div class="box-footer">
					<div class="pull-left">
						<div class="form-group form-inline">
							总共${fileList.pages} 页，共${fileList.total} 条数据。 每页
							<select class="form-control" id="pageSize" onchange="topage(1)">
								<option value="2">2</option>
								<option value="5" selected="selected">5</option>
								<option value="10">10</option>
								<option value="15">15</option>
							</select> 条
						</div>
					</div>

					<div class="box-tools pull-right" >
						<ul class="pagination">
							<li><a href="javascript:topage(1)" aria-label="Previous">首页</a></li>
							<li><a href="javascript:topage(${fileList.prePage})">上一页</a></li>
							<c:forEach begin="${fileList.navigateFirstPage}" end="${fileList.navigateLastPage}" var="i" >
                                <c:if test="${i==fileList.pageNum}">
								<li><a href="javascript:void(0)"><b>${i}</b></a></li>

                               </c:if>
                                <c:if test="${i!=fileList.pageNum}">
                                    <li><a href="javascript:topage(${i})">${i}</a></li>
                                </c:if>

							</c:forEach>
							<li><a href="javascript:topage(${fileList.nextPage})">下一页</a></li>
							<li><a href="javascript:topage(${fileList.pages})" aria-label="Next">尾页</a></li>
						</ul>
					</div>

				</div>
				<!-- /.box-footer-->

			</div>
    <script
		src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script type="text/javascript">
        $("#pageSize option[value=${fileList.pageSize}]").prop("selected","selected");
		function topage(pageNum) {
		    if (pageNum<1||pageNum>${fileList.pages}){
		        return;
			}
			<%--if (pageNum==${list.pageNum}){--%>
		        <%--return;--%>
			<%--}--%>
			var  pageSize = $("#pageSize").val();
			location.href="/search/find1?pageNum="+pageNum+"&pageSize="+pageSize;
        }
	</script>
</body>

</html>