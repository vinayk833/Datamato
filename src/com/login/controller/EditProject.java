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


/**
 * Servlet implementation class EditProject
 */
@WebServlet("/EditProject")
public class EditProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String customerName;
	private static String projectId;
	public static String projectName;
	private static String projectDescription;
	private static String projectType;
	private static String productManagers;
	private static String startDate;
	private static String endDate;

	/**
	 * @return 
	 * @see HttpServlet#HttpServlet()
	 */

	public static void setParameters(HttpServletRequest request){
		customerName = request.getParameter("CustName");
		projectId = request.getParameter("id");
		projectName = request.getParameter("PName");
		projectDescription = request.getParameter("Description");
		projectType = request.getParameter("Type");
		productManagers = request.getParameter("ProductManager");
		startDate = request.getParameter("StartDate");
		endDate = request.getParameter("EndDate");

	}

	public static void updateQuery (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{

		Connection dbconnection = null;
		dbconnection = DBConnection.createConnection();

		// Printing out Connection
		System.out.println("Connection------------->" + dbconnection);

		// Setting update query
		String updateQuery = "UPDATE myproject set CustomerName= ? , ID= ? , Description= ?, Type= ? ,"
				+ "ProductManager= ? , StartDate= ? , EndDate= ? where ProjName= ?";
		
		// Setting up Prepared Statement
		PreparedStatement preparedStatement = (PreparedStatement) dbconnection.prepareStatement(updateQuery);
		
		//Passing parameters for Prepared Statement
		preparedStatement.setString(1,customerName);
		preparedStatement.setString(2,projectId);
		preparedStatement.setString(3,projectDescription);
		preparedStatement.setString(4,projectType);
		preparedStatement.setString(5,productManagers);
		preparedStatement.setString(6,startDate);
		preparedStatement.setString(7,endDate);
		preparedStatement.setString(8,projectName);
		
		System.out.println(preparedStatement);
		
		// Execute update SQL statement
		preparedStatement.executeUpdate();
		
		// Closing DB connection
		dbconnection.close();
		
		// Request dispatcher
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Admin/UpdateProjects.jsp");
		requestDispatcher.include(request, response);
		PrintWriter out = response.getWriter();
		  out.println("<h4 style='color:red;margin-left:400px;margin-top:-120px;'>Updated Successfully...</h4>");

	}

	public EditProject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		projectName = request.getParameter("ProjName");
		System.out.println(projectName);
		Statement st=null;
		Connection con = null;
		con = DBConnection.createConnection();
		System.out.println("connected!.....");
		String query = "SELECT * FROM myproject WHERE ProjName = '" + projectName +"'";
		System.out.println(query);
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String custName = null,projId = null,projName=null,tdes = null,type = null,ProductMang = null,startDate=null,endDate=null;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
                custName =rs.getString(1);
                projId =rs.getString(2);
                projName =rs.getString(3);
                tdes =rs.getString(4);
                type =rs.getString(5);
                ProductMang =rs.getString(6);
                startDate =rs.getString(7);
                endDate =rs.getString(8);
                
            }
			System.out.println(tdes);
			 request.setAttribute("custName", custName);
			 request.setAttribute("projId", projId);
			 request.setAttribute("projName", projName);
			 request.setAttribute("tdes", tdes);
			 request.setAttribute("type", type);
			 request.setAttribute("ProductMang", ProductMang);
			 request.setAttribute("startDate", startDate);
			 request.setAttribute("endDate", endDate);
			 
			 RequestDispatcher view = request.getRequestDispatcher("/Admin/EditProject.jsp");
             view.include(request, response);
             con.close();
             System.out.println("Disconnected!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Setter method to initialize the Attribute Values
		setParameters(request);

		// Printing the Values for Debug check
		System.out.println(customerName + "\n" + projectId + "\n" + projectName 
				+ "\n" + projectDescription + "\n" + projectType + "\n" + productManagers
				+ "\n" + startDate + "\n" + endDate);

		// Calling Update Method
		try {
			updateQuery(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
