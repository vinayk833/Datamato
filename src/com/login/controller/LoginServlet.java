//LoginServlet.java
package com.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.login.bean.LoginBean;
import com.login.dao.LoginDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LoginServlet() {
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String EmployeeID = request.getParameter("EmployeeID");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUserName(EmployeeID);
		loginBean.setPassword(password);
		LoginDao loginDao = new LoginDao();
		
		try
		{		
			String userValidate = loginDao.authenticateUser(loginBean);
			if(userValidate.equals("Admin_Role"))
			{
				System.out.println("Admin's Home");
				HttpSession session = request.getSession(); //Creating a session
				session.setMaxInactiveInterval(30*60);
				session.setAttribute("Admin", EmployeeID); //setting session attribute
				//session.setAttribute("Admin", EmployeeName); //setting session attribute
				request.setAttribute("userName", EmployeeID);
				request.getRequestDispatcher("/Admin/AdminDashboard.jsp").forward(request, response);
			}
			else if(userValidate.equals("Manager_Role"))
			{
				System.out.println("Manager's Home");
				HttpSession session = request.getSession();
				session.setAttribute("Manager", EmployeeID);
				session.setMaxInactiveInterval(30*60);
				request.setAttribute("userName", EmployeeID);
				request.getRequestDispatcher("/ProjMag/ProjectManager.jsp").forward(request, response);
			}
			else if(userValidate.equals("Director_Role"))
			{
				System.out.println("Director's Home");
				HttpSession session = request.getSession();
			session.setMaxInactiveInterval(30*60);
				session.setAttribute("Director", EmployeeID);
				request.setAttribute("userName", EmployeeID);
				request.getRequestDispatcher("/Director/DirectorDashboard.jsp").forward(request, response);
			}
			else if(userValidate.equals("User_Role"))
			{
				System.out.println("User's Home");
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(30*60);
				session.setAttribute("User", EmployeeID);
				request.setAttribute("userName", EmployeeID);
				request.getRequestDispatcher("/JSP/UserDashboard.jsp").forward(request, response);

			}
			else
			{
				System.out.println("Error message = "+userValidate);
				request.setAttribute("errMessage", userValidate);
				request.getRequestDispatcher("/JSP/Login.jsp").forward(request, response);
			}
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
	} //End of doPost()
}