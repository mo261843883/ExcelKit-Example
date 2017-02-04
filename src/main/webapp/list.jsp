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
		<h1 class="page-header">ExcelKit-Example v1.0 
			<small><a href="http://git.oschina.net/wuwenze/ExcelKit" target="_blank">Download ExcelKit SourceCode</a></small>
		</h1>
		
		<form class="form-inline" action="example?t=import" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<a href="example?t=export" target="_self" class="btn btn-primary btn-sm">1. 导出Excel</a>
			</div>
			<div class="form-group">
				<a href="example?t=downtmpl" target="_self" class="btn btn-success btn-sm">2. 下载Excel导入模板</a>
			</div>
			
			<div class="form-group">
				<input type="file" id="uploadFile" name="uploadFile">
			</div>
			<button type="submit" class="btn btn-danger btn-sm">3. 导入Excel数据</button>
		</form>
		<hr />
		
		<table class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>UID</th>
					<th>用户名</th>
					<th>密码</th>
					<th>昵称</th>
					<th>年龄</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${users}">
					<tr>
						<td>${item.uid}</td>
						<td>${item.username}</td>
						<td>${item.password}</td>
						<td>${item.nickname}</td>
						<td>${item.age}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>