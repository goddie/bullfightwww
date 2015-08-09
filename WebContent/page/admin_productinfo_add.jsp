<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增理财产品</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/productinfo/add"
		name="form1" method="post">
		<div>
			<ul>
				<li>产品名: <label title="">${product.name}</label> <input
					name="pid" type="hidden" value="${product.id}" /> <input
					name="type" type="hidden" value="${type}" />
				</li>
				<li>须知标题： <input name="subTitle" type="text" /><input
					onclick="subTitle.value=this.value" name="" value="还款来源"
					type="button" /><input onclick="subTitle.value=this.value" name=""
					value="资金保障" type="button" />
				</li>
				<li>须知内容： <textarea name="subContent" cols="50" rows="3"></textarea>
				</li>
				<li><input name="" type="submit" value="提交" /></li>
			</ul>
		</div>
		<div>
			<ul>
				<c:forEach var="m" varStatus="status" items="${list}">
					<li>
						<h2>${m.subTitle}</h2>
						<div>${m.subContent}</div>
					</li>
				</c:forEach>

			</ul>
		</div>
	</form>
</body>
</html>