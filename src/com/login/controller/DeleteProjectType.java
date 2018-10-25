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


@WebServlet("/DeleteProjectType")
public class DeleteProjectType extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DeleteProjectType() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String ProjectType  = request.getParameter("ProjectType");

		System.out.println("MySQL Connect Example.");
		con = DBConnection.createConnection();

	try
		{
			String deleteQuery = "DELETE FROM addprojecttype WHERE ProjectType = ?";
			PreparedStatement prpStat = con.prepareStatement(deleteQuery);
			
			prpStat.setString(1, ProjectType);
			
			System.out.println("prpStat :" + prpStat.toString());
			prpStat.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/DeleteProjectType.jsp");
			rd.include(request, response);
			out.println("<h4 style='color:red;margin-left:400px;margin-top:-20px;'>" +ProjectType+ " Deleted Successfully!</h4>");
			prpStat.close();
			
			
			System.out.println("Disconnected from database");
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
