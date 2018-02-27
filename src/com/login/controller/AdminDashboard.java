package com.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminDashboard")
public class AdminDashboard extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		request.getRequestDispatcher("/Admin/AdminDashboard.jsp").include(request, response);
		

	}
	protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
        doPost(request, response);
}
}
