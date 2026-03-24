<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单列表</title>
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
            <h2>订单列表</h2>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>订单ID</th>
                        <th>收货人</th>
                        <th>电话</th>
                        <th>地址</th>
                        <th>邮编</th>
                        <th>总金额</th>
                        <th>下单时间</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="forder">
                        <tr>
                            <td>${forder.fid}</td>
                            <td>${forder.name}</td>
                            <td>${forder.phone}</td>
                            <td>${forder.address}</td>
                            <td>${forder.post}</td>
                            <td>${forder.total}</td>
                            <td><fmt:formatDate value="${forder.date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <c:if test="${forder.status == 0}">未发货</c:if>
                                <c:if test="${forder.status == 1}">已发货</c:if>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/sorder/listbyfid?fid=${forder.fid}">查看详情</a>
                                <c:if test="${forder.status == 0}">
                                    <a href="${pageContext.request.contextPath}/order/updatestatus?fid=${forder.fid}&status=1">发货</a>
                                </c:if>
                                <a href="${pageContext.request.contextPath}/order/delete?fid=${forder.fid}" onclick="return confirm('确定要删除吗？')">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
