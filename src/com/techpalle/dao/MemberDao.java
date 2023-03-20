package com.techpalle.dao;

import java.sql.*;
import java.util.ArrayList;

import com.techpalle.model.Member;

public class MemberDao {
	
	private static final String dbUrl = "jdbc:mysql://localhost:3306/member_management";
	private static final String dbUsername = "root";
	private static final String dbPassword = "admin";
	
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static Statement stm = null;
	private static ResultSet rs = null;
	
	private static final String memberListQuery = "select * from member";
	private static final String memberInsertQuery = "insert into member(name, email,mobile) values(?, ?, ?)";
	private static final String memberEditQuery = "select * from member where id = ?";
	private static final String memberUpdateQuery = "update member set name=?, email=?, mobile=? where id=?";
	private static final String memberDeleteQuery = "delete from member where id=?";
	
	//Method for deleting a row from member list
	public static void deleteMember(int id) {
		try {
			con = getConnectionDef();
			
			ps = con.prepareStatement(memberDeleteQuery);
			ps.setInt(1, id);
			 ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(ps !=null) {
				try {
					ps.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con !=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	//Method for editing the details of an existing member
	public static void editMember(Member m) {
		try {
			con = getConnectionDef();
			
			ps = con.prepareStatement(memberUpdateQuery);
			ps.setString(1, m.getName());
			ps.setString(2, m.getEmail());
			ps.setLong(3, m.getMobile());
			ps.setInt(4, m.getId());
			
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(ps !=null) {
				try {
					ps.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con !=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//Method for getting details of an existing member
	public static Member getOneMember(int id) {
		Member m = null;
		try {
			con = getConnectionDef();
			
			ps = con.prepareStatement(memberEditQuery);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			int i = rs.getInt("id");
			String n = rs.getString("name");
			String e = rs.getString("email");
			long mob = rs.getLong("mobile");
			
			m = new Member(i, n, e, mob);
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs !=null) {
				try {
					rs.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps !=null) {
				try {
					ps.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con !=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return m;
	}
	
	//Method for adding details of a new member
	public static void addMember(Member member) {
		try {
			con = getConnectionDef();
			
			ps = con.prepareStatement(memberInsertQuery);
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setLong(3, member.getMobile());
			
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(ps !=null) {
				try {
					ps.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con !=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
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
	
	//Method for getting all the details of all existing members in the form of a collection
	public static ArrayList<Member> getAllMembers() {
		ArrayList<Member> al = new ArrayList<Member>();
		try {
			con = getConnectionDef();
			stm = con.createStatement();
			
			rs = stm.executeQuery(memberListQuery);
			
			while(rs.next()) {
				int i = rs.getInt("id");
				String n = rs.getString("name");
				String e = rs.getString("email");
				Long mob = rs.getLong("mobile");
				
				Member m = new Member(i, n, e, mob);
				al.add(m);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs !=null) {
				try {
					rs.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stm !=null) {
				try {
					stm.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con !=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return al;
	}
}
