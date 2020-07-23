<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/style.css' type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<title>SHOW EDU</title>
<style>
html {
	background-color: black;
}

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
	height:75vh;
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

/* 公告 */
.list-unstyled {
	width: 58.3%;
	background-color: #271a59;
	color: rgb(226, 222, 222);
	padding: 7px;
	border: solid 3px rgba(250, 163, 1, 0.767);
	border-radius: 15px;
	margin-left: 41.7%;
	margin-top: -23.9%;
	/* border:solid 2px darkgrey; */
}

.vv {
	margin-left: 100px;
	color: #fec400;
}

.media {
	margin-left: 80px;
	width: 80%;
}

/* 卡片 */
.card-group {
	background-color: #271a59;
	margin-top: -21px;
	padding: 40px;
}

.card {
	border: solid 2px rgb(255, 255, 255);
	height: 400px;
	width: 280px;
	margin-top: 2px;
	margin-bottom: 2px;
	margin-left: 100px;
	margin-right: 10px;
	text-align: center;
}

/* 頁尾 */
.bottom {
	background-color: rgb(211, 211, 211);
	color: #271a59;
	height: 48px;
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>
	<%-- <h1>${loginMember.memberBean.memberId}</h1> --%>

	<%-- <img width='60' height='72' src='crm/picture/${loginMember.memberBean.memberId}' />	 --%>

	<div>


		<link
			href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
			rel="stylesheet" id="bootstrap-css">
		<script
			src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script
			src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!------ Include the above in your HEAD tag ---------->

		<nav class="navbar  navbar-expand-lg navbar-dark fixed-top">
			<img class="logo" src=images/LOGO.jpg width="100" height="100">
			<a href="<c:url value='/'/> " class="navbar-brand">
				<h4>SHOW EDU</h4>
			</a>

			<button class="navbar-toggler" type="button"
				data-target="#navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav">
					<!-- <li class="nav-item active"><a href="#" class="nav-link"><i class="fa fa-shopping-cart" aria-hidden="true" ></i>首頁</a> -->
					<!-- 					</li> -->
					<li class="nav-item"><a href="#" class="nav-link">影城介紹</a></li>
					<li class="nav-item"><a href="<c:url value='movieList' />"
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
								class="dropdown-item">周邊商品</a> <a href="product/backSelect" class="dropdown-item">商品後台</a>
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
									<a href="<c:url value='/yourApplication' />" class="dropdown-item">查看個人訂單</a>
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

					<div>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="navdrop" role="button"
							data-toggle="dropdown" data-hover="dropdown">會員系統</a>
							<div class="dropdown-menu" aria-labelledby="navdrop">

								<a href='member/crm/login' class="dropdown-item">會員登入</a>

								<c:if test="${ ! empty memberBean }">
									<a href='member/crm/loginout' class="dropdown-item">會員登出</a>
								</c:if>

								<c:if test="${  empty memberBean }">
									<a href='member/crm/mem' class="dropdown-item"> 會員註冊</a>
								</c:if>

								<c:if test="${ ! empty memberBean}">
									<a href="<c:url value='member/crm/memb'/>"
										class="dropdown-item">會員資料</a>
								</c:if>

								<c:if test="${memberBean.userType eq 'M'}">
									<a href="orderList" class="dropdown-item">我的訂單</a>
								</c:if>

								<c:if test="${memberBean.userType eq 'A'}">
									<a href="back" target="_blank" class="dropdown-item">後台管理</a>
								</c:if>

								<c:if test="${memberBean.userType eq 'A'}">
									<a href="<c:url value='member/crm/showAllMembers'/>"
										class="dropdown-item">後台會員資料</a>
								</c:if>





							</div></li>


					</div>
					<li class="nav-item active"><a href="ShowCartContent" class="nav-link"><i
							class="fa fa-shopping-cart fa-lg" aria-hidden="true"></i></a></li>

					<c:if test="${ ! empty memberBean }">
						<li><img width='60' height='60'
							src='member/crm/picture/${memberBean.memberId}' class='mmimg' /></li>
					</c:if>

					<c:if test="${ empty memberBean }">
						<li class="nav-item active"><a href="member/crm/login"
							class="nav-link"><i class="fa fa-user fa-lg"
								aria-hidden="true"></i></a></li>
					</c:if>
				</ul>
			</div>
	</div>
	</nav>

	<div id="carouselExampleIndicators"
		class="carousel slide my-carousel my-carousel" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
		</ol>

		<div class="carousel-inner" role="listbox">
			<div class="text-box"></div>
			<div class="carousel-item active" 
				style="background-image: url('https://www.kukatko.cz/wp-content/uploads/2017/07/JL2.jpg')">
				<div class="pp">
					<br>
					<h3 class="wow slideInRight" data-wow-duration="2s"></h3>
					<p class="wow slideInLeft" data-wow-duration="2s"></p>
				</div>
			</div>
			<div class="carousel-item "
				style="background-image: url('https://img.linetv.tw/large/drama/10441-p.jpg')">
				<div class="p2">
					<br>
					<h3 class="wow slideInRight" data-wow-duration="2s">
						</h3>
					<p class="wow slideInLeft" data-wow-duration="2s">
						</p>
				</div>
			</div>
			<div class="carousel-item "
				style="background-image: url('https://i.ytimg.com/vi/E9Cx_B1kOK0/maxresdefault.jpg')">

			</div>
			<div class="carousel-item "
				style="background-image: url('https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/02/ChMkJlbKxgqIc2QIAAd2hfADVfMAALHfQDYbgAAB3ad736.jpg')">

			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
<!-- <iframe width="727" height="409" src="https://www.youtube.com/embed/SIFPsjcWC6g" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe> -->
	<div>
	
    <iframe src="https://www.youtube.com/embed/SIFPsjcWC6g?autoplay=1&mute=1" width="560" height="315" frameborder="0" allowfullscreen="allowfullscreen"></iframe></div>             
<!--                  <iframe width="864" height="516" src="https://www.youtube.com/embed/cRxeLrV475w" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe> -->
	
	
	<div>
		<ul class="list-unstyled">
			<div class="vv">
				<h4>最新公告</h4>
				<br>
			</div>
			<li class="media"><img src="images/LOGO.jpg" height="80px"
				class="mr-3" alt="...">
				<div class="media-body">
					<h6 class="mt-0 mb-1">2020-7-26</h6>
					<p>休館一天。</p>
					<hr color="dimgray">
				</div></li>
			<li class="media"><img src="images/LOGO.jpg" height="80px"
				class="mr-3" alt="...">
				<div class="media-body">
					<h6 class="mt-0 mb-1">2020-6-03</h6>
					<p>維修設備，開始營業時間更改為下午3點。</p>
					<hr color="dimgray">

				</div>
			<li class="media"><img src="images/LOGO.jpg" height="80px"
				class="mr-3" alt="...">
				<div class="media-body">
					<h6 class="mt-0 mb-1">2020-3-18</h6>
					<p>休館一天。</p>
				</div></li>
		</ul>
	</div>



	<div class="card-group">
		<div class="row">
			<div class="col-md-4">
				<div class="card mb-4 border-light bg-dark text-white">
					<img class="card-img-top" src=images/電影4.jpeg height="60%" alt="">
					<div class="card-body">
						<h5 class="card-title border-bottom pb-3">塔尖</h5>
						<p class="card-text">Some day.....</p>
						<a href="#" class="btn btn-sm btn-info float-center">電影詳細 <i
							class="fas fa-angle-double-right"></i></a>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card mb-4 border-light bg-dark text-white">
					<img class="card-img-top" src=images/電影9.jpeg height="60%" alt="">
					<div class="card-body">
						<h5 class="card-title border-bottom pb-3">相遇</h5>
						<p class="card-text">Some day...some..</p>
						<a href="#" class="btn btn-sm btn-info float-center">電影詳細 <i
							class="fas fa-angle-double-right"></i></a>
					</div>
				</div>
			</div>

			<div class="card mb-4 border-light bg-dark text-white">

				<img class="card-img-top" src=images/電影7.jpeg height="60%" alt="">
				<div class="card-body">
					<h5 class="card-title border-bottom pb-3">舞</h5>
					<p class="card-text">Dancing.....</p>
					<a href="#" class="btn btn-sm btn-info float-center">電影詳細 <i
						class="fas fa-angle-double-right"></i></a>
				</div>
			</div>
		</div>
	</div>
	<div class="bottom">
		<p>SHOW EDU 聯絡我們電話:地址:</p>

	</div>
	</header>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>

</html>


</body>
</html>