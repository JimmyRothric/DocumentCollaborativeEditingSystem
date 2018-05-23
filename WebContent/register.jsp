<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/element_style.css" />
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<title>Register</title>
</head>
<body style="background-image: url(img/grey-bg.png); background-repeat: repeat-x;">
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color: #000000;"> 
<div class="container-fluid"> 
    <div class="navbar-header" style="padding-left: 3%"> 
        <a class="navbar-brand" href="main.jsp" style="padding-top: 5%">
        	<img src="img/LOGO_S.png" alt="logo"/>
        </a> 
    </div> 
    <div>
   		<form class="navbar-form navbar-right">
			<button type="button" class="btn btn-default btn-sm" onclick="window.location.href='login.jsp'">
				<span class="glyphicon glyphicon-log-in"></span> 登录
			</button>
		</form>
	</div>
</div>
</nav>
<form action="RegisterServlet" method="post"  onsubmit="return isValid();">

<div style="position: relative; top: 50px">
<div class="container">
	<div class="row" style="padding-left: 6%">
		<div class="col-md-4">
		</div>
		<div class="col-md-3" style="height: 475px; width: 300px; background-image: url('img/login.png');">
			<div style="text-align: center; padding-top: 3%;">
				<!-- LOGO -->
				<img src="img/LOGO_M.png" alt="logo" class="img-circle"/>
			</div>
			<div style="padding-top: 10%">
				<input type="text" class="form-control" id="accountid" name="accountid" placeholder="账号ID" required autofocus/><br/>
				<input type="text" class="form-control" id="email" name="email" placeholder="邮箱" required autofocus/><br/>
				<input type="text" class="form-control" id="name" name="name" placeholder="姓名" required autofocus/><br/>
				<input type="password" class="form-control" id="password0" name="password0" placeholder="密码" required autofocus/><br/>
				<input type="password" class="form-control" id="password1" name="password1" placeholder="确认密码" required autofocus/><br/>
				<button type="submit" class="btn .btn-default col-md-12" style="height: 40px; width: 270px; font-size: 16px; color: white; background-color: #5aa9e3; margin-left: 0%" >
		   			注册
		   		</button>
	   		</div>
		</div>
		<div class="col-md-4">
		</div>
	</div>
</div>
</div>

<script src="js/register_isValid.js"></script>

</form>
</body>
</html>