<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在此处插入标题</title>

</head>
<body>
<form action = "DocumentServlet" method = "post">
<input id="id" type="hidden" name="docPath">
	<input type ="button" value = "创建文件" onclick = "window.location.href='upload.jsp?function=create'">
	<!--<input type ="button" value = "下载文件" onclick = "window.location.href='download.jsp'">-->
	
	<table class="table table-hover">		
		<thead>
			<tr>
				<td>title</td>
				<td>create date</td>
				<td>last modify date</td>
				<td>version</td>
				<td></td>
			</tr>
		</thead>
		
		<c:forEach var="doc" varStatus="i" items="${requestScope.docList}">
			<tbody>
		
				<tr>
				
			
					<td><p style="margin-top: 10px;"><input type = "submit" onclick = "document.getElementById('id').value = '${doc.replace()}';" name = "showdocBtn" value = "${doc.title}"></p></td>
					<td><p style="margin-top: 10px;">${doc.createDate}</p></td>
					<td><p style="margin-top: 10px;">${doc.lastModifyDate}</p></td>
					<td><p style="margin-top: 10px;">${doc.version}</p></td>
					<td><input type="button" value="下载文件" onclick="window.location.href='DownloadHandleServlet?docid=${doc.documentID}'"></td>
					<td><input type="button" value="更新文件" onclick="window.location.href='upload.jsp?function=update&docid=${doc.documentID}'"></td>
				</tr>
			</tbody>
		</c:forEach>
		
	</table>
</form>
</body>
</html>