<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/add" name="form1"
		method="post">
        
        用户名: <input name="username" type="text"> <br/>
        昵称: <input name="nickname" type="text"> <br/>
        密码: <input name="password" type="password"> <br/>
        简介：<textarea name="info" cols="30" rows="3"></textarea><br/>
        累计收益率： <input name="totalRate" type="text"> <br/>
        跟买人数： <input name="followBuy" type="text"> <br/>
        预计年收益率：<input name="yearRate" type="text"> <br/>
        
        <input name="" type="submit" value="提交" />

</form>
</body>
</html>