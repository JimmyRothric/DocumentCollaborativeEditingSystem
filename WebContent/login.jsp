<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在此处插入标题</title>
</head>
<body>
<form action = "LoginServlet" method = "post">
	用户名：<input type="text" name="username" /><br/>
	密码：<input type="password"  name="password" /><br/>
	<input type ="submit" value = "登录" >
	<input type ="button" value = "注册" onclick = "window.location.href='register.jsp'">
</form>
</body>
</html>