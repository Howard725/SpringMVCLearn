<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form:form action="${pageContext.request.contextPath }/emp" method="POST" modelAttribute="employee">
	
		<form:errors path="*"></form:errors>
		<br>

		<c:if test="${employee.id == null }">
			LastName: <form:input path="lastName"/>
			<form:errors path="lastName"></form:errors>
			<br>
		</c:if>
		
		<c:if test="${employee.id != null }">
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
		
		
		Email: <form:input path="email"/>
		<form:errors path="email"/>
		<br>
		<%
			Map<String, String> genders = new HashMap<String, String>();
			genders.put("1", "Male");
			genders.put("0", "Female");
			
			request.setAttribute("genders", genders);
		%>
		Gender: <form:radiobuttons path="gender" items="${genders }"/>
		<br>
		Department: <form:select path="department.id" items="${departments }"
			itemLabel="departmentName" itemValue="id"></form:select>
		<br>
		
		Birth: <form:input path="birth"/>
		<form:errors path="birth"/>
		<br>

		Salary: <form:input path="salary"/>
		<br>
		
		<input type="submit" value="Submit"/>
	</form:form>

</body>
</html>