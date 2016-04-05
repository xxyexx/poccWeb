window.onload=function(){
//	alert("init");
    $('#table').dataTable( {
    	"bLengthChange": false,
    	"aLengthMenu": [17],
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
				 "search":         "关键字:",
        }
    } );
};

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
	
	//	批量删除
	function batchdelete(){
		$("#tform").attr("action","member/userbatchdelete.html");
		$("#tform").submit();
	}
	
	function submitedit(){
		alert("submit");
//		$("#tform").attr("action","member/userbatchedit.html");
//		$("#tform").submit();
	}
	
	function openedit(s){
		if (s<0){
			//批量修改
			$(".body1").hide();
		}
		else{
//			alert(s);
		}
		$("#myModal").modal();
	}