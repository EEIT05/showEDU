<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<style type="text/css">
   span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}
</style>
<meta charset="UTF-8">
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/style.css'  type="text/css" />
</head>
<body>
<fieldset>
	<legend >更新管理員資料</legend>
	<form:form method="POST" modelAttribute="administrator" enctype='multipart/form-data'>
	<Table align="center">
	<c:if test='${administrator.admId == null}'>
		<br>
	 	<tr>
	      <td>帳號：<br>&nbsp;</td>
	   	  <td width='360'><form:input path='adaccount'/><br>&nbsp;
	   	   <form:errors path="adaccount" cssClass="error"/>
	   	  </td>
	   </tr>
    </c:if>	   
    	<c:if test='${administrator.admId != null}'>
	 	<tr>
	      <td>帳號：<br>&nbsp;</td>
	   	  <td><form:hidden path='adaccount'/>
	   	  	${administrator.adaccount}<br>&nbsp;
	   	  </td>
	   </tr>
    </c:if>	
    
	   <tr>
	      <td>姓名：<br>&nbsp;</td>
		  <td  width='360'><form:input path='adname' /><br>&nbsp;	
		      <form:errors path='adname' cssClass="error"/>
		  </td>
		  </tr>
		
		  <tr>
		   <td>密碼：<br>&nbsp;</td>
	   	  <td>
	      	<form:input path="adpswd"/><br>&nbsp;	
		      <form:errors path='adpswd' cssClass="error"/>
		  </td>
	   </tr>	 
	   	
	  	   

	   <tr>
	    <td colspan='4' align='center'><br>&nbsp;
	      <input type='submit'>
        </td>
	   </tr>
	</Table>
		 
	</form:form>
	
</fieldset>
<br>
<a href='administrators' >回前頁</a> 
</body>
</html>