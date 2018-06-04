<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload</title>
</head>
<body>
<form action="UploadHandleServlet" id="form" enctype="multipart/form-data" method="post">
<input id="id" type="hidden" name="acc_id">

	

        上传文件：<input type="file" name="file"><br/>
     <input type="submit" value="提交" onclick="document.getElementById('id').value = '${account.accountID}'">

</form>
<script src="js/upload.js"></script>
</body>
</html>