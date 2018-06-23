<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editor Management</title>




											<!-- DISCARD -->




</head>
<body>
<form action = "InvitationServlet" method = "post">
<p>
<h3>添加编辑者：（输入对方账号id,发送邀请，对方接受后会自动加入小组）</h3>
<input type = "hidden" name = "doc_id" value = "${ctbtrList[0].documentID}">
被邀请者id：<input type = "text" name = "recv_id"><input type = "submit" name = "invBtn" value = "发送邀请">
</p>
</form>

<table>		
		<thead>
			<tr>
				<td>account_id</td>
				<td>authority</td>
				<td></td>
			</tr>
		</thead>
		
		<c:forEach var="ctbtr" varStatus="i" items="${requestScope.ctbtrList}">
			<tbody>
		
				<tr>
				
			
			
					<td><p style="margin-top: 10px;">${ctbtr.accountID}</p></td>
					<td><p style="margin-top: 10px;">${ctbtr.getAuthorityStr()}</p></td>
					<td><input type="button" value="修改权限" onclick="window.location.href='ContributorServlet?function=change&accid=${ctbtr.accountID}&docid=${ctbtr.documentID}'"></td>
					<td><input type="button" value="删除" onclick="window.location.href='ContributorServlet?function=del&accid=${ctbtr.accountID}&docid=${ctbtr.documentID}'"></td>
				</tr>
			</tbody>
		</c:forEach>
		
	</table>
</body>
</html>