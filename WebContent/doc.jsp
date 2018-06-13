<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

	<span style="font-size: 20px">
		<a href="DocumentServlet?function=showMyFile&accid=${owner}">${owner}</a>
		</span>
	<span style="font-size: 25px">
		/<a href="DocumentServlet?function=showdoc&docid=${doc.documentID}">${doc.title}</a>
	</span>
	
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#doc" data-toggle="tab">Doc</a></li>
		<li><a href="#commit" data-toggle="tab">Commits</a></li>
		<li><a href="#collaborators" data-toggle="tab">Collaborators</a></li>
		<li><a href="#settings" data-toggle="tab">Settings</a></li>
	</ul>
	
	<div id="myTabContent" class="tab-content">
	
		<!-- DOC -->
		<div class="tab-pane fade in active" id="doc">
			<div class="row" style="float: right; padding-right: 3%">
				<input type="button" style="margin-top: 15px" class="btn btn-default" value="下载文件" onclick="window.location.href='DownloadHandleServlet?docid=${doc.documentID}'">
				<button type="submit" style="margin-top: 15px" class="btn btn-default" data-toggle="modal" onclick="$('#update').modal('show');">  
					 更新文件
				</button>
				<!-- 
					<input type="button" style="margin-top: 15px" class="btn btn-default" value="更新文件" onclick="window.location.href='upload.jsp?function=update&docid=${doc.documentID}'">
				 -->
				<input type="button" style="margin-top: 15px" class="btn btn-default" value="编辑记录" onclick="window.location.href='DocumentServlet?function=showRecord&docid=${doc.documentID}'">
				<input type="button" style="margin-top: 15px" class="btn btn-default" value="历史文件" onclick="window.location.href='DocumentServlet?function=showHistory&docid=${doc.documentID}'">
			</div>
			 
			<!-- modal -->
			<div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop='static'>
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title">上传文件</h4>
		            </div>
		            
		            <form id="update_form" action="UploadHandleServlet" enctype="multipart/form-data" method="post">
		           
					<!-- body -->
		            <div class="modal-body">
			            <input id="update_file" name="file" type="file" style="display: none">
						<div class="input-group form-group">
						   	<input id="update_path" class="form-control content" type="text">
						   	<span class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('input[id=update_file]').click();">Browse</button>
							</span>
						</div>
					</div>
					<!-- /body -->
					
		            <div class="modal-footer">
		                <button type="button" class="btn btn-primary" onclick="update();">提交</button>
		            </div>
		            </form>
		            
		        </div>
		    </div>
		    </div><!-- /modal -->
			
			<div style="padding-top: 3%">
				<iframe src="${src.replaceAll('#','\\\\')}" width='100%' height='100%' frameborder='0' name="_blank" id="_blank" >
				</iframe>
			</div>
		</div>
		<!-- /DOC -->
		
		<!-- COMMIT -->
		<div class="tab-pane fade" id="commit">
			<table class="table table-hover" >	
				<thead>
					<tr align="center">
						<td>ID</td>
						<td>Contributor</td>
						<td>Date</td>
						<td>State</td>
						<td></td>
					</tr>
				</thead>
				
				<c:forEach var="ctb" items="${requestScope.contributionList}">
				<c:if test="${ctb.state eq 'W' and authority eq 'P'}">
					<tbody>
						<tr align="center">
							<!-- 
								<td><p style="margin-top: 10px"><input type = "submit" class="btn btn-link" style="font-size: 20px" onclick = "document.getElementById('id').value = '${ctb.replace()}';" name = "showdocBtn" value = "${ctb.contributionID}"></p></td>
							 -->
							<td><p style="margin-top: 20px">${ctb.contributionID}</p></td>
							<td><p style="margin-top: 20px">${ctb.accountID}</p></td>
							<td><p style="margin-top: 20px">${ctb.uploadDate.toLocaleString()}</p></td>
							<td><p style="margin-top: 20px">${ctb.getStateStr()}</p></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="下载文件" onclick="window.location.href='DownloadHandleServlet?ctbid=${ctb.contributionID}''"></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-success" value="Accept" onclick="window.location.href='ContributionServlet?function=pass&ctbid=${ctb.contributionID}'"></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-danger" value="Reject" onclick="window.location.href='ContributionServlet?function=notpass&ctbid=${ctb.contributionID}'"></td>
						</tr>
					</tbody>
				</c:if>
				<c:if test="${ctb.state eq 'A' or authority eq 'E'}">
					<tbody>
						<tr align="center">
							<td><p style="margin-top: 20px">${ctb.contributionID}</p></td>
							<td><p style="margin-top: 20px">${ctb.accountID}</p></td>
							<td><p style="margin-top: 20px">${ctb.uploadDate.toLocaleString()}</p></td>
							<td><p style="margin-top: 20px">${ctb.getStateStr()}</p></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="下载文件" onclick="window.location.href='DownloadHandleServlet?ctbid=${ctb.contributionID}''"></td>
							<td><button style="visibility: hidden"></button></td>
							<td><button style="visibility: hidden"></button></td>
						</tr>
					</tbody>
				</c:if>
				<c:if test="${ctb.state eq 'W' and authority eq 'E' and ctb.accountID eq account.accountID}">
				<tbody>
						<tr align="center">
							<td><p style="margin-top: 20px">${ctb.contributionID}</p></td>
							<td><p style="margin-top: 20px">${ctb.accountID}</p></td>
							<td><p style="margin-top: 20px">${ctb.uploadDate.toLocaleString()}</p></td>
							<td><p style="margin-top: 20px">${ctb.getStateStr()}</p></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-default" value="下载文件" onclick="window.location.href='DownloadHandleServlet?ctbid=${ctb.contributionID}''"></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-danger" value="撤销" onclick="window.location.href='ContributionServlet?function=cancel&ctbid=${ctb.contributionID}'"></td>
							<td><button style="visibility: hidden"></button></td>
						</tr>
					</tbody>
				</c:if>
				</c:forEach>
			</table>
		</div>
		<!-- /COMMIT -->
		
		<!-- Collaborators -->
		<div class="tab-pane fade" id="collaborators">
			<table class="table table-hover" >	
				<thead>
					<tr align="center">
						<td>ID</td>
						<td>Authority</td>
					</tr>
				</thead>
				
				<c:forEach var="contributor" varStatus="i" items="${requestScope.contributorList}">
				<c:if test="${authority eq 'P'}">
					<tbody>
						<tr align="center">
							<td><p style="margin-top: 20px">${contributor.accountID}</p></td>
							<td><p style="margin-top: 20px">${contributor.getAuthorityStr()}</p></td>
							<td><input type="button" style="margin-top: 15px" id="changeAuthority" class="btn btn-warning" value="修改权限" onclick="window.location.href='ContributorServlet?function=change&accid=${contributor.accountID}&docid=${contributor.documentID}'"></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-danger" value="删除" onclick="window.location.href='ContributorServlet?function=del&accid=${contributor.accountID}&docid=${contributor.documentID}'"></td>
						</tr>
					</tbody>
				</c:if>
				<c:if test="${authority ne 'P'}">
					<tbody>
						<tr align="center">
							<td><p style="margin-top: 20px">${contributor.accountID}</p></td>
							<td><p style="margin-top: 20px">${contributor.getAuthorityStr()}</p></td>
							<td><button style="visibility: hidden"></button></td>
							<td><button style="visibility: hidden"></button></td>
						</tr>
					</tbody>
				</c:if>
				</c:forEach>
			</table>
			<!-- Invite -->
			<c:if test="${authority eq 'P'}">
			<div class="col-lg-6" style="float: right; padding-right: 5%; margin-top: 10%">
				<form action="InvitationServlet" method = "post">
					<input type="hidden" name="doc_id" value="${doc.documentID}">
					<div class="input-group form-group">
						<input type="text" class="form-control content" name="recv_id">
						<span class="input-group-btn">
							<input type="submit" class="btn btn-primary" name="invBtn" value="Invite">
						</span>
					</div>
				</form>
			</div>
			</c:if>
			<!-- /Invite -->
		</div>
		<!-- /Collaborators -->
		
		
		<div class="tab-pane fade" id="settings">
			
		</div>
	
	</div>
	
	
		

</div>
</div>

<script>
	$('input[id=update_file]').change(function() {
		$('#update_path').val($(this).val().substring(12));
	});

	function update() {
		var url = "UploadHandleServlet?function=update&docid=" + ${doc.documentID};
		document.getElementById("update_form").action = url;
		document.getElementById("update_form").submit(); 
	}
</script>

</body>
</html>