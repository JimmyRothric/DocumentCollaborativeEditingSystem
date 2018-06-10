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
		
		<c:forEach var="doc" varStatus="doci" items="${requestScope.docList}">
			<tbody>
				<tr>
					<td><p style="margin-top: 10px;"><input type = "submit" onclick = "document.getElementById('id').value = '${doc.replace()}';" name = "showdocBtn" value = "${doc.title}"></p></td>
					<td><p style="margin-top: 10px;">${doc.createDate}</p></td>
					<td><p style="margin-top: 10px;">${doc.lastModifyDate}</p></td>
					<td><p style="margin-top: 10px;">${doc.version}</p></td>
					<td><input type="button" value="下载文件" onclick="window.location.href='DownloadHandleServlet?docid=${doc.documentID}'"></td>
					<td><input type="button" value="上传文件" onclick="window.location.href='upload.jsp?function=upload&docid=${doc.documentID}'"></td>
				</tr>
				<tr>
					<thead>
						<tr>
							<td>contributionID</td>
							<td>accountID</td>
							<td>uploadDate</td>
							<td>state</td>
							<td>操作</td>
						</tr>
					</thead>
					
					<c:forEach var="ctb" varStatus="ctbi" items="${ctbList[doci.index]}">
					<tbody>
		
						<tr>
							<td><p style="margin-top: 10px;"><input type = "submit" onclick = "document.getElementById('id').value = '${ctb.replace()}';" name = "showdocBtn" value = "${ctb.contributionID}"></p></td>
							<td><p style="margin-top: 10px;">${ctb.accountID}</p></td>
							<td><p style="margin-top: 10px;">${ctb.uploadDate.toLocaleString()}</p></td>
							<td><p style="margin-top: 10px;">${ctb.getStateStr()}</p></td>
							<td><input type="button" value="下载文件" onclick="window.location.href='DownloadHandleServlet?ctbid=${ctb.contributionID}'"></td>
							<td><input type="button" value="撤销" onclick="window.location.href='ContributionServlet?function=cancel&ctbid=${ctb.contributionID}'"></td>
						</tr>
					</tbody>
					</c:forEach>
				</tr>
			</tbody>
		</c:forEach>
		
	</table>
</form>
</body>
</html>