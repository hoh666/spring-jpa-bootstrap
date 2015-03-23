document.write("<script language='javascript' src='../../../js/base.js?v=<%=version%>'></script>");
function formSubmit() {

	getUser();

	var reportType = new Array();
	phone = $(" #phone").val();
	mail = $(" #mail").val();
	content = $(" #content").val();
	uid = $(" #uid").val();
	username = $(" #username").val();
	
	$("input[name='reportType']:checked").each(function(){
		reportType.push($(this).val());
	});
	
	$.ajax({
		url : "/api/report/sendReport", 
		type : "post",
		traditional : true,
		data : {
		phone : phone,
		mail : mail,
		content : content,
		reportType : reportType,
		uid : uid,
		username : username
		}, 
		success : function(data) {
			if (data.code == 0) {
				window.location.href = "/api/report/list";
			} else {
				$("#alert").show();
				$("#errMsg").empty().append(data.message);
				$("#alert").fadeOut(2000);
			}
		},
		error : function() {
			$("#alert").show();
			$("#errMsg").empty().append("服务异常，请联系技术人员！");
			$("#alert").fadeOut(2000);
		}
	});
}