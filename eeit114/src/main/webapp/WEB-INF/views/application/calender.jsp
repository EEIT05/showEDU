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
	background-color: #271a59;
	padding: 5px;
	height:100vh;
}
.dateForm td{
	width: 156px;
	height: 40px;
	font-size: 16px;
}
.noon{
	width: 156px;
	border-radius: 20px;
	text-align: center;
	background-color:rgb(240, 193, 119);
}
.night{
	width: 156px;
	border-radius: 20px;
	text-align: center;
	background-color:rgb(151, 206, 243);
}

#weektable{
	margin-bottom: 0;
	background-color: rgb(186, 206, 248);
}
#weektable th{
	width: 156px;
	text-align: center;
	font-family: "微軟正黑體";
}

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
	height: 50px;
}

.panel-table .panel-heading .col h3 {
	line-height: 50px;
	height: 50px;
}

.panel-table .panel-body .table-bordered>tbody>tr>td {
	line-height: 34px;
}
</style>



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
	<div class="col-md-10 col-md-offset-1">

		<div class="panel panel-default panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-xs-6">
						<h2 class="panel-title">行事曆</h2>
					</div>

				</div>
			</div>

			<select id='SelectDate'>
				<option value="-1" label="請選擇日期" />
				<option value="2020/07" label='2020年7月' />
				<option value="2020/08" label='2020年8月' />
				<option value="2020/09" label='2020年9月' />
			</select>


			<div class="panel-body">
			
				<table id="weektable"
					class="table table-striped table-bordered table-list">

				</table>
				<table id="table1"
					class="table table-striped table-bordered table-list">

				</table>

				<hr>
				<a href="<c:url value='/'/> ">首頁</a>
			</div>
</body>

<script>
	//日期表格
	var selectElement = document.getElementById("SelectDate");
	selectElement.addEventListener('change', function() {
		var t = document.getElementById("table1");
		var w = document.getElementById("weektable");
		w.innerHTML = "";
		w.innerHTML="<thead><tr><th>日</th><th>一</th><th>二</th>"+
	        "<th>三</th><th>四</th><th>五</th><th>六</th></tr></thead>"
		t.innerHTML = "";
		var selectMonth = selectElement.options[selectElement.selectedIndex].value;
		console.log(selectMonth.substring(5));

		var selectMonthNumber = parseInt((selectMonth.substring(5)));
		var selectYearNumber = parseInt((selectMonth.substring(0, 5)));

		var myDates;
		var firstdate;
		var lastdate;
		if (selectMonthNumber == 1 || selectMonthNumber == 3
				|| selectMonthNumber == 1 || selectMonthNumber == 5
				|| selectMonthNumber == 7 || selectMonthNumber == 8
				|| selectMonthNumber == 10 || selectMonthNumber == 12) {
			myDates = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
					17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 ];

			var date = new Date(selectYearNumber, (selectMonthNumber - 1), 1);
			firstdate = date.getDay();
			var date2 = new Date(selectYearNumber, (selectMonthNumber - 1), 31);
			lastdate = date2.getDay();

		} else if (selectMonthNumber == 2) {
			myDates = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
					17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28 ];

			var date = new Date(selectYearNumber, (selectMonthNumber - 1), 1);
			firstdate = date.getDay();
			var date2 = new Date(selectYearNumber, (selectMonthNumber - 1), 28);
			lastdate = date2.getDay();

		} else {
			myDates = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
					17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 ];

			var date = new Date(selectYearNumber, (selectMonthNumber - 1), 1);
			firstdate = date.getDay();
			var date2 = new Date(selectYearNumber, (selectMonthNumber - 1), 30);
			lastdate = date2.getDay();
		}

		for (a = 0; a < firstdate; a++) {
			myDates.unshift("");
		}
		for (b = 0; b < (6 - lastdate); b++) {
			myDates.push("");
		}

		var tArray = new Array(); //先宣告一
		var c = 0;
		if ((myDates.length / 7) == 5) {
			for (var k = 0; k < 5; k++) { //一維長度為i,i為變數，可以根據實際情況改變
				tArray[k] = new Array(); //宣告二維，每一個一維數組裡面的一個元素都是一個數組；
				for (var l = 0; l < 7; l++) { //一維數組裡面每個元素陣列可以包含的數量p，p也是一個變數；
					tArray[k][l] = myDates[c]; //這裡將變數初始化，我這邊統一初始化為空，後面在用所需的值覆蓋裡面的值
					c++;
				}
			}

			console.log(tArray);

			var t = document.getElementById("table1");

			for (var i = 0; i < 5; i++) {
				t.insertRow();
				for (var j = 0; j < 7; j++) {
					t.rows[i].insertCell(j);
					t.rows[i].cells[j].innerHTML = "<div id =date"+tArray[i][j]+" class='dateForm'>"
							+ "<table><tr><td>"
							+ tArray[i][j]
							+ "</td></tr><tr><td id = noon"+tArray[i][j]+"></td></tr><tr><td id = night"+tArray[i][j]+"></td></tr></table></div>";
				}
			}
		} else if ((myDates.length / 7) == 6) {
			for (var k = 0; k < 6; k++) { //一維長度為i,i為變數，可以根據實際情況改變
				tArray[k] = new Array(); //宣告二維，每一個一維數組裡面的一個元素都是一個數組；
				for (var l = 0; l < 7; l++) { //一維數組裡面每個元素陣列可以包含的數量p，p也是一個變數；
					tArray[k][l] = myDates[c]; //這裡將變數初始化，我這邊統一初始化為空，後面在用所需的值覆蓋裡面的值
					c++;
				}
			}

			console.log(tArray);

			var t = document.getElementById("table1");

			for (var i = 0; i < 6; i++) {
				t.insertRow();
				for (var j = 0; j < 7; j++) {
					t.rows[i].insertCell(j);
					t.rows[i].cells[j].innerHTML = "<div id =date"+tArray[i][j]+" class='dateForm'>"
							+ "<table><tr><td>"
							+ tArray[i][j]
							+ "</td></tr><tr><td id = noon"+tArray[i][j]+"></td></tr><tr><td id = night"+tArray[i][j]+"></td></tr></table></div>";
				}
			}
		}
		console.log(document.getElementById("noon23").innerText);

	})
//======================================塞入申請項目============================================
	selectElement.addEventListener('change', function() {
		var t = document.getElementById("table1");
		var selectMonth = selectElement.options[selectElement.selectedIndex].value;
		console.log(selectMonth.substring(5));

		var selectMonthNumber = parseInt((selectMonth.substring(5)));
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "<c:url value='/showAplcBeanByMonth' />" + "?month="
				+ selectMonthNumber, true);
		xhr.send();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var list = JSON.parse(xhr.responseText);
				console.log(list);
				for(var i=0; i < list.length ; i++){
					if( list[i].time == new String("13:00")){
						document.getElementById("noon"+list[i].date).innerHTML ="<div class='noon'>13:00</div>";
					}else if( list[i].time == new String("18:00")){
						document.getElementById("night"+list[i].date).innerHTML ="<div class='night'>18:00</div>";
					}else{
						;
					}
					console.log((list[i].time));
					console.log(document.getElementById("noon"+list[i].date));
				}
			}
		
		}
	})
</script>

</html>