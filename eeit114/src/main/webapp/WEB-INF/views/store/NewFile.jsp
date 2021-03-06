<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的訂單</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.text-primary {
    color: #4e73df;
}
.font-weight-bold {
    font-weight: 700;
}
.m-0 {
    margin: 0;
}
h6, .h6 {
    font-size: 1rem;
}
.card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 1px solid #e3e6f0;
    border-radius: 0.35rem;
    margin:20px auto;
}
.card-header {
    padding: 0.75rem 1.25rem;
    margin-bottom: 0;
    background-color: #f8f9fc;
    border-bottom: 1px solid #e3e6f0;
}
.shadow {
    box-shadow: 0 0.15rem 1.75rem 0 rgba(58, 59, 69, 0.15) !important;
}
.display-flex {
    display: flex;
}
.align-center {
    align-items: center;
}
.ml-auto {
    margin-left: auto !important;
}
.form-control {
    display: block;
    width: 100%;
    height: calc(1.5em + 0.75rem + 2px);
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #6e707e;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #d1d3e2;
    border-radius: 0.35rem;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.btn {
    display: inline-block;
    font-weight: 400;
    color: #858796;
    text-align: center;
    vertical-align: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-color: transparent;
    border: 1px solid transparent;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    line-height: 1.5;
    border-radius: 0.35rem;
    transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.btn-primary {
    color: #fff;
    background-color: #4e73df;
    border-color: #4e73df;
}
.card-body {
    flex: 1 1 auto;
    padding: 1.25rem;
}
.table-responsive {
    display: block;
    width: 100%;
    overflow-x: auto;
}
.table-responsive > .table-bordered {
    border: 0;
}
.table-bordered th, .table-bordered td {
    border: 1px solid #e3e6f0;
}
.table thead th {
    vertical-align: middle;
    border-bottom: 2px solid #e3e6f0;
}
.table-bordered thead th, .table-bordered thead td {
    border-bottom-width: 2px;
}
.table-bordered thead th {
    background: #5d71e4;
    color: #fff;
}
</style>
</head>
<body>
<div class="container">
	<div class="card shadow mb-4">
            <div class="card-header py-3">
              <div class="display-flex align-center">
                <h6 class="m-0 font-weight-bold text-primary">我的訂單</h6>
              </div>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                
                
                <table class="table table-bordered" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>商品編號</th>
                      <th>商品名稱</th>
                      <th>購買數量</th>
                      <th>金額</th>
                    </tr>
                  </thead>
                  <c:forEach var='pob' items='${pob.items}'>
                  <tbody>
                    <tr>
                      <td>${pob.productId}</td>
                      <td>${pob.peripheralProductBean.name}</td>
                      <td>${pob.buyCount}</td>
                      <td>${pob.peripheralProductBean.price}元</td>
                      
<%--                       <td>${order.payStatus}</td> --%>
<%--                       <td>${order.sendStatus}</td> --%>
                      
                    </tr>                   
                  </tbody>
                  </c:forEach>
                </table>
              </div>
            </div>
          </div>
</div>
</body>
</html>