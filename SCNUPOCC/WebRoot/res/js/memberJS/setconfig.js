function editconfig(id){
	$("#editform").attr("action","member/configupdate.html");
	$("#editform").submit();
}

function deleteconfig(id){
	$("#cid").val(id);
	$("#form").attr("action","member/configdelete.html");
	$("#form").submit();
	
}