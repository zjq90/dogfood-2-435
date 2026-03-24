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
<title>后台登录</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
body {
	font-family: "Microsoft YaHei", Arial, sans-serif;
	font-size: 14px;
	background: #f5f5f5;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}
.login-container {
	background: #fff;
	padding: 40px;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0,0,0,0.1);
	width: 400px;
}
.login-header {
	text-align: center;
	margin-bottom: 30px;
}
.login-header h1 {
	color: #2c3e50;
	font-size: 24px;
	margin-bottom: 10px;
}
.login-header p {
	color: #7f8c8d;
}
.form-group {
	margin-bottom: 20px;
}
.form-group label {
	display: block;
	margin-bottom: 8px;
	color: #333;
	font-weight: bold;
}
.form-group input {
	width: 100%;
	padding: 12px;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 14px;
	transition: border-color 0.3s;
}
.form-group input:focus {
	outline: none;
	border-color: #3498db;
}
.btn-login {
	width: 100%;
	padding: 12px;
	background: #3498db;
	color: #fff;
	border: none;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
	transition: background 0.3s;
}
.btn-login:hover {
	background: #2980b9;
}
.error-msg {
	color: #e74c3c;
	text-align: center;
	margin-bottom: 15px;
	padding: 10px;
	background: #fdf2f2;
	border-radius: 4px;
}
.back-link {
	text-align: center;
	margin-top: 20px;
}
.back-link a {
	color: #3498db;
	text-decoration: none;
}
.back-link a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
<div class="login-container">
	<div class="login-header">
		<h1>后台管理系统</h1>
		<p>请登录以继续</p>
	</div>
	<c:if test="${not empty errorMsg}">
		<div class="error-msg">${errorMsg}</div>
	</c:if>
	<form action="<%=basePath %>admin/login" method="post">
		<div class="form-group">
			<label>用户名</label>
			<input type="text" name="username" required placeholder="请输入管理员用户名">
		</div>
		<div class="form-group">
			<label>密码</label>
			<input type="password" name="password" required placeholder="请输入密码">
		</div>
		<button type="submit" class="btn-login">登录</button>
	</form>
	<div class="back-link">
		<a href="<%=basePath %>product/frontlist">← 返回前台首页</a>
	</div>
</div>
</body>
</html>
