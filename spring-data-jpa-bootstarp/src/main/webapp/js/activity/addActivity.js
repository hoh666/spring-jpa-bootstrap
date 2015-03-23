$(document).ready(function () {
	$('#startDate').datetimepicker({
		language:  'zh-CN',
	    format: "yyyy-mm-dd hh:ii:ss",
	    autoclose: 1,
	    todayHighlight: 1,
	    showMeridian: 1
	});
	$('#endDate').datetimepicker({
		language:  'zh-CN',
	    format: "yyyy-mm-dd hh:ii:ss",
	    autoclose: 1,
	    todayHighlight: 1,
	    showMeridian: 1
	}); 
});

function formSubmit() {

	resetClass();

	if ($("#activityName").val() == "") {
		$("#activityNameDiv").addClass(" has-error");
		$("#activityName").focus();
		return ;
	}
	if ($("#startDate").val() == "") {
		$("#startDiv").addClass(" has-error");
		$("#startDate").focus();
		return ;
	}
	if ($("#endDate").val() == "") {
		$("#endDiv").addClass(" has-error");
		$("#endDate").focus();
		return ;
	}
	if ($("#status").val() == "") {
		$("#statusDiv").addClass(" has-error");
		$("#status").focus();
		return ;
	}
	if ($("#href").val() == "") {
		$("#hrefDiv").addClass(" has-error");
		$("#href").focus();
		return ;
	}
	if ($("#redirect").val() == "") {
		$("#redirectDiv").addClass(" has-error");
		$("#redirect").focus();
		return ;
	}
	if ($("#img").val() == "") {
		$("#imgDiv").addClass(" has-error");
		$("#img").focus();
		return ;
	}
	if ($("#countName").val() == "") {
		$("#countDiv").addClass(" has-error");
		$("#countName").focus();
		return ;
	}
	if ($("#unSupport").val() == "") {
		$("#unSupportDiv").addClass(" has-error");
		$("#unSupport").focus();
		return ;
	}

	$.ajax({
		url : "/api/activity/add", 
		type : "post",
		traditional : true,
		data : {
			activityName : $("#activityName").val(),
			startDate : $("#startDate").val(),
			endDate : $("#endDate").val(),
			status : $("#status").val(),
			href : $("#href").val(),
			redirect : $("#redirect").val(),
			img : $("#img").val(),
			countName : $("#countName").val(),
			unSupport : $("#unSupport").val()
		}, 
		success : function(data) {
			if (data.code == 0) {
				window.location.href = "/api/activity/list";
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

function resetClass(){
	$("#activityNameDiv").attr("class", "form-group");
	$("#startDiv").attr("class", "form-group");
	$("#endDiv").attr("class", "form-group");
	$("#hrefDiv").attr("class", "form-group");
	$("#redirectDiv").attr("class", "form-group");
	$("#imgDiv").attr("class", "form-group");
	$("#countDiv").attr("class", "form-group");
	$("#unSupportDiv").attr("class", "form-group");
}

function goBack(){
	window.location.href="/api/activity/list";
}