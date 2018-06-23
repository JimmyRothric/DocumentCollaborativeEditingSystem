<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>profile</title>
</head>
<body>
<script src="js/checkValidate.js"></script>
<%@ include file="head.jsp" %>
<div style="margin-top: 5%; margin-left: 5%; margin-right: 5%;">
<div class="container-fluid">
	<p>
		<h3>个人资料：</h3>
		账号：${account.accountID}<br/>
		姓名：${account.name}<br/>
		邮箱：${account.email}<br/>
		<br/>
		重置密码：<br/>
		<div class="col-md-4">
			<form action="MyInfoServlet?function=updatePassword" method="post" onsubmit="return isValidPassword();"> 
				<input type="password" class="form-control col-md-4" id="password0" name="password0" placeholder="请输入旧密码" style="margin-top: 3%" required autofocus/><br/>
				<input type="password" class="form-control col-md-4" id="password1" name="password1" placeholder="请输入新密码" style="margin-top: 3%" required autofocus/><br/>
				<input type="password" class="form-control col-md-4" id="password2" name="password2" placeholder="请确认密码" style="margin-top: 3%" required autofocus/><br/>
				<input type="submit" class="btn btn-primary col-md-12" style="margin-top: 3%" value="修改 "/>
			</form>
		</div>
		
		
		
	</p>
</div>
</div>
</body>
</html>