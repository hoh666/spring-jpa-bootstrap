$(document).ready(function(){
	$('.modal-footer button').click(function(){
		var button = $(this);

		if ( button.attr("data-dismiss") != "modal" ){
			var replyContent = $("#replyContent").val();
			var replyToUid = $("#replyToUid").val();
			var inputs = $('form input');
			var title = $('.modal-title');
			var progress = $('.progress');
			var progressBar = $('.progress-bar');

			inputs.attr("disabled", "disabled");

			button.hide();

			progress.show();

			progressBar.animate({width : "100%"}, 100);

			progress.delay(1000)
					.fadeOut(600);
			
			$.ajax({
				url : "/api/report/reply", 
				type : "post",
				traditional : true,
				data : {
				content : replyContent,
				replyToUid : replyToUid
				}, 
				success : function(data) {
					if (data.code == 0) {
						button.text("Close")
							.removeClass("btn-primary")
							.addClass("btn-success")
		    				.blur()
							.delay(1600)
							.fadeIn(function(){
								title.text("回复成功");
								button.attr("data-dismiss", "modal");
								window.location.href = "/api/report/detail?uid=" + replyToUid;
							});
					} else {
						button.text("Close")
							.removeClass("btn-primary")
							.addClass("btn-danger")
		    				.blur()
							.delay(1600)
							.fadeIn(function(){
								title.text("回复失败, " + data.message);
								button.attr("data-dismiss", "modal");
							});
					}
				},
				error : function() {
					button.text("Close")
						.removeClass("btn-primary")
						.addClass("btn-danger")
	    				.blur()
						.delay(1600)
						.fadeIn(function(){
							title.text("服务异常，请联系技术人员！");
							button.attr("data-dismiss", "modal");
						});
				}
			});
		}
	});

	$('#myModal').on('hidden.bs.modal', function (e) {
		var inputs = $('form input');
		var title = $('.modal-title');
		var progressBar = $('.progress-bar');
		var button = $('.modal-footer button');

		inputs.removeAttr("disabled");

		title.text("回复");

		progressBar.css({ "width" : "0%" });

		button.removeClass("btn-success")
				.addClass("btn-primary")
				.text("Ok")
				.removeAttr("data-dismiss");
	            
	});
});

function inject(uid) {
	$("#replyToUid").val(uid);
	$("#replyContent").val("");
}

function deleteReply(id, replyToUid) {

	$.ajax({
		url : "/api/report/deleteReply", 
		type : "post",
		traditional : true,
		data : {
		id : id
		}, 
		success : function(data) {
			if (data.code == 0) {
				window.location.href = "/api/report/detail?uid=" + replyToUid;
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