package com.cg.jdbc;

import java.sql.*;

public class Connection1{
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String username="system";
		String pass="1234";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con = DriverManager.getConnection(url, username, pass);
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from products");
		System.out.println("hello");
		while(rs.next()) {
			System.out.print(" "+rs.getInt(1));
			System.out.print(" "+rs.getString(2));
			System.out.print(" "+rs.getInt(3));
			System.out.println();
		}
		rs.close();
		con.close();
		//stmt.close();
		stmt.close();)
	}
}
