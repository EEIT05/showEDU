<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<head>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/style.css' type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

<style>

/* .navbar-brand{
  margin-left: 20px;

} */
.navbar {
	background: #271a59;
	height: 100px;
	/*     height: 100px; */
	margin-bottom: 0px;
}

.navbar-collapse {
	margin-left: 10px;
	margin-top: 8px;
}

.nav-item::after {
	content: '';
	display: block;
	width: 0px;
	height: 2px;
	background: #fec400;
	transition: 0.2s;
	color: #fec400;
}

.dropdown {
	color: gray;
}

.nav-item:hover:after {
	width: 100%;
}

.navbar-dark .navbar-nav .active>.nav-link, .navbar-dark .navbar-nav .nav-link.active,
	.navbar-dark .navbar-nav .nav-link.show, .navbar-dark .navbar-nav .show>.nav-link,
	.navbar-dark .navbar-nav .nav-link:focus, .navbar-dark .navbar-nav 
.nav-link:hover {
	color: #fec400;
	margin-left: 10px;
	margin-right: 5px;
}

.nav-link {
	padding: 2px;
	transition: 0.2s;
	margin-left: 20px;
	margin-right: 8px;
	font-size: 18px;
}

.dropdown-item.active, .dropdown-item:active {
	color: #282a2c;
}

.dropdown-item:focus, .dropdown-item:hover {
	background: #fec400;
}

/* .my-nav{
position: absolute;
z-index: 10;
width: 100%;

} */
.carousel-item {
	/* 	height: 75vh; */
	height: 75vh;
	background: no-repeat center center scroll;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	margin-top: 80px;
}

.text-box {
	position: absolute;
	top: 40%;
	left: 15%;
	right: 15%;
	color: #000;
	transform: translateY(-50%);
}

.pp {
	color: #ffffff;
	margin-left: 50%;
}

.p2 {
	color: #ffffff;
	margin-left: 5%;
}

.mmimg {
	border-radius: 50%;
	margin-left: 15px;
	margin-bottom: 5px;
}

.vv {
	margin-left: 100px;
	color: #fec400;
}

.media {
	margin-left: 80px;
	width: 80%;
}

.logo{
	width:100px;
	height:100px;
}
</style>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

	<nav class="navbar  navbar-expand-lg navbar-dark fixed-top">
		<img class="logo" src="<c:url value='/images/LOGO.jpg' />" width="100"
			height="100"> <a href="<c:url value='/'/> "
			class="navbar-brand">
			<h3>SHOW EDU</h3>
		</a>

		<button class="navbar-toggler" type="button" data-target="#navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav">

				<!-- 					<li class="nav-item"><a href="#" class="nav-link">影城介紹</a></li> -->
				<li class="nav-item"><a href="<c:url value='/movieList' />"
					class="nav-link">電影介紹</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navdrop"
					role="button" data-toggle="dropdown" data-hover="dropdown">訂票</a>
					<div class="dropdown-menu" aria-labelledby="navdrop">
						<a href="#" class="dropdown-item">訂票</a> <a href="#"
							class="dropdown-item">票種</a> <a href="#" class="dropdown-item"></a>
					</div>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navdrop"
					role="button" data-toggle="dropdown" data-hover="dropdown">餐飲商品</a>
					<div class="dropdown-menu" aria-labelledby="navdrop">
						<a href="#" class="dropdown-item">餐飲</a> <a href="showproduct"
							class="dropdown-item">周邊商品</a> <a
							href=<c:url value='/product/backSelect'/> class="dropdown-item">商品後台</a>
						<!-- <a href="#" class="dropdown-item">Service3</a> -->
					</div>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navdrop"
					role="button" data-toggle="dropdown" data-hover="dropdown">包場</a>
					<div class="dropdown-menu" aria-labelledby="navdrop">
						<a href="<c:url value='/application/add'/>" class="dropdown-item">包場預約</a>
						<c:choose>
							<c:when test="${memberBean.userType eq 'A'}">
								<a href="<c:url value='/allApplication'/>" class="dropdown-item">查看訂單</a>
							</c:when>
							<c:otherwise>
								<a href="<c:url value='/yourApplication' />"
									class="dropdown-item">查看個人訂單</a>
							</c:otherwise>
						</c:choose>
						<a href="<c:url value='/showCalender' />" class="dropdown-item">查看行事曆</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navdrop"
					role="button" data-toggle="dropdown" data-hover="dropdown">活動</a>
					<div class="dropdown-menu" aria-labelledby="navdrop">
						<a href="<c:url value='/activities'/>" class="dropdown-item">活動公告</a>
						<c:if test="${memberBean.userType eq 'A'}">
							<a href="<c:url value='/activitiesDate'/>" class="dropdown-item">活動公告查詢</a>
							<a href="<c:url value='/activities/add'/>" class="dropdown-item">新增活動</a>
						</c:if>
						<a href="#" class="dropdown-item"></a>
					</div></li>


				<li class="nav-item"><a href="<c:url value='/boards' />"
					class="nav-link">討論區</a></li>

<!-- 				<div> -->


					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navdrop" role="button"
						data-toggle="dropdown" data-hover="dropdown">會員系統</a>
						<div class="dropdown-menu" aria-labelledby="navdrop">

							<c:if test="${  empty memberBean }">
								<a href='login' class="dropdown-item">會員登入</a>
							</c:if>

							<c:if test="${ ! empty memberBean }">
								<a href='loginout' class="dropdown-item">會員登出</a>
							</c:if>

							<c:if test="${  empty memberBean }">
								<a href='mem' class="dropdown-item"> 會員註冊</a>
							</c:if>

							<c:if test="${ ! empty memberBean}">
								<a href="memb" class="dropdown-item">會員資料</a>
							</c:if>

							<c:if test="${memberBean.userType eq 'M'}">
								<a href="orderList" class="dropdown-item">我的訂單</a>
							</c:if>

							<c:if test="${memberBean.userType eq 'A'}">
								<a href="../../back" target="_blank" class="dropdown-item">後台管理</a>
							</c:if>

							<c:if test="${memberBean.userType eq 'A'}">
								<a href="showAllMembers" class="dropdown-item">後台會員資料</a>
							</c:if>





						</div></li>


<!-- 				</div> -->
				<li class="nav-item active"><a href="ShowCartContent"
					class="nav-link"><i class="fa fa-shopping-cart fa-lg"
						aria-hidden="true"></i></a></li>

				<c:if test="${ ! empty memberBean }">
					<li><img width='60' height='60'
						src="<c:url value='/member/crm/picture/${memberBean.memberId}' />"
						class='mmimg' /></li>

				</c:if>

				<c:if test="${ empty memberBean }">
					<li class="nav-item active"><a href="login" class="nav-link"><i
							class="fa fa-user fa-lg" aria-hidden="true"></i></a></li>
				</c:if>
			</ul>
		</div>
		</div>
	</nav>




</body>

