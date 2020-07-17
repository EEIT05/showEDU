<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="UTF-8">
<!-- <link rel='stylesheet' -->
<%-- 	href='${pageContext.request.contextPath}/css/styles.css' --%>
<!-- 	type="text/css" /> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"> -->
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
</style>
</head>
<body>
	<div style="text-align: left; background-color: lightgray;">
		<a href="<spring:url value='/' />">首頁</a><a>=></a> <a href="movieList">電影列表</a><a>=></a><a>${movie.chName}</a>
	</div>
	<br>
	<!-- ===============電影下拉快選================ -->
	<div id='content'>
	<section>
		<div>
			<form:form method='POST' modelAttribute="movie"
				class='form-horizontal'>


				<div style='text-align: right' class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="movieId">
					</label>
					<div class='col-lg-10'>
						<form:select id='selectmovie' path="movieId" name="movielist"
							onchange="changemovie(this.value);">
							<form:options items="${movieList}" />
						</form:select>
					</div>
				</div>
			</form:form>
		</div>
	</section>
	<!-- ==================詳細資料================= -->
	<div style="text-align: center">
		<table style="width: 820; text-align: center;">
			<tr>
			<tr>
				<td><img style="margin: auto" width='250' height='340'
					alt='${movie.chName}' title='${movie.chName}'
					src="<c:url value='/getMovieImg/${movie.movieId}' />" /></td>
				<td>
					<table style="width: 400;">
						<tr>
							<td><h3>${movie.chName}</h3>
						</tr>
						<tr>
							<td><p style='font: 15px saddlebrown;'>${movie.enName}</p></td>
						</tr>
						<tr>
							<td><p style='font-size: 13px;'>上映日期：${movie.premierDate}</p></td>
						</tr>
						<tr>
							<td><img style="text-align: center" width='50' height='60'
								alt='${movie.movieLevelBean.level}'
								title='${movie.movieLevelBean.level}'
								src="<c:url value='/getMovieLevelImg/${movie.movieLevelBean.movieLevelId}' />" /></td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
						<tr>
							<td><p>導演：${movie.director}</p></td>
						</tr>
						<tr>
							<td><p>演員：${movie.actors}</p></td>
						</tr>
						<tr>
							<td><p>片長：${movie.length}分</p></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>
<br>
	<!-- =============================預告============================= -->
	<div style='text-align: center; background-color: lightgray'>
		<!-- 預告位置 -->
		<iframe width="956" height="538" src="${movie.trailer}"
			frameborder="0"
			allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
			allowfullscreen></iframe>
	</div>
	<!-- =============================劇情============================= -->
	<div id='content'>
		<table style="width: 820;">
			<tr>
				<td><h1>劇情大綱</h1> <a>// ABOUT THE Movie</a></td>
			</tr>
			<tr>
				<td><a>${movie.synopsis}</a></td>
			</tr>
		</table>
	</div>

	<script src="../js/jquery-3.5.1.min.js"></script>
	<script language="Javascript">
		function changemovie(movieId) {
			window.location.href = "<spring:url value='/movieDetail?movieId="
					+ movieId + "' />"
		}
	</script>
</body>
</html>
