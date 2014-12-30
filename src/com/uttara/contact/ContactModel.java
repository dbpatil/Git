package com.uttara.contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


	/*This is an Model Class Named as ContactModel
	 * 	This class has several method which all has it's own responsibility
	 * 	of communicating with DB using JDBCHelperClass.
	*/

public class ContactModel 
{
	public ContactModel()
	{
		System.out.println("inside of No-arg Constructor");
	}
	
	
	public String  verifyEmail(RegBean bean) 
	{
		System.out.println(" inside of Model---> register method--------> "+bean);
		String result=bean.validate();
		System.out.println(result);
		if(result.equals(Constants.SUCCESS))
		{
			System.out.println(" bean validation has been completed ------> right now need to write data into DB");			
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_sel=null,ps_ins=null;
			
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
				return "DB Connection has been failed Kindly contact your admin ";
				else
				{
					ps_sel = con.prepareStatement("select * from regusers where email = ?");
					ps_sel.setString(1, bean.getEmail());
					ps_sel.execute();
					rs = ps_sel.getResultSet();
				}
				
				if(rs.next())
				{				
					return "Dear user this email is already registered Kindly give an unique email id";
				}else
				{
					String code=SecurityHelper.secureCode();
					System.out.println("Secure generated now is "+code);
					String subject=" EMAIL ID Verfication by ContactApp Devaloper[Group] ";
					String body=" Dear "+bean.getUname()+", Enter code in the Verfication session & Kindly keep it confidential. It can also used to Reset the Password. Your Security Code is "+code+". Thanks & Regards Doddanna B ContactApp Devaloper Team (Lead) Banglore - 560001";
					String resultMail= SendMail.send(bean.getEmail(), Constants.FROMEMAIL, Constants.EMAILPWD, subject, body);
					System.out.println("Result Mail return value is "+resultMail);
					if(resultMail.equals(Constants.SUCCESS))
					{
						ps_ins = con.prepareStatement("insert into SECURETABLE (Email,securecode) values(?,?)");
						ps_ins.setString(1, bean.getEmail());
						ps_ins.setString(2, code);
						ps_ins.execute();
						
						return Constants.SUCCESS;
					}else
					{
						return resultMail;
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception is Occured in VerfyMail method : cause is = "+e.getMessage());
				return "Exception is Occured in VerfyMail method : cause is = "+e.getMessage();
			}				
		}
		return result;		
	}
	
	public String sendCodetoResetPassword(String email)
	{
		System.out.println(" inside of Model---> resetPassword method--------> "+email);
		ArrayList<RegBean> al = (ArrayList<RegBean>)getUsers();
		boolean validEmail=false;
		for(RegBean rb:al)
		{
			if(rb.getEmail().equals(email))
			{
				validEmail=true;
			}
		}
		
		if(validEmail==true)
		{
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_sel=null,ps_ins=null;
			
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
				return "DB Connection has been failed Kindly contact your admin ";
				else
				{
					String code=SecurityHelper.secureCode();			
					System.out.println("Secure generated now is "+code);
					String subject=" RESET Password by ContactApp Devaloper[Group] ";
					String body=" Dear user, Enter the code in the Verfication session & Kindly keep it confidential. Your Security Code is "+code+". Thanks & Regards Doddanna B ContactApp Devaloper Team (Lead) Banglore - 560001";
					String resultMail= SendMail.send(email, Constants.FROMEMAIL, Constants.EMAILPWD, subject, body);
					System.out.println("Result Mail return value is "+resultMail);
					if(resultMail.equals(Constants.SUCCESS))
					{
						ps_ins = con.prepareStatement("insert into SECURETABLE (Email,securecode) values(?,?)");
						ps_ins.setString(1, email);
						ps_ins.setString(2, code);
						ps_ins.execute();
						
						return Constants.SUCCESS;
					}else
					{
						return "<h3>Dear user Some problem Occured in the Server while sending a Email, due to "+resultMail+" </h3>";
					}
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "<h3>Dear user Some problem Occured in the Server while sending a Email, due to "+e.getMessage()+" </h3>";
				/*return "<h3>Ooops</h3> Some problem has been occured [ dueto this = "+e.getMessage()+ " ]";*/
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(ps_ins);
			}		
	}else
	return "<h1> Email ID is Invalid, Kindly Enter Proper Email ID </h3>";
}
		
	
	
	public String resetPassword(PasswordRecoveryBean bean)
	{
		System.out.println(" inside of Model---> resetPassword method--------> "+bean);
		String result=bean.validate();
		System.out.println(result);
		if(result.equals(Constants.SUCCESS))
		{
			ArrayList<RegBean> al= (ArrayList<RegBean>)getUsers();
			boolean verifyemail=false;
			for(RegBean b:al)
			{
				if(b.getEmail().equals(bean.getUserEmail()))
				{
					verifyemail=true;
				}
			}
				
			if(verifyemail==true)
			{
				System.out.println(" bean validation has been completed ");
				
				Connection con = null;
				ResultSet rs = null;
				PreparedStatement ps_sel=null,ps_ins=null;
				
				try
				{
					con = JDBCHelper.getConnection();
					if(con==null)
					return "DB Connection has been failed Kindly contact your admin ";
					else
					{
							ps_sel = con.prepareStatement("select * from SECURETABLE where email = ? ");
							ps_sel.setString(1, bean.getUserEmail());
							ps_sel.execute();
							rs = ps_sel.getResultSet();
							String jdbcCode1="";
							while(rs.next())
							{
								jdbcCode1+=rs.getString("secureCode")+",";
							}
							String x=jdbcCode1.substring(0, jdbcCode1.length()-1);
							String [] arr= x.split(",");
							String jdbcCode="";
							for(String s:arr)
							{
								jdbcCode=s;
							}						
							System.out.println("JDBC Code is return this code"+jdbcCode +" & the actual code is "+bean.getUserCode());
							if(bean.getUserCode().equals(jdbcCode))
							{
								PreparedStatement ps_upd = con.prepareStatement("update regusers SET pass=? where email = '"+bean.getUserEmail()+"'");
								ps_upd.setString(1, "RESET");
								ps_upd.execute();
								
								return Constants.SUCCESS;
								
							}else
							{
								return "<h1> Dear user, Entered Verification Code is Not Matching, Kindly Enter Properly </h1><br> ";
							}
						}					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					return "<h3>Ooops</h3> Some problem has been occured [ dueto this = "+e.getMessage()+ " ]";
				}
				finally
				{
					JDBCHelper.close(rs);
					JDBCHelper.close(ps_sel);
					JDBCHelper.close(ps_ins);
				}			
			
			}else
			{
				return result;
			}
				
			}else
			{
				return "Email id is Invalid, Kindly enter Proper Email ID";
			}		
	}
	

	
	public String register (RegBean bean,String code)
	{
		System.out.println(" inside of Model---> register method--------> "+bean+" and the Code is "+code);
		String result=bean.validate();
		System.out.println(result);
		if(result.equals(Constants.SUCCESS))
		{
			System.out.println(" bean validation has been completed ------> right now need to write data into DB");
			
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_sel=null,ps_ins=null;
			
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
				return "DB Connection has been failed Kindly contact your admin ";
				else
				{
						ps_sel = con.prepareStatement("select * from SECURETABLE where email = ? ");
						ps_sel.setString(1, bean.getEmail());
						ps_sel.execute();
						rs = ps_sel.getResultSet();
						String jdbcCode1="";
						while(rs.next())
						{
							jdbcCode1+=rs.getString("secureCode")+",";
						}
						String x=jdbcCode1.substring(0, jdbcCode1.length()-1);
						String [] arr= x.split(",");
						String jdbcCode="";
						for(String s:arr)
						{
							jdbcCode=s;
						}						
						System.out.println("JDBC Code is return this code"+jdbcCode +" & the actual code is "+code);
						if(code.equals(jdbcCode))
						{
							ps_ins = con.prepareStatement("insert into regusers(name,email,pass) values(?,?,?)");
							ps_ins.setString(1, bean.getUname());
							ps_ins.setString(2, bean.getEmail());
							ps_ins.setString(3, bean.getPass());
							ps_ins.execute();
							return Constants.SUCCESS;
							
						}else
						{
							return "<h1> Dear "+bean.getUname()+", Entered Verification Code is Not Matching, Kindly Enter Properly </h1><br> ";
						}
					}
				
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "<h3>Ooops</h3> Some problem has been occured [ dueto this = "+e.getMessage()+ " ]";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(ps_ins);
			}			
		
		}else
		{
			return result;
		}
	}
	
	
	public List<RegBean> getUsers() throws IllegalArgumentException
	{
		System.out.println(" inside of Model---> register method--------> ");
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps_sel=null,ps_ins=null;
		try
		{
			con=JDBCHelper.getConnection();
			if(con==null)
			throw new IllegalArgumentException(" Dear user problem occured in DB Connection Kindly contatct in your admin");
			else
			{
				ps_sel = con.prepareStatement("select * from regusers");
				ps_sel.execute();
				rs=ps_sel.getResultSet();
				List<RegBean> al = new ArrayList<RegBean>();
				RegBean rb = null;
				while(rs.next())
				{
					rb = new RegBean();
					rb.setUname(rs.getString("name"));
					rb.setEmail(rs.getString("email"));
					rb.setPass(rs.getString("pass"));
					rb.setRpass(rs.getString("pass"));
					
					al.add(rb);
				}
				return al;
			}		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException(" Dear user problem occured in DB Connection Kindly contatct in your admin [ cause is "+e.getMessage()+" ]");
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(ps_ins);
		}
	}
	
	
	public String login(LogBean lb) throws IllegalArgumentException
	{
		System.out.println(" inside ------>Model, -------> Login method "+lb);
		String result= lb.validate();
		if(result.equals(Constants.SUCCESS))
		{
			System.out.println("Logbena validation sucecceded writing data into the DB");
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_sel=null,ps_ins=null;
			try
			{
				con=JDBCHelper.getConnection();
				if(con==null)
				throw new IllegalArgumentException(" Dear user problem occured in DB Connection Kindly contatct in your admin");
				else
				{
					ps_sel = con.prepareStatement("SELECT sl_no, name, email, pass from regusers where email = '"+lb.getEmail()+"'");
					ps_sel.execute();
					rs=ps_sel.getResultSet();
					String dbName=null;
					String dbEmail=null;
					String dbPass=null;
					while(rs.next())
					{
						 dbName = rs.getString("name");
						 dbEmail = rs.getString("email");
						 dbPass = rs.getString("pass");
						System.out.println("Name = "+dbName+"  "+"email = "+dbEmail+"  "+"pass = "+dbPass);				
					}
					System.out.println("Name = "+dbName+"  "+"email = "+dbEmail+"  "+"pass = "+dbPass);
					if(lb.getEmail().equals(dbEmail))
					{
						if(lb.getPass().equals(dbPass))
						return Constants.SUCCESS;
						else
						return "<b>Dear user password is not matching try once again or contact admin <b> <br>";
					}else
					{
						return "<b>Dear user Email id INCORRECT, Kindly enter proper Email ID <b> <br>";
					}
					
				}		
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return " Dear user problem occured in DB Connection Kindly contatct in your admin [ cause is "+e.getMessage()+" ]";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(ps_ins);
			}
			
		}else
		{
			return result;
		}		
	}
	
	public RegBean authentication(String user) 
	{
		System.out.println("inside---->ContactModel ------>currentUser Method [ input is emailID = "+user+" ]");
		List<RegBean> l= getUsers();
		ArrayList<RegBean> al= (ArrayList<RegBean>) l; 
		RegBean foundRegBean=null;
		for( RegBean rb:al)
		{
			if(rb.getEmail().equals(user))
			{
				System.out.println(rb);
				foundRegBean=rb;
			}			
		}
		return foundRegBean;			
	}
	
	public String updateRegister(RegBean rb)
	{
		System.out.println(" inside of Model---> updateRegister method--------> "+rb);
		String result=rb.validate();
		System.out.println(result);
		if(result.equals(Constants.SUCCESS))
		{
			System.out.println(" bean validation has been completed ------> right now need to write data into DB");
			
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_sel=null,ps_ins=null;
			
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
				return "DB Connection has been failed Kindly contact your admin ";
				else
				{
					
					PreparedStatement ps_upd = con.prepareStatement("update regusers set name=? , pass=? where email = '"+rb.getEmail()+"'");
					ps_upd.setString(1, rb.getUname());
					ps_upd.setString(2, rb.getPass());
					ps_upd.execute();
					
					return Constants.SUCCESS;
				}			
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "<h3>Ooops</h3> Some problem has been occured [ dueto this = "+e.getMessage()+ " ]";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(ps_ins);
			}		
		}else
		{
			return result;
		}		
	}
	
	public String addContact(ContactBean conBean,String email)
	{
		System.out.println(" inside of Model---> addContact method--------> "+conBean+" and the email is "+email);		
		String result=conBean.validate();
		System.out.println(result);
		if(result.equals(Constants.SUCCESS))
		{
			System.out.println(" ContactBean validation has been completed ------> right now need to write data into DB");
						
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_sel=null,ps_ins=null;
			Date d=null;
			
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
				return "DB Connection has been failed Kindly contact your admin ";
				else
				{
					System.out.println("Writing date into tables I'st taking the ref key of Regusers table for current user, the current user is "+email);
					System.out.println("Email is "+email);
					ps_sel = con.prepareStatement("SELECT sl_no from regusers WHERE email = ?");
					ps_sel.setString(1, email);
					ps_sel.execute();
					rs = ps_sel.getResultSet();
					
					rs.next();
					String db_reg_sl_no=rs.getString("sl_no");
									
					System.out.println("Registerd user sl no is = "+db_reg_sl_no +"for the email id "+email);
					
					try
					{
						d=DateHelper.javaToHsqlDateConverter(conBean.getConDate());
						if(d==null)
							return "Date is not syntax error Kindly provide a date in a given format";
					}
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
					
					ps_ins = con.prepareStatement("insert into contact (name,dob,gender,cr_dt,reguser_no) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
					ps_ins.setString(1, conBean.getConName());
					ps_ins.setDate(2, (java.sql.Date) d);
					ps_ins.setString(3, conBean.getConGender());
					ps_ins.setDate(4, (java.sql.Date) DateHelper.createhsqlDate());
					ps_ins.setString(5, db_reg_sl_no );
					ps_ins.executeUpdate();	
					rs= ps_ins.getGeneratedKeys();
					
					String db_cont_sl_no="";
					if(rs.next())
					{
						db_cont_sl_no+=rs.getInt(1);
					}
									
					System.out.println("Registerd user sl no is = "+db_cont_sl_no);
					
					String [] emails = conBean.getConEmails().split(",");
						for(String inemails :emails)
						{
							ps_ins = con.prepareStatement("insert into cont_emails (cont_sl_no,emails)  values (?,?)");
							ps_ins.setString(1, db_cont_sl_no );
							ps_ins.setString(2, inemails);
							ps_ins.executeUpdate();
						}
						
						String [] tags = conBean.getConTags().split(",");
						for(String intags :tags)
						{
							ps_ins = con.prepareStatement("insert into con_tags(cont_sl_no,tags) values (?,?)");
							ps_ins.setString(1, db_cont_sl_no );
							ps_ins.setString(2, intags);
							ps_ins.executeUpdate();
						}
						
						String [] phonenos= conBean.getConPhoneNos().split(",");
						for(String inphonenos :phonenos)
						{
							ps_ins = con.prepareStatement("insert into con_phoneno (cont_sl_no,phoneno) values (?,?)");
							ps_ins.setString(1, db_cont_sl_no );
							ps_ins.setString(2, inphonenos);
							ps_ins.executeUpdate();
						}					
												
						return Constants.SUCCESS;
						
				   }
			}	
			catch(Exception e)
			{
				e.printStackTrace();
				return "<h3>Ooops</h3> Some problem has been occured [ dueto this = "+e.getMessage()+ " ]";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(ps_ins);
			}
		}
		else
		return result;
	}	
	
	
	public List<ContactBean> listContacts(String useremail) throws IllegalArgumentException
	{
		System.out.println(" inside of Model---> listContact method--------> ");
		Connection con = null;
		ResultSet rs = null;
		List<ContactBean> al=null;
		PreparedStatement ps_sel=null,ps_sel1=null,ps_sel2=null,ps_sel3=null,ps_sel4=null,ps_ins=null;
		ResultSet rs1 = null;ResultSet rs2 = null;ResultSet rs3 = null;ResultSet rs4 = null;
		try
		{
			al= new ArrayList<ContactBean>();
			con=JDBCHelper.getConnection();
			if(con==null)
			throw new IllegalArgumentException(" Dear user problem occured in DB Connection Kindly contatct in your admin");
			else
			{
				System.out.println("Email is "+useremail);
				System.out.println("Accessing db to get reuser slno of present user ");
				
				ps_sel = con.prepareStatement("SELECT sl_no from regusers WHERE email = ?");
				ps_sel.setString(1, useremail);
				ps_sel.execute();
				rs = ps_sel.getResultSet();
				
				rs.next();
				String db_reg_sl_no=rs.getString("sl_no");
								
				System.out.println("Registerd user sl no is = "+db_reg_sl_no +" for the email id "+useremail);
				
				
				System.out.println("Accessing db to get slno of contacts (more than one may be) by using reguser sl_no ");
				ps_sel1 = con.prepareStatement("SELECT * from contact WHERE reguser_no = ?");
				ps_sel1.setString(1, db_reg_sl_no);
				ps_sel1.execute();
				rs1 = ps_sel1.getResultSet();
				ContactBean cb=null;
				Date d=null;
				while(rs1.next())
				{
					cb=new ContactBean();
					cb.setCon_sl_no(rs1.getString("sl_no"));
					cb.setConName(rs1.getString("name"));
					cb.setConDate(DateHelper.HsqlDateToUtilDateConverter((rs1.getDate("dob"))));
					cb.setConGender(rs1.getString("gender"));
					cb.setConCreatedDate(DateHelper.HsqlDateToUtilDateConverter((rs1.getDate("cr_dt"))));
					String db_cont_sl_no=rs1.getString("sl_no");
					
					
					System.out.println("Accessing db to get slno of contacts_emails (more than one may be) by using contact sl_no ");
					ps_sel2 = con.prepareStatement("SELECT emails from CONT_EMAILS WHERE cont_sl_no = ?");
					ps_sel2.setString(1, db_cont_sl_no);
					ps_sel2.execute();
					rs2 = ps_sel2.getResultSet();
					
					
					String db_emailsone="";
					System.out.println("this email will contain only one unneccesary , at end need to remove thats why i use two strings");
					while(rs2.next())
					{
						db_emailsone+=rs2.getString("emails")+",";
					}
					String db_emails=db_emailsone.substring(0, db_emailsone.length()-1);
					System.out.println("Setting emails");
					
					cb.setConEmails(db_emails);
					
					
					System.out.println("Accessing db to get contacts phonenos (more than one may be) by using contact sl_no ");
					
					ps_sel3 = con.prepareStatement("SELECT PHONENO from CON_PHONENO WHERE cont_sl_no = ?");
					ps_sel3.setString(1, db_cont_sl_no);
					ps_sel3.execute();
					rs3 = ps_sel3.getResultSet();
					
					String db_phonenosone="";
					System.out.println("this email will contain only one unneccesary , at end need to remove thats why i use two strings");
					while(rs3.next())
					{
						db_phonenosone+=rs3.getString("PHONENO")+",";
					}
					String db_phonenos=db_phonenosone.substring(0, db_phonenosone.length()-1);
					System.out.println("Setting phonenos");
					
					cb.setConPhoneNos(db_phonenos);
					
					System.out.println("Accessing db to get contacts tags (more than one may be) by using contact sl_no ");
					
					ps_sel4 = con.prepareStatement("SELECT TAGS from CON_TAGS WHERE cont_sl_no = ?");
					ps_sel4.setString(1, db_cont_sl_no);
					ps_sel4.execute();
					rs4 = ps_sel4.getResultSet();
					
					String db_tagsone="";
					System.out.println("this email will contain only one unneccesary , at end need to remove thats why i use two strings");
					while(rs4.next())
					{
						db_tagsone+=rs4.getString("TAGS")+",";
					}
					String db_tags=db_tagsone.substring(0, db_tagsone.length()-1);
					System.out.println("Setting tags");
					
					cb.setConTags(db_tags);
					
					System.out.println("Adding ContactBean to ArrayList<ContactBean> ContactBean is "+cb);
					al.add(cb);					
					
				}
				return al;
			}		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException(" Dear user problem occured in DB Connection Kindly contatct in your admin [ cause is "+e.getMessage()+" ]");
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(ps_ins);
		}		
	}
	
	
	public ContactBean currentContactBean(String Con_sl_no,String useremail)
	{
		System.out.println(" inside of Model---> currentContactBean method--------> "+Con_sl_no);
		List<ContactBean> al= (List<ContactBean>) listContacts(useremail);
		ArrayList<ContactBean> al2= new ArrayList<ContactBean>();
		al2=(ArrayList<ContactBean>) al;
		
		System.out.println("Return by listContacts(String useremail) "+al2);
		ContactBean found=null;
		for(ContactBean cb:al2)
		{
			if(cb.getCon_sl_no().equals(Con_sl_no))
			{
				found=cb;
				return found;
			}
			
		}
		return found;
				
	}
	
	
	
	
	public String updateContact(ContactBean cb)
	{
		System.out.println(" inside of Model---> updateContact method--------> "+cb);
		String result=cb.validate();
		System.out.println(result);
		if(result.equals(Constants.SUCCESS))
		{
			System.out.println(" bean validation has been completed ------> right now need to write data into DB");
			
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps_sel=null,ps_ins=null;
			PreparedStatement ps_upd=null;
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
				return "DB Connection has been failed Kindly contact your admin ";
				else
				{
					Date d=null;
					try
					{
						d=DateHelper.javaToHsqlDateConverter(cb.getConDate());
						if(d==null)
							return "Date is not syntax error Kindly provide a date in a given format";
					}
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
					System.out.println("Updating data where sl no is "+cb.getCon_sl_no());
					ps_upd = con.prepareStatement("update CONTACT set name=? , dob=?, gender=?, cr_dt=?  WHERE sl_no = '"+cb.getCon_sl_no()+"'");
					ps_upd.setString(1, cb.getConName());
					ps_upd.setDate(2, (java.sql.Date) d);
					ps_upd.setString(3, cb.getConGender());
					ps_upd.setDate(4, (java.sql.Date) DateHelper.createhsqlDate());
					ps_upd.execute();
					
					
										
					ps_upd = con.prepareStatement("update CONT_EMAILS set Emails=? WHERE cont_sl_no = '"+cb.getCon_sl_no()+"'");
					ps_upd.setString(1, cb.getConEmails());
					ps_upd.execute();
					
					ps_upd = con.prepareStatement("update CON_PHONENO set phoneno=? WHERE cont_sl_no = '"+cb.getCon_sl_no()+"'");
					ps_upd.setString(1, cb.getConPhoneNos());
					ps_upd.execute();
					
					ps_upd = con.prepareStatement("update CON_TAGS set Tags=? WHERE cont_sl_no = '"+cb.getCon_sl_no()+"'");
					ps_upd.setString(1, cb.getConTags());
					ps_upd.execute();
					
					return Constants.SUCCESS;
				}			
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "<h3>Ooops</h3> Some problem has been occured [ dueto this = "+e.getMessage()+ " ]";
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps_sel);
				JDBCHelper.close(ps_ins);
			}		
		}else
		{
			return result;
		}		
	}

	
	
	public String deleteContact(String Con_sl_no)
	{
		System.out.println(" inside of Model---> updateContact method--------> "+Con_sl_no);
					
			Connection con = null;
			PreparedStatement ps_del=null,ps_del1=null,ps_del2=null,ps_del3=null;
			
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
				return "DB Connection has been failed Kindly contact your admin ";
				else
				{
					
					System.out.println("deleting data where sl no is "+Con_sl_no);
					ps_del = con.prepareStatement("delete from CONT_EMAILS WHERE cont_sl_no = '"+Con_sl_no+"'");
					ps_del.execute();
					
					ps_del1 = con.prepareStatement("delete from CON_PHONENO WHERE cont_sl_no = '"+Con_sl_no+"'");
					ps_del1.execute();
					
					ps_del2 = con.prepareStatement("delete from CON_TAGS WHERE cont_sl_no = '"+Con_sl_no+"'");
					ps_del2.execute();
					
					System.out.println("deleting data where sl no is "+Con_sl_no);
					ps_del3 = con.prepareStatement("delete from contact WHERE sl_no = '"+Con_sl_no+"'");
					ps_del3.execute();
					return Constants.SUCCESS;
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "<h3>Ooops</h3> Some problem has been occured [ dueto this = "+e.getMessage()+ " ]";
			}
			finally
			{
				JDBCHelper.close(con);
				JDBCHelper.close(ps_del);
				JDBCHelper.close(ps_del1);
				JDBCHelper.close(ps_del2);
				JDBCHelper.close(ps_del3);
			}
	}
	
	
	public ArrayList<ContactBean> searchContact(String useremail,String Searchby,String searchvalue  )
	{
		System.out.println(" inside of Model---> Search Contact method--------> ");
		System.out.println("user mail is "+useremail+" Serchby is "+Searchby+" Search value is "+searchvalue);
		List<ContactBean> list=listContacts(useremail);
		ArrayList<ContactBean> al= (ArrayList<ContactBean>)list;
		ArrayList<ContactBean> Searchedlist =new ArrayList<ContactBean>();
		
		
		if(Searchby.contains("Name"))
		{
			for(ContactBean cb:list)
			{
				if(cb.getConName().contains(searchvalue))
				{
					Searchedlist.add(cb);
				}
			}
			return Searchedlist;
		}
		
		if(Searchby.contains("Birth"))
		{
			for(ContactBean cb:list)
			{
				if(cb.getConDate().contains(searchvalue))
				{
					Searchedlist.add(cb);
				}
			}
			return Searchedlist;
		}
		
		if(Searchby.contains("Gender"))
		{
			for(ContactBean cb:list)
			{
				if(cb.getConGender().contains(searchvalue))
				{
					Searchedlist.add(cb);
				}
			}
			return Searchedlist;
		}
		
		if(Searchby.contains("Created"))
		{
			for(ContactBean cb:list)
			{
				if(cb.getConCreatedDate().contains(searchvalue))
				{
					Searchedlist.add(cb);
				}
			}
			return Searchedlist;
		}
		
		
		if(Searchby.contains("Email"))
		{
			for(ContactBean cb:list)
			{
				if(cb.getConEmails().contains(searchvalue))
				{
					Searchedlist.add(cb);
				}
			}
			return Searchedlist;
		}
		
		if(Searchby.contains("Phoneno"))
		{
			for(ContactBean cb:list)
			{
				if(cb.getConPhoneNos().contains(searchvalue))
				{
					Searchedlist.add(cb);
				}
			}
			return Searchedlist;
		}
		
		
		if(Searchby.contains("Tag"))
		{
			for(ContactBean cb:list)
			{
				if(cb.getConTags().contains(searchvalue))
				{
					Searchedlist.add(cb);
				}
			}
			return Searchedlist;
		}
		return Searchedlist;
			
	}
	
	
	public String checkSearchContact(ArrayList<ContactBean> cb)
	{
		System.out.println("inside of checkSearchContact(ArrayList<ContactBean> cb) method "+cb );
		if(cb.size()>0)
		{
			return Constants.SUCCESS;
		}else
		{
			return "<h1>Ooops</h1> <br> <h3> Given value is not matching in any contact</h3>" ;
		}
	}
	
	
	public ArrayList<ContactBean> birthDayreminder(String email)
	{
		System.out.println("inside birthDayreminder ");
		List<ContactBean> al=listContacts(email);
		ArrayList<ContactBean> birthlist= new ArrayList<ContactBean>();
		
		for(ContactBean cb:al)
		{
			String Condate=cb.getConDate();
			
			ArrayList<String> listOfDays=DateHelper.birthdaycalculator(Condate);
			System.out.println("List of Days for the Condate "+Condate+"======="+listOfDays);
			if(listOfDays!=null)
			{
				String alterdate=Condate.substring(0, 5);
				two:for(String s:listOfDays)
				{
					if(s.contains(alterdate))
					{
						System.out.println("Adding to birthday list of MODEL "+cb);
						birthlist.add(cb);
						break two;
					}
				}
			}
					
		}
		return birthlist;
	}
	
	
	public String checkbirthdaylist(ArrayList<ContactBean> cb)
	{
		System.out.println("inside of checkbirthdaylist(ArrayList<ContactBean> cb) method "+cb );
		if(cb.size()>0)
		{
			return Constants.SUCCESS;
		}else
		{
			return "<h3> There is no birthday in this week </h3>" ;
		}
	}
}

	
