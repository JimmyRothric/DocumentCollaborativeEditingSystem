<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editor Management</title>
</head>
<body>
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
					<td><p style="margin-top: 10px;">${ctbtr.authority}</p></td>
					<td><input type="button" value="修改权限" onclick="window.location.href='ContributorServlet?function=change&accid=${ctbtr.accountID}&docid=${ctbtr.documentID}'"></td>
					<td><input type="button" value="删除" onclick="window.location.href='ContributorServlet?function=del&accid=${ctbtr.accountID}&docid=${ctbtr.documentID}'"></td>
				</tr>
			</tbody>
		</c:forEach>
		
	</table>
</body>
</html>