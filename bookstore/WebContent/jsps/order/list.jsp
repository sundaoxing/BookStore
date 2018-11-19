<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
div {
	border: solid 2px gray;
	width: 75px;
	height: 75px;
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
<c:choose>
<c:when test="${empty orderList or fn:length(orderList) eq 0}">
<h1 align="center">暂无订单,请购买书籍</h1>
</c:when>
<c:otherwise>
<table border="1" width="100%" cellspacing="0" background="black">
<c:forEach items="${orderList}" var="order">
		<tr bgcolor="gray" bordercolor="gray">
			<td colspan="6">订单编号：${order.oid} 成交时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.ordertime}"/> 金额：${order.total}元
			<c:choose>
			<c:when test="${order.state eq 1}">
			<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid}'/>">付款</a>
			</c:when>
			<c:when test="${order.state eq 2}"><span style="color: red">等待卖家发货</span></c:when>
			<c:when test="${order.state eq 3}">
			<a href="<c:url value='/OrderServlet?method=confirm&oid=${order.oid}'/>">确认收货</a>
			</c:when>
			<c:when test="${order.state eq 4}">交易成功</c:when>
			</c:choose>
			</td>
		</tr>
			<c:forEach items="${order.orderItemList}" var="orderItem">
				<tr bordercolor="gray" align="center">
					<td width="15%"><img style="width: 150px;height: 150px;" src="<c:url value='/${orderItem.book.image}'/>" /></td>
					<td>书名：${orderItem.book.bname}</td>
					<td>作者：${orderItem.book.author}</td>
					<td>单价：${orderItem.book.price}</td>
					<td>数量：${orderItem.count}</td>
					<td>小计：${orderItem.subtotal}</td>
				</tr>
			</c:forEach>
	</c:forEach>
	</table>
	</c:otherwise>
</c:choose>
</body>
</html>