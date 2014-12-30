
package com.uttara.contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	/* 	This is a Servlet Class. Used as Controller for the ContactApp.
	 *	This Class is managed by the webserver. In this Application we have used
	 *	Tomcat 7.0.5.6 version of WebServer.
	 *	This Controller has built by Using all java best Practices.
	 *	It has Centralized Exception handling.
	 *
	 *	When a request comes to an this server it call's one of doXXX methods, 
	 *	by keeping this is in mind one more method is built & called method in all doXXX methods
	 *	and Passed inputs as it is to an another method. Named as Process Method.
	 */




/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * This is an default Constructor of Servlet.
     */
	
	
    public ControllerServlet() {
        super();
        System.out.println(" inside constructor of ControllerServlet of Contact App");        
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	System.out.println("inside overriden init method");
    }
    
    @Override
    public void destroy() {
        	super.destroy();
        	System.out.println(" inside of overriden destroy method ");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" inside of doGet Method");
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" inside of doPost Method");
		process(request, response);
	}

    
		/*In this method every is verified and forwarded to corresponding views according to problem domain  */
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		System.out.println(" inside of Process method");
		String uri=request.getRequestURI();
		System.out.println(uri);
		ContactModel cm= new ContactModel();
		RequestDispatcher rd= null;
		HttpSession s=null;
		String result=null;
	try
	{
		
		/*	
		 * 	Whenever request comes for Registration, the Controller provides 
		 * 	Registration view using RequestDispatcher it forwards to 
		 * 	Register.jsp
		 * */
		if(uri.contains("/openRegisterView"))
		{
			
			System.out.println("inside of if block of "+uri);
			rd=request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);
			System.out.println("request is forworded to Register.jsp");
		}
		
		/*	whenever user fills the inputs in Register form & submits to the Controller 
		 * 	the control comes to here & all entered inputs are accessed here 
		 *  passed to an Model's method to verify the email add security code 
		 *  is generated and send to an entered email this is to make sure about 
		 *  user email id Correct.
		 *  */
		if(uri.contains("/openVerifyRegisterEmail"))
		{
			System.out.println("inside of if block of "+uri);
			RegBean rb=(RegBean) request.getAttribute("reg");
			System.out.println(rb);
			System.out.println("Email ID is "+rb.getEmail());
			result=cm.verifyEmail(rb);
			System.out.println("Return by verifyEmail method is "+result);
			if(result.equals(Constants.SUCCESS))
			{
				System.out.println("Verfication Code is Sent to the Email");
				ServletContext sc =getServletContext();
				sc.setAttribute("Bean", rb);
				rd = request.getRequestDispatcher("VerifyEmail.jsp");
				rd.forward(request, response);
				System.out.println("Registration is succeeded, forwording to Success.jsp ----> (this) forwords to RegInt.jsp & attribute is reg which is RegBena Object ");
			}else
			{
				System.out.println("registration failed");
				request.setAttribute("errorMessage", result);
				rd=request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
				System.out.println("Registration is failed, forwording to Register.jsp [errorMessage is attribute]");
			}			
		}
		
		if(uri.contains("/register"))
		{
			System.out.println("inside of if block of "+uri);
			String userCode=(String)request.getParameter("userCode");
			System.out.println("User Entered the Code "+userCode);			
			RegBean rb=(RegBean)getServletContext().getAttribute("Bean");
			System.out.println("passing regbean obj ref to ContactModel.register()");
			result=cm.register(rb,userCode);
			System.out.println("Return by cm.register(rb) is "+result);
			if(result.equals(Constants.SUCCESS))
			{
				System.out.println("registration is succeeded[ inside register block ]");
				request.setAttribute("message", "Registration has succeeded! You can now login!!");
				rd = request.getRequestDispatcher("Success.jsp");
				rd.forward(request, response);
				System.out.println("Registration is succeeded, forwording to Success.jsp ----> (this) forwords to RegInt.jsp & attribute is reg which is RegBena Object ");
			}else
			{
				System.out.println("registration failed");
				request.setAttribute("errorMessage", result);
				rd=request.getRequestDispatcher("VerifyEmail.jsp");
				rd.forward(request, response);
				System.out.println("Registration is failed, forwording to VerifyEmail.jsp [errorMessage is attribute]");
			}		
		}
		
		if(uri.contains("/viewRegisteredUsers"))
		{
			System.out.println(" request came for "+uri );
			try
			{
				List<RegBean> al= cm.getUsers();
				System.out.println("Return by m.getUsers is "+al);
				rd=request.getRequestDispatcher("RegisteredUsers.jsp");
				request.setAttribute("regUsers", al);
				rd.forward(request, response);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				rd=request.getRequestDispatcher("Error.jsp");
				request.setAttribute("errorMessage", e.getMessage());
				rd.forward(request, response);
			}
		}
		
		if(uri.contains("/openLoginView"))
		{
			System.out.println("inside of if block of "+uri);
			rd=request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
			System.out.println("request is forworded to Login.jsp ----> (this) forwords to LogInt.jsp & attribute is log which is LogBena Object ");			
		}
		
		if(uri.contains("/login"))
		{
			System.out.println("inside of if block of "+uri);
			LogBean lb= (LogBean)request.getAttribute("log");
			System.out.println(lb);			
			System.out.println("passing LogBean obj ref to ContactModel.register()");
			result=cm.login(lb);			
			System.out.println("Return by login method is "+result);
			
			if(result.equals(Constants.SUCCESS))
			{
				System.out.println("Login is succeeded [ inside login block ]");
				request.setAttribute("message", "You have Logged in Successfully!!! ");
				s= request.getSession(true);
				s.setAttribute("USER", lb);
				System.out.println("Session is creted for this user & USER is stored for Referance "+lb.getEmail());
				rd = request.getRequestDispatcher("Menu.jsp");
				rd.forward(request, response);
				System.out.println("request is forworded to Menu.jsp ");
			}
			else
			{
				System.out.println("Login is failed");
				request.setAttribute("errorMessage", result);
				rd=request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
				System.out.println("request is forworded to Login.jsp with errorMessage Message");							
			}
		}
		
		
		if(uri.contains("/openResetPasswordView"))
		{
			System.out.println("inside of if block of "+uri);
			System.out.println("Forwording to ResetPassword View.jsp");
			rd = request.getRequestDispatcher("ResetPassword.jsp");
			rd.forward(request, response);
			System.out.println("request is forworded to ResetPassword.jsp ");
		}
		
		if(uri.contains("/resetPassword"))
		{
			System.out.println("inside of if block of "+uri);
			String email=(String)request.getParameter("resetPassword");
			System.out.println("User Entered the Code "+email);
			result=cm.sendCodetoResetPassword(email);			
			System.out.println("Return by login method is "+result);
			
			if(result.equals(Constants.SUCCESS))
			{
				System.out.println("Email is sent Successfully");
				rd = request.getRequestDispatcher("VerifyPasswordCode.jsp");
				rd.forward(request, response);
				System.out.println("request is forworded to VerifyPasswordCode.jsp ");
			}
			else
			{
				System.out.println("Email is not Sent, Some problem occured ");
				request.setAttribute("errorMessage", result);
				rd=request.getRequestDispatcher("ResetPassword.jsp");
				rd.forward(request, response);
				System.out.println("request is forworded to ResetPassword.jsp with errorMessage Message");							
			}
		}
		
		
		if(uri.contains("/openVerifyPasswordCode"))
		{
			System.out.println("inside of if block of "+uri);
			PasswordRecoveryBean passRecBean=(PasswordRecoveryBean)request.getAttribute("passwordRecoveryBean");
			System.out.println("PasswordRecoveryBean is "+passRecBean );			
			result=cm.resetPassword(passRecBean);
			System.out.println("Return by cm.register(rb) is "+result);
			if(result.equals(Constants.SUCCESS))
			{
				System.out.println("registration is succeeded[ inside register block ]");
				request.setAttribute("message", "Password is Successfully resetted to RESET. You can now login!! <br> Kindly Reset the password after Logging");
				rd = request.getRequestDispatcher("Success.jsp");
				rd.forward(request, response);
				System.out.println("Password is Successfully resetted, forwording to Success.jsp");
			}else
			{
				System.out.println("registration failed");
				request.setAttribute("errorMessage", result);
				rd=request.getRequestDispatcher("VerifyEmail.jsp");
				rd.forward(request, response);
				System.out.println("Registration is failed, forwording to VerifyEmail.jsp [errorMessage is attribute]");
			}		
		}
		
		if(uri.contains("/Logout"))
		{
			System.out.println(" request came for "+uri );
			s=request.getSession(false);
			s.removeAttribute("USER");
			System.out.println("Removing attributes RegBean & USER(LogBean)");
			s.invalidate();
			System.out.println("invalidating session");
			request.setAttribute("message", "<h1>Success</h1><br><h1><i>Logged out Successfully</i></h1>");
			rd=request.getRequestDispatcher("Logout.jsp");
			System.out.println("forwording to Logout.jsp");
			rd.forward(request, response);
		}			
			
		
		if(uri.contains("/openEditAccountView"))
		{
			System.out.println(" request came for "+uri );
			LogBean user=null;
			
			System.out.println("Accessing data via session from existing session");
			s= request.getSession(false);			
			System.out.println("Accessing logbean which is saved in session ");
			user=(LogBean) s.getAttribute("USER");	
			System.out.println("Present user is "+user.getEmail());
			System.out.println("Getting the RegBean from model based on emailID ");
			RegBean rb=cm.authentication(user.getEmail());
			System.out.println("Current User data is "+rb.getUname());
			if(rb!=null)
			{
				System.out.println(" RegBean is matched with LogBean (Email)");
				System.out.println(" Saving the RegBean in Session as RegBean attribute ");
				s.setAttribute("RegBean", rb);
				rd=request.getRequestDispatcher("EditAccount.jsp");				
				System.out.println("forwording to EditAccount.jsp");
				rd.forward(request, response);
			}
		}
		
		if(uri.contains("/updateRegister"))
		{
			System.out.println(" request came for "+uri );
			
			s= request.getSession(false);			
			RegBean rb=(RegBean) request.getAttribute("updateregbean");
			System.out.println(rb);
			result=cm.updateRegister(rb);
			if(result.equals(Constants.SUCCESS))
			{
				System.out.println("Updated Successfully");
				request.setAttribute("message", "Details modified Successfully");
				rd=request.getRequestDispatcher("SuccessUpdate.jsp");
				System.out.println("Forwording to SuccessUpdate.jsp");
				rd.forward(request, response);
			}else
		    {
				System.out.println("Update failed");
				request.setAttribute("errorMessage", result);
				rd=request.getRequestDispatcher("EditAccount.jsp");
				System.out.println("Forwording to EditAccount.jsp");
				rd.forward(request, response);	
			}			
		}
		
		if(uri.contains("/openAddContactView"))
		{
			System.out.println(" request came for "+uri );
			s= request.getSession(false);			
			System.out.println(" request came for "+uri );
			rd=request.getRequestDispatcher("AddContact.jsp");
			rd.forward(request, response);
			System.out.println("request is forworded to AddContact.jsp");			
		}
		
		if(uri.contains("/AddContact"))
		{			
			System.out.println(" request came for "+uri );
			ContactBean conbean = (ContactBean) request.getAttribute("conBean");
			String email="";
			s=request.getSession(false);
			LogBean lb=(LogBean)s.getAttribute("USER");
			email=lb.getEmail();
			System.out.println(conbean + " And the User is "+email);
			result=cm.addContact(conbean,email);
			if(result.equals(Constants.SUCCESS))
			{
				System.out.println("AddContact Successfully completed");
				request.setAttribute("message", "Contacts are added Successfully");
				rd=request.getRequestDispatcher("SuccessUpdate.jsp");
				System.out.println("Forwording to SuccessUpdate.jsp");
				rd.forward(request, response);
			}else
			{
				System.out.println("AddContacat failed");
				request.setAttribute("errorMessage", result);
				rd=request.getRequestDispatcher("AddContact.jsp");
				System.out.println("Forwording to AddContact.jsp");
				rd.forward(request, response);
			}			
		}
		
		
		if(uri.contains("/openListContactView"))
		{
			System.out.println(" request came for "+uri );
			ContactBean conbean = (ContactBean) request.getAttribute("conBean");
			String email="";
			s=request.getSession(false);
			LogBean lb=(LogBean)s.getAttribute("USER");
			String useremail=lb.getEmail();
			List<ContactBean> al=cm.listContacts(useremail);
			request.setAttribute("ListCont", al);
			rd=request.getRequestDispatcher("ListContacts.jsp");
			rd.forward(request, response);
			System.out.println("List<ContactBean> is added to the request and sent to ListContacts.jsp");			
		}
		
		
		
		if(uri.contains("/openEditContactView"))
		{
			System.out.println(" request came for "+uri );
			s=request.getSession(false);
			LogBean lb=(LogBean)s.getAttribute("USER");
			String useremail=lb.getEmail();	
			System.out.println("User email is "+useremail);
			String Con_sl_no =(String) request.getParameter("Con_sl_no");
			System.out.println("User Con_sl_no is "+Con_sl_no);
			ContactBean cb=cm.currentContactBean(Con_sl_no, useremail);
			System.out.println("Return by cm.currentContactBean(Con_sl_no, useremail) is "+cb);
			s.setAttribute("ContactBean", cb);
			s.setAttribute("Con_sl_no", Con_sl_no);
			rd=request.getRequestDispatcher("EditContact.jsp");
			rd.forward(request, response);
			System.out.println("List<ContactBean> is added to the request and sent to EditContact.jsp");			
		}
		
		if(uri.contains("/EditContact"))
		{
			System.out.println(" request came for "+uri );
			s=request.getSession(false);
			ContactBean cb=(ContactBean) request.getAttribute("editConBean");
			System.out.println("Edited ContactBean is "+cb);
			cb.setCon_sl_no((String)s.getAttribute("Con_sl_no"));
			System.out.println("Sending the obj ref to cm.updateContact method");
			result=cm.updateContact(cb);
			System.out.println("Return by cm.updateContact is "+result);
			if(result.equals(Constants.SUCCESS))
			{
				System.out.println("Updated Successfully");
				request.setAttribute("message", "Details modified Successfully");
				rd=request.getRequestDispatcher("SuccessUpdate.jsp");
				System.out.println("Forwording to SuccessUpdate.jsp");
				rd.forward(request, response);
			}else
			{
				System.out.println("Update failed");
				request.setAttribute("errorMessage", result);
				rd=request.getRequestDispatcher("EditContact.jsp");
				System.out.println("Forwording to EditContact.jsp");
				rd.forward(request, response);	
			}
		}
		
		
		if(uri.contains("/openDeleteContactView"))
		{
			System.out.println(" request came for "+uri );
			s=request.getSession(false);		
			LogBean lb=(LogBean)s.getAttribute("USER");
			String useremail=lb.getEmail();	
			System.out.println("User email is "+useremail);
			String Con_sl_no =(String) request.getParameter("Con_sl_no");
			System.out.println("User Con_sl_no is "+Con_sl_no);
			result=cm.deleteContact(Con_sl_no);
			System.out.println("Return by cm.deleteContact is "+result);
			if(result.equals(Constants.SUCCESS))
			{
				System.out.println("Deleted Successfully");
				request.setAttribute("message", "Contact has been deleted Successfully");
				rd=request.getRequestDispatcher("SuccessUpdate.jsp");
				System.out.println("Forwording to SuccessUpdate.jsp");
				rd.forward(request, response);
				
			}else
			{
				System.out.println("Deleting contact is failed");
				request.setAttribute("errorMessage", result);
				rd=request.getRequestDispatcher("ListContacts.jsp");
				System.out.println("Forwording to ListContacts.jsp");
				rd.forward(request, response);
			}			
		}
		
		if(uri.contains("/openSearchView"))
		{
			System.out.println(" request came for "+uri );
			
			s=request.getSession(false);
			
				rd=request.getRequestDispatcher("SearchContact.jsp");
				rd.forward(request, response);
				System.out.println("forwording to SearchContacts.jsp");			
		}
		
		
		
		if(uri.contains("/SearchContact"))
		{
			System.out.println(" request came for "+uri );
			ContactBean conbean = (ContactBean) request.getAttribute("conBean");
			String email="";
			s=request.getSession(false);
			LogBean lb=(LogBean)s.getAttribute("USER");
			String useremail=lb.getEmail();
			System.out.println("Emailid  is "+useremail);
			String Searchby=request.getParameter("Searchby");
			System.out.println("Search by  "+Searchby);
			String searchvalue=request.getParameter("searchvalue");
			System.out.println("SearchValue by  "+searchvalue);
			ArrayList<ContactBean> searched=cm.searchContact(useremail, Searchby, searchvalue);
			System.out.println("Searched ArrayList<ContactBean> is "+searched);
			result=cm.checkSearchContact(searched);
			if(result.equals(Constants.SUCCESS))
			{
				System.out.println("Contact are present ");
				request.setAttribute("searched", searched);
				rd=request.getRequestDispatcher("SearchContact.jsp");
					rd.forward(request, response);
					System.out.println("forwording to SearchContacts.jsp");
				}else
				{
					System.out.println("Contact are not present");
					request.setAttribute("errorMessage", result);
					rd=request.getRequestDispatcher("SearchContact.jsp");
					System.out.println("Forwording to SearchContact.jsp");
					rd.forward(request, response);
				}		
			}
		
		
		if(uri.contains("/openBirthdayReminderView"))
		{
			System.out.println(" request came for "+uri );
			s=request.getSession(false);
			LogBean lb=(LogBean)s.getAttribute("USER");
			String useremail=lb.getEmail();
			ArrayList<ContactBean> al=cm.birthDayreminder(useremail);
			result = cm.checkbirthdaylist(al);
			if(result.equals(Constants.SUCCESS))
			{
				s.setAttribute("birthlist", al);
				rd=request.getRequestDispatcher("Birthdayreminder.jsp");
				rd.forward(request, response);
			}else
			{
				request.setAttribute("message", "<h1> Sorry !!!!   There is no birthday in this week </h1>");
				rd=request.getRequestDispatcher("Birthdayreminder.jsp");
				rd.forward(request, response);
			}			
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println(e.getMessage());
		rd=request.getRequestDispatcher("Error.jsp");
		request.setAttribute("errorMessage", e.getMessage());
		rd.forward(request, response);
	}
    		
}
    
		
}