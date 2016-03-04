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
				<jsp:include page="/resource/inc/league_left.jsp"></jsp:include>
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
								<div class="text-muted bootstrap-admin-box-title">联赛积分</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="${pageContext.request.contextPath}/leaguerecord/action/update"
									name="form1" method="post" class="form-horizontal">
									<input type="hidden" name="id" value="${entity.id }" />
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">联赛</label>
											<div class="col-lg-10">
												 
												<p class="help-block">${league.name }</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">队伍</label>
											<div class="col-lg-10">
												 
												<p class="help-block">${team.name }</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">胜</label>
											<div class="col-xs-6">
												<input
													name="win" value="${entity.win }" type="text" class="form-control col-md-6"
													id="win" />
											</div>
 
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">负</label>
											<div class="col-xs-6">
												<input
													name="lose" value="${entity.lose }" type="text" class="form-control col-md-6"
													id="lose" />
											</div>
 
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">积分</label>
											<div class="col-xs-6">
												<input
													name="score" value="${entity.score }" type="text" class="form-control col-md-6"
													id="score" />
											</div>
 
										</div>
 

										<button type="submit" class="btn btn-primary">提交</button>
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
	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
</body>
</html>
