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
	max-height:500px;
	overflow:auto;
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
		if(!confirm("确定要删除？")){
			window.event.returnValue=false;
		}
	}
	function receCfm(){
		if(!confirm("确定要提前关闭作业接收状态？")){
			window.event.returnValue=false;
		}
	}
	function  cloCfm(closeDate){
		var date=Date.parse(closeDate.replace(/-/g,"/"));
		var now=new Date();
		if(date<now){
			alert("作业已超过提交期限,无法改为接收状态");
			window.event.returnValue=false;
		}else if(!confirm("确定要将作业改为接收状态？")){
			window.event.returnValue=false;
		}
	}
</script>
<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<div class="main">
<!-- 作业管理模块 -->
	<div class="leftpart col-md-3">
		<div class="sidebar">
		  <div class="boxtitle"><h4><b>作业管理</b></h4></div>
		  <!-- Nav tabs -->
		  <ul id="homeworkTabs" class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#homeworks" aria-controls="homeworks" role="tab" data-toggle="tab">批改作业</a></li>
		    <li role="presentation" class=""><a href="#newHomework" aria-controls="newHomework" role="tab" data-toggle="tab">发布新作业</a></li>
		  </ul>
		</div>
	</div>
	<div class="rightpart col-md-9">
	<div class="tab-content">
		<!-- 已发布作业 -->
	    <div role="tabpanel" class="tab-pane active" id="homeworks">
	    	<div class="boxtitle"><h4><b>已发布作业列表</b></h4></div>
	    	<s:if test="#request.HWList.size==0">
	    		<div class="NotExist">
	    			<h1 align="center">:(</h1>
			    	<p align="center">没有相关的作业信息</p>
			    	<hr class="divider" width="99%" align="center"/>
		    	</div>
	    	</s:if>
	    	<s:else>
			<table class="table table-hover">
				<tr>
				    <th>作业标题</th>
				    <th>发布日期</th>
				    <th>提交期限</th>
				    <th>应交人数</th>
				    <th>已交人数</th>
				    <th>已批改数量</th>
				    <th>关闭/接收</th>
				    <th>操作</th>
				</tr>
				<s:iterator value="#request.HWList" var="homework">
				<tr>
				    <td class="homeworkTitle"><a href="teacher/checkHomework.html?hwID=${homework.id}">${homework.title}</a></td>
				    <td>${homework.assignDateFormat}</td>
				    <td>${homework.closeDateFormat}</td>
				    <td>${homework.totalPerson}</td>
				    <td>${homework.submittedCount}</td>
				    <td>${homework.checkedCount}</td>
				    <td>
				    	<s:if test="#homework.state">
				    		<a href="teacher/changeStateHomework.html?hwID=${homework.id}" onclick="receCfm()" class="btn btn-xs btn-success">接收</a>
				    	</s:if>
				    	<s:else><a href="teacher/changeStateHomework.html?hwID=${homework.id}" onclick="cloCfm('${homework.closeDateFormat}')" class="btn btn-xs btn-primary">已关闭</a></s:else>
				    </td>
				    <s:if test="#homework.submittedCount==0">
				    	<td><a href="teacher/deleteHomework.html?hwID=${homework.id}" 
				    	onclick="delcfm()" class="btn btn-xs btn-warning">撤销</a></td>
				    </s:if>
					<s:else><td><a href="teacher/checkHomework.html?hwID=${homework.id}" class="btn btn-xs btn-default">批改</a></td></s:else>
				</tr>
				</s:iterator>
			</table>
			<hr class="divider" width="99%" align="center"/>
			</s:else>
	    </div>
	    <!-- 新作业 -->
	    <div role="tabpanel" class="tab-pane" id="newHomework">
	    <form action="teacher/addHomework.html" method="post" class="form-horizontal">
	   		<div class="boxtitle"><h4><b>发布新作业</b></h4></div><br>
	    	<div class="form-group col-md-12">
	    	<label for="exp" class="col-sm-3 control-label">请选择作业相应实验</label>
	    	<div class="col-sm-6">
	     	     <s:select name="expID" id="exp" class="form-control" list="#request.EXPMap" listKey="key" 
	     	     listValue="value" required="required" ></s:select>
	   	    </div>
	  	    </div><br>
		    <div class="form-group col-md-12">
	    	<label for="title" class="col-sm-3 control-label">输入作业标题</label>
	    	<div class="col-sm-6">
	     	     <input name="homework.title" type="text" required="required" class="form-control" id="title" placeholder="标题">
	   	    </div>
	  	    </div><br>
		    <div class="form-group col-md-12">
	    	<label for="classes" class="col-sm-3 control-label">选择班级</label>
	    	<div class="col-sm-6">
	     	     <s:select name="classID" list="#request.ClassMap" multiple="true" size="2" listKey="key" 
	     	     listValue="value" id="classes" cssClass="form-control" required="required" ></s:select>
	   	    </div>
	  	    </div><br>
		    <div class="form-group col-md-12">
	    	<label for="time" class="col-sm-3 control-label">作业截止日期</label>
	    	<div class="col-sm-6">
	     	     <input name="closeDate" type="date" class="form-control" id="time" required="required" >
	   	    </div>
	  	    </div><br>
		    <div class="form-group col-md-12">
	    	<label for="time" class="col-sm-3 control-label">作业备注</label>
	    	<div class="col-sm-6">
	     	    <textarea name="hwdesc" rows="3"  class="form-control" placeholder="请输入作业描述（限300字以内）" maxlength="300"></textarea>
	   	    </div>
	  	    </div><br>
	  	   	<div class="form-group col-md-12">
	  	   	<label for="saveinfo" class="col-sm-3 control-label"></label>
	    	<div class="col-sm-6">
	     	 	<input type="submit" class="btn btn-primary col-sm-12" id="saveinfo" value="发&nbsp;&nbsp;布">
	   	    </div> 
	  	    </div><hr>	  	    
	    </form>
	    </div>
	</div>
	</div>
<!-- 作业管理 ..-->
</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
