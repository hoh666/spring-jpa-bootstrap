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

<title>report list</title>

<%@ include file="../include/menuFile.jsp"%>

<!-- DataTables CSS -->
<link href="/css/sb2/plugins/dataTables.bootstrap.css" rel="stylesheet">

<link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="/js/datetimepicker/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/jquery.twbsPagination.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/report/reportList.js?v=<%=version%>"></script>

<title></title>

<script type="text/javascript">
$(document).ready(function () {

    $('#pagination-demo').twbsPagination({
        totalPages: '${reportList.totalPages}',
        visiblePages: 6,
        onPageClick: function (event, page) {
        	query(page);
            $('#page-content').text('Page ' + page);
        }
    });

});
</script>
</head>
<body>
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
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
             <div class="panel panel-default">
                 <div class="panel-heading">用户对触屏版的意见及bug反馈</div>
                 <!-- /.panel-heading -->
                 <div class="panel-body">
                 	<!-- query items -->
					<div id="custom-toolbar">
						<form class="well form-inline" role="form">
							<div class="form-group">
								<input class="form-control" type="text" id="username" value="" placeholder="输入用户名">
							</div>
							<input type="text" class="form-control" readonly="readonly" id="starttime" value="" data-date-format="yyyy-mm-dd hh:ii:ss" placeholder="起始时间">
							<input type="text" class="form-control" readonly="readonly" id="endtime" value="" data-date-format="yyyy-mm-dd hh:ii:ss" placeholder="截止时间">
							<button type="button" class="btn btn-primary" id="searchButton" onclick="query(1);">查询</button>
						</form>
						<div id="alert" class="my-alert alert alert-dismissable alert-danger" style="display: none">
							<a href="#" class="close" data-dismiss="alert">&times;</a>
							<strong id="errMsg" style="text-align: center">Oh snap!</strong>
						</div>
					</div>
				</div>
					<!-- /. query items -->
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover" id="dataTables-example">
								<thead>
									<tr>
										<th>编号</th>
										<th>用户编号</th>
										<th>用户名</th>
										<th>联系方式</th>
										<th>邮箱</th>
										<th>反馈时间</th>
										<th>回复/查看更多</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty reportList.content}">
										<c:forEach items="${reportList.content}" var="content">
											<tr class="odd gradeX">
												<td>${content.id }</td>
												<td>${content.userId }</td>
												<td>${content.username }</td>
												<td>${content.phone }</td>
												<td>${content.mail }</td>
												<td class="center">
													<fmt:formatDate value="${content.reportDate}" var="formattedDate" type="date" pattern="yyyy-MM-dd HH:mm:ss" />${formattedDate }
												</td>
												<td><a href="/api/report/detail?uid=${content.userId }">回复/查看更多</a></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
                        <!-- /.panel-body -->
                    <ul id="pagination-demo" class="pull-right"></ul>
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
		</div>
	</div>
</body>
</html>