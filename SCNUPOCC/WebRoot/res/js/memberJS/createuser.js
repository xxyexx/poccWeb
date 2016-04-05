//初始化页面
	window.onload=function(){		
		$('.time').datetimepicker({
			format: 'yyyy/mm',  
	         weekStart: 1,  
	         autoclose: true,  
	         startView: 3,  
	         minView: 3,  
	         forceParse: false,  
	         language: 'zh-CN' 
		});	
	}

//根据省份获取学校列表
	function changeSchool(){
		var provinceID = $("#province option:selected").val();
//		alert("createuser.js");
		if(provinceID!="-1"){
			//通过省份ID获取学校数据
			$.ajax({
				url : "member/provincegetSchool.html",
				type :"post",
				data :{provinceID:provinceID},
				success : function(data) {
					setSchoolList(data);
					changeDept();
				}
			});
		}else{
			$("#school").empty();
			$("#school").append("<option value='-1'>--</option>");
		}
	}
	
	//设置学校列表
	function setSchoolList(schoolList){
		$("#school").empty();
//		if(getJsonLength(schoolList)!=0){
			for(var schoolName in schoolList){				
				$("#school").append("<option value='"+schoolName+"'>"+schoolList[schoolName]+"</option>");
			}
//		}else{
			$("#school").append("<option value='-1'>默认</option>");
//		}
	}
	
	//根据学校获取学院列表
	function changeDept(){
		var schoolID = $("#school option:selected").val();
			//通过学校名获取学校学院数据
			$.ajax({
				url : "member/provincegetDept.html",
				type : "post",
				data : {schoolID:schoolID},
				success : function(data){
					setDeptList(data);
				}
			});
	}
	
	//设置学院列表
	function setDeptList(deptList){
		$("#dept").empty();
		if (getJsonLength(deptList)>0){
			for(var deptID in deptList){
				$("#dept").append("<option value='"+deptID+"'>"+deptList[deptID]+"</option>");
			}
		}else
			$("#dept").append("<option value='-1'>不限</option>");
	}
	
	//计算json长度
	function getJsonLength(jsonData){
		var jsonLength = 0;
		for(var item in jsonData){
			jsonLength++;
		}
		return jsonLength;
	}
	
	//检查开始序号
	function checkFirst(){
		
	}
	
	//全部清空
	function cleanAll(){
	}
	
	//确认所有信息并提交
	function formSubmit(){
		$("form").attr("action","member/userbatchcreate.html");
		$("form").submit();
	}
	
/*************************旧脚本************************/
	
	//step1 账号前缀
	/*function funStep1(){
		var v=document.getElementById("step1").value;
		var reg=/^[a-zA-Z0-9_]{5,8}$/;
		var tip=document.getElementById("tip1");
		tip.style.color="red";
		if (v==""){ tip.innerHTML=""; return false;}
		if (reg.test(v)){
			tip.innerHTML="";
			return true;
		}else{
			tip.innerHTML="请输入5~8个字符，可包含字母、数字或下划线";
			return false;
		}
	}*/
	
	//step1 账号前缀
	function funStep1(){
		//var v=document.getElementById("step1").value;
		//alert("funStep1");
		var v = $("#step1");
		v.val($("#academy").find("option:selected").val());
		//var prefix = $("#academy").text().split("_")[2];
		//alert(prefix);
		return true;
	}
	
	//step2 开始序号
	function funStep2a(){
		//var v=document.getElementById("step2a").value;
		//var reg=/^[0-9]{1,4}$/;
		//var tip=document.getElementById("tip2a");
		//if (v==""){ tip.innerHTML=""; return false;}
		//tip.style.color="red";
		//if (reg.test(v)&&parseInt(v,10)>=0&&parseInt(v,10)<=9999){
			//序号没问题，输入符合要求
		//	tip.innerHTML="";
			//funStep2b();
		//	return true;
		//}else{
			tip.innerHTML="请输入为0~9999的整数";
		//	return false;
		//}
	}//step2 生成数量
	function funStep2b(){
		if (!funStep2a()){
			return false;
		}
		var v=document.getElementById("step2b").value;
		var reg=/^[0-9]{1,4}$/;
		var tip=document.getElementById("tip2b");
		if (v==""){ tip.innerHTML=""; return false;}
		tip.style.color="red";
		if (reg.test(v)&&parseInt(v,10)>=1&&parseInt(v,10)<=1000){
			var s=new Number;
			s=parseInt(v,10)+parseInt(document.getElementById("step2a").value,10);
			//alert(s);
			if (s>9999){
				tip.innerHTML="开始序号和生成数量的和不能大于9999";
				return false;
			}else{
				//输入符合要求
				document.getElementById("step2bRes").value=s;
				tip.innerHTML="";
				return true;
			}
		}else{
			tip.innerHTML="请输入1~1000的整数";
			return false;
		}		
	}
	//step2 提示先输入开始序号
	function funStep2c(){
		if (!funStep2a()){
			//alert("请先填写开始序号！");
			return false;
		}
	}
	//合并前缀和序号
	function merge(p,n){
		var num=String(n);
		while (num.length<4){
			num="0"+num;
		}
		return (p+num);
	}
	
	//预览生成账号
	function funDisplay(){
		if (funStep1()&&funStep2a()&&funStep2b()){
			var d1=document.getElementById("display1");	
			var d2=document.getElementById("display2");	
			var d3=document.getElementById("display3");
			d1.innerHTML=merge(document.getElementById("step1").value
						,document.getElementById("step2a").value);
			d2.innerHTML="&nbsp;&nbsp;~&nbsp;&nbsp;";
			d3.innerHTML=merge(document.getElementById("step1").value
						,document.getElementById("step2bRes").value-1);
			
			//d1.innerHTML="test";
			return;
		}else{
			return;
		}	
	}
	
	//检查后台是否有重复账号
	function checkAccount(){
		//使用AJAX检查后台是否有重复账号
		//alert("checkAccount");
	}
	//step 3 检查开始租期
	function funStep3a1(){
		//var v=document.getElementById("step3a1").value;
		//var tip=document.getElementById("tip3a1");
		//var reg=/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
		//tip.style.color="red";
		//if (reg.test(v)){
		//	tip.innerHTML="";
		//	return true;
		//}else{
		//	tip.innerHTML="无效的日期";
		//	return false;
		//}
	}
	//step 3 检查结束租期
	function funStep3a2(){
		//var v=document.getElementById("step3a2").value;
		//var tip=document.getElementById("tip3a2");
		//var reg=/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
		//tip.style.color="red";
		//if (reg.test(v)){
		//	tip.innerHTML="";
		//	return true;
		//}else{
		//	tip.innerHTML="无效的日期";
		//	return false;
		//}
	}	
	//step 3  检查租期内费用
	function funStep3b(){
		//var v=document.getElementById("step3b").value;
		//var reg=/^[0-9]{1,}(.[0-9]{1,})?$/;
		//var tip=document.getElementById("tip3b");
		//if (v==""){ tip.innerHTML=""; return false;}
		//tip.style.color="red";
		//if (reg.test(v)){
		//	tip.innerHTML="";
		//	return true;
		//}else{
		//	tip.innerHTML="输入大于0的小数或整数";
		//	return false;
		//}
	}
	
	function checkPassword1(){
		var v=document.getElementById("step3c").value;
		var reg=/^[0-9a-zA-Z_]{6,25}$/;
		var tip=document.getElementById("tip3c");
		if (v==""){ tip.innerHTML=""; return false;}
		tip.style.color="red";
		if (reg.test(v)){
			tip.innerHTML="";
			return true;
		}else{
			tip.innerHTML="长度为6~25，可使用字母、数字或下划线";
			return false;
		}
	}
	//step3 确认密码
	function checkPassword2(){
		var tip=document.getElementById("tip3d");
		var v=document.getElementById("step3d").value;
		if (v==""||!funStep3c()){ tip.innerHTML=""; return false;}
		tip.style.color="red";
		if (v==document.getElementById("step3c").value){
			tip.innerHTML="";
			return true;
		}else{
			tip.innerHTML="两次填写的密码需要一致";
			return false;
		}
	}	
	
	
	