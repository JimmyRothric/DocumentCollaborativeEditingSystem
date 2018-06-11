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
		<a href="DocumentServlet?function=showMyFile">${account.name}</a>
		</span>
	<span style="font-size: 25px">
		/<a href="DocumentServlet?function=showdoc&docid='${doc.documentID}'">${doc.title}</a>
	</span>
	
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#doc" data-toggle="tab">Doc</a></li>
		<li><a href="#commit" data-toggle="tab">Commits</a></li>
		<li><a href="#collaborators" data-toggle="tab">Collaborators</a></li>
		<li><a href="#settings" data-toggle="tab">Settings</a></li>
	</ul>
	
	<div id="myTabContent" class="tab-content">
		
		<div class="tab-pane fade in active" id="doc">
			<iframe src="${src.replaceAll('#','\\\\')}" width='100%' height='100%' frameborder='0' name="_blank" id="_blank" >
			</iframe>
			<p>
				<input type = "button" value = "返回" onclick = "javascript:history.back(-1);">
			</p>
		</div>
		
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
				<c:if test="${ctb.state eq 'W' and authority eq 'E'}">
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
							<td><input type="button" style="margin-top: 15px" id="changeAuthority" class="btn btn-warning" value="修改权限" onclick="window.location.href='ContributorServlet?function=change&accid=${ctbtr.accountID}&docid=${ctbtr.documentID}'"></td>
							<td><input type="button" style="margin-top: 15px" class="btn btn-danger" value="删除" onclick="window.location.href='ContributorServlet?function=del&accid=${ctbtr.accountID}&docid=${ctbtr.documentID}'"></td>
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
			<div class="col-lg-6" style="float: right; padding-right: 5%; padding-bottom: 5%">
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
</body>
</html>