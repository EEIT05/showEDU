<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Website Font style -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<link rel="stylesheet" href="style.css">
<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>
	
<!-- 	以下新增 -->
		<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
		<script>
		$(document).ready(function() {
			$('.one').click(function() {
				$('#name').val("炭治郎");
				$('#phone').val("0963158742");
				$('#address').val("台中市美村路55號");
				$('#account').val("example@gmail.com");
				$('#password').val("ASdf688");
				$('#confirm').val("ASdf688");
		});
	
		});
		
		
	</script>
	
<title>註冊會員</title>
<style type="text/css">
span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}

 body, html { 
	height: 120%; 
	background-repeat: no-repeat; 
 	background-image: linear-gradient(rgb(186, 240, 255), rgb(51, 65, 156), 
 		rgb(25, 0, 71)); 
} 


#playground-container {
    height: 500px;
    overflow: hidden !important;
    -webkit-overflow-scrolling: touch;
}
/* body, html{ */
/*      height: 100%; */
/*  	background-repeat: no-repeat; */
/*  	background:url(https://i.ytimg.com/vi/4kfXjatgeEU/maxresdefault.jpg); */
/*  	font-family: 'Oxygen', sans-serif; */
/* 	    background-size: cover; */
/* } */

.main{
 	margin:10px 15px;
}

h1.title { 
	font-size: 50px;
	font-family: 'Passion One', cursive; 
	font-weight: 400; 
}

hr{
	width: 10%;
	color:#000357;
}

.form-group{
	margin-bottom: 15px;
}

label{
	margin-bottom: 15px;
}

input,
input::-webkit-input-placeholder {
    font-size: 11px;
    padding-top: 2px;
}

.main-login{
 	background-color: #a1a4ff;
    /* shadows and rounded borders */
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

}
.form-control {
    height: auto!important;
padding: 8px 12px !important;
}
.input-group {
    -webkit-box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.21)!important;
    -moz-box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.21)!important;
    box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.21)!important;
}
/*  #button {  */
/*     border: 1px solid #ccc;  */
/*      margin-top: 28px;  */
/*      padding: 6px 12px;  */
/*      color: #0219b3;  */
/*      text-shadow: 0 1px #fff;  */
/*      cursor: pointer; */
/*      -moz-border-radius: 3px 3px;  */
/*     -webkit-border-radius: 3px 3px;  */
/*      border-radius: 3px 3px; */ */
/*      -moz-box-shadow: 0 1px #fff inset, 0 1px #ddd; */ */
/*     -webkit-box-shadow: 0 1px #fff inset, 0 1px #ddd; */ */
/*      box-shadow: 0 1px #fff inset, 0 1px #ddd; */ */
/*     background: #f5f5f5; */ */
/*      background: -moz-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%); */ */
/*      background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f5f5f5), color-stop(100%, #eeeeee)); */ */
/*      background: -webkit-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%); */ */
/*     background: -o-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%); */ */
/*      background: -ms-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%); */ */
/*      background: linear-gradient(top, #f5f5f5 0%, #eeeeee 100%); */ */
/*     filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f5f5f5', endColorstr='#eeeeee', GradientType=0); */ */
/* }  */

 .btn.btn-signin { 
/*  	background-color: #4d90fe;  */
	background-color: rgb(35, 191, 0);  
/*  	 background-color: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33)); */
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


/* 整個表單 */
.main-center{
 	margin-top: 30px;
 	margin: 0 auto;
 	max-width: 400px;
    padding: 10px 40px;
	background:#e6f8ff;
	    color: #000357;
    text-shadow: none;
	-webkit-box-shadow: 0px 3px 5px 0px rgba(0,0,0,0.31);
-moz-box-shadow: 0px 3px 5px 0px rgba(0,0,0,0.31);
box-shadow: 0px 3px 5px 0px rgba(0,0,0,0.31);

}
span.input-group-addon i {
    color: #009edf;
    font-size: 17px;
    
}

.login-button{
	margin-top: 50px;

}


/* .login-register{ */
/* 	font-size: 11px; */
/* 	text-align: center; */

/* } */




</style>
<meta charset="UTF-8">
<!-- <link rel='stylesheet' -->
<%-- 	href='${pageContext.request.contextPath}/css/style.css' type="text/css" /> --%>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>



</head>
<body>

	<div class="container">
		<div class="row main">
			<div class="main-login main-center">

				<h3>註冊會員</h3>
				<form:form method="POST" modelAttribute="member"
					enctype='multipart/form-data'>

					<div class="form-group">
						<label for="name" class="cols-sm-2 control-label">Your Name</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span>
								<form:input path='name' placeholder="請輸入姓名" class="form-control"
									name="name" id="name" />
								<form:errors path='name' cssClass="error" />
								
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="phone" class="cols-sm-2 control-label">Your Phone</label>
							
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-phone"
									aria-hidden="true"></i></span>
								<form:input path='phone' placeholder="請輸入電話"
									class="form-control" name="phone" id="phone" />
								<form:errors path='phone' cssClass="error" />
								<br>
							</div>
						</div>
					</div>

<div class="form-group">
						<label for="address" class="cols-sm-2 control-label">Your Address</label>
							
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-home"
									aria-hidden="true"></i></span>
					<form:input path="address" placeholder="請輸入地址" class="form-control" name="address" id="address" />
					<form:errors path='address' cssClass="error" />
					<br></div></div></div>

					<c:if test='${member.memberId == null}'>
					<div class="form-group">
						<label for="account" class="cols-sm-2 control-label">Your Account</label>
							
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-envelope"
									aria-hidden="true"></i></span>

						<form:input path='account' placeholder="請輸入帳號(example@gmail.com)" class="form-control" name="account" id="account"/>

						<form:errors path="account" cssClass="error" />
						<br></div></div></div>
					</c:if>
					<c:if test='${member.memberId != null}'>


						<form:hidden path='account' /> ${member.account}<br>
					</c:if>


					<div class="form-group">
						<label for="password" class="cols-sm-2 control-label">Your Password</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span>

								<form:input path="pswd" placeholder="請輸入密碼" class="form-control"
									name="password" id="password" />
								<form:errors path='pswd' cssClass="error" />
								<br>
							</div>
						</div>
					</div>


					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">Confirm
							Password</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
								<form:input path="pswd2" placeholder="請確認密碼"
									class="form-control" name="confirm" id="confirm" />
								<form:errors path='pswd2' cssClass="error" />
								<br>
							</div>
						</div>
					</div>

<div class="form-group">
						<label for="memImage" class="cols-sm-2 control-label">Your Photo</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-camera" aria-hidden="true"></i></span>
					<form:input path="memImage" type='file' class="form-control" name="memImage" id="memImage"/>
					<form:errors path="memImage" cssClass="error" />
					<br></div></div></div>

	<div class="form-group ">
					<input type='submit' value="送出" id="button" class="btn btn-primary btn-lg btn-block login-button">
					</div></form:form>
<input type='submit' value="一鍵輸入" id="button" class="one">
				

				<br> <a href="<c:url value='/'/> ">首頁</a>
			</div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	


</body>
</html>