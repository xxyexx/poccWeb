<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>计算机组成原理实验后台管理</title>
<!-- Bootstrap -->
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css" href="res/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script src="res/js/jquery.min.js" type="text/javascript"></script>
<script src="res/bootstrap-3.3.5-dist/js/bootstrap.js"></script>

<!-- Bootstrap datetimepicker -->
<link rel="stylesheet" type="text/css" href="res/bootstrap-3.3.5-dist/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<script src="res/bootstrap-3.3.5-dist/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="res/bootstrap-3.3.5-dist/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<!-- JavaScript -->
<script src="res/js/memberJS/createuser.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>	
  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- Bootstrap..-->
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="华南师范大学,实验平台,计算机组成原理">
<meta http-equiv="description" content="计算机组成原理实验平台">



<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
html,body{height:100%;}
body {
	background-color: #efefef;
	min-width:500px;
	display: block;
}
.container-fluid{
	padding-top:56px;
	height:100% !important;
}
.row{height:100% !important;}
.right-div{
	height:500px !important;
}
.right-div .title{
	width:100% !important;
	height:45px;
	background-color: white;
	padding-top:10px;
	padding-left:30px;
	margin-top:3px;
}
.title a{
	font-size:20px;
	color: #506470;
	text-decoration: none;
}
.title span{
	display: block;
	float:left;
}
.main{
	height:100%;
	margin-top:20px;
	min-height:450px;
	background-color: white;
	overflow: scroll;
}
.mainleft{
	height:100%;
	margin-top:20px;
	min-height:450px;
	background-color: white;
	overflow: scroll;
}
</style>

<script>





	
	
	//step1 账号前缀
	/*function funStep1(){
		var v=document.getElementById("step1").value;
		var reg=/^[a-zA-Z0-9_]{5,8}$/;
		var tip=document.getElementById("tip1");
		tip.style.color="red";
		if (v==""){ tip.innerHTML=""; return false;}
		if (reg.test(v)){
			tip.innerHTML="";
			return true;
		}else{
			tip.innerHTML="请输入5~8个字符，可包含字母、数字或下划线";
			return false;
		}
	}*/
	
	//step1 账号前缀
	function funStep1(){
		//var v=document.getElementById("step1").value;
		//alert("funStep1");
		var v = $("#step1");
		v.val($("#academy").find("option:selected").val());
		//var prefix = $("#academy").text().split("_")[2];
		//alert(prefix);
		return true;
	}
	
	//step2 开始序号
	function funStep2a(){
		//var v=document.getElementById("step2a").value;
		//var reg=/^[0-9]{1,4}$/;
		//var tip=document.getElementById("tip2a");
		//if (v==""){ tip.innerHTML=""; return false;}
		//tip.style.color="red";
		//if (reg.test(v)&&parseInt(v,10)>=0&&parseInt(v,10)<=9999){
			//序号没问题，输入符合要求
		//	tip.innerHTML="";
			//funStep2b();
		//	return true;
		//}else{
			tip.innerHTML="请输入为0~9999的整数";
		//	return false;
		//}
	}//step2 生成数量
	function funStep2b(){
		if (!funStep2a()){
			return false;
		}
		var v=document.getElementById("step2b").value;
		var reg=/^[0-9]{1,4}$/;
		var tip=document.getElementById("tip2b");
		if (v==""){ tip.innerHTML=""; return false;}
		tip.style.color="red";
		if (reg.test(v)&&parseInt(v,10)>=1&&parseInt(v,10)<=1000){
			var s=new Number;
			s=parseInt(v,10)+parseInt(document.getElementById("step2a").value,10);
			//alert(s);
			if (s>9999){
				tip.innerHTML="开始序号和生成数量的和不能大于9999";
				return false;
			}else{
				//输入符合要求
				document.getElementById("step2bRes").value=s;
				tip.innerHTML="";
				return true;
			}
		}else{
			tip.innerHTML="请输入1~1000的整数";
			return false;
		}		
	}
	//step2 提示先输入开始序号
	function funStep2c(){
		if (!funStep2a()){
			//alert("请先填写开始序号！");
			return false;
		}
	}
	//合并前缀和序号
	function merge(p,n){
		var num=String(n);
		while (num.length<4){
			num="0"+num;
		}
		return (p+num);
	}
	
	//预览生成账号
	function funDisplay(){
		if (funStep1()&&funStep2a()&&funStep2b()){
			var d1=document.getElementById("display1");	
			var d2=document.getElementById("display2");	
			var d3=document.getElementById("display3");
			d1.innerHTML=merge(document.getElementById("step1").value
						,document.getElementById("step2a").value);
			d2.innerHTML="&nbsp;&nbsp;~&nbsp;&nbsp;";
			d3.innerHTML=merge(document.getElementById("step1").value
						,document.getElementById("step2bRes").value-1);
			
			//d1.innerHTML="test";
			return;
		}else{
			return;
		}	
	}
	
	//检查后台是否有重复账号
	function checkAccount(){
		//使用AJAX检查后台是否有重复账号
		//alert("checkAccount");
	}
	//step 3 检查开始租期
	function funStep3a1(){
		//var v=document.getElementById("step3a1").value;
		//var tip=document.getElementById("tip3a1");
		//var reg=/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
		//tip.style.color="red";
		//if (reg.test(v)){
		//	tip.innerHTML="";
		//	return true;
		//}else{
		//	tip.innerHTML="无效的日期";
		//	return false;
		//}
	}
	//step 3 检查结束租期
	function funStep3a2(){
		//var v=document.getElementById("step3a2").value;
		//var tip=document.getElementById("tip3a2");
		//var reg=/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
		//tip.style.color="red";
		//if (reg.test(v)){
		//	tip.innerHTML="";
		//	return true;
		//}else{
		//	tip.innerHTML="无效的日期";
		//	return false;
		//}
	}	
	//step 3  检查租期内费用
	function funStep3b(){
		//var v=document.getElementById("step3b").value;
		//var reg=/^[0-9]{1,}(.[0-9]{1,})?$/;
		//var tip=document.getElementById("tip3b");
		//if (v==""){ tip.innerHTML=""; return false;}
		//tip.style.color="red";
		//if (reg.test(v)){
		//	tip.innerHTML="";
		//	return true;
		//}else{
		//	tip.innerHTML="输入大于0的小数或整数";
		//	return false;
		//}
	}
	
	function checkPassword1(){
		var v=document.getElementById("step3c").value;
		var reg=/^[0-9a-zA-Z_]{6,25}$/;
		var tip=document.getElementById("tip3c");
		if (v==""){ tip.innerHTML=""; return false;}
		tip.style.color="red";
		if (reg.test(v)){
			tip.innerHTML="";
			return true;
		}else{
			tip.innerHTML="长度为6~25，可使用字母、数字或下划线";
			return false;
		}
	}
	//step3 确认密码
	function checkPassword2(){
		var tip=document.getElementById("tip3d");
		var v=document.getElementById("step3d").value;
		if (v==""||!funStep3c()){ tip.innerHTML=""; return false;}
		tip.style.color="red";
		if (v==document.getElementById("step3c").value){
			tip.innerHTML="";
			return true;
		}else{
			tip.innerHTML="两次填写的密码需要一致";
			return false;
		}
	}
	
	
	
	
	
	
	
	
</script>
<body>
<!--导入头部导航条-->
<%@include file="/html/memberJSP/common/header.jsp" %>

<div class="container-fluid">
<div class="row">
	<!--导入左边导航条-->
	<%@include file="/html/memberJSP/common/left.jsp" %>
	<input type="hidden" id="leftflag" value="实验用户管理" >
	<!-- 右边内容区域 -->
	<div class="col-md-10 col-sm-8 right-div">
	    <div class="title">
		    <a><span class="glyphicon glyphicon-blackboard"></span>
		       <span>&nbsp;&nbsp;用户批量生成</span>
		    </a>
	    </div>
	    <!--用户列表模块-->
	    <div class="main">
	    <div class="container">
	    	<form id="form" class="form-horizontal" method="post">
	    	<!--第一步及第二步-->
	    	<div class="col-md-5 col-md-offset-1">	    		
   	 			<label><h3>第一步&nbsp;&nbsp;<small>选择学校(学院)：</small></h3></label><br>
	    		
   	 			<div class="form-group">
   	 				<label class="control-label"><h5><small><em>（其中省份、学校为必选）</em></small></h5></label>
	    			<div class="col-md-11">
					<s:select id="province" list="#session.ProvinceMap" class="form-control"
							  listKey="key" listValue="value" headerKey="-1" headerValue="-请选择省份-" 
							  onchange="changeSchool()">
					</s:select>
					<select name="schoolID" id="school" class="form-control" onchange="changeDept()">
						<option value="-1">-请选择学校-</option>
					</select>	  
					<select name="deptID" id="dept" class="form-control col-md-12">
				    	<option value="-1">-请选择学院-</option>
				    </select>    
	    			</div>
	    		</div>
   	 			
   	 			<label><h3>第二步&nbsp;&nbsp;<small>账号开始序号及生成数量：</small></h3></label><br>
  				<div class="form-group">
  					<label style="text-align: right;" class="col-md-3 control-label"><h5><small><em>开始序号：</em></small></h5></label>
  					<div class="col-md-6">
   	 					<input id="first" name="first"type="text" placeholder="0~9999的整数" class="form-control" 
   	 					onblur="checkFirst">
   	 					<small id="tipFirst"></small>
   	 				</div>
   	 			</div>
   	 			<div class="form-group">
   	 				<label style="text-align: right;" class="col-md-3 control-label"><h5><small><em>生成数量：</em></small></h5></label>
   	 				<div class="col-md-6">
   	 					<input id="number" name="num"type="text" class="form-control" placeholder="1~1000的整数">
   	 					<small id="tipNumber"></small>
   	 				</div>
   	 			</div>
   	 			<!-- 
   	 			<div class="form-group">
   	 				<h5>生成的账号为：</h5>
   	 				<div style="margin-left: 10">
   	 				<h4><em style="margin-left: 10%;">
   	 					<b id="display1"></b>
   	 					<b id="display2"></b>
   	 					<b id="display3"></b>
   	 				</em></h4>
   	 				</div>
   	 				<button class="btn btn-default btn-sm" type="button">检查</button>
   	 			</div>
   	 			 -->
  			</div>
  			
  			<!--第三步-->
  			<div class="col-md-5">
  					<h3>第三步&nbsp;&nbsp;<small>账号初始信息：</small></h3>
  				<div class="col-md-offset-1">
  				<div class="form-group">
  				<input id="unit" type="hidden">
  					<label class="col-md-4 control-label"><h5><small><em>开始时间(年/月)：</em></small></h5></label>
  					<div class="col-md-8">
  						<input id="startTime" value="2016/01" class="form-control time">
  						<small id="startTimetTip"></small>
  					</div>	
  				</div>
  				<div class="form-group">
  					<label class="col-md-4 control-label"><h5><small><em>到期时间(年/月)：</em></small></h5></label>
  					<div class="col-md-8">
  						<input id="endTime" value="2016/06" class="form-control time">
  						<small id="endTimeTip"></small>
  					</div>	
  				</div>
   	 			<div class="form-group">
   	 				<label class="col-md-4 control-label"><h5><small><em>租期费用（元）：</em></small></h5></label>
   	 				<div class="col-md-6">
   	 					<input id="step3b" type="text" class="form-control" placeholder="大于0">
   	 					<small id="tip3b"></small>
  					</div>	
  				</div>	
  				<div class="form-group">
  					<label class="col-md-3 control-label"><h5><small><em>初始密码：</em></small></h5></label>
   	 				<div class="col-md-8">
   	 					<input id="password1" type="text" class="form-control" placeholder="6~25个字母、数字或下划线">
   	 					<small></small>
  					</div>	
  				</div>
  				
  				<div class="form-group">	
  					<label class="col-md-3 control-label"><h5><small><em>确认密码：</em></small></h5></label>
   	 				<div class="col-md-8">	
   	 					<input id="password2" type="text" class="form-control" placeholder="再次输入密码">
   	 					<small id="tip3d"></small>
					</div>	
				</div>
				</div>
			</div>
			
			<!--清空、确认-->
			<div align="center" class="col-md-12">
  				<button class="btn btn-warning col-md-2 col-md-offset-3"
  					type="button" onclick="cleanAll()">全部清空</button>
  				<span class="col-md-1"></span>
  				<button onclick="formSubmit()" type="button"
  				class="btn btn-success col-md-2">批量生成</button>
  					
  			</div>
  			</form>
		</div>

	    </div>	
	    
	    </div>
	    <!--用户列表模块..-->
	</div>
	<!-- 右边内容区域..-->
</div>   
</body>
</html>
