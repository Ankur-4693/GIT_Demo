<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	MARKLIST
	</br></br>
	<table>
		<tr>
			<th>Roll No</th>
			<th>Name</th>
			<th>Email</th>
			<th>M1</th>
			<th>M2</th>
			<th>M3</th>
		</tr>
		<c:forEach items="${list}" var="stu">
			<tr>
				<td>${stu.rollNo}</td>
				<td>${stu.name}</td>
				<td>${stu.email}</td>
				<td>${stu.m1}</td>
				<td>${stu.m2}</td>
				<td>${stu.m3}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>