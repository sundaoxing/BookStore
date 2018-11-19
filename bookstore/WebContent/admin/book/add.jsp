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
<h2 align="center">添加图书</h2>
<p style="color:red;font-weight:900">${msg }</p>
	<form id="form" action="<c:url value='/admin/AdminAddBookServlet'/>" method="post" enctype="multipart/form-data">
		<p>图书名称：<input type="text" name="bname" value="${book.bname}" /></p>
		<p>图书图片：<input type="file" name="image" value="${book.image}" /></p>
		<p>图书单价：<input type="text" name="price" value="${book.price}" /></p>
		<p>图书作者：<input type="text" name="author" value="${book.author}" /><p/>
		图书分类：<select style="width: 150px; height: 20px;" name="cid">
		<c:forEach items="${cateList}" var="cate">
				<option value="${cate.cid }">${cate.cname }</option>
		</c:forEach>
		</select>
		<p><input type="submit" value="添加图书" /><p/>
	</form>
</body>
</html>