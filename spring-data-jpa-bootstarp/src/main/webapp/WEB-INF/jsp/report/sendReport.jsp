<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html>

<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>send report</title>

<%@ include file="../include/menuFile.jsp"%>
<script type="text/javascript" src="/js/report/sendReport.js?v=<%=version%>"></script>
</head>

<body>

	<div id="wrapper">

		<%@ include file="../include/nemu.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">反馈</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">请输入与反馈相关的信息</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
										<form class="form-horizontal" method="post" id="report_form"
											action="/api/report/sendReport">
												<div class="form-group" id="phoneDiv">
													<label for="inputPhone" class="col-lg-4 control-label">手机号</label>
													<div class="col-lg-3">
														<input type="text" class="form-control" id="phone"
															name="phone" placeholder="填写手机号以便联系您">
													</div>
												</div>
												<div class="form-group" id="mailDiv">
													<label for="inputMail" class="col-lg-4 control-label">邮箱</label>
													<div class="col-lg-3">
														<input type="text" class="form-control" id="mail"
															name="mail" placeholder="填写邮箱以便联系您">
													</div>
												</div>
												<div class="form-group">
													<label for="inputReportType" class="col-lg-4 control-label">遇到的问题:</label>
													<div class="col-lg-3">
														<input type="checkbox" id="reportType" name="reportType"
															value="UI" /> 样式弱到爆&nbsp; <input type="checkbox"
															id="reportType" name="reportType" value="SLOW" />
														打开速度慢&nbsp; <input type="checkbox" id="reportType"
															name="reportType" value="SERVER_ERROR" /> 服务异常多&nbsp; <input
															type="checkbox" id="reportType" name="reportType"
															value="MANY_BUGS" /> bug多&nbsp; <input type="checkbox"
															id="reportType" name="reportType" value="OTHER" />
														其他&nbsp;
													</div>
												</div>
												<div class="form-group" id="contentDiv">
													<label for="textArea" class="col-lg-4 control-label">反馈意见</label>
													<div class="col-lg-3">
														<textarea class="form-control" rows="3" id="content"
															name="content"></textarea>
														<input type="hidden" id="uid" value="2172834791" /> <input
															type="hidden" id="username" value="hoh555" />
													</div>
												</div>
												<div class="form-group">
													<div class="col-lg-10 col-lg-offset-4">
														<button type="button" class="btn btn-default" onclick="">取消</button>
														<button type="button" class="btn btn-primary"
															onclick="formSubmit();">反馈</button>
													</div>
												</div>
												<div id="alert" class="my-alert alert alert-dismissable alert-danger" style="display: none">
													<strong id="errMsg" style="text-align: center">Oh
														snap!</strong>
												</div>
										</form>
								</div>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

</body>

</html>
