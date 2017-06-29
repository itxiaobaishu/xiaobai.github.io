<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>超市管理</h1>
<form action="shopAddSuccess" method="post">
超市名称：<input type = "text" name = "shop.name"><br>
超市负责人：<input type = "text" name = "shop.people"><br>
超市简介：<textarea rows="2" cols="15" name="shop.intro" ></textarea><br>
超市成立时间：<input type = "text" name = "shop.time"> *格式为：2000-01-01<br>
<button>添加超市</button>
</form>
</body>

</html>