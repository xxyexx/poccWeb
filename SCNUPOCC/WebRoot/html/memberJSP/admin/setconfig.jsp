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

<script src="res/js/memberJS/setconfig.js"></script>
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
</style>
<body>
<!--导入头部导航条-->
<%@include file="/html/memberJSP/common/header.jsp" %>

<div class="container-fluid">
<div class="row">
	<!--导入左边导航条-->
	<%@include file="/html/memberJSP/common/left.jsp"%>
	<input type="hidden" id="leftflag" value="系统管理" >
	<!-- 右边内容区域 -->
	<div class="col-md-10 col-sm-8 right-div">
	    <div class="title">
		    <a><span class="glyphicon glyphicon-blackboard"></span>
		       <span>计算机组成原理实验平台后台管理系统</span>
		    </a>
	    </div>
	    
	    <table class="table table-striped table-responsive table-condensed" style="overflow:inherit;">
	    	<thead>
	    		<tr>
	    			<th>类型</th>
	    			<th>值</th>
	    			<th>显示名称</th>
	    			<th>备注</th>
	    		</tr>

	    	</thead>
	    	<tbody>
	    		<s:iterator value="#request.configList" var="config">
	    		<tr>
	    			<td>${config.item}</td>
	    			<td>${config.value}</td>
	    			<td>${config.displayName}</td>
	    			<td>${config.memo}</td>
	    			<td><button class="btn btn-primary btn-xs" type="button" onclick="editconfig(${config.id})">							
						<span class="glyphicon glyphicon-edit"></span></button>
					</td>
					<td><button class="btn btn-danger btn-xs" type="button" onclick="deleteconfig(${config.id})">
						<span class="glyphicon glyphicon-trash"></span></button>
					</td>  
	    		</tr>
	    		</s:iterator>
	    	</tbody>
	    </table>
	    <form id="form" method="post">
	    	<input name="id" type="hidden" id="cid">
	    </form>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
  新增优惠
</button>

<!-- 新增优惠模态框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增优惠</h4>
      	</div>
      <div class="modal-body">
        <form id="editform" method="post" class="form-horizontal">
        	<input name="id" id="editid" type="hidden">
  			<div class="form-group">
    			<label class="col-sm-2 control-label">类型</label>
    			<div class="col-sm-10">
      				<select name="item" class="col-sm-5 form-control ">
      					<option value="tryInterval">tryInterval</option>
      					<option value="tryDuration">tryDuration</option>
      					<option value="monthRent">monthRent</option>
      					<option value="discount">discount</option>
      					<option value="moreMonth">moreMonth</option>
      				</select>
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-2 control-label">值</label>
    			<div class="col-sm-10">
      				<input name="value" type="text" class="form-control">
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-2 control-label">显示名称</label>
    			<div class="col-sm-10">
      				<input name="displayName" type="text" class="form-control">
    			</div>
  			</div>
  			<div class="form-group">
    			<label class="col-sm-2 control-label">备注</label>
    			<div class="col-sm-10">
      				<input name="memo" type="text" class="form-control">
    			</div>
  			</div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>
	</div>
	<!-- 右边内容区域..-->
</div>
</div>   
</body>
</html>
