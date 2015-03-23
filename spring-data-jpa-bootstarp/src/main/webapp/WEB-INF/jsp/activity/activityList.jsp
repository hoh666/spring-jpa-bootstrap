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

<title>activity list</title>

<%@ include file="../include/menuFile.jsp"%>

<!-- DataTables CSS -->
<link href="/css/sb2/plugins/dataTables.bootstrap.css" rel="stylesheet">

<link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="/js/datetimepicker/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/jquery.twbsPagination.js" charset="UTF-8"></script>

<script type="text/javascript">
$(document).ready(function () {
    
    $('#startDateBegin').datetimepicker({
    	language:  'zh-CN',
	    format: "yyyy-mm-dd hh:ii:ss",
	    autoclose: 1,
	    todayHighlight: 1,
	    showMeridian: 1
    });
    $('#startDateEnd').datetimepicker({
    	language:  'zh-CN',
	    format: "yyyy-mm-dd hh:ii:ss",
	    autoclose: 1,
	    todayHighlight: 1,
	    showMeridian: 1
    }); 
    $('#endDateBegin').datetimepicker({
    	language:  'zh-CN',
	    format: "yyyy-mm-dd hh:ii:ss",
	    autoclose: 1,
	    todayHighlight: 1,
	    showMeridian: 1
    });
    $('#endDateEnd').datetimepicker({
    	language:  'zh-CN',
	    format: "yyyy-mm-dd hh:ii:ss",
	    autoclose: 1,
	    todayHighlight: 1,
	    showMeridian: 1
    });

    $('#pagination-demo').twbsPagination({
        totalPages: '1',
        visiblePages: 6,
        onPageClick: function (event, page) {
        	query(page);
            $('#page-content').text('Page ' + page);
        }
    });

});  

function query(page) {
	var activityName = $("#activityName");
	var startDateBegin = $("#startDateBegin");
	var startDateEnd = $("#startDateEnd");
	var endDateBegin = $("#endDateBegin");
	var endDateEnd = $("#endDateEnd");
	
	
	if (startDateBegin.val() != "" && startDateEnd.val() == "") {
		startDateEnd.focus();
		showErrMsg("请选择活动开始时间（截止）");
		return false;
	}
	
	if (startDateBegin.val() == "" && startDateEnd.val() != "") {
		starttime.focus();
		showErrMsg("请选择活动开始时间（开始）");
		return false;
	}

	if (endDateBegin.val() != "" && endDateEnd.val() == "") {
		startDateEnd.focus();
		showErrMsg("请选择活动结束时间（截止）");
		return false;
	}
	
	if (endDateBegin.val() == "" && endDateEnd.val() != "") {
		starttime.focus();
		showErrMsg("请选择活动结束时间（开始）");
		return false;
	}
	
	$.ajax({
		url : "/api/activity/query", 
		type : "post",
		traditional : true,
		data : {
			activityName : activityName.val(),
			startDateBegin : startDateBegin.val(),
			startDateEnd : startDateEnd.val(),
			endDateBegin : endDateBegin.val(),
			endDateEnd : endDateEnd.val(),
			page : page
		}, 
		success : function(data) {
			if (data.code == 0) {
				$("#dataTables-example tbody").html("");
				$.each(data.content[0].content, function(i, item) {
					//alert(item.id);
					var row = "<tr><td>"+ item.id +"</td><td>"+ item.activityName +"</td><td>"+ item.createDate +"</td><td>"+ item.startDate +"</td><td class='center'>" + item.endDate + "</td><td>" + item.status +"</td><td><a href='/api/activity/detail?id="+ item.id +"'>修改/查看详情</a></td></tr>";
					$("#dataTables-example tbody").append(row);
				});
			} else {
				$("#dataTables-example tbody").append(data.message);
			}
		},
		error : function() {
			$("#dataTables-example tbody").append(data.message);
		}
	});
}

function showErrMsg(msg) {
	$("#alert").show();
	$("#errMsg").empty().append(msg);
	$("#alert").fadeOut(3000);
}

function jumpAdd() {
	window.location.href = "/api/activity/goAdd";
}
</script>
<title></title>
</head>
<body>
<body>
<div id="wrapper">
	<%@ include file="../include/nemu.jsp"%>
	<div id="page-wrapper">
		<div class="row">
               <div class="col-lg-12">
                   <h1 class="page-header">活动信息</h1>
               </div>
               <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
             <div class="panel panel-default">
                 <div class="panel-heading">活动配置的相关信息 </div>
                 <!-- /.panel-heading -->
                 <div class="panel-body">
                 	<!-- query items -->
					<div id="custom-toolbar">
						<form class="well form-inline" role="form">
							<div class="form-group">
								<input class="form-control" type="text" id="activityName" value="" placeholder="输入活动名称">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" readonly="readonly" id="startDateBegin" value="" data-date-format="yyyy-mm-dd hh:ii:ss" placeholder="活动起始时间（开始）">
								<input type="text" class="form-control" readonly="readonly" id="startDateEnd" value="" data-date-format="yyyy-mm-dd hh:ii:ss" placeholder="活动开始时间（结束）">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" readonly="readonly" id="endDateBegin" value="" data-date-format="yyyy-mm-dd hh:ii:ss" placeholder="活动结束时间（开始）">
								<input type="text" class="form-control" readonly="readonly" id="endDateEnd" value="" data-date-format="yyyy-mm-dd hh:ii:ss" placeholder="活动结束时间（结束）">
							</div>
							<button type="button" class="btn btn-primary" id="searchButton" onclick="query(1);">查询</button>
							<button type="button" class="btn btn-primary" id="addActivityButton" onclick="jumpAdd();">新增活动</button>
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
										<th>活动编号</th>
										<th>活动名称</th>
										<th>创建时间</th>
										<th>开始时间</th>
										<th>结束时间</th>
										<th>状态</th>
										<th>修改/查看详情</th>
									</tr>
								</thead>
								<tbody>
									
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