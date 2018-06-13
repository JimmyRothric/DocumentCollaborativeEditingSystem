<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="p" varStatus="i" items="${filePathList}">
	<p>
	<iframe src = ${p }></iframe>
	<c:forEach var="s" varStatus="i2" items="${difAllList[i.index]}">
	${s}
	</c:forEach>
	</p>
</c:forEach>
<p>
<input type = "button" value = "返回" onclick = "javascript:history.back(-1);">
</p>
</body>
</html>