<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOME</title>
</head>
<body>
<%@ include file="head.jsp" %>
<div style="position: relative; top: 50px">
<div class="container">
	
	<a href="home.jsp">主页 </a>
	<a href="DocumentServlet?function=showMyFile">我的文件</a>
	<a href="DocumentServlet?function=teamFile">协同编辑文件</a>
	
	<p>
	账号：${account.accountID}<br/>
	姓名：${account.name}<br/>
	邮箱：${account.email}<br/>
	</p>
	

</div>
</div>
</body>
</html>