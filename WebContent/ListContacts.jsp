<%@ include file="Headder.jsp" %>
<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

   <div style = "text-align:right; float:right">
     <a href="Menu.jsp"> Menu Page</a>
   </div>
	<h1>   List of Contacts    </h1>     
   <br>
   <hr>
      
<c:if test="${not empty requestScope.ListCont}">      
<table  border:20 style="width:100%">
<tr><td>SL No  </td><td> Name  </td><td> Date of Birth </td><td> Gender </td><td> Created date </td><td> Emails </td><td> Phone No's </td><td> Tags </td><td> Contact Sl No </td><td>Edit Contact </td><td>Delete Contact </td></tr>  
<% int uid=0; %>
<c:forEach items="${requestScope.ListCont}" var="ListCont">
<tr><td><%= uid++ %></td><td>${ListCont.conName}</td><td> ${ListCont.conDate} </td><td>${ListCont.conGender} </td><td>${ListCont.conCreatedDate}</td><td>${ListCont.conEmails}</td><td>${ListCont.conPhoneNos}</td><td>${ListCont.conTags}</td><td>${ListCont.con_sl_no}</td><td><a href="openEditContactView.do?Con_sl_no=${ListCont.con_sl_no}"> Edit Contact</a></td> <td><a href="openDeleteContactView.do?Con_sl_no=${ListCont.con_sl_no}"> Delete Contact</a></td> </tr>
</c:forEach>
</table>
<hr>
<% uid=0; %>
</c:if>

<c:if test="${not empty requestScope.message}">
<h3>${requestScope.message}</h3>    
</c:if>

</body>
</html>