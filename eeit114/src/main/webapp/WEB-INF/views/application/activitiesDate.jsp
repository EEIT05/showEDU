<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type='text/javascript'
	src='${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js'></script>
<title>預約</title>
<%-- <link rel='stylesheet' href='${pageContext.request.contextPath}/css/style.css'  type="text/css" /> --%>

<style>
html {
	height: 100%;
	background-color: #271a59;
	padding: 5px;
}


/* .panel .panel-default .panel-table{ */
/* 	position: absolute; */
/* 	top: 100px; */
/* 	left: 60px; */
/* }  */

/* .container{ */
/* margin:100px; */
/* color:white;  
}

.panel-title{
color:white;
}

.panel-table .panel-body{
  padding:30;
}

.panel-table .panel-body .table-bordered{
  border-style: none;
  margin:0;
}

.panel-table .panel-body .table-bordered > thead > tr > th:first-of-type {
    text-align:center;
    width: 100px;
}

.panel-table .panel-body .table-bordered > thead > tr > th:last-of-type,
.panel-table .panel-body .table-bordered > tbody > tr > td:last-of-type {
  border-right: 0px;
}

.panel-table .panel-body .table-bordered > thead > tr > th:first-of-type,
.panel-table .panel-body .table-bordered > tbody > tr > td:first-of-type {
  border-left: 0px;
}

.panel-table .panel-body .table-bordered > tbody > tr:first-of-type > td{
  border-bottom: 0px;
}

.panel-table .panel-body .table-bordered > thead > tr:first-of-type > th{
  border-top: 0px;
}

.panel-table .panel-footer .pagination{
  margin:0; 
}

/*
used to vertically center elements, may need modification if you're not using default sizes.
*/
.panel-table .panel-footer .col {
	line-height: 34px;
	height: 34px;
}

.panel-table .panel-heading .col h3 {
	line-height: 50px;
	height: 50px;
}

.panel-table .panel-body .table-bordered>tbody>tr>td {
	line-height: 34px;
}
</style>

<script>
	
</script>

</head>
<body>
	<link
		href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<!------ Include the above in your HEAD tag ---------->

	<link
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
		rel='stylesheet' type='text/css'>
		
<%-- 	<%@ include file="../back.jsp" %>	 --%>
	<div class="col-md-10 col-md-offset-1 ">

		<div class="panel panel-default panel-table ">
			<div class="panel-heading ">
				<div class="row">
					<div class="col col-xs-6">
						<h2 class="panel-title">活動公告清單</h2>
					</div>
					<div>
						<form action='activitiesByDatePerPage' method='GET'>
							<input type='date' name='date' id='mydate' value='${selectDate}'>
							<button type='submit'>查詢</button>
						</form>
					</div>
				</div>
			</div>


			<c:choose>
				<c:when test="${empty activities}">
	    沒有任何預約資料<br>
				</c:when>
				<c:otherwise>
					<div class="panel-body">
						<table class="table table-striped table-bordered table-list">
							<thead>
								<tr>

									<th>編號</th>
									<th>標題</th>
									<th>開始日期</th>
									<th>截止日期</th>
									<th>發佈日期</th>

								</tr>
							</thead>
							<tbody id='activities'>
								<c:forEach var='activity' items='${activities}'>
									<tr>
										<td align='center'>${activity.actId}</td>
										<td><a href="activity?id=${activity.actId}">${activity.actTitle}</a></td>
										<td>${activity.startDate}</td>
										<td>${activity.endDate}</td>
										<td align='right'>${activity.postDate}</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
				</c:otherwise>
			</c:choose>
			<hr>
			<div id="pagebtns">
				<c:choose>
					<c:when test="${empty selectDate}">
						<c:forEach var='totalPageNo' begin='1' end='${totalPage}'>
							<a id="page${totalPageNo}"
								href="activitiesDate?pageNo=${totalPageNo}">${totalPageNo}</a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var='totalPageNo' begin='1' end='${totalPage}'>
							<a id="page${totalPageNo}"
								href="activitiesByDatePerPage?date=${selectDate}&pageNo=${totalPageNo}">${totalPageNo}</a>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<a href="<c:url value='/'/> ">首頁</a>
		</div>
		</div>
</body>

</html>