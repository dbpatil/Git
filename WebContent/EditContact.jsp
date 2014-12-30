<%@ include file="Headder.jsp" %>
<%@ page isErrorPage="false" %>
<%@ page errorPage="Error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
   <div style = "text-align:right; float:right">
     <a href="Menu.jsp"> Menu Page</a>
   </div>

</head>
<form action="EditContactInt.jsp" method="post">
<table>
<br>
<h1> Enter Contact Details to Update</h1>
<hr>
<tr><td>Enter Name  </td><td><input type="text" name="conName" value="${ContactBean.conName}" ></td></tr>
<tr><td>Enter Email  </td><td><input type="text" name="conEmails"  value="${ContactBean.conEmails}"></td></tr>
<tr><td>Enter Phone No  </td><td><input type="text" name="conPhoneNos" value="${ContactBean.conPhoneNos}"></td></tr>
<tr><td>Enter Tags  </td><td><input type="text" name="conTags" value="${ContactBean.conTags}"></td></tr>
<tr><td>Enter Date of Birth  </td><td><input type="date" name="conDate" value="${ContactBean.conDate}"></td></tr>
<tr><td>Select Gender  </td><td><SELECT type="text" name="conGender" Gender="Select" >   <OPTION>${ContactBean.conGender}<OPTION>Male 	<OPTION>Female 	<OPTION>Other </SELECT></td></tr>
<tr><td>         <input type="submit"  value="Update Contact"/></td></tr>
</table>
</form><hr>
<h1>Note</h1>
<h3>
1] Email's separated by ( , )<br>
2] Phone No's separated by ( , )<br>
3] Tags separated by ( , )<br>
4] Date Format is "DD/MM/YYYY"<br>
</h3>

	<c:if test="${not empty requestScope.errorMessage}">
	<h3>${requestScope.errorMessage}</h3>    
	</c:if>
	
</body>
</html>