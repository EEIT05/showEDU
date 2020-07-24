<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/style.css' type="text/css" />
<style type="text/css">
span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}

.fieldset-auto-width {
	display: inline-block;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">

		<c:if test='${not empty check}'>
			<font color='red'>${check}</font>
			<c:remove var="check" />
		</c:if>
	</div>

	<span id="totalSecond">5</span>
	<span>秒後畫面自動跳轉</span>
	<a>


</body>
<script type="text/javascript">
	var second = totalSecond.innerText;
	setInterval("redirect()", 1000);
	function redirect() {
		totalSecond.innerText = --second;
		if (second <= 0)
			location.href = '../yourApplication';
	}
</script>
</html>