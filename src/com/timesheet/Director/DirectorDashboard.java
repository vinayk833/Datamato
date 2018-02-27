package com.timesheet.Director;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DirectorDashboard
 */
@WebServlet("/DirectorDashboard")
public class DirectorDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DirectorDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			
			request.getRequestDispatcher("/Director/DirectorDashboard.jsp").include(request, response);
			

		}
		protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
	        doPost(request, response);
	}
	}

