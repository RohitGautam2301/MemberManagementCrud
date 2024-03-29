package com.techpalle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.AdminDao;
import com.techpalle.dao.MemberDao;
import com.techpalle.model.Member;


@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		//Calling different methods based on the form actions generated
		switch(path) {
		case "/adminLogin":
			verifyAdmin(request, response);
			break;
		case "/list":
			getCustomerListPage(request, response);
			break;
		case "/delete":
			deleteMember(request,response);
			break;	
		case "/edit":
			editMember(request,response);
			break;
		case "/editForm":
			getEditForm(request,response);
			break;
		case "/insertForm":
			getInsertForm(request,response);
			break;
		case "/add":
			addMember(request,response);
			break;
		default:
			getStartUpPage(request,response);
			break;
		}
	}

	
	private void verifyAdmin(HttpServletRequest request, HttpServletResponse response) {
		//Reading the data from admin-login page
		String u = request.getParameter("tbUsername");
		String p = request.getParameter("tbPassword");
		
		//Calling the method to verify the admin credentials
		boolean b = AdminDao.verifyAdmin(u, p);
		
		//Redirecting user to appropriate jsp page based on the boolean value returned
		if(b) {
			getCustomerListPage(request, response);
		}
		else {
			getStartUpPage(request,response);
		}
		
	}


	//Redirect user to admin-login page
	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher rd = request.getRequestDispatcher("admin-login.jsp");
			rd.forward(request, response);
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ServletException e) {
			e.printStackTrace();
		}
		
	}


	private void deleteMember(HttpServletRequest request, HttpServletResponse response) {
		//Read the id from url
		int i = Integer.parseInt(request.getParameter("id"));
		
		//Delete the details of a customer based on the given id
		MemberDao.deleteMember(i);
		
		//Redirect user to member-list page
		getCustomerListPage(request, response);
	}


	private void editMember(HttpServletRequest request, HttpServletResponse response) {
		//Read the data from member-form page
		int i = Integer.parseInt(request.getParameter("tbId"));
		String  n = request.getParameter("tbName");
		String  e = request.getParameter("tbEmail");
		Long mob = Long.parseLong(request.getParameter("tbMobile"));
		
		//Create an object with the above values
		Member m = new Member(i, n, e, mob);
		
		//Edit the details of an existing member in DB
		MemberDao.editMember(m);
		
		//Redirect admin to member-list page
		getCustomerListPage(request, response);
	}


	private void getEditForm(HttpServletRequest request, HttpServletResponse response) {
		//Fetch the id from url:
		int i = Integer.parseInt(request.getParameter("id"));
		
		//Fetch the details of an existing member in the form of an object
		Member c = MemberDao.getOneMember(i);
		
		//Redirect admin to member-form and set attribute
		try {
			RequestDispatcher rd = request.getRequestDispatcher("member-form.jsp");
			request.setAttribute("member", c);
			rd.forward(request, response);
		} 
		catch (ServletException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void getInsertForm(HttpServletRequest request, HttpServletResponse response) {
		//Redirect admin to member-form
		try {
			RequestDispatcher rd = request.getRequestDispatcher("member-form.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void addMember(HttpServletRequest request, HttpServletResponse response) {
		//Reading data from member-form Page
		String n = request.getParameter("tbName");
		String e = request.getParameter("tbEmail");
		long mob = Long.parseLong(request.getParameter("tbMobile"));
		
		//Store the admin given data into model/Object
		Member m = new Member(n, e, mob);
		
		//Insert the details of a new member in DB
		MemberDao.addMember(m);

		//Redirect admin to member-list page
		getCustomerListPage(request,response);
	}


	private void getCustomerListPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			//Calling method to get the details of all members and storing the details in collection
			ArrayList<Member> alMember = MemberDao.getAllMembers();
			
			//Redirecting admin to member-list page and setting attribute
			RequestDispatcher rd = request.getRequestDispatcher("member-list.jsp");
			request.setAttribute("al", alMember);
			rd.forward(request, response);
		} 
		catch (ServletException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
