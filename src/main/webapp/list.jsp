<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>列表页</title>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1 class="page-header">ExcelKit-Example v2.0 
			<small><a href="http://git.oschina.net/wuwenze/ExcelKit" target="_blank">Download ExcelKit SourceCode</a></small>
		</h1>
		
		<form class="form-inline" action="example?t=import" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<a href="example?t=export" target="_self" class="btn btn-primary btn-sm">1. 导出Excel</a>
			</div>
			<div class="form-group">
				<input type="file" id="uploadFile" name="uploadFile" />
			</div>
			<button type="submit" class="btn btn-danger btn-sm">2. 导入Excel数据</button>
		</form>
		<hr />
		
		<table class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>UID</th>
					<th>用户名</th>
					<th>密码</th>
					<th>性别</th>
					<th>年级</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${users}">
					<tr>
						<td>${item.uid}</td>
						<td>${item.username}</td>
						<td>${item.password}</td>
						<td>
							<c:if var="sex" test="${item.sex eq 1}">男</c:if>
							<c:if test="${not sex}">女</c:if>
						</td>
						<td>
							<c:choose>
								<c:when test="${item.gradeId eq 1}">一年级学生</c:when>
								<c:when test="${item.gradeId eq 2}">二年级学生</c:when>
								<c:when test="${item.gradeId eq 3}">三年级学生</c:when>
								<c:otherwise>无记录</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>