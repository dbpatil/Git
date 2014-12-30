<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
</head>
<body background="Back4 (Large) (Medium).jpg">

<div style = "text-align:left; float:left;">
      <a href="openLoginView.do" >Click to Login</a>
    </div> 

<div style = "text-align:right; float:right">
     <a href="openRegisterView.do" >Click to Register</a>
   </div>
   
 <div style = "text-align:center; float:center">
     <a href="HomePage.html" >Click to HomePage</a>
   </div> 


<h1>Reseting Password</h1>
<hr>
	<form action="resetPassword.do" method="post">
	<table>	
	<tr><td>Enter Email id</td><td><input type="text" align="left" name="resetPassword" value="${param.resetPassword}" /></td></tr>
	
	<tr><td><input type="submit" value="Reset Password"> </td>	</tr>
	</form>
	</table>
	<hr>
<c:if test="${not empty requestScope.message}">
<h3>${requestScope.message}</h3>    
</c:if>	
	
	
<c:if test="${not empty requestScope.errorMessage}">
<h3>${requestScope.errorMessage}</h3>    
</c:if>

</body>
</html>