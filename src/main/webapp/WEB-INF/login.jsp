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
	<h1>Login</h1>
	<p><c:out value="${error}"/></p>
	<form method="POST" action="/login/process">
		<p>Email: <input type="text" name="email"/></p>
		<p>Password: <input type="text" name="password"/></p>
		<button type="submit">Log In</button>
	</form>
</body>
</html>