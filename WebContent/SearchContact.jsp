<%@ include file="Headder.jsp" %>
<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
   <br>
   <div style = "text-align:right; float:right">
     <a href="Menu.jsp"> Menu Page</a>
     <br>
   </div>	<h1>   Searching Contacts    </h1>     
   <br>
   <form action="SearchContact.do">
   <tr><td>Select Type of Search  </td><td><SELECT type="text" name="Searchby" Searchby="Select" >  <OPTION>Name 	<OPTION>Date of Birth 	<OPTION>Gender <OPTION>Created date <OPTION> Email ID <OPTION>Phoneno <OPTION>Tag </SELECT></td></tr><br>
   <br>
    <tr><td>Enter value to Search </td><td><input type="text" name="searchvalue" required="required" ></td></tr>   <br>
   <tr><td>        </td><td> <input type="submit" value="Search"></td>
   </form>
   <hr>
   

<c:if test="${not empty requestScope.searched}">      
<table  border:20 style="width:100%">
<tr><td>SL No  </td><td> Name  </td><td> Date of Birth </td><td> Gender </td><td> Created date </td><td> Emails </td><td> Phone No's </td><td> Tags </td><td> Contact Sl No </td><td>Edit Contact </td><td>Delete Contact </td></tr>  
<% int uid=0; %>
<c:forEach items="${requestScope.searched}" var="searched">
<tr><td><%= uid++ %></td><td>${searched.conName}</td><td> ${searched.conDate} </td><td>${searched.conGender} </td><td>${searched.conCreatedDate}</td><td>${searched.conEmails}</td><td>${searched.conPhoneNos}</td><td>${searched.conTags}</td><td>${searched.con_sl_no}</td><td><a href="openEditContactView.do?Con_sl_no=${searched.con_sl_no}"> Edit Contact</a></td> <td><a href="openDeleteContactView.do?Con_sl_no=${searched.con_sl_no}"> Delete Contact</a></td> </tr>
</c:forEach>
</table>
<hr>
<% uid=0; %>
</c:if>
  
<c:if test="${not empty requestScope.errorMessage}">
<h3>${requestScope.errorMessage}</h3>    
</c:if>  
    
</body>
</html>