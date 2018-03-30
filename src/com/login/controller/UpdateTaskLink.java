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
 * Servlet implementation class UpdateTaskLink
 */
@WebServlet("/UpdateTaskLink")
public class UpdateTaskLink extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String taskID;
	private static String Date;
	private static String ProjectName;
	private static String ProjectID;
	private static String TaskCategory;
	private static String Description;
	private static String Hours;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	public static void setParameters(HttpServletRequest request){
		Date = request.getParameter("date");
		ProjectName = request.getParameter("proname");
		ProjectID = request.getParameter("proId");
		TaskCategory = request.getParameter("values");
		Description = request.getParameter("description");
		Hours = request.getParameter("hours");
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
		preparedStatement.setString(7,taskID);
		System.out.println(taskID);
		
		
		System.out.println(preparedStatement);
		
		// Execute update SQL statement
		preparedStatement.executeUpdate();
		
		// Closing DB connection
		preparedStatement.close();
		dbconnection.close();
		
		// Request dispatcher
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("Admin/ViewTask.jsp");
		requestDispatcher.include(request, response);
		PrintWriter out = response.getWriter();
		  out.println("<h4 style='color:red;margin-left:400px;margin-top:-120px;'>Updated Successfully...</h4>");

	}

	public UpdateTaskLink() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		taskID = request.getParameter("taskid");
		System.out.println(taskID);

		Statement st=null;
		Connection con = null;
		con = DBConnection.createConnection();
		System.out.println("connected!.....");
		String query = "SELECT * FROM task WHERE taskID =" +taskID;
		System.out.println(query);
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String pname = null,projId = null,tdes = null,hours = null,date = null,taskCat=null;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
                pname =rs.getString(4);
                projId =rs.getString(5);
                taskCat =rs.getString(6);
                tdes =rs.getString(7);
                date =rs.getString(3);
                hours =rs.getString(8);
                
            }
			System.out.println(tdes);
			 request.setAttribute("pname", pname);
			 request.setAttribute("projId", projId);
			 request.setAttribute("taskCat", taskCat);
			 request.setAttribute("tdes", tdes);
			 request.setAttribute("hours", hours);
			 request.setAttribute("date", date);
			 
			 RequestDispatcher view = request.getRequestDispatcher("/Admin/UpdateTask.jsp");
             view.include(request, response);
             rs.close();
             st.close();
             con.close();
             System.out.println("Disconnected!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside Edit view Task");
		// Setter method to initialize the Attribute Values
		setParameters(request);
		
		//Printing the Values for Debug check
		System.out.println(Date + "\n" + ProjectName + "\n" + ProjectID + "\n" + TaskCategory
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
