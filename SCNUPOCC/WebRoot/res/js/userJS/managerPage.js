window.onload=function(){	
//	initDate();
	initSTable();
	selectTab();
};

function selectTab(){
	if ($("#left").val()=="teacher"){
//		alert("teacher");
		$("#teacherNav").addClass("active").siblings().removeClass("active");
		$("#teacherList").addClass("active").siblings().removeClass("active");
		
	}
}

function initSTable(){	
	$('#sTable').dataTable( {
    	"bLengthChange": true,//每页显示条数是否可选
    	"aLengthMenu": [15,25,35],//每页显示条数选项
        "language": {//汉化
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

function initTTable(){	
	$('#tTable').dataTable( {
    	"bLengthChange": true,//每页显示条数是否可选
    	"aLengthMenu": [15,25,35],//每页显示条数选项
        "language": {//汉化
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

function updateTea(o){
	hidePass();
	
//	console.log($(o).parent());
//	alert($(o).parent().siblings().eq(0).text());
	$("#tid").val($(o).parent().siblings().eq(0).text());
	$("#tloginId").val($(o).parent().siblings().eq(1).text());
	$("#tname").val($(o).parent().siblings().eq(2).text());
	$("#tmobile").val($(o).parent().siblings().eq(5).text());
	$("#temail").val($(o).parent().siblings().eq(6).text());
//	$("#id").val($(o).parent().siblings().eq(0).text());
//	$("#id").val($(o).parent().siblings().eq(0).text());
	
	$('#teacherModal').modal();
}

function submitTea(){
	$("#tform").attr("action","manager/managerpageupdateTea.html");
	$("#tform").submit();
}

function showPass(){
	$(".case1").show();
	$(".case2").hide();
}

function hidePass(){
	$(".case2").show();
	$(".case1").hide();
}