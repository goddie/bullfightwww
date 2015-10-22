<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table width="100%" border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td>&nbsp;</td>
			<td>产品名</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<c:forEach var="m" varStatus="status" items="${list}">
			<tr>
				<td>${status.index+1}</td>
				<td>${m.name}</td>
				<td>${m.weekRate}</td>
				<td><a
					href="${pageContext.request.contextPath}/productinfo/admin/add?pid=${m.id}&type=1">购买须知</a>
					<a
					href="${pageContext.request.contextPath}/productinfo/admin/add?pid=${m.id}&type=2">产品详情</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>