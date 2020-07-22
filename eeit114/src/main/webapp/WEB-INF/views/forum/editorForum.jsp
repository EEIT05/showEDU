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
	height: 100%;
	background-repeat: no-repeat;
	background-image: linear-gradient(rgb(186, 240, 255), rgb(51, 65, 156),
		rgb(25, 0, 71));
	padding: 5px;
}

.panel-table .panel-footer .col {
	line-height: 34px;
	height: 34px;
}

.panel-table .panel-heading .col h3 {
	line-height: 50px;
	height: 50px;
}

.panel-table .panel-body .table-bordered>tbody>tr>td {
	line-height: 34px;
}
</style>



</head>
<body>
	<form>
		<input type="hidden" name="a" />
	</form>
	<link
		href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<!------ Include the above in your HEAD tag ---------->

	<link
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
		rel='stylesheet' type='text/css'>
	<div class="col-md-10 col-md-offset-1">

		<div class="panel panel-default panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-xs-6">
						<h2 class="panel-title">討論版後台</h2>
					</div>

				</div>
			</div>

			<div class="panel-body">
				<table id="table"
					class="table table-striped table-bordered table-list">
					<thead>
						<tr>
							<th>討論版編號</th>
							<th>電影名稱</th>
							<th>電影圖片</th>
							<th><em class="fa fa-cog"></em>文章管理</th>
							<th><em class="fa fa-cog"></em>&nbsp;資料修改</th>
							<th>資料刪除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var='boardBean' items='${allBoards}'>
							<tr>
								<td>${boardBean.boardId}</td>
								<td>${boardBean.movieBean.name}</td>
								<td><img width='110' height='50'
									src="<c:url value='/getPicture/${boardBean.movieBean.movieId}' />" /></td>
								<td><button class=" myMOUSEEE" type="button" onclick="article(${boardBean.boardId})">文章編輯</button></td>						
								<td><a class="btn btn-success" href="#"><em
										class="fa fa-pencil"></em>&nbsp;編輯</a></td>

								<td><a class="btn btn-danger"
									onclick="deleteBoard(${boardBean.boardId})"><em
										class="fa fa-trash"></em>&nbsp;刪除</a></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

				<hr>
				<button class=" myMOUSEEE" type="button" onclick="addBoard()">新增討論版</button>
				<a href="<c:url value='/'/> ">首頁</a>


			</div>


			<script type='text/javascript'>
			window.addEventListener('onclick', deleteArticle, false);
			
		
		// 新增討論版
		function addBoard() {
			document.location.href="<c:url value='/boards/add' />";
		}
		// 刪除討論版
		function deleteBoard(boardId) {
			if (confirm('確定刪除此討論版?')) {
				document.forms[0].action="<c:url value='/deleteBoard?boardId=" + boardId + "' />";
	     		document.forms[0].method="POST";
	     		document.forms[0].submit();
			}
		}
		
		
		// Ajax 顯示文章資料
		function article(boardId) {
			var xhr = new XMLHttpRequest;
			xhr.open("GET", "<c:url value='/getArticles' />" + "?boardId=" + boardId, true);
			xhr.send();
			xhr.onreadystatechange = function(){
				if (xhr.readyState == 4 && xhr.status == 200) {
					var allArticleBeans = JSON.parse(xhr.responseText);
					var table = document.getElementById("table");
					console.log(allArticleBeans[0].title);
					table.innerHTML = "";
					var content = "";
						content = "<thead>"
						content += "<tr><th>文章編號</th><th>標題 </th><th>" 
								+  "瀏覽數</th><th>類型 "
								+  "</th><th>發布者</th>"
								+  "<th><em class='fa fa-cog'></em>留言管理</th>"  
								+  "<th><em class='fa fa-cog'></em>&nbsp;資料修改</th>"
								+  "<th>資料刪除</th>"
								+  "</tr></thead><tbody>" 
					for (var i = 0; i < allArticleBeans.length; i++) {
						content += "<tr><td>" + allArticleBeans[i].artId + "</td>"
								+	"<td>" + allArticleBeans[i].title + "</td>"
								+	"<td>" + allArticleBeans[i].viewCount + "</td>"
								+	"<td>" + allArticleBeans[i].artTypeBean.type + "</td>"
								+	"<td>" + allArticleBeans[i].memberBean.name + "</td>"
								+   "<td><button class='myMOUSEEE' onclick='comment(" + allArticleBeans[i].artId + ")'>"
								+   "留言編輯</button></td>"
								+   "<td><a class='btn btn-success' onclick='updateArticle(" + allArticleBeans[i].artId + ")'>"
								+   "<em class='fa fa-pencil'></em>&nbsp;編輯</a></td>"
								+	"<td><a class='btn btn-danger' onclick='deleteArticle(" + allArticleBeans[i].artId + ")'>"
								+   "<em class='fa fa-trash'></em>&nbsp;刪除</a></td></tr>"
					}
						content += "</tbody>"
						table.innerHTML = content;
				}
			}
		}
		// Ajax 顯示留言資料
		function comment(artId) {
			var xhr = new XMLHttpRequest;
			xhr.open("GET", "<c:url value='/getComments' />" + "?artId=" + artId, true);
			xhr.send();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var allCommentBeans = JSON.parse(xhr.responseText);
					var table = document.getElementById("table");
					table.innerHTML = "";
					var content = "";
						content = "<thead>"
						content += "<tr><th>留言編號</th><th>內容 </th><th>" 
								+  "留言者</th>"
								+  "<th><em class='fa fa-cog'></em>回覆管理</th>"  
								+  "<th><em class='fa fa-cog'></em>&nbsp;資料修改</th>"
								+  "<th>資料刪除</th>"
								+  "</tr></thead><tbody>" 
						for (var i = 0; i < allCommentBeans.length; i++) {
							content += "<tr><td>" + allCommentBeans[i].commentId + "</td>"
							+	"<td>" + allCommentBeans[i].content + "</td>"
							+	"<td>" + allCommentBeans[i].memberBean.name + "</td>"
							+   "<td><button class='myMOUSEEE' onclick='secComment(" + allCommentBeans[i].commentId + ")'>"
							+   "回覆編輯</button></td>"
							+   "<td><a class='btn btn-success' onclick='updateComment(" + allCommentBeans[i].commentId + ")'>"
							+   "<em class='fa fa-pencil'></em>&nbsp;編輯</a></td>"
							+	"<td><a class='btn btn-danger' onclick='deleteComment(" + allCommentBeans[i].commentId + ")'>"
							+   "<em class='fa fa-trash'></em>&nbsp;刪除</a></td></tr>"
						}
						content += "</tbody>"
						table.innerHTML = content;
				}
			}
		}
		// Ajax 顯示回覆資料
		function secComment(commentId) {
			var xhr = new XMLHttpRequest;
			xhr.open("GET", "<c:url value='/getSecComments' />" + "?commentId=" + commentId, true);
			xhr.send();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var allCommentSecBeans = JSON.parse(xhr.responseText);
					var table = document.getElementById("table");
					table.innerHTML = "";
					var content = "";
						content = "<thead>"
						content += "<tr><th>回覆編號</th><th>內容 </th><th>" 
								+  "回覆者</th>"
								+  "<th><em class='fa fa-cog'></em>&nbsp;資料修改</th>"
								+  "<th>資料刪除</th>"
								+  "</tr></thead><tbody>" 
						for (var i = 0; i < allCommentSecBeans.length; i++) {
							content += "<tr><td>" + allCommentSecBeans[i].commentSecId + "</td>"
							+	"<td>" + allCommentSecBeans[i].content + "</td>"
							+	"<td>" + allCommentSecBeans[i].memberBean.name + "</td>"
							+   "<td><a class='btn btn-success' onclick='updateSecComment(" + allCommentSecBeans[i].commentSecId + ")'>"
							+   "<em class='fa fa-pencil'></em>&nbsp;編輯</a></td>"
							+	"<td><a class='btn btn-danger' onclick='deleteSecComment(" + allCommentSecBeans[i].commentSecId + ")'>"
							+   "<em class='fa fa-trash'></em>&nbsp;刪除</a></td></tr>"
						}
						content += "</tbody>"
						table.innerHTML = content;
				}
			}
		}
		
		// 刪除文章
		function deleteArticle(artId) {
			if (confirm('確定刪除此文章?')) {
				document.forms[0].action="<c:url value='/deleteArticle?artId=" + artId + "' />";
	     		document.forms[0].method="POST";
	     		document.forms[0].submit();
			}
		}
		// 刪除留言
		function deleteComment(commentId) {
			if (confirm('確定刪除此留言?')) {
				document.forms[0].action="<c:url value='/deleteComment?commentId=" + commentId + "' />";
	     		document.forms[0].method="POST";
	     		document.forms[0].submit();
			}
		}
		// 刪除回覆
		function deleteSecComment(commentSecId) {
			if (confirm('確定刪除此回覆?')) {
				document.forms[0].action="<c:url value='/deleteSecComment?commentSecId=" + commentSecId + "' />";
	     		document.forms[0].method="POST";
	     		document.forms[0].submit();
			}
		}
		
		
	</script>
</body>
</html>