package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

/**
 * Servlet implementation class SearchProject
 */
@WebServlet("/UpdateUsers")
public class UpdateUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
          try {
        	Statement st=null;
        	 Connection con = null;
        	 con = DBConnection.createConnection();
        	 System.out.println("connected!.....");
        	 String EmployeeID = request.getParameter("EmployeeID");
      		  String EmployeeName = request.getParameter("EmployeeName");
      		  String Email = request.getParameter("EMAIL");
      		// String Password = request.getParameter("PASSWORD");
      		  String Role = request.getParameter("ROLE");
      		  String Department = request.getParameter("Department");
      		  String Approver = request.getParameter("Approver");
                request.setAttribute(EmployeeName, "EmployeeName");
                System.out.println(EmployeeName);
            
             ArrayList al = null;
             ArrayList pid_list = new ArrayList();
            
          
             String query = "select * from users ;";
            if(EmployeeName!=null && !EmployeeName.equals("")){
                 query = "select * from users where EmployeeName='" + EmployeeName + "' ";
             }
             System.out.println("query " + query);
              st = con.createStatement();
             ResultSet rs = st.executeQuery(query);

             while (rs.next()) {
                 al = new ArrayList();
            
               
               al.add(rs.getString(1));
                 al.add(rs.getString(2));
                 al.add(rs.getString(3));
                 al.add(rs.getString(5));
                 al.add(rs.getString(6));
                 al.add(rs.getString(7));
               
                 System.out.println("al :: " + al);
                 pid_list.add(al);
             }

             request.setAttribute("piList", pid_list);
             System.out.println(pid_list);
             RequestDispatcher view = request.getRequestDispatcher("/Admin/UpdateUsers.jsp");
             view.forward(request, response);
             
             rs.close();
             st.close();
             con.close();
            
             System.out.println("Disconnected!");
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     @Override
     public String getServletInfo() {
         return "getting records from database through servlet controller";
     }// </editor-fold>
	}


