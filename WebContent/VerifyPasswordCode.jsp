<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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



<form action="VerifyPasswordCodeInt.jsp" method="post">
<table>
<br>
<h1> Enter the Verification Code</h1>
<br>
<h3> Enter Properly as it is present in Email </h3>
<br>
<tr><td>Enter Login Email  </td><td><input type="text" name="userEmail" value="${param.userEmail}"></td></tr>
<tr><td>Enter Security Code  </td><td><input type="text" name="userCode" value="${param.userCode}"></td></tr>
<tr><td>         <input type="submit"  value="Submit"/></td></tr>
</table>
</form><hr></body>
<c:if test="${not empty requestScope.errorMessage}">
<h3>${requestScope.errorMessage}</h3>    
</c:if>
</html>

