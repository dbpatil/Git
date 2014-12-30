package com.uttara.contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class JDBCHelper 
{
	public static void close(ResultSet rs)
	{
		try
		{
				if(rs!=null)
					rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}	
	public static void close(Connection con)
	{
		try
		{
				if(con!=null)
					con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}	
	public static void close(Statement stmt)
	{
		try
		{
				if(stmt!=null)
					stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	

	/****
	 * This method will load the driver, ask the DriverManager to get the connection
	 * and return it to invoker. If connection cannot be established, null will be 
	 * returned.
	 * @return
	 */
	public static Connection getConnection()
	{
		try
		{
			Class.forName(Constants.DRIVER);
			
			Connection con= DriverManager.getConnection(Constants.URL, Constants.UID, Constants.PWD);
			System.out.println(con);
			return con;
			
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}



}
