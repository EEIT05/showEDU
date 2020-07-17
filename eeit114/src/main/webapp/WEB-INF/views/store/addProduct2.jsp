<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增商品</title>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/jquery-3.5.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/jquery-3.3.1.slim.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous">
	
</script>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<style type="text/css">
.flex {
	display: inline-flex;
	height: auto;
	margin-top: 30px;
	vertical-align: top;
}

.flex-300 {
	width: 80%;
	/*             margin-left: 7em; */
}

.item {
	/* height: auto; */
	text-align: left;
	line-height: 20px;
}

.item1 {
	height: 100%;
	flex: 1 2 80px;
}

.item2 {
	height: auto;
	flex: 2 1 900px;
}

#title {
	margin-left: 30px;
}

img {
	height: 200px;
	width: 50px;
}

.product {
	display: inline-flex;
	margin: 30px;
	height: 320px;
	width: 200px;
}

.card-title {
	/* 讓商品標題超出文字以...顯示 */
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

.card-header {
	padding: 20px;
	border-bottom: 0px;
	background-color: transparent;
}

.card {
	border: 1px black solid;
	color: black;
}

.card-link a {
	color: black;
	text-decoration: none;
}

.card-header a {
	text-decoration: none;
	color: black;
}

span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="flex flex-300">
			<aside class="item item1">
<%-- 				<%@include file="types/category.jsp"%> --%>
			</aside>
			<section class="item item2">

				<h1 style="text-align: center">新增商品資料</h1>
				<hr>

				<form:form method='POST' modelAttribute="productBean"
					enctype="multipart/form-data" class='form-horizontal'
					style="padding-left:5cm">
					<fieldset>
						<div class="form-group">
							<label class="control-label col-lg-0 col-lg-0" for='name'>商品名稱:</label>
							<form:input id="name" path="name" type='text'
								class='form:input-large' />
							<form:errors path="name" cssClass="error" />
						</div>
						<div class="form-group">
							<label class="control-label col-lg-0 col-lg-0" for='categoryId'>商品類別:</label>
							<form:select id="categoryId" path="categoryId" type='text'
								class='form:input-large'>
								<form:option value="-1" label="請挑選" />
								<form:options items="${categoryList}" />
							</form:select>
							<form:errors path="categoryId" cssClass="error" />
						</div>
						<div class="form-group">
							<label class="control-label col-lg-0 col-lg-0"
								for='productCategoryId'>商品種類:</label>
							<form:select id="productCategoryId" path="productCategoryId"
								type='text' class='form:input-large'>
								<form:option value="-1" label="請挑選" />
								<form:options items="${ProductCategoryList}" />
							</form:select>
							<form:errors path="productCategoryId" cssClass="error" />
						</div>
						<div class="form-group">
							<label class="control-label col-lg-0 col-lg-0" for=company>廠商:</label>
							<form:input id="company" path="company" type='text'
								class='form:input-large' />
						</div>
						<div class="form-group">
							<label class="control-label col-lg-0 col-lg-0" for='price'>商品價格:</label>
							<form:input id="price" path="price" type='text'
								class='form:input-large' />
							<form:errors path="price" cssClass="error" />
						</div>
						<div class="form-group">
							<label class="control-label col-lg-0 col-lg-0" for='stock'>商品庫存:</label>
							<form:input id="stock" path="stock" type='text'
								class='form:input-large' />
							<form:errors path="stock" cssClass="error" />
						</div>
						<div class="form-group">
							<label class="control-label col-lg-0 col-lg-0" for='detail'>商品說明:</label>
							<form:input id="detail" path="detail" type='text'
								class='form:input-large' />
						</div>
						<div class="form-group">
							<label class="control-label col-lg-0 col-lg-0" for='status'>商品狀態:</label>
							<form:select id="status" path="status" type='text'
								class='form:input-large'>

								<form:option value="A" label="上架" />
								<form:option value="D" label="下架" />
							</form:select>
						</div>
						<div class="form-group">
							<label class='control-label col-lg-0 col-lg-0' for="productImage"></label>
							<form:input id="productImage" path="productImage" type='file'
								class='form:input-large' />
						</div>
						<div class="form-group">
							<div class='col-lg-offset-2 col-lg-10'>
								<button id="btnAdd" type='submit' class='btn btn-primary'>新增</button>
							</div>
						</div>
					</fieldset>
				</form:form>
			</section>
		</div>
	</div>
</body>
</html>