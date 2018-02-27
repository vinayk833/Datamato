package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

/**
 * Servlet implementation class DeleteHoliday
 */
@WebServlet("/DeleteHoliday")
public class DeleteHoliday extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
  
    public DeleteHoliday() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String date  = request.getParameter("date");
		System.out.println(date);
		System.out.println("MySQL Connect Example.");
		 SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String reformattedStr = null;
			try {

			    reformattedStr = myFormat.format(fromUser.parse(date));
			} catch (ParseException e) {
			    e.printStackTrace();
			}


		try
		{
			con = DBConnection.createConnection();
			String deleteQuery = "DELETE FROM holidays WHERE date = ?";
			PreparedStatement prpStat = con.prepareStatement(deleteQuery);
			
			prpStat.setString(1, reformattedStr);
			
			System.out.println("prpStat :" + prpStat.toString());
			prpStat.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/UpdateHoliday.jsp");
			rd.include(request, response);
			out.println("<h4 style='color:red;margin-left:250px;margin-top:-70px;'>" +reformattedStr+ " Deleted Successfully!</h4>");
			
			
			con.close();
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	}


