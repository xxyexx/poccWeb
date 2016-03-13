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
	min-height:300px;
	overflow-x: hidden;
}
.contain{
	padding-top:55px;
}
.container_center{
	width: 100%  !important;
	background-image: url("res/images/userLoginPic4.jpg");
	height:580px !important;
	background-size:100% 100%;
	margin-bottom: 0 !important;
	padding-top: 150px;
}
.options{
	min-height:700px;
	background-color: #FBFCFC;  /*#EBEAE9*/
	padding-left:60px;
	padding-right:60px;
	padding-top:0px;
	border-bottom: 1px solid #e2e5e7;
}

.container_center_pic{
	width: 40% !important;
	height:300px;
	margin:0 auto;
}
.thumbnail{
	background-color: #FBFCFC;
	text-align: center;
	border: 0px;
}
.thumbnail img{
	width: 160px;
}
.thumbnail a{
	text-decoration: none;
	color: #11364c;
}
.thumbnail a:HOVER{
	color:black;
}
.thumbnail a:HOVER img{
	 width: 155px;
}
h2{
	margin-top:0px;
	height:130px;
	padding-top:60px;
	background-color: #FBFCFC;
	text-align: center;
	color:#11364c;
	font-weight: bolder;
}
.introduction{
	height:550px;
	background-color:#FFF;
	padding-top:100px;
	padding-left:30px;
	padding-right:70px;
	border-top: 1px solid #e2e5e7;
	border-bottom: 1px solid #e2e5e7;
}
.introduction h3{
	color:#677D8B;
	font-family:"Open Sans",Arial, "Hiragino Sans GB", "Microsoft YaHei",
				 "STHeiti", "WenQuanYi Micro Hei", SimSun, sans-serif;
	font-weight: bolder;
}
.introduction p{
	color:#677D8B;
	font-size: 17px;
	font-family:"Open Sans",Arial, "Hiragino Sans GB", "Microsoft YaHei",
			 "STHeiti", "WenQuanYi Micro Hei", SimSun, sans-serif;
	margin-top: 20px;
}
</style>
<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<div class="contain">
<div class="container_center">
	<div class="container_center_pic">
		<img alt="计算机组成原理实验平台" src="res/images/stuIndex_CenterPic.png" width="100%;" height="100%;">
	</div>
</div>
<!-- 仿真软件系统简介 -->
<div class="introduction">
	<div class="media">
	  <div class="media-left media-middle">
	    <a >
	      <img class="media-object" width="550px;"  src="res/images/introduction2.jpg" alt="仿真软件系统">
	    </a>
	  </div>
	  <div class="media-body">
	    <h3 class="media-heading">仿真系统简介</h3>
	    <p>模拟“TEC—4计算机组成原理实验仪”所提供的实验过程，除 “用户自选器件实验区”外，其它实验都能模拟。</p>
	    <p>通过直观的实验交互操作，将抽象、复杂的计算机内部运行流程，以可视化方式动态呈现，使整个实验过程直观、易懂。</p>
	    <p>不受资金、时间、实验室环境等因素限制。</p>
	    <p>如果实验仪实验与仿真系统实验相结合，教学效果一流。</p>
	  </div>
	</div>
</div>
<!-- 仿真软件系统简介  ..-->
<!-- 功能选择 -->
<div class="options">
  <h2>功能选择</h2>
  <div class="row">
  <div class="col-sm-4 col-md-4">
    <div class="thumbnail">
      <a href="student/homepage.html"><img class="img-responsive" src="res/images/thumbnail_Pic6.png" alt="主页"></a>
      <div class="caption">
        <h3><a href="student/homepage.html">个人主页</a></h3>
        </div>
    </div>
  </div>
  <div class="col-sm-4 col-md-4">
    <div class="thumbnail">
      <a href="student/experiment.html"><img class="img-responsive" src="res/images/thumbnail_Pic4.png" alt="实验"></a>
      <div class="caption">
        <h3><a href="student/experiment.html">在线实验</a></h3>
        </div>
    </div>
  </div>
  <div class="col-sm-4 col-md-4">
    <div class="thumbnail">
      <a href="student/mailbox.html"><img class="img-responsive" src="res/images/thumbnail_Pic5.png" alt="信箱"></a>
      <div class="caption">
        <h3><a href="student/mailbox.html">站内信箱</a></h3>
        </div>
    </div>
  </div>
</div>

<div class="row">
   <div class="col-sm-4 col-md-4">
    <div class="thumbnail">
      <a href="student/homework.html"><img class="img-responsive" src="res/images/thumbnail_Pic7.png" alt="作业"></a>
      <div class="caption">
        <h3><a href="student/homework.html">我的作业</a></h3>
        </div>
    </div>
  </div>
     <div class="col-sm-4 col-md-4">
    <div class="thumbnail">
      <a ><img class="img-responsive" src="res/images/thumbnail_Pic3.png" alt="资源"></a>
      <div class="caption">
        <h3><a >学习资源</a></h3>
        </div>
    </div>
  </div>
     <div class="col-sm-4 col-md-4">
    <div class="thumbnail">
      <a href="student/settings.html"><img class="img-responsive" src="res/images/thumbnail_Pic8.png" alt="设置"></a>
      <div class="caption">
        <h3><a href="student/settings.html">设置</a></h3>
        </div>
    </div>
  </div>
</div> 
  
</div>  
<!-- 功能选择.. -->
<!--导入footer-->
<%@include file="footer.jsp" %>
</div>
</body>
</html>
