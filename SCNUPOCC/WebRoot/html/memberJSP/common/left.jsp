<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<style>
.left-div{
	background-color: #171d1f;
	height:100% !important;
	overflow-y:auto;
	overflow-x:hidden;
}
.nav{
	height:100% !important;
}
.nav-sidebar li a{
	color:#7d868a;
	display: block;
	width:115%;
	min-width:210px;
	height:70px;
	margin-left:-13px;
	text-align:center;
    font-size:16px;
    font-family:"Lantinghei SC", "Open Sans", Arial, 
    "Hiragino Sans GB", "Microsoft YaHei", "STHeiti", 
    "WenQuanYi Micro Hei", SimSun, sans-serif;
    padding-top:25px;
}
.nav-sidebar li a:HOVER{
	color:#65c17a;
	background-color: #1F2527;
	border-left: 2px solid #65c17a;
}
.nav-sidebar li a:LINK{
	background-color: #1F2527;
}
.active{
	color:#65c17a !important;
	border-left: 1px solid #65c17a !important;
}
.nav-sidebar li span{
	display: block;
	float:left;
}
.second_li{
	background-color: #171d1f !important;
	min-width: 50px !important;
	height:50px !important;
	font-size:8px !important;
	display: block !important;
	text-align: center !important;
	text-decoration: none !important;
	padding-top:16px !important; 
	margin-left:3px !important;
}
.second_li:HOVER{
	min-width: 50px !important;
	height:50px !important;
	font-size:8px !important;
	display: block !important;
	text-align: center !important;
	text-decoration: none !important;
	color:#65c17a !important ;
	background-color: #1F2527 !important;
	border: 1px solid #65c17a !important;
}
</style>

<!--左边导航条-->
<div class="col-md-2 col-sm-4 left-div">
  <ul class="nav nav-sidebar">
    <li>
    	<a class="active" role="button" data-toggle="collapse" href="#collapse1" aria-expanded="false" aria-controls="collapseExample">
    	<span class='glyphicon glyphicon-stats'></span>实验用户管理
    	</a>
	    <div class="collapse" id="collapse1">
		    <a class="second_li">实验用户批量生成</a>
	        <a class="second_li">实验用户管理</a>
	        <a class="second_li">实验用户状态统计</a>
		</div>
    </li>
    <li><a role="button" data-toggle="collapse" href="#collapse2" aria-expanded="false" aria-controls="collapseExample">
    	<span class='glyphicon glyphicon-lock'></span>用户权限管理
    	</a>
        <div class="collapse" id="collapse2">
		    <a class="second_li">管理员用户管理</a>
		</div>
    </li>
    <li><a role="button" data-toggle="collapse" href="#collapse3" aria-expanded="false" aria-controls="collapseExample">
    	<span class='glyphicon glyphicon-pencil'></span>在线仿真实验管理
    	</a>
        <div class="collapse" id="collapse3">
		    <a class="second_li">查看在线实验用户的列表</a>
		</div>
    </li>
    <li><a role="button" data-toggle="collapse" href="#collapse4" aria-expanded="false" aria-controls="collapseExample">
   		<span class='glyphicon glyphicon-cog'></span>系统日志管理
   		</a>
   		 <div class="collapse" id="collapse4">
		    <a class="second_li">查看在线实验用户的列表</a>
		</div>
    </li>
  </ul>
</div>
<!-- 结束左边导航条 -->
