<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Activities</title>
<script>

</script>
</head>
<body>
	<section>
		<div>
			<div class="container" style="text-align: center">
				<h1>活動公告</h1>
			</div>
		</div>
	</section>
	<section>
		<div>
			<form action='activitiesByDatePerPage' method='GET'>
			<input type='date' name='date' id='mydate' value='${selectDate}'>
			<button type='submit'>查詢</button>
			</form>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container" id="container">
		<table border='1'>
			<tr>
				<th>編號</th>
				<th>活動名稱</th>
				<th>開始日期</th>
				<th>截止日期</th>
				<th>上傳日期</th>
				<c:forEach var='activity' items='${activities}'>
					<tr>
						<td align='center'>${activity.actId}</td>
						<td><a href="activity?id=${activity.actId}">${activity.actTitle}</a></td>
						<td>${activity.startDate}</td>
						<td>${activity.endDate}</td>
						<td align='right'>${activity.postDate}</td>
					</tr>
				</c:forEach>
		</table>
	</section>
	<div id="pagebtns">
	<c:choose>
		<c:when test= "${empty selectDate}">
			<c:forEach var = 'totalPageNo' begin = '1' end='${totalPage}'>
				<a id="page${totalPageNo}" href="activitiesDate?pageNo=${totalPageNo}">${totalPageNo}</a>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach var = 'totalPageNo' begin = '1' end='${totalPage}'>
				<a id="page${totalPageNo}" href="activitiesByDatePerPage?date=${selectDate}&pageNo=${totalPageNo}">${totalPageNo}</a>
			</c:forEach>
		</c:otherwise>
	</c:choose>
<!-- 		<a id="page1" href='activitiesDate?pageNo=1'>1</a> -->
<!-- 		<a id="page2" href='activitiesDate?pageNo=2'>2</a> -->
<!-- 		<a id="page3" href='activitiesDate?pageNo=3'>3</a> -->
<!-- 		<a id="page4" href='activitiesDate?pageNo=4'>4</a> -->
	</div>
	<div>
		<a href="<c:url value='/'/> ">首頁</a>
	</div>
</body>

</html>
