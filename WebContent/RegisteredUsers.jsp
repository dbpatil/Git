<%-- <%@ include file="Headder.jsp" %> --%>
<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<title>RegisteredUsers</title>
</head>
<br>
<body background="Back4 (Large) (Medium).jpg">
<div style = "text-align:right; float:right">
     <a href="Menu.jsp"> Menu Page</a>
   </div>
<h1 align="center"><big><i>Registered Users are </i></big></h1>

<hr>

   
<c:if test="${not empty requestScope.regUsers}">
<table  border:20 style="width:100%">
<tr><td>SL No  </td><td>User Name  </td><td> User Email</td></tr>
<% int uid=0; %>
<c:forEach items="${requestScope.regUsers}" var="regUsers">
<tr><td><%= uid++ %></td><td>${regUsers.uname}</td><td> ${regUsers.email} </td></tr>
</c:forEach>
</table>
<hr>
<% uid=0; %>
</c:if>
<br><br>
<a href="HomePage.html" /a> Click here to go HomePage<br>
<a href="Login.jsp" /a> Click here for login<br>
</body>
</html>