function loginSumbit() {

	username = $(" #username").val();
	password = $(" #pwd").val();

	$.post("/api/login", {
		username : username,
		password : password
	}, function(data) {
		if (data.code == 0) {
			window.location.href = "/api/home/index";
		} else {
			// var content = $( data ).find( "#content" );
			$("#alert").show();
			$("#errMsg").empty().append(data.message);
			$("#alert").fadeOut(2000);
		}
	});
}