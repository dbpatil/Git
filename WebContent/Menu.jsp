<%@ include file="Headder.jsp" %>
<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

    
<html><body>     
<h1 align="center"><big>Menu</big></h1>
   
     <div style = "text-align:center; float:center">
     <table>
     <tr><h3><a href="openAddContactView.do"> Add Contact</a></h3></tr>
     <tr><h3><a href="openListContactView.do"> List Contacts</a></h3></tr>
     <tr><h3><a href="openSearchView.do"> Search</a></h3></tr>
     <tr><h3><a href="openBirthdayReminderView.do"> Birthday Reminder</a></h3></tr>
     <tr><h3><a href="viewRegisteredUsers.do" >Click here to view registered users</a></h3></tr>
     </table>
   </div>
</body>
</html>