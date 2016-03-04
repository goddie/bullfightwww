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


	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">选择队伍</h4>
				</div>
				<div class="modal-body">
					<iframe marginheight="0" marginwidth="0" frameborder="0"
						width="100%"
						src="${pageContext.request.contextPath}/team/admin/sel"></iframe>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">选择场地</h4>
				</div>
				<div class="modal-body">
					<iframe marginheight="0" marginwidth="0" frameborder="0"
						width="100%"
						src="${pageContext.request.contextPath}/arena/admin/sel"></iframe>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/league_left.jsp"></jsp:include>
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
								<div class="text-muted bootstrap-admin-box-title">新增联赛</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="${pageContext.request.contextPath}/league/action/add"
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
											<label class="col-lg-2 control-label" for="typeahead">联赛图标</label>
											<div class="col-lg-10">
												<img id="upimage" src="" alt="" width="100" height="100" />
												<input name="avatar" id="avatar" value="" type="hidden" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">上传图标</label>
											<div class="col-lg-10">
												<iframe
													src="${pageContext.request.contextPath}/album/page/upload"
													style="height: 34px; width: 100%;" frameborder="0"
													marginheight="0" marginwidth="0" scrolling="no"></iframe>
											</div>
										</div>

										
									
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">比赛场地</label>
											<div class="col-xs-6">
												<input name="arenaid" id="arenaid" type="hidden" /> <input
													name="arenaname" type="text" class="form-control col-md-6"
													id="arenaname" />
											</div>
											<div class="col-xs-4">
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#myModal3">选择场地</button>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">联赛状态</label>
											<div class="col-lg-10">
												<input type="radio" name="status" id="status0" value="0" checked="checked" /><label for="status0">未开始</label> 
												&nbsp;&nbsp;<input type="radio" name="status" id="status1" value="1" /><label for="status1">未结束</label> 
												&nbsp;&nbsp;<input type="radio" name="status" id="status2" value="2" /><label for="status2">已结束</label> 
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

	
	var nameEle;
	var valueEle;

	$('#myModal1').on('show.bs.modal', function(e) {
		nameEle = $("#hostteamname");
		valueEle = $("#hostteam");
	})

	$('#myModal2').on('show.bs.modal', function(e) {
		nameEle = $("#guestteamname");
		valueEle = $("#guestteam");
	})

	function onsel(e, k, v) {

		if (e == "team") {
			nameEle.val(k);
			valueEle.val(v);
			$('#myModal1').modal('hide');
			$('#myModal2').modal('hide');
		}

		if (e == "arena") {
			$('#arenaname').val(k);
			$('#arenaid').val(v);
			$('#myModal3').modal('hide');
		}
	}
	
	
	
	
</script>