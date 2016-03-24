<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>计算机组成原理实验平台</title>
    <link rel="icon" href="res/images/icon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<meta http-equiv="keywords" content="华南师范大学,计算机组成原理实验平台,计算机组成原理">
	<meta http-equiv="description" content="计算机组成原理实验平台">
	<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
body{
	background-color: #EBEAE9;
	font-family:"Open Sans",Arial, "Hiragino Sans GB", "Microsoft YaHei",
			 	"STHeiti", "WenQuanYi Micro Hei", SimSun, sans-serif;
}
.main{
	min-height:600px;
	padding-top:80px;
	padding-left:50px;
	padding-right:100px;
}
.leftpart {
	min-height: 300px;
	padding-left:50px;
	padding-right:30px;
}
.sidebar{
	max-height: 300px;
	border: 1px solid #ccc;
}
.sidebar li{
	width: 100%;
	border-top: 1px solid #eee;
	background-color: white;
}
li.active>a{
	color: #337ab7 !important;
}
.sidebar li a{
	height:60px;
	color: #555;
	text-align:center;
	padding-top:20px;
	border:none !important;
	font-weight:500;
}
.boxtitle{
	width:100%;
	height:60px;
	background-color: #F8F9FA;
	padding-top:13px;
	padding-left:15px;
	color:#777;
	border-bottom:1px solid #eee;
}
.sidebar li a:HOVER{
	color:#1F7BD8;
	background-color: white;
}

.rightpart{
	min-height:350px;
	max-height:800px;
	padding:0;
	border: 1px solid #ccc;
	background-color:white;
}
.divider{
	margin-top:5px;
	margin-bottom: 5px;
}
</style>
</head>
  
<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<div class="main">
<!-- 站内邮箱管理模块 -->
<div class="leftpart col-md-3">
	<div class="sidebar">
	  <div class="boxtitle"><h4><b>信箱管理</b></h4></div>
	  <!-- Nav tabs -->
	  <ul id="homeworkTabs" class="nav nav-tabs" role="tablist">
	    <li role="presentation" class="active"><a href="#mailunread" aria-controls="mailunread" role="tab" data-toggle="tab">未读消息</a></li>
	    <li role="presentation" class=""><a href="#mailread" aria-controls="mailread" role="tab" data-toggle="tab">已读消息</a></li>
	    <li role="presentation" class=""><a href="#mailsent" aria-controls="mailsent" role="tab" data-toggle="tab">已发消息</a></li>
	    <li role="presentation" class=""><a href="#sendmail" aria-controls="sendmail" role="tab" data-toggle="tab">发送消息</a></li>
	  </ul>
	</div>
</div>
<div class="rightpart col-md-9">
  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="mailunread">
    	<div class="boxtitle"><h4><b>未读信息列表</b></h4></div>
    	<!-- 未读信息列表 -->
    	<s:if test="#request.UnreadMsgList.size==0">
    		<div class="NotExist">
    			<h1 align="center">:)</h1>
		    	<p align="center">没有相关消息</p>
		    	<hr class="divider" width="99%" align="center"/>
	    	</div>
    	</s:if>
    	<s:else>
		<table class="table table-hover">
			<tr>
			    <th>标题</th>
			    <th>发送时间</th>
			    <th>发送人</th>
			    <th>操作</th>
			</tr>
			<s:iterator value="#request.UnreadMsgList" var="siteMsg">
				<tr>
				    <td><s:property value="#siteMsg.title"/></td>
				    <td><s:property value="#siteMsg.sendTimeFormat"/></td>
				    <td><s:property value="#siteMsg.sender"/></td>
				    <td>阅读|忽略</td>
				</tr>
			</s:iterator>
		</table>
		<hr class="divider" width="99%" align="center"/>
		
		</s:else>
		<!-- 未读信息列表END -->
	</div>
    <div role="tabpanel" class="tab-pane" id="mailread">
    	 <div class="boxtitle"><h4><b>已读信息列表</b></h4></div>
    	<!-- 已读消息列表 -->
    	<s:if test="#request.ReadMsgList.size==0">
	    	<div class="NotExist">
		    	<h1 align="center">:(</h1>
		    	<p align="center">没有相关消息</p>
		    	<hr class="divider" width="99%" align="center"/>
	    	</div>
    	</s:if>
    	<s:else>
		<table class="table table-hover">
			<tr>
			    <th>标题</th>
			    <th>发送时间</th>
			    <th>发送人</th>
			    <th>操作</th>
			</tr>
			<s:iterator value="#request.ReadMsgList" var="siteMsg">
				<tr>
				    <td><s:property value="#siteMsg.title"/></td>
				    <td><s:property value="#siteMsg.sendTimeFormat"/></td>
				    <td><s:property value="#siteMsg.sender"/></td>
				    <td>阅读|忽略</td>
				</tr>
			</s:iterator>
		</table>
		</s:else>
		<hr class="divider" width="99%" align="center"/>
		<!-- 已读消息列表 END -->
	</div>
    <div role="tabpanel" class="tab-pane" id="mailsent">
    	 <div class="boxtitle"><h4><b>已发信息列表</b></h4></div>
    	<!-- 已发消息 -->
    	<s:if test="#request.SentMsgList.size==0">
	    	<div class="NotExist">
		    	<h1 align="center">:(</h1>
		    	<p align="center">没有相关消息</p>
		    	<hr class="divider" width="95%" align="center"/>
	    	</div>
    	</s:if>
    	<s:else>
		<table class="table table-hover">
			<tr>
			    <th>标题</th>
			    <th>发送时间</th>
			    <th>发信人</th>
			    <th>收信人</th>
			    <th>操作</th>
			</tr>
			<s:iterator value="#request.SentMsgList" var="siteMsg">
				<tr>
				    <td><s:property value="#siteMsg.title"/></td>
				    <td><s:property value="#siteMsg.sendTimeFormat"/></td>
				    <td><s:property value="#siteMsg.sender"/></td>
				    <td><s:property value="#siteMsg.receiver"/></td>
				    <td>阅读|忽略</td>
				</tr>
			</s:iterator>
		</table>
		</s:else>
		<hr class="divider" width="99%" align="center"/>
		<!-- 已发信息列表 END -->
	</div>
   <!-- 发送信息 -->
   <div role="tabpanel" class="tab-pane" id="sendmail">
    	 <div class="boxtitle"><h4><b>发送信息</b></h4></div>
		 
		<!-- 发送信息 END -->
	</div>	
  </div>	
</div>

<!-- 站内邮箱管理.. -->
</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
