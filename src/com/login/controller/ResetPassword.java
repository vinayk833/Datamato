package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection con =null;
		con = DBConnection.createConnection();
		try{
			
			Statement st = con.createStatement();
			String password = request.getParameter("newPassword");
			String empid = request.getParameter("empid");
			System.out.println(password);
			System.out.println(empid);
			String query="Update users Set PASSWORD='" + password +"' where EmployeeID='" + empid +"'";
			st.executeUpdate(query);
			System.out.println(query);
			//PrintWriter out = response.getWriter();
			//out.println("<h4 style='color:red;margin-left:400px;margin-top:-70px;'>Password Updated Successfully...</h4>");
			String suc = "Password Updated Successfully";
			System.out.println("Password Updated Successfully");
			request.getRequestDispatcher("/JSP/Login.jsp").forward(request, response);;
			st.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
				System.out.println("Connection close------------->");
				System.out.println("In Finally Block------------>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
