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
.table a{
	color:#202020;
}
.table a:HOVER {
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
		    <li role="presentation" class="active"><a href="#HWunfinished" aria-controls="HWdoing" role="tab" data-toggle="tab">未完成作业</a></li>
		    <li role="presentation" class=""><a href="#HWfinished" aria-controls="HWfinished" role="tab" data-toggle="tab">已完成作业</a></li>
		    <li role="presentation" class=""><a href="#HWall" aria-controls="HWall" role="tab" data-toggle="tab">全部作业</a></li>
		  </ul>
		</div>
	</div>
	<div class="rightpart col-md-9">
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div role="tabpanel" class="tab-pane active" id="HWunfinished">
	    	<div class="boxtitle"><h4><b>未完成作业列表</b></h4></div>
	    	<!-- 未完成作业列表列表 -->
	    	
	    	<s:if test="#request.unfinHWList.size==0">
	    		<div class="NotExist">
	    			<h1 align="center">:)</h1>
			    	<p align="center">没有未完成的作业</p>
			    	<hr class="divider" width="99%" align="center"/>
		    	</div>
	    	</s:if>
	    	<s:else>
			<table class="table table-hover">
				<tr>
				    <th>作业标题</th>
				    <th>发布日期</th>
				    <th>提交期限</th>
				    <th>关闭/接收</th>
				</tr>
				<s:iterator value="#request.unfinHWList" var="unfinhomework">
				<tr>
				    <td><a href="student/hwDetails.html?hwID=${unfinhomework.id}">${unfinhomework.title}</a></td>
				    <td>${unfinhomework.assignDateFormat}</td>
				    <td>${unfinhomework.closeDateFormat}</td>
				    <td>
				    	<s:if test="#unfinhomework.state">接收</s:if>
				    	<s:else>已关闭</s:else>
				    </td>
				</tr>
				</s:iterator>
			</table>
			<hr class="divider" width="99%" align="center"/>
			
			</s:else>
			<!-- 未完成作业列表END -->
		</div>
	    <div role="tabpanel" class="tab-pane" id="HWfinished">
	    	 <div class="boxtitle"><h4><b>已完成作业列表</b></h4></div>
	    	<!-- 已完成作业列表 -->
	    	<s:if test="#request.finHWList.size==0">
		    	<div class="NotExist">
			    	<h1 align="center">:(</h1>
			    	<p align="center">没有已完成的作业</p>
			    	<hr class="divider" width="99%" align="center"/>
		    	</div>
	    	</s:if>
	    	<s:else>
			<table class="table table-hover">
				<tr>
				    <th>作业标题</th>
				    <th>发布日期</th>
				    <th>提交期限</th>
				    <th>提交日期</th>
				    <th>教师下载日期</th>
				    <th>已批改/未批改</th>
				    <th>分数</th>
					<th>关闭/接收</th>
				</tr>
				<s:iterator value="#request.finHWList" var="hwsubmit">
				<tr>
				    <td><a href="student/hwDetails.html?hwID=${hwsubmit.homework.id}">${hwsubmit.homework.title}</a></td>
				    <td>${hwsubmit.homework.assignDateFormat}</td>
				    <td>${hwsubmit.homework.closeDateFormat}</td>
				    <td>${hwsubmit.submitTimeFormat}</td>
				    <s:if test="#hwsubmit.downloadTime!=NULL">
						<td>${hwsubmit.downloadTimeFormat}</td>
					</s:if>
				    <s:else><td>--</td></s:else>
				    <td>
				    	<s:if test="#hwsubmit.checked==1">已批改</s:if>
				    	<s:else>未批改</s:else>
				    </td>
				    <td>
				    	<s:if test="#hwsubmit.score>-1">${hwsubmit.score}</s:if>
						<s:else>--</s:else>
					</td>
				    <td>
				    	<s:if test="#hwsubmit.homework.state">接收</s:if>
				    	<s:else>已关闭</s:else>
				    </td>
				</tr>
				</s:iterator>
			</table>
			</s:else>
			<hr class="divider" width="99%" align="center"/>
			<!-- 已完成作业列表 END -->
		</div>
	    <div role="tabpanel" class="tab-pane" id="HWall">
	    	 <div class="boxtitle"><h4><b>全部作业列表</b></h4></div>
	    	<!-- 全部作业列表 -->
	    	<s:if test="#request.allHWList.size==0">
		    	<div class="NotExist">
			    	<h1 align="center">:)</h1>
			    	<p align="center">没有作业</p>
			    	<hr class="divider" width="95%" align="center"/>
		    	</div>
	    	</s:if>
	    	<s:else>
			<table class="table table-hover">
				<tr>
				    <th>作业标题</th>
				    <th>发布日期</th>
				    <th>提交期限</th>
				    <th>已交/未交</th>
				    <th>提交日期</th>
				    <th>教师下载日期</th>
				    <th>已批改/未批改</th>
				    <th>分数</th>
					<th>关闭/接收</th>
				</tr>
				<s:iterator value="#request.allHWList" var="homework">
				<tr>
					<s:set name="flag" value="1"></s:set>
					<s:iterator value="#request.finHWList" var="hwsubmit">
						<s:if test="#hwsubmit.homework.id==#homework.id">
						    <td><a href="student/hwDetails.html?hwID=${homework.id}">${homework.title}</a></td>
						    <td>${homework.assignDateFormat}</td>
						    <td>${homework.closeDateFormat}</td>
						    <td>已交</td>
						    <td>${hwsubmit.submitTimeFormat}</td>
						    <s:if test="#hwsubmit.downloadTime!=null">
								<td>${hwsubmit.downloadTimeFormat}</td>
							</s:if>
						    <s:else><td>--</td></s:else>
						    <td>
						    	<s:if test="#hwsubmit.checked==1">已批改</s:if>
						    	<s:else>未批改</s:else>
						    </td>
						    <td>
						    	<s:if test="#hwsubmit.score>0">${hwsubmit.score}</s:if>
						    	<s:else>--</s:else>
						    </td>
						    <td>
						    	<s:if test="#homework.state">接收</s:if>
						    	<s:else>已关闭</s:else>
						    </td>
						   <s:set name="flag" value="0"></s:set>
					    </s:if>
				    </s:iterator>
				    <s:if test="#flag==1">
				    		<td><a href="student/hwDetails.html?hwID=${homework.id}">${homework.title}</a></td>
						    <td>${homework.assignDateFormat}</td>
						    <td>${homework.closeDateFormat}</td>
						    <td>未交</td>
						    <td>--</td>
						    <td>--</td>
						    <td>--</td>
						    <td>--</td>
						    <td>
						    	<s:if test="#homework.state">接收</s:if>
						    	<s:else>已关闭</s:else>
						    </td>
				    </s:if>
				</tr>
				</s:iterator>
			</table>
			</s:else>
			<hr class="divider" width="99%" align="center"/>
			<!-- 全部作业列表 END -->
		</div>
		
	  </div>	
	</div>
<!-- 作业管理 ..-->
</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
