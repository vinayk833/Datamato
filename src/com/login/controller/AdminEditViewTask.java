package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

/**
 * Servlet implementation class AdminEditViewTask
 */
@WebServlet("/AdminEditViewTask")
public class AdminEditViewTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private static String TaskID;
	private static String EmployeeID;
	private static String Date;
	private static String ProjectName;
	private static String ProjectID;
	private static String TaskCategory;
	private static String Description;
	private static String Hours;
	//static String reformattedStr = null;
    public AdminEditViewTask() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void setParameters(HttpServletRequest request){
		TaskID = request.getParameter("taskId");
		EmployeeID = request.getParameter("EmployeeId");
		Date = request.getParameter("date");
		ProjectName = request.getParameter("proname");
		ProjectID = request.getParameter("proid");
		TaskCategory = request.getParameter("TaskCat");
		Description = request.getParameter("description");
		Hours = request.getParameter("hour");
		/*SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {

		    reformattedStr = myFormat.format(fromUser.parse(Date));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
*/
	}

	public static void updateQuery (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{

		Connection dbconnection = null;
		dbconnection = DBConnection.createConnection();

		// Printing out Connection
		System.out.println("Connection------------->" + dbconnection);

		// Setting update query
		String updateQuery = "UPDATE task  set date=?, ProjName= ? , proid= ?, TaskCat= ? ,"
				+ "description= ? , hours= ? where taskId= ?";
		
		// Setting up Prepared Statement
		PreparedStatement preparedStatement = (PreparedStatement) dbconnection.prepareStatement(updateQuery);
		
		//Passing parameters for Prepared Statement
		preparedStatement.setString(1,Date);
		preparedStatement.setString(2,ProjectName);
		preparedStatement.setString(3,ProjectID);
		preparedStatement.setString(4,TaskCategory);
		preparedStatement.setString(5,Description);
		preparedStatement.setString(6,Hours);
		preparedStatement.setString(7,TaskID);
		
		
		System.out.println(preparedStatement);
		
		// Execute update SQL statement
		preparedStatement.executeUpdate();
		
		// Closing DB connection
		preparedStatement.close();
		dbconnection.close();
		
		// Request dispatcher
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Admin/ViewTask.jsp");
		requestDispatcher.include(request, response);
		PrintWriter out = response.getWriter();
		  out.println("<h4 style='color:red;margin-left:400px;margin-top:-150px;'>Updated Successfully...</h4>");

	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside Edit view Task");
		// Setter method to initialize the Attribute Values
		setParameters(request);
		
		//Printing the Values for Debug check
		System.out.println(TaskID + "\n" + EmployeeID + "\n" + Date 
				+ "\n" + ProjectName + "\n" + ProjectID + "\n" + TaskCategory
				+ "\n" + Description + "\n" + Hours);

		// Calling Update Method
		try {
			updateQuery(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
