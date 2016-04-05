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
	<script src="res/js/userJS/managerPage.js"></script>
	
	<!-- DataTables -->
	<link rel="stylesheet" type="text/css" href="res/DataTables-1.10.7/css/jquery.dataTables.css">
	<script type="text/javascript" charset="utf8" src="res/DataTables-1.10.7/js/jquery.dataTables.min.js"></script>
	
	
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
	min-height:900px;
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
	#max-height:500px;
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
<!-- 用户管理模块 -->
	<input id="left" value="${requestScope.left}" type="hidden">
	<div class="leftpart col-md-3">
		<div class="sidebar">
		  <div class="boxtitle"><h4><b>用户管理</b></h4></div>
		  <!-- Nav tabs -->
		  <ul  class="nav nav-tabs" role="tablist">
		    <li id="studentNav" role="presentation" class="active"><a href="#studentList" aria-controls="HWdoing" role="tab" data-toggle="tab">学生管理</a></li>
		    <li id="teacherNav" role="presentation"><a href="#teacherList" aria-controls="HWfinished" role="tab" data-toggle="tab">教师管理</a></li>
		    <li  class=""><a>计费统计</a></li>
			<li  class=""><a>学生绑定</a></li>
			
		  </ul>
		</div>
	</div>
	<div class="rightpart col-md-9">
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div role="tabpanel" class="tab-pane active" id="studentList">
	    	<div class="boxtitle"><h4><b>学生列表</b></h4></div>
	    	
	    	<!-- 学生列表 -->	    	
	    	<s:if test="#request.studentPage.list.size==0">
	    		<div class="NotExist">
	    			<h1 align="center">:)</h1>
			    	<p align="center">没有未完成的作业</p>
			    	<hr class="divider" width="99%" align="center"/>
		    	</div>
	    	</s:if>
	    	<s:else>
			<table id="sTable" class="table table-hover">
				<thead>
				<tr>
				    <th>登录账号</th>
				    <th>姓名</th>
				    <th>学校</th>
				    <th>学院</th>
				    <th>起始时间</th>
				    <th>结束时间</th>
				    <th>基本月租</th>
				    <th>优惠类型</th>
				    <th>优惠率</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="#request.studentPage.list" var="student">
				<tr>
					<td>${student.loginID}</td>
				    <td>${student.acctName}</td>
				    <td>${student.acctTag}</td>
				    <td>${student.acctType}</td>
				    <td><s:property value="#student.rentDate1.toString().substring(0,4)"/>年
				    	<s:property value="#student.rentDate1.toString().substring(5,7)"/>月</td>
				    <td><s:property value="#student.rentDate2.toString().substring(0,4)"/>年
				    	<s:property value="#student.rentDate2.toString().substring(5,7)"/>月</td>
				    <td>${student.monthlyRent}</td>
				    <s:if test='#student.couponType=="no"'>
	    	 				<td>无优惠</td> <td>无</td>
	    	 			</s:if>
	    	 			<s:elseif test='#student.couponType=="discount"'>
	    	 				<td>折扣优惠</td><td>${student.discount}</td>
	    	 			</s:elseif>
	    	 			<s:elseif test='#student.couponType=="moreMonth"'>
	    	 				<td>赠送月份</td><td>${student.moreMonth}月</td>
	    	 			</s:elseif>
	    	 			<s:elseif test='#student.couponType=="free"'>
	    	 				<td>免费账号</td><td>免费账号</td>
	    	 			</s:elseif>
	    	 			<s:else><td>???</td><td>???</td></s:else>	 
				</tr>
				</s:iterator>
				</tbody>
			</table>
			<hr class="divider" width="99%" align="center"/>
			
			</s:else>
			<!-- 学生列表END -->
		</div>
	    <div role="tabpanel" class="tab-pane" id="teacherList">
	    	 <div class="boxtitle"><h4><b>教师列表</b></h4></div>
	    	<!-- 教师列表 -->
	    	<s:if test="#request.teacherPage.list.size==0">
		    	<div class="NotExist">
			    	<h1 align="center">:(</h1>
			    	<p align="center">没有教师</p>
			    	<hr class="divider" width="99%" align="center"/>
		    	</div>
	    	</s:if>
	    	<s:else>
			<table id="tTable" class="table table-hover">
				<thead>
				<tr>
					<th>登录账号</th>
				    <th>姓名</th>
				    <th>学校</th>
				    <th>学院</th>
				    <th>电话</th>
				    <th>邮箱</th>
				    <th>优惠类型</th>
				    <th>操作</th>
				</tr>
				</thead>
				<tbody>
				<s:iterator value="#request.teacherPage.list" var="teacher">
				<tr>
					<td style="display:none;">${teacher.id}</td>
					<td>${teacher.loginID}</td>
					<td>${teacher.acctName}</td>
					<td>${teacher.acctTag}</td>
					<td>${teacher.acctType}</td>
					<td>${teacher.mobile}</td>
					<td>${teacher.email}</td>
					<td>免费账号</td>
					<td><a onclick="updateTea(this)">修改</a></td>
				</tr>
				</s:iterator>
				</tbody>
			</table>
			</s:else>
			<hr class="divider" width="99%" align="center"/>
			<!-- 教师列表 END -->
		</div>

		
	  </div>	
	</div>
<!-- 用户管理 ..-->

<!-- 教师模态框 -->
<div class="modal fade" id="teacherModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="title">修改教师信息</h4>
      </div>
      <div class="modal-body">
        <form id="tform" method="post" class="form-horizontal">
        	<input id="tid" name="id" type="text">
  			<div class="form-group">
    			<label class="col-sm-3 control-label">姓名</label>
    			<div class="col-sm-9">
     			<input id="tname" type="text" class="form-control" name="name">
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-3 control-label">登录账号</label>
    			<div class="col-sm-9">
     			<input id="tloginId" type="text" class="form-control" name="loginId">
    			</div>
  			</div> 
  			<div class="form-group">
    			<label class="col-sm-3 control-label">密码</label>
    			<div class="col-sm-9"><button onclick="showPass()" type="button" class="btn btn-default case2">重设密码</button>
    			</div>
    			<div class="col-sm-9 case1">
      				<input id="tpassword" type="password" class="form-control" name="password">
    			</div>
  			</div>
  			<div class="form-group case1">
    			<label class="col-sm-3 control-label">确认密码</label>
    			<div class="col-sm-9">
      				<input id="tpassword1" type="password" class="form-control">
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-3 control-label">账号类型</label>
    			<div class="col-sm-9">
     				<select class="form-control">
  						<option>教师</option>
					</select>
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-3 control-label">电话</label>
    			<div class="col-sm-9">
     			<input id="tmobile" type="text" class="form-control" name="mobile">
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-3 control-label">电子邮箱</label>
    			<div class="col-sm-9">
     			<input id="temail" type="email" class="form-control" name="email">
    			</div>
  			</div>
	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" onclick="submitTea()" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>
<!-- 教师模态框 -->

</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
