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

/**
 * Servlet implementation class DeleteDepartment
 */
@WebServlet("/DeleteDepartment")
public class DeleteDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DeleteDepartment() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String Department  = request.getParameter("Department");

		System.out.println("MySQL Connect Example.");
		// validate given input


		try
		{
			con = DBConnection.createConnection();
			String deleteQuery = "DELETE FROM department WHERE Department = ?";
			PreparedStatement prpStat = con.prepareStatement(deleteQuery);
			
			prpStat.setString(1, Department);
			
			System.out.println("prpStat :" + prpStat.toString());
			prpStat.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/DeleteDepartment.jsp");
			rd.include(request, response);
			out.println("<h4 style='color:red;margin-left:250px;margin-top:-40px;'>" +Department+ " Deleted Successfully!</h4>");
			
			
			con.close();
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}


