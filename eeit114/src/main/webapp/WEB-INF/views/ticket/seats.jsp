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

#content {
	width: 820px;
	margin-left: auto;
	margin-right: auto;
}

.seatSelected {
	width: 40px;
	height: 40px;
	margin: 5px;
	border-radius: 50%;
	background-color: lightblue;
}

.seatNormal {
	width: 40px;
	height: 40px;
	margin: 2px; 
	border-radius: 50%;
	background-color: lightgray;
}

.seatSold {
	width: 40px;
	height: 40px;
	margin: 2px;
	border-radius: 50%;
	background-color: pink;
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
</style>
</head>
<body>
	<div style="text-align: center;">
		<%-- 	<a>${seats}</a> --%>
		<h1>座位圖表</h1>
	</div>
	<!--  -->
	<div class='screan'>
		<div style="text-align: center;">
			<a style='font-size: 40px; color: white;'>銀 幕</a> <br> <br>
			<a style='font-size: 15px; color: white;'>選擇您希望購買的座位.請注意系統將自動為您保留可訂的最佳座位,
				每筆交易最多可購買10張電影票</a>
		</div>
	</div>
	<div style="text-align: center;" class='icon'>
		<table>
			<tr>
				<td class='seatSold'></td>
				<td class='seatNormal'></td>
				<td class='seatSelected'></td>
			</tr>
			<tr>
				<td>已賣出</td>
				<td>可選取</td>
				<td>已選取</td>
			</tr>

		</table>
	</div>
	<!-- ==============座位圖================= -->
	
	<div>
	<table>
	<c:forEach var="seat"   items="${seats}" >
        <tr><td>${seat.lineLetters}</td>
	<c:forEach var="seat"   items="${seats}" >
        	<td class='seatSold' id='${seat.rowNumber}'>${seat.rowNumber}</td>

   </c:forEach>
        </tr>
   </c:forEach>
	</table>
	</div>

	<script language="Javascript">
		
	</script>
</body>
</html>
