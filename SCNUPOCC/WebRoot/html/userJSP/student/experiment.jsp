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
			 	"STHeiti", "WenQuanYi   Hei", SimSun, sans-serif;
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
.expList a{
	color:#202020;
}
.expList a:HOVER{
	color: #64ADBC;
}
</style>
  </head>
<script type="text/javascript">
	$(function () {
		$("[data-toggle='tooltip']").tooltip(); 
		var select=GetQueryString("select");
		$("#homeworkTabs li:eq("+select+") a").tab('show'); // Select third tab (0-indexed)
	});
	
	function GetQueryString(name)//获取地址栏参数
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
	function delcfm(){
		if(!confirm("确定要删除？")){
			window.event.returnValue=false;
		}
	}
</script>  
  <body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<div class="main">
	<!-- 实验管理 -->
	<div class="leftpart col-md-3">
		<div class="sidebar">
		  <div class="boxtitle"><h4><b>仿真实验</b></h4></div>
		  <!-- Nav tabs -->
		  <ul id="homeworkTabs" class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#Explist" aria-controls="Explist" role="tab" data-toggle="tab">实验课程</a></li>
		    <li role="presentation" class=""><a href="#Expfile" aria-controls="Expfile" role="tab" data-toggle="tab">实验存档</a></li>
		  </ul>
		</div>
	</div>
	<div class="rightpart col-md-9">
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div role="tabpanel" class="tab-pane active" id="Explist">
	    	<div class="boxtitle"><h4><b>课程列表</b></h4></div>
	    	<!-- 课程列表 -->
	    	<s:if test="#request.ExpList.size==0">
	    		<div class="NotExist">
	    			<h1 align="center">:)</h1>
			    	<p align="center">没有相关的课程</p>
			    	<hr class="divider" width="99%" align="center"/>
		    	</div>
	    	</s:if>
	    	<s:else>
			<table class="expList table table-hover">
				<tr>
				    <th>标题</th>
				    <th>撰写人</th>
				</tr>
				<s:iterator value="#request.ExpList" var="experiment">
					<tr>
					    <td><a href="student/inExperiment.action?id=${experiment.id}">${experiment.title}</a></td>
					    <td>${experiment.user.acctName}</td>
					</tr>
				</s:iterator>
			</table>
			<hr class="divider" width="99%" align="center"/>
			
			</s:else>
			<!-- 课程列表END -->
		</div>
	    <div role="tabpanel" class="tab-pane" id="Expfile">
	    	 <div class="boxtitle"><h4><b>实验存档列表</b></h4></div>
	    	<!-- 存档列表 -->
	    	<s:if test="#request.FileList.size==0">
		    	<div class="NotExist">
			    	<h1 align="center">:(</h1>
			    	<p align="center">没有相关的实验存档</p>
		    	</div>
	    	</s:if>
	    	<s:else>
			<table class="table table-hover">
				<tr>
				    <th style="width:160px;">实验标题</th>
				    <th>文件名称</th>
				    <th>上传日期</th>
				    <th>操作</th>
				</tr>
				<s:iterator value="#request.FileList" var="poccfile">
					<tr>
					    <td>${poccfile.experiment.title}</td>
					    <td>${poccfile.file_name}</td>
					    <td>${poccfile.upload_timeFormat}</td>
					    <td>
					    	<a href="${poccfile.file_url}" type="button" class="btn btn-sm btn-success">下载</a>
					    	<a href="student/deletePoccfile.action?poccFileid=${poccfile.id}" onclick="delcfm()" type="button" class="btn btn-sm btn-danger">删除</a>
					    </td>
					</tr>
				</s:iterator>
			</table>
			</s:else>
			<hr class="divider" width="99%" align="center"/>
			<!-- 已完成作业列表 END -->
		</div>
		</div>
	  </div>
	</div>
	<!-- 实验管理 -->
<!--导入footer-->
<%@include file="footer.jsp" %>
  </body>
</html>
