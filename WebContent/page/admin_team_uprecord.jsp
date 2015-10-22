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
						<c:if test="${msg!=null}">
							<div class="alert alert-info">
								<a class="close" data-dismiss="alert" href="#">&times;</a>
								${msg}
							</div>

						</c:if>
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">比赛记录</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="${pageContext.request.contextPath}/matchdatauser/action/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">用户名</label>
											<div class="col-lg-10">
												<input name="username" type="text"
													class="form-control col-md-6" id="city" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">得分</label>
											<div class="col-lg-3">
												<input name="scoring" type="text"
													class="form-control col-md-6" id="scoring" />
												<p class="help-block"></p>
											</div>
											<label class="col-lg-2 control-label" for="typeahead">命中/出手</label>
											<div class="col-lg-2">
												<input name="shot" type="text"
													class="form-control" id="shot" />
												<p class="help-block"></p>
											</div>
											<div class="col-lg-1">
												<input name="goal" type="text"
													class="form-control" id="goal" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">三分命中/出手</label>
											<div class="col-lg-2">
												<input name="threeGoal" type="text"
													class="form-control col-md-6" id="threeGoal" />
												<p class="help-block"></p>
											</div>
											<div class="col-lg-1">
												<input name="threeShot" type="text"
													class="form-control col-md-6" id="threeShot" />
												<p class="help-block"></p>
											</div>
											
											<label class="col-lg-2 control-label" for="typeahead">罚球命中/出手</label>
												<div class="col-lg-2">
												<input name="freeGoal" type="text"
													class="form-control col-md-6" id="freeGoal" />
												<p class="help-block"></p>
											</div>
											<div class="col-lg-1">
												<input name="free" type="text"
													class="form-control col-md-6" id="free" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">篮板</label>
											<div class="col-lg-3">
												<input name="rebound" type="text"
													class="form-control col-md-6" id="rebound" />
												<p class="help-block"></p>
											</div>
											<label class="col-lg-2 control-label" for="typeahead">助攻</label>
											<div class="col-lg-3">
												<input name="assist" type="text"
													class="form-control col-md-6" id="assist" />
												<p class="help-block"></p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">盖帽</label>
											<div class="col-lg-3">
												<input name="block" type="text"
													class="form-control col-md-6" id="block" />
												<p class="help-block"></p>
											</div>
											<label class="col-lg-2 control-label" for="typeahead">抢断</label>
											<div class="col-lg-3">
												<input name="steal" type="text"
													class="form-control col-md-6" id="steal" />
												<p class="help-block"></p>
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
