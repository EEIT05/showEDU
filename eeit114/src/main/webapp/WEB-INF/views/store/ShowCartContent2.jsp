<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>溝物清單</title>
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
	<table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:40%">商品名稱</th>
							<th style="width:10%">購買數量</th>
							<th style="width:15%">價格</th>
							<th style="width:22%" class="text-center">商品總額 </th>
							<th style="width:10%">動作</th>
						</tr>
					</thead>
					<c:forEach varStatus='vs' var='anEntry' items='${ShoppingCart.content}'>
					<tbody>
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs"><img src=<c:url value='/getProductPicture/${anEntry.value.productId}'/> alt="..." class="img-responsive"/></div>
									<div class="col-sm-10">
										<h4 class="nomargin">${anEntry.value.peripheralProductBean.name}</h4>
										
									</div>
								</div>
							</td>
							<td data-th="Quantity">
								<input type="number" class="form-control text-center" value="${anEntry.value.buyCount}">
							</td>
							<td data-th="Price"><fmt:formatNumber value="${anEntry.value.peripheralProductBean.price}" pattern="#,###,###" /></td>
							<td data-th="Subtotal" class="text-center" ><fmt:formatNumber value="${anEntry.value.peripheralProductBean.price * anEntry.value.buyCount}" pattern="#,###,###" />元</td>
							<td class="actions" data-th="">
								<button class="btn btn-info btn-sm"><i class="fa fa-pencil"></i></button>
								<button class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button>								
							</td>
						</tr>
					</tbody>
					</c:forEach>
					<tfoot>
						<tr>
							<td><a href="showproduct" class="btn btn-warning"><i class="fa fa-angle-left"></i> 繼續購物</a></td>
							<td colspan="2" class="hidden-xs"></td>
							<td class="hidden-xs text-center"><fmt:formatNumber value="${buyTotal}" pattern="#,###,###" />元</td>
							<td><a href="https://www.paypal.com/webapps/shoppingcart?mfid=1546373779156_cb91e3a2b2dc7&flowlogging_id=cb91e3a2b2dc7#/checkout/shoppingCart" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a></td>
						</tr>
					</tfoot>
				</table>
</div>
</body>
</html>