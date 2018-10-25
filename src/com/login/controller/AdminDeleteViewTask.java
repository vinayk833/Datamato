
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
 * Servlet implementation class AdminDeleteViewTask
 */
@WebServlet("/AdminDeleteViewTask")
public class AdminDeleteViewTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String taskID;
	public static String Date;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteViewTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		taskID = request.getParameter("taskid");
		Date = request.getParameter("date");

		System.out.println(taskID);

		Statement st=null;
		Connection con = null;
		con = DBConnection.createConnection();
		System.out.println("connected!.....");
		String query = "SELECT * FROM task WHERE taskID =" +taskID;
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
			 
			 RequestDispatcher view = request.getRequestDispatcher("/Admin/DeleteTask.jsp");
             view.include(request, response);
             con.close();
             st.close();
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
		Connection con = null;
		String employeeID  = (String) request.getSession().getAttribute("Admin");
		System.out.println("Here employee ID in do post"+employeeID);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// String TaskID = request.getParameter("taskId");
		//System.out.println(TaskID);
		System.out.println("MySQL Connect Example.");
		try
		{
			System.out.println("Here task id in do post method"+taskID);
			System.out.println("Here date in do post method"+Date);
            con = DBConnection.createConnection();
            
            
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
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/ViewTask.jsp");
			rd.include(request, response);
			out.println("<h4 style='color:red;margin-left:400px;margin-top:-120px;'>" +taskID+ " Deleted Successfully!</h4>");
			
			prpStat.close();
			
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
				System.out.println("In Finally Block");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}





	}


