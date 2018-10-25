package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public static String Name;
	private static String email;
	private static String role;
	private static String department;
	private static String approver;
	public static String taskID;
	/**
	 * @return 
	 * @see HttpServlet#HttpServlet()
	 */

	public static void setParameters(HttpServletRequest request){
		ID = request.getParameter("EmployeeID");
		Name = request.getParameter("EmployeeName");
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
 try{
		// Setting update query
		String updateQuery = "UPDATE users set ROLE= ? , Department= ? , Approver= ? where EmployeeName= ?";
		System.out.println(updateQuery);
		
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
		preparedStatement.close();
		
 }
 finally{
		dbconnection.close();
		System.out.println("Connection close 1------------->");
		System.out.println("In Finally Block 1------------>");
 } 
		// Request dispatcher
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Admin/UpdateUsers.jsp");
		requestDispatcher.include(request, response);
		PrintWriter out = response.getWriter();
		  out.println("<h4 style='color:red;margin-left:400px;margin-top:-120px;'>Updated Successfully...</h4>");

	}

	public EditUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Name = request.getParameter("EmpName");
		System.out.println(Name);

		Statement st=null;
		Connection con = null;
		con = DBConnection.createConnection();
		System.out.println("connected 2.....");
		String query = "SELECT * FROM users WHERE EmployeeName = '" +Name+"'";
		System.out.println(query);
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String EmployeeID = null,EmployeeName = null,EMAIL = null,PASSWORD = null,ROLE = null,Department=null,Approver=null;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				EmployeeID =rs.getString(1);
				EmployeeName =rs.getString(2);
				EMAIL =rs.getString(3);
				PASSWORD =rs.getString(4);
				ROLE =rs.getString(5);
				Department =rs.getString(6);
				Approver =rs.getString(7);
            }
			System.out.println(EmployeeName);
			 request.setAttribute("EmployeeID", EmployeeID);
			 request.setAttribute("EmployeeName", EmployeeName);
			 request.setAttribute("EMAIL", EMAIL);
			 request.setAttribute("PASSWORD", PASSWORD);
			 request.setAttribute("ROLE", ROLE);
			 request.setAttribute("Department", Department);
			 request.setAttribute("Approver", Approver);
			 
			 RequestDispatcher view = request.getRequestDispatcher("/Admin/EditUser.jsp");
             view.include(request, response);
             st.close();
             
           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
				System.out.println("Connection close 2------------->");
				System.out.println("In Finally Block 2------------>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
