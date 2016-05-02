<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建文章</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body class="bootstrap-admin-with-small-navbar">

	<jsp:include page="/resource/inc/top_nav.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/article_left.jsp"></jsp:include>
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
								<div class="text-muted bootstrap-admin-box-title">编辑文章</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/article/action/update"
									name="form1" method="post" class="form-horizontal">
									<input name="topType" value="${topType }" type="hidden" />
									<input name="id" value="${entity.id }" type="hidden" />
									<fieldset>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError">分类</label>
											<div class="col-lg-10">
												<select name="parentTypeId" id="parentTypeId"
													class="form-control">

												</select> <span class="help-block"></span>
											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="selectError"></label>
											<div class="col-lg-10">
												<select name="subTypeId" id="subTypeId" class="form-control">
												</select> <span class="help-block"></span>
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">标题</label>
											<div class="col-lg-10">
												<input name="title" type="text"
													class="form-control col-md-6" id="title" value="${entity.title }" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">封面图</label>
											<div class="col-lg-10">
												<img id="upimage" src="${entity.thumb}" alt="" width="120" height="90" />
												<input name="thumb" id="thumb" value="${entity.thumb }" type="hidden" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="fileInput">上传图片</label>
											<div class="col-lg-10">
												<iframe
													src="${pageContext.request.contextPath}/album/page/upload"
													style="height: 34px; width: 100%;" frameborder="0"
													marginheight="0" marginwidth="0" scrolling="no"></iframe>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label"
												for="textarea-wysihtml5">简介</label>
											<div class="col-lg-10">

												<textarea id="description" name="description"
													class="form-control">${entity.description }</textarea>

											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label"
												for="textarea-wysihtml5">内容</label>
											<div class="col-lg-10">

												<textarea id="ckeditor_full" name="content"
													class="form-control textarea-wysihtml5">${body.body}</textarea>

											</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">跳转URL</label>
											<div class="col-lg-10">
												<input name="redirectUrl" type="text"
													class="form-control col-md-6" id="redirectUrl"
													autocomplete="off" data-provide="typeahead" data-items="4"
													data-source="">
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



</body>

</html>

<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/bsadmin/vendors/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
	var editor;

	var pid = '${entity.type.id}';
	var pname = '${entity.type.name}';
	var sid = '${entity.subType.id}';
	var sname = '${entity.subType.name}';
	
	$(function() {

		$('.datepicker').datepicker({
			format : 'yyyy-mm-dd',
			weekStart : 1,
			autoclose : true,
			todayBtn : 'linked'
		});
		// CKEditor Full
		editor = $('textarea#ckeditor_full').ckeditor({
			height : '320px'
		}).editor;

		
		getParentType();
		

	})

	$('#parentTypeId').bind('change', function() {

		getSubType($('#parentTypeId').val());

	});

	function getParentType() {
		$.getJSON("${pageContext.request.contextPath}/articletype/json/list", {
			parentId : ""
		}, function(data) {
			$.each(data, function(i, item) {
				if (i == 0) {
					getSubType(item.id);
				}
				selectAdd("#parentTypeId", item.id, item.name);
			});
			
			
		});
	}

	function getSubType(pid) {
		selectClear("#subTypeId");
		$.getJSON("${pageContext.request.contextPath}/articletype/json/list", {
			parentId : pid,
			t : new Date()
		}, function(data) {
			$.each(data, function(i, item) {
				selectAdd("#subTypeId", item.id, item.name);
			});
			
			
			selectAdd('#parentTypeId',pid,pname);
			selectAdd('#subTypeId',sid,sname);
			$("#parentTypeId option[value='"+pid+"']").attr("selected", "selected"); 
			$("#subTypeId option[value='"+sid+"']").attr("selected", "selected");
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

	function upfile(jsonstr) {
		var obj = jQuery.parseJSON(jsonstr);
		editor.insertHtml('<img src="' + obj.path
				+ getThumb(obj.name, 1000, 1000) + '"/>');

		if (obj.cover == 1) {
			$('#upimage').attr('src', obj.path + getThumb(obj.name, 240, 180));
			$('#thumb').val(obj.path + getThumb(obj.name, 240, 180));
		}

	}

	function getThumb(filename, w, h) {
		var ext = filename.substring(filename.lastIndexOf('.'));
		var newfile = filename.substring(0, filename.lastIndexOf('.')) + "_"
				+ w + "x" + h + ext;
		return newfile;
	}
	
	
	
</script>