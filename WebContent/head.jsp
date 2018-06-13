<%@page import="entity.Account"%>
<%@page import="dao.InvitationDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 导航栏 -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color: #000000;"> 
<div class="container-fluid"> 
    <div class="navbar-header" style="padding-left: 3%"> 
        <a class="navbar-brand" href="home.jsp" style="padding-top: 5%">
        	<img src="img/LOGO_S.png" alt="logo"/>
        </a> 
    </div> 
    <div>
    
    <ul class="nav navbar-nav navbar-left">
		<li><a href="DocumentServlet?function=showMyFile" style="color: white">我的文件</a></li> 
		<li><a href="DocumentServlet?function=teamFile" style="color: white">协同编辑文件</a></li>
	</ul>
	
    <form class="navbar-form navbar-right">
    
    <!-- 用户未登录 -->
	<c:if test = "${account.accountID eq null}">
		<div class="btn-group">
			<button type="button" class="btn btn-default btn-sm" onclick="window.location.href='login.jsp'">
				<span class="glyphicon glyphicon-log-in"></span> 登录
			</button>
			<button type="button" class="btn btn-default btn-sm" onclick="window.location.href='register.jsp'">
				<span class="glyphicon glyphicon-user"></span> 注册
			</button>
		</div>
	</c:if>
	<!--  -->
	
	<!-- 用户已登录 -->
	<c:if test = "${account.accountID ne null}">
		<!--<a href="notifications.jsp">-->
		<button type="button" class="btn btn-link btn-sm" style="background-color: #000000; text-decoration: none" onclick="window.location.href='notifications.jsp'">
			<span class="glyphicon glyphicon-bell" style="color: #aaaaaa"></span>
			<span class="badge" style="background-color: #aaaaaa">
			<%
				InvitationDao idao = new InvitationDao();
				Account acc = (Account)session.getAttribute("account");
				int cnt = idao.getCountInvitationofReceiver(acc.getAccountID());
				out.print(cnt);
			%>
			</span>
        </button> 
       <!-- </a>-->
        
		<div id="head" class="btn-group">
		<!-- 个人主页 -->
			<button type="button" class="btn btn-default btn-sm" onclick="window.location.href='profile.jsp'">
		  		<span class="glyphicon glyphicon-user"></span> ${account.name}
			</button>
			<button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='login.jsp?logout=true'">
				注销
			</button>
		</div>
	</c:if>
	<!--  -->
	</form>
    </div>
</div>
</nav>