/*
引入文件:
 
<!-- DataTables -->
<link rel="stylesheet" type="text/css" href="res/DataTables-1.10.7/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="res/DataTables-1.10.7/js/jquery.dataTables.min.js"></script>

*/

//引入脚本
$('#table').dataTable( {
    	"bLengthChange": false,//每页显示条数是否可选
    	"aLengthMenu": [17],//每页显示条数选项
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