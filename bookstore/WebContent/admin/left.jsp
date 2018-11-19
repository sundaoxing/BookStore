<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base target="body" />
</head>
<body>
	<div>
		<p>分类管理</p>
		<ul>
			<li><a href="<c:url value='/admin/AdminCategoryServlet?method=findAllCategory'/>">查看分类</a></li>
			<li><a href="<c:url value='/admin/category/add.jsp'/>">添加分类</a></li>
		</ul>
	</div>
	<div>
		<p>图书管理</p>
		<ul>
			<li><a href="<c:url value='/admin/AdminBookServlet?method=findAllBook'/>">查看图书</a></li>
			<li><a href="<c:url value='/admin/AdminBookServlet?method=findAllCategory'/>">添加图书</a></li>
		</ul>
	</div>
	<div>
		<p>订单管理</p>
		<ul>
			<li><a href="<c:url value='/admin/AdminOrderServlet?method=findAllOrder'/>">所有订单</a></li>
			<li><a href="<c:url value='/admin/AdminOrderServlet?method=findCategoryOrder&state=1'/>">未付款订单</a></li>
			<li><a href="<c:url value='/admin/AdminOrderServlet?method=findCategoryOrder&state=2'/>">已付款订单</a></li>
			<li><a href="<c:url value='/admin/AdminOrderServlet?method=findCategoryOrder&state=3'/>">未收货订单</a></li>
			<li><a href="<c:url value='/admin/AdminOrderServlet?method=findCategoryOrder&state=4'/>">已完成订单</a></li>
		</ul>
	</div>
</body>
</html>