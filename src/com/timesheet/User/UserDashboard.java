package com.timesheet.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UserDashboard")
public class UserDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		request.getRequestDispatcher("/JSP/UserDashboard.jsp").include(request, response);
		

	}
	protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
        doPost(request, response);
}

}
