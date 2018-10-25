package com.timesheet.Director;

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
 * Servlet implementation class DirectorDeleteTask2
 */
@WebServlet("/DirectorDeleteTask2")
public class DirectorDeleteTask2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String taskID; 
	public static String Date; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectorDeleteTask2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String employeeID  = (String) request.getSession().getAttribute("Director");

		Connection con = null;
		taskID = request.getParameter("taskid");
		Date = request.getParameter("date");
		System.out.println("Here task id is>>>"+taskID);
		System.out.println("Here task id is>>>"+Date);
		System.out.println("Here employee id is>>>"+employeeID);


		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// String TaskID = request.getParameter("taskId");
		//System.out.println(TaskID);
		System.out.println("MySQL Connect Example.");

		con = DBConnection.createConnection();

		try
		{
			double hours = 0;
			double result1 = 0;

			double sum= 0;
			Statement st=con.createStatement();

			String query= "select hours,sum from task where taskId ="+taskID+"";
			System.out.println("Retreive hours from database...."+query);
			
			
			ResultSet r2= st.executeQuery(query);
			

			while(r2.next()){
				
				System.out.println(r2.getString("hours"));
				System.out.println(r2.getString("sum"));
				hours = Double.parseDouble(r2.getString("hours"));
				
				
				
				sum =Double.parseDouble(r2.getString("sum"));
				System.out.println("hours....>"+hours);
				
				System.out.println("Toatl hours....>"+sum);
				
				
				//double result1 = 0;
				result1=sum-hours;
				
			}
			System.out.println("Here the Substraction  is...>>"+result1);
			
			request.setAttribute("Totalhour",+result1);
			System.out.println(" Substraction..."+result1 );
			
			
			
			Statement supdate = con.createStatement();
			
			
			
			supdate.executeUpdate("Update task set sum="+result1+" where date='"+Date+"' and EmployeeID="+employeeID+"");
			
			request.setAttribute("Totalhour",result1);
			
			
			
			
				System.out.println("Updated successfully");
		

			String deleteQuery = "DELETE FROM task WHERE taskId = ?";
			PreparedStatement prpStat = con.prepareStatement(deleteQuery);
			
			prpStat.setString(1, taskID);
			
			System.out.println("prpStat :" + prpStat.toString());
			prpStat.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("/Director/DirectorResubmit.jsp");
			rd.include(request, response);
			out.println("<h4 style='color:red;margin-left:400px;margin-top:-129px;'>" +taskID+ " Deleted Successfully!</h4>");
			
			prpStat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
				System.out.println("Connection close------------->");
				System.out.println("In Finally Block------------>");
			} catch (SQLException e) {

				e.printStackTrace();
			}
			

		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
