<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

<title>${article.title }</title>
<style type="text/css">
body {
	background: #152a3d;
	color: #fff;
	line-height: 2em;
}

img { 
    width:100%;
    max-width: 640px;
    min-width: 320px;
    margin: 0 auto;
    text-align: center;
}

img:parent { text-align: center; } 
</style>
</head>
<body>
<h2>${article.title }</h2>
${body.body }
</body>
</html>