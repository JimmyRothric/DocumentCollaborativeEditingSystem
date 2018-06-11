<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="entity.Invitation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Account"%>
<%@page import="dao.InvitationDao"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOME</title>
</head>
<body>
<%@ include file="head.jsp" %>
<div style="position: relative; top: 50px">
<div class="container">
	<!-- 
	<a href="home.jsp">主页 </a>
	<a href="DocumentServlet?function=showMyFile">我的文件</a>
	<a href="DocumentServlet?function=teamFile">协同编辑文件</a>
 	-->
	<p>
	<h3>个人资料：</h3>
	账号：${account.accountID}<br/>
	姓名：${account.name}<br/>
	邮箱：${account.email}<br/>
	</p>
	<p>
	<h3>邀请信息：</h3>
	
	<%
		InvitationDao idao = new InvitationDao();
		Account acc = (Account)session.getAttribute("account");
		String accid = acc.getAccountID();
		ArrayList<Invitation> recvList = idao.getInvitationofReceiver(accid);
		ArrayList<Invitation> sendList = idao.getInvitationofSender(accid);
		request.setAttribute("recvList", recvList);
		request.setAttribute("sendList", sendList);
	%>
	<h4>收到的邀请：</h4>
		<table>		
		<thead>
			<tr>
				<td>发送者id</td>
				<td>接受者id</td>
				<td>文档id</td>
				<td>操作</td>
			</tr>
		</thead>
		<c:forEach var="inv" varStatus="i" items="${requestScope.recvList}">
			<tbody>
				<tr>
					<td>${inv.senderID}</td>
					<td>${inv.receiverID}</td>
					<td>${inv.documentID}</td>
					<td><input type="button"  class="btn btn-success" value="接受" onclick="window.location.href='InvitationServlet?function=accept&sender_id=${inv.senderID}&receiver_id=${inv.receiverID}&document_id=${inv.documentID}'">
					<input type="button"  class="btn btn-danger" value="忽略" onclick="window.location.href='InvitationServlet?function=ignore&sender_id=${inv.senderID}&receiver_id=${inv.receiverID}&document_id=${inv.documentID}'"></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<h4>发出的等待对方接受的邀请：</h4>
	<table>		
		<thead>
			<tr>
				<td>发送者id</td>
				<td>接受者id</td>
				<td>文档id</td>
				<td>操作</td>
			</tr>
		</thead>
		<c:forEach var="inv" varStatus="i" items="${requestScope.sendList}">
			<tbody>
				<tr>
					<td>${inv.senderID}</td>
					<td>${inv.receiverID}</td>
					<td>${inv.documentID}</td>
					<td><input type="button" class="btn btn-danger" value="撤销" onclick="window.location.href='InvitationServlet?function=cancel&sender_id=${inv.senderID}&receiver_id=${inv.receiverID}&document_id=${inv.documentID}'"></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	
	

</div>
</div>
</body>
</html>