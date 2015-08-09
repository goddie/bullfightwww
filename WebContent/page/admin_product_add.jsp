<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增理财产品</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/product/add"
		name="form1" method="post">
  <div>
    <ul>
      <li>产品名:
        <input name="name" type="text" />
      </li>
      <li>7日年化收益率：
        <input name="weekRate" type="text" />%
      </li>
      <li>万份收益：
        <input name="tenThousand" type="text" />
      </li>
      <li>风险评级：
        <input name="risk" type="text" /> <input onClick="risk.value=this.value" value="低风险" type="button" /> <input onClick="risk.value=this.value" value="高风险" type="button" />
      </li>
      <li>起购金额：
        <input name="minBuy" type="text" /> 元
      </li>
      <li>理财期限：
        <input name="timeLimit" type="text" /> <input onClick="timeLimit.value=this.value" value="随买随卖" type="button" />
      </li>
      <li>取现到账：
        <input name="cashTime" type="text" /> <input onClick="cashTime.value=this.value" value="支持10分钟快速到账" type="button" />
      </li>
      <li>购买须知：
        <textarea name="notice" cols="50" rows="3"></textarea>
      </li>
      <li>产品详情：
        <textarea name="info" cols="50" rows="3"></textarea>
      </li>
      <li>
        <input name="" type="submit" value="提交" />
      </li>
    </ul>
  </div>
</form>
</body>
</html>