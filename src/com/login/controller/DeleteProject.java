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


@WebServlet("/DeleteProject")
public class DeleteProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String projectName;
   public DeleteProject() {
        super();
        // TODO Auto-generated constructor stub
    }
  
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		projectName = request.getParameter("ProjName");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String ProjName = request.getParameter("PName");
		System.out.println(ProjName);
		System.out.println("MySQL Connect Example.");
		try
		{
			con = DBConnection.createConnection();
			String deleteQuery = "DELETE FROM myproject WHERE ProjName = ?";
			PreparedStatement prpStat = con.prepareStatement(deleteQuery);
			
			prpStat.setString(1, projectName);
			
			System.out.println("prpStat :" + prpStat.toString());
			prpStat.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/UpdateProjects.jsp");
			rd.include(request, response);
			out.println("<h4 style='color:red;margin-left:400px;margin-top:-120px;'>" +projectName+ " Deleted Successfully!</h4>");
			
			prpStat.close();
			con.close();
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
