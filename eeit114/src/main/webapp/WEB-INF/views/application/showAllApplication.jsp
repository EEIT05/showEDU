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
window.onload = function() {
	var selectId = null;
	var a0 = document.getElementById("all");
	var a1 = document.getElementById("waitForPass");
	var a2 = document.getElementById("pass");
	var a3 = document.getElementById("fail");
	var a4 = document.getElementById("cancle");
	
	var allform = document.getElementById("applications")
	var pagebtns = document.getElementById("pagebtns");
	function selectStatus() {
		allform.innerHTML=''
		pagebtns.innerHTML=''
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "<c:url value='/allApplicationByStatus' />" + "?statusId="
				+ statusId, true);
		xhr.send();
		xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					console.log(JSON.parse(xhr.responseText));
					var re = JSON.parse(xhr.responseText);
					var content='';
					var pages='';
					for (var i = 0; i < re.application.length; i++) {
						var payStatus
						var passBtn
						var failBtn
						var payBtn
						
						if(re.application[i].payStatus == 0){
							payStatus = "未付款";
 						}else{
 							payStatus = "已付款";
 						}
						
						if(re.application[i].statusBean.statusId == 1){
							passBtn="<a class='btn btn-default'"+
							"href='passApplication/"+re.application[i].aplcId+"'>"+
							"<em class='fa fa-pencil'></em>&nbsp;審核</a>";
						}else{
							passBtn="";
						}
						
						if(re.application[i].statusBean.statusId == 1){
							failBtn="<a class='btn btn-danger'"+
								"href='failApplication/"+re.application[i].aplcId+"'>"+
								"<em class='fa fa-trash'></em>&nbsp;拒絕</a>";
						}else{
							failBtn="";
						}
						
						if(re.application[i].statusBean.statusId == 2  ){
							payBtn="<a class='btn btn-default'"+
							"href='changePayStatus/"+re.application[i].aplcId+"'>"+
							"<em class='fa fa-check'></em>&nbsp;已付款</a>";
								
						}else{
							payBtn="";
						}
						
						content += "<tr>"+
						"<td><a href='applicationDetail/"+re.application[i].aplcId+"'>"+re.application[i].aplcId+"</a></td>"+
						"<td>"+re.application[i].actClassBean.name+"</td>"+
						"<td>"+re.application[i].date+"</td>"+
						"<td>"+re.application[i].time+"</td>"+
						"<td>"+re.application[i].totalAmount+"</td>"+
						"<td>"+re.application[i].aplcTime+"</td>"+
						"<td>"+re.application[i].statusBean.descr+"</td>"+
						"<td>"+re.application[i].payStatus+"</td>"+
						"<td>"+passBtn+"</td>"+
						"<td>"+failBtn+"</td>"+
						"<td>"+payBtn+"</td>"+
						"</tr>";
						allform.innerHTML = content;
					
					}
					console.log(re.totalPage)
					for(j=1;j<=re.totalPage;j++){
						pages+="<a id='page"+j+"' onclick ='getpage("+re.selectedStatus+","+j+")'>"+j+"</a>";
						console.log(pages)
						pagebtns.innerHTML=pages;
					}
				}
			}
		}
// 	window.addEventListener('onclick', getpage, false);
	
// 	function getpage(statusId,pageNo) {
// 		allform.innerHTML=''
// 		var xhr = new XMLHttpRequest();
// 		xhr.open("GET", "<c:url value='/allApplicationByStatus' />" + "?statusId="
// 				+ statusId +"&pageNo="+pageNo, true);
// 		xhr.send();
// 		xhr.onreadystatechange = function() {
// 				if (xhr.readyState == 4 && xhr.status == 200) {
// 					console.log(JSON.parse(xhr.responseText));
// 					var re = JSON.parse(xhr.responseText);
// 					var content='';
// 					for (var i = 0; i < re.application.length; i++) {
// 						var payStatus
// 						var passBtn
// 						var failBtn
// 						var payBtn
						
// 						if(re.application[i].payStatus == 0){
// 							payStatus = "未付款";
//  						}else{
//  							payStatus = "已付款";
//  						}
						
// 						if(re.application[i].statusBean.statusId == 1){
// 							passBtn="<a class='btn btn-default'"+
// 							"href='passApplication/"+re.application[i].aplcId+"'>"+
// 							"<em class='fa fa-pencil'></em>&nbsp;審核</a>";
// 						}else{
// 							passBtn="";
// 						}
						
// 						if(re.application[i].statusBean.statusId == 1){
// 							failBtn="<a class='btn btn-default'"+
// 								"href='failApplication/"+re.application[i].aplcId+"'>"+
// 								"<em class='fa fa-trash'></em>&nbsp;拒絕</a>";
// 						}else{
// 							failBtn="";
// 						}
						
// 						if(re.application[i].statusBean.statusId == 2  ){
// 							payBtn="<a class='btn btn-default'"+
// 							"href='changePayStatus/"+re.application[i].aplcId+"'>"+
// 							"<em class='fa fa-default'></em>&nbsp;已付款</a>";
								
// 						}else{
// 							payBtn="";
// 						}
						
// 						content += "<tr>"+
// 						"<td><a href='applicationDetail/"+re.application[i].aplcId+"'>"+re.application[i].aplcId+"</a></td>"+
// 						"<td>"+re.application[i].actClassBean.name+"</td>"+
// 						"<td>"+re.application[i].date+"</td>"+
// 						"<td>"+re.application[i].time+"</td>"+
// 						"<td>"+re.application[i].totalAmount+"</td>"+
// 						"<td>"+re.application[i].aplcTime+"</td>"+
// 						"<td>"+re.application[i].aplcTime+"</td>"+
// 						"<td>"+re.application[i].statusBean.descr+"</td>"+
// 						"<td>"+passBtn+"</td>"+
// 						"<td>"+failBtn+"</td>"+
// 						"<td>"+payBtn+"</td>"+
// 						"</tr>";
// 						allform.innerHTML = content;
					
// 					}
// 					for(j=1;j<=re.totalPage;j++){
// 						pages+="<a id='page"+j+"' onclick ='getpage("+re.selectedStatus+","+j+")'>"+j+"</a>";
// 						pagebtns.innerHTML=pages
// 					}
// 				}
// 			}
// 		}

//分類按鈕事件===============================================================================
	a0.onclick = function() {
		statusId = 0;
		selectStatus();
	}
	a1.onclick = function() {
		statusId = 1;
		selectStatus();
	}
	a2.onclick = function() {
		statusId = 2;
		selectStatus();
	}
	a3.onclick = function() {
		statusId = 3;
		selectStatus();
	}
	a4.onclick = function() {
		statusId = 4;
		selectStatus();
	}
}
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
	<div class="col-md-10 col-md-offset-1">

		<div class="panel panel-default panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-xs-6">
						<h2 class="panel-title">預約清單</h2>
					</div>
					<div>
						<ul class="nav nav-tabs">
							<li><button id = 'all'>全部</button></li>
							<li><button id = 'waitForPass'>未審核</button></li>
							<li><button id = 'pass'>通過</button></li>
							<li><button id = 'fail'>未通過</button></li>
							<li><button id = 'cancle'>取消</button></li>
							
						</ul>
					</div>
				</div>
			</div>
			<!-- 		<form method='POST'> -->
			<!-- 			<input type='hidden' name='_method' value='DELETE'> -->
			<!-- 		</form> -->

			<c:choose>
				<c:when test="${empty allApplication}">
	    沒有任何預約資料<br>
				</c:when>
				<c:otherwise>
					<div class="panel-body">
						<table class="table table-striped table-bordered table-list">
							<thead>
								<tr>

									<th>申請編號</th>
									<th>類別</th>
									<th>日期</th>
									<th>時段</th>
									<th>金額</th>
									<th width=220px>申請時間</th>
									<th>狀態</th>
									<th>付款狀態</th>
									<th width=132px></th>
									<th width=132px></th>
									<th width=132px></th>
								</tr>
							</thead>
							<tbody id = 'applications'>
								<c:forEach var='application' items='${allApplication}'>
									<tr>
										<td><a href='applicationDetail/${application.aplcId}'>${application.aplcId}</a></td>
										<td >${application.actClassBean.name}</td>
										<td >${application.date}</td>
										<td >${application.time}</td>
										<td >${application.totalAmount}</td>
										<td >${application.aplcTime}</td>
										<td >${application.statusBean.descr}</td>
										<td >
											<c:choose>
												<c:when test="${application.payStatus == 1}">
	   													已付款
												</c:when>
												<c:otherwise>
														未付款
												</c:otherwise>
										</c:choose>
										</td>


										<td ><c:choose>
												<c:when test="${application.statusBean.statusId == 1 }">
													<a class="btn btn-default"
														href="passApplication/${application.aplcId}"><em
														class="fa fa-pencil"></em>&nbsp;審核</a>
												</c:when>
												<c:otherwise>

												</c:otherwise>
											</c:choose></td>
										<td ><c:choose>
												<c:when test="${application.statusBean.statusId == 1 }">
													<a class="btn btn-danger"
														href="failApplication/${application.aplcId}"><em
														class="fa fa-trash"></em>&nbsp;拒絕</a>
												</c:when>
												<c:otherwise>

												</c:otherwise>
											</c:choose></td>
										<td><c:choose>
												<c:when
													test="${application.statusBean.statusId == 2 }">
													<a id = "changePay" class="btn btn-default "
														href="changePayStatus/${application.aplcId}"><em
														class="fa fa-check"></em>&nbsp;已付款</a>
												</c:when>
												<c:otherwise>
													
												</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</c:otherwise>
			</c:choose>
			<hr>
	<div id="pagebtns">
		<c:choose>
			<c:when test= "${empty selectDate}">
				<c:forEach var = 'totalPageNo' begin = '1' end='${totalPage}'>
					<a id="page${totalPageNo}" href="allApplication?pageNo=${totalPageNo}">${totalPageNo}</a>
				</c:forEach>
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
	</div>
			 <a href="<c:url value='/'/> ">首頁</a>
		</div>
</body>
<script>
//查詢後分頁
window.addEventListener('onclick', getpage, false);
var selectId = null;
var a0 = document.getElementById("all");
var a1 = document.getElementById("waitForPass");
var a2 = document.getElementById("pass");
var a3 = document.getElementById("fail");
var a4 = document.getElementById("cancle");

var allform = document.getElementById("applications")
var pagebtns = document.getElementById("pagebtns");
function getpage(statusId,pageNo) {
	allform.innerHTML=''
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/allApplicationByStatus' />" + "?statusId="
			+ statusId +"&pageNo="+pageNo, true);
	xhr.send();
	xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				console.log(JSON.parse(xhr.responseText));
				var re = JSON.parse(xhr.responseText);
				var content='';
				var pages='';
				for (var i = 0; i < re.application.length; i++) {
					var payStatus
					var passBtn
					var failBtn
					var payBtn
					
					if(re.application[i].payStatus == 0){
						payStatus = "未付款";
						}else{
							payStatus = "已付款";
						}
					
					if(re.application[i].statusBean.statusId == 1){
						passBtn="<a class='btn btn-default'"+
						"href='passApplication/"+re.application[i].aplcId+"'>"+
						"<em class='fa fa-pencil'></em>&nbsp;審核</a>";
					}else{
						passBtn="";
					}
					
					if(re.application[i].statusBean.statusId == 1){
						failBtn="<a class='btn btn-danger'"+
							"href='failApplication/"+re.application[i].aplcId+"'>"+
							"<em class='fa fa-trash'></em>&nbsp;拒絕</a>";
					}else{
						failBtn="";
					}
					
					if(re.application[i].statusBean.statusId == 2  ){
						payBtn="<a class='btn btn-default'"+
						"href='changePayStatus/"+re.application[i].aplcId+"'>"+
						"<em class='fa fa-check'></em>&nbsp;已付款</a>";
							
					}else{
						payBtn="";
					}
					
					content += "<tr>"+
					"<td><a href='applicationDetail/"+re.application[i].aplcId+"'>"+re.application[i].aplcId+"</a></td>"+
					"<td>"+re.application[i].actClassBean.name+"</td>"+
					"<td>"+re.application[i].date+"</td>"+
					"<td>"+re.application[i].time+"</td>"+
					"<td>"+re.application[i].totalAmount+"</td>"+
					"<td>"+re.application[i].aplcTime+"</td>"+
					"<td>"+re.application[i].aplcTime+"</td>"+
					"<td>"+re.application[i].statusBean.descr+"</td>"+
					"<td>"+passBtn+"</td>"+
					"<td>"+failBtn+"</td>"+
					"<td>"+payBtn+"</td>"+
					"</tr>";
					allform.innerHTML = content;
				
				}
				//頁數按鈕
				for(j=1;j<=re.totalPage;j++){
					pages+="<a id='page"+j+"' onclick ='getpage("+re.selectedStatus+","+j+")'>"+j+"</a>";
					pagebtns.innerHTML=pages
				}
			}
		}
	}

</script>
</html>