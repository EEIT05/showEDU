<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

			<!-- Post Content Column -->

			<div class="col-lg-8">

				<!-- Title -->
				<h1 class="mt-4">[${articleBean.artTypeBean.type}]&emsp;&emsp;&emsp;${articleBean.title}</h1>

				<!-- Author -->
				<img class="d-flex mr-3 rounded-circle"
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
				<img class="img-fluid rounded" src="http://placehold.it/900x300"
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

				<!-- Comments Form -->
				<div class="card my-2">
					<p class="card-header">
						<button type="submit" class="btn btn-primary">留言</button>
						<button type="submit" class="btn btn-primary">收藏</button>
					</p>


				</div>

				<!-- Comment with nested comments -->
				<c:forEach var='commentBean' items='${commentBeans}'>
					<div class="media mb-4">
						<img class="d-flex mr-3 rounded-circle"
							src="<c:url value='/getPictureComment/${commentBean.memberBean.memberId}' />"
							alt="${commentBean.memberBean.memberId}">
						<div class="media-body">
							<h5 class="mt-0" style="float: left">${commentBean.memberBean.name}</h5>
							<p>&emsp;${commentBean.time}</p>
							<p>${commentBean.content}</p>
							<div >
								<i class="fa fa-thumbs-up myMOUSE" aria-hidden="true"
									id="ComThumlike${commentBean.commentId}"
									onclick="thumbUp(${commentBean.commentId}, ${loginMember.memberId})">
									
									<c:if test="${commentBean.likeCount != 0}">
									${commentBean.likeCount}
									</c:if>
								</i> &emsp; <i class="fa fa-thumbs-down myMOUSE" aria-hidden="true"
									id="ComThumDislike${commentBean.commentId}"
									onclick="thumbDown(${commentBean.commentId}, ${loginMember.memberId})">
									<c:if test="${commentBean.dislikeCount != 0}">
									${commentBean.dislikeCount}
									</c:if>
								</i> &emsp;
								<button  onclick="SecComment(${commentBean.commentId}, ${loginMember.memberId})"; style="margin: 3px; padding: 3px 2px 3px 2px; " class="btn btn-outline-secondary" type="button">回覆</button>
								<button  onclick="SecSubmit(${commentBean.commentId}, ${loginMember.memberId})"; style="margin: 3px; padding: 3px 2px 3px 2px; " class="btn btn-outline-secondary" type="button">送出</button>
								<input id="inputSecComment${commentBean.commentId}" type="text" class="form-control" placeholder=""
										aria-label="" aria-describedby="basic-addon1"  style="display:none">
									

<!-- 								<a href="#" style="text-decoration: none;">回覆</a> -->
							</div>
							<c:forEach var='commentSecBean' items='${commentSecList}'>
								<c:if
									test="${commentBean.commentId == commentSecBean.commentBean.commentId}">
									<div class="media mt-4">
										<img class="d-flex mr-3 rounded-circle"
											src="<c:url value='/getPictureComment/${commentSecBean.memberBean.memberId}' />"
											alt="${commentSecBean.memberBean.memberId}">
										<div class="media-body">
											<h5 class="mt-0" style="float: left">${commentSecBean.memberBean.name}</h5>
											<p>&emsp;&emsp;${commentSecBean.time}</p>
											<p>${commentSecBean.content}</p>
											<div>

												<i class="fa fa-thumbs-up myMOUSE" aria-hidden="true"
													id="SecComThumlike${commentSecBean.commentSecId}"
													onclick="SecThumbUp(${commentSecBean.commentSecId}, ${loginMember.memberId})">
													<c:if test="${commentSecBean.likeCount != 0}">
													${commentSecBean.likeCount}
													</c:if>
												</i>&emsp; <i class="fa fa-thumbs-down myMOUSE"
													aria-hidden="true"
													id="SecComThumDislike${commentSecBean.commentSecId}"
													onclick="SecThumbDown(${commentSecBean.commentSecId}, ${loginMember.memberId})">
													<c:if test="${commentSecBean.dislikeCount != 0}">
													${commentSecBean.dislikeCount}
													</c:if>
												</i> &emsp;
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
										<h4 style="align-content: center;">復仇者聯盟復仇之戰&emsp;&emsp;</h4>
									</div>

									<div class="div2">
										&emsp;上集無線之戰講到薩諾斯消滅了一半的生物，鋼鐵人與薩諾斯的女兒涅布拉在宇宙中漂流，在太空船失去動力與剩餘氧氣不到48小時後，鋼鐵人絕望了，用剩下的一點力氣，留下遺言，給他所愛的小辣椒，然後靜靜睡了等待死亡的到來，但是驚奇隊長來了，並帶他回到地球上，然而回到地球後，美國隊長想要招集人去找薩諾斯復仇，但東尼經過那一戰，已經心灰意冷，絕對退出復仇者，加上他身體已經十分虛弱，昏了過去。
									</div>
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
        	  } 
          }
          
          
         
          window.addEventListener('onclick', SecThumbDown, false)
		  window.addEventListener('onclick', thumbUp, false);
		  window.addEventListener('onclick', thumbDown, false);
		  window.addEventListener('onclick', SecThumbUp, false);
		  window.addEventListener('onclick', SecComment, false);
		  window.addEventListener('onclick', SecSubmit, false);
          function SecComment(commentId, memberId) {
        	  if (memberId == null) {
        		  document.location.href="<c:url value='/member/crm/login' />";
         	  } else{
        	      var x = "inputSecComment" + commentId;
        	      var input = document.getElementById(x);
        	      input.style.display = 'block';
         	  } 
        	  
          }
          // 第一層留言回覆送出
          function SecSubmit(commentId, memberId) {
        	  if (memberId == null) {
        		  return; //請先登入
        	  } else {
        		  console.log("會員名稱為:" + memberId);
        		  var x = "inputSecComment" + commentId;
        		  var input = document.getElementById(x);
        		  var SecContent = input.value;
        		  alert(SecContent);
        		  if (SecContent.length < 0) {
        			  return; // 輸入不可為空白
        		  } else {
        			  document.forms[0].action="<c:url value='/addSecComment?commentId="+ commentId + "&memberId=" + memberId + "&SecContent=" + SecContent + "' />";
             		  document.forms[0].method="POST";
             		  document.forms[0].submit();
        		  }
        	  }
        	  
          }
          // 第一層按讚的function
          function thumbUp(commentId, memberId) {
        	  console.log("會員名稱為:" + memberId);
        	  var x = "ComThumlike" + commentId;
        	  var thumbLikeCount = document.getElementById(x);
        	  var count = thumbLikeCount.innerHTML;
        	  console.log("按讚數= " + count);
        	  if (memberId == null) {
        		  document.location.href="<c:url value='/member/crm/login' />";
         	  } 
        	  else {
        		  thumbLikeCount.innerHTML = "";
        		  var xhr = new XMLHttpRequest();
        		  xhr.open("POST", "<c:url value='/thumbUpCalculate' />", true);
        		  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        		  xhr.send("commentId=" + commentId + "&memberId=" + memberId + "&count=" + count);
        		  xhr.onreadystatechange = function() {
        			  if (xhr.readyState == 4 && xhr.status == 200) {
        				  var CommentBean = JSON.parse(xhr.responseText);
        				  console.log("按讚數= " + CommentBean.likeCount);
        				  if (CommentBean.likeCount == 0) {
        					  ;
        				  } else {
        				  thumbLikeCount.innerHTML = CommentBean.likeCount;
        				  }
        				 	
        			  }
        		  }
        	  }
          }
       // 第一層按爛的function
          function thumbDown(commentId, memberId) {
        	  console.log("會員名稱為:" + memberId);
        	  var x = "ComThumDislike" + commentId;
        	  var thumbDisLikeCount = document.getElementById(x);
        	  var count = thumbDisLikeCount.innerHTML;
        	  console.log("按爛數= " + count);
        	  if (memberId == null) {
        		  document.location.href="<c:url value='/member/crm/login' />";
         	  } 
        	  else {
        		  thumbDisLikeCount.innerHTML = "";
        		  var xhr = new XMLHttpRequest();
        		  xhr.open("POST", "<c:url value='/thumbDownCalculate' />", true);
        		  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        		  xhr.send("commentId=" + commentId + "&memberId=" + memberId + "&count=" + count);
        		  xhr.onreadystatechange = function() {
        			  if (xhr.readyState == 4 && xhr.status == 200) {
        				  var CommentBean = JSON.parse(xhr.responseText);
        				  console.log("按爛數= " + CommentBean.dislikeCount);
        				  if (CommentBean.dislikeCount == 0) {
        					  ;
        				  } else {
        					  thumbDisLikeCount.innerHTML = CommentBean.dislikeCount;
        				  }
        			  }
        		  }
          	  }
          }
          
          function SecThumbUp(commentSecId, memberId) {
        	  console.log("會員名稱為:" + memberId);
        	  var x = "SecComThumlike" + commentSecId;
        	  var thumbLikeCount = document.getElementById(x);
        	  var count = thumbLikeCount.innerHTML;
        	  console.log("按讚數= " + count);
        	  if (memberId == null) {
        		  document.location.href="<c:url value='/member/crm/login' />";
         	  } 
        	  else {
        		  thumbLikeCount.innerHTML = "";
        		  var xhr = new XMLHttpRequest();
        		  xhr.open("POST", "<c:url value='/SecThumbUpCalculate' />", true);
        		  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        		  xhr.send("commentSecId=" + commentSecId + "&memberId=" + memberId + "&count=" + count);
        		  xhr.onreadystatechange = function() {
        			  if (xhr.readyState == 4 && xhr.status == 200) {
        				  var CommentSecBean = JSON.parse(xhr.responseText);
        				  console.log("按讚數= " + CommentSecBean.likeCount);
        				  if (CommentSecBean.likeCount == 0) {
        					  ;
        				  } else {
        				  thumbLikeCount.innerHTML = CommentSecBean.likeCount;
        				  }
        				 	
        			  }
        		  }
        	  }
          }
          
          function SecThumbDown(commentSecId, memberId) {
        	  console.log("會員名稱為:" + memberId);
        	  var x = "SecComThumDislike" + commentSecId;
        	  var thumbDisLikeCount = document.getElementById(x);
        	  var count = thumbDisLikeCount.innerHTML;
        	  console.log("按讚數= " + count);
        	  if (memberId == null) {
        		  document.location.href="<c:url value='/member/crm/login' />";
         	  } 
        	  else {
        		  thumbDisLikeCount.innerHTML = "";
        		  var xhr = new XMLHttpRequest();
        		  xhr.open("POST", "<c:url value='/SecThumbDownCalculate' />", true);
        		  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        		  xhr.send("commentSecId=" + commentSecId + "&memberId=" + memberId + "&count=" + count);
        		  xhr.onreadystatechange = function() {
        			  if (xhr.readyState == 4 && xhr.status == 200) {
        				  var CommentSecBean = JSON.parse(xhr.responseText);
        				  console.log("按爛數= " + CommentSecBean.dislikeCount);
        				  if (CommentSecBean.dislikeCount == 0) {
        					  ;
        				  } else {
        					  thumbDisLikeCount.innerHTML = CommentSecBean.dislikeCount;
        				  }
        				 	
        			  }
        		  }
        	  }
          }
          
//         	  	else {
//         		  document.forms[0].action="<c:url value='/thumbUpCalculate?commentId="+ commentId + "&memberId=" + memberId + "' />";
//         		  document.forms[0].method="POST";
//         		  document.forms[0].submit();
//         	  }
       	  
//   			console.log("留言Id為:"+ commentId);
//   			console.log("按讚數為:"+ likeCount);
  			
//   		}
//           function thumbDown(commentId,dislikeCount, memberId) {
//   	       		if (memberId == null) {
//   	      		  document.location.href="<c:url value='/login' />";
//   	       	  } else {
//   	      		  document.forms[0].action="<c:url value='/thumbDownCalculate?commentId="+ commentId + "&memberId=" + memberId + "' />";
//   	      		  document.forms[0].method="POST";
//   	      		  document.forms[0].submit();
//   	      	  }
//          }
			</script>
		<form>
			<input type="hidden" name="a" />
		</form>
</body>

</html>
