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

/* .panel .panel-default .panel-table{ */
/* 	position: absolute; */
/* 	top: 100px; */
/* 	left: 60px; */
/* }  */

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

	<%-- 	<%@ include file="../back.jsp" %>	 --%>
	<div class="col-md-10 col-md-offset-1 ">

		<div class="panel panel-default panel-table ">
			<div class="panel-heading ">
				<div class="row">
					<div class="col col-xs-6">
						<h2 class="panel-title">場地租借規定</h2>
					</div>

				</div>
			</div>
			<div class="panel-body">
				<h3>請先閱讀場地租借規定後再進行下一步</h3>
			</div>
			<div>
				&lt;包場&gt;<br> 
				*請於7-10個工作天前預定<br>
				*片長超過150分鐘或特殊節日需加價，確認後於付款前另行告知。<br>
				*包廳團劃活動受限於片商拷貝調度，強片首二週僅能安排大廳映演。<br> *團劃包廳須一人一票對號入座(無自由入座方式)<br>
				*包廳與團劃活動依現場排片為主 / 席次安排以預訂先後順序為主<br>
				*團劃包廳活動為專屬優惠活動，出票後恕不退換，主辦單位請勿提供外食<br> *週末影片，出票時間為當週三下午，須付款後才可出票<br>
				*新片上映首週、假日與假日前一晚,需搭配可樂爆米花組合餐飲。<br> 
				<br>
				&lt;影廳租借&gt;<br>
				*影廳內不可使用拉炮、紙花與煙霧等器材<br> *數位放映素材僅限單一一種，藍光或DVD或NOTEBOOK(三選一)<br>
				*影廳租借活動請於7-10個工作天前預定<br> *數位放映另收取單場10000元數位放映費用<br>
			</div>
			<hr>
			<form action="agreeRule">
				<input type="checkbox" id="agree" name="agreement" value="agree">
				<label for="agree"> 我已閱讀並同意以上規定</label><br> 
				<input type="submit" value="下一步">
			</form>

			<hr>

			<a href="<c:url value='/'/> ">首頁</a>
		</div>
	</div>
</body>

</html>