<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>账户信息</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>会员中心首页</a></li>
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>个人资料修改</a></li>

		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>安全中心</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>登录密码</a></li>
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>支付密码</a></li>
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>手机绑定</a></li>
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>邮箱绑定</a></li>
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>企业认证</a></li>
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>实名认证</a></li>
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>银行卡认证</a></li>
		</ul></li>

	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>我的消息</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/message/inbox/1"><i
					class="glyphicon glyphicon-chevron-right"></i>收件箱</a></li>
			<li><a
				href="${pageContext.request.contextPath}/message/outbox/1"><i
					class="glyphicon glyphicon-chevron-right"></i>发件箱</a></li>
			<li><a
				href="${pageContext.request.contextPath}/message/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>发送消息</a></li>
			<li><a href="${pageContext.request.contextPath}"><i
					class="glyphicon glyphicon-chevron-right"></i>消息设置</a></li>

		</ul></li>

</ul>