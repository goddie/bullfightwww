<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务大厅</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body class="bootstrap-admin-with-small-navbar">
	<jsp:include page="/resource/inc/top_nav.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/leftmenu_task.jsp"></jsp:include>
			</div>
			<div class="col-md-10">

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">我发布的单人悬赏</div>
								<a href="${pageContext.request.contextPath}/task/admin/add" class="btn btn-xs btn-primary">发布单人悬赏</a>
							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row">
										<div class="col-md-6">
											<div id="example_length" class="dataTables_length">
												<label><select size="1" name="example_length"
													aria-controls="example"><option value="10"
															selected="selected">10</option>
														<option value="25">25</option>
														<option value="50">50</option>
														<option value="100">100</option></select> records per page</label>
											</div>
										</div>
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
												<th class="sorting_asc" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Rendering engine: activate to sort column descending"
													style="width: 161px;">序号</th>
												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="Browser: activate to sort column ascending"
													style="width: 224px;">任务</th>
												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="Platform(s): activate to sort column ascending">赏金</th>
												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="Engine version: activate to sort column ascending">投稿数</th>
												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="CSS grade: activate to sort column ascending">中标人</th>
												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="CSS grade: activate to sort column ascending">状态</th>
												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="CSS grade: activate to sort column ascending">发布时间</th>
												<th class="sorting" role="columnheader" tabindex="0"
													aria-controls="example" rowspan="1" colspan="1"
													aria-label="CSS grade: activate to sort column ascending"
													style="width: 140px;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">
											<tr class="gradeA odd">
												<td class="sorting_1">Gecko</td>
												<td class="">Firefox 1.0</td>
												<td class="">Win 98+ / OSX.2+</td>
												<td class="center ">1.7</td>
												<td class="center ">A</td>
												<td class="center ">A</td>
												<td class="center ">A</td>
												<td class="action"><a
													href="${pageContext.request.contextPath}/task/edit/${m.id}">
														编辑 </a> <a href="${pageContext.request.contextPath}/task/edit/${m.id}">
														删除 </a></td>
											</tr>

										</tbody>
									</table>
									<div class="row">
										<div class="col-md-6">
											<div class="dataTables_info" id="example_info">Showing
												1 to 10 of 57 entries</div>
										</div>
										<div class="col-md-6">
											<div class="dataTables_paginate paging_bootstrap">
												<ul class="pagination">
													<li class="prev disabled"><a href="#">← Previous</a></li>
													<li class="active"><a href="#">1</a></li>
													<li><a href="#">2</a></li>
													<li><a href="#">3</a></li>
													<li><a href="#">4</a></li>
													<li><a href="#">5</a></li>
													<li class="next"><a href="#">Next → </a></li>
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

	</div>
	</div>
	</div>
	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
</body>

</html>