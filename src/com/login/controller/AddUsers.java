package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

@WebServlet("/AddUsers")
public class AddUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AddUsers() {
		super();
	}
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request, response);
	    }
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection con = null;
		response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  String EmployeeID = request.getParameter("EmployeeID");
		  String EmployeeName = request.getParameter("EmployeeName");
		  String Email = request.getParameter("EMAIL");
		String Password = request.getParameter("PASSWORD");
		  String Role = request.getParameter("ROLE");
		  String Department = request.getParameter("Department");
		  String Approver = request.getParameter("Approver");
          try {
             
           con = DBConnection.createConnection();
               String query = "insert into users values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query); // generates sql query
           ps.setString(1, EmployeeID);
           ps.setString(2,EmployeeName);
           ps.setString(3,Email);
           ps.setString(4, Password);
           ps.setString(5, Role);
           ps.setString(6, Department);
           ps.setString(7,Approver);
         
           int i= ps.executeUpdate();
	           if(i!=0)    {
	        	   RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddUsers.jsp");
					rd.include(request, response);
					out.println("<h4 style='color:red;margin-left:400px;margin-top:5px;'>Added " +EmployeeName+ " Successfully...</h4>");
           }
           else    {
        	   RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddUsers.jsp");
					rd.include(request, response);
               out.println("<h4 style='color:red;margin-left:400px;margin-top:5px;'>Saved " +EmployeeName+ " failed</h4>");
           }
           System.out.println("successfully inserted");
          
         
           con.close();
           System.out.println("Disconnected from database");
       } catch (Exception e) {
       e.printStackTrace();
       RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddUsers.jsp");
		rd.include(request, response);
		out.println("<h4 style='color:red;margin-left:400px;margin-top:5px;'>" +EmployeeName+ " Already Exist</h4>");
       }
   }
   



}