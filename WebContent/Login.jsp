<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body background="Back4 (Large) (Medium).jpg">

<div style = "text-align:left; float:left">
     <a href="openRegisterView.do" >Click to Register</a>
   </div>
   
   <div style = "text-align:right; float:right">
     <a href="HomePage.html" >Click to HomePage</a><br>
   </div>
   
   <br>

<h1>Login</h1>
<hr>
<form action="LogInt.jsp" method="Post">
<table>
<tr><td> Enter the Email </td><td> <input type="text"  name="email" required="required" value="${param.email}"></td></tr>
<tr><td> Enter the Password  </td><td><input type="password"  name="pass" required="required" value="${param.pass }"></td></tr>
<tr><td> <input type="Submit" value="Login" ></td> <td><a href="openResetPasswordView.do" >Forgot Password</a><br></td> </tr>
</table>
</form>
<hr>

<c:if test="${not empty requestScope.errorMessage}">
	<h3>${requestScope.errorMessage}</h3>    
</c:if>

</body>
</html>