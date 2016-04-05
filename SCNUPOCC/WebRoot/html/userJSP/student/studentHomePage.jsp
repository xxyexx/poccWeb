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
	background-color: #EBEAE9;
	font-family:"Open Sans",Arial, "Hiragino Sans GB", "Microsoft YaHei",
			 	"STHeiti", "WenQuanYi Micro Hei", SimSun, sans-serif;
}
.main{
	width:1170px;
	height:950px;
	margin:0 auto;
}
.module1{
	height:280px;
	margin-top:80px;
	background-color: white;
}
.userinfo{
	height:280px;
	padding-top:20px;
}
.announce{
	padding:0;
}
.panel{
	height:280px;
}
.panel-body{
	height:190px;
	padding-left: 10px;
	padding-right: 20px;
	font-size: 15px;
}
.panel-body li{
	margin-top:5px;
}
.panel-body p{
	padding-left: 35px;
}
.panel-body span{
	float:right;
}
.panel-body a{
	color: #202020;
}
.viewmore{
	float:right;
	margin-right:20px;
	color: #202020;
}
.panel a:HOVER{
	color:#64ADBC;
	text-decoration: none;
}
.media-body cite{
	font: 8px/1.0em Arial,Microsoft Yahei, 'Simsun', sans-serif;
	color: #999;
}
.media-body p{
	line-height: 10px;
}
.media-body a{
	color:#202020;
}
.media-body a:HOVER{
	color: #64ADBC;
}
.module1 hr{
	margin-top:8px;
	margin-bottom: 8px;
}
.list-group-item{
	padding-top: 1px;
	padding-bottom: 1px;
	padding-left:5px;
	padding-right:0px;
	font: 14px/1.5em Arial,Microsoft Yahei, 'Simsun', sans-serif;
	color: #666 !important;
}
.list-group-item .badge{
	margin-right:10px;
	background-color:#3879D9;
}
.module2{
	height:280px;
	margin-top:30px;
}
.module2 hr{
    margin-top: 8px;
    margin-bottom: 8px;
}
.part1{
	padding:0px 10px 0px 0px;
}
.part2{
	padding:0px 0px 10px 0px;
}
</style>

<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<!-- 主页 -->
<div class="main">
	<div class="module1">
		<div class="userinfo col-md-3">
			<div class="media">
			  <div class="media-left">
			    <a href="user/settings.html?select=1">
			      <img src="user/showIcon.html" style="width: 80px;height:80px; border: 1px solid #DDD;"  class="media-object" alt="点击更改头像">
			    </a>
			  </div>
			  <div class="media-body">
			    <h4 class="media-heading">${session.User.acctName}</h4>
			    <p>
			    	<cite>帐号：</cite>
					<span id="userAccount"><a href="user/settings.html?select=0">${session.User.loginID}</a></span>
			    </p>
			    <p>
			    	<cite>单位：</cite>
			    	<span id="acctTag">${session.User.acctTag}</span>
			    	<span id="acctType">&nbsp;&nbsp;${session.User.acctType}</span>
			    </p>
			    <p><cite>手机：</cite><span id="userPhone"><a href="user/settings.html?select=0">${session.User.mobile}</a></span></p>
			  </div>
			</div>
		<hr/>
		<div class="list-group">
		  <a class="list-group-item">帐号有效期:<s:date name="#session.User.rentDate1" format="yyyy-MM-dd"/>~<s:date name="#session.User.rentDate2" format="yyyy-MM-dd"/></a>
		  <a class="list-group-item">上次登录时间:${session.lastLoginTime}</a>
		  <a class="list-group-item">上次登录IP:${session.lastLoginIP}</a>
		  <a href="user/mailbox.html" class="list-group-item">未读消息:<span class="badge" >${session.UnreadMsgNum}</span></a>
		  <a href="student/homework.html?select=0" class="list-group-item">未交作业:<span class="badge" >${session.UnfinHWNum}</span></a>
		  <a href="student/homework.html?select=1" class="list-group-item">最近作业已批改:<span class="badge" >${request.CheckedHWnum}</span></a>
		</div>
		
		</div><!-- end//userinfo -->
		<div class="announce col-md-9">
			<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">消息 公告</h3>
			  </div>
			  <div class="panel-body">
			  		<s:if test="#request.MsgList.size==0">
			  			<h1 align="center">:)</h1>
			    		<p align="center">没有公告</p>
			  		</s:if>
			  		<s:else>
				   <div class="col-md-6">
				   		<ul class="msg">
				   		<s:set name="msgMax" value="#request.MsgList.size"></s:set>
				   		<s:if test="#request.MsgList.size>6"><!-- 设置一边最多6个 -->
				   			<s:set name="msgMax" value="6"></s:set>
				   		</s:if>
				   		<s:iterator value="#request.MsgList" var="siteMsg" begin="0" end="#msgMax-1">
					    	<li>
					    		<a target="blank" href="user/readMsg.html?MsgID=${siteMsg.id}">${siteMsg.title}</a>
					    		<span>${siteMsg.sendTimeFormat}</span>
					    	</li>
					    </s:iterator>
				    	</ul>
				   </div>	
				   <div class="col-md-6">
				  		<ul class="msg">
				  		<s:if test="#msgMax==6">
					    <s:iterator value="#request.MsgList" var="siteMsg" begin="#msgMax" end="#request.MsgList.size-1">
					    	<li>
					    		<a target="blank" href="user/readMsg.html?MsgID=${siteMsg.id}">${siteMsg.title}</a>
					    		<span>${siteMsg.sendTimeFormat}</span>
					    	</li>
					    </s:iterator>
					    </s:if>
				    	</ul>
				   </div>
				   </s:else>
			  </div>
			  <hr/>
			  <a class="viewmore" href="user/mailbox.html">查看更多</a>
			</div>
		</div>
	</div><!-- end//module1 -->
	
	<div class="module2">
		<div class="part1 col-md-6">
			<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">作业</h3>
			  </div>
			  <div class="panel-body">
			  		<s:if test="#request.NewHWList.size==0">
			  			<h1 align="center">:)</h1>
			    		<p align="center">没有作业</p><hr/>
			  		</s:if>
			  		<s:else>
				  		<p>标题<span>截止时间</span></p><hr/>
			    	 	<ul class="HWList"> 
			    	 	<s:iterator value="#request.NewHWList" var="homework">
						  <li>
						  	  <a href="student/hwDetails.html?hwID=${homework.id}">${homework.title}</a>
						      <span>${homework.closeDateFormat}</span>
						  </li>
						</s:iterator>
						</ul>
				    </s:else>	
			  </div>
			  <hr/>
			  <a class="viewmore" href="student/homework.html">查看更多</a>
			</div>
		</div>
		<div class="part2 col-md-6">
			<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">flash仿真实验项目</h3>
			  </div>
			  <div class="panel-body">
			    	<s:if test="#request.ExpList.size==0">
			  			<h1 align="center">:)</h1>
			    		<p align="center">没有相关的信息</p><hr/>
			  		</s:if>
			  		<s:else>
			    	 	<ul class="HWList"> 
			    	 	<s:iterator value="#request.ExpList" var="experiment">
						  <li>
						  	  <a href="user/inExperiment.html?id=${experiment.id}">${experiment.title}</a>
						  </li>
						</s:iterator>
						</ul>
				    </s:else>
			  </div>
			  <hr/>
			  <a class="viewmore" href="user/experiment.html">查看更多</a>
			</div>
		</div>
	</div>
	
	<div class="module2">
		<div class="part1 col-md-6">
			<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">资料介绍</h3>
			  </div>
			  <div class="panel-body">
			    	资料列表
			  </div>
			  <hr/>
			  <a class="viewmore" href="##">查看更多</a>
			</div>
		</div>
		<div class="part2 col-md-6">
			<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">友情链接</h3>
			  </div>
			  <div class="panel-body">
			    	友情链接列表
			  </div>
			  <hr/>
			  <a class="viewmore" href="##">查看更多</a>
			</div>
		</div>
	</div>
</div>
<!-- 主页 -->
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
