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
											<form
												action="${pageContext.request.contextPath}/matchfight/admin/list"
												method="get">
												<div class="dataTables_filter" id="example_filter">
													<input name="p" value="1" type="hidden" /> <label>队名:
														<input type="text" name="name" aria-controls="example"
														value="${name }">
													</label>
													<button type="submit" class="btn btn-sm btn-default">搜索</button>
												</div>
											</form>
										</div>
									</div>
									<table class="table table-striped table-bordered dataTable"
										id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
												<th role="columnheader" style="width: 80px;">序号</th>
												<th role="columnheader">类型</th>
												<th role="columnheader" style="width: 10%;">主队</th>
												<th role="columnheader" style="width: 10%;">客队</th>

												<th role="columnheader" style="width: 10%;">场地</th>

												<th role="columnheader" style="width: 10%;">队伍人数</th>
												<th role="columnheader">比赛时间</th>
												<th role="columnheader" style="width: 10%;">比分</th>
												<th role="columnheader" style="">胜</th>
												<th role="columnheader" style="">负</th>
												<th role="columnheader" style="width: 10%;">状态</th>
												<th role="columnheader" style="width: 10%;">支付</th>
												<th role="columnheader" style="width: 20%;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class=""><c:if test="${m.matchType==1 }">团队</c:if>
													<c:if test="${m.matchType==2 }">野球</c:if>
													<c:if test="${m.matchType==3 }">联赛</c:if></td>
													<td class="">${m.host.name}</td>
													<td class="">${m.guest.name}</td>
													<td class="center ">${m.arena.name}</td>
													<td class="center ">${m.teamSize}</td>

													<td class="center ">${m.createdDate}</td>
													<td class="center ">${m.hostScore}-${m.guestScore}</td>
													<td class="center ">${m.winner.name}</td>
													<td class="center ">${m.loser.name}</td>
													<td class="center ">
													<c:if test="${m.status==2 }">已结束</c:if>
													<c:if test="${m.status==1 }">未开始</c:if>
													<c:if test="${m.status==0 }">待接招</c:if>
													 </td>
													<td class="center ">${m.isPay}</td>
													<td class="action"><a
														href="${pageContext.request.contextPath}/matchdatauser/admin/add?mfid=${m.id}&tid=${m.host.id}">
															主个人数据 </a><br/> <a
														href="${pageContext.request.contextPath}/matchdatauser/admin/add?mfid=${m.id}&tid=${m.guest.id}">
															客个人数据 </a><br/> <a href="${pageContext.request.contextPath}/matchdatateam/admin/list?mfid=${m.id}"
														> 队伍数据 </a> <a href="javascript:void(0)"
														onclick="finish('${m.id}')"> 计算成绩 </a><br/><a
														href="${pageContext.request.contextPath}/team/edit/${m.id}">
															编辑 </a> <a
														href="${pageContext.request.contextPath}/matchfight/action/del?mfid=${m.id}">
															删除 </a><a
														href="${pageContext.request.contextPath}/matchfight/action/pay?mfid=${m.id}">
															支付 </a></td>
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