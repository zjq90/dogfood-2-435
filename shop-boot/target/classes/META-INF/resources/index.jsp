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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>商城</title>
	<link href="<%=basePath %>static/css/slider.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath %>static/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath %>static/css/index.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery-3.1.1.min.js"></script>
</head>
<body>

<div class="container all">
	<div id="allpages" class="containerpages">
		<div class="container header">
			<div class="span5">
				<div class="logo">
					<a href="<%=basePath %>index.jsp">
						<img src="<%=basePath %>static/images/header.jpg" alt="商城"/>
					</a>
				</div>
			</div>
			<div class="span9">
				<div class="headerAd">
					<img src="<%=basePath %>static/images/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
				</div>	</div>
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

						<li id="headerUsername" class="headerUsername" ></li>
						<c:if test="${sessionScope.frontUser!=null}">
							<li id="headerLogout" class="headerLogout" style="display: list-item;">
								<a href="<%=basePath %>user/logout">[退出]</a>|
							</li>
						</c:if>

						<li>
							<a>会员中心</a>
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
					<a  href="<%=basePath %>car.jsp">购物车</a>
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
		<div class="container index">
			<div class="span24">
				<div id="newProduct" class="newProduct clearfix">
					<div class="title">
						<strong>全部商品</strong>
						<a  target="_blank" ></a>
					</div>
					<ul class="tab">
						<li class="current">
							<a href="#" target="_blank"></a>
						</li>
						<li>
							<a  target="_blank"></a>
						</li>
						<li>
							<a target="_blank"></a>
						</li>
					</ul>
					<div class="pages">
						<ul class="tabContent" style="display: block;">
							<c:forEach items="${pageInfo.list}" var="product">
								<li>
									<a target="_blank" href="<%=basePath%>product/detail?id=${product.productId}">
										<img src="<%=basePath %>upload/${product.picture}" style="display: block; width: 150px; height: 150px;">
										<div style="text-align: center; margin-top: 5px;">${product.productName}</div>
										<div style="text-align: center; color: #ff6600;">¥${product.costPrice}</div>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div id="page_div" style="text-align: center; margin-top: 20px;">
						<c:if test="${pageInfo.hasPreviousPage}">
							<a href="<%=basePath%>product/frontlist?pageNum=${pageInfo.prePage}">上一页</a>
						</c:if>
						<span>第 ${pageInfo.pageNum} 页 / 共 ${pageInfo.pages} 页</span>
						<c:if test="${pageInfo.hasNextPage}">
							<a href="<%=basePath%>product/frontlist?pageNum=${pageInfo.nextPage}">下一页</a>
						</c:if>
					</div>
				</div>

				<!-- 热门商品 -->
				<div style="margin-top: 30px;">
					<div class="title">
						<strong>热门商品</strong>
					</div>
					<ul class="tabContent" style="display: block;">
						<c:forEach items="${hotProducts}" var="product">
							<li style="display: inline-block; margin: 10px;">
								<a target="_blank" href="<%=basePath%>product/detail?id=${product.productId}">
									<img src="<%=basePath %>upload/${product.picture}" style="display: block; width: 120px; height: 120px;">
									<div style="text-align: center; margin-top: 5px;">${product.productName}</div>
									<div style="text-align: center; color: #ff6600;">¥${product.costPrice}</div>
								</a>
							</li>
						</c:forEach>
					</ul>
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
	</div>
</div>
</body>
</html>
