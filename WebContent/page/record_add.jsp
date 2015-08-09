<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
<title>Insert title here</title>

</head>
<body class="bootstrap-admin-with-small-navbar">
	<!-- small navbar -->
	<nav
		class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar-sm"
		role="navigation"> </nav>

	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/admin_menu.jsp"></jsp:include>
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header">
							<h1>新增比赛数据</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">新增比赛数据</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="${pageContext.request.contextPath}/record/add"
									class="form-horizontal" method="post">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="team_name">球队</label>
											<div class="col-lg-8">
												<input class="form-control" name="team_name" id="team_name"
													type="text" value="" /> <input name="team_id" id="team_id"
													type="hidden" value=""/>
											</div>
											<div class="col-lg-2">
												<a href="#myModal" data-toggle="modal"
													class="btn btn-primary">选择球队</a>

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="player_name">球员</label>
											<div class="col-lg-8">
												<input class="form-control" name="player_name"
													id="player_name" type="text" value="" /> <input
													name="player_id" id="player_id" type="hidden" value="" />
											</div>
											<div class="col-lg-2">
												<a href="#myModal2" data-toggle="modal"
													class="btn btn-primary">选择球员</a>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="no">号码</label>
											<div class="col-lg-10">
												<input class="form-control" name="no" id="no" type="text"
													value="0" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="pts">得分</label>
											<div class="col-lg-10">
												<input class="form-control" name="pts" id="pts" type="text"
													value="0" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="fg">命中</label>
											<div class="col-lg-10">
												<input class="form-control" name="fg" id="fg" type="text"
													value="0" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="shot">出手</label>
											<div class="col-lg-10">
												<input class="form-control" name="shot" id="shot"
													type="text" value="0" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="shot3">3分出手</label>
											<div class="col-lg-10">
												<input class="form-control" name="shot3" id="shot3"
													type="text" value="0" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="fg3">3分命中</label>
											<div class="col-lg-10">
												<input class="form-control" name="fg3" id="fg3" type="text"
													value="0" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="reb">篮板球</label>
											<div class="col-lg-10">
												<input class="form-control" name="reb" id="reb" type="text"
													value="0" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="ast">助攻</label>
											<div class="col-lg-10">
												<input class="form-control" name="ast" id="ast" type="text"
													value="0" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="stl">抢断</label>
											<div class="col-lg-10">
												<input class="form-control" name="stl" id="stl" type="text"
													value="0" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="blk">盖帽</label>
											<div class="col-lg-10">
												<input class="form-control" name="blk" id="blk" type="text"
													value="0" />
											</div>
										</div>
										<button type="submit" class="btn btn-primary">保存</button>
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
	<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 id="myModalLabel" class="modal-title">选择球队</h4>
				</div>
				<div class="modal-body">
					<iframe id="chooseIframe" width="100%" height="100%"
						marginheight="0" marginwidth="0" frameborder="0"
						src="${pageContext.request.contextPath}/team/jspchoose"></iframe>
				</div>

			</div>
		</div>
	</div>
	
	<div id="myModal2" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 id="myModalLabel2" class="modal-title">选择球员</h4>
				</div>
				<div class="modal-body">
					<iframe id="chooseIframe2" width="100%" height="100%"
						marginheight="0" marginwidth="0" frameborder="0"
						src="${pageContext.request.contextPath}/player/jspchoose"></iframe>
				</div>

			</div>
		</div>
	</div>
	
	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
	<script type="text/javascript">
		function parseMap(map) {
			var keys = map.keySet();
			for (var i = 0; i < keys.length ; i++) {
				var k = keys[i];
				$('#' + k).val(map.get(k));
			}
		}
	</script>
</body>
</html>