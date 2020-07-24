<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>周邊商品首頁</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery-3.5.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery-3.3.1.slim.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous">
	
</script>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/PeripheralProduct.css">


</head>
<body>
<jsp:include page="/WEB-INF/views/top.jsp" />
<!-- 	<a href="/eeit114">回首頁</a> -->
<div >
	<nav class="all">
		<div class="wrapper" id="pic">
			<ul class="slides">
				<li><img src="${pageContext.request.contextPath}/images/下載.jpg"
					alt=""></li>
				<li><img
					src="${pageContext.request.contextPath}/images/下載2.jpg" alt=""></li>
				<li><img src="${pageContext.request.contextPath}/images/下載.jpg"
					alt=""></li>
			</ul>
			<ul class="dot">
				<li id="1"></li>
				<li id="2"></li>
				<li id="3"></li>
			</ul>
			<div id="prevSlide" class="slide_btn">
				<i class="fa fa-caret-left"></i>
			</div>
			<div id="nextSlide" class="slide_btn">
				<i class="fa fa-caret-right"></i>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="flex flex-300">
			<aside class="item item1">
				<div class="accordion" id="sidelist">

					<ul class="list-group">
						<li class="list-group-item" id="00">全部商品</li>
						<!--  						<a class="list-group-item"  href="#" id="0"> -->
						<li class="list-group-item" id="10">海報</li>

						<li class="list-group-item" id="20">杯子</li>

						<li class="list-group-item" id="30">行李箱</li>

						<li class="list-group-item" id="40">玩偶</li>

						<li class="list-group-item" id="50">其他</li>

					</ul>
				</div>
			</aside>
			<article class="item item2">
				<div class="flex-auto">

					<span><input
						style='width: 5cm; display: inline; margin-left: 20cm' id='select'
						type="text" name="" class="form-control" placeholder="Search">
						<button id='send' class="btn btn-md btn-primary ml-2">查詢</button></span>


					<div id='allproduct'>
						<div class='row'>
							<c:forEach var='products' items='${products}'>


								<div class="col-md-4">
									<div class="card mb">
										<img class="card-img-top"
											src="<c:url value='/getProductPicture/${products.productId}'/>"
											alt="Card image cap">
										<div class="card-body mb">
											<h4 class="card-title">${products.name}</h4>
											<h5 class="card-text">${products.company}</h5>
											<h5 class="card-text">NT$${products.price}</h5>
											<a
												href="<spring:url value='/Product?id=${products.productId}' />"
												class="btn btn-outline-secondary btn-block mb">查看商品</a>
										</div>
									</div>
								</div>

							</c:forEach>
						</div>
					</div>
				</div>
			</article>
		</div>
	</div>
</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/Peripheral.js"></script>

	<script>
		window.onload = function() {
			var a0 = document.getElementById("00");

			var a1 = document.getElementById("10");

			var a2 = document.getElementById("20");

			var a3 = document.getElementById("30");

			var a4 = document.getElementById("40");

			var a5 = document.getElementById("50");

			var xhr = new XMLHttpRequest;
			var CategoryId = null;
			var noProduct = "<h2>無此類別商品</h2>";

			//Ajax全部商品-------------------------------
			a0.onclick = function() {
				xhr.open("GET", "<c:url value='/Peripheralproduct2'/>", true)
				xhr.send();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var AllProduct = JSON.parse(xhr.responseText);

						var context = "<div class='row'>";

						for (var i = 0; i < AllProduct.length; i++) {
							context += "<div class='col-md-4'>"
									+ "<div class='card mb'>"
									+ "<img src=' <c:url value='/getProductPicture/"
								+AllProduct[i].productId
								+"'/>'"
									+ "class='card-img-top' alt='...'>"
									+ "<div class='card-body mb'>"
									+ "<h4 class='card-title'>"
									+ AllProduct[i].name
									+ "</h4>"
									+ "<h5 class='card-text'>"
									+ AllProduct[i].company
									+ "</h5>"
									+ "<h5 class='card-text'>"
									+ "NT$"
									+ AllProduct[i].price
									+ "</h5>"
									+ "<a href='<spring:url value='/Product?id="
									+ AllProduct[i].productId
									+ "' />'"
									+ "class='btn btn-outline-secondary btn-block mb'>"
									+ "查看商品" + "</a>" + "</div></div></div>"

						}
						var allproduct = document.getElementById("allproduct");
						allproduct.innerHTML = context;
					}
				}

			}

			function productCategory() {
				xhr.open("GET", "<c:url value='/Peripheralproducts?CategoryId="
						+ CategoryId + "'/>", true)
				xhr.send(null);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var AllProducts = JSON.parse(xhr.responseText);
						console.log(AllProducts.length)
						var allproduct = document.getElementById("allproduct");
						var context = "<div class='row'>";

						for (var i = 0; i < AllProducts.length; i++) {
							context += "<div class='col-md-4'>"
									+ "<div class='card mb'>"
									+ "<img src=' <c:url value='/getProductPicture/"
								+AllProducts[i].productId
								+"'/>'"
									+ "class='card-img-top' alt='...'>"
									+ "<div class='card-body mb'>"
									+ "<h4 class='card-title'>"
									+ AllProducts[i].name
									+ "</h4>"
									+ "<h5 class='card-text'>"
									+ AllProducts[i].company
									+ "</h5>"
									+ "<h5 class='card-text'>"
									+ "NT$"
									+ AllProducts[i].price
									+ "</h5>"
									+ "<a href='<spring:url value='/Product?id="
									+ AllProducts[i].productId
									+ "' />'"
									+ "class='btn btn-outline-secondary btn-block mb'>"
									+ "查看商品" + "</a>" + "</div></div></div>"

						}
						allproduct.innerHTML = context;
						if (AllProducts.length === 0) {
							allproduct.innerHTML = noProduct;
						}
					}
				}
			}

			//-------------Ajax海報商品---------		
			a1.onclick = function() {
				CategoryId = 1;
				productCategory();
			}
			//-------------Ajax杯子商品---------		
			a2.onclick = function() {
				CategoryId = 2;
				productCategory();
			}

			//-------------Ajax行李箱商品---------		
			a3.onclick = function() {
				CategoryId = 3;
				productCategory();

			}

			//-------------Ajax玩偶商品---------		
			a4.onclick = function() {
				CategoryId = 4;
				productCategory();
			}
			//-------------Ajax其他商品---------		
			a5.onclick = function() {
				CategoryId = 5;
				productCategory();
			}

			console.log(select)
			send.onclick = function() {
				var select = document.getElementById("select").value;
				var send = document.getElementById("send");
				console.log(111)
				xhr.open("GET", "<c:url value='/selectName?name=" + select
						+ "'/>", true)
				xhr.send();
				console.log(select)
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var AllProducts = JSON.parse(xhr.responseText);
						//console.log(AllProducts)
						var allproduct = document.getElementById("allproduct");
						var context = "<div class='row'>";

						for (var i = 0; i < AllProducts.length; i++) {
							context += "<div class='col-md-4'>"
									+ "<div class='card mb'>"
									+ "<img src=' <c:url value='/getProductPicture/"
								+AllProducts[i].productId
								+"'/>'"
									+ "class='card-img-top' alt='...'>"
									+ "<div class='card-body mb'>"
									+ "<h4 class='card-title'>"
									+ AllProducts[i].name
									+ "</h4>"
									+ "<h5 class='card-text'>"
									+ AllProducts[i].company
									+ "</h5>"
									+ "<h5 class='card-text'>"
									+ "NT$"
									+ AllProducts[i].price
									+ "</h5>"
									+ "<a href='<spring:url value='/Product?id="
									+ AllProducts[i].productId
									+ "' />'"
									+ "class='btn btn-outline-secondary btn-block mb'>"
									+ "查看商品" + "</a>" + "</div></div></div>"

						}
						allproduct.innerHTML = context;
						if (AllProducts.length === 0) {
							allproduct.innerHTML = "<h2>無此類別商品</h2>";
							;
						}
					}

				}
			}
		}
	</script>


</body>
</html>