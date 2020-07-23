<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta charset="UTF-8">
<!-- <link rel='stylesheet' -->
<%-- 	href='${pageContext.request.contextPath}/css/styles.css' --%>
<!-- 	type="text/css" /> -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>EDUShowTime-hot售電影</title>
<style>
.center {
	style ="text-align: center"
 white-space: nowrap
}

#content {
	width: 820px;
	margin-left: auto;
	margin-right: auto;
}

.flex {
	display: inline-flex;
	height: 250px;
	margin: 30px 0px 0px 0px;
	vertical-align: top;
	text-align: center;
}

.flex-300 {
	width: 520px;
}

.flex-150 {
	width: 80px;
}

.item {
	height: 60px;
	text-align: center;
	line-height: 50px;
/* 	border: 1px solid; */
}

.item1 {
	flex: 1 2 200px;
	text-align: left;
	width: 400px;
}

.item2 {
	flex: 2 1 100px;
	text-align: right;
	width: 300px;
}	
</style>
</head>
<body>
<div>
<h1 style="text-align: center;">訂票資訊</h1>
</div>
	<div style="text-align: center;">
		<div class='flex'>
			<!-- =============================電影資訊===================== -->
			<div class='item item1'>
				<table style="text-align: left;">
					<tr>
						<td width='100'><img style="margin: auto;" width='100'
							height=145 alt='${movie.chName}' title='${movie.chName}'
							src="<c:url value='/getMovieImg/${movie.movieId}' />" /></td>
						<td>
							<table>
								<tr>
									<td>
										<table>
											<tr>
												<td><img style="text-align: center" width='50'
													height='60' alt='${movie.movieLevelBean.level}'
													title='${movie.movieLevelBean.level}'
													src="<c:url value='/getMovieLevelImg/${movie.movieLevelBean.movieLevelId}' />" />
												</td>
												<td>
											<tr>
												<td><a>${movie.chName}</a></td>
											</tr>
											<tr>
												<td><a>${movie.enName}</a></td>
											</tr>

										</table>
									</td>
								</tr>
								<tr>
								</tr>
								<tr>
									<td><p style='font: 12px saddlebrown;'>${movie.enName}</p></td>
								</tr>
								<tr>
									<td><p style='font-size: 10px;'>上映日期：${movie.premierDate}</p></td>
								</tr>


							</table>
						</td>
				</table>
			</div>

			<!-- ==========================下拉選單================================ -->
			<div class='item item2'>
					<form:form method='POST' modelAttribute="movie"
						class='form-horizontal'>
						<div style='text-align: right'>
							<label for="movieId"> </label>
								<form:select id='selectMovie' path="movieId" name="movielist"
									onchange="changeMovie(this.value);">
									<form:options items="${bookingmovieList}" />
								</form:select>
							<br>
								<form:select id='selectDate' path="movieId" name="movielist">
									<option>請選擇日期</option>
									<form:options value='${DateList.key}' items="${DateList}" />
								</form:select>
							<br>
								<form:select id='selectTime' path="movieId" name="movielist">
									<option>請選擇時間</option>
									<form:options value='${TimeList.value}' items="${TimeList}" />
								</form:select>
						</div>
					</form:form>
			</div>
		</div>
		<!-- ======================選擇電影票======================== -->
		<div class='content'>
			<form style='margin-left: 200px'action='movieShowTime' method='post'>
				<c:forEach var='ticket' items='${tickets}'>
					<table border='1px solid' style='text-align: center'>
						<tr>
							<td><img style="margin: auto" width='180' height='120'
								alt='${ticket.name}' title='${ticket.name}'
								src="<c:url value='/getTicketImg/${ticket.movieTicketId}' />" />
							</td>
							<td style='text-align: left; width: 500px;'>${ticket.name}<br>${ticket.info}</td>
								<div style='text-align: right'>
							<td width='200'>價格:${ticket.price}
									<select id='ticket${ticket.movieTicketId}' name='ticket'
										onchange='checkTicketCount()'>
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
									</select>
							</td>
								</div>
						</tr>
					</table>

				</c:forEach>
			</form>
			<div></div>
		</div>
	</div>
	<div style='text-align: center'>
		<button type="button" onclick='send()'>下一步</button>
	</div>
	
	<!-- 	<script src="../js/jquery-3.5.1.min.js"></script> -->
	<script language="Javascript">
		//============確認訂票張數==============
		function checkTicketCount() {
			var sum = 0;
			for (i = 1; i < 4; i++) {
				var count = parseInt(document.getElementById("ticket" + i).value);
				sum = sum + count;
			}
			if (sum > 10) {
				alert("單次線上訂票不可超過10張，請重新選擇數量");
			}
		}	
		function test(){
			window.location.href = "<spring:url value='/test' />"
		}
		
// 		訂票-電影選擇
		function changeMovie(movieId) {
			window.location.href = "<spring:url value='/movieShowTime?movieId="
					+ movieId + "' />"
		}
		
		function send() {
			var movieId = document.getElementById("selectMovie").value;
			var d = document.getElementById("selectDate");
			var t = document.getElementById("selectTime");
			
			var date = d.options[d.selectedIndex].text;
			var time = t.options[t.selectedIndex].text;
			if(date !="請選擇日期" && time != "請選擇時間" ){
				window.location.href = "<spring:url value='/seatmap?movieId="
					+ movieId + "&date=" + date +
					"&time=" + time + "' />";
			}else{
				alert("請輸入日期 & 時間")
			}

// 			for (i = 1; i < 3; i++) {
// 				var count = parseInt(document.getElementById("ticket" + i).value);
// 				sum = sum + count;
// 			}

		}
	</script>
</body>
</html>
