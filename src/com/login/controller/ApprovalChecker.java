package com.login.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
 * Servlet implementation class ApprovalChecker
 */
@WebServlet("/ApprovalChecker")
public class ApprovalChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalChecker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String approvalStatus = request.getParameter("approval");
		String employeeId = request.getParameter("empid");
		int empID = Integer.parseInt(employeeId);
		String date = request.getParameter("date");
		 SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String reformattedStr = null;
			try {

			    reformattedStr = myFormat.format(fromUser.parse(date));
			} catch (ParseException e) {
			    e.printStackTrace();
			}
		//String employeeId = request.getParameter("empid");
		System.out.println(approvalStatus);
		System.out.println(employeeId);
		
		if(approvalStatus.equalsIgnoreCase("yes")){
			try {
				Connection con = null;
				con = DBConnection.createConnection();
				System.out.println("connected!.....");
				
				String query = "UPDATE task SET approval='yes' WHERE EmployeeID="+ empID + " AND " + "date='" +reformattedStr+"'";
				System.out.println(query);
				Statement stmt = null;
				stmt = con.createStatement();
				stmt.executeUpdate(query);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		RequestDispatcher view = request.getRequestDispatcher("/Admin/confrimApproval.jsp");
        view.include(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
