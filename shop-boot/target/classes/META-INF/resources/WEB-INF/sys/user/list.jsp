<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}
body {
	font-family: "Microsoft YaHei", Arial, sans-serif;
	font-size: 14px;
}
.header {
	height: 60px;
	background: #2c3e50;
	color: #fff;
	line-height: 60px;
	padding: 0 20px;
}
.header h1 {
	float: left;
	font-size: 20px;
}
.header .user-info {
	float: right;
}
.header .user-info a {
	color: #fff;
	text-decoration: none;
	margin-left: 20px;
}
.sidebar {
	width: 200px;
	float: left;
	background: #34495e;
	min-height: calc(100vh - 60px);
}
.sidebar ul {
	list-style: none;
}
.sidebar ul li {
	border-bottom: 1px solid #2c3e50;
}
.sidebar ul li a {
	display: block;
	padding: 15px 20px;
	color: #ecf0f1;
	text-decoration: none;
	transition: all 0.3s;
}
.sidebar ul li a:hover {
	background: #2c3e50;
}
.content {
	margin-left: 200px;
	padding: 20px;
	background: #ecf0f1;
	min-height: calc(100vh - 60px);
}
.content-box {
	background: #fff;
	padding: 20px;
	border-radius: 4px;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}
.content-box h2 {
	margin-bottom: 20px;
	color: #2c3e50;
}
.btn {
	display: inline-block;
	padding: 6px 12px;
	background: #3498db;
	color: #fff;
	text-decoration: none;
	border-radius: 4px;
	border: none;
	cursor: pointer;
	margin-right: 5px;
	font-size: 12px;
}
.btn:hover {
	background: #2980b9;
}
.btn-danger {
	background: #e74c3c;
}
.btn-danger:hover {
	background: #c0392b;
}
table {
	width: 100%;
	border-collapse: collapse;
}
table th, table td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}
table th {
	background: #f8f9fa;
	font-weight: bold;
}
table tr:hover {
	background: #f8f9fa;
}
.pagination {
	margin-top: 20px;
	text-align: center;
}
.pagination a {
	display: inline-block;
	padding: 8px 12px;
	margin: 0 4px;
	background: #fff;
	border: 1px solid #ddd;
	color: #333;
	text-decoration: none;
	border-radius: 4px;
}
.pagination a:hover {
	background: #3498db;
	color: #fff;
	border-color: #3498db;
}
.pagination .current {
	background: #3498db;
	color: #fff;
	border-color: #3498db;
}
</style>
</head>
<body>
<div class="header">
	<h1>商城后台管理系统</h1>
	<div class="user-info">
		<span>欢迎，${sessionScope.adminUser}</span>
		<a href="<%=basePath %>admin/logout">退出</a>
	</div>
</div>
<div class="sidebar">
	<ul>
		<li><a href="<%=basePath %>admin/index">☰ 后台首页</a></li>
		<li><a href="<%=basePath %>admin/product/list">📦 商品管理</a></li>
		<li><a href="<%=basePath %>admin/order/list">📋 订单管理</a></li>
		<li><a href="<%=basePath %>admin/user/list">👥 用户管理</a></li>
		<li><a href="<%=basePath %>product/frontlist" target="_blank">🌐 前台首页</a></li>
	</ul>
</div>
<div class="content">
	<div class="content-box">
		<h2>用户管理</h2>
		<table>
			<thead>
				<tr>
					<th>用户ID</th>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>邮箱</th>
					<th>电话</th>
					<th>注册时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageInfo.list}" var="user">
					<tr>
						<td>${user.userId}</td>
						<td>${user.username}</td>
						<td>${user.realName}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td>
							<fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a href="<%=basePath %>admin/user/delete?id=${user.userId}" class="btn btn-danger" onclick="return confirm('确定要删除该用户吗？')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">
			<c:if test="${pageInfo.hasPreviousPage}">
				<a href="<%=basePath %>admin/user/list?pageNum=${pageInfo.prePage}">上一页</a>
			</c:if>
			<c:forEach items="${pageInfo.navigatepageNums}" var="num">
				<c:choose>
					<c:when test="${num == pageInfo.pageNum}">
						<a class="current">${num}</a>
					</c:when>
					<c:otherwise>
						<a href="<%=basePath %>admin/user/list?pageNum=${num}">${num}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pageInfo.hasNextPage}">
				<a href="<%=basePath %>admin/user/list?pageNum=${pageInfo.nextPage}">下一页</a>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>
