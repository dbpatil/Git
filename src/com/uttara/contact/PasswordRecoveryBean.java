package com.uttara.contact;

import java.io.Serializable;

public class PasswordRecoveryBean implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String userEmail;
	private String userCode;
	
	public PasswordRecoveryBean() 
	{
		System.out.println(" Inside of no-arg Constructor of PasswordRecoveryBean");
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PasswordRecoveryBean [userEmail=" + userEmail + ", userCode="
				+ userCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result
				+ ((userEmail == null) ? 0 : userEmail.hashCode());
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
		PasswordRecoveryBean other = (PasswordRecoveryBean) obj;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		return true;
	}
	
	
	public String validate()
	{
		String msg="";
		if(userEmail==null || userEmail.trim().equals(""))
			msg+=" Dear user email should be not null kindly enter proper email <br>";
			else
			{
				String mail = userEmail;
				String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	            Boolean b = mail.matches(emailreg);

	            if (b == false) 
	            {
	                msg=msg+"Email is Invalid";
	            }
			}
		
		if(userCode==null || userCode.trim().equals(""))
			msg+=" Dear user password should not null or empty input & Kindly enter proper password <br>";
		
		if(msg.equals(""))
		{
			return Constants.SUCCESS;
		}else
		{
			return msg;
		}
	}		
}
