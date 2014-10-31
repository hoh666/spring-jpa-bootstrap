<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/body.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	<div class="col-lg-12">
		<div class="well bs-component">
			<form class="form-horizontal" method="post" id="user_form" action="/api/user/create">
				<fieldset>
					<legend>基本信息</legend>
					<div class="form-group" id="usernameDiv">
						<label for="inputUsername" class="col-lg-4 control-label">用户名</label>
						<div class="col-lg-3">
							<input type="text" class="form-control" id="userName" name="userName"
								placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group" id="pwdDiv">
						<label for="inputPassword" class="col-lg-4 control-label">密码</label>
						<div class="col-lg-3">
							<input type="password" class="form-control" id="password" name="password"
								placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputSex" class="col-lg-4 control-label">other</label>
						<div class="col-lg-3">
							<input type="checkbox" name="sex1" value="MALE" checked="checked" /> one&nbsp;
							<input type="checkbox" name="sex1" value="FEMALE" /> two
						</div>
					</div>
					<div class="form-group" id="ageDiv">
						<label for="inputAge" class="col-lg-4 control-label">年龄</label>
						<div class="col-lg-3">
							<input type="text" name="age" value="" id="age" class="form-control"
								placeholder="请输入年龄"/>
						</div>
					</div>
					<div class="form-group" id="descriptionDiv">
						<label for="textArea" class="col-lg-4 control-label">自我描述</label>
						<div class="col-lg-3">
							<textarea class="form-control" rows="3" id="description" name="description"></textarea>
							<span class="help-block">A longer block of help text that
								breaks onto a new line and may extend beyond one line.</span>
						</div>
					</div>
					<div class="form-group" id="sexDiv">
						<label class="col-lg-4 control-label">Radios</label>
						<div class="col-lg-3">
							<div class="radio">
								<label> <input type="radio" name="sex"
									id="sex" value="FELAME" checked="checked"> 女
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="sex"
									id="sex" value="MALE"> 男
								</label>
							</div>
						</div>
					</div>
					<div class="form-group" id="professionDiv">
						<label for="select" class="col-lg-4 control-label">职业</label>
						<div class="col-lg-3">
							<select class="form-control" id="profession" name="profession.id">
							<c:if test="${not empty professions}">
								<option value="" selected="selected">----------------------- 请选择 -----------------------</option>
								<c:forEach items="${professions.content }" var="profession">
									<option value="${profession.id }">${profession.professionName }</option>
								</c:forEach>
							</c:if>
							<c:if test="${empty professions }">
								<option value="2" selected="selected">默认</option>
							</c:if>
							</select>
							<input type="hidden" name="profession.professionName" value="">
						</div>
					</div>
					<div class="form-group">
						<label for="select" class="col-lg-4 control-label">多选</label>
						<div class="col-lg-3">
							<select multiple="multiple" class="form-control" name="testMultiple" id="testMultiple">
								<option value="1" selected="selected">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-10 col-lg-offset-4">
							<button type="button" class="btn btn-default" onclick="goBack();">取消</button>
							<button type="button" class="btn btn-primary" onclick="formSubmit();">提交</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>

<script src="/js/jquery/jquery-1.11.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/user/addUser.js"></script>
</html>