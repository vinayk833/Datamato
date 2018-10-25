package com.timesheet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.controller.AdminAddTask;
import com.login.util.DBConnection;


@WebServlet("/ResubmitTask")
public class ResubmitTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String mydate = null;

       
    
    public ResubmitTask() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con = null;
   	 con = DBConnection.createConnection();
   	 System.out.println("connected!.....");
          try {
        	Statement st=null;
        	 
        		String employeeID  = (String) request.getSession().getAttribute("User");
        	 String EmployeeName = request.getParameter("EmpName");
             String startDate = request.getParameter("startdate");
             request.setAttribute("startdate",startDate);
            
            
     		 SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
     			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
     			String reformattedStr1 = null;
			try {

    			    reformattedStr1 = myFormat.format(fromUser.parse(startDate));
    			    
    			    System.out.println( reformattedStr1);

    			} catch (ParseException e) {
    			    e.printStackTrace();
    			}
			
			
             ArrayList al = null;
             ArrayList pid_list = new ArrayList();
            
           String query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval from task where approval='Rejected' AND EmployeeID='" + employeeID + "'";
             if((reformattedStr1!=null && !reformattedStr1.equals(""))){
          
             query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval FROM task WHERE approval='Rejected' AND date =" +"'" + reformattedStr1 +"'"  + " AND " 
                   + "EmployeeID='" + employeeID +"'";
             } 
       
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
                 al.add(rs.getString(9));
                 System.out.println("al :: " + al);
                 pid_list.add(al);
             }

             request.setAttribute("piList", pid_list);
             System.out.println(pid_list);
             
             
             
             ////////////////////////////
             Statement statement1 = con.createStatement();
             
             String que2= "select distinct sum from task where date='" +  reformattedStr1  + "' AND EmployeeID='" + employeeID + "' " ;
             System.out.println(que2);
         ResultSet r2=statement1.executeQuery( que2);		
         String sum = null;
			while(r2.next()) {
			sum = r2.getString("sum");
				System.out.println(sum);
			}
			
			request.setAttribute("Totalhour",sum);
			
             
           
  ///////////////////////////////////////           
             
             
             
             
             
             
             
             
             RequestDispatcher view = request.getRequestDispatcher("/JSP/Resubmit.jsp");
             view.forward(request, response);
             rs.close();
             st.close();
           
            
           
         } catch (Exception e) {
             e.printStackTrace();
         }
          finally{
        	  try {
				con.close();
				System.out.println("Connection close....In Finally Block");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
          }
	
	}

}
