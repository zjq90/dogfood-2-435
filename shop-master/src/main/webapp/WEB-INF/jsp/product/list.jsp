<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品列表</title>
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
                <li><a href="${pageContext.request.contextPath}/product/list" class="active">商品管理</a></li>
                <li><a href="${pageContext.request.contextPath}/order/list">订单管理</a></li>
            </ul>
        </div>
        <div class="right">
            <div class="toolbar">
                <a href="${pageContext.request.contextPath}/product/add" class="btn">添加商品</a>
                <form action="${pageContext.request.contextPath}/product/find" method="post" style="display:inline;">
                    <input type="text" name="pname" placeholder="商品名称">
                    <button type="submit">搜索</button>
                </form>
            </div>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>商品ID</th>
                        <th>商品名称</th>
                        <th>市场价格</th>
                        <th>销售价格</th>
                        <th>库存</th>
                        <th>是否热销</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="product">
                        <tr>
                            <td>${product.pid}</td>
                            <td>${product.pname}</td>
                            <td>${product.sprice}</td>
                            <td>${product.cprice}</td>
                            <td>${product.number}</td>
                            <td><c:if test="${product.isHot == 1}">是</c:if><c:if test="${product.isHot != 1}">否</c:if></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/product/edit?pid=${product.pid}">编辑</a>
                                <a href="${pageContext.request.contextPath}/product/delete?pid=${product.pid}" onclick="return confirm('确定要删除吗？')">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
