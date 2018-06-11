<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My File</title>

</head>
<%@ include file="head.jsp" %>
<body>

<div style="margin-top: 5%; margin-left: 5%; margin-right: 5%;">
<div class="container-fluid">

	<div style="float: right">
		<button type="submit" class="btn btn-primary col-md-12" onclick="window.location.href='upload.jsp?function=create'"> 
			<span class="glyphicon glyphicon-plus"></span> 创建文件
		</button> 
		<!--<input type="button" class="btn btn-primary" value="创建文件" onclick = "window.location.href='upload.jsp?function=create'">
		<!--<input type ="button" value = "下载文件" onclick = "window.location.href='download.jsp'">-->
	</div>
	
	<form action="DocumentServlet" method="post">
	<input id="id" type="hidden" name="docPath">
	<table class="table table-hover" >		
		<thead>
			<tr align="center">
				<td>Title</td>
				<td>Create Date</td>
				<td>Last Modify Date</td>
				<td>Version</td>
			</tr>
		</thead>
		
		<c:forEach var="doc" varStatus="doci" items="${requestScope.docList}">
			<tbody>
		
				<tr align="center">
				
			
					<!--  <td><a href = "DocumentServlet?showdocBtn=true&docPath=${doc.replace()}">${doc.title}</a></td>
					  	<td><p style="margin-top: 10px"><input type="submit" class="btn btn-link" style="font-size: 20px" onclick="document.getElementById('id').value = '${doc.replace()}';" name="showdocBtn" value="${doc.title}"></p></td>
					-->
					<td><p style="margin-top: 10px"><input type="button" style="font-size: 20px" class="btn btn-link" onclick="window.location.href='DocumentServlet?function=showdoc&docid=${doc.documentID}'" value="${doc.title}"></p></td>
					<td><p style="margin-top: 20px">${doc.createDate.toLocaleString()}</p></td>
					<td><p style="margin-top: 20px">${doc.lastModifyDate.toLocaleString()}</p></td>
					<td><p style="margin-top: 20px">${doc.version}</p></td>
					<!-- 
					<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="管理编辑者" onclick="window.location.href='ContributorServlet?function=show&docid=${doc.documentID}'"></td>
					 -->
					<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="下载文件" onclick="window.location.href='DownloadHandleServlet?docid=${doc.documentID}'"></td>
					<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="更新文件" onclick="window.location.href='upload.jsp?function=update&docid=${doc.documentID}'"></td>
					<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="查看编辑记录" onclick="window.location.href='DocumentServlet?function=showRecord&docid=${doc.documentID}'"></td>
					<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="查看历史文件" onclick="window.location.href='DocumentServlet?function=showHistory&docid=${doc.documentID}'"></td>
				</tr>
				<!--  
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
							<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="下载文件" onclick="window.location.href='DownloadHandleServlet?ctbid=${ctb.contributionID}''"></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="审核通过" onclick="window.location.href='ContributionServlet?function=pass&ctbid=${ctb.contributionID}'"></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="不通过" onclick="window.location.href='ContributionServlet?function=notpass&ctbid=${ctb.contributionID}'"></td>
						</tr>
					</tbody>
					</c:forEach>
					
				</tr>
				-->
			</tbody>
		</c:forEach>
		
	</table>
	</form>
</div>
</div>
</body>
</html>