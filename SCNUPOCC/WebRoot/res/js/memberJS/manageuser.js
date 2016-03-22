	
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
		if(getJsonLength(schoolList)!=0){
			for(var schoolName in schoolList){				
				$("#school").append("<option value='"+schoolName+"'>"+schoolList[schoolName]+"</option>");
			}
		}else{
			$("#school").append("<option value='-1'>不限</option>");
		}
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
		//if (getJsonLength(deptList)>0){
			for(var deptID in deptList){
				$("#dept").append("<option value='"+deptID+"'>"+deptList[deptID]+"</option>");
			}
		//}else
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