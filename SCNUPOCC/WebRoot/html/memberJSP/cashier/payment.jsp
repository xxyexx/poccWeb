<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="../../assets/js/ie8-responsive-file-warning.js"></script>
  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- Bootstrap datetimepicker -->
<link rel="stylesheet" type="text/css" href="res/bootstrap-3.3.5-dist/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<script src="res/bootstrap-3.3.5-dist/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="res/bootstrap-3.3.5-dist/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<!-- JavaScript -->
<script src="res/js/memberJS/payment.js"></script>

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
	margin-top:20px;
	padding:10px;
	min-height:450px;
	background-color: white;
	max-height:500px;
	overflow: auto;
}
.radio_option{
	margin-bottom:8px !important;
}
.item{
	padding:0px;
}
</style>
<body>
<!--导入头部导航条-->
<%@include file="/html/memberJSP/common/header.jsp" %>

<div class="container-fluid">
<div class="row">
	<!--导入左边导航条-->
	<%@include file="/html/memberJSP/common/left.jsp"%>
	<input type="hidden" id="leftflag" value="用户缴费管理" >
	<!-- 右边内容区域 -->
	<div class="col-md-10 col-sm-8 right-div">
	    <div class="title">
		    <a><span class="glyphicon glyphicon-blackboard"></span>
		       <span>&nbsp;&nbsp;用户缴费管理</span>
		    </a>
		    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
	新增记录
</button>
	    </div>
	    <div style="margin-left: 0px;width: 1000px;height: 715px;">
		
		</div>

	</div>
	<!-- 右边内容区域..-->
	<!-- Button trigger modal -->


<!-- 新增记录模态框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">缴费记录</h4>
      </div>
      <div class="modal-body container">     
      
		<!-- 缴费表单 -->
      	<form id="form" class="form-horizontal" method="post">		
  		<div class="form-group">
    		<label for="userID" class="col-md-3 control-label">缴费人：</label>
    		<div class="col-md-5">
		    <input name="acctID" id="userID" type="text" class="form-control" value="1001">
		    </div>
		</div>
 		<div class="form-group">
 			<label class="col-md-3 control-label">缴费日期：</label>
 			<div class="col-md-5">
  			<input name="payDate" type="text" class="form-control time_date" value="2016/3/25">
  			</div>
 		</div>
 		<div class="form-group">
 			<label class="col-md-3 control-label">帐号数量：</label>
  			<div class="col-md-5">
  			<input name="quantity" type="text" class="form-control" value="3">
  			</div>
 		</div>
 		<div class="form-group">
 			<label class="col-md-3 control-label">缴费金额：</label>
 			<div class="col-md-5">
  			<input name="amount" type="text" class="form-control" value="100">
  			</div>
 		</div>
 		<div class="form-group">
 			<label class="col-md-3 control-label">租期起始月份：</label>
  			<div class="col-md-5">
  			<input name="rentDate1" type="text" class="form-control time" value="2016/01">
  			</div>
 		</div>
 		<div class="form-group">
 			<label class="col-md-3 control-label">租期终止月份：</label>
  			<div class="col-md-5">
  			<input name="rentDate2" type="text" class="form-control time" value="2016/06">
  			</div>
 		</div> 		
 		<div class="form-group">
 		<label class="col-md-3 control-label">优惠类型：</label>
		
 			<div class="col-md-5">
 				<label class="radio-inline radio_option">
  				<input type="radio" name="coupon" value="no" checked>无优惠 
					</label>	
				</div>
			<div class="col-md-9 col-md-offset-3">			
				<label class="radio-inline radio_option col-md-3">
  				<input type="radio" name="coupon" value="discount">
  				折扣优惠
  					</label>
  				<select style="width: 105px" name="discount" class="form-control col-md-2">
  					<option selected = "selected" value="90">九折</option>
  					<option value="85">八五折</option>
  					<option value="70">七折</option>
  					</select>
  				</div> 	
  			<div class="col-md-9 col-md-offset-3">
				<label class="radio-inline radio_option col-md-3">
  				<input type="radio" name="coupon" value="moreMonth"> 
  				赠送月份
				</label>
				<select style="width: 105px" name="moreMonth" class="form-control col-md-2">
  					<option selected = "selected" value="1">一个月</option>
  					<option value="2">两个月</option>
  					<option value="3">三个月</option>
  					</select>
				</div>
								
 		</div> 	
 		<div class="form-group">
 			<label class="col-md-3 control-label">备注：</label>
 			<div class="col-md-5">  			
  			<textarea name="memo" rows="3" style=";resize:none;"class="form-control"></textarea>
  			</div>
 		</div>
		</form>
		<!-- 缴费表单 -->
		
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button onclick="update()" type="button" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>
</div>
</div>   
</body>
</html>
