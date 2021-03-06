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
								<div class="text-muted bootstrap-admin-box-title">队伍列表</div>
								</a>
							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row">

										<div class="col-md-12">
											<form
												action="${pageContext.request.contextPath}/team/admin/list"
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
												<th role="columnheader" style="width: 5%;">队标</th>
												<th role="columnheader" style="width: 15%;">名称</th>
												<th role="columnheader" style="width: 10%;">城市</th>

												<th role="columnheader" style="width: 10%;">队长</th>

												<th role="columnheader" style="width: 10%;">状态</th>
												<th role="columnheader">成立时间</th>
												<th role="columnheader" style="width: 20%;">操作</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class=""><img src="${m.avatar }" width="30"
														height="30" /></td>
													<td class="">${m.name}</td>
													<td class="">${m.city}</td>
													<td class="center ">${m.admin.nickname}</td>
													<td class="center ">${m.status}</td>

													<td class="center ">${m.createdDate}</td>
													<td class="action"><a href="${pageContext.request.contextPath}/matchfight/admin/teamlist?teamid=${m.id}&p=1">
																对战 </a> 
													<a href="${pageContext.request.contextPath}/team/action/del?id=${m.id}">
																删除 </a> 
																
														<div style="display: none">
															<a
																href="${pageContext.request.contextPath}/team/edit/${m.id}">
																编辑 </a> <a
																href="${pageContext.request.contextPath}/team/del/${m.id}">
																删除 </a> <a
																href="${pageContext.request.contextPath}/team/member/">
																查看成员 </a>
														</div>
													</td>
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