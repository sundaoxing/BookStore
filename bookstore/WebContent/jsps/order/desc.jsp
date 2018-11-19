<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

#pay {
	background: gray;
	display: inline-block;
	background-position: 0 -902px;
	margin-left: 30px;
	height: 36px;
	width: 146px;
}

#pay:hover {
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
	<h1 align="center">当前订单</h1>
	<c:choose>
		<c:when test="${empty order}">
			<h1 align="center">该订单已被您删除，请重新下单</h1>
		</c:when>
		<c:otherwise>
			<table border="1" width="100%" cellspacing="0" background="black">
				<tr bgcolor="gray" bordercolor="gray">
					<td colspan="6">订单编号：${order.oid}成交时间：<fmt:formatDate
							pattern="yyyy-MM-dd HH:mm:ss" value="${order.ordertime}" />金额：${order.total}元
					</td>
				</tr>
				<c:forEach items="${order.orderItemList}" var="orderItem">
					<tr>
						<td><img style="width: 150px;height: 150px;" src="<c:url value='/${orderItem.book.image}'/>" /></td>
						<td>书名：${orderItem.book.bname}</td>
						<td>作者：${orderItem.book.author}</td>
						<td>单价：${orderItem.book.price}</td>
						<td>数量：${orderItem.count}</td>
						<td>小计：${orderItem.subtotal}</td>
						<td><a
							href="<c:url value='/OrderServlet?method=remove&oid=${order.oid}&iid=${orderItem.iid}'/>">删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<form action="javascript:alert('暂无此功能')" method="post" id="form">
		收货地址：<input type="text" name="adress" size="50" value="湖北省武汉市湖北工业大学" /><br />
		选择银行：<br /> <input type="radio" name="pd_FrpId" value="TCBC-NET-BZC"
			checked="checked" />工商银行 <input type="radio" name="pd_FrpId"
			value="TCBC-NET-BZC" checked="checked" />农业银行 <input type="radio"
			name="pd_FrpId" value="TCBC-NET-BZC" checked="checked" />中国银行
	</form>
	<a href="javascript:document.getElementById('form').submit();">去付款</a>
</body>
</html>