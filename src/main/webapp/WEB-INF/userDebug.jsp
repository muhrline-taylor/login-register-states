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
	<h2>Email: <c:out value="${user.email}"/></h2>
	<h2>Password: <c:out value="${user.password}"/></h2>
	<h2>State: <c:out value="${user.state.name}"/></h2>
	<h2></h2>
	<h2></h2>
</body>
</html>