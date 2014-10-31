<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  	<%@ include file="../include/navbar.jsp"%>
    <div class="container">
      <h2>用户</h2>
      <p>Using all the table classes on one table: ${users.totalElements }</p>                                          
      <table class="table table-striped table-bordered table-hover table-condensed">
        <thead>
          <tr>
            <th>#</th>
            <th>用户名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>职业</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${not empty users.content}">
			<c:forEach items="${users.content}" var="content">
				<tr>
					<td>${content.id }</td>
					<td>${content.userName }</td>
					<td>${content.sex.displayName}</td>
					<td>${content.age }</td>
					<td>${content.profession.professionName }</td>
				</tr>
			</c:forEach>
		  </c:if>
			<c:if test="${empty users.content}">
				<p>
					<spring:message code="page.users.no.found"></spring:message>
				</p>	
			</c:if>
        </tbody>
      </table>
    </div>

    <script src="/js/jquery/jquery-1.11.1.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</html>