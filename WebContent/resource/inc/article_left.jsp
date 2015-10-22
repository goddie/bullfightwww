<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>文章管理</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/article/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>新增文章</a></li>
			<li><a
				href="${pageContext.request.contextPath}/article/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>文章列表</a></li>
			<li><a href="${pageContext.request.contextPath}/articletype/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>新增分类</a></li>
			<li><a
				href="${pageContext.request.contextPath}/articletype/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>分类列表</a></li>
		</ul></li>




</ul>