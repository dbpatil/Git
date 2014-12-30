<%@ include file="Headder.jsp" %>
<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div style = "text-align:right; float:right">
     <a href="Menu.jsp"> Menu Page</a>
   </div>

<h1>Edit Account Details</h1>
<hr>
	<form action="EditAccountInt.jsp" method="post">
	<table>	
	<tr><td>Name  </td><td><input type="text" align="left" name="uname" value="${RegBean.uname}" /></td></tr>
	<tr><td>Email  </td><td><input type="text"  align="left" name="email" value="${RegBean.email}" readonly="readonly" /></td></tr>
	<tr><td>Password  </td><td><input type="password" align="left" name="pass" value="${RegBean.pass}" /></td></tr>
	<tr><td>Repeat Password  </td><td><input type="password" align="left" name="rpass" value="${RegBean.rpass}" /></td></tr>
	<tr><td><input type="submit" value="Update"> </td>	</tr>
	</form>
	</table>
	<hr>
	
	<h2>NOTE 
	 1] Dear user you can edit all details Except email because of Contract of this Application </h2>
	
<c:if test="${not empty requestScope.errorMessage}">
<h3>${requestScope.errorMessage}</h3>    
</c:if>

</body>
</html>