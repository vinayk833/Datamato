package com.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reset
 */
@WebServlet("/Reset")
public class Reset extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empid = request.getParameter("empid");
		request.setAttribute("empid", empid);
		request.getRequestDispatcher("/JSP/resetpassword.jsp").forward(request, response);	
	}

}
