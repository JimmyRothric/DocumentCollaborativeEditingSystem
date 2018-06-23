<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entity.Invitation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Account"%>
<%@page import="dao.InvitationDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>notifications</title>
</head>
<body id="a">
<%@ include file="head.jsp" %>
<script src="js/ajax_invitation.js"></script>
<div style="position: relative; top: 50px">
<div class="container">

	<h3>邀请信息：</h3>
	
	<%
		/*
		InvitationDao idao = new InvitationDao();
		Account acc = (Account)session.getAttribute("account");
		String accid = acc.getAccountID();
		ArrayList<Invitation> recvList = idao.getInvitationofReceiver(accid);
		ArrayList<Invitation> sendList = idao.getInvitationofSender(accid);
		request.setAttribute("recvList", recvList);
		request.setAttribute("sendList", sendList);
		*/
	%>
	<div class="col-md-6">
	<h4>收到的邀请：</h4>
	<table class="table table-hover" id="receiver">	
		<thead>
			<tr align="center">
				<td>发送者id</td>
				<td>接受者id</td>
				<td>文档id</td>
			</tr>
		</thead>
		<tbody>
			<tr id="receiverTr" align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
		<!--<c:forEach var="inv" varStatus="i" items="${requestScope.recvList}">
			<tbody>
				<tr align="center">
					<td>${inv.senderID}</td>
					<td>${inv.receiverID}</td>
					<td>${inv.documentID}</td>
					<td><input type="button"  class="btn btn-success" value="接受" onclick="window.location.href='InvitationServlet?function=accept&sender_id=${inv.senderID}&receiver_id=${inv.receiverID}&document_id=${inv.documentID}'">
					<input type="button"  class="btn btn-danger" value="忽略" onclick="window.location.href='InvitationServlet?function=ignore&sender_id=${inv.senderID}&receiver_id=${inv.receiverID}&document_id=${inv.documentID}'"></td>
				</tr>
			</tbody>
		</c:forEach>-->
	</table>
	
	<h4>发出的邀请：</h4>
	<table class="table table-hover" id="sneder">		
		<thead>
			<tr align="center">
				<td>发送者id</td>
				<td>接受者id</td>
				<td>文档id</td>
			</tr>
		</thead>
		<tbody>
			<tr id="senderTr" align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
		<!--<c:forEach var="inv" varStatus="i" items="${requestScope.sendList}">
			<tbody>
				<tr>
					<td>${inv.senderID}</td>
					<td>${inv.receiverID}</td>
					<td>${inv.documentID}</td>
					<td><input type="button" class="btn btn-danger" value="撤销" onclick="window.location.href='InvitationServlet?function=cancel&sender_id=${inv.senderID}&receiver_id=${inv.receiverID}&document_id=${inv.documentID}'"></td>
				</tr>
			</tbody>
		</c:forEach>-->
	</table>
	</div>
	
</div>
</div>
</body>
</html>