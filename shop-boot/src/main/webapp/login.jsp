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
<title>会员登录</title>

<link href="<%=basePath %>static/css/common.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>static/css/login.css" rel="stylesheet" type="text/css"/>

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
					<a >购物指南</a>
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

<div class="container login">
	<div class="span12">
		<div class="ad">
			<img src="<%=basePath %>static/images/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
		</div>
	</div>
	<div class="span12 last">
		<div class="wrap">
			<div class="main">
				<div class="title">
					<strong>会员登录</strong>USER LOGIN
				</div>
				<form id="loginForm" action="<%=basePath %>user/login" method="post" novalidate="novalidate">
					<table>
						<tbody>
							<tr>
								<th>
									用户名:
								</th>
								<td>
									<input type="text" id="username" name="username" class="text" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th>
									密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off"/>
								</td>
							</tr>
							<tr>
								<th>&nbsp;</th>
								<td>
									<label>
										<input type="checkbox" id="isRememberUsername" name="isRememberUsername" value="true">记住用户名
									</label>
									<label>
										&nbsp;&nbsp;<a >找回密码</a>
									</label>
								</td>
							</tr>
							<tr>
								<th>&nbsp;</th>
								<td>
									<input type="submit" class="submit" value="登 录">
								</td>
							</tr>
							<tr class="register">
								<th>&nbsp;</th>
								<td>
									<dl>
										<dt>还没有注册账号？</dt>
										<dd>
											立即注册即可体验在线购物！
											<a href="">立即注册</a>
										</dd>
									</dl>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
				<c:if test="${sessionScope.errorMsg != null}">
					<div style="color: red; text-align: center; margin-top: 10px;">
						${sessionScope.errorMsg}
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>

<div class="container footer">
	<div class="span24">
		<div class="footerAd">
			<img src="<%=basePath %>static/images/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
		</div>
	</div>
	<div class="span24">
		<ul class="bottomNav">
			<li>
				<a >关于我们</a>
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
