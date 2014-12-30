<jsp:useBean id="passwordRecoveryBean" class="com.uttara.contact.PasswordRecoveryBean"  scope="request" >
	<jsp:setProperty name="passwordRecoveryBean" property="*"/> 
</jsp:useBean>
<jsp:forward page="/openVerifyPasswordCode.do"/>