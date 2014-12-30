<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
</head>
<body background="Back4 (Large) (Medium).jpg">


<c:if test="${not empty requestScope.message}">
<h3>${requestScope.message}</h3>    
</c:if>

<c:if test="${not empty requestScope.errorMessage}">
<h3>${requestScope.errorMessage}</h3>    
</c:if>

<c:if test="${not empty requestScope.sessionOut}">
<h3>Message <br> ${requestScope.sessionOut}</h3>    
</c:if>

<a href="HomePage.html"> Click here to view HomePage</a><br>
<a href="Login.jsp"> Click here to view LoginPage</a><br>

</body>
</html>