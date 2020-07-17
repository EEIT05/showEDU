<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Blog Home - Start Bootstrap Template</title>
<!-- Icon CDN-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/blog-home.css" rel="stylesheet">

</head>

<body>
	<script>
		window.onload = function() {
			var a0 = document.getElementById('a0');
			var a1 = document.getElementById('a1');
			var a2 = document.getElementById('a2');
			var a3 = document.getElementById('a3');
			var a4 = document.getElementById('a4');
			var xhr = new XMLHttpRequest;
			var typeId = null;
			var boardId = ${articleBean.discussionBoardBean.boardId};
			
			// 全部文章 ----------------------------------------------------------------(Ajax)
			a0.onclick = function() {
				xhr.open("GET", "<c:url value='/allArtType' />" , true);
				xhr.send();
				xhr.onreadystatechange = function(){
					if (xhr.readyState == 4 && xhr.status == 200) {
						var allArticleBean = JSON.parse(xhr.responseText);
						var anytype = document.getElementById("anytype")
						var content = "";
						anytype.innerHTML = "";
						for (var i = 0; i < allArticleBean.length; i++) {
							if (allArticleBean[i].discussionBoardBean.boardId == boardId) {
							content += "<a class='a10' href='<c:url value='/comment/" + allArticleBean[i].artId +"' />'>"
									+ "<div class='card mb-4'>"
									+ "<div style='display: inline;'><div style='float: right;'><h3>"
									+ allArticleBean[i].title
									+ "&emsp;&emsp;&emsp;</h3></div></div>"
									+ "<div class='card mb-2' style='display: inline; ' ><div style='float: left;'><img class='img1'src='<c:url value='/getPictureType/"
	                        		+ allArticleBean[i].artTypeBean.typeId + "' />' alt='"
									+ allArticleBean[i].artTypeBean.typeId
									+ "' /></div><div class='div1'>&emsp;&emsp;"
									+ allArticleBean[i].content
									+ "</div></div>"
									+ "<div class='card-footer text-muted' style='text-align: right;'><i class='fa fa-user' aria-hidden='true' style='float: left;'>&ensp;發文者:"
									+ allArticleBean[i].memberBean.name
									+ "</i><i class='fa fa-comments' aria-hidden='true'>&ensp;回覆數:"
									+ allArticleBean[i].replyCount
									+ "&ensp;</i><i class='fa fa-clock-o' aria-hidden='true'>&ensp;更新時間:"
									+ allArticleBean[i].registerTime + "</i></div></div></a>";
								anytype.innerHTML = content;
							}	
						}
					}
				}
			}
				function anyType() {
					xhr.open("GET", "<c:url value='/artType' />" + "?typeId="
							+ typeId, true);
					xhr.send();
					xhr.onreadystatechange = function() {
						if (xhr.readyState == 4 && xhr.status == 200) {
							var articleBeans = JSON.parse(xhr.responseText);
							var anytype = document.getElementById("anytype")
								anytype.innerHTML = "";
							var content = "";
							for (var i = 0; i < articleBeans.length; i++) {
								if (articleBeans[i].articleBean.discussionBoardBean.boardId == boardId) {
								content += "<a class='a10' href='<c:url value='/comment/" + articleBeans[i].articleBean.artId +"' />'>"
										+ "<div class='card mb-4'>"
										+ "<div style='display: inline;'><div style='float: right;'><h3>"
										+ articleBeans[i].articleBean.title
										+ "&emsp;&emsp;&emsp;</h3></div></div>"
										+ "<div class='card mb-2' style='display: inline; ' ><div style='float: left;'><img class='img1'src='<c:url value='/getPictureType/"
		                        		+ articleBeans[i].articleBean.artTypeBean.typeId + "' />' alt='"
										+ articleBeans[i].articleBean.artTypeBean.typeId
										+ "' /></div><div class='div1'>&emsp;&emsp;"
										+ articleBeans[i].articleBean.content
										+ "</div></div>"
										+ "<div class='card-footer text-muted' style='text-align: right;'><i class='fa fa-user' aria-hidden='true' style='float: left;'>&ensp;發文者:"
										+ articleBeans[i].articleBean.memberBean.name
										+ "</i><i class='fa fa-comments' aria-hidden='true'>&ensp;回覆數:"
										+ articleBeans[i].articleBean.replyCount
										+ "&ensp;</i><i class='fa fa-clock-o' aria-hidden='true'>&ensp;更新時間:"
										+ articleBeans[i].articleBean.registerTime + "</i></div></div></a>";
									anytype.innerHTML = content;
								}	
							}
						}
					}	
							}
			// 情報分類 -----------------------------------------------------------------(Ajax)
			a1.onclick = function() {
				typeId = 1
				anyType();
			}
			// 發問分類 -----------------------------------------------------------------(Ajax)
			a2.onclick = function() {
				typeId = 2
				anyType();
			}
			// 心得分類 -----------------------------------------------------------------(Ajax)
			a3.onclick = function() {
				typeId = 3;
				anyType();
			}
			// 其他分類 -----------------------------------------------------------------(Ajax)
			a4.onclick = function() {
				typeId = 4;
				anyType();
			}
		}
	</script>
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
			<div class="col-md-2">
				<br>
				<div>
					<h2 style="font-weight: bold">文章分類</h2>
				</div>
				<table>
					<tr style="font-size: 26px; border: black 1px solid">
						<td><a id='a0' class="mh6" href="#"
							style="text-decoration: none;">&emsp;所有&emsp;</a></td>
					</tr>
					<tr style="font-size: 26px; border: black 1px solid">
						<td><a id='a1' class="mh6" href="#"
							style="text-decoration: none;">&emsp;情報&emsp;</a></td>
					</tr>
					<tr style="font-size: 26px; border: black 1px solid">
						<td><a id='a2' class="mh6" href="#"
							style="text-decoration: none;">&emsp;發問&emsp;</a></td>
					</tr>
					<tr style="font-size: 26px; border: black 1px solid">
						<td><a id='a3' class="mh6" href="#"
							style="text-decoration: none;">&emsp;心得&emsp;</a></td>
					</tr>
					<tr style="font-size: 26px; border: black 1px solid">
						<td><a id='a4' class="mh6" href="#"
							style="text-decoration: none;">&emsp;其他&emsp;</a></td>
					</tr>

				</table>


			</div>
			<div class="col-md-6">
				<h3 class="my-3" style="font-weight: bold; text-align: center;">
					${movieName}
					<!-- <small>Secondary Text</small> -->
				</h3>
				<br>
				<!-- Article 1-->
				<div id='anytype'>
					<c:forEach var='article' items='${articles}'>
						<a class="a10" href="<c:url value='/comment/${article.artId}' />">
							<div class="card mb-4">
								<div style="display: inline;">
									<div style="float: right;">
										<h3>${article.title}&emsp;&emsp;&emsp;</h3>
									</div>
								</div>
								<div class="card mb-2" style="display: inline;">
									<div style="float: left;">
										<img class="img1"
											src="<c:url value='/getPictureType/${article.artTypeBean.typeId}' />"
											alt="${article.artTypeBean.typeId}" />
									</div>
									<div class="div1" style="height: 215px">&emsp;&emsp;${article.content}
									<p>&emsp;</p>
									</div>
								</div>
								<div class="card-footer text-muted" style="text-align: right;">
									<i class="fa fa-user" aria-hidden="true" style="float: left;">&ensp;發文者:${article.memberBean.name}</i>
									<i class="fa fa-comments" aria-hidden="true">&ensp;回覆數:${article.replyCount}&ensp;</i>
									<i class="fa fa-clock-o" aria-hidden="true">&ensp;更新時間:${article.registerTime}</i>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
				<!-- Pagination -->
				<ul class="pagination justify-content-center mb-4">
					<li class="page-item"><a class="page-link" href="#">&larr;
							上一頁</a></li>
					<!-- 如果要把取消超連結在 li 後面加disabled -->
					<li class="page-item "><a class="page-link" href="#">下一頁
							&rarr;</a></li>
				</ul>

			</div>

			<!-- Sidebar Widgets Column -->
			<div class="col-md-4">

				<!-- Search Widget -->
				<div class="card my-4">
					<h5 class="card-header">搜尋文章</h5>
					<div class="card-body">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Search for..."> <span
								class="input-group-append">
								<button class="btn btn-secondary" type="button">Go</button>
							</span>
						</div>
					</div>
				</div>

				<!-- Categories Widget -->
				<div class="card my-4">
					<h5 class="card-header">熱門文章</h5>
					<div style="overflow: scroll; height: 500px;">


						<!-- Article 1-->
						<c:forEach var='article' items='${articles}'>
							<a class="a10" href="#" style="text-decoration: none">
								<div class="card mb-4">

									<div class="card mb-2" style="display: inline;">
										<div style="float: left;">
											<img class="img2"
												src="<c:url value='/getPictureType/${article.artTypeBean.typeId}' />"
												alt="${article.artTypeBean.typeId}" />
										</div>

										<div>
											<h4 style="align-content: center;">${article.title}&emsp;&emsp;</h4>
										</div>

										<div class="div2">&emsp;${article.content}</div>
									</div>
									<div class="card-footer text-muted" style="text-align: right;">
										<i class="fa fa-user" aria-hidden="true" style="float: left;">&ensp;發文者:${article.memberBean.name}</i>
										<i class="fa fa-comments" aria-hidden="true">&ensp;回覆數:${article.replyCount}&ensp;</i>
										<i class="fa fa-clock-o" aria-hidden="true">&ensp;更新時間:${article.registerTime}</i>
									</div>
								</div>
							</a>
						</c:forEach>

						<!-- Side Widget -->

					</div>

				</div>
				<!-- /.row -->

			</div>
			<!-- /.container -->

			<!-- Footer -->
			<footer class="py-5 bg-dark">
				<div class="container">
					<p class="m-0 text-center text-white">Copyright &copy; Your
						Website 2020</p>
				</div>
				<!-- /.container -->
			</footer>

			<!-- Bootstrap core JavaScript -->
			<script src="vendor/jquery/jquery.min.js"></script>
			<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>
