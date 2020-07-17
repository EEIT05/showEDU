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
<style type="text/css">
fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 400px;
	margin: auto;
}
#descr{
	height: 80px
}
</style>
<title>Activity</title>
</head>
<body>
	<section>
		<div class="container">
			<h1 style="text-align: center">修改活動</h1>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container">
		<!--       三個地方要完全一樣     modelAttribute, @ModelAttribute-->
		<form:form method='POST' modelAttribute="activityBean" enctype="multipart/form-data" class='form-horizontal'>
			<c:if test='${activityBean.actId != null}'>
                 <form:hidden path="actId" /><br>&nbsp;
			</c:if>
			<fieldset>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='actTitle'>
						活動名稱 </label>
					<div class="col-lg-10">
						<form:input id="actTitle" path="actTitle" type='text'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='startDate'>
						開始日期 </label>
					<div class="col-lg-10">
						<form:input id="startDate" path="startDate" type='Date'
							class='form:input-large' />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for='endDate'>
						截止日期 </label>
					<div class="col-lg-10">
						<form:input id="endDate" path="endDate" type='Date'
							class='form:input-large' />
					</div>
				</div>

				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="descr">
						活動詳細 </label>
					<div class='col-lg-10'>
						<div class="col-lg-10">
							<form:textarea id="descr" path="descr" 
								class='form:input-large' />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="activityImage">
						活動圖片 </label>
					<div class='col-lg-10'>
						<form:input id="activityImage" path="activityImage" type='file'
							class='form:input-large' />
						<form:input id="fileName" path="fileName" type='hidden'
							class='form:input-large' />
						<form:input id="actImg" path="actImg" type='hidden'
							class='form:input-large' /> 	 	 
					</div>
				</div>
				
				<div class="form-group">
					<div class='col-lg-offset-2 col-lg-10'>
						<input id="btnAdd" type='submit' class='btn btn-primary'
							value="送出" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
</body>
</html>
