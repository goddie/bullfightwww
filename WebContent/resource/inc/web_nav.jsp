<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-static-top"
	style="margin-bottom: 0px;">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><img
				src="${pageContext.request.contextPath}/resource/web/images/logo.png" /></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/page/index.jsp" target="_self">首页</a></li>
				<li><a href="${pageContext.request.contextPath}/page/task.jsp" target="_self">任务大厅</a></li>
				<li><a href="${pageContext.request.contextPath}/page/online.jsp" target="_self">在线创作</a></li>
				<li><a href="${pageContext.request.contextPath}/page/online.jsp" target="_self">在线办公</a></li>
				<li><a href="${pageContext.request.contextPath}/page/shop.jsp" target="_self">创意商城</a></li>
				<li><a href="${pageContext.request.contextPath}/page/forum.jsp" target="_self">创意社区</a></li>
				<li><a href="${pageContext.request.contextPath}/page/member_login.jsp" target="_self" class="login">登陆</a></li>
				<li><a href="${pageContext.request.contextPath}/page/member_register.jsp" target="_self" class="login">注册</a></li>
			</ul>
		</div>
	</div>
</nav>

<style type="text/css">
.navbar-nav>li>a.login { width:80px; font-szie:12px;}
</style>