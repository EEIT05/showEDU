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
	window.onload = function() {
		var btn = document.getElementById("findByDate");
		
		btn.onclick = function() {
			var selectDate = document.getElementById("mydate").value;
			console.log(selectDate);
			var xhr = new XMLHttpRequest();
			xhr.open("GET", "<c:url value='/activitiesByDate' />" + "?date="
					+ selectDate, true);
			xhr.send();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {

					var content = "<table border='1'>";
					content += "<tr><th>編號</th><th>活動名稱</th><th>開始日期</th><th>截止日期</th><th>上傳日期";
					var activities = JSON.parse(xhr.responseText);
					for (var i = 0; i < activities.length; i++) {
						content += "<tr><td align='center'>"
								+ activities[i].actId + "</td>" + "<td>"
								+ "<a href='activity?id=" + activities[i].actId
								+ "'>" + activities[i].actTitle + "</a></td>"
								+ "<td>"
								// 								+ activities[i].actTitle + "</td>" + "<td>"
								+ activities[i].startDate + "</td>" + "<td>"
								+ activities[i].endDate + "</td>"
								+ "<td align='right'>" + activities[i].postDate
								+ "</td>"
					}
					content += "</table>";
					var divs = document.getElementById("container");
					divs.innerHTML = content;
				}
			}
		}
	}
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

			<input type='date' name='date' id='mydate'>
			<button id='findByDate'>查詢</button>

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

</body>
</html>
