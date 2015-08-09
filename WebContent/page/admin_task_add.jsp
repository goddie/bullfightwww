<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增任务</title>
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
						<c:if test="${msg!=null}">
							<div class="alert alert-info">
								<a class="close" data-dismiss="alert" href="#">&times;</a>
								${msg}
							</div>

						</c:if>
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">发布任务</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form action="${pageContext.request.contextPath}/task/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>
										<legend>基本信息</legend>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">任务标题</label>
											<div class="col-lg-10">
												<input name="title" type="text"
													class="form-control col-md-6" id="title" autocomplete="off"
													data-provide="typeahead" data-items="4" data-source="">
												<p class="help-block">请用一句话来描述您的任务最多20个字</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">任务分类</label>
											<div class="col-lg-10">
												<select name="parentType" id="parentType"
													class="form-control">

												</select> <span class="help-block"></span>
											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError"></label>
											<div class="col-lg-10">
												<select name="subType" id="subType" class="form-control">


												</select> <span class="help-block"></span>
											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label"
												for="textarea-wysihtml5">任务内容</label>
											<div class="col-lg-10">

												<textarea id="ckeditor_full"
													class="form-control textarea-wysihtml5"></textarea>

											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">上传文件</label>
											<div class="col-lg-10">
												<iframe src="${pageContext.request.contextPath}/page/upfile.jsp" style="height: 34px; width: 100%;" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></iframe>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">Q
												Q</label>
											<div class="col-lg-10">
												<input type="text" class="form-control col-md-6" />

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">手机</label>
											<div class="col-lg-10">
												<input type="text" class="form-control col-md-6" />

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">联系人</label>
											<div class="col-lg-10">
												<input type="text" class="form-control col-md-6" />

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">称呼</label>


											<div class="col-lg-10">
												<label class="uniform"> <input class="uniform_on"
													type="radio" name="optionsCheckbox" value="1"
													checked="checked" />先生
												</label> <label class="uniform"> <input class="uniform_on"
													type="radio" name="optionsCheckbox" value="0" />女士

												</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">邮箱</label>
											<div class="col-lg-10">
												<input type="text" class="form-control col-md-6" />

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">座机</label>
											<div class="col-lg-10">
												<input type="text" class="form-control col-md-6" />

											</div>
										</div>
										<legend>悬赏模式</legend>

										<div id="rootwizard">
											<div class="navbar">
												<div class="container">
													<ul class="nav nav-pills">
														<li class="active"><a href="#tab1" data-toggle="tab">单人悬赏
														</a></li>
														<li class=""><a href="#tab2" data-toggle="tab">计件悬赏
														</a></li>

													</ul>
												</div>
											</div>

											<div class="tab-content">
												<div class="tab-pane active" id="tab1">
													<div class="form-group">
														<label class="col-lg-2 control-label" for="focusedInput1">悬赏单价</label>
														<div class="col-lg-10">
															<input class="form-control" id="focusedInput1"
																type="text" value="">
														</div>
													</div>
												</div>
												<div class="tab-pane" id="tab2">
													<div class="form-group">
														<label class="col-lg-2 control-label" for="focusedInput4">悬赏单价</label>
														<div class="col-lg-10">
															<input class="form-control" id="focusedInput4"
																type="text" value="">
														</div>
													</div>
													<div class="form-group">
														<label class="col-lg-2 control-label" for="focusedInput5">选稿数量</label>
														<div class="col-lg-10">
															<input class="form-control" id="focusedInput5"
																type="text" value="">
														</div>
													</div>
												</div>
											</div>
										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="date01">投稿的时间</label>
											<div class="col-lg-10">
												<input type="text" class="form-control datepicker"
													id="date01" value="02/16/12">
												<p class="help-block">投稿截止后，您有48小时的时间进行选稿。</p>
											</div>
										</div>


										<legend>发布结算</legend>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="date01">您发布的任务</label>
											<div class="col-lg-10">

												<p class="help-block">范例：XXX珠宝公司LOGO设计</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="date01">您设定的悬赏模式</label>
											<div class="col-lg-10">

												<p class="help-block">单人悬赏</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="date01">您设定的悬赏金额</label>
											<div class="col-lg-10">

												<p class="help-block">0元</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="date01">您设定的截稿时间</label>
											<div class="col-lg-10">

												<p class="help-block">2015/7/23</p>
												<p class="help-block">在投稿截止后，您有48小时的时间进行选稿</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="date01">结算清单</label>
											<div class="col-lg-10">

												<p class="help-block">托管赏金：0元</p>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="date01">应付总额</label>
											<div class="col-lg-10">

												<p class="help-block">0元</p>
											</div>
										</div>
										<button type="submit" class="btn btn-primary">下一步</button>
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

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/ckeditor.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/adapters/jquery.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resource/bsadmin/vendors/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.datepicker').datepicker();
			// CKEditor Full
			$('textarea#ckeditor_full').ckeditor({
				height : '320px'
			});
		})
	</script>

	<script type="text/javascript">
	
		getParentType();
		
		$('#parentType').bind('change',function(){
			
			getSubType($('#parentType').val());
			
		});
		
		function getParentType() {
			$.getJSON("${pageContext.request.contextPath}/tasktype/json/list",
					{
						parentId : ""
					}, function(data) {
						$.each(data, function(i, item) {
							selectAdd("#parentType",item.id,item.name);
						});
					});
		}
		
		function getSubType(pid) {
			selectClear("#subType");
			$.getJSON("${pageContext.request.contextPath}/tasktype/json/list",
					{
						parentId : pid
					}, function(data) {
						$.each(data, function(i, item) {
							selectAdd("#subType",item.id,item.name);
						});
					});
		}

		function selectAdd(name, value, text) {
			var selObj = $(name);
			selObj.append("<option value='"+value+"'>" + text + "</option>");
		}

		// 清空
		function selectClear(name) {
			var selOpt = $(name + " option");
			selOpt.remove();
		}
		
		function upfile(jsonstr)
		{
			var json = eval(jsonstr);
			alert(json.name);
		}
	</script>

</body>

</html>