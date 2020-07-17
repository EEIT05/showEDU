<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>電影清單</title>
<style type="text/css">
.movielist {
	background-color: white;
}
a.myWord:link{text-decoration :underline;color:#FFFFFF;font-family:  標楷體, 細明體, Tahoma, Arial;}
a.myWord:hover{text-decoration : underline;color:#FF0000;font-family: 標楷體, 細明體, Tahoma, Arial;}
a.myWord:visited{text-decoration :underline;color:#FFFFFF;font-family: 標楷體, 細明體, Tahoma, Arial;}
</style>
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.11.1/jquery.js"></script>

</head>
<body>
<!-- 	<div style="text-align: left; background-color: lightgray;"> -->
<%-- 		<a href="<spring:url value='/' />">首頁</a><a>=></a> <a style='font:weight'>電影列表</a> --%>
<!-- 	</div> -->
	<section>
		<div>
			<div class="container" style="text-align: center">
				<h1>hot售電影</h1>
			</div>
		</div>
		<br>
		<br>
	</section>
	<section class="container">
		<div class="row" >
			<c:forEach var='movie' items='${movies}'>
				<div  style="text-align: center" class="col-sm-6 col-md-3" style="width: 200px; height: 400px" >
					<div style="text-align: center">					
					<div style="text-align: center" style="border:0px; width: 150px; height: 350px;" class='movielist' class="thumbnail" >
						<div style="text-align: center">
						<a href="<spring:url value='/movieDetail?movieId=${movie.movieId}' />" >
						<img  style="margin:auto" width='150' height='210' alt='${movie.chName}' title='${movie.chName}'
							src="<c:url value='/getMovieImg/${movie.movieId}' />" />
							</a>
							
							
						<div class="caption">
						
						<img style="text-align: center" width='25' height='30' alt='${movie.movieLevelBean.level}' title='${movie.movieLevelBean.level}'
							src="<c:url value='/getMovieLevelImg/${movie.movieLevelBean.movieLevelId}' />" />
							
							<p>
							<a style='font-size: 15px weight;' href="<spring:url value='/movieDetail?movieId=${movie.movieId}' />">
								${movie.chName}	
								</a>
							</p>
							<p style='font: 13px saddlebrown;'>${movie.enName}</p>
							<p style='font-size: 10px;'>${movie.premierDate}</p>
							
							<p>
<%-- 								<a href="<spring:url value='/movie?id=${movie.movieId}' />" --%>
<!-- 									class="btn btn-primary"> <span -->
<!-- 									class="glyphicon-info-sigh glyphicon"></span>詳細資料 -->
<!-- 								</a>  -->
								
							</p>
						</div>
						</div>
					</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
	<hr>

<script>
$(function () {
    var oldColor = "";
    $(".movielist").mouseover(function () {
        oldColor = $(this).css("background-color");
    $(this).css("background-color","gainsboro");
    }).mouseout(function () {
        $(this).css("background-color",oldColor);
    });
});
</script>
</body>
</html>
