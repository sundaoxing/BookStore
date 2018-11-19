<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align: center;">书店</h1>
	<div>
		<c:choose>
			<c:when test="${empty sessionScope.session_user}">
				<a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登陆&nbsp;|</a>
				<a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册</a>
			</c:when>
			<c:otherwise>
				<span>您好：${sessionScope.session_user.username}&nbsp;&nbsp;|</span>
				<a href="<c:url value='/jsps/cart/list.jsp'/>" target="body">我的购物车&nbsp;|</a>
				<a href="<c:url value='/OrderServlet?method=findMyOrders'/>" target="body">我的订单&nbsp;|</a>
				<a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>