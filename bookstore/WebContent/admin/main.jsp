<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body {
	font-size:20px;
	text-align: center;
}

.table {
	width: 80%;
	height: 80%;
	border: 1px solid gray;
	border-collapse: collapse;
}

.table td {
	border: 1px solid gray;
}

iframe {
	width: 100%;
	height: 100%;
}
</style>

<title>主页</title>
</head>
<body>
	<table class="table" align="center">
		<tr>
			<td colspan="2" align="center"><iframe frameborder="0"
					src="<c:url value='/admin/top.jsp' />" name="top"></iframe></td>
		</tr>
		<tr>
			<td height="450" width="150" style="padding: 5px;" align="left" valign="top">
				<iframe frameborder="0" width="150"
					src="<c:url value='/admin/left.jsp' />" name="left"></iframe>
			</td>
			<td><iframe frameborder="0" 
					src="<c:url value='/admin/body.jsp' />" name="body"></iframe></td>
		</tr>
	</table>
</body>
</html>