<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车列表</title>
<style type="text/css">
div {
	margin: 20px;
	border: solid 2px gray;
	width: 150px;
	height: 150px;
	text-align: center;
}

li {
	margin: 10px;
}

#buy {
	background: gray;
	display: inline-block;
	background-position: 0 -902px;
	margin-left: 30px;
	height: 36px;
	width: 146px;
}

#buy:hover {
	background: red;
	display: inline-block;
	background-position: 0 -938px;
	margin-left: 30px;
	height: 36px;
	width: 146px;
}
</style>
</head>
<body>
	<h1 align="center">购物车</h1>
<c:choose>
<c:when test="${empty sessionScope.session_cart or fn:length(sessionScope.session_cart.cartItems) eq 0}">
<h1 align="center">购物车为空,请添加书籍</h1>
</c:when>
<c:otherwise>
	<table border="1" width="100%" cellspacing="0" background="black">
		<tr>
			<td colspan="7" align="right"
				style="font-size: 15px; font-weight: 900">
				<a href="<c:url value='/CartServlet?method=clear'/>">清空购物车</a>
			</td>
		</tr>
		<tr>
			<th>图书</th>
			<th>书名</th>
			<th>作者</th>
			<th>单价</th>
			<th>数量</th>
			<th>小计</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${sessionScope.session_cart.cartItems}" var="cartItem">
			<tr>
				<td><img style="width: 150px;height: 150px;" src="<c:url value='/${cartItem.book.image}'/>" /></td>
				<td>${cartItem.book.bname}</td>
				<td>${cartItem.book.author}</td>
				<td>${cartItem.book.price}</td>
				<td>${cartItem.count}</td>
				<td>${cartItem.subTotal}</td>
				<td><a href="<c:url value='/CartServlet?method=remove&bid=${cartItem.book.bid}'/>">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7" align="center"
				style="font-size: 15px; font-weight: 900">
				合计： ${sessionScope.session_cart.total}元
				</td>
		</tr>
		<tr>
			<td colspan="7" align="right"
				style="font-size: 15px; font-weight: 900">
				<a href="<c:url value='/OrderServlet?method=add'/>">去付款</a>
				</td>
		</tr>
	</table>
</c:otherwise>
</c:choose>
</body>
</html>