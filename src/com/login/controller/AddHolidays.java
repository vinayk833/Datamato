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


@WebServlet("/AddHolidays")
public class AddHolidays extends HttpServlet {
	private static final long serialVersionUID = 1L;
public AddHolidays() {
		super();
	}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection con = null;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String  date  = request.getParameter("date");
		String  Description  = request.getParameter("Description");
		SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String reformattedStr = null;
		try {

		    reformattedStr = myFormat.format(fromUser.parse(date));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		System.out.println("MySQL Connect Example.");
	

			try
			{
				con = DBConnection.createConnection();
				
				 String query = "insert into holidays values(?,?)";
	             PreparedStatement ps = con.prepareStatement(query); // generates sql query
	            ps.setString(1, reformattedStr);
	            ps.setString(2,Description);
	           int i= ps.executeUpdate();
	           if(i!=0)    {
	        	   RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddHolidays.jsp");
					rd.include(request, response);
					out.println("<h4 style='color:red;margin-left:600px;margin-top:50px;text-align='center';'>Added " +date+ " Successfully as holiday ...</h4>");
               }
               else    {
            	   RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddHolidays.jsp");
					rd.include(request, response);
                   out.println("<h4 style='color:red;margin-left:600px;margin-top:50px;text-align='center';'>data can not be stored</h4>");
               }
				System.out.println("query " + query);
				 //response.sendRedirect("/Admin/AddHolidays.jsp");
				
				
				
				con.close();
				System.out.println("Disconnected from database");
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddHolidays.jsp");
				rd.include(request, response);
				out.println("<h4 style='color:red;margin-left:600px;margin-top:50px;text-align='center';'>" +date+ " Already Exist</h4>");
			}
			
		
	}

}


