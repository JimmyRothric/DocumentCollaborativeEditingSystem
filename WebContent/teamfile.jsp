<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Team File</title>
</head>
<body>
<%@ include file="head.jsp" %>
<div style="margin-top: 5%; margin-left: 5%; margin-right: 5%;">
<div class="container-fluid">
<form action = "DocumentServlet" method = "post">
	<table class="table table-hover">		
		<thead>
			<tr align="center">
				<td>title</td>
				<td>create date</td>
				<td>last modify date</td>
				<td>version</td>
				<td></td>
			</tr>
		</thead>
		
		<c:forEach var="doc" varStatus="doci" items="${requestScope.docList}">
			<!--  <h2>${nameList[doci.index]}:</h2> -->
			<tbody>
				<tr align="center">
					<td><p style="margin-top: 10px"><input type = "submit" class="btn btn-link" style="font-size: 20px" onclick = "document.getElementById('id').value = '${doc.replace()}';" name = "showdocBtn" value = "${doc.title}"></p></td>
					<td><p style="margin-top: 20px">${doc.createDate.toLocaleString()}</p></td>
					<td><p style="margin-top: 20px">${doc.lastModifyDate.toLocaleString()}</p></td>
					<td><p style="margin-top: 20px">${doc.version}</p></td>
					<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="下载文件" onclick="window.location.href='DownloadHandleServlet?docid=${doc.documentID}'"></td>
					<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="上传文件" onclick="window.location.href='upload.jsp?function=upload&docid=${doc.documentID}'"></td>
				</tr>
				<tr>
					<thead>
						<tr align="center">
							<td>contributionID</td>
							<td>accountID</td>
							<td>uploadDate</td>
							<td>state</td>
							<td>操作</td>
						</tr>
					</thead>
					
					<c:forEach var="ctb" varStatus="ctbi" items="${ctbList[doci.index]}">
					<tbody>
		
						<tr align="center">
							<td><p style="margin-top: 10px"><input type = "submit" class="btn btn-link" style="font-size: 20px" onclick = "document.getElementById('id').value = '${ctb.replace()}';" name = "showdocBtn" value = "${ctb.contributionID}"></p></td>
							<td><p style="margin-top: 20px">${ctb.accountID}</p></td>
							<td><p style="margin-top: 20px">${ctb.uploadDate.toLocaleString()}</p></td>
							<td><p style="margin-top: 20px">${ctb.getStateStr()}</p></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="下载文件" onclick="window.location.href='DownloadHandleServlet?ctbid=${ctb.contributionID}'"></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="撤销" onclick="window.location.href='ContributionServlet?function=cancel&ctbid=${ctb.contributionID}'"></td>
						</tr>
					</tbody>
					</c:forEach>
				</tr>
			</tbody>
		</c:forEach>
		
	</table>
</form>
</div>
</div>
</body>
</html>