package com.cg.jdbc;

//step1
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleSavepoint;

public class TransactionDemo {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement stmt = null;
		Statement stmt1 = null;
		ResultSet rs = null;
		String url = "";
		String username = "";
		String password = "";
		Savepoint spoint = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Step 2

			url = "jdbc:oracle:thin:@localhost:1521:XE"; // type 4
			username = "system";
			password = "1234";

			con = DriverManager.getConnection(url, username, password); // Step 3

			// Disabling auto commit mode
			con.setAutoCommit(false);

			DatabaseMetaData dbmd = con.getMetaData();

			// Check whether Savepoint is supported

			if (dbmd.supportsSavepoints())
				System.out.println("Savepoint is supported");
			else
				System.out.println("Savepoint is not supported");

			System.out.println("Before Transaction .....");
			Statement stmt0 = con.createStatement();
			ResultSet rs1 = stmt0.executeQuery("select * from products");
			while (rs1.next()) {
				System.out.println(rs1.getInt(1) + "    " + rs1.getString(2) + "     " + rs1.getInt(3));
			}

			spoint = con.setSavepoint();
			//-----------------------------Transaction------------------------
			PreparedStatement stmt2=con.prepareStatement("update products set price= ? where pname = ?");
			stmt2.setInt(1,1000);
			stmt2.setString(2,"mouse");
			System.out.println("update1-------------------");
			int r1=stmt2.executeUpdate();
			
			PreparedStatement stmt3=con.prepareStatement("update products set prodname= ? where pname = ?");
			stmt3.setString(1,"laptop");
			stmt3.setString(2,"lapy");
			System.out.println("update2-----------------");
			int r2=stmt3.executeUpdate();
			
			stmt = con.prepareStatement ("delete from products where pid= ?"); 
			stmt.setInt(1,104);	
			int count = stmt.executeUpdate ();
			System.out.println(count);
			//--------------------------------------------------
			
			con.commit();
			
			System.out.println("After Transaction .....");
			 stmt1 = con.createStatement();
			ResultSet rs2 = stmt0.executeQuery("select * from products");
			while (rs2.next()) {
				System.out.println(rs2.getInt(1) + "    " + rs2.getString(2) + "     " + rs2.getInt(3));
			}
			con.close(); // step 6
			stmt0.close();
			rs1.close();

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Rollbacking Transaction .....");
			try {
				con.rollback(spoint);

				System.out.println("After Rollback .....");
				Statement stmt3 = con.createStatement();
				ResultSet rs2 = stmt3.executeQuery("select * from Products");
				while (rs2.next()) {
					System.out.println(rs2.getInt(1) + "    "
							+ rs2.getString(2) + "     " + rs2.getInt(3));
				}

			} catch (SQLException ae) {
				System.out.println("Error " + ae.getMessage());
			}

		} finally {  asp yes

		}

	}

}
