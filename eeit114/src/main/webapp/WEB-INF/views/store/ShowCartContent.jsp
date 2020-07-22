<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://use.fontawesome.com/c560c025cf.js"></script>
<title>溝物清單</title>

<style>

.quantity {
    float: left;
    margin-right: 15px;
    background-color: #eee;
    position: relative;
    width: 80px;
    overflow: hidden
}

.quantity input {
    margin: 0;
    text-align: center;
    width: 15px;
    height: 15px;
    padding: 0;
    float: right;
    color: #000;
    font-size: 20px;
    border: 0;
    outline: 0;
    background-color: #F6F6F6
}

.quantity input.qty {
    position: relative;
    border: 0;
    width: 100%;
    height: 40px;
    padding: 10px 25px 10px 10px;
    text-align: center;
    font-weight: 400;
    font-size: 15px;
    border-radius: 0;
    background-clip: padding-box
}

.quantity .minus, .quantity .plus {
    line-height: 0;
    background-clip: padding-box;
    -webkit-border-radius: 0;
    -moz-border-radius: 0;
    border-radius: 0;
    -webkit-background-size: 6px 30px;
    -moz-background-size: 6px 30px;
    color: #bbb;
    font-size: 20px;
    position: absolute;
    height: 50%;
    border: 0;
    right: 0;
    padding: 0;
    width: 25px;
    z-index: 3
}

.quantity .minus:hover, .quantity .plus:hover {
    background-color: #dad8da
}

.quantity .minus {
    bottom: 0
}
.shopping-cart {
    margin-top: 20px;
}
.card-body {
	padding: 1.1rem;
}
.pull-right{
	margin-bottom:14px;
}
</style>
</head>
<body>
<c:choose>
<c:when test="${ShoppingCart.buyTotal > 0}">
<%-- 	<c:set var="subtotalMessage" value="金額小計:${ShoppingCart.buytotal} 元"/> --%>
    <c:set var="buyTotal" value="${ShoppingCart.buyTotal}"/>
</c:when>
	<c:otherwise>
<%-- 	<c:set var="subtotalMessage" value="金額小計:  0 元"/> --%>
      <c:set var="buyTotal" value="0"/>
	</c:otherwise>
</c:choose>
<div class="container">
   <div class="card shopping-cart">
            <div class="card-header bg-dark text-light">
                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                Shipping cart
                <a href="showproduct" class="btn btn-outline-info btn-sm pull-right">繼續購物</a>
                <div class="clearfix"></div>
            </div>
            <c:forEach varStatus='vs' var='anEntry' items='${ShoppingCart.content}'>
            <div class="card-body">
                    <!-- PRODUCT -->
                    <div class="row">
                        <div class="col-12 col-sm-12 col-md-2 text-center">
                                <img class="img-responsive" src="<c:url value='/getProductPicture/${anEntry.value.productId}'/>" alt="prewiew" width="120" height="80">
                        </div>
                        <div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                            <h4 class="product-name"><strong>${anEntry.value.peripheralProductBean.name}</strong></h4>

                        </div>
                        <div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                            <div class="col-3 col-sm-3 col-md-6 text-md-right" style="padding-top: 5px">
                                <h6><strong><fmt:formatNumber value="${anEntry.value.peripheralProductBean.price}" pattern="#,###,###" /><span class="text-muted"> 元 x</span></strong></h6>
                            </div>
                            <div class="col-4 col-sm-4 col-md-4">
                               
                            <td>
								<input type="number" class="form-control text-center" value="${anEntry.value.buyCount}">
							</td>
                               
                            </div>
                            <div class="col-2 col-sm-2 col-md-2 text-right">
                                <button type="button" class="btn btn-outline-danger btn-xs">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    
                    <hr>
                    
                    <!-- END PRODUCT -->    
            </div>
            </c:forEach>
            <div class="pull-right">
                    <a href="" class="btn btn-outline-secondary pull-right">
                        Update shopping cart
                    </a>
                </div>
            <div class="card-footer">
                <div class="coupon col-md-5 col-sm-5 no-padding-left pull-left">

                </div>
                <div class="pull-right" style="margin: 10px">
                    <a href="<c:url value='checkout' />" onClick="return Checkout(${buyTotal});" class="btn btn-success pull-right">結帳</a>
                    <div class="pull-right" style="margin: 5px">總金額:
                         <b><fmt:formatNumber value="${buyTotal}" pattern="#,###,###" />元</b>
                    </div>
                </div>
            </div>
        </div>
</div>
</body>
</html>