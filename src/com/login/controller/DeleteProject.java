package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;


@WebServlet("/DeleteProject")
public class DeleteProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String projectName;
   public DeleteProject() {
        super();
        // TODO Auto-generated constructor stub
    }
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   projectName = request.getParameter("ProjName");
		System.out.println(projectName);

	   Statement st=null;
		Connection con = null;
		con = DBConnection.createConnection();
		System.out.println("connected!.....");
		String query = "SELECT * FROM myproject WHERE ProjName =" +projectName;
		System.out.println(query);
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String custName = null,projId = null,projName=null,tdes = null,type = null,ProductMang = null,startDate=null,endDate=null;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
               custName =rs.getString(1);
               projId =rs.getString(2);
               projName =rs.getString(3);
               tdes =rs.getString(4);
               type =rs.getString(5);
               ProductMang =rs.getString(6);
               startDate =rs.getString(7);
               endDate =rs.getString(8);
               
           }
			System.out.println(tdes);
			 request.setAttribute("custName", custName);
			 request.setAttribute("projId", projId);
			 request.setAttribute("projName", projName);
			 request.setAttribute("tdes", tdes);
			 request.setAttribute("type", type);
			 request.setAttribute("ProductMang", ProductMang);
			 request.setAttribute("startDate", startDate);
			 request.setAttribute("endDate", endDate);
			 
			 RequestDispatcher view = request.getRequestDispatcher("/Admin/DeleteProject.jsp");
            view.include(request, response);
            con.close();
            System.out.println("Disconnected!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String ProjName = request.getParameter("PName");
		System.out.println(ProjName);
		System.out.println("MySQL Connect Example.");
		try
		{
			con = DBConnection.createConnection();
			String deleteQuery = "DELETE FROM myproject WHERE ProjName = ?";
			PreparedStatement prpStat = con.prepareStatement(deleteQuery);
			
			prpStat.setString(1, projectName);
			
			System.out.println("prpStat :" + prpStat.toString());
			prpStat.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/UpdateProjects.jsp");
			rd.include(request, response);
			out.println("<h4 style='color:red;margin-left:400px;margin-top:-120px;'>" +ProjName+ " Deleted Successfully!</h4>");
			
			
			con.close();
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
