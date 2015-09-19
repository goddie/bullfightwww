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
				<jsp:include page="/resource/inc/payrecord_left.jsp"></jsp:include>
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">交易记录</div>
								</a>
							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row">

										<div class="col-md-12">
											<form action="${pageContext.request.contextPath}/payrecord/admin/list" method="get">
												<div class="dataTables_filter" id="example_filter">
													<input name="p" value="1" type="hidden" />
													<label>用户: <input type="text" name="username"
														aria-controls="example" value="${username }"></label>
											 
													<button type="submit" class="btn btn-sm btn-default">搜索</button>
												</div>
											</form>
										</div>
									</div>
									<table class="table table-striped table-bordered dataTable"
										id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
												<th role="columnheader" style="">序号</th>
												<th role="columnheader" style="width: 15%;">日期</th>
												<th role="columnheader" style="width: 10%;">金额</th>

												<th role="columnheader" style="">比赛队伍</th>
												<th role="columnheader" style="">场地</th>
												<th role="columnheader" style="">裁判</th>
												<th role="columnheader" style="">数据员</th>
												<th role="columnheader" style="">用户</th>

											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class="">${m.createdDate}</td>
													<td class="">${m.total}</td>
													<td class="center ">${m.matchFight.host.name } vs ${m.matchFight.guest.name }</td>
													<td class="center ">${m.matchFight.arena.name }</td>
													<td class="center ">${m.matchFight.judge }</td>
													<td class="center ">${m.matchFight.dataRecord }</td>
													<td class="center ">${m.user.nickname} ${m.user.username}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">

										${pageHtml }
									</div>
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