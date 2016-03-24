<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>华南师范大学计算机组成原理实验平台</title>
    <link rel="icon" href="res/images/icon.png">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="华南师范大学,实验平台,计算机组成原理">
	<meta http-equiv="description" content="计算机组成原理实验平台">
	<!-- Bootstrap --> 
	<script src="res/js/jquery.min.js" type="text/javascript"></script>
	<script src="res/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="res/bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<!-- Bootstrap..-->
	<link rel="stylesheet" type="text/css" href="res/css/userCSS/common/userLogin.css">
  </head>
  <body>
  <div class="main">
	<header>
		<div class="container">
			<img alt="华南师范大学计算机组成原理实验平台" src="res/images/banner.png" width="217" height="48">
		</div>
	</header>
	<form method="post" action="userlogin.action">
		<div class="row">
			<input class="form-control" type="text" id="account" name="account" placeholder="账号" required="required"/>
			<span class="tips">账号不存在</span>
		</div>
		<div class="row">
			<input class="form-control" type="password" id="password" name="password" placeholder="密码" required="required"/>
			<span class="tips">密码错误</span>
		</div>
		<div class="row">
			<%--极验验证--%>
			<script src="http://api.geetest.com/get.php"></script>
			<div id="div_id_embed"></div>
			<img src="securityCodeImage.action" id="Verify"  style="cursor:pointer;" alt="看不清，换一张" onclick="verifyChange()"/> 
			<input class="form-control" type="text" id="securityCode" name="securityCode" placeholder="验证码"/>
			<span class="tips">验证码错误</span>
			<%--极验验证..--%>
			<script type="text/javascript">
				var data = null;//验证码
				var flag = false;//极验验证,true:自定义
				$(function(){
					$(":input").focus(function(){
			             resetCss();
			        });
		        	$(".form-control").focus(function(){//输入框聚焦
						$(this).css("background-color","white");
						$(this).css("color","black");
					});
					$(".form-control").blur(function(){//输入框失焦
						if($(this).val()==""){
							$(this).css("background-color","transparent");
						}
					});
					
				});
			    //点击图片更换验证码  
		    	function verifyChange(){  
		            $("#Verify").attr("src","securityCodeImage.action?timestamp="+new Date().getTime());  
		        };
				function resetCss(){
					$(":input").css("border-color","");//清除边框样式
					$(".tips").css("visibility","hidden");
				}
				//get  geetest server status, use the failback solution
				$.ajax({
					url : "startCaptcha.action",
					type : "get",
					dataType : 'JSON',
					success : function(result) {
						if (result.success) {
							//隐藏自定义验证码
							$("#Verify").css("display","none");
							$("#securityCode").css("display","none");
							//1. use geetest capthca
							window.gt_captcha_obj = new window.Geetest(
							{
								gt : result.gt,
								challenge : result.challenge,
								product : 'float'
							});
							gt_captcha_obj.appendTo("#div_id_embed");
							$("#gt-msg-component").show();
							//Ajax request demo,if you use submit form ,then ignore it 
							gt_captcha_obj.onError(function() {
								gt_captcha_obj.refresh();
							});
							flag = false;
						}else{
							//failback :use your own captcha template
							//Geetest Server is down,Please use your own captcha system	in your web page
							//or use the simple geetest failback solution
							//$("#div_id_embed").html('failback:gt-server is down ,please use your own captcha front');
							$("#gt-msg-component").hide();
							//document.write('gt-server is down ,please use your own captcha')
							//显示自定义验证码
							$("#Verify").css("display","block");
							$("#securityCode").css("display","block");
							flag = true;
						}
					}
				});
				//Form Submit
				function formSubmit(){
					$.ajax({
						url : "userlogin.html",
						type : "post",
						data : {
							account:$("#account").val(),
							password:$("#password").val(),
						},
						success : function(data) {
							if(data.result=="inexistence"){//账号不存在
								$(".tips:eq(0)").css("visibility","visible");
								$("#account").css("border-color","red");
							}else if(data.result=="wrongPassword"){//密码错误
								$(".tips:eq(1)").css("visibility","visible");
								$("#password").css("border-color","red");
							}else{
								window.location.href=data.userType+"index.html";
							}
						}
					});
				};
				//Ajax Check Verification code
				function geetest_ajax_results() {
					if(flag==true){
						data = {securityCode:$("#securityCode").val().trim()};
					}else{
						data = gt_captcha_obj.getValidate();
					}
					$.ajax({
						url : "verifyLogin.action",
						type : "post",
						data : data,
						success : function(sdk_result) {
							if(sdk_result.result=="success"){
								$(".tips:eq(2)").css("visibility","hidden");
								formSubmit();//login
							}else if(sdk_result.result=="fail"){//验证码错误提示
								$(".tips:eq(2)").css("visibility","visible");
							}else if(sdk_result=="forbidden"){
								alert("验证码错误:被判定为机器人,请重新验证。");
							}
							//refresh
							verifyChange();
							gt_captcha_obj.refresh();
						}
					});
				};
			
			</script>
		</div>
		
		<div class="row-box">
			<span><input type="checkbox">下次自动登录</span>
			<a href="##">忘记密码？</a>
		</div>	
		<div class="row">
			<input class="btn btn-success" type="button" onclick="geetest_ajax_results()" value="登&nbsp;&nbsp;录" id="submit-button" />
		</div>
	</form>
	</div><!-- end main -->
  </body>
</html>
