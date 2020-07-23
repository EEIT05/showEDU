<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Activity</title>
</head>
<body>

	<section>
		<div>
			<div class="container" style="text-align: center">
				<h2>活動公告</h2>
			</div>
		</div>
	</section>
	<section class="container">

		<div class="row">
			<div class="col-md-5">
				<h3>${application.aplcId}</h3>
				<p>申請人：${application.memberBean.name}</p>
				<p>類別：${application.actClassBean.name}</p>
				<p>日期：${application.date}</p>
				<p>時段：${application.time}</p>
				<p>備註：${application.intro}</p>
				<div>
					<table>
						<tr>
							<td>${application.statusBean.descr}</td>
							<td>
							<c:choose>
								<c:when test="${application.payStatus == 1}">
	   													已付款
												</c:when>
								<c:otherwise>
														未付款
												</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</table>
				</div>


				<p>
					<strong>申請時間: </strong> <span class='label label-warning'>
						${application.aplcTime} </span>
				</p>
				<p>
					<a href="<spring:url value='/allApplication' />"
						class="btn btn-default"> <span
						class="glyphicon-hand-left glyphicon"></span>返回
					</a>


				</p>
			</div>
		</div>
		<div>
			<a href="<c:url value='/'/> ">首頁</a>
		</div>
	</section>
</body>
</html>
