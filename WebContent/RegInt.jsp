<jsp:useBean id="reg" class="com.uttara.contact.RegBean"  scope="request" >
	<jsp:setProperty name="reg" property="*"/> 
</jsp:useBean>
<jsp:forward page="/openVerifyRegisterEmail.do"/>