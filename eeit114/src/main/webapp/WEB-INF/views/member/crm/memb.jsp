<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type='text/javascript'
	src='${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js'></script>
<title>會員資料</title>
<%-- <link rel='stylesheet' href='${pageContext.request.contextPath}/css/style.css'  type="text/css" /> --%>

<style>

body, html {
margin-top:150px; 
	height: auto;
	background-repeat: no-repeat;
	background-image: linear-gradient(rgb(186, 240, 255), rgb(51, 65, 156),
		rgb(25, 0, 71));
/* 	padding:2px; */
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
.panel-table .panel-footer .col{
 line-height: 34px;
 height: 34px;
 
}

.panel-table .panel-heading .col h3{
 line-height: 50px;
 height: 50px;
 
}

.panel-table .panel-body .table-bordered > tbody > tr > td{
  line-height: 34px;
  
}


</style>



</head>
<body>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
	     <jsp:include page="/WEB-INF/views/top.jsp" />
	     <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
              <div class="panel-heading">
              <div class="row">
	<div class="col col-xs-6">
		<h2 class="panel-title">會員資料</h2>
		</div>
		  
                </div>
              </div>
		<form method='POST'>
			<input type='hidden' name='_method' value='DELETE'>
		</form>

		<c:choose>
			<c:when test="${empty memberBean}">
	    沒有任何會員資料<br>
			</c:when>
			<c:otherwise>
			<div class="panel-body">
                <table class="table table-striped table-bordered table-list">
                  <thead>
                    <tr >

<!-- 						<th >編號</th> -->
						<th >姓名</th>
						<th >電話</th>
						<th>地址</th>
						<th >帳號</th>
						<th >照片</th>
						<th ><em class="fa fa-cog"></em>&nbsp;資料修改</th>
						
					</tr>
					</thead>
                  <tbody>

			
						<tr >
<%-- 							<td >${loginMember.memberId}</td> --%>
							<td>${memberBean.name}</td>
							<td>${memberBean.phone}</td>
							<td>${memberBean.address}</td>
							<td>${memberBean.account}</td>
							
<!-- 		              會員圖片 -->

					  	<td><img width='60' height='72'
								src='picture/${memberBean.memberId}' /></td>
					
								
							<td ><a class="btn btn-success" 
								href="${pageContext.request.contextPath}/member/crm/mem/${memberBean.memberId}"><em class="fa fa-pencil"></em>&nbsp;編輯</a></td>
						
						
						</tr>
				
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		<hr>
<!-- 		<a href='mem'>新增會員資料</a> &nbsp;&nbsp;&nbsp; -->
		<a href="<c:url value='/'/> ">首頁</a>
	</div></div>


</body>
</html>