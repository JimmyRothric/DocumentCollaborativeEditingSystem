<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>profile</title>
</head>
<body>
<%@ include file="head.jsp" %>
<div style="margin-top: 5%; margin-left: 5%; margin-right: 5%;">
<div class="container-fluid">
	<p>
		<h3>个人资料：</h3>
		账号：${account.accountID}<br/>
		姓名：${account.name}<br/>
		邮箱：${account.email}<br/>
	</p>
</div>
</div>
</body>
</html>