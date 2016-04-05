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
	<!-- DataTables CSS -->
	<link rel="stylesheet" type="text/css" href="res/DataTables-1.10.7/css/jquery.dataTables.css">
	<!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="res/DataTables-1.10.7/js/jquery.dataTables.min.js"></script>
<style type="text/css">
body{
	background-color: #EBEAE9;
	font-family:"Open Sans",Arial, "Hiragino Sans GB", "Microsoft YaHei",
			 	"STHeiti", "WenQuanYi Micro Hei", SimSun, sans-serif;
}
.main{
	width:100% !important;
	min-height:600px;
	padding-top:80px;
	padding-left:50px;
	padding-right:100px;
	padding-bottom: 50px;
	overflow: hidden;
}
li.active>a{
	color: #337ab7 !important;
}
.rightpart{
	min-height:350px;
	padding:0;
	border: 1px solid #ccc;
	background-color:white;
}
.divider{
	margin-top:5px;
	margin-bottom: 5px;
}
.showDesc{
	padding:20px;
	min-height: 80px;
	background-color: #FBFCFC;
	overflow: auto;
}
.boxtitle{
	width:100%;
	height:60px;
	background-color: white;
	padding-top:13px;
	padding-left:15px;
	color:#777;
	border-bottom:1px solid #eee;
}
.msg{
	float: right;
	padding-right:20px;
}
.score{
	width: 50px;
	height: 25px;
}
</style>
</head>
<script type="text/javascript">

</script>
<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<div class="main">
	<div class="rightpart col-md-10 col-md-offset-1">
		<s:if test="#request.Msg!=null">
		<div class="boxtitle"><h5><b>标题</b></h5></div>
		<div class="showDesc">${request.Msg.title}</div>
		<hr class="divider" width="99%" align="center"/>
		<div class="boxtitle"><h5><b>正文</b></h5></div>
		<div class="showDesc">${request.Msg.message}</div>
		<div class="boxtitle">
			<h5>
				<b>发送人</b>:${request.Msg.sender}
				发送时间:${request.Msg.sendTimeFormat}
			</h5>
		</div>
		</s:if>
		<s:else>
			<div class="boxtitle"><h5><b>读取消息失败</b></h5></div>
			<div class="NotExist">
		    	<h1 align="center">:(</h1>
		    	<p align="center">没有相关消息或权限</p>
		    	<hr class="divider" width="99%" align="center"/>
	    	</div>
		</s:else>
	</div>
</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
