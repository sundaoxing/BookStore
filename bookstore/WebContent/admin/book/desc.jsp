<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</style>
</head>
<body>
	<div>
		<img style="width: 150px;height: 150px;" src="<c:url value='/${book.image}'/>" border="0" />
	</div>
	<form id="form" action="<c:url value='/admin/AdminBookServlet'/>" method="post">
	<input type="hidden" name="method" value="update" />
	<input type="hidden" name="bid" value="${book.bid}" />
	<input type="hidden" name="image" value="${book.image}" />
		图书名称：<input type="text" name="bname" value="${book.bname}" /><br/>
		图书单价：<input type="text" name="price" value="${book.price}" /><br/>
		图书作者：<input type="text" name="author" value="${book.author}" /><br/>
		图书分类：<select style="width: 150px; height: 20px;" name="cid">
		<c:forEach items="${cateList}" var="cate">
				<option value="${cate.cid}" <c:if test="${cate.cid eq book.category.cid }">selected="selected"</c:if>>${cate.cname }</option>
		</c:forEach>
		</select><br/>
		<a href="<c:url value='/admin/AdminBookServlet?method=delete&bid=${book.bid}'/>" onclick="return confirm('确认删除？');">删除</a>
		<input type="submit" value="修改" />
	</form>
</body>
</html>