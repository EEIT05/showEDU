<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Blog Post - Start Bootstrap Template</title>

<!-- Icon CDN-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/blog-post.css" rel="stylesheet">

<style>
.myMOUSE {
	cursor: pointer;
}
</style>

</head>

<body>
<jsp:include page="/WEB-INF/views/top.jsp" />
	<form>
		<input type="hidden" name="a" />
	</form>

	<form id="deleteForm" method='POST'>
		<input type='hidden' name='_method' value='DELETE'>
	</form>


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

			<!-- Post Content Column -->

			<div class="col-lg-8">

				<!-- Title -->
				<h1 class="mt-4">[${articleBean.artTypeBean.type}]&emsp;&emsp;&emsp;${articleBean.title}</h1>

				<!-- Author -->
				<img class="d-flex mr-3 rounded-circle" width='50' height='50'
					src="<c:url value='/getPictureComment/${articleBean.memberBean.memberId}' />"
					alt="${articleBean.memberBean.memberId}">
				<p class="lead">By: ${articleBean.memberBean.name}</p>

				<hr>

				<!-- Date/Time -->
				<p>
					<i class="fa fa-clock-o" aria-hidden="true">&ensp;更新時間:${registerTime}</i>
				</p>

				<hr>

				<!-- Preview Image -->
				<img class="img-fluid rounded"
					src="<c:url value='/getPictureArticle/${articleBean.artId}' />"
					alt="">
				<hr>

				<!-- Post Content -->
				<!-- 內容區 -->
				<p class="lead" id="p1"></p>
				<p class="lead" id="p2"></p>
				<p class="lead" id="p3"></p>
				<p class="lead" id="p4"></p>
				<p class="lead" id="p5"></p>
				<p class="lead" id="p6"></p>
				<p class="lead" id="p7"></p>
				<p class="lead" id="p8"></p>
				<p class="lead" id="p9"></p>
				<p class="lead" id="p10"></p>

				<!-- Comments Form -->
				<div class="card my-2">
					<p class="card-header">
						<button id="comment" type="submit" class="btn btn-primary"
							onclick="Comment(${articleBean.artId} ,${memberBean.memberId})">留言</button>
						<textarea id="inputComment" style="margin-top: 7px; height: 100px"
							type="text" class="form-control" placeholder=""></textarea>
					</p>


				</div>

				<!-- Comment with nested comments -->
				<c:forEach var='commentBean' items='${commentBeans}'>
					<div class="media mb-4">
						<img class="d-flex mr-3 rounded-circle" width='50' height='50'
							src="<c:url value='/getPictureComment/${commentBean.memberBean.memberId}' />"
							alt="${commentBean.memberBean.memberId}">
						<div class="media-body">
							<h5 class="mt-0" style="float: left">${commentBean.memberBean.name}</h5>
							<p>&emsp;${commentBean.time}</p>
							<p>${commentBean.content}</p>
							<div>
								<i class="fa fa-thumbs-up myMOUSE" aria-hidden="true"
									id="ComThumlike${commentBean.commentId}"
									onclick="thumbUp(${commentBean.commentId}, ${memberBean.memberId})">
									<c:if test="${commentBean.likeCount != 0}">
									${commentBean.likeCount}
									</c:if>
								</i> &emsp; <i class="fa fa-thumbs-down myMOUSE" aria-hidden="true"
									id="ComThumDislike${commentBean.commentId}"
									onclick="thumbDown(${commentBean.commentId}, ${memberBean.memberId})">
									<c:if test="${commentBean.dislikeCount != 0}">
									${commentBean.dislikeCount}
									</c:if>
								</i> &emsp;
								<button
									onclick="SecComment(${commentBean.commentId}, ${memberBean.memberId})"
									style="margin: 3px; padding: 3px 2px 3px 2px;"
									class="btn btn-outline-secondary" type="button">回覆</button>
								<button
									onclick="SecSubmit(${commentBean.commentId}, ${memberBean.memberId})"
									style="margin: 3px; padding: 3px 2px 3px 2px;"
									class="btn btn-outline-secondary" type="button">送出</button>
								<c:if test="${ ! empty memberBean}">
									<c:if
										test="${memberBean.memberId != commentBean.memberBean.memberId}">
										<div style="float: right; font-size: 15px; color: red;"
											class="myMOUSE"
											onclick="report(
									${commentBean.commentId}, ${memberBean.memberId})">檢舉</div>
									</c:if>
								</c:if>
								<input id="inputSecComment${commentBean.commentId}" type="text"
									class="form-control" placeholder="" aria-label=""
									aria-describedby="basic-addon1" style="display: none">
								<c:if test="${ ! empty memberBean}">
									<c:if
										test="${memberBean.memberId == commentBean.memberBean.memberId}">

										<button class="btn btn-danger"
											onclick="deleteComment(${commentBean.commentId})">
											刪除</button>

									</c:if>
								</c:if>

							</div>
							<c:forEach var='commentSecBean' items='${commentSecList}'>
								<c:if
									test="${commentBean.commentId == commentSecBean.commentBean.commentId}">
									<div class="media mt-4">
										<img class="d-flex mr-3 rounded-circle" width='50' height='50'
											src="<c:url value='/getPictureComment/${commentSecBean.memberBean.memberId}' />"
											alt="${commentSecBean.memberBean.memberId}">
										<div class="media-body">
											<h5 class="mt-0" style="float: left">${commentSecBean.memberBean.name}</h5>
											<p>&emsp;&emsp;${commentSecBean.time}</p>
											<p>${commentSecBean.content}</p>
											<div>

												<i class="fa fa-thumbs-up myMOUSE" aria-hidden="true"
													id="SecComThumlike${commentSecBean.commentSecId}"
													onclick="SecThumbUp(${commentSecBean.commentSecId}, ${memberBean.memberId})">
													<c:if test="${commentSecBean.likeCount != 0}">
													${commentSecBean.likeCount}
													</c:if>
												</i>&emsp; <i class="fa fa-thumbs-down myMOUSE"
													aria-hidden="true"
													id="SecComThumDislike${commentSecBean.commentSecId}"
													onclick="SecThumbDown(${commentSecBean.commentSecId}, ${memberBean.memberId})">
													<c:if test="${commentSecBean.dislikeCount != 0}">
													${commentSecBean.dislikeCount}
													</c:if>
												</i> &emsp;
												<c:if test="${ ! empty memberBean}">
													<c:if
														test="${memberBean.memberId == commentSecBean.memberBean.memberId}">

														<button class="btn btn-danger"
															onclick="deleteSecComment(${commentSecBean.commentSecId}, ${articleBean.artId})">
															刪除</button>

													</c:if>
												</c:if>
												<c:if test="${ ! empty memberBean}">
													<c:if
														test="${memberBean.memberId != commentSecBean.memberBean.memberId}">
														<div style="float: right; font-size: 15px; color: red;"
															class="myMOUSE"
															onclick="reportSec(
															${commentSecBean.commentSecId}, ${memberBean.memberId})">檢舉</div>
													</c:if>
												</c:if>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</c:forEach>

			</div>

			<!-- Sidebar Widgets Column -->
			<div class="col-md-4">

				<!-- Categories Widget -->
				<div class="card my-4">
					<h5 class="card-header">熱門文章</h5>
					<div style="overflow: scroll; height: 500px;">


						<!-- Article 1-->
						<a class="a1" href="#" style="text-decoration: none">
							<div class="card mb-4">

								<div class="card mb-2" style="display: inline;">
									<div style="float: left;">
										<img class="img2" src="image/artType4.png" alt="artType1">
									</div>

									<div>
										<h4 style="align-content: center;">標題&emsp;&emsp;</h4>
									</div>

									<div class="div2">&emsp;內容</div>
								</div>
								<div class="card-footer text-muted" style="text-align: right;">
									<i class="fa fa-user" aria-hidden="true" style="float: left;">&ensp;王小明</i>
									<i class="fa fa-comments" aria-hidden="true">&ensp;回覆數:25&ensp;</i>
									<i class="fa fa-clock-o" aria-hidden="true">&ensp;更新時間:2020/6/27
										19:12</i>
								</div>
							</div>
						</a>
						<!-- Article 2-->
						<a class="a1" href="#" style="text-decoration: none">
							<div class="card mb-4">

								<div class="card mb-2" style="display: inline;">
									<div style="float: left;">
										<img class="img2" src="image/artType3.png" alt="artType1">
									</div>

									<div>
										<h4 style="align-content: center;">牠...恐怖&emsp;&emsp;</h4>
									</div>

									<div class="div2">
										&emsp;用剩下的一點力氣，留下遺言，然後靜靜睡了等待死亡的到來，並帶他回到地球上，然而回到地球後，想要招集人去復仇，但東尼已經心灰意冷，絕對退出復仇者，加上他身體已經十分虛弱，昏了過去。
									</div>
								</div>
								<div class="card-footer text-muted" style="text-align: right;">
									<i class="fa fa-user" aria-hidden="true" style="float: left;">&ensp;文麗麗</i>
									<i class="fa fa-comments" aria-hidden="true">&ensp;回覆數:21&ensp;</i>
									<i class="fa fa-clock-o" aria-hidden="true">&ensp;更新時間:2020/6/22
										19:12</i>
								</div>
							</div>
						</a>

						<!-- Article 3-->
						<a class="a1" href="#" style="text-decoration: none">
							<div class="card mb-4">

								<div class="card mb-2" style="display: inline;">
									<div style="float: left;">
										<img class="img2" src="image/artType2.png" alt="artType1">
									</div>

									<div>
										<h4 style="align-content: center;">世界末日男主最後...&emsp;&emsp;</h4>
									</div>

									<div class="div2">
										&emsp;消滅了一半的生物，在太空船失去動力與剩餘氧氣不到48小時後，絕望了，用剩下的一點力氣，留下遺言，然後靜靜睡了等待死亡的到來，地球上，那一戰，十分虛弱。
									</div>
								</div>
								<div class="card-footer text-muted" style="text-align: right;">
									<i class="fa fa-user" aria-hidden="true" style="float: left;">&ensp;王陽明</i>
									<i class="fa fa-comments" aria-hidden="true">&ensp;回覆數:12&ensp;</i>
									<i class="fa fa-clock-o" aria-hidden="true">&ensp;更新時間:2019/8/27
										19:12</i>
								</div>
							</div>
						</a>
					</div>

				</div>
				<!-- Side Widget -->
				<div class="card my-4">
					<h5 class="card-header">Side Widget</h5>
					<div class="card-body">You can put anything you want inside
						of these side widgets. They are easy to use, and feature the new
						Bootstrap 4 card containers!</div>
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
		<script type="text/javascript">
		// 文章排版
          var p0 = '${articleBean.content}';
          var p1 = document.getElementById("p1");
          var p2 = document.getElementById("p2");
          var p3 = document.getElementById("p3");
          var p4 = document.getElementById("p4");
          var p5 = document.getElementById("p5");
          var p6 = document.getElementById("p6");
          var p7 = document.getElementById("p7");
          var p8 = document.getElementById("p8");
          var p9 = document.getElementById("p9");
          var p10 = document.getElementById("p10");
          var arr = new Array();
          function calculate(p0) {
                arr = p0.split("。");
              }
          calculate(p0);
          for (var i = 0 ; i < arr.length; i++) {
        	  if(arr[i] == ""){
        		  break;
        	  } 
        	  if(p1.innerHTML == "") {
        		  p1.innerHTML = arr[i] + "。";
        	  } else if (p2.innerHTML == "") {
        		  p2.innerHTML = arr[i]　+ "。";
        	  } else if (p3.innerHTML == "") {
        		  p3.innerHTML = arr[i]　+ "。";
        	  } else if (p4.innerHTML == "") {
        		  p4.innerHTML = arr[i]　+ "。";
        	  } else if (p5.innerHTML == "") {
        		  p5.innerHTML = arr[i]　+ "。";
        	  } else if (p6.innerHTML == "") {
        		  p6.innerHTML = arr[i]　+ "。";
        	  } else if (p7.innerHTML == "") {
        		  p7.innerHTML = arr[i]　+ "。";
        	  } else if (p8.innerHTML == "") {
        		  p8.innerHTML = arr[i]　+ "。";
        	  } else if (p9.innerHTML == "") {
        		  p9.innerHTML = arr[i]　+ "。";
        	  } else if (p10.innerHTML == "") {
        		  p10.innerHTML = arr[i]　+ "。";
        	  } 
          }
          
          
         
          window.addEventListener('onclick', SecThumbDown, false)
		  window.addEventListener('onclick', thumbUp, false);
		  window.addEventListener('onclick', thumbDown, false);
		  window.addEventListener('onclick', SecThumbUp, false);
		  window.addEventListener('onclick', SecComment, false);
		  window.addEventListener('onclick', SecSubmit, false);
		  window.addEventListener('onclick', Comment, false);
		  window.addEventListener('onclick', deleteComment, false);
		  
		  // 送出回覆檢舉
		  function reportSec(commentSecId, memberId) {
			  if (memberId == null) {
				  document.location.href="<c:url value='/member/crm/login' />";
			  }else if (confirm('確定檢舉此回覆?')) {
				  document.forms[0].action="<c:url value='/reportSecComment?commentSecId=" + commentSecId + "' />";
	     		  document.forms[0].method="POST";
	     		  document.forms[0].submit();
			  }
			  return false;
		  }
		  // 送出留言檢舉
		  function report(commentId, memberId) {
			  if (memberId == null) {
				  document.location.href="<c:url value='/member/crm/login' />";
			  }else if (confirm('確定檢舉此留言?')) {
				  document.forms[0].action="<c:url value='/reportComment?commentId=" + commentId + "' />";
	     		  document.forms[0].method="POST";
	     		  document.forms[0].submit();
			  }
			  return false;
			  
		  }
		  
		  // 刪除第一層留言
		  function deleteComment(commentId) {
			  if (confirm('確定刪除此留言?')){
				  document.forms[0].action="<c:url value='/deleteComment?commentId=" + commentId + "' />";
	     		  document.forms[0].method="POST";
	     		  document.forms[0].submit();
			  }
			  return false;
		  }
		  
		  // 刪除第二層留言
		  function deleteSecComment(commentSecId,artId) {
			  if (confirm('確定刪除此留言?')){
				  document.forms[0].action="<c:url value='/deleteSecComment?commentSecId=" + commentSecId + "&artId="+ artId + "' />";
	     		  document.forms[0].method="POST";
	     		  document.forms[0].submit();
			  }
		  }
		  
		  
		  // 新增第一層留言
		  function Comment(artId, memberId) {
			  if (memberId == null) {
				  document.location.href="<c:url value='/member/crm/login' />";
			  } else{
				  var inputComment = document.getElementById("inputComment");
				  var content = inputComment.value;
				  alert(content);
				  if (content.length == 0) {
					  alert("留言為空白");
				  } else {
					  document.forms[0].action="<c:url value='/addComment?artId=" + artId + "&memberId=" + memberId + "&content=" + content + "' />";
             		  document.forms[0].method="POST";
             		  document.forms[0].submit();
				  }
				  
			  }
		  }
		  
	      // 將留言的地方顯示出來
          function SecComment(commentId, memberId) {
        	  if (memberId == null) {
        		  document.location.href="<c:url value='/member/crm/login' />";
         	  } else{
        	      var x = "inputSecComment" + commentId;
        	      var input = document.getElementById(x);
        	      input.style.display = 'block';
         	  } 
        	  
          }
          // 第二層留言回覆送出
          function SecSubmit(commentId, memberId) {
        	  if (memberId == null) {
        		  return; //請先登入
        	  } else {
        		  console.log("會員名稱為:" + memberId);
        		  var x = "inputSecComment" + commentId;
        		  var input = document.getElementById(x);
        		  var SecContent = input.value;
        		  if (SecContent.length == 0) {
        			  alert("不可送出空白留言");
        			  return; // 輸入不可為空白
        		  } else {
        			  document.forms[0].action="<c:url value='/addSecComment?commentId="+ commentId + "&memberId=" + memberId + "&SecContent=" + SecContent + "' />";
             		  document.forms[0].method="POST";
             		  document.forms[0].submit();
        		  }
        	  }
        	  
          }
//           var thumbArray = [];
//           var thumbArray = ${thumbsUpBeans};
//           var thumbsize = ${thumbsize};
//           for (var i = 0; i < thumbsize; i++) {
//         	  if ()
//           }
			          
          // 第一層按讚的function
          function thumbUp(commentId, memberId) {
        	  console.log("會員名稱為:" + memberId);
        	  var x = "ComThumlike" + commentId; 
        	  var thumbLikeCount = document.getElementById(x);
        	  var count = thumbLikeCount.innerHTML; // 取得按讚數
        	  var y = "ComThumDislike" + commentId; 
        	  var thumbDisLikeCount = document.getElementById(y);
        	  var discount = thumbDisLikeCount.innerHTML; // 取得按爛數
        	  console.log("按讚數= " + count);
        	  if (memberId == null) {
        		  document.location.href="<c:url value='/member/crm/login' />";
         	  } 
        	  else {
        		  thumbLikeCount.innerHTML = "";
        		  thumbDisLikeCount.innerHTML = "";
        		  var xhr = new XMLHttpRequest();
        		  xhr.open("POST", "<c:url value='/thumbUpCalculate' />", true);
        		  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        		  xhr.send("commentId=" + commentId + "&memberId=" + memberId + "&count=" + count + "&discount=" + discount);
        		  xhr.onreadystatechange = function() {
        			  if (xhr.readyState == 4 && xhr.status == 200) {
        				  var CommentBean = JSON.parse(xhr.responseText);
        				  console.log("按讚數= " + CommentBean.likeCount);
        				  if (CommentBean.likeCount == 0) {
        					  ;
        				  } else {
        				  thumbLikeCount.innerHTML = CommentBean.likeCount;
        				  }
        				  if (CommentBean.dislikeCount == 0) {
        					  ;
        				  } else {
        				  thumbDisLikeCount.innerHTML = CommentBean.dislikeCount;
        				  }
        				 	
        			  }
        		  }
        	  }
          }
       // 第一層按爛的function
          function thumbDown(commentId, memberId) {
        	  console.log("會員名稱為:" + memberId);
        	  var x = "ComThumlike" + commentId;
        	  var thumbLikeCount = document.getElementById(x);
        	  var count = thumbLikeCount.innerHTML; // 取得按讚數
        	  var y = "ComThumDislike" + commentId;
        	  var thumbDisLikeCount = document.getElementById(y);
        	  var discount = thumbDisLikeCount.innerHTML; // 取得按爛數
        	  console.log("按爛數= " + count);
        	  if (memberId == null) {
        		  document.location.href="<c:url value='/member/crm/login' />";
         	  } 
        	  else {
        		  thumbDisLikeCount.innerHTML = "";
        		  thumbLikeCount.innerHTML = "";
        		  var xhr = new XMLHttpRequest();
        		  xhr.open("POST", "<c:url value='/thumbDownCalculate' />", true);
        		  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        		  xhr.send("commentId=" + commentId + "&memberId=" + memberId + "&count=" + count + "&discount=" + discount);
        		  xhr.onreadystatechange = function() {
        			  if (xhr.readyState == 4 && xhr.status == 200) {
        				  var CommentBean = JSON.parse(xhr.responseText);
        				  console.log("按爛數= " + CommentBean.dislikeCount);
        				  console.log("按讚數=" + CommentBean.likeCount);
        				  if (CommentBean.dislikeCount == 0) {
        					  ;
        				  } else {
        					  thumbDisLikeCount.innerHTML = CommentBean.dislikeCount;
        				  }
        				  if (CommentBean.likeCount == 0) {
        					  ;
        				  } else {
        					  thumbLikeCount.innerHTML = CommentBean.likeCount;
        				  }
        			  }
        		  }
          	  }
          }
          // 第二層按讚的function
          function SecThumbUp(commentSecId, memberId) {
        	  console.log("會員名稱為:" + memberId);
        	  var x = "SecComThumlike" + commentSecId;
        	  var thumbLikeCount = document.getElementById(x);
        	  var count = thumbLikeCount.innerHTML;
        	  var y = "SecComThumDislike" + commentSecId;
        	  var thumbDisLikeCount = document.getElementById(y);
        	  var discount = thumbDisLikeCount.innerHTML;
        	  console.log("按讚數= " + count);
        	  if (memberId == null) {
        		  document.location.href="<c:url value='/member/crm/login' />";
         	  } 
        	  else {
        		  thumbLikeCount.innerHTML = "";
        		  thumbDisLikeCount.innerHTML = "";
        		  var xhr = new XMLHttpRequest();
        		  xhr.open("POST", "<c:url value='/SecThumbUpCalculate' />", true);
        		  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        		  xhr.send("commentSecId=" + commentSecId + "&memberId=" + memberId + "&count=" + count + "&discount=" + discount);
        		  xhr.onreadystatechange = function() {
        			  if (xhr.readyState == 4 && xhr.status == 200) {
        				  var CommentSecBean = JSON.parse(xhr.responseText);
        				  console.log("按讚數= " + CommentSecBean.likeCount);
        				  if (CommentSecBean.likeCount == 0) {
        					  ;
        				  } else {
        				  thumbLikeCount.innerHTML = CommentSecBean.likeCount;
        				  }
        				  if (CommentSecBean.dislikeCount == 0) {
        					  ;
        				  } else {
        				  thumbDisLikeCount.innerHTML = CommentSecBean.dislikeCount;
        				  }
        				 	
        			  }
        		  }
        	  }
          }
          
          function SecThumbDown(commentSecId, memberId) {
        	  console.log("會員名稱為:" + memberId);
        	  var x = "SecComThumlike" + commentSecId;
        	  var thumbLikeCount = document.getElementById(x);
        	  var count = thumbLikeCount.innerHTML;
        	  var y = "SecComThumDislike" + commentSecId;
        	  var thumbDisLikeCount = document.getElementById(y);
        	  var discount = thumbDisLikeCount.innerHTML;
        	  console.log("按讚數= " + count);
        	  if (memberId == null) {
        		  document.location.href="<c:url value='/member/crm/login' />";
         	  } 
        	  else {
        		  thumbDisLikeCount.innerHTML = "";
        		  thumbLikeCount.innerHTML = "";
        		  var xhr = new XMLHttpRequest();
        		  xhr.open("POST", "<c:url value='/SecThumbDownCalculate' />", true);
        		  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        		  xhr.send("commentSecId=" + commentSecId + "&memberId=" + memberId + "&count=" + count + "&discount=" + discount);
        		  xhr.onreadystatechange = function() {
        			  if (xhr.readyState == 4 && xhr.status == 200) {
        				  var CommentSecBean = JSON.parse(xhr.responseText);
        				  console.log("按爛數= " + CommentSecBean.dislikeCount);
        				  if (CommentSecBean.dislikeCount == 0) {
        					  ;
        				  } else {
        					  thumbDisLikeCount.innerHTML = CommentSecBean.dislikeCount;
        				  }
        				  if (CommentSecBean.likeCount == 0) {
        					  ;
        				  } else {
        					  thumbLikeCount.innerHTML = CommentSecBean.likeCount;
        				  }
        				 	
        			  }
        		  }
        	  }
          }
          
          

			</script>
</body>

</html>
