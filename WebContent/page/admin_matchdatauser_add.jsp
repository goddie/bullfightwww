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
					<h4 class="modal-title" id="myModalLabel">选择球员</h4>
				</div>
				<div class="modal-body">
					<iframe marginheight="0" marginwidth="0" frameborder="0"
						width="100%"
						src="${pageContext.request.contextPath}/user/admin/sel?tid=${team.id}"></iframe>
				</div>
			</div>
		</div>
	</div>

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
								<div class="text-muted bootstrap-admin-box-title">录入个人成绩</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/matchdatauser/action/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>
										<input name="tid" type="hidden" id="tid" value="${team.id}" />
										<input name="mfid" type="hidden" id="tid"
											value="${matchFight.id}" />
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">参赛队员</label>
											<div class="col-xs-6">
												<input name="nickname" disabled="disabled" type="text"
													class="form-control col-md-6" id="nickname" /> <input
													name="uid" type="hidden" id="uid" />

												<p class="help-block"></p>
											</div>
											<div class="col-xs-4">
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#myModal1">选择队员</button>
											</div>
										</div>
										<div class="form-group">

											<label class="col-lg-2 control-label" for="typeahead">场上位置</label>
											<div class="col-lg-3">
												<select name="position" id="position" class="form-control">
													<option value="1">控球后卫</option>
													<option value="2">得分后卫</option>
													<option value="3">小前锋</option>
													<option value="4">大前锋</option>
													<option value="5">中锋</option>
												</select>
											</div>


										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">得分</label>
											<div class="col-lg-3">
												<input name="scoring" type="text"
													class="form-control col-md-6" id="scoring" value="0" />
												<p class="help-block"></p>
											</div>
											<label class="col-lg-2 control-label" for="typeahead">命中/出手</label>
											<div class="col-lg-2">
												<input name="goal" type="text" class="form-control"
													id="goal"  value="0" />
												<p class="help-block"></p>
											</div>
											<div class="col-lg-1">
												<input name="shot" type="text" class="form-control"
													id="shot"  value="0" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">三分命中/出手</label>
											<div class="col-lg-2">
												<input name="threeGoal" type="text"
													class="form-control col-md-6" id="threeGoal"  value="0" />
												<p class="help-block"></p>
											</div>
											<div class="col-lg-1">
												<input name="threeShot" type="text"
													class="form-control col-md-6" id="threeShot"  value="0" />
												<p class="help-block"></p>
											</div>

											<label class="col-lg-2 control-label" for="typeahead">罚球命中/出手</label>
											<div class="col-lg-2">
												<input name="freeGoal" type="text"
													class="form-control col-md-6" id="freeGoal"  value="0" />
												<p class="help-block"></p>
											</div>
											<div class="col-lg-1">
												<input name="free" type="text" class="form-control col-md-6"
													id="free"  value="0" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">

											<label class="col-lg-2 control-label" for="typeahead">失误</label>
											<div class="col-lg-3">
												<input name="turnover" type="text"
													class="form-control col-md-6" id="turnover"  value="0" />
												<p class="help-block"></p>
											</div>
											<label class="col-lg-2 control-label" for="typeahead">犯规</label>
											<div class="col-lg-3">
												<input name="foul" type="text" class="form-control col-md-6"
													id="foul"  value="0" />
												<p class="help-block"></p>
											</div>

										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">篮板</label>
											<div class="col-lg-3">
												<input name="rebound" type="text"
													class="form-control col-md-6" id="rebound"  value="0" />
												<p class="help-block"></p>
											</div>
											<label class="col-lg-2 control-label" for="typeahead">助攻</label>
											<div class="col-lg-3">
												<input name="assist" type="text"
													class="form-control col-md-6" id="assist"  value="0" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">盖帽</label>
											<div class="col-lg-3">
												<input name="block" type="text"
													class="form-control col-md-6" id="block"  value="0" />
												<p class="help-block"></p>
											</div>
											<label class="col-lg-2 control-label" for="typeahead">抢断</label>
											<div class="col-lg-3">
												<input name="steal" type="text"
													class="form-control col-md-6" id="steal"  value="0" />
												<p class="help-block"></p>
											</div>
										</div>
										<button type="submit" class="btn btn-primary">提交</button>
										<button type="reset" class="btn btn-default">取消</button>

									</fieldset>


								</form>


							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">已录成绩</div>
								</a>
							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">

									<table class="table table-striped table-bordered dataTable"
										id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
												<th role="columnheader" style="width: 80px;">序号</th>
												<th role="columnheader" style="width: 10%;">队员</th>


												<th role="columnheader">位置</th>
												<th role="columnheader">得分</th>

												<th role="columnheader">命中/出手/命中率</th>
												<th role="columnheader">三分/出手/命中率</th>

												<th role="columnheader">篮板</th>
												<th role="columnheader">助攻</th>
												<th role="columnheader">盖帽</th>
												<th role="columnheader">抢断</th>
												<th role="columnheader">操作</th>
											</tr>
										</thead>

										<tbody>

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class="center "><img src="${m.user.avatar}" width="30" height="30"/><br/>${m.user.nickname}</td>


													<td class="center ">${m.position}</td>
													<td class="center ">${m.scoring}</td>

													<td class="center ">${m.goal}/${m.shot}/${m.goalPercent}</td>
													<td class="center ">${m.threeGoal}/${m.threeShot}/${m.threeGoalPercent}</td>

													<td class="center ">${m.rebound}</td>
													<td class="center ">${m.assist}</td>
													<td class="center ">${m.block}</td>
													<td class="center ">${m.steal}</td>

													<td class="center ">删除</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

								</div>
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
	function onsel(e, k, v) {

		$("#nickname").val(k);
		$("#uid").val(v);
		$('#myModal1').modal('hide');

	}
</script>