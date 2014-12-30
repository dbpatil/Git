<jsp:useBean id="log" class="com.uttara.contact.LogBean"  scope="request" >
	<jsp:setProperty name="log" property="*"/> 
</jsp:useBean>
<jsp:forward page="/login.do"/>