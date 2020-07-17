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
		<form:form method='POST' modelAttribute="aplcBean">
			<fieldset class="fieldset-auto-width">
				<legend>包場租借</legend>
				<table>
					<tr>
						<td align='right'>活動類型：<br>&nbsp;
						</td>
						<td><form:select id='ActClass' path='actClassId'>
								<form:option value="-1" label="請選擇" />
								<form:options items="${actClassList}" />
							</form:select><br>&nbsp;</td>
					</tr>
					<tr>
						<td align='right'>日期：<br>&nbsp;
						</td>
						<td><form:select id='SelectDate' path="date">
								<form:option value="-1" label="請選擇" />
								<form:options items="${orderableDate}" />
							</form:select><br>&nbsp;<br>&nbsp;</td>
					</tr>
					<tr>
						<td align='right'>時段：<br>&nbsp;
						</td>
						<td><form:select id='SelectTime' path="time">
								<form:option value="-1" label="請選擇" />
							</form:select><br>&nbsp;</td>
					</tr>
					<tr>
						<td align='right'>備註事項：<br>&nbsp;
						</td>
						<td><form:textarea path="intro" /><br>&nbsp;
<%-- 						<form:errors path="email" cssClass="error" /> --%>
						</td>
					</tr>
					
					<tr>
						<td colspan='2' align='center'><input type='submit'
							value='提交'></td>
					</tr>
				</table>
			</fieldset>
		</form:form>
		<br> <a href="<c:url value='/' />">回首頁</a>
	</div>
	<script>
		/*//放入actClassBean的選項
		var selectElement = document.getElementById('ActClass');  // 取出select標籤
		var xhr = new XMLHttpRequest();        	// 讀取actClassBean的資料
		var actClassData = [];						
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var actClasses = JSON.parse(xhr.responseText);
				for (var i = 0; i < actClasses.length; i++) {
					
					var actClass = [ actClasses[i].name, actClasses[i].actClassId ];
					
					actClassData.push(actClass);
				}
		        
				for (var i = 0; i < actClassData.length; i++) {
					var option = new Option(actClassData[i][0], "" + actClassData[i][1]);
					selectElement.options[selectElement.options.length] = option;
				}
			}
		}
		xhr.open("GET", "<c:url value='/allActClassBean' />", true);
		xhr.send();*/


		// 定義select標籤的change事件處理函數
		var selectElement = document.getElementById('SelectDate');
		var selectElementTime = document.getElementById('SelectTime');
		var xhr = new XMLHttpRequest();
		
		selectElement.onchange = function() {
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var orderTimes = JSON.parse(xhr.responseText);
					var orderTimeData = [];
					for (var i = 0; i < orderTimes.length; i++) {
						var orderTime = orderTimes[i];
						orderTimeData.push(orderTime);
					}
					selectElementTime.options.length= 1;//把option清空
					for (var i = 0; i < orderTimeData.length; i++) {
						var option = new Option(orderTimeData[i], ""
								+ orderTimeData[i]);
						selectElementTime.options[selectElementTime.options.length] = option;
					}
				}
			}
			//從selectElement.options[selectElement.selectedIndex].value中抓取日期
			var selectDate = selectElement.options[selectElement.selectedIndex].value;
			// 定義open方法
			xhr.open("GET", "<c:url value='/getOrderableTime' />"
					+ "?selectDate=" + selectDate, true);
			// 送出請求
			xhr.send();

		}
	</script>
</body>
</html>