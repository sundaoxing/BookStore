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
<h2>欢迎注册</h2>
<p style="color:red;font-weight:900">${msg }</p>
<p style="color:red;font-weight:900">${msgemail }</p>
<p style="color:red;font-weight:900">${error }</p>
<form action="<c:url value='/UserServlet'/>" method="post">
<input type="hidden" name="method" value="regist"/>
用户名：<input type="text" name="username" value="${form.username}"/>${errors.username}<br/>
密    码：<input type="password" name="password" value="${form.password}"/>${errors.password}<br/>
邮箱：<input type="text" name="email" value="${form.email}"/>${errors.email}<br/>
<input type="submit" value="注册"/>
</form>
</div>
</body>
</html>