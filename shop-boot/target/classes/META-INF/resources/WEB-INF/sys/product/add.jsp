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
<title>添加商品</title>
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
.form-group {
	margin-bottom: 20px;
}
.form-group label {
	display: block;
	margin-bottom: 8px;
	font-weight: bold;
}
.form-group input[type="text"],
.form-group input[type="number"],
.form-group textarea {
	width: 100%;
	max-width: 400px;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 14px;
}
.form-group textarea {
	resize: vertical;
	height: 100px;
}
.form-group input[type="file"] {
	padding: 10px 0;
}
.btn {
	display: inline-block;
	padding: 10px 20px;
	background: #3498db;
	color: #fff;
	text-decoration: none;
	border-radius: 4px;
	border: none;
	cursor: pointer;
	margin-right: 10px;
}
.btn:hover {
	background: #2980b9;
}
.btn-cancel {
	background: #95a5a6;
}
.btn-cancel:hover {
	background: #7f8c8d;
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
		<h2>添加商品</h2>
		<form action="<%=basePath %>admin/product/save" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label>商品名称</label>
				<input type="text" name="productName" required placeholder="请输入商品名称">
			</div>
			<div class="form-group">
				<label>销售价</label>
				<input type="number" name="salePrice" step="0.01" required placeholder="请输入销售价">
			</div>
			<div class="form-group">
				<label>成本价</label>
				<input type="number" name="costPrice" step="0.01" required placeholder="请输入成本价">
			</div>
			<div class="form-group">
				<label>库存数量</label>
				<input type="number" name="stock" min="0" required placeholder="请输入库存数量">
			</div>
			<div class="form-group">
				<label>商品图片</label>
				<input type="file" name="file" accept="image/*" required>
				<small style="color: #666;">支持jpg、png、gif格式，大小不超过10MB</small>
			</div>
			<div class="form-group">
				<label>商品描述</label>
				<textarea name="description" placeholder="请输入商品描述（选填）"></textarea>
			</div>
			<div class="form-group">
				<label>
					<input type="checkbox" name="isHot" value="true"> 热门商品
				</label>
			</div>
			<div class="form-group">
				<button type="submit" class="btn">保存</button>
				<a href="<%=basePath %>admin/product/list" class="btn btn-cancel">取消</a>
			</div>
		</form>
	</div>
</div>
</body>
</html>
