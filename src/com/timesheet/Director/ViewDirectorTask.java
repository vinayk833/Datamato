package com.timesheet.Director;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;


@WebServlet("/ViewDirectorTask")
public class ViewDirectorTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
   
    public ViewDirectorTask() {
        super();
        // TODO Auto-generated constructor stub
    }
  
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
		 
		 
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
          
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con = null;
   	 con = DBConnection.createConnection();
   	 System.out.println("connected!.....");
          try {
        	Statement st=null;
        	 
       	 String employeeID  = (String) request.getSession().getAttribute("Director");

        	 String EmployeeName = request.getParameter("EmpName");
             String startDate = request.getParameter("startdate");
             String endDate = request.getParameter("enddate");
             String selectopt = request.getParameter("filter");
             System.out.println("Here select option is"+selectopt);
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
            
             switch(selectopt) {
             case "approve": System.out.println(" In Approved case");
            
            	System.out.println("In "+selectopt);
           String query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval from task where approval='Approved' and EmployeeID='" + employeeID + "'";
             if((reformattedStr1!=null && !reformattedStr1.equals(""))||(reformattedStr2!=null && !reformattedStr2.equals(""))){
          
             query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval FROM task WHERE approval='Approved' AND date BETWEEN " +"'" + reformattedStr1 +"'" + " AND " + "'"+ reformattedStr2 + "'" + " AND " 
                  + "EmployeeID= (SELECT EmployeeID FROM users WHERE EmployeeName=\""+ EmployeeName +"\")";
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
             
             break;
             case "Pending": System.out.println("Pending case");
             
             String query1 = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval from task where approval='Email sent' AND EmployeeID='" + employeeID + "'";
             if((reformattedStr1!=null && !reformattedStr1.equals(""))||(reformattedStr2!=null && !reformattedStr2.equals(""))){
          
             query1 = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval FROM task WHERE approval='Email sent' AND date BETWEEN " +"'" + reformattedStr1 +"'" + " AND " + "'"+ reformattedStr2 + "'" + " AND " 
                  + "EmployeeID= (SELECT EmployeeID FROM users WHERE EmployeeName=\""+ EmployeeName +"\")";
             } 
        
             System.out.println("query1 " + query1);
             
             
             st = con.createStatement();
            ResultSet rs1 = st.executeQuery(query1);

             while (rs1.next()) {
                 al = new ArrayList();
                 al.add(rs1.getString(1));
                 al.add(rs1.getString(2));
                 al.add(rs1.getString(3));
                 al.add(rs1.getString(4));
                 al.add(rs1.getString(5));
                 al.add(rs1.getString(6));
                 al.add(rs1.getString(7));
                 al.add(rs1.getString(8));
                 al.add(rs1.getString(9));
                 System.out.println("al :: " + al);
                 pid_list.add(al);
             }

             request.setAttribute("piList", pid_list);
             System.out.println(pid_list);
             
             break;
             
case "reject": System.out.println("Reject case");
             
             String query3 = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval from task where approval='Rejected' AND EmployeeID='" + employeeID + "'";
             if((reformattedStr1!=null && !reformattedStr1.equals(""))||(reformattedStr2!=null && !reformattedStr2.equals(""))){
          
             query3 = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval FROM task WHERE approval='Rejected' AND date BETWEEN " +"'" + reformattedStr1 +"'" + " AND " + "'"+ reformattedStr2 + "'" + " AND " 
                  + "EmployeeID= (SELECT EmployeeID FROM users WHERE EmployeeName=\""+ EmployeeName +"\")";
             } 
        
             System.out.println("query1 " + query3);
             
             
             st = con.createStatement();
            ResultSet rs3 = st.executeQuery(query3);

             while (rs3.next()) {
                 al = new ArrayList();
                 al.add(rs3.getString(1));
                 al.add(rs3.getString(2));
                 al.add(rs3.getString(3));
                 al.add(rs3.getString(4));
                 al.add(rs3.getString(5));
                 al.add(rs3.getString(6));
                 al.add(rs3.getString(7));
                 al.add(rs3.getString(8));
                 al.add(rs3.getString(9));
                 System.out.println("al :: " + al);
                 pid_list.add(al);
             }

             request.setAttribute("piList", pid_list);
             System.out.println(pid_list);
             
             
             break;
             
             }
             
             RequestDispatcher view = request.getRequestDispatcher("/Director/ViewDirTask.jsp");
             view.forward(request, response);
             
             st.close();
             
             System.out.println("Disconnected!");
           
         } catch (Exception e) {
             e.printStackTrace();
         }
          finally{
        	  try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }
	}

}
