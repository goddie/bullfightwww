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
					<h4 class="modal-title" id="myModalLabel">选择联赛</h4>
				</div>
				<div class="modal-body">
					<iframe marginheight="0" marginwidth="0" frameborder="0"
						width="100%"
						src="${pageContext.request.contextPath}/league/admin/sel"></iframe>
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
								<div class="text-muted bootstrap-admin-box-title">新增队伍</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="${pageContext.request.contextPath}/leagueteam/action/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">联赛</label>
											<div class="col-xs-6">
												<input name="leagueid" id="leagueid" type="hidden" /> <input
													name="leaguename" type="text" class="form-control col-md-6"
													id="leaguename" />
											</div>
											<div class="col-xs-4">
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#myModal3">选择联赛</button>
											</div>
										</div>
										 
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">参赛队伍</label>
											<div class="col-xs-6">
												<input name="teamid" id="teamid" type="hidden" /> <input
													name="teamname" id="teamname" type="text"
													class="form-control col-md-6"/>
											</div>
											<div class="col-xs-4">
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#myModal1">选择队伍</button>
											</div>
										</div>
										 

										 
										
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">小组编号</label>
											<div class="col-lg-6">
												<input name="groupNum" id="groupNum" type="text" value="1"
													class="form-control col-md-6"  />
											</div>

										</div>

										   
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">种子队伍</label>
											<div class="col-lg-10">
												<input name="isSeed" value="0" type="radio"
													checked="checked" 
													id="isSeed0" /><label for="isSeed0">否</label>&nbsp;&nbsp;
													<input name="isSeed" value="1" type="radio" id="isSeed1" /><label for="isSeed1">是</label>
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



 

	var nameEle;
	var valueEle;

	$('#myModal1').on('show.bs.modal', function(e) {
		nameEle = $("#teamname");
		valueEle = $("#teamid");
	})

	
		$('#myModal3').on('show.bs.modal', function(e) {
		nameEle = $("#leaguename");
		valueEle = $("#leagueid");
	})

	function onsel(e, k, v) {

		if (e == "team") {
			nameEle.val(k);
			valueEle.val(v);
			$('#myModal1').modal('hide');
			$('#myModal2').modal('hide');
		}

		if (e == "league") {
			$('#leaguename').val(k);
			$('#leagueid').val(v);
			$('#myModal3').modal('hide');
		}
	}

	
</script>