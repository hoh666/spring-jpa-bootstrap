function getUser() {
	$.get("/api/user/userinfo",  function(data) {
		if (data.code == -2) {
			window.location.href = "/api/report/reportDetail";
		}
	});
}