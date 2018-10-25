package com.timesheet.Manager;

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
 * Servlet implementation class ManagerUpdateTask
 */
@WebServlet("/ManagerUpdateTask")
public class ManagerUpdateTask extends HttpServlet {
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
		
	}

	public static void updateQuery (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String employeeID  = (String) request.getSession().getAttribute("Manager");

		Connection dbconnection = null;
		dbconnection = DBConnection.createConnection();
try{
		// Printing out Connection
		System.out.println("Connection------------->" + dbconnection);
		
		Statement stt = dbconnection.createStatement();
		String q="select ProjName from task where proid='"+ProjectID+"'";
		System.out.println(q);
		ResultSet rs = stt.executeQuery(q);
		Statement st= dbconnection.createStatement();
		while(rs.next()) {
			ProjectName = rs.getString("ProjName");
			System.out.println(ProjectName);
		}
		Float hr = Float.parseFloat(Hours);

		
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
		preparedStatement.setFloat(6, hr);
		preparedStatement.setString(7,taskID);
		System.out.println(taskID);
		
		
		System.out.println(preparedStatement);
		
		// Execute update SQL statement
		preparedStatement.executeUpdate();
	// code added by priyanka
		
		String query= "select hours from task where date='"+Date+"'AND EmployeeId="+employeeID+"";
		System.out.println("Retreive hours from database...."+query);
		float sum1 = 0;
		float sum= 0;
		ResultSet r2= st.executeQuery(query);
		
		while(r2.next()){
			String s = r2.getString("hours");
			System.out.println("hello"+s+"world");
			sum1 = Float.parseFloat(s.trim());
			
			System.out.println("sum1....>"+sum1+"thanx");
			float result1 = sum1;
			
			
			sum += result1; 
		}
		System.out.println("Here the sum is...>>"+sum);
		Statement supdate = dbconnection.createStatement();
		supdate.executeUpdate("Update task set sum="+sum+" where date='"+Date+"' and EmployeeID="+employeeID+"");
	
		request.setAttribute("Totalhour",sum);
		

}
finally{
		dbconnection.close();
System.out.println(" Db closed 1..");

}	
		// Request dispatcher
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("ProjMag/CreateTask.jsp");
		requestDispatcher.include(request, response);
		PrintWriter out = response.getWriter();
		  out.println("<h4 style='color:red;margin-left:400px;margin-top:-190px;'>Updated Successfully...</h4>");

	}

	public ManagerUpdateTask() {
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
			 
			 RequestDispatcher view = request.getRequestDispatcher("/ProjMag/UpdateAddTask.jsp");
             view.include(request, response);
             
             System.out.println("Disconnected!");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside Edit  Task");
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
