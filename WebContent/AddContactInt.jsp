<jsp:useBean id="conBean" class="com.uttara.contact.ContactBean"  scope="request" >
	<jsp:setProperty name="conBean" property="*"/> 
</jsp:useBean>
<jsp:forward page="/AddContact.do"/>