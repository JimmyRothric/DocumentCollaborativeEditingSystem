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
<body>
<%@ include file="head.jsp" %>

<div style="margin-top: 5%; margin-left: 5%; margin-right: 5%;">
<div class="container-fluid">

	<div style="float: right">
		<button type="submit" class="btn btn-primary col-md-12" data-toggle="modal" onclick="$('#create').modal('show');">  
			<span class="glyphicon glyphicon-plus"></span> 创建文件
		</button>
		 
		<!-- modal -->
		<div class="modal fade" id="create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop='static'>
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title">上传文件</h4>
	            </div>
	            
	            <form id="create_form" action="UploadHandleServlet" enctype="multipart/form-data" method="post">
	            <!-- body -->
	            <div class="modal-body">
		            <input id="file" name="file" type="file" style="display: none">
					<div class="input-group form-group">
					   	<input id="path" class="form-control content" type="text">
					   	<span class="input-group-btn">
							<button type="button" class="btn btn-default" onclick="$('input[id=file]').click();">Browse</button>
						</span>
					</div>
				</div>
				<!-- /body -->
				
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" onclick="create();">提交</button>
	            </div>
	            </form>
	            
	        </div>
	    </div>
	    </div>
	    <!-- /modal -->
	    
	</div>
	
	<form action="DocumentServlet" method="post">
	<input id="id" type="hidden" name="docPath">
	<table class="table table-hover">		
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
					<td><p style="margin-top: 10px"><input type="button" style="font-size: 20px" class="btn btn-link" onclick="window.location.href='DocumentServlet?function=showdoc&docid=${doc.documentID}'" value="${doc.title}"></p></td>
					<td><p style="margin-top: 20px">${doc.createDate.toLocaleString()}</p></td>
					<td><p style="margin-top: 20px">${doc.lastModifyDate.toLocaleString()}</p></td>
					<td><p style="margin-top: 20px">${doc.version}</p></td>
					<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="下载文件" onclick="window.location.href='DownloadHandleServlet?docid=${doc.documentID}'"></td>
				</tr>
			</tbody>
		</c:forEach>
		
	</table>
	</form>
</div>
</div>

<script>
	$('input[id=file]').change(function() {
		$('#path').val($(this).val().substring(12));
	});

	function create() {
		var url = "UploadHandleServlet?function=create";
		document.getElementById("create_form").action = url;
		document.getElementById("create_form").submit(); 
	}
</script>
</body>
</html>