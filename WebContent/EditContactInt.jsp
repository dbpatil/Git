<jsp:useBean id="editConBean" class="com.uttara.contact.ContactBean"  scope="request" >
	<jsp:setProperty name="editConBean" property="*"/> 
</jsp:useBean>
<jsp:forward page="/EditContact.do"/>