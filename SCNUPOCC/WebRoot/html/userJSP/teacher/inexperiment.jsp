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
<title>计算机组成原理实验平台</title>
    <link rel="icon" href="res/images/icon.png">
<!-- Bootstrap -->
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css" href="res/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="res/css/userCSS/student/BootSideMenu.css">
<script src="res/js/BootSideMenu.js"  type="text/javascript"></script> 
<script src="res/js/jquery.min.js" type="text/javascript"></script>
<script src="res/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
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
	min-height:300px;
	overflow-x: hidden;
	background-color: #EBEAEB;
	font-family:"Open Sans",Arial, "Hiragino Sans GB", "Microsoft YaHei",
		 	"STHeiti", "WenQuanYi Micro Hei", SimSun, sans-serif;
}
.Test_main{
	margin-top:60px;
}
.right{
}
.btn-div{
	padding-top: 18px;
	padding-bottom:10px;
	background-color:rgba(7,26,36,0.8);
}
#demo{
	margin-top:60px;
	width: 580px;
}
#pfile{
	visibility: hidden;
}
.embed-responsive{
	height: 300px;
}
.panel-body span{
	font-size: 5px;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#demo").BootSideMenu({
		  side:"left", // left or right
		  autoClose:true // auto close when page loads
		});
	});
	function GetQueryString(name)//获取地址栏参数
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
    function submitFile(){//打开文件的输入框
    	 $("#pfile").click();
    };
    function Formsubmit(ele){
    	 // 返回 KB，保留小数点后两位
    	if((ele.files[0].size / 1024).toFixed(2)>50){
    		alert("文件大小不能超过50kb");
    	}else{
    		$("#form1").submit();
    	}
    }
    
</script>
<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>

<div id="demo">
<!-- 左边实验提示 --> 
	<div class="panel panel-primary">
	  <div class="panel-heading">${request.Experiment.title}</div>
	  <div class="panel-body">
	    	<h5>实验说明:</h5>
	    	<p>${request.Experiment.expDesc}</p>
	  </div>
	</div>
	<div class="panel panel-primary">
	  <div class="panel-heading">提示</div>
	  <div class="panel-body">
	  	<span>1）点击flash仿真软件中的“保存实验”，得到实验文件；</span><br/>
		<span>2）点击右下方保存按钮保存实验文件；</span><br/>
		<span>3）可以下载已保存实验文件加载上次实验；</span><br/>
		<span>4）保存实验文件个数限制最多上传5个，超出自动删除时间最早文件</span><br/>
	  </div>
	</div>
	<div class="panel panel-primary">
	  <div class="panel-heading">我的实验</div>
	  <div class="panel-body">
	  	<div>
	  		<s:if test="#request.PoccfileList.size==0">
	    		<div class="NotExist">
	    			<h4 align="center">:(</h4>
			    	<p align="center">没有相关的存档</p>
		    	</div>
	    	</s:if>
	    	<s:else>
	  		<s:iterator value="#request.PoccfileList" var="poccfile" >   
	  			<div class="col-md-10">${poccfile.file_name}</div>
			    <div class="col-md-2"><a href="user/downloadPoccfile.html?poccFileid=${poccfile.id}"><span class="glyphicon glyphicon-save" aria-hidden="true"></span></a></div>
			</s:iterator>  
			</s:else>
	  	</div>
	  </div>
	</div>
<!-- 左边实验提示 ..-->
</div>  

<div class="Test_main">
<!-- 右边实验仿真软件 -->
<div class="col-md-12 right">
	<!-- 16:9 aspect ratio -->
	<div class="embed-responsive embed-responsive-4by3">
	  <embed class="embed-responsive-item" allowFullScreen="true" src="res/swf/pocc.swf"></embed>
	</div>
	<div class="btn-div col-md-12">
		<div class="col-md-offset-6">
		<form id="form1" action="user/savePoccfile.html"  enctype="multipart/form-data" method="post">
			<input onclick="submitFile()" class="btn btn-primary col-md-4 col-md-offset-5" type="button" value="保存">
			<input name="expID" type="hidden" value="${request.Experiment.id}"/>
			<input name="pocc" id="pfile" title="上传文件" type="file" onchange="Formsubmit(this)" />
		</form>
		</div>
	</div>
</div>
<!-- 右边实验仿真软件 ..-->
</div>
<script src="res/js/BootSideMenu.js"  type="text/javascript"></script>  
</body>
</html>
