<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="entity.Invitation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Account"%>
<%@page import="dao.InvitationDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOME</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrapValidator.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrapValidator.js">
    </script>
    <link href="css/cover.css" rel="stylesheet">
      <script type="text/javascript" src="js/register_isValid.js" charset="UTF-8"></script>
</head>


<body>

<section id = "home">
    <div class="site-wrapper">
      <div class="site-wrapper-inner">
        <div class="cover-container">
          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">
              DCES
              </h3>            
 			
	<!-- 
	<a href="home.jsp">主页 </a>
	<a href="DocumentServlet?function=showMyFile">我的文件</a>
	<a href="DocumentServlet?function=teamFile">协同编辑文件</a>
 	
	<p>
	<h3>个人资料：</h3>
	账号：${account.accountID}<br/>
	姓名：${account.name}<br/>
	邮箱：${account.email}<br/>
	</p>
<<<<<<< HEAD

	

	-->
	<!--  

=======
	-->
	<!--  
>>>>>>> origin/master
	<p>
	<h3>邀请信息：</h3>
	
	<%
		
		/*
		Account acc = (Account)session.getAttribute("account");
		InvitationDao idao = new InvitationDao();
		String accid = acc.getAccountID();
		ArrayList<Invitation> recvList = idao.getInvitationofReceiver(accid);
		ArrayList<Invitation> sendList = idao.getInvitationofSender(accid);
		request.setAttribute("recvList", recvList);
		request.setAttribute("sendList", sendList);

		InvitationDao idao = new InvitationDao();
		Account acc = (Account)session.getAttribute("account");
		if (acc != null){
			String accid = acc.getAccountID();
			ArrayList<Invitation> recvList = idao.getInvitationofReceiver(accid);
			ArrayList<Invitation> sendList = idao.getInvitationofSender(accid);
			request.setAttribute("recvList", recvList);
			request.setAttribute("sendList", sendList);
		}
		*/

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

	

	</table>
	
	<nav>
                <ul class="nav masthead-nav">

	</table> -->
	<nav>
                <ul class="nav masthead-nav">

                  <li class="active"><a href="#">首页</a></li>
                  <li><a href="DocumentServlet?function=showMyFile">我的文件</a></li>
                  <li><a href="DocumentServlet?function=teamFile">协作文件</a></li>
                  <c:if test = "${account != null }">
                  <li><a href ="profile.jsp">我的信息</a></li>
                  <li><a href ="LoginServlet?logout=true">注销</a></li>
                  </c:if>
                </ul>
    </nav>
        </div>
          </div>
        <section id="dbs">
          <div class="inner cover">
            <h1 class="cover-heading">DCES，你的云端Office</h1>
            <p class="lead">实时协作、轻松分享<br>查看历史、不怕丢失<br>共享文件、权限管理<br>
              现在开始你的协作办公之旅吧！
            </p>
            <c:if test = "${account == null}">
            <p class="lead">
              <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#login">
                          登录
              </button>
               <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#register">
                          注册
              </button>
              
            </p>
            </c:if>
          </div>
        </section>
        </div>

      </div>
    </div>
    </section>

<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title"  id="myModalLabel">
          登录
        </h4>
      </div>
      <div class="modal-body">

  <form  action="LoginServlet" class="form-horizontal" role="form" method="POST">

  <div class="form-group">
    <label for="" class="col-sm-2 control-label">ID</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id=""  name="username"
           placeholder="请输入ID">
    </div>
  </div>

  <div class="form-group">
    <label for="" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="" name="password"
           placeholder="请输入密码">
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button id="login" type="submit" class="btn btn-primary" onclick = "this.form.submit();">
        登录
      </button>
    </div>
  </div>
 </form>
 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
        </button>
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>
  
 <div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title" id="myModalLabel">
         注册
        </h4>
      </div>
      <div class="modal-body">

  <form  action="RegisterServlet" class="form-horizontal" role="form" method="POST">

  <div class="form-group">
    <label for="" class="col-sm-2 control-label">ID</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id=""  name="accountid"
           placeholder="请输入ID">
    </div>
  </div>

  <div class="form-group">
    <label for="" class="col-sm-2 control-label">邮箱</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="" name="email"
           placeholder="请输入邮箱">
    </div>
  </div>
  <div class="form-group">
    <label for="" class="col-sm-2 control-label">姓名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="" name="name"
           placeholder="请输入姓名">
    </div>
  </div>
  
  <div class="form-group">
    <label for="" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="" name="password0"
           placeholder="请输入密码">
    </div>
  </div>
  
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button id="register" type="submit" class="btn btn-primary" onclick = "this.form.submit();">
        注册
      </button>
    </div>
  </div>
 </form>
 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
        </button>
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div> 
  </body>
</html>