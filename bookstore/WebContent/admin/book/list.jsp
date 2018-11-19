<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.icon {
	margin: 10px;
	border: solid 2px gray;
	width: 160px;
	height: 180px;
	text-align: center;
	float: left;
}
</style>
</head>
<body>
<c:forEach items="${bookList}" var="book">
<div class="icon">
<a href="<c:url value='/admin/AdminBookServlet?method=load&bid=${book.bid}'/>"><img style="width: 150px;height: 150px;" src="<c:url value='/${book.image}'/>" border="0"/></a>
<br/>
<a href="<c:url value='/admin/AdminBookServlet?method=load&bid=${book.bid}'/>">${book.bname}</a>
</div>
</c:forEach>
</body>
</html>