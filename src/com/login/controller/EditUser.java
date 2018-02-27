package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;


@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String ID;
	private static String Name;
	private static String email;
	private static String role;
	private static String department;
	private static String approver;
	
	/**
	 * @return 
	 * @see HttpServlet#HttpServlet()
	 */

	public static void setParameters(HttpServletRequest request){
		ID = request.getParameter("EmployeeID");
		Name = request.getParameter("EmpName");
		email = request.getParameter("EMAIL");
		role = request.getParameter("ROLE");
		department = request.getParameter("Department");
		approver = request.getParameter("Approver");
		System.out.println(department);

	}

	public static void updateQuery (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{

		Connection dbconnection = null;
		dbconnection = DBConnection.createConnection();

		// Printing out Connection
		System.out.println("Connection------------->" + dbconnection);

		// Setting update query
		String updateQuery = "UPDATE users set ROLE= ? , Department= ? , Approver= ? where EmployeeName= ?";
		
		// Setting up Prepared Statement
		PreparedStatement preparedStatement = (PreparedStatement) dbconnection.prepareStatement(updateQuery);
		
		//Passing parameters for Prepared Statement
		
		preparedStatement.setString(1,role);
		preparedStatement.setString(2,department);
		preparedStatement.setString(3,approver);
		preparedStatement.setString(4,Name);
		
		System.out.println(preparedStatement);
		
		// Execute update SQL statement
		preparedStatement.executeUpdate();
		
		// Closing DB connection
		dbconnection.close();
		
		// Request dispatcher
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Admin/UpdateUsers.jsp");
		requestDispatcher.include(request, response);
		PrintWriter out = response.getWriter();
		  out.println("<h4 style='color:red;margin-left:400px;margin-top:-150px;'>Updated Successfully...</h4>");

	}

	public EditUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Setter method to initialize the Attribute Values
		setParameters(request);
		// Printing the Values for Debug check
				System.out.println(ID + "\n"+ Name 
						+ "\n" + email + "\n" + role
						+ "\n" + department + "\n" + approver);

		

		// Calling Update Method
		try {
			updateQuery(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
