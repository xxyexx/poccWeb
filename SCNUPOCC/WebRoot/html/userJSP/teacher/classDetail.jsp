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
	function GetQueryString(name)//获取地址栏参数
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
	function delcfm(obj){
		var name = $(obj).parent().parent().children().eq(1).html();
		if(!confirm("确定要将"+name+"从班级中移除？")){
			window.event.returnValue=false;
		}
	}
	function msg(){
		alert("学生人数已达班级人数上限,不能再添加学生!");
	}
	function selectAll(){
		if($("#sa").is(':checked')){ 
			$("input[type='checkbox']").each(function(){this.checked=true;}); 
		}else{
			$("input[type='checkbox']").each(function(){this.checked=false;}); 
		}
	}
	function checked(){
		$("input[type='checkbox']").each(function(){
			if(this.checked){
				$("#delForm").submit();
			}
		}); 
	}
	$(document).ready(function() {
	      $('.classTable').dataTable( {
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
	<div class="rightpart col-md-10 col-md-offset-1">
		<div class="boxtitle"><h5><b>${request.schoolClass.className}</b></h5></div>
		<div class="showDesc">${request.schoolClass.scdesc}</div>
		<hr class="divider" width="99%" align="center"/>
		  <div class="boxtitle">
	  		<h5><b>${request.schoolClass.className}学生列表</b>
	  		<span class="msg">
	  			 <span>当前班级人数<span style="background-color: #337ab7" class="badge" >${request.studNum}</span>&nbsp;&nbsp;</span>
	  			 <span>人数上限<span style="background-color: #337ab7" class="badge" >${request.schoolClass.totalStudents}</span>&nbsp;&nbsp;</span>
	  			 <a href="teacher/exportExcelClass.html?classID=${request.schoolClass.id}" class="btn btn-sm btn-warning">导出名单</a>
	  			 <s:if test="#request.studNum==request.schoolClass.totalStudents">
	  			 <input type="button" onclick="msg()" class="btn btn-sm btn-info" value="添加学生" />
	  			 </s:if>
	  			 <s:else><a href="teacher/addStudentClass.html?classID=${request.schoolClass.id}" class="btn btn-sm btn-info">添加学生</a></s:else>
	  		</span>
	  		</h5>
	  	  </div>
	  	  <div class="showDesc">
	  	  <!-- dataTable -->
	  	  <form id="delForm" action="teacher/removeStudClass.html" method="post">
	  	  <input name="classID" type="hidden" value="${request.schoolClass.id}">
	  	  <table class="classTable table table-hover display">
	  	  	<s:if test="#request.studentList!=null&&#request.studentList.size()==0">
				<div class="NotExist">
	    			<h1 align="center">:(</h1>
			    	<p align="center">当前班级还没有学生</p>
			    	<hr class="divider" width="99%" align="center"/>
		    	</div>
			</s:if>
	  	  	<s:else>
			<thead>
			<tr>
				<th>学号</th>
			    <th>姓名</th>
			    <th>班级</th>
			    <th>学校</th>
			    <th>学院</th>
			    <th>上次登陆时间</th>
			    <th>上次登陆IP地址</th>
			    <th>
			    	<input id="sa" onclick="selectAll()" type="checkbox"/>
			    	<button onclick="checked()" type="button" class="btn btn-xs btn-danger">删除</button>
			    </th>
			</tr>
			</thead>
			<tbody>
			<s:iterator value="#request.studentList" var="student" status="status">
			<tr>
				<td>${student.loginID}</td>
				<td>${student.acctName}</td>
				<td>${student.classID}</td>
				<td>${student.acctTag}</td>
				<td>${student.acctType}</td>
				<s:if test="#student.lastLoginTime==null"><td>--</td></s:if>
				<s:else><td>${student.lastLoginTimeFormat}</td></s:else>
				<s:if test="#student.lastLoginIP==null"><td>--</td></s:if>
				<s:else><td>${student.lastLoginIP}</td></s:else>
				<td><input name="userID" type="checkbox" value="${student.id}" /></td>
			</tr>			
			</s:iterator>
			</tbody>  	  	
	  	  	</s:else>
	  	  </table>
	  	  </form>
	  	  <!-- dataTable.. -->
	  	  </div>	
	</div>
</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
