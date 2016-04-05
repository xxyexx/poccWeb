window.onload=function(){
//	alert("init");
	$("#table").DataTable();
	hide();
};

function hide(){
	$(".case1").hide();
	$(".case2").show();
}

function show(){
	$(".case2").hide();
	$(".case1").show();
}

function update(){	
	if ($("#id").val()>0){
		$("#form").attr("action","member/memberupdate.html");
	}else{
		$("#form").attr("action","member/membercreate.html");
	}
	$("#form").submit();
}

function create(){
	clean();
	hide();
	$('#myModal').modal();
}

function clean(){
	$("#title").text("新增管理员");
	$("#id").val("0");
	$("#name").val("");	
	$("#loginId").val("");
	$("#password").val("");
//	$("#memberType").val($(m).parent().siblings().eq(4).text());
	$("#mobile").val("");
	$("#email").val("");
}

function edit(m){
//	$("#form").attr("action","member/membercreate.html");
//	$("#form").submit();
//	alert(m);
	hide();
//	alert($(m).parent().siblings().eq(0).text());
//	alert($(m).parent().siblings().eq(1).text());
	$("#title").text("修改管理员");
	$("#id").val($(m).parent().siblings().eq(0).text());
	$("#name").val($(m).parent().siblings().eq(2).text());	
	$("#loginId").val($(m).parent().siblings().eq(1).text());
	$("#password").val("");
//	$("#memberType").val($(m).parent().siblings().eq(4).text());
	$("#mobile").val($(m).parent().siblings().eq(4).text());
	$("#email").val($(m).parent().siblings().eq(5).text());
	$('#myModal').modal();
	
}

function deletemember(mid){
//	alert("delete"+mid);
	$("#mid").val(mid);
	$("#tform").attr("action","member/memberdelete.html");
	$("#tform").submit();
}