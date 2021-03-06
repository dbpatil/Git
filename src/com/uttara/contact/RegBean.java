package com.uttara.contact;

import java.io.Serializable;

 
	public class RegBean implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -1593158250059920980L;

		public RegBean()
		{
			System.out.println(" inside of no org constructor of Java Bean");
		}
		
		private String uname,email,pass,rpass;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((pass == null) ? 0 : pass.hashCode());
			result = prime * result + ((rpass == null) ? 0 : rpass.hashCode());
			result = prime * result + ((uname == null) ? 0 : uname.hashCode());
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
			RegBean other = (RegBean) obj;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (pass == null) {
				if (other.pass != null)
					return false;
			} else if (!pass.equals(other.pass))
				return false;
			if (rpass == null) {
				if (other.rpass != null)
					return false;
			} else if (!rpass.equals(other.rpass))
				return false;
			if (uname == null) {
				if (other.uname != null)
					return false;
			} else if (!uname.equals(other.uname))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "RegBean [uname=" + uname + ", email=" + email + ", pass="
					+ pass + ", rpass=" + rpass + "]";
		}

		public String getUname() {
			return uname;
		}

		public void setUname(String uname) {
			this.uname = uname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public String getRpass() {
			return rpass;
		}

		public void setRpass(String rpass) {
			this.rpass = rpass;
		}
		
		
		public String validate() 
		{
			String msg="";
			if(uname==null || uname.trim().equals(""))
			msg="Dear user kindly enter a Proper name & should not be empty or null <br>";
			
			
			if(email==null || email.trim().equals(""))
			msg+=" Dear user email should be not null kindly enter proper email <br>";
			else
			{
				String mail = email;
				String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	            Boolean b = mail.matches(emailreg);

	            if (b == false) 
	            {
	                msg=msg+"Email is Invalid <br>";
	            }
			}
			
			
			if(pass==null || pass.trim().equals(""))
			msg+=" Dear user password should not null or empty input & Kindly enter proper password <br>";
			else if(!pass.equals(rpass))
			msg+=" Dear user Kindly enter proper repeating password [Password & Repeating password should be same] <br>";
			
			
			if(msg.equals(""))
			{
				return Constants.SUCCESS;
			}else
			{
				return msg;
			}
			
		}
	}