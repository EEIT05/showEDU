<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/jquery-3.3.1.slim.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous">
	
</script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<!-- <script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath}/Js/Peripheral.js"></script> --%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/productitem.css">
<!-- 	top.jsp bootstrap -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>	
	
</head>
<body>
	<jsp:include page="/WEB-INF/views/top.jsp" />
	<div class="container-fluid">
		<div class="flex flex-300">
		
			<aside class="item item1">
		
				<img class='picture' src="<c:url value='/getProductPicture/${product.productId}'/>">

			</aside>
			<article class="item item2">
			<form action="<c:url value='addCart' />" method="POST">
				<div class="flex-auto">
			
					<h2 id='name' name='name'>${product.name}</h2>
				</div>
				<br>
				<div>
					<h2 id='price' name='price'>NT$${product.price}</h2>
				</div>
				<div>
					<input type='hidden' id='check' value='${product.stock}'>
					<p id='stock' class="stock-div">庫存剩餘量: ${product.stock}</p>
				</div>
				
				
				<div>
						<label for="buyCount">數量:</label>
						<br>
						<select id="buyCount" name='buyCount' style="width: 3cm; height: 1cm;" >
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
				</div>
				<div>
					<Input type='hidden' name='productId' value='${product.productId}'>
<%-- 					<Input type='hidden' name='name' value='${product.name}'> --%>
<%-- 					<Input type='hidden' name='price' value='${product.price}'> --%>
					<button id='send' type='submit' class="btn btn-danger buycar"
						style="width: 10cm;">
						<i class="fa fa-shopping-cart" aria-hidden="true"></i> 加入購物車
					</button>
				</div>
				</form>
				
				<div class="buyitem">
					<p>付款後，從備貨到寄出商品為 2 個工作天。（不包含假日）</p>
					<p>設計館提供統一發票或免用統一發票收據</p>
					<hr>
				</div>
				<div>【購買注意事項】</div>
			</article>
			
		</div>
	</div>
	
<script>

function checkstock(){
	var check = document.getElementById("check").value;
	
	checkInt = parseInt(check)
	
    var buyCount = document.getElementById("buyCount").value;
	
	buyCountInt = parseInt(buyCount)
	
    console.log(buyCountInt)
    console.log(checkInt)
    if (checkInt < buyCountInt) {  	
      alert("庫存量小於購買量");
      parent.location.reload();
      //buyCount.fireEvent("onchange");
    }
    
  }
window.onload = function () {
    
    
    document.getElementById("buyCount").onchange = function(){checkstock();} 
  }
</script>

</body>
</html>