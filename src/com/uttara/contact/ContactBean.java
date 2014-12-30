package com.uttara.contact;

import java.io.Serializable;


/*	This is a Simple JavaBean Class Used as ContactBean.
 * 	In order to Add Contact into the DB the detailed input is taken by an 
 * 	.jsp & Using an Intermediate JSP all properties are initialized to user input by 
 * 	calling setters of this class.
 * 
 * 
 * 	All inputs are validated according to the requirement.
 * 	This class is built using the BestPractices of Java.
 * 
 * 	This class is also while updating an Contact.
 * */
public class ContactBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ContactBean()
	{
		System.out.println(" In side constructor of Contact bean");
	}
	
	private String conName,conEmails, conPhoneNos, conTags, conDate,conGender,conCreatedDate,con_sl_no;
	
	
	

	public String getCon_sl_no() {
		return con_sl_no;
	}



	public void setCon_sl_no(String con_sl_no) {
		this.con_sl_no = con_sl_no;
	}



	public String getConName() {
		return conName;
	}



	public void setConName(String conName) {
		this.conName = conName;
	}



	public String getConEmails() {
		return conEmails;
	}



	public void setConEmails(String conEmails) {
		this.conEmails = conEmails;
	}



	public String getConPhoneNos() {
		return conPhoneNos;
	}



	public void setConPhoneNos(String conPhoneNos) {
		this.conPhoneNos = conPhoneNos;
	}



	public String getConTags() {
		return conTags;
	}



	public void setConTags(String conTags) {
		this.conTags = conTags;
	}



	public String getConDate() {
		return conDate;
	}



	public void setConDate(String conDate) {
		this.conDate = conDate;
	}



	public String getConGender() {
		return conGender;
	}



	public void setConGender(String conGender) {
		this.conGender = conGender;
	}



	public String getConCreatedDate() {
		return conCreatedDate;
	}



	public void setConCreatedDate(String conCreatedDate) {
		this.conCreatedDate = conCreatedDate;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((conCreatedDate == null) ? 0 : conCreatedDate.hashCode());
		result = prime * result + ((conDate == null) ? 0 : conDate.hashCode());
		result = prime * result
				+ ((conEmails == null) ? 0 : conEmails.hashCode());
		result = prime * result
				+ ((conGender == null) ? 0 : conGender.hashCode());
		result = prime * result + ((conName == null) ? 0 : conName.hashCode());
		result = prime * result
				+ ((conPhoneNos == null) ? 0 : conPhoneNos.hashCode());
		result = prime * result + ((conTags == null) ? 0 : conTags.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactBean other = (ContactBean) obj;
		if (conCreatedDate == null) {
			if (other.conCreatedDate != null)
				return false;
		} else if (!conCreatedDate.equals(other.conCreatedDate))
			return false;
		if (conDate == null) {
			if (other.conDate != null)
				return false;
		} else if (!conDate.equals(other.conDate))
			return false;
		if (conEmails == null) {
			if (other.conEmails != null)
				return false;
		} else if (!conEmails.equals(other.conEmails))
			return false;
		if (conGender == null) {
			if (other.conGender != null)
				return false;
		} else if (!conGender.equals(other.conGender))
			return false;
		if (conName == null) {
			if (other.conName != null)
				return false;
		} else if (!conName.equals(other.conName))
			return false;
		if (conPhoneNos == null) {
			if (other.conPhoneNos != null)
				return false;
		} else if (!conPhoneNos.equals(other.conPhoneNos))
			return false;
		if (conTags == null) {
			if (other.conTags != null)
				return false;
		} else if (!conTags.equals(other.conTags))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "ContactBean [conName=" + conName + ", conEmails=" + conEmails
				+ ", conPhoneNos=" + conPhoneNos + ", conTags=" + conTags
				+ ", conDate=" + conDate + ", conGender=" + conGender
				+ ", conCreatedDate=" + conCreatedDate + "]";
	}



	public String validate() 
	{
		String message="";
		/*private String conName,conEmails, conPhoneNos, conTags, ConDate,conGender;*/
		
		if(conName==null || conName.trim().equals(""))
			message="Dear user kindly enter a Proper name & should not be empty or null <br>";
		
		
		
		
		if(conEmails==null || conEmails.trim().equals(""))
		{
		message+=" Dear user kindly enter a Proper Email & should not be empty or null <br>";
		}
		else
		{
			String[] emails=conEmails.split(",");
			for(String check:emails)
			{
				if(check==null || check.trim().equals(""))
				{
					message+=" Kindly provide a proper Email-ID [There is blank which is separated by ,]<br>";
				}else
				{
					if(validateEmail(check)==false)
					{
						message+=" "+check+" this Email-ID is invalid, Kindly provide a proper Email-ID <br>";
					}
				}
			}			
		}
		
		
		
		
		if(conPhoneNos==null || conPhoneNos.trim().equals(""))
		{
			message+= " Dear user phone should not be empty, Kindly enter proper phone no's <br>";
		}
		else
		{
			String[] phonenos=conPhoneNos.split(",");
			for(String checkphonenos:phonenos)
			{
				if(checkphonenos==null || checkphonenos.trim().equals(""))
				{
					message+=" Kindly provide a proper Phone no [There is blank which is separated by ,]<br>";
				}else
				{
					if(checkphonenos.length()==10)
					{
						 if(false==Validatephonenos(checkphonenos))
						   {
							 message+=" "+checkphonenos+" this phone no's is invalid beacause it has some special characters, Kindly provide a proper phone no <br>";	
						   }
						 
					}else
					{
						message+=" "+checkphonenos+" this phone no's is invalid beacause it does not have 10 digit valid phone no's, Kindly provide a proper phone no <br>";
					}
				}
			}
			/*message+=" Kindly enter proper Phone-No's, In the list of Phone No's it contains "+count+" no of Null valued or empty Phone no's. <br>";*/
		}
		
		
		
		
		if(conTags==null || conTags.trim().equals(""))
		{
			message+= " Dear user Tags should not be empty, Kindly enter proper Tags <br>";
		}else
		{
			String[] tags=conTags.split(",");
			for(String checktags:tags)
			{
				if(checktags==null || checktags.trim().equals(""))
				{
					message+=" Kindly enter proper Tags <br>";
				}
			}			
		}	
		
		
			
		if(conDate==null||conDate.trim().equals(""))
		{
			message+=" Dear user Kindly provide a date <br>";
		}else if((conDate.charAt(2)=='/') && (conDate.charAt(5)=='/'))
		{
			try
			{
				String result=DateHelper.DateChecker(conDate);
				if(result!=null && (!result.equals(Constants.SUCCESS)))
				{
					message+=result;
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
				message+=" Dear user Kindly provide a valid date as per given Syntax [ "+e.getMessage()+" ]<br> ";
			}			
		}else
			message+=" Dear user Kindly provide a Date in the given date format ";
		
		
		
		if(conGender==null||conGender.trim().equals(""))
			message+=" Dear user Kindly Select a Gender [Mandatary]<br> ";
		
		if(message.equals(""))
		{
			return Constants.SUCCESS;
		}else
		{
			return message;
		}
					
	}
	
	

	private boolean validateEmail(String email)
	{
		String mail = email;
		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return mail.matches(emailreg);       
	}
	
	private boolean Validatephonenos(String Checkphoneno) 
	{
				
		for(int i=0;i<Checkphoneno.length();i++)
		{
			char c= Checkphoneno.charAt(i);
			if(Character.isDigit(c)==false)
			{
				return false;
			}						
		}
		return true;
	}
	
}
