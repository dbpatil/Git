<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
</head>
<body background="Back4 (Large) (Medium).jpg">
<div style = "text-align:left; float:left">
     <a href="openLoginView.do" >Click to Login</a><br>
   </div>
   
   <div style = "text-align:right; float:right">
     <a href="HomePage.html" >Click to HomePage</a><br>
   </div>
   
   <br>
<h1>Enter the Details</h1>
<hr>

<form action="RegInt.jsp" method="post">
<table>
<tr><td>Enter the Name  </td><td><input type="text" required="required" name="uname"  value="${param.uname}"></td></tr>
<tr><td>Enter the Email  </td><td><input type="email" required="required" name="email" value="${param.email}"></td></tr>
<tr><td>Enter the Password  </td><td><input type="password" required="required" name="pass" value="${param.pass}"></td></tr>
<tr><td>Enter the Repeat Password  </td><td><input type="password" required="required" name="rpass"  value="${param.rpass}"></td></tr>
<tr><td><input type="submit" value="Register"></td></tr>
</table>
<hr>

<%

	if(request.getAttribute("errorMessage")!=null)
	{
		out.println("<h1>Error</h1>");
		
		out.println(request.getAttribute("errorMessage"));	
	}

%>

</form>
</body>
</html>