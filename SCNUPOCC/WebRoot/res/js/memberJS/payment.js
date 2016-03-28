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
	};
	
//新增记录
	function update(){
		$("form").attr("action","member/paymentupdate.html");
		$("form").submit();
	}