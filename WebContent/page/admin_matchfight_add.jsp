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

	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
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
				<jsp:include page="/resource/inc/matchfight_left.jsp"></jsp:include>
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
								<div class="text-muted bootstrap-admin-box-title">新增比赛</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="${pageContext.request.contextPath}/matchfight/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="select02">比赛类型</label>
											<div class="col-lg-10">
												<select id="matchType" name="matchType"
													class="selectize-select" style="width: 150px">
													<option value="1">团队约战</option>
													<option value="2">野球娱乐</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">主队</label>
											<div class="col-xs-6">
												<input name="hostteam" id="hostteam" type="hidden" /> <input
													name="hostteamname" type="text"
													class="form-control col-md-6" id="hostteamname" />
											</div>
											<div class="col-xs-4">
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#myModal1">选择主队</button>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">客队</label>
											<div class="col-xs-6">
												<input name="guestteam" id="guestteam" type="hidden" /> <input
													name="guestteamname" type="text"
													class="form-control col-md-6" id="guestteamname" />
											</div>
											<div class="col-xs-4">
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#myModal2">选择客队</button>
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
											<label class="col-lg-2 control-label" for="typeahead">比赛人数</label>
											<div class="col-lg-10">
												<select name="teamSize" id="teamSize"
													class="selectize-select" style="width: 150px">
													<option value="5">5v5</option>
													<option value="4">4v4</option>
													<option value="3">3v3</option>
													<option value="1">1v1</option>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">开始时间</label>
											<div class="col-lg-10">

												<input type="text" name="startDateStr"
													class="form-control datepicker" id="startDateStr"
													value="2015-01-01 12:00">

											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">结束时间</label>
											<div class="col-lg-10">
												<input type="text" name="endDateStr"
													class="form-control datepicker" id="endDateStr"
													value="2015-01-01 14:00">

											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">裁判人数</label>
											<div class="col-lg-10">
												<input name="judge" type="text" value="1"
													class="form-control col-md-6" id="judge" />
											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">数据员</label>
											<div class="col-lg-10">
												<input name="dataRecord" value="3" type="text"
													class="form-control col-md-6" id="dataRecord" />
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

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter 
		"S" : this.getMilliseconds()
	//millisecond 
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}

	var now = new Date(); 
	var nowStr = now.format("yyyy-MM-dd hh:mm"); 
	
	$('#startDateStr').val(nowStr);
	$('#endDateStr').val(nowStr);

	$('.datepicker').datetimepicker({
		format : 'yyyy-mm-dd hh:ii',
		language : 'zh-CN',
		weekStart : 1
	});

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