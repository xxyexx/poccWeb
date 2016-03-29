<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>计算机组成原理实验后台管理</title>
<link rel="icon" href="res/images/icon.png">
<script src="res/js/memberJS/createmember.js"></script>
<!-- Bootstrap -->
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css" href="res/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script src="res/js/jquery.min.js" type="text/javascript"></script>
<script src="res/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="../../assets/js/ie8-responsive-file-warning.js"></script>
  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- Bootstrap..-->
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="华南师范大学,实验平台,计算机组成原理">
<meta http-equiv="description" content="计算机组成原理实验平台">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
html,body{height:100%;}
body {
	background-color: #efefef;
	min-width:500px;
	display: block;
}
.container-fluid{
	padding-top:56px;
	height:100% !important;
}
.row{height:100% !important;}
.right-div{
	height:500px !important;
}
.right-div .title{
	width:100% !important;
	height:45px;
	background-color: white;
	padding-top:10px;
	padding-left:30px;
	margin-top:3px;
}
.title a{
	font-size:20px;
	color: #506470;
	text-decoration: none;
}
.title span{
	display: block;
	float:left;
}
.main{
	margin-top:20px;
	padding:10px;
	min-height:450px;
	background-color: white;
	max-height:500px;
	overflow: auto;
}
</style>
<body>
<!--导入头部导航条-->
<%@include file="/html/memberJSP/common/header.jsp" %>

<div class="container-fluid">
<div class="row">
	<!--导入左边导航条-->
	<%@include file="/html/memberJSP/common/left.jsp"%>
	<input type="hidden" id="leftflag" value="系统管理" >
	<!-- 右边内容区域 -->
	<div class="col-md-10 col-sm-8 right-div">
	    <div class="title">
		    <a><span class="glyphicon glyphicon-blackboard"></span>
		       <span>创建系统内部用户</span>
		    </a>
	    </div>
		
		<form id="tform" method="post">
		<input type="hidden" name="id" id="mid">
		<table class="table table-striped table-responsive table-condensed" style="overflow:inherit;">
	    		<thead>
	    	 		<tr>
	    	 			<th>登录账号</th>
	    	 			<th>姓名</th>
	    	 			<th>管理员类型</th>
	    	 			<th>电话</th>
	    	 			<th>电子邮箱</th>
	    	 		</tr>
	    	 	</thead>
	    	 	<tbody>	  
	    	 		<s:iterator value="#request.memberPage.list" var="member">	
	    	 		<tr>
	    	 			<td style="display: none;">${member.id}</td>
	    	 			<td>${member.loginID}</td>
	    	 			<td>${member.memberName}</td>
	    	 			<td><s:if test='#member.memberType=="admin"'>系统管理员</s:if>
	    	 				<s:elseif test='#member.memberType=="cashier"'>操作员</s:elseif>
	    	 				<s:elseif test='#member.memberType=="member"'>普通会员</s:elseif></td>	    
	    	 			<td>${member.mobile}</td>		 			
	    	 			<td>${member.email}</td>
	    	 			<td>
						<button onclick="edit(this)" class="btn btn-primary btn-xs" type="button">							
						<span class="glyphicon glyphicon-edit"></span></button>
						</td><td>
						<button onclick="deletemember(${member.id})" class="btn btn-danger btn-xs" type="button">
						<span class="glyphicon glyphicon-trash"></span></button>
						</td>  	 			
	    	 		</tr>
	    	 		</s:iterator>
	    	 	</tbody>
	    	 </table>	
	    	 </form>
	    	 <button onclick="init()" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
  				新增管理员
			</button>
	</div>
	<!-- 右边内容区域..-->
	

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="title">新增管理员</h4>
      </div>
      <div class="modal-body">
        <form id="form" method="post" class="form-horizontal">
        	<input id="id" name="id" type="hidden">
  			<div class="form-group">
    			<label class="col-sm-2 control-label">姓名</label>
    			<div class="col-sm-10">
     			<input id="name" type="text" class="form-control" name="memberName">
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-2 control-label">登录账号</label>
    			<div class="col-sm-10">
     			<input id="loginId" type="text" class="form-control" name="loginId">
    			</div>
  			</div>  		
  				
  			<div class="form-group">
    			<label class="col-sm-2 control-label">密码</label>
    			<div class="col-sm-10"><button onclick="show()" type="button" class="btn btn-default case2">重设密码</button>
    			</div>
    			<div class="col-sm-10 case1">
      				<input id="password" type="password" class="form-control" name="password">
    			</div>
  			</div>
  			<div class="form-group case1">
    			<label class="col-sm-2 control-label">确认密码</label>
    			<div class="col-sm-10">
      				<input type="password" class="form-control">
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-2 control-label">管理员类型</label>
    			<div class="col-sm-10">
     				<select id="memberType" class="form-control" name="memberType">
  						<option value="admin">系统管理员</option>
  						<option value="cashier">操作员</option>
  						<option value="member" selected>普通会员</option>
					</select>
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-2 control-label">电话</label>
    			<div class="col-sm-10">
     			<input id="mobile" type="text" class="form-control" name="mobile" value="13138645151">
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-2 control-label">电子邮箱</label>
    			<div class="col-sm-10">
     			<input id="email" type="email" class="form-control" name="email" value="313140949@qq.com">
    			</div>
  			</div>
	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" onclick="update()" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>
</div>
</div>   
</body>
</html>
