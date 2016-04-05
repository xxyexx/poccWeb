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
	<!-- DataTables CSS -->
	<link rel="stylesheet" type="text/css" href="res/DataTables-1.10.7/css/jquery.dataTables.css">
	<!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="res/DataTables-1.10.7/js/jquery.dataTables.min.js"></script>
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
	overflow: hidden;
	padding-bottom: 50px;
	overflow: hidden;
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
	padding:0;
	border: 1px solid #ccc;
	background-color:white;
}

.divider{
	margin-top:5px;
	margin-bottom: 5px;
}
.homeworkTitle a{
	color:#202020;
}
.homeworkTitle a:HOVER {
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
		if(!confirm("删除班级会自动解散班级所有学生,确定要删除？")){
			window.event.returnValue=false;
		}
	}
	$(document).ready(function() {
	         $('#classTable').dataTable( {
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
	 } );
</script>
<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<div class="main">
	<!-- 教学班管理 -->
	<div class="leftpart col-md-3">
		<div class="sidebar">
		  <div class="boxtitle"><h4><b>教学班管理</b></h4></div>
		  <!-- Nav tabs -->
		  <ul id="homeworkTabs" class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#Classlist" aria-controls="Classlist" role="tab" data-toggle="tab">班级列表</a></li>
		    <li role="presentation" class=""><a href="#AddClass" aria-controls="AddClass" role="tab" data-toggle="tab">添加班级</a></li>
		  </ul>
		</div>
	</div>
	<div class="rightpart col-md-9">
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div role="tabpanel" class="tab-pane active" id="Classlist">
	    	<div class="boxtitle"><h4><b>教学班列表</b></h4></div>
	    	<!-- 教学班列表 -->
	    	<s:if test="#request.classList.size==0">
	    		<div class="NotExist">
	    			<h1 align="center">:(</h1>
			    	<p align="center">没有相关的信息</p>
			    	<hr class="divider" width="99%" align="center"/>
		    	</div>
	    	</s:if>
	    	<s:else>
	    	<!-- dataTable -->
			<table id="classTable" class="table table-hover display">
				<thead>
				<tr>
				    <th>班级名称</th>
				    <th>班级人数</th>
				    <th>班级容量(人)</th>
				    <th>创建日期</th>
				    <th>操作</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="#request.classList" var="schoolClass">
					<tr>
					    <td class="expTitle"><a target="_blank" href="teacher/detailClass.html?classID=${schoolClass.id}">${schoolClass.className}</a></td>
					    <td>${schoolClass.currentStudents}</td>
					    <td>${schoolClass.totalStudents}</td>
					    <td>${schoolClass.createDateFormat}</td>
					    <td>
					    	<a target="_blank" href="teacher/detailClass.html?classID=${schoolClass.id}" class="btn btn-xs btn-info">管理</a>
					    	<a href="teacher/deleteClass.html?classID=${schoolClass.id}" onclick="delcfm()" class="btn btn-xs btn-warning">删除</a>
					    </td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			<!-- dataTable.. -->
			<hr class="divider" width="99%" align="center"/>
			</s:else>
			<!--教学班列表END -->
		</div>
	    <div role="tabpanel" class="tab-pane" id="AddClass">
	    	<div class="boxtitle"><h4><b>添加教学班</b></h4></div>
	    	<form action="teacher/addClass.html" method="post" class="form-horizontal">
		    	<div class="form-group col-md-12"><br>
		    	<label for="exp" class="col-sm-3 control-label">请输入班级名称</label>
		    	<div class="col-sm-6">
		    		<input name="schoolclass.className" type="text" required="required" class="form-control" id="title" placeholder="班级名称">
		   	    </div>
		  	    </div>
		    	<div class="form-group col-md-12">
		    	<label for="exp" class="col-sm-3 control-label">请输入班级人数</label>
		    	<div class="col-sm-6">
		    		<input name="schoolclass.totalStudents" type="number" required="required" min="1" class="form-control" id="title" placeholder="班级人数">
		   	    </div>
		  	    </div><br>			  	    	
		    	<div class="form-group col-md-12">
		    	<label for="exp" class="col-sm-3 control-label">请输入备注信息</label>
		    	<div class="col-sm-6">
		    		<textarea name="schoolclass.scdesc" rows="4"  class="form-control" placeholder="请输入备注内容（限300字以内）" maxlength="300"></textarea>
		   	    </div>
		  	    </div><br>
		  	   	<div class="form-group col-md-12">
		  	   	<label for="saveinfo" class="col-sm-3 control-label"></label>
		    	<div class="col-sm-6">
		     	 	<input type="submit" class="btn btn-primary col-sm-12" id="saveinfo" value="添&nbsp;&nbsp;加">
		   	    </div> 
		  	    </div><hr>		  	    		  	    
	    	</form>
		</div>		
		
		</div>
	  </div>
	<!-- 教学班管理 -->
</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
