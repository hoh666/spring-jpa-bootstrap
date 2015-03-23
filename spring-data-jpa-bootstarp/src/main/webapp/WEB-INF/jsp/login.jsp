<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/signin.css">
<script src="/js/jquery/jquery-1.11.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/login.js?v=<%=version%>"></script>
<title>login</title>

<%@ include file="include/menuFile.jsp"%>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">请登录</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" id="loginForm" action="/api/login">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="请输入用户名" id="username" name="username" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="请输入密码" id="pwd" name="password" type="password" value="">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button class="btn btn-lg btn-primary btn-block" type="button" onclick="loginSumbit();">登录</button>
                            </fieldset>
                            <div id="alert" class="my-alert alert alert-dismissable alert-danger" style="display: none">
							  <strong id="errMsg" style="text-align: center">Oh snap!</strong>
							</div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>