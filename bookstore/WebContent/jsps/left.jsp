<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base target="body"/>
</head>
<body>
	<div>
		<a href="<c:url value='/BookServlet?method=findAllBook'/>">全部分类</a>
	</div>
	<c:forEach items="${cateList}" var="cate">
		<div>
			<a href="<c:url value='/BookServlet?method=findCategoryBook&cid=${cate.cid}'/>">${cate.cname}</a>
		</div>
	</c:forEach>
</body>
</html>