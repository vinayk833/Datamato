package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;


@WebServlet("/ViewAdminTask")
public class ViewAdminTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
   
    public ViewAdminTask() {
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
         
          String query =  "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours, approval FROM task WHERE date BETWEEN " +"'" +  reformattedStr1 +"'" + " AND " + "'"+  reformattedStr2 + "'" + " AND " 
                  + "EmployeeID IN(SELECT EmployeeID FROM users WHERE EmployeeName=\""+ EmployeeName +"\")";
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
             
             RequestDispatcher view = request.getRequestDispatcher("/Admin/ViewTask.jsp");
             view.forward(request, response);
             st.close();
            
           
         } catch (Exception e) {
             e.printStackTrace();
         }
          finally{
              try {
				con.close();
				System.out.println("Connection close------------->");
				System.out.println("In Finally Block------------>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  
          }
	}

}
