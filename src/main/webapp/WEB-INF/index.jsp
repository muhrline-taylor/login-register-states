<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello <c:out value="${user.email}"/></h1>
	<p><a href="/login">Login</a></p>
	<p><a href="/registration">Register</a></p>
	<c:if test = "${user.id != null}">
		<p><a href="/logout">Logout</a></p>
	</c:if>
	<p><a href=""></a></p>
	<p><a href=""></a></p>
</body>
</html>