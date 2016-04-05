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

<script src="res/js/memberJS/manageuser.js"></script>
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

<!-- DataTables -->
<link rel="stylesheet" type="text/css" href="res/DataTables-1.10.7/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="res/DataTables-1.10.7/js/jquery.dataTables.min.js"></script>


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
	<input type="hidden" id="leftflag" value="实验用户管理" >
	<!-- 右边内容区域 -->
	<div class="col-md-10 col-sm-8 right-div">
	    <div class="title">
		    <a><span class="glyphicon glyphicon-blackboard"></span>
		       <span>&nbsp;&nbsp;实验用户批量管理</span>
		    </a>
	    </div>
	    <form id="form" action="member/userbatchmanagerView.html" class="form-inline" method="post">
	    	<div class="form-group">
	    		<label class="control-label">学校：</label>
	   	 		
				<s:select id="province" list="#session.ProvinceMap" class="form-control"
							 listKey="key" listValue="value" headerKey="-1" headerValue="-请选择省份-" 
							  onchange="changeSchool()">
				</s:select>
			</div>
			<div class="form-group">
				<select name="schoolID" id="school" class="form-control" onchange="changeDept()">
					<option value="-1">-请选择学校-</option>
				</select>	  
			</div>
			<div class="form-group">
				<select name="deptID" id="dept" class="form-control col-md-12">
				    <option value="-1">-请选择学院-</option>
				   </select>    
	    	</div>
	    	<button type="submit" class="btn btn-info">查询</button>
	    </form>
	    <form id="tform" method="post">
	    	 <table id="table" class="table table-striped table-responsive table-condensed" style="overflow:inherit;">
	    		<thead>
	    	 		<tr>
	    	 			<th>内部账号</th>
	    	 			<th>登录账号</th>
	    	 			<th>姓名</th>
	    	 			<th>学校</th>
	    	 			<th>学院</th>
	    	 			<th>状态</th>
	    	 			<th>操作</th>
	    	 			<th></th>
	    	 			<th><input type="checkbox"></th>
	    	 		</tr>
	    	 	</thead>
	    	 	<tbody>	  
	    	 		<s:iterator value="#request.userPage.list" var="user">	
	    	 		<tr>
	    	 			<td>${user.acctID}</td>
	    	 			<td>${user.loginID}</td>
	    	 			<td>${user.acctName}</td>
	    	 			<td>${user.acctTag}</td>	    	 			
	    	 			<td>${user.acctType}</td>
	    	 			<td>启用</td>
	    	 			<td>
						<button onclick="openedit(${user.id})" class="btn btn-primary btn-xs" type="button">							
						<span class="glyphicon glyphicon-edit"></span></button>
						</td><td>
						<button class="btn btn-danger btn-xs" type="button">
						<span class="glyphicon glyphicon-trash"></span></button>
						</td><td>
						<input name="uid" type="checkbox" value="${user.id}">
						</td>   	 			
	    	 		</tr>
	    	 		</s:iterator>
	    	 	</tbody>
	    	 </table>	    
	    </form>
	    
	    <button type="button" class="btn btn-primary" onclick="openedit('-1')">
  			批量修改学生
		</button>
	    <button type="button" class="btn btn-primary" onclick="batchdelete()">
  			批量删除学生
		</button>

	</div>
	<!-- 右边内容区域..-->
	
	
	
<!-- 学生修改模态框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
 	<div class="modal-content">
    	<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        	<h4 class="modal-title" id="myModalLabel">修改学生</h4>
      	</div>
      	
      	<!-- 学生表单 -->
      	<form id="form" class="form-horizontal" method="post">	
   		<div class="modal-body container body1" style="border-bottom:1px solid #e5e5e5">
		  		<div class="form-group">
    			<label class="col-md-3 control-label">内部账号：</label>
    			<div class="col-md-5">
		    	<input name="acctID" id="userID" type="text" class="form-control">
		    	</div>
			</div>
		<div class="form-group">
    			<label class="col-md-3 control-label">登录账号：</label>
    			<div class="col-md-5">
		   	 	<input name="acctID" id="userID" type="text" class="form-control">
		    	</div>
			</div>
		<div class="form-group">
    		<label class="col-md-3 control-label">密码：</label>
    		<div class="col-md-5">
		    <input name="acctID" id="userID" type="button" class="form-control" value="重设密码">
		    </div>
		</div>
		<div class="form-group">
    		<label class="col-md-3 control-label">姓名：</label>
    		<div class="col-md-5">
		    <input name="acctID" id="userID" type="text" class="form-control" value="1001">
		    </div>
		</div>
		</div>
   		
   		<div class="modal-body container"> 
		<div class="form-group">
    		<label class="col-md-3 control-label">学校：</label>
    		<div class="col-md-5">
		    <input name="acctID" id="userID" type="text" class="form-control" readonly="readonly" value="${request.userPage.list[0].acctTag}">
		    </div>
		</div>
		<div class="form-group">
    		<label class="col-md-3 control-label">学院：</label>
    		<div class="col-md-5">
		    <input name="acctID" id="userID" type="text" class="form-control" readonly="readonly" value="${request.userPage.list[0].acctType}">
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
    		<label class="col-md-3 control-label">基本月租：</label>
    		<div class="col-md-5">
		    <select class="form-control">
		    	<option value="15">15</option>
		    	<option value="20">20</option>
		    	<option value="25">25</option>
		    </select>
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
  					<option value="90">九折</option>
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
    		<label class="col-md-3 control-label">账号状态：</label>
    		<div class="col-md-5">
		    <select class="form-control">
		    	<option value="on">启用</option>
		    	<option value="off">禁用</option>
		    </select>
		   </div>
		</div>
		</div>
		</form>
		
		<!-- 学生信息表单 -->
		
		
		
		<div class="modal-footer">  
			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        	<button type="button" class="btn btn-primary" onclick="submitedit()">提交</button>      
     	</div> 
  	</div>
	</div>
</div>
<!-- 学生修改模态框 -->

</div>
</div>
</body>
</html>
