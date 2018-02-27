<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Spring MVC</title>
</head>
<body>
	<br>
	<div style="text-align: center">
		<h2>
			Welcome to the GC Coffee thing<br> <br>
		</h2>
		<h3>
			<a href="welcome.html">Sign-up! free hat!</a>
		</h3>
	</div>
	<table border="1">
		<c:forEach items="${message}" var="list">
			<tr>
				<td><c:out value="${list}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>