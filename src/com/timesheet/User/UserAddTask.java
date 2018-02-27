package com.timesheet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

/**
 * Servlet implementation class UserAddTask
 */
@WebServlet("/UserAddTask")
public class UserAddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    public static String mydate;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
		super.doGet(req, resp);
		String Test = null;
		req.setAttribute(mydate, Test);
		System.out.println(Test + "/" + mydate);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			
		PrintWriter out = response.getWriter();
			String employeeID  = (String) request.getSession().getAttribute("User");
			String date  = request.getParameter("date");
			SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String reformattedStr = null;
			
			try {

			    reformattedStr = myFormat.format(fromUser.parse(date));
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			
			String[] projectName  = request.getParameterValues("proname");
			String[] projectID  = request.getParameterValues("proid");
			String[] taskCat  = request.getParameterValues("TaskCat");
			String[] taskDescription  = request.getParameterValues("taskDescription");
			String[] hours  = request.getParameterValues("hours");
			String sum   = request.getParameter("AgencyRating");
			
			String  hours1  = request.getParameter("hours");
			String  projectName1  = request.getParameter("proname");

			// validate given input
			if ( projectName1.isEmpty()||  hours1.isEmpty()) {
					RequestDispatcher rd = request.getRequestDispatcher("/JSP/emp_event.jsp");
				//out.println("<font color=red>Please fill all the fields</font>");
					rd.include(request, response);
				} else {

			try{
				
			
			Connection dbconnection = null;
			dbconnection = DBConnection.createConnection();
			// Printing out Connection
			System.out.println("Connection------------->" + dbconnection);
			//select Query
			 Statement statement1 = dbconnection.createStatement() ;
			 ResultSet resultset1 =null;
			 resultset1 =statement1.executeQuery("select ProjName,ID from myproject") ;  
			
			 HashMap<String, String> checkProjName  = new HashMap<String, String>();
			 
			
			
			 while(resultset1.next()){
				checkProjName.put(resultset1.getString(2),resultset1.getString(1));
			}	
			System.out.println("My HashMap" + checkProjName.toString());
			
			//iterate for each  row
			for (int i=0;i<projectName.length;i++){
				System.out.println(projectName[i].toString() + " / " + projectID[i].toString());
				// Setting update query
				String insertQuery = "insert into task(EmployeeID,date,ProjName,proid,TaskCat,description,hours,sum) values(?,?,?,?,?,?,?,?)";
				
				// Setting up Prepared Statement
				PreparedStatement preparedStatement = (PreparedStatement) dbconnection.prepareStatement(insertQuery);
				
				//Passing parameters for Prepared Statement
				preparedStatement.setString(1,employeeID);
				preparedStatement.setString(2,reformattedStr);
				preparedStatement.setString(3,checkProjName.get(projectName[i]));
				preparedStatement.setString(4,projectID[i]);
				preparedStatement.setString(5,taskCat[i]);
				preparedStatement.setString(6,taskDescription[i]);
				preparedStatement.setString(7,hours[i]);
				preparedStatement.setString(8,sum);
			
				System.out.println(preparedStatement);
				
				// Execute update SQL statement
				preparedStatement.executeUpdate();
			}
			// Closing DB connection
			dbconnection.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			// Request dispatcher
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("/JSP/emp_event.jsp");
			requestDispatcher.include(request, response);
			
			  out.println("<h4 style='color:red;margin-left:400px;margin-top:-190px;'>Tasks Added Successfully...</h4>");
}
	}
}
