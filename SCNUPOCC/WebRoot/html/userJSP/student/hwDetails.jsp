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
	min-height:650px;
	padding-top:80px;
	padding-left:50px;
	padding-right:100px;
}
.leftpart {
	min-height: 300px;
	padding-left:50px;
	padding-right:30px;
}
.sidebar{
	max-height: 300px;
	border: 1px solid #ccc;
	background-color: white;
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
.sidebar li a:HOVER{
	color:#1F7BD8;
	background-color: white;
}

.rightpart{
	min-height:350px;
	max-height:500px;
	overflow:auto;
	padding:0;
	border: 1px solid #ccc;
	background-color:white;
}
.divider{
	margin-top:5px;
	margin-bottom: 5px;
}
.showDesc{
	padding:20px;
	min-height: 160px;
	background-color: #FBFCFC;
	overflow: auto;
}
.boxtitle{
	width:100%;
	height:60px;
	background-color: white;
	padding-top:13px;
	padding-left:15px;
	color:#777;
	border-bottom:1px solid #eee;
}
#File{
	display: none;
}
</style>
</head>
<script type="text/javascript">
	function changefile(){
		$("#File").click();
	}
	function changefileName(){
		$("#filename").removeAttr("href");
		$("#filename").html($("#File").val().split("\\").pop());
		$("#File").attr("name","hwfile");
	}
</script>
<body>
<!--导入头部导航条-->
<%@include file="header.jsp" %>
<div class="main">
<!-- 作业详情 -->
	<div class="col-md-10 col-md-offset-1">
		<div class="sidebar">
		  <div class="boxtitle"><h5><b>${request.homework.title}</b></h5></div>
		  <div class="showDesc">${request.homework.hwdesc}</div>
		  <hr class="divider" width="99%" align="center"/>
		  <div class="boxtitle"><h5><b>提交作业</b></h5></div>
		  <div class="showDesc">
		  <form action="student/submitHomework.html" enctype="multipart/form-data" method="post">
		  <input name="hwID" type="hidden" value="${request.homework.id}">
		  <textarea name="hwsdesc" class="form-control" rows="3" placeholder="请输入作业描述、反馈或建议（限300字以内）" 
		  maxlength="300"><s:if test="#request.hwSubmit!=null">${request.hwSubmit.hwsdesc}</s:if></textarea>
	  	 		 <hr class="divider" width="99%" align="center"/>
	  		    <div class="form-group">
			       <s:if test="#request.hwSubmit==null">
			       	   <input name="hwSubmitID" type="hidden" value="non-existent">
				       <label for="InputFile">上传作业文件</label>
				       <input name="hwfile" type="file" id="InputFile">
				       <p class="help-block">请上传.pocc结尾的实验文件</p>
			       </s:if>
			       <s:else>
			           <label for="InputFile">上传作业文件</label>
			           <input name="hwSubmitID" type="hidden" value="${request.hwSubmit.id}">
				       <p class="help-block">
				       	  <a id="filename" href="student/downloadHWFile.html?hwSubmitID=${request.hwSubmit.id}">${request.hwSubmit.filename}</a>
				          <input onclick="changefile()" type="button" class="btn btn-warning btn-xs" value="重新上传"/>
				       </p>
				       <input name="hwfile" onchange="changefileName()" type="file" id="File" />
			       </s:else>
			    </div>
			    <s:if test="#request.homework.state">
			   	   <input class="btn btn-success" type="submit" title="提交 ">
			    </s:if>
			    <s:else>
			       <input class="btn btn-success" disabled="disabled" type="submit" title="提交 ">
			    </s:else>
	  			
	  		</form>
		  </div> 
		</div>
	</div>
<!-- 作业详情  ..-->
</div>
<!--导入footer-->
<%@include file="footer.jsp" %>
</body>
</html>
