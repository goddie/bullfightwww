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


	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">选择队长</h4>
				</div>
				<div class="modal-body">
					<iframe marginheight="0" marginwidth="0" frameborder="0"
						width="100%"
						src="${pageContext.request.contextPath}/user/admin/sel"></iframe>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/team_left.jsp"></jsp:include>
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
								<div class="text-muted bootstrap-admin-box-title">新增队伍</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="${pageContext.request.contextPath}/team/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">名称</label>
											<div class="col-lg-10">
												<input name="name" type="text" class="form-control col-md-6"
													id="title" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">队长</label>

											<div class="col-xs-6">
												<input name="uid" id="uid" type="hidden" /> <input name="adminname"
													type="text" class="form-control col-md-6" id="adminname" />
											</div>
											<div class="col-xs-4">
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#myModal">选择队长</button>
											</div>


										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">城市</label>
											<div class="col-lg-10">
												<input name="city" type="text" class="form-control col-md-6"
													id="city" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">简介</label>
											<div class="col-lg-10">
												<input name="info" type="text" class="form-control col-md-6"
													id="info" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">队标</label>
											<div class="col-lg-10">
												<img id="upimage" src="" alt="" width="100" height="100" />
												<input name="avatar" id="avatar" value="" type="hidden" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">上传队标</label>
											<div class="col-lg-10">
												<iframe
													src="${pageContext.request.contextPath}/page/upfile.jsp"
													style="height: 34px; width: 100%;" frameborder="0"
													marginheight="0" marginwidth="0" scrolling="no"></iframe>
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
	
	function onsel(e,k,v)
	{
		$('#adminname').val(k);
		$('#uid').val(v);
		$('#myModal').modal('hide');
	}
</script>