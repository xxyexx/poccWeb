<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<style type="text/css">
	.navbar-defalut {
		 font:15px/1.5 "Microsoft Yahei", 
   		"Hiragino Sans GB", Helvetica, 
   		"Helvetica Neue", "微软雅黑", Tahoma, 
   		 Arial, sans-serif;
		background-color: white !important;
		height:56px;
		width:100%;
		border-bottom: 3px solid #ddd; 
	}
	.navbar-toggle{
		background-color: #65c17a;
	}
	.navbar-nav li{
		background-color: white;
	}
	.icon-bar{
		color:white;
	}
	.navbar li a{
	  color:#777;
	}
	.navbar-header{
		height:55px;
		padding-top:0 0 0 0px;
	}
	.navbar-brand{
		height:30px;
		padding:10 0 0 0px;
		
	}
	.navbar li a:HOVER{
		background-color: white;
	}
	#nav-div{
		min-width:400px;
	}
	.header_container{
		width: 80% !important;
		min-width:700px !important;
		margin:0 auto;
	}
	.TouserCenter{
		min-width: 160px;
		text-align: center;
	}
	.navbar-right li .active:HOVER{
		background:#4DB849 !important;
		height:60px !important;
		color:white;
	}
}
</style>
<!--导航条-->
<div class="navbar navbar-defalut navbar-fixed-top" role="navigation" id="menu-nav">
 <div class="header_container" align="left" id="nav-div">
  <div class="navbar-header">
     　<!-- .navbar-toggle样式用于toggle收缩的内容，即nav-collapse collapse样式所在元素 -->
       <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
         <span class="sr-only">切换导航</span>
         <span class="icon-bar">—</span>
         <span class="icon-bar">—</span>
         <span class="icon-bar">—</span>
       </button>
       <!-- 确保无论是宽屏还是窄屏，navbar-brand都显示 ·品牌图标-->
       	<a href="userlogout.html" class="btn btn-sm btn-primary navbar-toggle" id="hidden-login-btn" role="button">[退出]</a>
		<a href="teacher/index.html" class="navbar-brand" id="brand-img">
		  <img alt="Brand" src="res/images/banner.png"  align="middle" width="150px">
		</a>
  </div>
  <!-- 屏幕宽度小于768px时，div.navbar-responsive-collapse容器里的内容都会隐藏，显示icon-bar图标，当点击icon-bar图标时，再展开。屏幕大于768px时，默认显示。 -->
  <div class="collapse navbar-collapse header" role="navigation">
    <ul class="nav navbar-nav navbar-right ">
   		 <li><a class="active"  href="teacher/index.html"><span class='glyphicon glyphicon-home' aria-hidden=true></span>&nbsp;&nbsp;首页</a></li>
     	 <li><a class="active"  href="user/experiment.html"><span class='glyphicon glyphicon-education' aria-hidden=true></span>&nbsp;&nbsp;实验</a></li>
     	 <li><a class="active"  href="teacher/homework.html"><span class='glyphicon glyphicon-pencil' aria-hidden=true></span>&nbsp;&nbsp;<span style="margin-left:-13px;margin-top:-15px;background-color: red" class="badge" >${session.UnfinHWNum}</span>作业</a></li>
     	 <li><a class="active"  href="user/mailbox.html"><span class='glyphicon glyphicon-envelope' aria-hidden=true></span>&nbsp;&nbsp;<span style="margin-left:-13px;margin-top:-15px;background-color: red" class="badge">${session.UnreadMsgNum}</span>信箱</a></li>
        <li class="dropdown">
           <a href="#" class="dropdown-toggle TouserCenter" data-toggle="dropdown"><img class="img-circle" alt="头像" src="user/showIcon.html" width="22px;" height="22px;">&nbsp;<s:property value="#session.User.acctName"/></a>
           <ul class="dropdown-menu" role="menu">
           	<li><a href="teacher/homepage.html"><span class='glyphicon glyphicon-user' aria-hidden=true></span>&nbsp;&nbsp;个人主页</a></li>
           	<li role="separator" class="divider"></li>
			<li><a href="user/settings.html"><span class='glyphicon glyphicon-cog' aria-hidden=true></span>&nbsp;&nbsp;设置</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="userlogout.html"><span class='glyphicon glyphicon-log-out' aria-hidden=true></span>&nbsp;&nbsp;退出</a></li>
           </ul>
         </li>
 	</ul>
  </div>
 </div>
</div>
<!-- 结束导航条 -->
