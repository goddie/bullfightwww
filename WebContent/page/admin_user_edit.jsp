<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
<title></title>
</head>
<body class="bootstrap-admin-with-small-navbar">

	<jsp:include page="/resource/inc/top_nav.jsp"></jsp:include>


	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/user_left.jsp"></jsp:include>
			</div>
			<div class="col-md-10">




				<div class="row">
					<div class="col-lg-12">
						<c:if test="${msg!=null}">
							<div class="alert alert-info">
								<a class="close" data-dismiss="alert" href="#">&times;</a>
								${msg}
							</div>

						</c:if>
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">修改资料</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="${pageContext.request.contextPath}/user/action/edit"
									name="form1" method="post" class="form-horizontal">
									<input type="hidden" name="id" value="${entity.id }" />
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">用户名</label>
											<div class="col-lg-10">
												<input name="username" type="text"
													class="form-control col-md-6" id="username"
													value="${entity.username }" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">密码</label>
											<div class="col-lg-10">
												<input name="password" type="text"
													class="form-control col-md-6" id="password"
													value="${entity.password }" />
												<p class="help-block"></p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">姓名</label>
											<div class="col-lg-10">
												<input name="nickname" type="text"
													class="form-control col-md-6" id="nickname" value="${entity.nickname }"/>
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">手机</label>
											<div class="col-lg-10">
												<input name="phone" type="text"
													class="form-control col-md-6" id="phone" value="${entity.phone }"/>
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">城市</label>
											<div class="col-lg-10">
												<input name="city" type="text" class="form-control col-md-6"
													id="city" value="${entity.city }"/>
												<p class="help-block"></p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">头像</label>
											<div class="col-lg-10">
												<img id="upimage" src="${pageContext.request.contextPath}${entity.avatar }" alt="" width="100" height="100" />
												<input name="avatar" id="avatar" value="${entity.avatar }" type="hidden" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">上传头像</label>
											<div class="col-lg-10">
												<iframe
													src="${pageContext.request.contextPath}/album/page/upload"
													style="height: 34px; width: 100%;" frameborder="0"
													marginheight="0" marginwidth="0" scrolling="no"></iframe>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">擅长位置</label>
											<div class="col-lg-10">
												<input name="position" type="text"
													class="form-control col-md-6" id="position" value="${entity.position }"/>
												<p class="help-block"></p>
											</div>
										</div>




										<button type="submit" class="btn btn-primary">提交</button>
										<button type="reset" class="btn btn-default">取消</button>

									</fieldset>


								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
</body>
</html>
<script type="text/javascript">
	function upfile(jsonstr) {
		var obj = jQuery.parseJSON(jsonstr);
		$('#upimage').attr('src', obj.path + obj.name);
		$('#avatar').val(obj.path + obj.name);
	}
</script>