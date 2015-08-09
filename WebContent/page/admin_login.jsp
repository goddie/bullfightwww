<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body class="bootstrap-admin-without-padding">

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<c:if test="${msg!=null}">
					<div class="alert alert-info">
						<a class="close" data-dismiss="alert" href="#">&times;</a> ${msg}
					</div>

				</c:if>

				<form method="post"
					action="${pageContext.request.contextPath}/member/login"
					class="bootstrap-admin-login-form">
					<h1>管理登录</h1>
					<div class="form-group">
						<input class="form-control" type="text" name="username"
							placeholder="输入用户名" />
					</div>
					<div class="form-group">
						<input class="form-control" type="password" name="password"
							placeholder="输入密码" />
					</div>
					<div class="form-group">
						<label> <input type="checkbox" name="remember_me">
							记住密码
						</label>
					</div>
					<button class="btn btn-lg btn-primary" type="submit">登录</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {
			// Setting focus
			$('input[name="email"]').focus();

			// Setting width of the alert box
			var alert = $('.alert');
			var formWidth = $('.bootstrap-admin-login-form').innerWidth();
			var alertPadding = parseInt($('.alert').css('padding'));

			if (isNaN(alertPadding)) {
				alertPadding = parseInt($(alert).css('padding-left'));
			}

			$('.alert').width(formWidth - 2 * alertPadding);
		});
	</script>
</body>
</html>