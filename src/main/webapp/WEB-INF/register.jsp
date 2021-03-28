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
	<h1>Register</h1>
	
	<p><form:errors path="user.*"/>
	
	<form:form action="/registration/process" method="POST" modelAttribute="user">
		<p>
			<form:label path="email">Email: </form:label>
			<form:input type="text" path="email"/>
			
		</p>
		<p>
			<form:label path="password">Password: </form:label>
			<form:input type="text" path="password"/>
			
		</p>
		<p>
			<form:label path="passwordConfirmation">Confirm Password: </form:label>
			<form:input type="text" path="passwordConfirmation"/>
			
		</p>
		<p>
			<form:label path="state">State: </form:label>
			<form:select path="state">
				<c:forEach items="${states}" var="state">
					<form:option value="${state.id}">
						<c:out value="${state.name}"/>
					</form:option>
				</c:forEach>
			</form:select>
				
			
		</p>
		<button type="submit">Submit</button>
	</form:form>
</body>
</html>