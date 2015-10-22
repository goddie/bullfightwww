<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>账户信息</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/matchfight/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>新建比赛</a></li>
			<li><a href="${pageContext.request.contextPath}/matchfight/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>未开始比赛</a></li>
				<li><a href="${pageContext.request.contextPath}/matchfight/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>已结束比赛</a></li>	
		</ul></li>




</ul>