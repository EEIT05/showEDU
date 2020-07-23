<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/jquery-3.5.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/jquery-3.3.1.slim.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<!-- sweet訊息框 -->
<script type="text/javascript"
	src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />

</head>
<body>
	<div class="container">

		<a style="margin: 10px" class="btn btn-primary"
			href="<spring:url value='/product/add'/>"> <i class="fa fa-plus"
			style="margin-right: 5px"></i>新增
		</a>
		<input type="button" value="首頁" onclick="location.href='<c:url value='/back'/>'" style="width:91px;">
		<button id='clickmeS'>click me</button>

		<div id="table" class="row">
						<table class="table table-hover table-striped">
						<thead>
							<tr class="thead-dark">
								<th>商品編號</th>
								<th>商品名稱</th>
								<th>類別種類</th>
								<th>商品類別</th>
								<th>商品價格</th>
								<th>商品庫存</th>
								<th>商品狀態</th>
								<th>動作</th>
							</tr>
						</thead>
						<tbody>
								<c:forEach var='ProductSelect' items='${getAllProductSelect}'>
								<tr>

									<td>${ProductSelect.productId}</td>
									<td>${ProductSelect.name}</td>
									<td>${ProductSelect.categoryBean.category}</td>
									<td>${ProductSelect.productCategoryBean.items}</td>
									<td>${ProductSelect.price}</td>
									<td>${ProductSelect.stock}</td>
									<td>${ProductSelect.status}</td>

									<td><a
										href="<spring:url value='/product/backUpdate/${ProductSelect.productId}' />"
										class="btn btn-warning"><i class="fa fa-pencil"></i></a> | <a
										class='btn btn-danger deletelink' 
										href="${pageContext.request.contextPath}/product/backUpdate/delete/${ProductSelect.productId}">
											<i class='fa fa-trash-o'></i>
									</a></td>

								</tr>
							</c:forEach>
						</tbody>
						</table>
						
			<c:choose>
				<c:when test="${empty getAllProductSelect}">
					<h1>沒有任何商品資料</h1>
					<br>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script>
		window.onload = function() {
			var btn = document.getElementById("clickmeS");
			btn.onclick = function() {
				var xhr = new XMLHttpRequest();
				xhr.open("GET", "<c:url value='/product/backSelect2'/>", true);
				xhr.send();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {

						var context = "<table class='table table-hover table-striped'>"
								+ "<thead>"
								+ "<tr class='thead-dark'><th>商品編號</th><th>商品名稱</th><th>類別種類</th><th>商品類別</th><th>商品價格</th><th>商品庫存</th><th>商品狀態</th><th>動作</th></tr>"
								+ "</thead>" + "<tbody>";
						var products = JSON.parse(xhr.responseText);

						for (var i = 0; i < products.length; i++) {

							context += "<tr><td>"
									+ products[i].productId
									+ "</td>"
									+ "<td>"
									+ products[i].name
									+ "</td>"
									+ "<td>"
									+ products[i].categoryBean.category
									+ "</td>"
									+ "<td>"
									+ products[i].productCategoryBean.items
									+ "</td>"
									+ "<td>"
									+ products[i].price
									+ "</td>"
									+ "<td>"
									+ products[i].stock
									+ "</td>"
									+ "<td>"
									+ products[i].status
									+ "</td>"
									+ "<td>"
									+ "<a href='<spring:url value='/product/backUpdate/"
									+ products[i].productId
									+"' />'"
									+ "class='btn btn-warning'><i class='fa fa-pencil'></i></a> | "
									+ "<a class='btn btn-danger deletelink'  href='${pageContext.request.contextPath}/product/backUpdate/delete/"
									+products[i].productId
									+"'>"
									+ "<i class='fa fa-trash-o'></i></a>"
									+ "</td></tr>";

						}
						context += "</tbody>" + "</table>";
						var divs = document.getElementById("table");
						divs.innerHTML = context;
					}
				}
			}

		}

$(document).ready(function() {
			$('.deletelink').click(function() {

				if (confirm('確定刪除此筆紀錄? ')) {

					return alert('刪除成功');
				} else {
					return false;
				}

			});

		});
	</script>
</body>
</html>