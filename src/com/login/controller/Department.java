package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

/**
 * Servlet implementation class Department
 */
@WebServlet("/Department")
public class Department extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Department() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String  Department = request.getParameter("Department");
		
		System.out.println("MySQL Connect Example.");
		con = DBConnection.createConnection();
		// validate given input
		
		if (Department.isEmpty()) {
     	   RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddDepartment.jsp");
				rd.include(request, response);
				//out.println("<font color=red>Please fill all the fields</font>");
				
			} else {
		
			try
			{
				
				 String query = "insert into department values(?)";
	             PreparedStatement ps = con.prepareStatement(query); // generates sql query
	            ps.setString(1, Department);
	            int i= ps.executeUpdate();
		           if(i!=0)    {
		        	   RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddDepartment.jsp");
						rd.include(request, response);
						out.println("<h4 style='color:red;margin-left:400px;margin-top:-40px;'>Added " +Department+ " Successfully...</h4>");
	            }
	            else    {
	            	RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddDepartment.jsp");
					rd.include(request, response);
	                out.println("<h4 style='color:red;margin-left:400px;margin-top:-40px;'>Saved " +Department+ " failed</h4>");
	            }
				System.out.println("connected");
				ps.close();
					System.out.println("Disconnected from database");
				} catch (Exception e) {
					e.printStackTrace();
					RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddDepartment.jsp");
					rd.include(request, response);
					out.println("<h4 style='color:red;margin-left:400px;margin-top:-40px;'>" +Department+ " Already Exist</h4>");
				}
			finally{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Connection close------------->");
				System.out.println("In Finally Block------------>");

			}
			}
		
	
	}
}
