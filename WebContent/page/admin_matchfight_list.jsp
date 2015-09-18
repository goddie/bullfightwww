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
				<jsp:include page="/resource/inc/matchfight_left.jsp"></jsp:include>
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">比赛列表</div>
								</a>
							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row">

										<div class="col-md-12">
											
										</div>
									</div>
									<table class="table table-striped table-bordered dataTable"
										id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
												<th role="columnheader" style="width: 80px;">序号</th>
												<th role="columnheader" style="width: 10%;">主队</th>
												<th role="columnheader" style="width: 10%;">客队</th>

												<th role="columnheader" style="width: 10%;">场地</th>

												<th role="columnheader" style="width: 10%;">队伍人数</th>
												<th role="columnheader">比赛时间</th>
												<th role="columnheader" style="width: 10%;">比分</th>
												<th role="columnheader" style="width: 10%;">状态</th>
												<th role="columnheader" style="width: 20%;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class="">${m.host.name}</td>
													<td class="">${m.guest.name}</td>
													<td class="center ">${m.arena.name}</td>
													<td class="center ">${m.teamSize}</td>

													<td class="center ">${m.createdDate}</td>
													<td class="center ">${m.hostScore}-${m.guestScore}</td>
													<td class="center ">${m.status}</td>
													<td class="action"><a
														href="${pageContext.request.contextPath}/matchdatauser/admin/add?mfid=${m.id}&tid=${m.host.id}">
															主队成绩 </a> <a
														href="${pageContext.request.contextPath}/matchdatauser/admin/add?mfid=${m.id}&tid=${m.guest.id}">
															客队成绩 </a> <a href="javascript:void(0)"
														onclick="finish('${m.id}')"> 结束比赛 </a> <a
														href="${pageContext.request.contextPath}/team/edit/${m.id}">
															编辑 </a> <a
														href="${pageContext.request.contextPath}/matchfight/action/del?mfid=${m.id}">
															删除 </a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">${pageHtml }</div>
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
	function finish(mfid) {

		if (!confirm('确定要结束比赛吗?')) {
			return;
		}

		$.getJSON("${pageContext.request.contextPath}/matchfight/json/finish",
				{
					mfid : mfid,
					t : new Date()
				}, function(json) {
					alert(json.msg);
				});
	}
</script>