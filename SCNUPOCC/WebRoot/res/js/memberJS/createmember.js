window.onload=init;
function init(){
//	alert("init");
	$("#id").val("");
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

function edit(m){
//	$("#form").attr("action","member/membercreate.html");
//	$("#form").submit();
//	alert(m);
	init();
//	alert($(m).parent().siblings().eq(0).text());
//	alert($(m).parent().siblings().eq(1).text());
	$("#title").val("修改管理员");
	$("#id").val($(m).parent().siblings().eq(0).text());
	$("#name").val($(m).parent().siblings().eq(2).text());	
	$("#loginId").val($(m).parent().siblings().eq(1).text());
//	$("#password").val($(m).parent().siblings().eq(3).text());
//	$("#memberType").val($(m).parent().siblings().eq(4).text());
	$("#mobile").val($(m).parent().siblings().eq(4).text());
	$("#email").val($(m).parent().siblings().eq(5).text());

	
	$('#myModal').modal();
	
}

function deletemember(mid){
	alert("delete"+mid);
	$("#mid").val(mid);
	$("#tform").attr("action","member/memberdelete.html");
	$("#tform").submit();
}