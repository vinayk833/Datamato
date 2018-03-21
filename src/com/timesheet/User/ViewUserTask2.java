package com.timesheet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

/**
 * Servlet implementation class ViewUserTask2
 */
@WebServlet("/ViewUserTask2")
public class ViewUserTask2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUserTask2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	 
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	          try {
	        	Statement st=null;
	        	 Connection con = null;
	        	 con = DBConnection.createConnection();
	        	 System.out.println("connected!.....");
	        		String employeeID  = (String) request.getSession().getAttribute("User");
	        	 String EmployeeName = request.getParameter("EmpName");
	             String startDate = request.getParameter("startdate");
	             String endDate = request.getParameter("enddate");
	             System.out.println("MySQL Connect Example.");
	       		
	     		 SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
	     			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
	     			String reformattedStr1 = null;
	     			String reformattedStr2 = null;

	    			
	    			
					
	    			try {

	    			    reformattedStr1 = myFormat.format(fromUser.parse(startDate));
	    			    reformattedStr2 = myFormat.format(fromUser.parse(endDate));
	    			    
	    			    System.out.println( reformattedStr1);
	    			    System.out.println( reformattedStr2);

	    			} catch (ParseException e) {
	    			    e.printStackTrace();
	    			}
	           
	             ArrayList al = null;
	             ArrayList pid_list = new ArrayList();
	            
	           String query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours from task where EmployeeID='" + employeeID + "'";
	             if((reformattedStr1!=null && !reformattedStr1.equals(""))||(reformattedStr2!=null && !reformattedStr2.equals(""))){
	          
	             query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours FROM task WHERE date BETWEEN " +"'" + reformattedStr1 +"'" + " AND " + "'"+ reformattedStr2 + "'" + " AND " 
	                   + "EmployeeID='" + employeeID +"'";
	             } 
	        /*  String query =  "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours FROM task WHERE date BETWEEN " +"'" + startDate +"'" + " AND " + "'"+ endDate + "'" + " AND " 
	                  + "EmployeeID= (SELECT EmployeeID FROM users WHERE EmployeeName=\""+ EmployeeName +"\")";*/
	             System.out.println("query " + query);
	             st = con.createStatement();
	            ResultSet rs = st.executeQuery(query);

	             while (rs.next()) {
	                 al = new ArrayList();
	                 al.add(rs.getString(1));
	                 al.add(rs.getString(2));
	                 al.add(rs.getString(3));
	                 al.add(rs.getString(4));
	                 al.add(rs.getString(5));
	                 al.add(rs.getString(6));
	                 al.add(rs.getString(7));
	                 al.add(rs.getString(8));
	                 System.out.println("al :: " + al);
	                 pid_list.add(al);
	             }

	             request.setAttribute("piList", pid_list);
	             System.out.println(pid_list);
	             
	             RequestDispatcher view = request.getRequestDispatcher("/JSP/viewevent.jsp");
	             view.forward(request, response);
	             con.close();
	             System.out.println("Disconnected!");
	           
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
		}

	}
	
	
	