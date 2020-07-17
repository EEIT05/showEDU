<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/styles.css'
	type="text/css" />
<style type="text/css">
fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 400px;
	margin: auto;
}
</style>
<title>EDUShowtime後台-新增電影</title>
<link rel='stylesheet' href='css/styles.css' type="text/css" />
</head>
<body>
	<section>
		<div class="container">
			<h1 style="text-align: center">
				<a>新增電影</a>
			</h1>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container">
		<!--       三個地方要完全一樣 -->
		<form:form method='POST' modelAttribute="movieBean" class='form-horizontal'
			enctype="multipart/form-data" >
		
			<fieldset>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='chName'>
						<a>名稱(中文):</a>
					</label>
					<div class="col-lg-10">
						<form:input id="chName" path="chName" type='text'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='enName'>
						<a>名稱(英文):</a>
					</label>
					<div class="col-lg-10">
						<form:input id="enName" path="enName" type='text'
							class='form:input-large' />
					</div>
				</div>
				
								<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="movieImage">
						<a>圖片:</a>
					</label>
					<div class='col-lg-10'>
						<form:input id="movieImage" path="movieImage" type='file'
							class='form:input-large' />
					</div>
				</div>
				
				
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="movieLevelId">
						<a>級別:</a>
					</label>
					<div class='col-lg-10'>
						<form:select path="movieLevelId">
							<form:options items="${movieLevelList}" />
						</form:select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='length'>
						<a>片長:</a>
					</label>
					<div class="col-lg-10">
						<form:input id="length" path="length" type='text'
							class='form:input-large' />
					</div>
				</div>
				
								<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='director'>
						<a>導演:</a>
					</label>
					<div class="col-lg-10">
						<form:input id="director" path="director" type='text'
							class='form:input-large' />
					</div>
				</div>
				
								<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='actors'>
						<a>演員:</a>
					</label>
					<div class="col-lg-10">
						<form:input id="actors" path="actors" type='text'
							class='form:input-large' />
					</div>
				</div>
				
							<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='trailer'>
						<a>預告:</a>
					</label>
					<div class="col-lg-10">
						<form:input id="trailer" path="trailer" type='text'
							class='form:input-large' />
					</div>
				</div>
				
							<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='synopsis'>
						<a>簡介:</a>
					</label>
					<div class="col-lg-10">
						<form:input id="synopsis" path="synopsis" type='text'
							class='form:input-large' />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='premierDate'>
						<a>上映日期:</a>
					</label>
					<div class="col-lg-10">
						<form:input id="premierDate" path="premierDate" type='date'
							class='form:input-large' />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='offDate'>
						<a>下檔日期:</a>
					</label>
					<div class="col-lg-10">
						<form:input id="offDate" path="offDate" type='date'
							class='form:input-large' onchange='test(this.value)'/>
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="movieStatusId">
						<a>上映狀態</a>
					</label>
					<div class='col-lg-10'>
						<form:select path="movieStatusId">
							<form:options items="${movieStatusList}" />
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<div class='col-lg-offset-2 col-lg-10'>
						<input id="btnAdd" type='submit' class='btn btn-primary'
							value="新增" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
	<script>
	function test(date){
// 		console.log(date);
	}
	</script>
</body>
</html>
