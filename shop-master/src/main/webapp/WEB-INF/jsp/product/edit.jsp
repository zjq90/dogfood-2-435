<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑商品</title>
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
            <h2>编辑商品</h2>
            <form action="${pageContext.request.contextPath}/product/edit" method="post" enctype="multipart/form-data">
                <input type="hidden" name="pid" value="${product.pid}">
                <div class="form-group">
                    <label>商品名称：</label>
                    <input type="text" name="pname" value="${product.pname}" required>
                </div>
                <div class="form-group">
                    <label>市场价格：</label>
                    <input type="number" name="sprice" step="0.01" value="${product.sprice}" required>
                </div>
                <div class="form-group">
                    <label>销售价格：</label>
                    <input type="number" name="cprice" step="0.01" value="${product.cprice}" required>
                </div>
                <div class="form-group">
                    <label>库存数量：</label>
                    <input type="number" name="number" value="${product.number}" required>
                </div>
                <div class="form-group">
                    <label>是否热销：</label>
                    <select name="isHot">
                        <option value="0" <c:if test="${product.isHot != 1}">selected</c:if>>否</option>
                        <option value="1" <c:if test="${product.isHot == 1}">selected</c:if>>是</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>当前图片：</label>
                    <c:if test="${not empty product.pic}">
                        <img src="${pageContext.request.contextPath}/upload/${product.pic}" width="100">
                    </c:if>
                </div>
                <div class="form-group">
                    <label>更换图片：</label>
                    <input type="file" name="file">
                </div>
                <div class="form-group">
                    <label>商品描述：</label>
                    <textarea name="pdesc" rows="3">${product.pdesc}</textarea>
                </div>
                <div class="form-group">
                    <button type="submit">提交</button>
                    <a href="${pageContext.request.contextPath}/product/list">返回</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
