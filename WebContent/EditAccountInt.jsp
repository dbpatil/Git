<jsp:useBean id="updateregbean" class="com.uttara.contact.RegBean" scope="request">
	<jsp:setProperty name="updateregbean" property="*" />
</jsp:useBean>
<jsp:forward page="/updateRegister.do"></jsp:forward>
