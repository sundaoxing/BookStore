<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div{
		margin: 20px;
		border: solid 2px gray;
		width:150px;
		height:150px;
		text-align: center;
	}
	li{
		margin: 10px;
	}
	a{
		background: gray;
		display:inline-block;
		background-position: 0 -70px;
		margin-left: 30px;
		height:36px;
		width:146px;
		text-align: center;
	}
	
	a:hover {
		background: red;
		display:inline-block;
		background-position: 0 -106px;
		margin-left: 30px;
		height:36px;
		width:146px;
		text-align: center;
	}
</style>
</head>
<body>
<div>
	<img style="width: 150px;height: 150px;" src="<c:url value='${book.image}'/>"border="0" />
</div>
<ul>
<li>书名：${book.bname}</li>
<li>作者：${book.author}</li>
<li>单价：${book.price}</li>
</ul>
<form id="form" action="<c:url value='/CartServlet'/>" method="post">
<input type="hidden" name="method" value="add"/>
<input type="hidden" name="bid" value="${book.bid}"/>
<input style="margin-left: 40px" type="text" size="3" name="count" value="1"/>
</form>
<br/>
<a href="javascript:document.getElementById('form').submit();">添加到购物车</a>
</body>
</html>