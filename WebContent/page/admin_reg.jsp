<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">

				<form method="post"
					action="${pageContext.request.contextPath}/member/reg"
					class="bootstrap-admin-login-form">
					<h1>用户注册</h1>
					<div class="form-group">
						<input class="form-control" type="text" name="username"
							placeholder="输入用户名" />
					</div>
					<div class="form-group">
						<input class="form-control" type="password" name="password"
							placeholder="输入密码" />
					</div>
					 
					<button class="btn btn-lg btn-primary" type="submit">注册</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
</body>
</html>