package com.cg.jdbc;


import java.sql.*;
public class FirstDatabase {
public static void main (String[] args) throws ClassNotFoundException, SQLException
{
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String username="system";
	String password="1234";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	con=DriverManager.getConnection(url,username,password);
	stmt=con.createStatement();
	rs=stmt.executeQuery("Select * from products");
	while(rs.next())
	{
		//System.out.println(" hello mounika");
		System.out.print(" "+rs.getInt("pid"));
		System.out.print(" "+rs.getString("pname"));
		System.out.print(" " +rs.getInt("price"));
		
		System.out.println();
		
	}
	
	
	rs.close();
	con.close();
	stmt.close();
	
}
}