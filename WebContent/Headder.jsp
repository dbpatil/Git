<%@page import="com.uttara.contact.*"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu </title>
</head>
<body background="Back4 (Large) (Medium).jpg">
	<div style = "text-align:left; float:left;">
      <a href="openEditAccountView.do"> Edit Account</a>
    </div> 
         
   <div style = "text-align:right; float:right">
      <a href="Logout.do"> Logout </a>
   </div>
   <br>
   
     <div style = "text-align:left; float:left;">
     User ID: ${sessionScope.USER.email}
     </div>
     
        <div style = "text-align:right; float:right">
      <%
       Date d= new Date();
       DateFormat df= DateFormat.getDateTimeInstance();
       out.print(df.format(d));
      %>
   </div>
   <br>
   </body>
   </html>