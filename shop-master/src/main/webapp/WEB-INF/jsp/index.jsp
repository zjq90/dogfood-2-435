<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/left.css">
</head>
<body>
    <div class="header">
        <h1>后台管理系统</h1>
        <div class="user-info">
            欢迎您，${sessionScope.adminusername} | <a href="${pageContext.request.contextPath}/admin/logout">退出</a>
        </div>
    </div>
    <div class="container">
        <div class="left">
            <ul>
                <li><a href="${pageContext.request.contextPath}/product/list">商品管理</a></li>
                <li><a href="${pageContext.request.contextPath}/order/list">订单管理</a></li>
            </ul>
        </div>
        <div class="right">
            <h2>欢迎使用后台管理系统</h2>
            <p>请从左侧菜单选择功能进行操作。</p>
        </div>
    </div>
</body>
</html>
