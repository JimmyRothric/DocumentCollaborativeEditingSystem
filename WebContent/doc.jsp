
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Details</title>
<style>
body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;overflow: hidden;}
</style>
</head>
<body>
<%@ include file="head.jsp" %>
<div style="margin-top: 5%; margin-left: 5%; margin-right: 5%;">
<div class="container-fluid">
	<iframe src="${src.replaceAll('#','\\\\')}" width='100%' height='100%' frameborder='0' name="_blank" id="_blank" >
	</iframe>
	<p>
	<input type = "button" value = "返回" onclick = "javascript:history.back(-1);">
	</p>
</div>
</div>
</body>
</html>