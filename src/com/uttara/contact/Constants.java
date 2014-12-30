package com.uttara.contact;

public interface Constants {

	/*	This interface is used to Hold commonly required Constant Values
	 * 	which Holds the Values of JDBC or DataBase related Constants
	 * 	and also it holds email id of User (who is going to send email by using this mail id)
	 * 	Kindly provide proper email id & Password in-order to authenticate while sending
	 * */
	String SUCCESS = "success";
	String DRIVER = "org.hsqldb.jdbcDriver";
	String URL = "jdbc:hsqldb:hsql://localhost/";
	String UID = "SA";
	String PWD = "";
	String FROMEMAIL="Enter your mail id";
	String EMAILPWD="Enter your password";
}
