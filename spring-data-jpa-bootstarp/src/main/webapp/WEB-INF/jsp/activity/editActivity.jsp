<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>edit activity</title>

<%@ include file="../include/menuFile.jsp"%>

<link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="/js/datetimepicker/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<script type="text/javascript" src="/js/activity/editActivity.js?v=<%=version%>" charset="UTF-8"></script>
</head>

<body>

	<div id="wrapper">

		<%@ include file="../include/nemu.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">活动</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">编辑活动</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
										<form class="form-horizontal" method="post" id="activity_form" action="/api/activity/add">
												<div class="form-group" id="activityNameDiv">
													<label for="inputActivityName" class="col-lg-4 control-label">活动名称*</label>
													<div class="col-lg-3">
														<input type="text" class="form-control" id="activityName" name="activityName" value="${record.activityName }" placeholder="填写活动名称">
														<input type="hidden" id="recordId" name="recordId" value="${record.id }">
													</div>
												</div>
												<div class="form-group" id="startDiv">
													<label for="inputStart" class="col-lg-4 control-label">开始时间*</label>
													<div class="col-lg-3">
														<input type="text" class="form-control" id="startDate" name="startDate" value="${record.startDate }" data-date-format="yyyy-mm-dd hh:ii:ss" readonly="readonly" placeholder="选择开始时间">
													</div>
												</div>
												<div class="form-group" id="endDiv">
													<label for="inputEnd" class="col-lg-4 control-label">结束时间*</label>
													<div class="col-lg-3">
														<input type="text" class="form-control" id="endDate" name="endDate" value="${record.endDate }" data-date-format="yyyy-mm-dd hh:ii:ss" readonly="readonly" placeholder="选择结束时间">
													</div>
												</div>
												<div class="form-group" id="statusDiv">
													<label for="select" class="col-lg-4 control-label">活动状态</label>
													<div class="col-lg-3">
														<select class="form-control" id="status" name="status">
															<c:if test="${record.status == 'OPEN' }">
																<option selected="selected" value="OPEN">开启</option>
																<option value="CLOSED">关闭</option>
															</c:if>
															<c:if test="${record.status == 'CLOSED' }">
																<option value="OPEN">开启</option>
																<option selected="selected" value="CLOSED">关闭</option>
															</c:if>
														</select>
													</div>
												</div>
												<div class="form-group" id="discriptionDiv">
													<label for="textArea" class="col-lg-4 control-label">活动描述</label>
													<div class="col-lg-3">
														<textarea class="form-control" rows="3" id="discription" name="discription">${record.discription }</textarea>
													</div>
												</div>
												<div class="form-group" id="hrefDiv">
													<label for="textArea" class="col-lg-4 control-label">活动地址*</label>
													<div class="col-lg-3">
														<textarea class="form-control" rows="3" id="href" name="href">${record.href }</textarea>
													</div>
												</div>
												<div class="form-group" id="redirectDiv">
													<label for="textArea" class="col-lg-4 control-label">是否跳出打开*</label>
													<div class="col-lg-3">
														<input type="radio" id="redirect" name="redirect" value="true" <c:if test="${record.redirect == true }">checked="checked" </c:if> /> 是&nbsp; 
														<input type="radio" id="redirect" name="redirect" value="false" <c:if test="${record.redirect == false }">checked="checked" </c:if> /> 否&nbsp;
													</div>
												</div>
												
												<div class="form-group" id="imgDiv">
													<label for="textArea" class="col-lg-4 control-label">焦点图地址*</label>
													<div class="col-lg-3">
														<input type="text" class="form-control" id="img" name="img" value="${record.img}" placeholder="请输入焦点图地址">
													</div>
												</div>
												<div class="form-group" id="countDiv">
													<label for="textArea" class="col-lg-4 control-label">统计事件名称*</label>
													<div class="col-lg-3">
														<input type="text" class="form-control" id="countName" name="countName" value="${record.countName}" placeholder="请输入统计事件名称">
													</div>
												</div>
												<div class="form-group" id="unSupportDiv">
													<label for="textArea" class="col-lg-4 control-label">分站支持情况（json格式）*</label>
													<div class="col-lg-3">
														<textarea class="form-control" rows="3" id="unSupport" name="unSupport">${record.unSupport}</textarea>
													</div>
												</div>
												<div class="form-group">
													<div class="col-lg-10 col-lg-offset-4">
														<button type="button" class="btn btn-default" onclick="goBack();">取消</button>
														<button type="button" class="btn btn-primary" onclick="formSubmit();">提交</button>
													</div>
												</div>
												<div id="alert" class="my-alert alert alert-dismissable alert-danger" style="display: none">
													<strong id="errMsg" style="text-align: center">Oh snap!</strong>
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