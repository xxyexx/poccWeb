<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>计算机组成原理实验平台</title>
    <link rel="icon" href="res/images/icon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<!-- DataTables CSS -->
	<link rel="stylesheet" type="text/css" href="res/DataTables-1.10.7/css/jquery.dataTables.css">
	<!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="res/DataTables-1.10.7/js/jquery.dataTables.min.js"></script>
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
	min-height:620px;
	padding-top:80px;
	padding-left:50px;
	padding-right:100px;
	padding-bottom:50px;
	overflow: hidden;
}
.leftpart {
	min-height: 300px;
	padding-left:50px;
	padding-right:30px;
}
.sidebar{
	max-height: 300px;
	border: 1px solid #ccc;
}
.sidebar li{
	width: 100%;
	border-top: 1px solid #eee;
	background-color: white;
}
li.active>a{
	color: #337ab7 !important;
}
.sidebar li a{
	height:60px;
	color: #555;
	text-align:center;
	padding-top:20px;
	border:none !important;
	font-weight:500;
}
.boxtitle{
	width:100%;
	height:60px;
	background-color: #F8F9FA;
	padding-top:13px;
	padding-left:15px;
	color:#777;
	border-bottom:1px solid #eee;
}
.sidebar li a:HOVER{
	color:#1F7BD8;
	background-color: white;
}

.rightpart{
	min-height:350px;
	max-height:800px;
	padding:0;
	border: 1px solid #ccc;
	background-color:white;
}
.divider{
	margin-top:5px;
	margin-bottom: 5px;
}
.panel-group{
	max-height:200px;
	overflow: auto;
}
#mess{
	text-align:left;
	direction: rtl;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('.collapse').collapse('hide');
		hideDiv();
	    $('.classTable').dataTable( {
	             "language": {
	                 "lengthMenu": "每页 _MENU_ 条记录",
	                 "zeroRecords": "没有找到记录",
	                 "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
	                 "infoEmpty": "无记录",
	                 "infoFiltered": "(从 _MAX_ 条记录过滤)",
	                  "paginate": {
				        "first":      "首页",
				        "last":       "尾页",
				        "next":       "下页",
				        "previous":   "上页"
				     },
				     "processing":     "加载中...",
   					 "search":         "搜索:",
	             }
	         } );
	 } );
	 //添加一个收件人
	function addRece(id,name){
		var names = $("#mess").val().split(";");
		var r = true;
		if(names.length>10){
			r=confirm("收信人超过10个(建议选择公告群发),是否继续添加？");
		}
		if(r==true){
			var flag = true;
			for(var i=0;i<names.length;i++){
				if(names[i]==name){flag = false;}
			}
			if(flag==true){
				$("#mess").val($("#mess").val()+";"+name);
				$("#rece").val($("#rece").val()+id+";");
			}
		}
	}
	//删除一个收件人
	function rmRece(){
		var names = $("#mess").val().split(";");
		var ids = $("#rece").val().split(";");
		var n ="", id ="";
		if(names.length>0){
			for(var i=0;i<names.length-1;i++){
				if(names[i]!=""){
					n = n+ ";"+names[i];
				}
			}
			for(var i=0;i<ids.length-2;i++){	
				id = id + ids[i] + ";"; 
			}
			$("#mess").val(n);
			$("#rece").val(id);
		}
	}
	//隐藏不需要的收件人列表div
	function hideDiv(){
	    var t = $("#type option:selected").val();
		if(t=='0'){//消息
			$("#people").css("display","block");
			$("#group").css("display","none");
		}else{//公告
			$("#people").css("display","none");
			$("#group").css("display","block");
		}
	}
	
</script>
</head>
  
<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<div class="main">
<!-- 站内邮箱管理模块 -->
<div class="leftpart col-md-3">
	<div class="sidebar">
	  <div class="boxtitle"><h4><b>信箱管理</b></h4></div>
	  <!-- Nav tabs -->
	  <ul id="homeworkTabs" class="nav nav-tabs" role="tablist">
	    <li role="presentation" class="active"><a href="#mailunread" aria-controls="mailunread" role="tab" data-toggle="tab">未读消息</a></li>
	    <li role="presentation" class=""><a href="#mailread" aria-controls="mailread" role="tab" data-toggle="tab">已读消息</a></li>
	    <li role="presentation" class=""><a href="#mailsent" aria-controls="mailsent" role="tab" data-toggle="tab">已发消息</a></li>
	    <li role="presentation" class=""><a href="#sendmail" aria-controls="sendmail" role="tab" data-toggle="tab">发送消息</a></li>
	  </ul>
	</div>
</div>
<div class="rightpart col-md-9">
  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="mailunread">
    	<div class="boxtitle"><h4><b>未读信息列表</b></h4></div>
    	<!-- 未读信息列表 -->
    	<s:if test="#request.UnreadMsgList.size==0">
    		<div class="NotExist">
    			<h1 align="center">:)</h1>
		    	<p align="center">没有相关消息</p>
		    	<hr class="divider" width="99%" align="center"/>
	    	</div>
    	</s:if>
    	<s:else>
		<table class="table table-hover">
			<tr>
			    <th>标题</th>
			    <th>发送时间</th>
			    <th>发送人</th>
			    <th>操作</th>
			</tr>
			<s:iterator value="#request.UnreadMsgList" var="siteMsg">
				<tr>
				    <td><a target="blank" href="user/readMsg.html?MsgID=${siteMsg.id}"><s:property value="#siteMsg.title"/></a></td>
				    <td><s:property value="#siteMsg.sendTimeFormat"/></td>
				    <td><s:property value="#siteMsg.sender"/></td>
				    <td>
				    	<a target="blank" href="user/readMsg.html?MsgID=${siteMsg.id}" class="btn btn-xs btn-info">阅读</a>
				    </td>
				</tr>
			</s:iterator>
		</table>
		<hr class="divider" width="99%" align="center"/>
		
		</s:else>
		<!-- 未读信息列表END -->
	</div>
    <div role="tabpanel" class="tab-pane" id="mailread">
    	 <div class="boxtitle"><h4><b>已读信息列表</b></h4></div>
    	<!-- 已读消息列表 -->
    	<s:if test="#request.ReadMsgList.size==0">
	    	<div class="NotExist">
		    	<h1 align="center">:(</h1>
		    	<p align="center">没有相关消息</p>
		    	<hr class="divider" width="99%" align="center"/>
	    	</div>
    	</s:if>
    	<s:else>
		<table class="table table-hover">
			<tr>
			    <th>标题</th>
			    <th>发送时间</th>
			    <th>发送人</th>
			    <th>操作</th>
			</tr>
			<s:iterator value="#request.ReadMsgList" var="siteMsg">
				<tr>
				    <td><a target="blank" href="user/readMsg.html?MsgID=${siteMsg.id}"><s:property value="#siteMsg.title"/></a></td>
				    <td><s:property value="#siteMsg.sendTimeFormat"/></td>
				    <td><s:property value="#siteMsg.sender"/></td>
				    <td><a href="user/deleteMsg.html?MsgID=${siteMsg.id}" class="btn btn-xs btn-warning">删除</a></td>
				</tr>
			</s:iterator>
		</table>
		</s:else>
		<hr class="divider" width="99%" align="center"/>
		<!-- 已读消息列表 END -->
	</div>
    <div role="tabpanel" class="tab-pane" id="mailsent">
    	 <div class="boxtitle"><h4><b>已发信息列表</b></h4></div>
    	<!-- 已发消息 -->
    	<s:if test="#request.SentMsgList.size==0">
	    	<div class="NotExist">
		    	<h1 align="center">:(</h1>
		    	<p align="center">没有相关消息</p>
		    	<hr class="divider" width="95%" align="center"/>
	    	</div>
    	</s:if>
    	<s:else>
		<table class="table table-hover">
			<tr>
			    <th>标题</th>
			    <th>发送时间</th>
			    <th>发信人</th>
			    <th>收信人</th>
			    <th>操作</th>
			</tr>
			<s:iterator value="#request.SentMsgList" var="siteMsg">
				<tr>
				    <td><a target="blank" href="user/readMsg.html?MsgID=${siteMsg.id}"><s:property value="#siteMsg.title"/></a></td>
				    <td><s:property value="#siteMsg.sendTimeFormat"/></td>
				    <td><s:property value="#siteMsg.sender"/></td>
				    <td><s:property value="#siteMsg.receiver"/></td>
				    <td>
						<a target="blank" href="user/readMsg.html?MsgID=${siteMsg.id}" class="btn btn-xs btn-info">阅读</a>
						<a href="user/deleteMsg.html?MsgID=${siteMsg.id}" class="btn btn-xs btn-warning">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		</s:else>
		<hr class="divider" width="99%" align="center"/>
		<!-- 已发信息列表 END -->
	</div>
   <!-- 发送信息 -->
   <div role="tabpanel" class="tab-pane" id="sendmail">
      <form action="user/sendMsg.html" class="form-horizontal" method="post">
    	<div class="boxtitle"><h4><b>发送信息</b></h4></div><br>
   		<div class="form-group col-md-12">
    	<label for="title" class="col-sm-3 control-label">输入标题</label>
    	<div class="col-sm-6">
     	     <input name="siteMsg.title" type="text" required="required" class="form-control" id="title" placeholder="标题">
   	    </div>
  	    </div><br>
	    <div class="form-group col-md-12">
    	<label for="text" class="col-sm-3 control-label">输入消息正文</label>
    	<div class="col-sm-6">
     	    <textarea name="siteMsg.message" rows="3" required="required" class="form-control" placeholder="请输入消息正文" maxlength="300"></textarea>
   	    </div>
  	    </div><br>
	    <div class="form-group col-md-12">
    	<label for="type" class="col-sm-3 control-label">消息类型</label>
    	<div class="col-sm-6">
		<select onchange="hideDiv()" name="ispublic" id="type" class="form-control">
			<option value="0" selected="selected">消息</option>
			<option value="1">公告</option>
		</select>
		</div>
  	    </div><br>
  	    <div id="people">
	    <div class="form-group col-md-12">
    	<label for="mess" class="col-sm-3 control-label">接收者</label>
    	<div class="col-sm-6">
			<div class="col-sm-8" style="padding-left:0px;">
				<input class="form-control" type="text" required="required" onfocus = "javascript:this.blur()" id="mess" placeholder="请点击添加收信人" >
				<input name="receivers" type="hidden"  id="rece" >
			</div>
			<a onclick="rmRece()" class="btn btn-danger col-sm-1" role="button"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span></a>
			<a class="btn btn-primary col-sm-3" role="button" data-toggle="collapse" href="#receivers" aria-expanded="false" aria-controls="receivers">添加收信人</a>
		</div>
  	    </div><br>
	    <div class="form-group col-md-12 collapse" id="receivers">
    	<label for="message" class="col-sm-3 control-label"></label>
    	<div class="col-sm-6">
    	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="false">
    		<s:iterator value="#request.receMap" var="column" status="s">
    		<div class="panel panel-default">
    		    <div class="panel-heading" role="tab" id="heading${s.index}">
			      <h4 class="panel-title">
			        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${s.index}" aria-expanded="false" aria-controls="collapse${s.index}">
			         	 <s:property value="key"/>
			        </a>
			      </h4>
			    </div>
			     <div id="collapse${s.index}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading${s.index}">
				      <div class="panel-body">
				      	<s:iterator value="#column.value" var="user">
			         	    <a onclick="addRece('${user.id}','${user.acctName}')">${user.acctName}</a>
			         	</s:iterator>
				      </div>
				 </div>
    		</div>
    		</s:iterator>
    	</div>
		</div>
  	    </div><br>
  	    </div>
  	    <!-- 群发 -->
	    <div class="form-group col-md-12" id="group">
    	<label for="class" class="col-sm-3 control-label">接收群组</label>
    	<div class="col-sm-6">
		<s:select name="recvgroup" list="#request.ClassMap" listKey="key" listValue="value" id="class" cssClass="form-control" multiple="true" SIZE="3"></s:select>
		</div>
  	    </div><br>		
  	   	<div class="form-group col-md-12">
  	   	<label for="saveinfo" class="col-sm-3 control-label"></label>
    	<div class="col-sm-6">
     	 	<input type="submit" class="btn btn-primary col-sm-12" id="saveinfo" value="发&nbsp;&nbsp;送">
   	    </div> 
  	    </div><hr>	  	 
  	    </form>	
		<!-- 发送信息 END -->
	</div>
  </div>	
</div>

<!-- 站内邮箱管理.. -->
</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
