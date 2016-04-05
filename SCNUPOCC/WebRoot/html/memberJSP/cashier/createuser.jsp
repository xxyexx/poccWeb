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
<link rel="icon" href="res/images/icon.png">
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
	height:800px !important;
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
   	 				<label class="control-label"><h5><small><em>（其中省份、学校为必选，系统自动为新的学校创建院校管理员）</em></small></h5></label>
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
   	 				<label style="text-align: right;" class="col-md-3 control-label"><h5><small><em>学生数量：</em></small></h5></label>
   	 				<div class="col-md-6">
   	 					<input id="number" name="num" type="text" class="form-control" placeholder="0~1000的整数">
   	 					<small id="tipNumber"></small>
   	 				</div>
   	 			</div>
   	 			<div class="form-group">
   	 				<label style="text-align: right;" class="col-md-3 control-label"><h5><small><em>教师数量：</em></small></h5></label>
   	 				<div class="col-md-6">
   	 					<input id="number" name="teaNum" type="text" class="form-control" placeholder="0~10的整数">
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
  						<input name="rentDate1" value="2016/01" class="form-control time">
  						<small id="startTimetTip"></small>
  					</div>	
  				</div>
  				<div class="form-group">
  					<label class="col-md-4 control-label"><h5><small><em>到期时间(年/月)：</em></small></h5></label>
  					<div class="col-md-8">
  						<input name="rentDate2" value="2016/06" class="form-control time">
  						<small id="endTimeTip"></small>
  					</div>	
  				</div>
   	 			<div class="form-group">
   	 				<label class="col-md-4 control-label"><h5><small><em>基本月租：</em></small></h5></label>
   	 				<div class="col-md-6">
   	 					<select class="form-control" name="monthlyRent">
   	 						<option value="10">10元</option>
   	 						<option value="15">15元</option>
   	 						<option value="20">20元</option>
   	 					</select>
  					</div>	
  				</div>	
  				<div class="form-group">
					<label class="col-md-4 control-label"><h5><small><em>优惠类型：</em></small></h5></label>
  					<div class="col-md-5" style="margin-top: 10px;">
 						<label class="radio-inline radio_option">
  							<input type="radio" name="couponType" value="no" checked>无优惠 
						</label>	
					</div>
			<div class="col-md-8 col-md-offset-4">			
				<label class="radio-inline radio_option col-md-5">
  				<input type="radio" name="couponType" value="discount">
  				折扣优惠
  					</label>
  				<select style="width: 105px" name="discount" class="form-control col-md-2">
  					<option value="90">九折</option>
  					<option value="85">八五折</option>
  					<option value="70">七折</option>
  					</select>
  				</div> 	
  			<div class="col-md-8 col-md-offset-4">
				<label class="radio-inline radio_option col-md-5">
  				<input type="radio" name="couponType" value="moreMonth"> 
  				赠送月份
				</label>
				<select style="width: 105px" name="moreMonth" class="form-control col-md-2">
  					<option selected = "selected" value="1">一个月</option>
  					<option value="2">两个月</option>
  					<option value="3">三个月</option>
  					</select>
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
