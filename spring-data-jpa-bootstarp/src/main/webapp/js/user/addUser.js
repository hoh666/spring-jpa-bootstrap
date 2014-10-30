function formSubmit() {

	resetClass();

	if ($("#userName").val() == "") {
		$("#usernameDiv").addClass(" has-error");
		$("#userName").focus();
		return ;
	}
	if ($("#password").val() == "") {
		$("#pwdDiv").addClass(" has-error");
		$("#password").focus();
		return ;
	}
	if ($("#sex").val() == "") {
		$("#sexDiv").addClass(" has-error");
		$("#sex").focus();
		return ;
	}
	if ($("#age").val() == "") {
		$("#ageDiv").addClass(" has-error");
		$("#age").focus();
		return ;
	}
	if ($("#description").val() == "") {
		$("#descriptionDiv").addClass(" has-error");
		$("#description").focus();
		return ;
	}
	if ($("#profession").val() == "") {
		$("#professionDiv").addClass(" has-error");
		$("#profession").focus();
		return ;
	}
	$("#user_form").submit();
}

function resetClass(){
	$("#usernameDiv").attr("class", "form-group");
	$("#pwdDiv").attr("class", "form-group");
	$("#sexDiv").attr("class", "form-group");
	$("#ageDiv").attr("class", "form-group");
	$("#descriptionDiv").attr("class", "form-group");
	$("#professionDiv").attr("class", "form-group");
}