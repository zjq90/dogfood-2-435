<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加商品</title>
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
            <h2>添加商品</h2>
            <form action="${pageContext.request.contextPath}/product/add" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label>商品名称：</label>
                    <input type="text" name="pname" required>
                </div>
                <div class="form-group">
                    <label>市场价格：</label>
                    <input type="number" name="sprice" step="0.01" required>
                </div>
                <div class="form-group">
                    <label>销售价格：</label>
                    <input type="number" name="cprice" step="0.01" required>
                </div>
                <div class="form-group">
                    <label>库存数量：</label>
                    <input type="number" name="number" required>
                </div>
                <div class="form-group">
                    <label>是否热销：</label>
                    <select name="isHot">
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>商品图片：</label>
                    <input type="file" name="file">
                </div>
                <div class="form-group">
                    <label>商品描述：</label>
                    <textarea name="pdesc" rows="3"></textarea>
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
