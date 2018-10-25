package com.timesheet.Director;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class AdminAddTask
 */
@WebServlet("/DirectorAddTask")
public class DirectorAddTask extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

		int status = 0;
		PrintWriter out = response.getWriter();
		String employeeID  = (String) request.getSession().getAttribute("Director");
		BigInteger bi=null;
		  String bigInt=null;
		  try{
			  bi = new BigInteger(employeeID);
			  System.out.println(bi);
			  bigInt=bi.toString();
			  System.out.println(bigInt);
		  }catch(Exception e){
			  System.out.println("Error in converting String to BIG INT");
		  }
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


		//				 validate given input
		Connection dbconnection = null;
		dbconnection = DBConnection.createConnection();
		// Printing out Connection
		System.out.println("Connection------------->" + dbconnection);

		try{


			
			//select Query
			Statement statement1 = dbconnection.createStatement() ;
			Statement s1 = dbconnection.createStatement();
			Statement s2 = dbconnection.createStatement();
			ResultSet resultset1 =null;
			
			Statement s = dbconnection.createStatement();
			String q1 = "select distinct date from task where date='"+ reformattedStr +"' AND EmployeeID="+ bigInt +" and approval='Email sent'";
			//checks if user have filled data on particular date
			ResultSet r2 = s2.executeQuery("select distinct sum from task where date='"+ reformattedStr +"' AND EmployeeID="+ bigInt +"");
			String sum1 = null; 
			while(r2.next()){
				sum1 = r2.getString("sum");
				System.out.println(sum1);
			}
			System.out.println(sum1);
			String totalhours = request.getParameter("AgencyRating");
			System.out.println(totalhours);
				
			//int result1 = Integer.parseInt(totalhours);
			float result1 = Float.parseFloat(totalhours);
			float su=result1;
			if(sum1!=null){
				float result = Float.parseFloat(sum1);
				System.out.println(result);
				su = result+result1;
				System.out.println(su);
			}
			
			ResultSet r = s.executeQuery("select date from task where approval <>'Pending' AND date='"+ reformattedStr +"' AND EmployeeID="+ bigInt +" HAVING SUM(hours)>8");
			ResultSet r1 = s1.executeQuery(q1);
			if((r.next()==true)||(r1.next()==true)) {
				System.out.println("date already exists");
				status = 2;
			}else {
				System.out.println("datte not present");
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
	
					if(hours[i]== "" || projectID[i] == "" || projectName[i] == ""){
						status = 0;
						break;
					}else {
						String insertQuery = "insert into task(EmployeeID,date,ProjName,proid,TaskCat,description,hours,sum) values(?,?,?,?,?,?,?,?)";
						
						System.out.println("hours=====>"+hours[i]);
						// Setting up Prepared Statement
						PreparedStatement preparedStatement = (PreparedStatement) dbconnection.prepareStatement(insertQuery);
	
						//Passing parameters for Prepared Statement
						preparedStatement.setString(1,bigInt);
						preparedStatement.setString(2,reformattedStr);
						preparedStatement.setString(3,checkProjName.get(projectName[i]));
						preparedStatement.setString(4,projectID[i]);
						preparedStatement.setString(5,taskCat[i]);
						preparedStatement.setString(6,taskDescription[i]);
						preparedStatement.setString(7,hours[i]);
						preparedStatement.setString(8,sum);
	
						System.out.println(preparedStatement);
	
						// Execute update SQL statement
						status= preparedStatement.executeUpdate();
					}
	
	
				}
				if(sum1!=null){
					Statement supdate = dbconnection.createStatement();
					supdate.executeUpdate("Update task set sum="+su+" where date='"+reformattedStr+"' and EmployeeID="+bigInt+"");
				
					}
			}
			// Closing DB connection
			r.close();
			
			s.close();
			resultset1.close();
			statement1.close();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try {
				dbconnection.close();
				System.out.println("Connection close------------->");
				System.out.println("In Finally Block------------>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// Request dispatcher
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Director/DirectorTask.jsp");
		requestDispatcher.include(request, response);
		System.out.println("status==========>" + status);
		if(status == 0){
			out.println("<h4 style='color:red;margin-left:600px;margin-top:-230px;'>Please fill all the Mandatory Field...</h4>");

		}else if(status==2){
			out.println("<h4 style='color:red;margin-left:600px;margin-top:-230px;'>Data already exists on this date</h4>");
		}else{
			out.println("<h4 style='color:red;margin-left:600px;margin-top:-230px;'>Tasks Added Successfully...</h4>");
		}
	}
}

