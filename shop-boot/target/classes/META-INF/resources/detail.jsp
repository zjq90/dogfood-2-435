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
<title>${product.productName} - 商品详情</title>

<link href="<%=basePath %>static/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>static/css/product.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="<%=basePath %>product/frontlist">
				<img src="<%=basePath %>static/images/header.jpg" alt="商城">
			</a>
		</div>
	</div>
	<div class="span9">
		<div class="headerAd">
			<img src="<%=basePath %>static/images/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
		</div>
	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<c:if test="${sessionScope.frontUser==null}">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="<%=basePath %>user/login">登录</a>|
					</li>
				</c:if>
				<c:if test="${sessionScope.frontUser!=null}">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="javascript:void(0)">${sessionScope.frontUser}</a>|
					</li>
				</c:if>
				<li id="headerUsername" class="headerUsername"></li>
				<c:if test="${sessionScope.frontUser!=null}">
					<li id="headerLogout" class="headerLogout" style="display: list-item;">
						<a href="<%=basePath %>user/logout">[退出]</a>|
					</li>
				</c:if>
				<li>
					<a >会员中心</a>
					|
				</li>
				<li>
					<a>购物指南</a>
					|
				</li>
				<li>
					<a>关于我们</a>
				</li>
			</ul>
		</div>
		<div class="cart">
			<a href="<%=basePath %>car.jsp">购物车</a>
		</div>
		<div class="phone">
			客服热线:
			<strong>96008/53277764</strong>
		</div>
	</div>
	<div class="span24">
	<ul class="mainNav">
		<li>
			<a href="<%=basePath %>product/frontlist">首页</a>
			|
		</li>
		<li>
			<a >男士休闲</a>
			|
		</li>
		<li>
			<a >女士休闲</a>
			|
		</li>
		<li>
			<a>商城积分</a>
			|
		</li>
		<li>
			<a>儿童休闲</a>
			|
		</li>
		<li>
			<a>老人休闲</a>
			|
		</li>
		<li>
			<a>联系我们</a>
			|
		</li>
	</ul>
	</div>
</div>

<div class="container productContent">
	<div class="span6">
		<div class="hotProductCategory">
			<!-- 分类列表 -->
		</div>
	</div>
	<div class="span18 last">
		<div class="productImage">
			<img src="<%=basePath %>upload/${product.picture}" style="width: 300px; height: 300px; object-fit: cover;">
		</div>
		<div class="name">${product.productName}</div>
		<div class="sn">编号: ${product.productId}</div>
		<div class="info">
			<dl>
				<dt>商城价:</dt>
				<dd>
					<strong>¥${product.costPrice}</strong>
				</dd>
			</dl>
			<dl>
				<dt>商品备注:</dt>
				<dd>${product.description}</dd>
			</dl>
		</div>
		<div class="action">
			<form action="<%=basePath %>cart/add" method="post">
				<input type="hidden" name="productId" value="${product.productId}">
				<div class="buy">
					<input id="quantity" name="quantity" value="1" maxlength="4" type="text" style="width: 50px; text-align: center;">
					<div class="button">
						<input type="submit" value="加入购物车">
					</div>
				</div>
			</form>
		</div>
		<div id="bar" class="bar">
			<ul>
				<li id="introductionTab">商品介绍</li>
			</ul>
		</div>
		<div id="introduction" name="introduction" class="introduction">
			<div class="title">
				<strong>${product.productName}</strong>
			</div>
			<div>
				<p>${product.description}</p>
			</div>
		</div>
	</div>
</div>

<div class="container footer">
	<div class="span24">
		<div class="footerAd">
			<img src="<%=basePath %>static/images/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
		</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
			<li>
				<a>关于我们</a>
				|
			</li>
			<li>
				<a>联系我们</a>
				|
			</li>
			<li>
				<a>诚聘英才</a>
				|
			</li>
			<li>
				<a>法律声明</a>
				|
			</li>
			<li>
				<a>友情链接</a>
				|
			</li>
			<li>
				<a target="_blank">支付方式</a>
				|
			</li>
			<li>
				<a target="_blank">配送方式</a>
				|
			</li>
			<li>
				<a>服务声明</a>
				|
			</li>
			<li>
				<a>广告声明</a>
			</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2024 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>
