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
		
		$('.time_date').datetimepicker({
			format: 'yyyy/mm/dd',  
	         weekStart: 0,  
	         autoclose: true,  
	         startView: 2,  
	         minView: 2,  
	         forceParse: false,  
	         language: 'zh-CN' 
		});	
		
		$("#dBtn").hover(
			function(){
//				$(this).css("padding-top","0px");
			},
			function (){
//				$(this).css("padding-top","0px");
			}
		);
		
	    $('#table1').DataTable( {
	    	"bLengthChange": false,
	    	"aLengthMenu": [17],
	    	"searching": false,
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
	
//新增记录
	function update(){
		$("#form").attr("action","member/paymentupdate.html");
		$("#form").submit();
	}
	

	