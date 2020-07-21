<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Activity</title>
</head>
<body>
	<section>
		<div>
			<div class="container" style="text-align: center">
				<h2>產品資料</h2>
			</div>
		</div>
	</section>
	<section class="container">
		<img width='150' height='200'
			src="<c:url value='/getActPicture/${activity.actId}'/>" />
		<div class="row">
			<div class="col-md-5">
				<h3>${activity.actTitle}</h3>
				<p>開始日期${activity.startDate}</p>
				<p>截止日期${activity.endDate}</p>

				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${activity.descr}</p>

				<p>
					<strong>發佈日期: </strong> <span class='label label-warning'>
						${activity.postDate} </span>
				</p>
				<p>
					<a href="<spring:url value='/activities' />"
						class="btn btn-default"> <span
						class="glyphicon-hand-left glyphicon"></span>返回
					</a>
					<c:choose>
						<c:when test="${loginMember.userType == 'A'}">
							<a href="<spring:url value='/activity/update/${activity.actId}' />"
								class="btn btn-default"> 修改 </a>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>


				</p>
			</div>
		</div>
	</section>
</body>
</html>
