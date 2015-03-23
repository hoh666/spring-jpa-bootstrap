<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>report detail</title>
<%@ include file="../include/menuFile.jsp"%>

<link href="/css/timeline.css" rel="stylesheet" media="screen">
<link href="/css/report/bootstrap-editable.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="/js/report/reportDetail.js" charset="UTF-8" ></script>
<link rel="stylesheet" href="/css/report/popover.css">
</head>

<body>
<div id="wrapper">
	<%@ include file="../include/nemu.jsp"%>
	<div id="page-wrapper">
		<div class="row">
               <div class="col-lg-12">
                   <h1 class="page-header">反馈信息</h1>
               </div>
               <!-- /.col-lg-12 -->
        </div>
		<ul class="timeline">
			<c:forEach var="detail" items="${reportDetail}">
				<c:if test="${detail.replyToUid == 0}">
					<li>
						<div class="timeline-badge danger">
							<i class="glyphicon glyphicon-hand-left"></i>
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<h4 class="timeline-title">${detail.username }</h4>
								<p>
									<small class="text-muted"><i class="glyphicon glyphicon-time"></i>
									<fmt:formatDate value="${detail.reportDate}" var="formattedDate" type="date" pattern="yyyy-MM-dd HH:mm:ss" />
									${formattedDate }</small>
								</p>
								<c:if test="${detail.replyToUid == 0}">
									<p><small class="text-muted"><i class="glyphicon glyphicon-tags"></i> ${detail.reportTypeValue }</small></p>
								</c:if>
							</div>
							<div class="timeline-body">
								<p>${detail.content }</p>
							</div>
							<button class="btn btn-sm btn-primary pull-right" data-toggle="modal" data-target="#myModal" onclick="inject('${detail.userId }');">回复</button>
						</div>
					</li>
				</c:if>
				<c:if test="${detail.replyToUid != 0}">
					<li class="timeline-inverted">
						<div class="timeline-badge success">
							<i class="glyphicon glyphicon-hand-right"></i>
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<h4 class="timeline-title">${detail.username }</h4>
								<p>
									<small class="text-muted"><i class="glyphicon glyphicon-time"></i>
									<fmt:formatDate value="${detail.reportDate}" var="formattedDate" type="date" pattern="yyyy-MM-dd HH:mm:ss" />
									${formattedDate }</small>
								</p>
							</div>
							<div class="timeline-body">
								<p>${detail.content }</p>
							</div>
							<button class="btn btn-sm btn-primary pull-right" onclick="deleteReply('${detail.id }', '${detail.replyToUid}');">删除</button>
							<div id="alert" class="my-alert alert alert-dismissable alert-danger" style="display: none">
								<a href="#" class="close" data-dismiss="alert">&times;</a>
								<strong id="errMsg" style="text-align: center">Oh snap!</strong>
							</div>
						</div>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">回复</h4>
			</div> <!-- /.modal-header -->

			<div class="modal-body">
				<form role="form">
					<div class="form-group">
						<div class="input-group">
							<textarea class="form-control" rows="3" id="replyContent" placeholder="请输入回复内容..."></textarea>
							<input type="hidden" id="replyToUid" value=""/>
						</div>
					</div> <!-- /.form-group -->
				</form>

			</div> <!-- /.modal-body -->

			<div class="modal-footer">
				<button class="form-control btn btn-primary" id="replyButton">Ok</button>

				<div class="progress">
					<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="1" aria-valuemin="1" aria-valuemax="100" style="width: 0%;">
						<span class="sr-only">progress</span>
					</div>
				</div>
			</div> <!-- /.modal-footer -->
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>