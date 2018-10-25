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


@WebServlet("/ProjectType")
public class ProjectType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ProjectType() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String  ProjectType = request.getParameter("ProjectType");
		
		System.out.println("MySQL Connect Example.");
		con = DBConnection.createConnection();
		// validate given input
		if (ProjectType.isEmpty()) {
			 RequestDispatcher rd=request.getRequestDispatcher("/Admin/ProjectType.jsp");
				rd.include(request, response);
				//out.println("<font color=red>Please fill all the fields</font>");
				
			} else {

		try
			{
				
				 String query = "insert into addprojecttype values(?)";
	             PreparedStatement ps = con.prepareStatement(query); // generates sql query
	            ps.setString(1, ProjectType);
	            int i= ps.executeUpdate();
		           if(i!=0)    {
		        	   RequestDispatcher rd=request.getRequestDispatcher("/Admin/ProjectType.jsp");
						rd.include(request, response);
						out.println("<h4 style='color:red;margin-left:500px;margin-top:-40px;'>Added " + ProjectType + "  Successfully...</h4>");
	            }
	            else    {
	            	RequestDispatcher rd=request.getRequestDispatcher("/Admin/ProjectType.jsp");
					rd.include(request, response);
	                out.println("<h4 style='color:red;margin-left:500px;margin-top:-40px;'>Saved " + ProjectType + " failed</h4>");
	            }
				System.out.println("connected");
				
					System.out.println(query);
					//statement.close();
					//resultSet.close();
				System.out.println("Disconnected from database");
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher rd=request.getRequestDispatcher("/Admin/ProjectType.jsp");
				rd.include(request, response);
				out.println("<h4 style='color:red;margin-left:400px;margin-top:-40px;'>" +"\t" +ProjectType+ " Already Exist</h4>");
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
	}


