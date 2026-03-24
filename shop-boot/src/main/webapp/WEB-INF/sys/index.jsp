<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>后台管理首页</title>
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
.sidebar ul li a i {
	margin-right: 10px;
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
.stats {
	display: flex;
	justify-content: space-between;
	margin-bottom: 30px;
}
.stat-item {
	background: #3498db;
	color: #fff;
	padding: 20px;
	border-radius: 4px;
	width: 23%;
	text-align: center;
}
.stat-item h3 {
	font-size: 32px;
	margin-bottom: 10px;
}
.stat-item p {
	font-size: 14px;
}
.stat-item:nth-child(2) {
	background: #e74c3c;
}
.stat-item:nth-child(3) {
	background: #2ecc71;
}
.stat-item:nth-child(4) {
	background: #f39c12;
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
		<h2>系统概览</h2>
		<div class="stats">
			<div class="stat-item">
				<h3>${productCount}</h3>
				<p>商品总数</p>
			</div>
			<div class="stat-item">
				<h3>${orderCount}</h3>
				<p>订单总数</p>
			</div>
			<div class="stat-item">
				<h3>${userCount}</h3>
				<p>用户总数</p>
			</div>
			<div class="stat-item">
				<h3>${todayOrderCount}</h3>
				<p>今日订单</p>
			</div>
		</div>
		<h2>欢迎使用商城后台管理系统</h2>
		<p>请从左侧菜单选择功能进行操作。</p>
	</div>
</div>
</body>
</html>
