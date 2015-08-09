<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>潮人码头 - 文化产业电子商务平台 - 登录</title>
<jsp:include page="/resource/inc/web_style.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/resource/inc/web_nav.jsp"></jsp:include>
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-8">
				<div class="vipLogin m_r20">
					<h2 class="l-title">会员注册</h2>
					<div class="col-sm-12 col-md-6">

						<form name="form1" action="${pageContext.request.contextPath}/member/reg" method="POST">
							<div class="form-table">
								<label>电子邮件</label> <input name="username" type="text"
									class="form-control">
							</div>
							<div class="form-table">
								<label>密码</label> <input name="password" type="password"
									class="form-control">
							</div>
							<div class="form-table">
								<label>确认密码</label> <input name="password2" type="password"
									class="form-control">
							</div>
							<a href="javascript:void(0)" onclick="form1.submit()" class="redBtn5">登录</a>

						</form>
					</div>


					<div class="col-sm-12 col-md-6">
						<div class="shouquan">
							<p>
								<a href="#"><img
									src="${pageContext.request.contextPath}/resource/web/images//wblogin.png"
									width="146" height="32"></a>
							</p>
							<p>
								<a href="#"><img
									src="${pageContext.request.contextPath}/resource/web/images//qqlogin.png"
									width="170" height="32"></a>
							</p>
							<p>
								<a href="#"><img
									src="${pageContext.request.contextPath}/resource/web/images//dblogin.png"
									width="168" height="32"></a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-12 m_t30" style="height: 350px;">
					<div class="phoneLogin m_r20" style="display: none; height: 300px;">
						<h2 class="l-title">手机登录</h2>
						<div class="col-sm-12 col-md-6">
							<div class="input-table p_t10">
								<input class="form-control" placeholder="请输入你的手机号">
							</div>
							<a href="#" class="redBtn5 m_t30">登录</a>
							<div class="notice">
								<p>* 请输入你首次创建应用时候的手机号.</p>
								<p>* 请确保你输入的手机号能接收验证码.</p>
								<p>* 首次使用手机登录网站不需要验证码.</p>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="help m_l20">
					<h2 class="l-title">需要帮助？</h2>
					<ul>
						<li><a href="#">新用户注册</a></li>
						<li><a href="#">忘记密码？</a></li>
						<li><a href="#">查看帮助说明</a></li>
						<li>
							<dl>
								<dt>联系客服：</dt>
								<dd class="p_t10">
									<img
										src="${pageContext.request.contextPath}/resource/web/images//icon-qq-boy.png"
										width="60" height="60"><img
										src="${pageContext.request.contextPath}/resource/web/images//icon-qq-girl.png"
										width="60" height="60"><img
										src="${pageContext.request.contextPath}/resource/web/images//icon-weixin.png"
										width="60" height="60">
								</dd>
								<dd class="p_t25">
									热线电话：<span class="f_c_fd0100">18200000000</span>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/resource/inc/web_foot.jsp"></jsp:include>
</body>
</html>