<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>球队</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/team/jspadd"><i
					class="glyphicon glyphicon-chevron-right"></i>新增球队</a></li>
			<li><a href="${pageContext.request.contextPath}/team/jsplist"><i
					class="glyphicon glyphicon-chevron-right"></i>管理球队</a></li>
		</ul></li>
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>球员</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/player/jspadd"><i
					class="glyphicon glyphicon-chevron-right"></i>新增球员</a></li>
			<li><a href="${pageContext.request.contextPath}/player/list"><i
					class="glyphicon glyphicon-chevron-right"></i>管理球员</a></li>
			<li><a href="${pageContext.request.contextPath}/record/jspadd"><i
					class="glyphicon glyphicon-chevron-right"></i>新增比赛数据</a></li>
			<li><a href="${pageContext.request.contextPath}/record/jsplist"><i
					class="glyphicon glyphicon-chevron-right"></i>管理球员数据</a></li>
		</ul></li>
</ul>