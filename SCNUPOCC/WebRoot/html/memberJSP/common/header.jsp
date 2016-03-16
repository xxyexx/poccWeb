<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<style type="text/css">
	.navbar-defalut {
		 font:15px/1.5 "Microsoft Yahei", 
   		"Hiragino Sans GB", Helvetica, 
   		"Helvetica Neue", "微软雅黑", Tahoma, 
   		 Arial, sans-serif;
		background-color: white;
		height:55px;
		padding-left:20px;
		padding-right:40px;
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
		color: #555;
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
	.container{
		width: 100% !important;
	}
</style>

<!--导航条-->
<div class="navbar navbar-defalut navbar-fixed-top" role="navigation" id="menu-nav">
 <div class="container " align="left" id="nav-div">
  <div class="navbar-header">
     　<!-- .navbar-toggle样式用于toggle收缩的内容，即nav-collapse collapse样式所在元素 -->
       <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
         <span class="sr-only">切换导航</span>
         <span class="icon-bar">—</span>
         <span class="icon-bar">—</span>
         <span class="icon-bar">—</span>
       </button>
       <!-- 确保无论是宽屏还是窄屏，navbar-brand都显示 ·品牌图标-->
       	<a class="btn btn-sm btn-primary navbar-toggle" id="hidden-login-btn" role="button">[退出]</a>
		<a class="navbar-brand" id="brand-img">
		  <img alt="Brand" src="res/images/banner.png"  align="middle" width="150px">
		</a>
  </div>
  <!-- 屏幕宽度小于768px时，div.navbar-responsive-collapse容器里的内容都会隐藏，显示icon-bar图标，当点击icon-bar图标时，再展开。屏幕大于768px时，默认显示。 -->
  <div class="collapse navbar-collapse header" role="navigation">
    <ul class="nav navbar-nav navbar-right ">
     	 <li><a class='glyphicon glyphicon-info-sign' aria-hidden=true></a></li>
        <li class="dropdown">
           <a href="#" class="dropdown-toggle" data-toggle="dropdown">
           <span class='glyphicon glyphicon-user' aria-hidden=true></span>&nbsp;&nbsp;欢迎您：<s:property value="#session.Role.user_account"></s:property><span class="caret"></span></a>
           <ul class="dropdown-menu" role="menu">
           	<li><a >当前用户权限：<s:property value="#session.Role.role"/></a></li>
			<li><a ></a></li>
			<li><a class="btn btn-sm btn-success" style="width: 80px;margin-left:20px;">退出</a></li>
           </ul>
         </li>
 	</ul>
  </div>
 </div>
</div>
<!-- 结束导航条 -->
