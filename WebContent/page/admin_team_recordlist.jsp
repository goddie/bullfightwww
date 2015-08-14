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
				<jsp:include page="/resource/inc/team_left.jsp"></jsp:include>
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">队伍数据</div>
								</a>
							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row">

										<div class="col-md-6">
											<div class="dataTables_filter" id="example_filter">
												<label>Search: <input type="text"
													aria-controls="example"></label>
											</div>
										</div>
									</div>
									<table class="table table-striped table-bordered dataTable"
										id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
												<th role="columnheader" style="width: 6%">序号</th>
												<th role="columnheader" style="width: 6%;">队标</th>
												<th role="columnheader">名称</th>
												<th role="columnheader" style="width: 10%;">比赛场次</th>
												<th role="columnheader" style="width: 6%;">胜利</th>
												<th role="columnheader" style="width: 6%;">失败</th>
												<th role="columnheader" style="width: 6%;">平局</th>
												<th role="columnheader" style="width: 8%;">总得分</th>
												<th role="columnheader" style="width: 6%;">篮板</th>
												<th role="columnheader" style="width: 6%;">盖帽</th>
												<th role="columnheader" style="width: 6%;">抢断</th>
												<th role="columnheader" style="width: 6%;">失误</th>


											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA">
													<td class="sorting_1">${status.index+1}</td>
													<td class=""><img src="${m.avatar }" width="30"
														height="30" /></td>
													<td>${m.name}</td>
													<td>${m.playCount}</td>
													<td>${m.win}</td>
													<td>${m.lose}</td>
													<td>${m.draw}</td>
													<td>${m.scoring}</td>
													<td>${m.rebound}</td>
													<td>${m.block}</td>
													<td>${m.steal}</td>
													<td>${m.turnover}</td>

												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">

										<div class="col-md-6">
											<div class="dataTables_paginate paging_bootstrap">
												<ul class="pagination">
													<li class="prev disabled"><a href="#">← 上页</a></li>
													<li class="active"><a href="#">1</a></li>
													<li><a href="#">2</a></li>
													<li><a href="#">3</a></li>
													<li><a href="#">4</a></li>
													<li><a href="#">5</a></li>
													<li class="next"><a href="#">下页 → </a></li>
												</ul>
											</div>
										</div>
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