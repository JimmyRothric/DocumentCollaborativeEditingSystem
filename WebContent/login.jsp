<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">  
<script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>  
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<%
	String logout = request.getParameter("logout");
	if (logout != null && logout.equals("true")){
		//session.removeAttribute("account");
		Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()){
			session.removeAttribute(e.nextElement());
		}
		
	}
%>
<title>Login</title>
</head>
<body style="background-image: url(img/grey-bg.png); background-repeat: repeat-x;">
<!-- navigation bar -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color: #000000;">
<div class="container-fluid"> 
	<!-- LOGO go to main.jsp -->
    <div class="navbar-header" style="padding-left: 3%"> 
        <a class="navbar-brand" href="main.jsp" style="padding-top: 5%">
        	<img src="img/LOGO_S.png" alt="logo"/>
        </a> 
    </div> 
</div>
</nav>

<form action="LoginServlet" method="post">
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
			<div style="padding-top: 25%">
				<!-- user-name & password -->
				<input type="text" class="form-control" name="username" placeholder="请输入账号ID" required autofocus/><br/>
				<input type="password" class="form-control" name="password" placeholder="请输入密码" required autofocus/><br/>
				<label class="form-inline"> 
					<!--  <input type="checkbox"/> 记住我-->
				</label> 
				
				<button type="button" class="btn .btn-default col-md-12" style="height: 40px; width: 270px; font-size: 16px;color: white; margin-left: 0%;" onclick="this.form.action='register.jsp';this.form.submit()">
		   			注册
		   		</button>
		   		<button type="submit" class="btn .btn-default col-md-12" style="height: 40px; width: 270px; font-size: 16px; color: white; background-color: #5aa9e3; margin-left: 0%; margin-top: 5%">
		   			登录
		   		</button>
		   
	   		</div>
		</div>
		<div class="col-md-4">
		</div>
	</div>
</div>
</div>

</form>
</body>
</html>