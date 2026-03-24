<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
    <div class="login">
        <div class="header">
            <h1>后台管理系统</h1>
        </div>
        <div class="content">
            <form action="${pageContext.request.contextPath}/admin/login" method="post">
                <div class="form-group">
                    <label>用户名：</label>
                    <input type="text" name="username" class="form-control" placeholder="请输入用户名" required>
                </div>
                <div class="form-group">
                    <label>密码：</label>
                    <input type="password" name="password" class="form-control" placeholder="请输入密码" required>
                </div>
                <div class="form-group">
                    <c:if test="${not empty msg}">
                        <span style="color: red;">${msg}</span>
                    </c:if>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">登录</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
