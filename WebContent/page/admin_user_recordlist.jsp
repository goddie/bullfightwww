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
				<jsp:include page="/resource/inc/user_left.jsp"></jsp:include>
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">球员列表</div>
								</a>
							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">
									<div class="row">

										<div class="col-md-6">
											<form
												action="${pageContext.request.contextPath}/user/admin/recordlist"
												method="post">
							<div class="dataTables_filter" id="example_filter">
							 <input name="p" value="1" type="hidden" />
								<label>昵称: <input name="key" type="text"
									aria-controls="example"></label><button type="submit" class="btn btn-sm btn-default">搜索</button>
							</div>
							</form>
										</div>
									</div>
									<table class="table table-striped table-bordered dataTable"
										id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
												<th role="columnheader" style="width: 80px;">序号</th>
												<th role="columnheader" style="width: 10%;">头像</th>
												<th role="columnheader" style="width: 10%;">昵称</th>
												<th role="columnheader">身高</th>
												<th role="columnheader">体重</th>
												<th role="columnheader">位置</th>
												<th role="columnheader">总分</th>
												<th role="columnheader">场均</th>
												<th role="columnheader">命中率</th>
												<th role="columnheader">罚球命中率</th>
												<th role="columnheader">三分命中率</th>
												<th role="columnheader">篮板</th>
												<th role="columnheader">助攻</th>
												<th role="columnheader">比赛场次</th>
												<th role="columnheader">胜负平</th>
												<th role="columnheader">关注</th>
												<th role="columnheader">粉丝</th>
											</tr>
										</thead>

										<tbody role="alert" aria-live="polite" aria-relevant="all">

											<c:forEach var="m" varStatus="status" items="${list}">

												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class="" title="${m.id }"><img src="${m.avatar }" width="30"
														height="30" /></td>
													<td class="center ">${m.nickname}</td>
													<td class="center ">${m.height}</td>
													<td class="center ">${m.weight}</td>
													<td class="center ">${m.position}</td>
													<td class="center ">${m.scoring}</td>
													<td class="center ">${m.scoringAvg}</td>
													<td class="center ">${m.goalPercent}</td>
													<td class="center ">${m.freeGoalPercent}</td>
													<td class="center ">${m.threeGoalPercent}</td>
													<td class="center ">${m.rebound}</td>
													<td class="center ">${m.assist}</td>
													<td class="center ">${m.playCount}</td>
													<td class="center ">${m.win}/${m.lose}/${m.draw}</td>
													<td class="center ">${m.follows}</td>
													<td class="center ">${m.fans}</td>

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