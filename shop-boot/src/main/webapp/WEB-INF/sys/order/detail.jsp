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
<title>订单详情</title>
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
	padding: 8px 16px;
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
.btn-success {
	background: #2ecc71;
}
.btn-success:hover {
	background: #27ae60;
}
.btn-cancel {
	background: #95a5a6;
}
.btn-cancel:hover {
	background: #7f8c8d;
}
.order-info {
	margin-bottom: 30px;
}
.order-info h3 {
	margin-bottom: 15px;
	color: #2c3e50;
	border-bottom: 2px solid #3498db;
	padding-bottom: 10px;
}
.info-row {
	display: flex;
	margin-bottom: 10px;
}
.info-label {
	width: 100px;
	font-weight: bold;
	color: #666;
}
.info-value {
	flex: 1;
	color: #333;
}
.status-pending {
	color: #f39c12;
}
.status-shipped {
	color: #3498db;
}
.status-completed {
	color: #2ecc71;
}
.status-cancelled {
	color: #e74c3c;
}
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
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
.product-img {
	width: 50px;
	height: 50px;
	object-fit: cover;
}
.total-price {
	text-align: right;
	margin-top: 20px;
	font-size: 18px;
	color: #e74c3c;
	font-weight: bold;
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
		<h2>订单详情</h2>
		<div class="order-info">
			<h3>订单信息</h3>
			<div class="info-row">
				<div class="info-label">订单编号：</div>
				<div class="info-value">${order.orderId}</div>
			</div>
			<div class="info-row">
				<div class="info-label">订单状态：</div>
				<div class="info-value">
					<c:choose>
						<c:when test="${order.status == 0}">
							<span class="status-pending">待发货</span>
						</c:when>
						<c:when test="${order.status == 1}">
							<span class="status-shipped">已发货</span>
						</c:when>
						<c:when test="${order.status == 2}">
							<span class="status-completed">已完成</span>
						</c:when>
						<c:otherwise>
							<span class="status-cancelled">已取消</span>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="info-row">
				<div class="info-label">下单时间：</div>
				<div class="info-value">
					<fmt:formatDate value="${order.fdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
			<div class="info-row">
				<div class="info-label">订单金额：</div>
				<div class="info-value" style="color: #e74c3c; font-weight: bold;">¥${order.total}</div>
			</div>
		</div>
		<div class="order-info">
			<h3>收货信息</h3>
			<div class="info-row">
				<div class="info-label">收货人：</div>
				<div class="info-value">${order.name}</div>
			</div>
			<div class="info-row">
				<div class="info-label">联系电话：</div>
				<div class="info-value">${order.phone}</div>
			</div>
			<div class="info-row">
				<div class="info-label">收货地址：</div>
				<div class="info-value">${order.addr}</div>
			</div>
		</div>
		<div class="order-info">
			<h3>商品明细</h3>
			<table>
				<thead>
					<tr>
						<th>商品图片</th>
						<th>商品名称</th>
						<th>单价</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderItems}" var="item">
						<tr>
							<td>
								<img src="<%=basePath %>upload/${item.product.picture}" class="product-img" alt="${item.name}">
							</td>
							<td>${item.name}</td>
							<td>¥${item.price}</td>
							<td>${item.number}</td>
							<td>¥${item.price * item.number}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="total-price">
				订单总额：¥${order.total}
			</div>
		</div>
		<div style="margin-top: 30px;">
			<c:if test="${order.status == 0}">
				<a href="<%=basePath %>admin/order/ship?id=${order.orderId}" class="btn btn-success" onclick="return confirm('确定要发货吗？')">确认发货</a>
			</c:if>
			<a href="<%=basePath %>admin/order/list" class="btn btn-cancel">返回列表</a>
		</div>
	</div>
</div>
</body>
</html>
