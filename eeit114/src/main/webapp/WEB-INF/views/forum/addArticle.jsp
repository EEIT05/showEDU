<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FORM Text</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<style>
.dark-matter {
    margin-left: auto;
    margin-right: auto;
    max-width: 500px;
    background: #555;
    padding: 20px 30px 20px 30px;
    font: 12px "Helvetica Neue", Helvetica, Arial, sans-serif;
    color: #D3D3D3;
    text-shadow: 1px 1px 1px #444;
    border: none;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
}
.dark-matter h1 {
    padding: 0px 0px 10px 40px;
    display: block;
    border-bottom: 1px solid #444;
    margin: -10px -30px 30px -30px;
}
.dark-matter h1>span {
    display: block;
    font-size: 11px;
}
.dark-matter label {
    display: block;
    margin: 0px 0px 5px;
}
.dark-matter label>span {
    float: left;
    width: 20%;
    text-align: right;
    padding-right: 10px;
    margin-top: 10px;
    font-weight: bold;
}
.dark-matter input[type="text"], .dark-matter input[type="email"], .dark-matter textarea, .dark-matter select {
    border: none;
    color: #525252;
    height: 25px;
    line-height:15px;
    margin-bottom: 16px;
    margin-right: 6px;
    margin-top: 2px;
    outline: 0 none;
    padding: 5px 0px 5px 5px;
    width: 70%;
    border-radius: 2px;
    -webkit-border-radius: 2px;
    -moz-border-radius: 2px;
    -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
    background: #DFDFDF;
}
.dark-matter select {
/*     background: #DFDFDF url('down-arrow.png') no-repeat right; */
/*     background: #DFDFDF url('down-arrow.png') no-repeat right; */
    appearance:none;
    -webkit-appearance:none;
    -moz-appearance: none;
    text-indent: 0.01px;
    text-overflow: '';
    width: 70%;
    height: 35px;
    color: #525252;
    line-height: 25px;
}
.dark-matter textarea{
    height:100px;
    padding: 5px 0px 0px 5px;
    width: 70%;
}
.dark-matter .button {
    background: #FFCC02;
    border: none;
    padding: 10px 25px 10px 25px;
    color: #585858;
    border-radius: 4px;
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    text-shadow: 1px 1px 1px #FFE477;
    font-weight: bold;
    box-shadow: 1px 1px 1px #3D3D3D;
    -webkit-box-shadow:1px 1px 1px #3D3D3D;
    -moz-box-shadow:1px 1px 1px #3D3D3D;
}
.dark-matter .button:hover {
    color: #333;
    background-color: #EBEBEB;
}
</style>
</head>
<body>

	<form:form action="" method="POST"  modelAttribute="articleBean" 
	class="dark-matter form-horizontal" enctype="multipart/form-data">
		<fieldset>
		<h1>
			文章新增
		</h1>
		<label> <span>標題:</span> <form:input path="title" type="text"
			 placeholder="Title" />
		</label> 
		<label> <span>內容 :</span> <form:textarea path="content"
				 placeholder="content"></form:textarea>
		</label> 
		<label> <span>文章類型 :</span>
		<form:select path="typeId">
				<form:option value="-1" label="請挑選" />
				<form:options items="${artTypeMap}" />
		</form:select>
		</label> 
		<label> <span>文章圖片:</span> <form:input path="image" type="file"
			class='form:input-large' />
		</label> 
		<label> <span>&nbsp;&nbsp;</span> <input type="submit"
			class="button" value="送出" />
		</label>
		</fieldset>
	</form:form>
</body>
</html>