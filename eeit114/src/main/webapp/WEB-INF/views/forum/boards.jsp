<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>

<body>
	<form id='deleteform' method='POST'>
		<input type='hidden' name='_method' value='DELETE'>
	</form>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">SHOW EDU</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">討論區
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#">電影訂票</a></li>
					<li class="nav-item"><a class="nav-link" href="#">購物車</a></li>
					<li class="nav-item"><a class="nav-link" href="#">包廂系統</a></li>
					<li class="nav-item"><a class="nav-link" href="#">會員管理</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Blog Entries Column -->
			<div class="col-md-8">
				<h1 class="my-4" style="font-weight: bold; text-align: center;">
					電影討論版
					<!-- <small>Secondary Text</small> -->
				</h1>

				<!-- Blog Post1 -->
				<c:forEach var='board' items='${boards}'>
					<div class="card mb-4">
						<a href="article/${board.boardId}" style="text-decoration: none;">
							<img class="card-img-top"
							src="<c:url value='/getPicture/${board.movieBean.movieId}' />"
							alt="Card image cap" />
							<div class="card-body">
								<h2 class="card-title" style="text-align: center;">
									${board.movieBean.name}</h2>
								<!-- <p class="card-text"></p> -->
								<!-- <a href="#" class="btn btn-primary"> &rarr;</a> -->
							</div>
						</a>
						<div class="card-footer text-muted" style="text-align: right;">
							<i class="fa fa-clock-o" aria-hidden="true" style="float: left;">
								更新時間:${board.registerTime}</i> <i class="fa fa-eye"
								aria-hidden="true">瀏覽數:${board.viewCount}</i> <i
								class="fa fa-newspaper-o" aria-hidden="true">文章數:${board.replyCounts}</i>
						</div>
						<button style="border: 0; background-color: none">
							<a id='deletelink' class="page-link"
								href="<c:url value='/boards/delete${board.boardId}' />"
								style="text-decoration: none; color: black;">刪除討論版</a>
						</button>
					</div>
				</c:forEach>

				<!-- Pagination -->
				<ul class="pagination justify-content-center mb-4">
					<li class="page-item"><a class="page-link" href="#">← 上一頁</a>
					</li>
					<!-- 如果要把取消超連結在 li 後面加disabled -->
					<li class="page-item"><a class="page-link" href="#">下一頁 →</a>
					</li>
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
				<button style="border: 0; background-color: none">
					<a class="page-link" href="<c:url value='/boards/add' />"
						style="text-decoration: none; color: black;">新增討論版</a>
				</button>

			</div>
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
			$(document).ready(function() {
				$('#deletelink').click(function() {
					if (confirm('確定刪除此討論版?')) {
						var href = $(this).attr('href');
						$('#deleteform').attr('action', href).submit();
					}
					return false;
				})
			})
		</script>
</body>
</html>
