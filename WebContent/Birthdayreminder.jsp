<%@ include file="Headder.jsp" %>
<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
   <br>
   <div style = "text-align:right; float:right">
     <a href="Menu.jsp"> Menu Page</a>
   </div>
<h1 align="center"><big><i>Wish You Happy Birthday </i></big></h1>
<hr>
<c:if test="${not empty sessionScope.birthlist}">
<table  border:20 style="width:100%">
<tr><td>SL No  </td><td> Name  </td><td> Date of Birth </td><td> Emails </td><td> Phone No's </td><td> <i> Wishe's </i></td></tr>
<% int uid=0; %>
<c:forEach items="${sessionScope.birthlist}" var="birthlist">
<tr><td><%= uid++ %></td><td>${birthlist.conName}</td><td> ${birthlist.conDate} </td><td>${birthlist.conEmails} </td><td>${birthlist.conPhoneNos}</td><td><i> Wish You Happy Birth Day to ${birthlist.conName}</i></td></tr>
</c:forEach>
</table>
<hr>
<% uid=0; %>
</c:if>

<c:if test="${empty sessionScope.birthlist}">
<h1>${requestScope.message}</h1>    
</c:if>

</body>
</html>
