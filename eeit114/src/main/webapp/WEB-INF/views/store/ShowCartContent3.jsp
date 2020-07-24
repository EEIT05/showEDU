<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
  <%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<!-- 	top.jsp bootstrap -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>	
<jsp:include page="/WEB-INF/views/top.jsp" />
<style>
body{margin-top:20px;
    background:#eee;
}
h3 {
    font-size: 16px;
}
.text-navy {
    color: #1ab394;
}
.cart-product-imitation {
  text-align: center;
  padding-top: 30px;
  height: 80px;
  width: 80px;
  background-color: #f8f8f9;
}
.product-imitation.xl {
  padding: 120px 0;
}
.product-desc {
  padding: 20px;
  position: relative;
}
.ecommerce .tag-list {
  padding: 0;
}
.ecommerce .fa-star {
  color: #d1dade;
}
.ecommerce .fa-star.active {
  color: #f8ac59;
}
.ecommerce .note-editor {
  border: 1px solid #e7eaec;
}
table.shoping-cart-table {
  margin-bottom: 0;
}
table.shoping-cart-table tr td {
  border: none;
  text-align: right;
}
table.shoping-cart-table tr td.desc,
table.shoping-cart-table tr td:first-child {
  text-align: left;
}
table.shoping-cart-table tr td:last-child {
  width: 80px;
}
.ibox {
  clear: both;
  margin-bottom: 25px;
  margin-top: 0;
  padding: 0;
}
.ibox.collapsed .ibox-content {
  display: none;
}
.ibox:after,
.ibox:before {
  display: table;
}
.ibox-title {
  -moz-border-bottom-colors: none;
  -moz-border-left-colors: none;
  -moz-border-right-colors: none;
  -moz-border-top-colors: none;
  background-color: #ffffff;
  border-color: #e7eaec;
  border-image: none;
  border-style: solid solid none;
  border-width: 3px 0 0;
  color: inherit;
  margin-bottom: 0;
  padding: 14px 15px 7px;
  min-height: 48px;
}
.ibox-content {
  background-color: #ffffff;
  color: inherit;
  padding: 15px 20px 20px 20px;
  border-color: #e7eaec;
  border-image: none;
  border-style: solid solid none;
  border-width: 1px 0;
}
.ibox-footer {
  color: inherit;
  border-top: 1px solid #e7eaec;
  font-size: 90%;
  background: #ffffff;
  padding: 10px 15px;
}
.table td, .table th{
	padding: .2rem;
}
.container{
margin-top:100px;
}
</style>
</head>
<body>

<c:choose>
<c:when test="${ShoppingCart.buyTotal > 0}">
    <c:set var="buyTotal" value="${ShoppingCart.buyTotal}"/>
</c:when>
	<c:otherwise>
      <c:set var="buyTotal" value="0"/>
	</c:otherwise>
</c:choose>
<div class="container">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-md-9">
            <div class="ibox">
                <div class="ibox-title">
                    <span id='items' class="pull-right">(<strong></strong>) items</span>
                    <h5>購買清單</h5>
                </div>
                 <c:forEach varStatus='vs' var='anEntry' items='${ShoppingCart.content}'>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table shoping-cart-table">
                            <tbody>
                            <tr>
                                <td width="90">
                                    <div>
                                    <img class="img-responsive" src="<c:url value='/getProductPicture/${anEntry.value.productId}'/>" width="90">
                                    </div>
                                </td>
                                <td class="desc">
                                    <h3>
                                    <a name='name' href="<spring:url value='/Product?id=${anEntry.value.productId}' />" class="text-navy">
                                        ${anEntry.value.peripheralProductBean.name}
                                    </a>
                                    </h3>
                                    <p class="small">
                                        
                                    </p>
                                    <dl class="small m-b-none">
                                        <dd>${anEntry.value.peripheralProductBean.detail}</dd>
                                    </dl>

                                    <div class="m-t-sm">
                                        <a href="#" class="text-muted"><i class="fa fa-gift"></i> Add gift package</a>
                                        |
                                        <a href="#" class="text-muted"><i class="fa fa-trash"></i> Remove item</a>
                                    </div>
                                </td>

                     
                                <td width="65">
                                   
                                    <input type="text" class="form-control" value="${anEntry.value.buyCount}">
                                </td>
                                <td>
                                    <h5>
                                        <fmt:formatNumber value="${anEntry.value.peripheralProductBean.price * anEntry.value.buyCount}" pattern="#,###,###" />元
                                    </h5>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
                </c:forEach>

                <div class="ibox-content">
                	<form>
<!--                     <button class="btn btn-primary pull-right"><i class="fa fa fa-shopping-cart"></i> 確認訂單</button> -->
                    <a class="btn btn-primary pull-right" href="<c:url value='checkout' />" onClick="return Checkout(${buyTotal});">
                    <i class="fa fa fa-shopping-cart"></i>確認訂單</a>
					<a class="btn btn-white" href='showproduct'><i class="fa fa-arrow-left"></i>繼續購買</a>
					
					<input type="hidden" name="finalDecision"  value="">
					<button  class="btn btn-danger pull-right" type="button" name='CancelBtn' onclick='cancelOrder()'>取消訂單</button>
					</form>
                </div>
            </div>

        </div>
        <div class="col-md-3">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>購買總額</h5>
                </div>
                <div class="ibox-content">
                    <span>
                        	Total
                    </span>
                    <h2 class="font-bold">
                       NT$ ${buyTotal}
                    </h2>

                    <hr>
                    <div class="m-t-sm">
                        <div class="btn-group">
                        <a href="#" class="btn btn-primary btn-sm"><i class="fa fa-shopping-cart"></i> 確認訂單</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="ibox">
                <div class="ibox-title">
                    <h5>聯絡我們</h5>
                </div>
                <div class="ibox-content text-center">
                    <h3><i class="fa fa-phone"></i> +43 100 783 001</h3>
                    <span class="small">
                       		 如有任何疑問，請與我們聯繫。
                       		 24小時免付費電話
                    </span>
                </div>
            </div>

<!--             <div class="ibox"> -->
<!--                 <div class="ibox-content"> -->

<!--                     <p class="font-bold"> -->
<!--                     Other products you may be interested -->
<!--                     </p> -->
<!--                     <hr> -->
<!--                     <div> -->
<!--                         <a href="#" class="product-name"> Product 1</a> -->
<!--                         <div class="small m-t-xs"> -->
<!--                             Many desktop publishing packages and web page editors now. -->
<!--                         </div> -->
<!--                         <div class="m-t text-righ"> -->

<!--                             <a href="#" class="btn btn-xs btn-outline btn-primary">Info <i class="fa fa-long-arrow-right"></i> </a> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <hr> -->
<!--                     <div> -->
<!--                         <a href="#" class="product-name"> Product 2</a> -->
<!--                         <div class="small m-t-xs"> -->
<!--                             Many desktop publishing packages and web page editors now. -->
<!--                         </div> -->
<!--                         <div class="m-t text-righ"> -->

<!--                             <a href="#" class="btn btn-xs btn-outline btn-primary">Info <i class="fa fa-long-arrow-right"></i> </a> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
        </div>
    </div>
</div>
</div>
<script>
  
  var item = document.getElementsByName("name").length;
  var items = document.getElementById("items");
  items.innerHTML = "<span><strong>"+"商品數: "+item+"</strong></span>"
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
</body>
</html>