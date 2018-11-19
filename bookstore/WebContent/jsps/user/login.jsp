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
<div align="center">
<h2>欢迎登陆</h2>
<p style="color:red;font-weight:900">${msg }</p>
<form action="<c:url value='/UserServlet'/>" method="post" target="_top">
<input type="hidden" name="method" value="login"/>
用户名：<input type="text" name="username"  value="${form.username }"/><br/>
密    码：<input type="password" name="password"  value="${form.password }"/><br/>
<input type="submit" value="登陆"/><br/>
</form>
</div>
</body>
</html>