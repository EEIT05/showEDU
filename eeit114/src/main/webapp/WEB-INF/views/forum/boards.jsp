<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>電影討論版</title>
<!-- Icon CDN-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="css/blog-home.css" rel="stylesheet" />

<style>
.myMOUSEEE {
	cursor: pointer;
}
</style>
</head>

<body>
 <jsp:include page="/WEB-INF/views/top.jsp" />
	<form id='deleteform' method='POST'>
		<input type='hidden' name='_method' value='DELETE'>
	</form>
	<script>
		var pageNo = 0;
		var totalPage = 0;
		var xhr = new XMLHttpRequest();
		
		xhr.open("GET", "<c:url value='/pagingBoards' />", true);
		xhr.send();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var responseData = xhr.responseText;
				displayPageBooks(responseData); // 顯示讀取到的非文字性資料
			}
		}
		// 當使用者按下『第一頁』、『前一頁』、『下一頁』、『最末頁』的連結時，由本方法發出非同步請求。
		function asynRequest(id) {
			var xhr = new XMLHttpRequest();
			var no = 0;
			var queryString = "";			// queryString紀錄查詢字串
				if (id == "first") {		// 算出查詢字串中，要送出的pageNo為何?
			    	no = 1;
			    } else if (id == "prev") {
			    	no = pageNo - 1;
			    } else if (id == "next") {
			    	no = pageNo + 1;
			    } else if (id == "last") {
			    	no = totalPage;	    	
			    }
				// 查詢字串包含1.即將要讀取的頁數(pageNo), 2.總共有幾頁(totalPage)
			    // 注意，查詢字串的前面有問號
				queryString = "?pageNo=" + no + "&totalPage=" + totalPage;
				xhr.open("GET", "<c:url value='/pagingBoards' />" + queryString, true);
				xhr.send();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var responseData = xhr.responseText;
						displayPageBooks(responseData);
					}
				}
		}
		
	function displayPageBooks(responseData) {
		var content = "";	
		var mapObj = JSON.parse(responseData);
		var boardBeans = mapObj.boardBeans;
		pageNo = mapObj.currPage;
		console.log(boardBeans[0].boardId);
		totalPage = mapObj.totalPage;
		for (var i = 0; i < boardBeans.length; i++) {  
			content += "<div class='card mb-4'><a href='article/" + boardBeans[i].boardId + "' style='text-decoration: none;'>"
					+  "<img class='card-img-top'src='<c:url value='/getPicture/" + boardBeans[i].movieBean.movieId + "' />'"
					+  "alt='Card image cap' /><div class='card-body'>"
					+  "<h2 class='card-title' style='text-align: center;'>" + boardBeans[i].movieBean.name + "</h2></div></a>"
					+  "<div class='card-footer text-muted' style='text-align: right;'>"
					+  "<i class='fa fa-clock-o' aria-hidden='true' style='float: left;'>"
					+  "更新時間:" + boardBeans[i].registerTime + "</i><i class='fa fa-eye'"
					+  "aria-hidden='true'>瀏覽數:" + boardBeans[i].viewCount + "</i>"
					+  "<i class='fa fa-newspaper-o' aria-hidden='true'>文章數:" + boardBeans[i].replyCounts + "</i></div></div>";
		}
		document.getElementById("pageDiv").innerHTML = content;
	
		var navContent = "";	
		if (pageNo != 1) { // 如果pageNo != 1 第一頁 前一頁
			navContent += "<li id='first' class='page-item page-link myMOUSEEE'>第一頁</li>";
			navContent += "<li id='prev' class='page-item page-link myMOUSEEE'"
						+ "style=''>← 上一頁</li>";
		} else {			// 否則不顯示
			navContent += "<li></li>";
			navContent += "<li></li>";
		}
		navContent += "<li class='page-item page-link' > " + pageNo + "頁/共" + totalPage + "頁</li>";
		if (pageNo != totalPage) {
			navContent += "<li id='next' class='page-item page-link myMOUSEEE'>下一頁 →</li>";
			navContent += "<li id='last' class='page-item page-link myMOUSEEE'"
						+ "style=''>最末頁</li>";
		} else { //如果是最後一頁
			navContent += "<li></li>";
			navContent += "<li></li>";
		}
		document.getElementById("navigation").innerHTML = navContent;
		var firstBtn = document.getElementById("first");
		var prevBtn  = document.getElementById("prev");
		var nextBtn  = document.getElementById("next");
		var lastBtn  = document.getElementById("last");
		if (firstBtn != null) {
			firstBtn.onclick=function(){
				asynRequest(this.id);
			}
		}
		
		if (prevBtn != null) {
			prevBtn.onclick=function(){
				asynRequest(this.id);
			}
		}
		
		if (nextBtn != null) {
			nextBtn.onclick=function(){
				asynRequest(this.id);
			}
		}
		
		if (lastBtn != null) {
			lastBtn.onclick=function(){
				asynRequest(this.id);				
			}
		}
	}
		
	</script>

	<!-- Navigation -->
<!-- 	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"> -->
<!-- 		<div class="container"> -->
<%-- 			<a href="<c:url value='/'/> " class="navbar-brand"> --%>
<!-- 				<h4>SHOW EDU</h4> -->
<!-- 			</a> -->
<!-- 			<button class="navbar-toggler" type="button" data-toggle="collapse" -->
<!-- 				data-target="#navbarResponsive" aria-controls="navbarResponsive" -->
<!-- 				aria-expanded="false" aria-label="Toggle navigation"> -->
<!-- 				<span class="navbar-toggler-icon"></span> -->
<!-- 			</button> -->
<!-- 			<div class="collapse navbar-collapse" id="navbarResponsive"> -->
<!-- 				<ul class="navbar-nav ml-auto"> -->
<!-- 					<li class="nav-item active"><a class="nav-link" href="#">討論區 -->
<!-- 							<span class="sr-only">(current)</span> -->
<!-- 					</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link" href="#">電影訂票</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link" href="#">購物車</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link" href="#">包廂系統</a></li> -->
<!-- 					<li class="nav-item"><a class="nav-link" href="#">會員管理</a></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</nav> -->

	<!-- Page Content -->
	<div class="container" style="margin-top:100px;">
		<div class="row">
			<!-- Blog Entries Column -->
			<div class="col-md-8">
				<h1 class="my-4" style="font-weight: bold; text-align: center;">
					電影討論版
					<!-- <small>Secondary Text</small> -->
				</h1>

				<!-- Blog Post1 -->
				<div id="pageDiv">
<%-- 				<c:forEach var='board' items='${boards}'> --%>
<!-- 					<div class="card mb-4"> -->
<%-- 						<a href="article/${board.boardId}" style="text-decoration: none;"> --%>
<!-- 							<img class="card-img-top" -->
<%-- 							src="<c:url value='/getPicture/${board.movieBean.movieId}' />" --%>
<!-- 							alt="Card image cap" /> -->
<!-- 							<div class="card-body"> -->
<!-- 								<h2 class="card-title" style="text-align: center;"> -->
<%-- 									${board.movieBean.name}</h2> --%>
<!-- 								<p class="card-text"></p> -->
<!-- 								<a href="#" class="btn btn-primary"> &rarr;</a> -->
<!-- 							</div> -->
<!-- 						</a> -->
<!-- 						<div class="card-footer text-muted" style="text-align: right;"> -->
<!-- 							<i class="fa fa-clock-o" aria-hidden="true" style="float: left;"> -->
<%-- 								更新時間:${board.registerTime}</i> <i class="fa fa-eye" --%>
<%-- 								aria-hidden="true">瀏覽數:${board.viewCount}</i> <i --%>
<%-- 								class="fa fa-newspaper-o" aria-hidden="true">文章數:${board.replyCounts}</i> --%>
<!-- 						</div> -->

<!-- 					</div> -->
<%-- 				</c:forEach> --%>
				</div>
				<!-- Pagination -->
				
				<ul class="pagination justify-content-center mb-4" id='navigation'>
<!-- 					<li id="prev" class="page-item page-link myMOUSEEE" -->
<!-- 						style="display: none">← 上一頁</li> -->
<!-- 					<li id="first" class="page-item page-link myMOUSEEE">第一頁</li> -->
<!-- 					如果要把取消超連結在 li 後面加disabled -->
<!-- 					<li id="next" class="page-item page-link myMOUSEEE">下一頁 →</li> -->
<!-- 					<li id="last" class="page-item page-link myMOUSEEE" -->
<!-- 						style="display: none">最末頁</li> -->
				</ul>
				
			</div>

			<!-- Sidebar Widgets Column -->
			<div class="col-md-4">
				<!-- Search Widget -->
				<div class="card my-4">
					<h5 class="card-header">搜尋電影</h5>
					<div class="card-body">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Search for..." /> <span class="input-group-append">
								<button class="btn btn-secondary" type="button">Go</button>
							</span>
						</div>
					</div>
				</div>

				<!-- Categories Widget -->
				<div class="card my-4" style="overflow: scroll; height: 500px;">
					<h5 class="card-header">熱門電影看板</h5>
					<!-- Post1 -->
					<c:forEach var='sortBoard' items='${sortedBoards}'>
						<div class="card mb-4">
							<a href="article/${sortBoard.boardId}"
								style="text-decoration: none;"> <img class="card-img-top"
								src="<c:url value='/getPicture/${sortBoard.movieBean.movieId}' />"
								alt="Card image cap" />
								<div class="card-body">
									<h2 class="card-title" style="text-align: center;">
										${sortBoard.movieBean.name}</h2>
								</div>
							</a>
							<div class="card-footer text-muted" style="text-align: right;">
								<i class="fa fa-clock-o" aria-hidden="true" style="float: left;">
									更新時間:${sortBoard.registerTime}</i> <i class="fa fa-eye"
									aria-hidden="true">瀏覽數:${sortBoard.viewCount}</i> <i
									class="fa fa-newspaper-o" aria-hidden="true">文章數:${sortBoard.replyCounts}</i>
							</div>


						</div>
					</c:forEach>
					<!-- 						
				<!-- Side Widget -->


				</div>
				<h5 class="card-header">關於我們</h5>
				<div class="card-body">
					客服專線 <br /> 撥打02-26225656
				</div>
				<button style="margin: 3px; padding: 3px 2px 3px 2px;"
					class="btn btn-outline-secondary myMOUSEEE" type="button"
					onclick="addBoard()">新增討論版</button>
				<button style="margin: 3px; padding: 3px 2px 3px 2px;"
					class="btn btn-outline-secondary myMOUSEEE" type="button"
					onclick="backstage()">後台</button>
			</div>
			<!-- /.row -->

			<!-- /.container -->

			<!-- Footer -->
			<footer class="py-5 bg-dark">
				<div class="container">
					<p class="m-0 text-center text-white">I'm Footer</p>
				</div>
				<!-- /.container -->
			</footer>

			<!-- Bootstrap core JavaScript -->
			<script src="vendor/jquery/jquery.min.js"></script>
			<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
			<script type='text/javascript'>
			
		function addBoard() {
			document.location.href="<c:url value='/boards/add' />";
		}
		function backstage() {
			document.location.href="<c:url value='/backstage'/>";
		}

			
		</script>
</body>
</html>
