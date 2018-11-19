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
<h2 align="center">分类列表</h2>
<p style="color:red;font-weight:900">${msg }</p>
<table width="80%" align="center" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<th>分类名称</th>
		<th>操作</th>
	</tr>
<c:forEach items="${cateList}" var="category">
	<tr align="center">
		<td>${category.cname}</td>
		<td><a onclick="return confirm('确定删除吗？')" href="<c:url value='/admin/AdminCategoryServlet?method=remove&cid=${category.cid}'/>">删除</a>
		<br/>
		<a href="<c:url value='/admin/AdminCategoryServlet?method=findCategory&cid=${category.cid}'/>">修改</a></td>
	</tr>
</c:forEach>
</table>
</body>
</html>