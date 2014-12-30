
<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Update Success</title>
<h1>Success</h1><br>
<c:if test="${not empty requestScope.message}">
<h1>${requestScope.message}</h1>    
</c:if>

<a href="Menu.jsp"> Click here to view Menu</a><br>
<br>
<a href="Logout.do"> Click here to Logout</a><br>

</body>
</html>