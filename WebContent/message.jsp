<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">  
<script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>  
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Message!</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color: #000000;">
<div class="container-fluid"> 
	<!-- LOGO go to main.jsp -->
    <div class="navbar-header" style="padding-left: 3%"> 
        <a class="navbar-brand" href="#" style="padding-top: 5%">
        	<img src="img/LOGO_S.png" alt="logo"/>
        </a> 
    </div> 
</div>
</nav>

<div style="margin-top: 80px; margin-left: 30px">
<h1>${message}</h1>
<input type="button" class="btn btn-default" value="返回" onclick="window.location.href='${loc}'">
</div>
</body>
</html>