package com.techpalle.model;

public class Member {
	
	private int id;
	private String name;
	private String email;
	private long mobile;
	
	//Setter and Getter methods for above declared private variables
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	
	// (U)Update Operation and (R)Display Operation
	public Member(int id, String name, String email, long mobile) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
	
	// (C)Insert Operation
	public Member(String name, String email, long mobile) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
	
	// (D)Delete Operation ----> We don't need any constructor.
}
