<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>

<body background="Back4 (Large) (Medium).jpg">


<c:if test="${not empty exception.getMessage()}">
<h1>Error</h1><br>
<h1>Message ${exception.getMessage()}</h1>    
</c:if>

<c:if test="${not empty sessionOut}">
<h1>Error</h1><br>
<h1>Message ${sessionOut}</h1>    
</c:if>

<c:if test="${not empty requestScope.errorMessage}">
<h1> Error<br> Message : ${requestScope.errorMessage}</h1>    
</c:if>
<br><br>


<a href="openRegisterView.do" >Click to Register</a><br>
<!-- <a href="viewRegisteredUsers.do" >Click here to view registered users</a><br> -->
<a href="openLoginView.do" >Click to Login</a><br>

</body>
</html>