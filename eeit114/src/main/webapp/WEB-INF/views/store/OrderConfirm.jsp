<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
 <%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<!doctype html>
<html lang="en">
  <head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <meta charset="utf-8">

   
    <title>Checkout example · Bootstrap</title>

<!--     <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/checkout/"> -->

    <!-- Bootstrap core CSS -->
<!-- <link href="../assets/dist/css/bootstrap.css" rel="stylesheet"> -->

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
       .container { 
  max-width: 960px; 
 } 

 .lh-condensed { line-height: 1.25; } 
 
  
    </style>
    <!-- Custom styles for this template -->
    <link href="form-validation.css" rel="stylesheet">
  </head>
  <body class="bg-light">
  <c:choose>
<c:when test="${ShoppingCart.buyTotal > 0}">
    <c:set var="buyTotal" value="${ShoppingCart.buyTotal}"/>
</c:when>
	<c:otherwise>
      <c:set var="buyTotal" value="0"/>
	</c:otherwise>
</c:choose>
    <div class="container">
 

  <div class="row">
    <div class="col-md-4 order-md-2 mb-4">
      <h4 class="d-flex justify-content-between align-items-center mb-3">
        <span class="text-muted">購買內容</span>
        <span id='items' class="badge badge-secondary badge-pill"></span>
      </h4>
      <ul class="list-group mb-3">
      
      <c:forEach varStatus='vs' var='anEntry' items='${ShoppingCart.content}'>
      
        <li class="list-group-item d-flex justify-content-between lh-condensed">
          <div>
            <h6 name='name' class="my-0">${anEntry.value.peripheralProductBean.name}</h6>
          </div>
          <span class="text-muted"><fmt:formatNumber value="${anEntry.value.peripheralProductBean.price * anEntry.value.buyCount}" pattern="#,###,###" /> </span>
        </li>
        </c:forEach>
        <li class="list-group-item d-flex justify-content-between ">
          <span>總金額</span>
          <strong>NT$<fmt:formatNumber value="${buyTotal}" pattern="#,###,###" />元</strong>
        </li>
      </ul>
    </div>
    <div class="col-md-8 order-md-1">
      <h4 class="mb-3">訂單</h4>
      <form class="needs-validation" novalidate>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="firstName">訂購人</label>
            <input type="text" class="form-control" id="firstName" value="${memberBean.name}" required disabled>
            <div class="invalid-feedback">
              Valid first name is required.
            </div>
          </div>   
        </div>
        <div class="mb-3">
          <label for="email">訂購人信箱 <span class="text-muted"></span></label>
          <input type="email" class="form-control" id="email" value="${memberBean.account}" >
          <div class="invalid-feedback">
            Please enter a valid email address for shipping updates.
          </div>
        </div>
        <div class="mb-3">
          <label for="address">訂購人地址</label>
          <input type="text" class="form-control" id="address" value="${memberBean.address}" >
        </div>
        
        <div class="mb-3">
          <label for="ShippingName">收件人</label>
          <div class="input-group">
            <input type="text" class="form-control" id="ShippingName" name='ShippingName' required>
            <div class="invalid-feedback" style="width: 100%;">
            	  收件人姓名不得空白
            </div>
          </div>
        </div>

        <div class="mb-3">
          <label for="ShippingPhone">收件人電話<span class="text-muted"></span></label>
          <input type="text" class="form-control" id="ShippingPhone" name='ShippingPhone' required>
           <div class="invalid-feedback">
           		 收件人電話不得空白
          </div>
        </div>
        <div class="mb-3">
          <label for="ShippingAddress">收件人地址<span class="text-muted"></span></label>
          <input type="text" class="form-control" id="ShippingAddress" name='ShippingAddress' required>
           <div class="invalid-feedback">
           		 收件人地址不得空白
          </div>
        </div>

        
        <hr class="mb-4">
        
        <h4 class="mb-3">付款方式</h4>

        <div class="d-block my-3">
          <div class="custom-control custom-radio">
            <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked required>
            <label class="custom-control-label" for="credit">信用卡</label>
          </div>
        
        </div>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="cc-number">信用卡號</label>
            <input type="text" class="form-control" id="cc-number" placeholder="" required>
            <div class="invalid-feedback">
             	 信用卡號碼不得空白
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 mb-3">
            <label for="cc-expiration">有效日期</label>
            <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
            <div class="invalid-feedback">
              	請輸入信用卡有效期
            </div>
          </div>
          <div class="col-md-3 mb-3">
            <label for="cc-cvv">CVV</label>
            <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
            <div class="invalid-feedback">
             	 請輸入卡片末三碼
            </div>
          </div>
        </div>
        <hr class="mb-4">
        <input type="hidden" name="finalDecision"  value="">
        <button style="width: 5cm;height: 1cm;display:inline-block;margin:0px" class="btn btn-danger btn-lg btn-block" type="button" name='CancelBtn' onclick='cancelOrder()'>取消訂單</button>
        <button style="width: 5cm;height: 1cm;display:inline-block;margin:0px" class="btn btn-primary btn-lg btn-block" type="submit" name='OrderBtn' >確認訂單</button>    
<!--         onclick='reconfirmOrder()' -->
      </form>
    </div>
  </div>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script><script src="../assets/dist/js/bootstrap.bundle.js"></script>
        <script src="form-validation.js">
 </script>
 <script>
 (function () {
     'use strict'

     window.addEventListener('load', function () {
       // Fetch all the forms we want to apply custom Bootstrap validation styles to
       var forms = document.getElementsByClassName('needs-validation')

       // Loop over them and prevent submission
       Array.prototype.filter.call(forms, function (form) {
         form.addEventListener('submit', function (event) {
           if (form.checkValidity() === false) {
             event.preventDefault()
             event.stopPropagation()
           }else {
           if (confirm("確定送出此份訂單 ? ")) {
             document.forms[0].finalDecision.value = "ORDER";
             document.forms[0].action = "<c:url value='ProcessOrder' />";
             document.forms[0].method = "POST";
             document.forms[0].submit();
             return;
           } else {
             return;
           }
         }
           form.classList.add('was-validated')
         }, false)

       })
     }, false)
   }())
 </script>
 <script>
 function cancelOrder() {
		if (confirm("確定取消此份訂單 ? ") ) {
			
			document.forms[0].finalDecision.value = "CANCEL";
			document.forms[0].action="<c:url value='removeShoppingCart' />";
			document.forms[0].method="GET";
			document.forms[0].submit();
			return;
		} else {
			return;
		}
	}	
 </script>
 <script>
 var item = document.getElementsByName("name").length;
 var items = document.getElementById("items");
 items.innerHTML = "<span><strong>"+item+"</strong></span>"
 </script>
</body>
</html>
