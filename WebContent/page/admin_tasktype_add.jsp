<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增任务分类</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body class="bootstrap-admin-with-small-navbar">
	<jsp:include page="/resource/inc/top_nav.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/admin_menu.jsp"></jsp:include>
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
								<div class="text-muted bootstrap-admin-box-title">新增任务类型</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="${pageContext.request.contextPath}/tasktype/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">选择父级</label>
											<div class="col-lg-10">


												<select name="parentId" id="parentId" class="form-control">

													<option value="-1">新建父级</option>


													<c:forEach var="tasktype" items="${list}">

														<option
															<c:if test="${tasktype.id==selected.id}">selected="selected"</c:if>
															value="${tasktype.id}">${tasktype.name}</option>

													</c:forEach>


												</select> <span class="help-block"></span>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">分类名称</label>
											<div class="col-lg-10">
												<input name="name" type="text" class="form-control col-md-6"
													id="typeahead" autocomplete="off" data-provide="typeahead"
													data-items="4" data-source="">
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