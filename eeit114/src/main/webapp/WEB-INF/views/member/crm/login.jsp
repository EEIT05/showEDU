<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<!-- 下面有修改 -->
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">


<title>會員登入</title>
<style type="text/css">
span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}

body, html {
	background-image: url('https://i.imgur.com/w439mEw.jpg');
	background-repeat: no-repeat;
	background-size: cover;
	width: 100%;
	height: 100vh;
	overflow: auto;

	/* 	height: 110%; */
	/* 	background-repeat: no-repeat; */
	/* 	background-image: linear-gradient(rgb(186, 240, 255), rgb(51, 65, 156), */
	/* 		rgb(25, 0, 71)); */
}

.card-container.card {
	max-width: 382px;
	padding: 40px 40px;
}

.btn {
	font-weight: 700;
	height: 36px;
	-moz-user-select: none;
	-webkit-user-select: none;
	user-select: none;
	cursor: default;
}

/*
 * Card component
 */
.card {
	background-color: #F7F7F7;
	/* just in case there no content*/
	padding: 20px 25px 30px;
	margin: 0 auto 25px;
	margin-top: 20px;
	/* shadows and rounded borders */
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
	width: 96px;
	height: 96px;
	margin: 0 auto -15px;
	display: block;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
}

/*
 * Form styles
 */
.profile-name-card {
	font-size: 16px;
	font-weight: bold;
	text-align: center;
	margin: 5px 0 0;
	min-height: 1em;
}

.reauth-email {
	display: block;
	color: #404040;
	line-height: 2;
	margin-bottom: 4px;
	font-size: 14px;
	text-align: center;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin #inputEmail, .form-signin #inputPassword {
	direction: ltr;
	height: 44px;
	font-size: 16px;
}

.form-signin input[type=email], .form-signin input[type=password],
	.form-signin input[type=text], .form-signin button {
	width: 100%;
	display: block;
	margin-bottom: -5px;
	z-index: 1;
	position: relative;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin .form-control:focus {
	border-color: rgb(104, 145, 162);
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgb(104, 145, 162);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgb(104, 145, 162);
}

.btn.btn-signin {
	/*background-color: #4d90fe; */
	background-color: rgb(255, 158, 3);
	/* background-color: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));*/
	padding: 0px;
	font-weight: 700;
	font-size: 14px;
	height: 36px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	border: none;
	-o-transition: all 0.218s;
	-moz-transition: all 0.218s;
	-webkit-transition: all 0.218s;
	transition: all 0.218s;
}

.btn.btn-signin1 {
	/*background-color: #4d90fe; */
	background-color: rgb(35, 191, 0);
	/* background-color: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));*/
	padding: 0px;
	font-weight: 700;
	font-size: 14px;
	height: 36px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	border: none;
	-o-transition: all 0.218s;
	-moz-transition: all 0.218s;
	-webkit-transition: all 0.218s;
	transition: all 0.218s;
}

.btn.btn-signin:hover, .btn.btn-signin:active, .btn.btn-signin:focus {
	background-color: rgb(0, 141, 217);
}

.forgot-password {
	color: rgb(2, 75, 191);
}

.forgot-password:hover, .forgot-password:active, .forgot-password:focus
	{
	color: rgb(132, 138, 148);
}

.checkbox1{
margin-left:5px;
}

</style>
<meta charset="UTF-8">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<script type="text/javascript">
	//由<body>的onLoad事件處理函數觸發此函數
	function setFocusToUserId() {
		document.forms[0].userId.focus(); // 將游標放在userId欄位內
	}
</script>
<script src='https://www.google.com/recaptcha/api.js'></script>

</head>

<body onLoad="setFocusToUserId()">
	<!-- 下列敘述設定變數funcName的值為LOG，top.jsp 會用到此變數 -->
	<c:set var="funcName" value="LOG" scope="session" />
	<c:set var="msg" value="登入" />
	<c:if test="${ ! empty sessionScope.timeOut }">
		<!-- 表示使用逾時，重新登入 -->
		<c:set var="msg"
			value="<font color='red'>${sessionScope.timeOut}</font>" />
	</c:if>

	<!-- 引入共同的頁首 -->
	<%-- <jsp:include page="/fragment/top.jsp" /> --%>

	<div class="container">
		<div class="card card-container">
			<!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
			<img id="profile-img" class="profile-img-card"
				src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
			<p id="profile-name" class="profile-name-card"></p>

			<div align='center'>
				<h3>會員登入</h3>
			</div>

			<form:form method="POST" modelAttribute="loginBean"
				onsubmit="return submitUserForm();">

				<span id="reauth-email" class="reauth-email"></span>




				<form:input path='userId' placeholder="請輸入Email@" id="inputEmail"
					class="form-control" />

				<form:errors path="userId" cssClass="error" id="inputEmail"
					class="form-control" />

				<br>


				<form:input path="password" placeholder="請輸入密碼" id="inputPassword"
					class="form-control" />
				<form:errors path='password' cssClass="error" />
				<br>


				<div id="remember1" class="checkbox1">

					<form:checkbox path="rememberMe" />  記住密碼
				</div>
				<br>

				<!--我不是機器人 -->
				<div class="g-recaptcha"
					data-sitekey='6LesdbQZAAAAALBLl5IawsTcfUAxq1oay9feSsQJ'></div>
				<div id='g-recaptcha-error'></div>
                <br>
                
				<button class="btn btn-lg btn-primary btn-block btn-signin1"
					type='submit'>登入</button>
                <br>
<!-- 				<hr> -->

				<button class="btn btn-lg btn-primary btn-block btn-signin"
					type='submit'>使用Gmail登入</button><br>
				<div>  
            <input type="button"  class="btn btn-light" value="忘記密碼" onclick="location.href='forget'" style="width:100px;">
			<input type="button"  class="btn btn-light" value="首頁" onclick="location.href='<c:url value='/'/>'" style="width:91px;">
			<input type="button" class="btn btn-light" value="註冊會員" onclick="location.href='mem'" style="width:100px;">
			</div>
			</form:form>
		
			</div>
		</div>
	</div>
	<script>
		function submitUserForm() {
			var response = grecaptcha.getResponse();
			if (response.length == 0) {
				document.getElementById('g-recaptcha-error').innerHTML = '<span style="color:red;">請點選我不是機器人</span>';
				return false;
			}
			return true;
		}
		function verifyCaptcha() {
			document.getElementById('g-recaptcha-error').innerHTML = '';
		}}
	</script>


</body>
</html>