<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>用户管理</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/user/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>新增球员</a></li>
			<li><a href="${pageContext.request.contextPath}/user/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>球员管理</a></li>

			<li><a
				href="${pageContext.request.contextPath}/user/admin/recordlist?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>球员数据</a></li>
		</ul></li>


</ul>