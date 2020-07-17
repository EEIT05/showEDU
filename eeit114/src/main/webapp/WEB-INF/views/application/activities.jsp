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
</head>
<body>
	<section>
		<div>
			<div class="container" style="text-align: center">
				<h1>活動公告</h1>
			</div>
		</div>
	</section>

	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container">
		<div class="row">
			<c:forEach var='activity' items='${activities}'>
				<div class="col-sm-6 col-md-3" style="width: 360px; height: 360px">
					<div class="thumbnail" style="width: 320px; height: 340px">
					<img width='100' height='200' 
						src="<c:url value='/getActPicture/${activity.actId}'/>"/>
						<div class="caption">
							<p>
								<b style='font-size: 16px;'>${activity.actTitle}</b>
							</p>
							<p>開始日期${activity.startDate}</p>
							<p>截止日期${activity.endDate}</p>

							<p>
								<a href="<spring:url value='/activity?id=${activity.actId}' />"
									class="btn btn-primary"> <span
									class="glyphicon-info-sigh glyphicon"></span>詳細資料
								</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>

</body>
</html>
