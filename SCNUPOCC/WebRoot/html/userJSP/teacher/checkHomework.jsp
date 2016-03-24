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
	<script type="text/javascript" charset="utf8" src="res/DataTables-1.10.7/js/jquery.dataTables.js"></script>
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
	function changefile(){
		$("#File").click();
	}
	function changefileName(){
		$("#filename").removeAttr("href");
		$("#filename").html($("#File").val().split("\\").pop());
		$("#File").attr("name","hwfile");
	}
	
	function changeType(){
		window.location.href="teacher/checkHomework.html?hwID="+${request.homework.id}+"&stud="+$("#selectType").val();
	}
	$(document).ready(function() {
	         $('#HWTable').dataTable( {
	             "language": {
	                 "lengthMenu": "每页 _MENU_ 条记录",
	                 "zeroRecords": "没有找到记录",
	                 "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
	                 "infoEmpty": "无记录",
	                 "infoFiltered": "(从 _MAX_ 条记录过滤)",
	                  "paginate": {
				        "first":      "首页",
				        "last":       "尾页",
				        "next":       "下页",
				        "previous":   "上页"
				     },
				     "processing":     "加载中...",
   					 "search":         "搜索:",
	             }
	         } );
	         var s = $("#s").val();
			 $("#selectType").val(s);
	 } );
	 
</script>
<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<div class="main">
<!-- 作业详情 -->
	<div class="rightpart col-md-10 col-md-offset-1">

		  <div class="boxtitle"><h5><b>${request.homework.title}</b></h5></div>
		  <div class="showDesc">${request.homework.hwdesc}</div>
		  <hr class="divider" width="99%" align="center"/>
		  <div class="boxtitle">
	  		<h5><b>作业批改:</b>
	  		<select id="selectType" onchange="changeType()" >
	  			<option value="finished">已完成作业学生</option>
	  			<option value="unfinished">未完成作业学生</option>
	  			<option value="all">该作业全部学生</option>
	  		</select>
	  		<span class="msg">
	  			 <span>已提交作业人数<span style="background-color: #337ab7" class="badge" >${request.submitNum}</span>&nbsp;&nbsp;</span>
	  			 <span>平均分<span style="background-color: #337ab7" class="badge" >${request.avg}</span>&nbsp;&nbsp;</span>
	  			 <a class="btn btn-sm btn-warning">导出名单</a>
	  			 <a class="btn btn-sm btn-info">下载作业文件</a>
	  		</span>
	  		</h5>
	  	  </div>
		  <div class="showDesc">
			<!-- dataTable -->
			<form action="teacher/scoreHomework.html?hwID=${request.homework.id}" method="post">
			<input id="s" type="hidden" value="${request.stud}">
			<table id="HWTable" class="table table-hover display">
			<s:if test="#request.stud.=='finished'&&#request.HWSList.size()==0">
				<div class="NotExist">
	    			<h1 align="center">:)</h1>
			    	<p align="center">没有需要批改的作业信息</p>
			    	<hr class="divider" width="99%" align="center"/>
		    	</div>
			</s:if>
			<s:else>
			<thead>
			<tr>
				<th>学号</th>
			    <th>姓名</th>
			    <th>班级</th>
			    <th>提交日期</th>
			    <th>作业文件</th>
			    <th>分数</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="#request.stud=='finished'">
			<!--已完成学生列表-->
			<s:iterator value="#request.HWSList" var="HWSubmit" status="status">
			<tr>
				<td>${HWSubmit.user.loginID}</td>
				<td>${HWSubmit.user.acctName}</td>
				<td>${HWSubmit.user.classID}</td>
				<td>${HWSubmit.submitTimeFormat}</td>
				<td><a href="student/downloadHWFile.html?hwSubmitID=${HWSubmit.id}">${HWSubmit.filename}</a></td>
				<td>
				<span hidden="true">${HWSubmit.score}</span>
				
				<input name="HWSubmitID" value="${HWSubmit.id}" type="hidden"/>
				<input name="score" class="score" type="number" required="required" min="0" max="100" value="${HWSubmit.score}"/>
				</td>
			</tr>
			</s:iterator>
			</s:if>
			<s:elseif test="#request.stud=='unfinished'">
			<!--未完成学生列表-->
			<s:iterator value="#request.UnfinishedStudList" var="student">
			<tr>
				<td>${student.loginID}</td>
				<td>${student.acctName}</td>
				<td>${student.classID}</td>
				<td>--</td>
				<td>--</td>
				<td>--</td>
			</tr>
			</s:iterator>
			<!--未完成学生列表..-->
			</s:elseif>
			<s:elseif test="#request.stud=='all'">
			<!--全部学生列表-->
			<s:iterator value="#request.StudList" var="student">
			<tr>
				<s:set name="flag" value="1"></s:set>
				<s:iterator value="#request.HWSList" var="HWSubmit" status="status">
					<s:if test="#HWSubmit.user.id==#student.id">
					<td>${HWSubmit.user.loginID}</td>
					<td>${HWSubmit.user.acctName}</td>
					<td>${HWSubmit.user.classID}</td>
					<td>${HWSubmit.submitTimeFormat}</td>
					<td><a href="student/downloadHWFile.html?hwSubmitID=${HWSubmit.id}">${HWSubmit.filename}</a></td>
					<td>
						<span hidden="true">${HWSubmit.score}</span>
						<input name="HWSubmitID" type="hidden" value="${HWSubmit.id}" />
						<input name="score" class="score" type="number" required="required" min="0" max="100" value="${HWSubmit.score}"/>
					</td>
					<s:set name="flag" value="0"></s:set>
					</s:if>
				</s:iterator>
				<s:if test="#flag==1">
					<td>${student.loginID}</td>
					<td>${student.acctName}</td>
					<td>${student.classID}</td>
					<td>--</td>
					<td>--</td>
					<td>--</td>
				</s:if>
			</tr>
			</s:iterator>
			<!--全部学生列表..-->
			</s:elseif>
			</tbody>
			</s:else>
			</table>
			<input class="btn btn-sm btn-primary col-md-3" type="submit" value="保&nbsp;&nbsp;存">
			</form>
			<!-- dateTable.. -->
			
		  </div>
	</div>
<!-- 作业详情  ..-->
</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
