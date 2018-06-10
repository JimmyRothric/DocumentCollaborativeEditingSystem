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
<form action = "DocumentServlet" method = "post">
<input id="id" type="hidden" name="docPath">
<table>		
		<thead>
			<tr>
				<td>title</td>
				<td>create date</td>
				<td>last modify date</td>
				<td>version</td>
				<td></td>
			</tr>
		</thead>
		
		<c:forEach var="doc" varStatus="i" items="${requestScope.hdocList}">
			<tbody>
				<tr>
					<td><p style="margin-top: 10px;"><input type = "submit" onclick = "document.getElementById('id').value = '${doc.replace()}';" name = "showdocBtn" value = "${doc.title}"></p></td>
					<td><p style="margin-top: 10px;">${doc.createDate.toLocaleString()}</p></td>
					<td><p style="margin-top: 10px;">${doc.lastModifyDate.toLocaleString()}</p></td>
					<td><p style="margin-top: 10px;">${doc.version}</p></td>
					<td><input type="button" value="下载文件" onclick="window.location.href='DownloadHandleServlet?hdocid=${doc.documentID}&index=${i.index}'"></td>
					<td><input type="button" value="回滚" onclick="window.location.href='DocumentServlet?function=rollback&hdocid=${doc.documentID}&version=${doc.version}"></td>
				</tr>
			</tbody>
		</c:forEach>
		
	</table>
</form>
</body>
</html>