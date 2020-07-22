<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<style type="text/css">
<
link

 

rel

 

='
stylesheet


'
href

 

='${
pageContext

 

.request


.contextPath


	


}
/
css /styles.css ' type ="text /css" />fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 400px;
	margin: auto;
}
</style>
<title>AddBoard</title>
</head>
<body>
	<section>
		<div class="container">
			<h1 style="text-align: center">新增電影討論版資料</h1>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container">
		<!--       三個地方要完全一樣 -->
		<!-- 回傳一個新增表單,要用POST , modelAttribute 一定要加 看controller-->
		<!-- 沒圖片不用加他(enctype="multipart/form-data" )用預設值  , 有圖片一訂要加(enctype="multipart/form-data" )-->
		<form:form method='POST' modelAttribute="boardBean"
			class='form-horizontal'>
			<fieldset>
				<div class="form-group">
					<label class='control-label col-lg-2 col-lg-2' for="movieId">
						電影名稱 </label>
					<div class='col-lg-10'>
						<form:select path="movieId">
							<form:option value="-1" label="請挑選" />
							<form:options items="${movieNameList}" />
						</form:select>
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