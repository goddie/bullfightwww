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
<body style="padding: 0">

	<div class="container-fluid">
		<div class="row">
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
								<th role="columnheader" style="width: 80px;">序号</th>
								<th role="columnheader" style="width: 10%;">头像</th>
								<th role="columnheader" style="width: 15%;">用户名</th>
								<th role="columnheader" style="width: 10%;">昵称</th>

								<th role="columnheader" style="width: 10%;">电话</th>


								<th role="columnheader">注册时间</th>
								<th role="columnheader" style="width: 20%;">操作</th>
							</tr>
						</thead>

						<tbody role="alert" aria-live="polite" aria-relevant="all">

							<c:forEach var="m" varStatus="status" items="${list}">

								<tr class="gradeA odd">
									<td class="sorting_1">${status.index+1}</td>
									<td class=""><img src="${m.avatar }" width="30"
										height="30" /></td>
									<td class="">${m.username}</td>
									<td class="">${m.city}</td>
									<td class="center ">${m.nickname}</td>

									<td class="center ">${m.createdDate}</td>
									<td class="action">
										<button type="button" class="btn btn-primary"
											onclick='parent.onsel("user","${m.nickname}","${m.id}")'>选择</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="row" style="display:none">

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
	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
</body>
</html>