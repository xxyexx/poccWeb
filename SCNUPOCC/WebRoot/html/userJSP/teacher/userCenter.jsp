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
	max-height:500px;
	overflow:auto;
	padding:0;
	padding-bottom:20px;
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
#userIcon{
	display: none;
}
.tipsWrong{
	color:#E15857;
	margin-top:9px;
	margin-left:-15px;
	visibility: hidden;
}
.tipsCorrect{
	color:#4DB849;
	margin-top:9px;
	margin-left:-15px;
	visibility: hidden;
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
	function changeIcon(){//
		$("#userIcon").click();
	}
	function sumbitform(){//提交头像
		iconForm.submit();
	}
	var checked0=false;
	var checked1=false;
	var checked2=false;
	function checkPwd(){//检测旧密码
		var password = $("#password0").val();
		if(password !=""){
			$.ajax({
				url : "user/checkPassword.action",
				type : "post",
				data :{password:password,},
				success : function(data) {
					if(data=="pwdWrong"){//密码错误
						checked0=false;
						setCss(0,"Wrong");
					}else if(data="pwdTrue"){
						checked0=true;
						setCss(0,"Correct");
					}
				}
			});
		}else{//密码为空，错误
			setCss(0,"Wrong");
		}
	}
	function checkNewPwd(){//检测新密码规则
		var reg = /^[\w]{6,12}$/;//这个是正则表达式
		if($("#password1").val().match(reg)){//正确
			checked1=true;
		    setCss(1,"Correct");
		}else{//错误
			checked1=false;
			setCss(1,"Wrong");
		}
	}
	function checkensurePwd(){//再次输入密码
		if($("#password1").val()==$("#password2").val()&&$("#password1").val()!=""){//正确
			checked2=true;
			setCss(2,"Correct");
		}else{
			checked2=false;
			setCss(2,"Wrong");
		}
	}
	function setCss(i,type){//设置第i个，样式
		if(type=="Correct"){
			$(".tipsWrong:eq("+i+")").css("visibility","hidden");
			$(".tipsCorrect:eq("+i+")").css("visibility","visible");
			$("#password"+i).css("border-color","#4DB849");
		}else{
			$(".tipsWrong:eq("+i+")").css("visibility","visible");
			$(".tipsCorrect:eq("+i+")").css("visibility","hidden");
			$("#password"+i).css("border-color","#E15857");
		}
	}
	function submitPwdForm(){
		if(checked0==false){checkPwd();}
		if(checked1==false){checkNewPwd();}
		if(checked2==false){checkensurePwd();}
		if(checked0&&checked1&&checked2){
			alert("密码修改成功");
			pwdForm.submit();
		}else{
			alert("请正确填写要修改的密码信息");
		}
	}
</script>
<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<div class="main">
<!-- 用户中心 -->
	<div class="leftpart col-md-3">
		<div class="sidebar">
		  <div class="boxtitle"><h4><b>设置</b></h4></div>
		  <!-- Nav tabs -->
		  <ul id="homeworkTabs" class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#UserInfo" aria-controls="UserInfo" role="tab" data-toggle="tab">个人信息</a></li>
		    <li role="presentation" class=""><a href="#UserIcon" aria-controls="UserIcon" role="tab" data-toggle="tab">头像修改</a></li>
		    <li role="presentation" class=""><a href="#Password" aria-controls="Password" role="tab" data-toggle="tab">密码修改</a></li>
		    <!--
		    <li role="presentation" class=""><a href="#Loginlog" aria-controls="Loginlog" role="tab" data-toggle="tab">登录记录</a></li>
		    <li role="presentation" class=""><a href="#Actionlog" aria-controls="Actionlog" role="tab" data-toggle="tab">操作记录</a></li>
		  	 -->
		  </ul>
		</div>
	</div>
	<div class="rightpart col-md-9">
	  <!-- Tab panes -->
	  <div class="tab-content">
	  	<div role="tabpanel" class="tab-pane active" id="UserInfo">
	  	 <div class="boxtitle"><h4><b>个人信息</b></h4></div>
	    	<!-- 个人信息列表 -->
	    	<form action="user/changeInfo.html" method="post" class="form-horizontal">
	    		<div class="form-group col-md-12">
		    	<label for="acctName" class="col-sm-2 control-label">姓名</label>
		    	<div class="col-sm-6">
		     	     <input type="text" class="form-control" id="acctName" placeholder="姓名" 
		     	     readonly="readonly" value="${session.User.acctName}">
		   	    </div>
		  	    </div><hr>
		  	   	<div class="form-group col-md-12">
		    	<label for="acctTag" class="col-sm-2 control-label">学校院系</label>
		    	<div class="col-sm-6">
			     	 <input type="text" class="form-control" id="acctTag" placeholder="XX学校 XX学院" 
			     	 readonly="readonly" value="${session.User.acctTag},${session.User.acctType}">
		   	    </div>
		  	    </div><hr>
		  	   	<div class="form-group col-md-12">
		    	<label for="mobile" class="col-sm-2 control-label">手机</label>
		    	<div class="col-sm-6">
		     	 	<input name="mobile" type="text" class="form-control" id="mobile" placeholder="手机号" value="${session.User.mobile}">
		   	    </div>
		  	    </div><hr>
		  	   	<div class="form-group col-md-12">
		    	<label for="email" class="col-sm-2 control-label">邮箱</label>
		    	<div class="col-sm-6">
		     	 	<input name="email" type="text" class="form-control" id="email" placeholder="邮箱" value="${session.User.email}">
		   	    </div>
		  	    </div><hr>
		  	   	<div class="form-group col-md-12">
		    	<label for="rentDate" class="col-sm-2 control-label">账号有效期</label>
		    	<div class="col-sm-3">
		     	 	<input type="date" class="form-control" id="rentDate" placeholder="使用租期起" value="${session.User.rentDate1Format}">
		   	    </div>
		   	    <div class="col-sm-3">
		     	 	<input type="date" class="form-control" id="rentDate" placeholder="使用租期止" value="${session.User.rentDate2Format}">
		   	    </div>
		  	    </div><hr>	  	    
		  	   	<div class="form-group col-md-12">
		  	   	<label for="saveinfo" class="col-sm-2 control-label"></label>
		    	<div class="col-sm-6">
		     	 	<input type="submit" class="btn btn-primary col-sm-12" id="saveinfo" value="保&nbsp;&nbsp;存">
		   	    </div>
		  	    </div><hr>	 				  	    
		    </form>
			<!-- 个人信息列表.. -->
		</div>
		<div role="tabpanel" class="tab-pane" id="UserIcon">
	  	 <div class="boxtitle"><h4><b>修改头像</b></h4></div>
	    	<form name="iconForm" action="user/changeIcon.html" class="form-horizontal" enctype="multipart/form-data" method="post">
	    		<img alt="用户头像" src="user/showIcon.html" width="160px" height="160px"/>
	    		<input onclick="changeIcon()" type="button" class="btn btn-warning" value="上传头像">
	    		<input name="icon" type="file" accept="image/*" id="userIcon" onchange="sumbitform()">
	    	</form>
		</div>
		<div role="tabpanel" class="tab-pane" id="Password">
	  	 <div class="boxtitle"><h4><b>修改密码</b></h4></div>
	    	<!-- 修改密码 -->
			<form name="pwdForm" action="user/changePassword.html" class="form-horizontal" method="post">
			<div class="form-group col-md-12">
		  	   	<label for="password0" class="col-sm-2 control-label">当前密码</label>
		    	<div class="col-sm-5">
		     	 	<input type="password" onblur="checkPwd()" class="form-control" id="password0" placeholder="请输入当前密码">
		   	    </div>
		   	    <div class="col-sm-1">
		   	    	<span class="tipsWrong glyphicon glyphicon-remove"></span>
		   	    	<span class="tipsCorrect glyphicon glyphicon-ok"></span>
		   	    </div>
	  	    </div><hr>
			<div class="form-group col-md-12">
		  	   	<label for="password1" class="col-sm-2 control-label">新密码</label>
		    	<div class="col-sm-5">
		     	 	<input type="password" onblur="checkNewPwd()" class="form-control" id="password1" placeholder="请输入密码">
		   	    </div>
		   	    <div class="col-sm-1">
		   	    	<span class="tipsWrong glyphicon glyphicon-remove"></span>
		   	    	<span class="tipsCorrect glyphicon glyphicon-ok"></span>
		   	    </div>
	  	    </div><hr>		  	    	
			<div class="form-group col-md-12">
		  	   	<label for="password2" class="col-sm-2 control-label">确认密码</label>
		    	<div class="col-sm-5">
		     	 	<input name="newPassword" type="password" onblur="checkensurePwd()" class="form-control" id="password2" placeholder="请输入密码">
		   	    </div>
		   	    <div class="col-sm-1">
		   	    	<span class="tipsWrong glyphicon glyphicon-remove"></span>
		   	    	<span class="tipsCorrect glyphicon glyphicon-ok"></span>
		   	    </div>
	  	    </div><hr>
			<div class="form-group col-md-12">
		  	   	<label for="password2" class="col-sm-2 control-label"></label>
		  	   	<div class="col-sm-5">
		     	<input type="button" onclick="submitPwdForm()" class="btn btn-primary col-sm-12" id="password2" value="保存">
		     	</div>
	  	    </div><hr>
			</form>
			<!-- 修改密码 -->
		</div>
		<!--
		<div role="tabpanel" class="tab-pane" id="Loginlog">
	  	 <div class="boxtitle"><h4><b>登录记录</b></h4></div>
		</div>
		<div role="tabpanel" class="tab-pane" id="Actionlog">
	  	 <div class="boxtitle"><h4><b>操作记录</b></h4></div>
		</div>
		-->
	  </div>
	</div>
<!-- 用户中心.. -->
</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
