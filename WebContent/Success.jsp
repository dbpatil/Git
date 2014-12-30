
<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body background="Back4 (Large) (Medium).jpg">
<h1>Success</h1>
<c:if test="${not empty requestScope.message}">
<h1>${requestScope.message}</h1>    
</c:if>

<br>
<a href="HomePage.html" /a> Click here to go HomePage<br><br>
<a href="openLoginView.do" /a> Click here for login<br>

</body>
</html>