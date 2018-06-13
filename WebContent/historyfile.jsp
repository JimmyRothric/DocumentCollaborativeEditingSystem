<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>History</title>
</head>
<body>
<%@ include file="head.jsp" %>
<div style="margin-top: 5%; margin-left: 5%; margin-right: 5%;">
<div class="container-fluid">
<form action = "DocumentServlet" method = "post">
<input id="id" type="hidden" name="docPath">

	<table class="table table-hover">		
		<thead>
			<tr align="center">
				<td>Title</td>
				<td>Create Date</td>
				<td>Last Modify Date</td>
				<td>Version</td>
				<td></td>
			</tr>
		</thead>
		
		<c:forEach var="doc" varStatus="i" items="${requestScope.hdocList}">
			<tbody>
				<tr align="center">
					<td><p style="margin-top: 10px;"><input type="submit" class="btn btn-link" style="font-size: 20px" onclick="document.getElementById('id').value='${doc.replace()}';" name="showdocBtn" value="${doc.title}"></p></td>
					<td><p style="margin-top: 20px;">${doc.createDate.toLocaleString()}</p></td>
					<td><p style="margin-top: 20px;">${doc.lastModifyDate.toLocaleString()}</p></td>
					<td><p style="margin-top: 20px;">${doc.version}</p></td>
					<td><input type="button" class="btn btn-default" style="margin-top: 15px" value="下载文件" onclick="window.location.href='DownloadHandleServlet?hdocid=${doc.documentID}&index=${i.index}'"></td>
					<td><input type="button" class="btn btn-danger" style="margin-top: 15px" value="回滚" onclick="window.location.href='DocumentServlet?function=rollback&hdocid=${doc.documentID}&version=${doc.version}'"></td>
				</tr>
			</tbody>
		</c:forEach>
		
	</table>
</form>
</div>
</div>
</body>
</html>