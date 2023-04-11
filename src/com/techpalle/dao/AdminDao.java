package com.techpalle.dao;

import java.sql.*;

public class AdminDao {
	private static final String dbUrl = "jdbc:mysql://localhost:3306/member_management";
	private static final String dbUsername = "root";
	private static final String dbPassword = "admin";
	
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private static final String adminVerifyQuery = "select * from admin where username = ? and password = ?";
	
	//Method for loading and registering the Driver class
	public static Connection getConnectionDef() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}	
	
	//Method for getting the details of an existing admin and based on that return a boolean value
	public static boolean verifyAdmin(String u, String p) {
		boolean b = false;
		try {
			con = getConnectionDef();

			ps = con.prepareStatement(adminVerifyQuery);
			ps.setString(1, u);
			ps.setString(2, p);
			
			rs = ps.executeQuery();
			
			b = rs.next();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}
		return b;
	}
	
}
