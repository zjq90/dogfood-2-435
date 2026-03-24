<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
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
                <li><a href="${pageContext.request.contextPath}/order/list" class="active">订单管理</a></li>
            </ul>
        </div>
        <div class="right">
            <h2>订单详情</h2>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>商品名称</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>小计</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listSorder}" var="sorder">
                        <tr>
                            <td>${sorder.name}</td>
                            <td>${sorder.price}</td>
                            <td>${sorder.number}</td>
                            <td>${sorder.price * sorder.number}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div style="margin-top: 20px;">
                <a href="${pageContext.request.contextPath}/order/list">返回订单列表</a>
            </div>
        </div>
    </div>
</body>
</html>
