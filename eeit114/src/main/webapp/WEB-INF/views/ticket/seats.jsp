<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>EDUShowTime-hot售電影</title>
<style>
.center {
	style ="text-align: center"
 white-space: nowrap
}

.content {
	width: 900px;
	margin-left: auto;
	margin-right: auto;
}

.seatSelected {
	width: 40px;
	height: 40px;
	margin: 5px;
	border-radius: 50%;
	background-color: blue;
}

.seatNormal {
	width: 40px;
	height: 40px;
	margin: 2px;
	border-radius: 50%;
	background-color: darkgray;
}

.seatSold {
	width: 40px;
	height: 40px;
	margin: 2px;
	border-radius: 50%;
	background-color: #FF8888;
}

.road {
	width: 40px;
	height: 40px;
}

.screan {
	width: 800px;
	height: 100px;
	background-color: darkgray;
	margin-left: auto;
	margin-right: auto;
}

.screanword {
	margin-left: auto;
	margin-right: auto;
}

.icon {
	width: 600px;
	height: 100px;
	margin-left: auto;
	margin-right: auto;
}
td{
 onclick : checkseat(id);
}
</style>
</head>
<body>
	<div style="text-align: center;">
		<%-- 	<a>${seats}</a> --%>
		<h1>座位圖表${totalTicket}</h1>
	</div>
	<!--  -->
	<div class='screan'>
		<div style="text-align: center;">
			<a style='font-size: 25px; color: white;'>電影布幕</a> <br> <br>
			<a style='font-size: 15px; color: white;'>選擇您希望購買的座位.請注意系統將自動為您保留可訂的最佳座位,
				每筆交易最多可購買10張電影票</a><br> <a style='font-size: 15px; color: white;'>剩餘座位數:
				${reSeat}</a>
		</div>
	</div>
	<div style="text-align: center;" class='icon'>
		<table>
			<tr style='height: 40px'>
				<td></td>
			</tr>
			<tr>
				<td class='seatSold'></td>
				<td class='seatNormal'></td>
				<td class='seatSelected'></td>
			</tr>
			<tr>
				<td>已售出</td>
				<td>可選取</td>
				<td>已選取</td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<!-- ==============座位圖================= -->

	<!-- 	<div class='content'> -->
	<!-- 		<table> -->
	<%-- 			<c:forEach var="line" items="${line}"> --%>
	<!-- 				<tr> -->
	<%-- 					<td width='60'>${line}排</td> --%>
	<%-- 					<c:forEach var="row" items="${row}"> --%>

	<%-- 								<c:if test="${line == C && row == 6 }"> --%>
	<!-- 								<td class='road'>走</td> -->
	<%-- 								</c:if> --%>
	<%-- 								<c:if test="${ line == C && row == 16}"> --%>
	<!-- 								<td class='road'>走</td> -->
	<%-- 								</c:if> --%>
	<%-- 								<c:if test="${line == F && row == 6 }"> --%>
	<!-- 								<td class='road'>道</td> -->
	<%-- 								</c:if> --%>
	<!-- 						顯示可選擇位置 -->
	<!-- 						顯示走道位置 -->
	<%-- 						<c:choose> --%>
	<%-- 							<c:when test="${line == 'F' && row == 8 }"> --%>
	<!-- 								<td style="text-align: center;" class='seatNormal' id=''><a -->
	<%-- 									href='#'>${row}</a></td> --%>
	<%-- 							</c:when> --%>
	<%-- 							<c:when test="${line == 'F' && row == 9 }"> --%>
	<!-- 								<td style="text-align: center;" class='seatNormal' id=''><a -->
	<%-- 									href='#'>${row}</a></td> --%>
	<%-- 							</c:when> --%>
	<%-- 							<c:when test="${line == 'H' && row == 11 }"> --%>
	<!-- 								<td style="text-align: center;" class='seatNormal' id=''><a -->
	<%-- 									href='#'>${row}</a></td> --%>
	<%-- 							</c:when> --%>
	<%-- 							<c:when test="${line == 'H' && row == 12 }"> --%>
	<!-- 								<td style="text-align: center;" class='seatNormal' id=''><a -->
	<%-- 									href='#'>${row}</a></td> --%>
	<%-- 							</c:when> --%>
	<%-- 							<c:when test="${line == 'H' && row == 13 }"> --%>
	<!-- 								<td style="text-align: center;" class='seatNormal' id=''><a -->
	<%-- 									href='#'>${row}</a></td> --%>
	<%-- 							</c:when> --%>
	<%-- 							<c:otherwise> --%>
	<%-- 								<c:choose> --%>
	<%-- 									<c:when test="${row == 6 || row == 16}"> --%>
	<!-- 										<td class='road'></td> -->
	<%-- 										<td style="text-align: center;" class='seatSold' id=''>${row}</td> --%>
	<%-- 									</c:when> --%>
	<%-- 									<c:otherwise> --%>
	<%-- 										<td style="text-align: center;" class='seatSold' id=''>${row}</td> --%>
	<%-- 									</c:otherwise> --%>
	<%-- 								</c:choose> --%>
	<%-- 							</c:otherwise> --%>
	<%-- 						</c:choose> --%>

	<%-- 					</c:forEach> --%>
	<!-- 				</tr> -->
	<%-- 			</c:forEach> --%>
	<!-- 		</table> -->
	<!-- 	</div> -->




	<div class='content'>
		<c:if test="${reSeat != 200}">
			<table class='seatTable'>
				<c:forEach var="seats" items="${seats}">
					<c:if test="${seats != 0}">
						<c:choose>
							<c:when test="${seats == 1}">
								<!-- 顯示排 -->
								<tr>
									<td width='60'>A 排</td>
									<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 20}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								</tr>
							</c:when>
							<c:when test="${seats == 21}">
								<!-- 顯示排 -->
								<tr>
									<td width='60'>B 排</td>
									<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 40}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								</tr>
							</c:when>
							<c:when test="${seats == 41}">
								<!-- 顯示排 -->
								<tr>
									<td width='60'>C 排</td>
									<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 60}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								</tr>
							</c:when>
							<c:when test="${seats == 61}">
								<!-- 顯示排 -->
								<tr>
									<td width='60'>D 排</td>
									<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 80}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								</tr>
							</c:when>
							<c:when test="${seats == 81}">
								<!-- 顯示排 -->
								<tr>
									<td width='60'>E 排</td>
									<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 100}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								</tr>
							</c:when>
							<c:when test="${seats == 101}">
								<!-- 顯示排 -->
								<tr>
									<td width='60'>F 排</td>
									<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 120}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								</tr>
							</c:when>
							<c:when test="${seats == 121}">
								<!-- 顯示排 -->
								<tr>
									<td width='60'>G 排</td>
									<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 140}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								</tr>
							</c:when>
							<c:when test="${seats == 141}">
								<!-- 顯示排 -->
								<tr>
									<td width='60'>H 排</td>
									<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 160}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								</tr>
							</c:when>
							<c:when test="${seats == 161}">
								<!-- 顯示排 -->
								<tr>
									<td width='60'> I 排</td>
									<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 180}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								</tr>
							</c:when>
							<c:when test="${seats == 181}">
								<!-- 顯示排 -->
								<tr>
									<td width='60'>J 排</td>
									<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 200}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								</tr>
							</c:when>

							<c:when test="${seats == 6}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 16}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 26}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 36}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 46}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 56}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 66}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 76}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 86}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 96}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 106}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 116}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 126}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 136}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 146}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 156}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 166}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 176}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 186}">
								<!-- 下一排 -->
								<td class='road'></td>
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
							</c:when>
							<c:when test="${seats == 195}">
								<!-- 下一排 -->
								<td style="text-align: center;" class='seatSold' id='${seats}'>${seats}</td>
								<td class='road'></td>
							</c:when>
							<c:otherwise>
								<!-- 若為0則代表未賣出 -->
								<td style="text-align: center;" class='seatSold' id=''>${seats}</td>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${seats == 0}">
						<td style="text-align: center;" class='seatNormal' id=''>${seats}</td>
					</c:if>
				</c:forEach>
			</table>
		</c:if>


<!-- ============================空位圖================================= -->
		<c:if test="${reSeat == 200}">
			<table>
				<c:forEach var="line" items="${line}">
					<tr>
						<td width='60'>${line}排</td>
						<c:forEach var="row" items="${row}">

							<c:if test="${line == C && row == 6 }">
								<td class='road'>走</td>
							</c:if>
							<c:if test="${ line == C && row == 16}">
								<td class='road'>走</td>
							</c:if>
							<c:if test="${line == F && row == 6 }">
								<td class='road'>道</td>
							</c:if>


							<c:choose>
								<c:when test="${row == 6 || row == 16}">
									<td class='road'></td>
									<td style="text-align: center;" class='seatSold' id=''>${row}</td>
								</c:when>
								<c:otherwise>
									<td style="text-align: center;" class='seatSold' id=''>${row}</td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	<script src="jquery-1.7.2.min.js"></script>
	<script language="Javascript">
		function checkseat(id){
			alert(id);
		}
		$(document).ready(function(){   //畫面一載入就執行此function
			$(".seatTable td").click(function(){
				alert("123")
			});
			})
	</script>
</body>
</html>
