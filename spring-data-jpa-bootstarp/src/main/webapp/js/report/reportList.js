$(document).ready(function () {
	$("a.active").removeClass("active");
	$("#a1").addClass("active");
    
    $('#starttime').datetimepicker({
    	language:  'zh-CN',
	    format: "yyyy-mm-dd hh:ii:ss",
	    autoclose: 1,
	    todayHighlight: 1,
	    showMeridian: 1
    });
    $('#endtime').datetimepicker({
    	language:  'zh-CN',
	    format: "yyyy-mm-dd hh:ii:ss",
	    autoclose: 1,
	    todayHighlight: 1,
	    showMeridian: 1
    }); 

});  

function query(page) {
	var username = $("#username");
	var starttime = $("#starttime");
	var endtime = $("#endtime");
	
	/*
	
	if (username.val() == "" && (starttime.val() == "" && endtime.val() == "")) {
		username.focus();
		showErrMsg("用户名不能为空");
		return false;
	}
	*/
	
	if (starttime.val() != "" && endtime.val() == "") {
		endtime.focus();
		showErrMsg("请选择截止时间");
		return false;
	}
	
	if (endtime.val() != "" && starttime.val() == "") {
		starttime.focus();
		showErrMsg("请选择开始时间");
		return false;
	}
	
	$.ajax({
		url : "/api/report/list/query", 
		type : "post",
		traditional : true,
		data : {
		username : username.val(),
		starttime : starttime.val(),
		endtime : endtime.val(),
		page : page
		}, 
		success : function(data) {
			if (data.code == 0) {
				$("#dataTables-example tbody").html("");
				$.each(data.content[0].content, function(i, item) {
					//alert(item.id);
					var row = "<tr><td>"+ item.id +"</td><td>"+ item.userId +"</td><td>"+ item.username +"</td><td>"+ item.phone +"</td><td>"+ item.mail +"</td><td class='center'>"+ item.reportDate +"</td><td><a href='/api/report/detail?uid="+ item.userId +"'>回复/查看更多</a></td></tr>";
					$("#dataTables-example tbody").append(row);
				});
			} else {
				$("#dataTables-example tbody").append(data.message);
			}
		},
		error : function() {
			$("#dataTables-example tbody").append(data.message);
		}
	});
}

function showErrMsg(msg) {
	$("#alert").show();
	$("#errMsg").empty().append(msg);
	$("#alert").fadeOut(2000);
}