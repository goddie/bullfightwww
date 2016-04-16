<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>账户信息</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/team/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>新增球队</a></li>
			<li><a
				href="${pageContext.request.contextPath}/team/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>球队管理</a></li>
			<li><a
				href="${pageContext.request.contextPath}/team/admin/recordlist?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>球队数据</a></li>
					<li><a
				href="${pageContext.request.contextPath}/team/admin/count"><i
					class="glyphicon glyphicon-chevron-right"></i>统计工具</a></li>
		</ul></li>




</ul>