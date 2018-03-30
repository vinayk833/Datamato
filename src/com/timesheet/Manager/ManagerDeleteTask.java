package com.timesheet.Manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

/**
 * Servlet implementation class ManagerDeleteTask
 */
@WebServlet("/ManagerDeleteTask")
public class ManagerDeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String taskID; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerDeleteTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		taskID = request.getParameter("taskid");
		System.out.println(taskID);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// String TaskID = request.getParameter("taskId");
		//System.out.println(TaskID);
		System.out.println("MySQL Connect Example.");
		try
		{
			con = DBConnection.createConnection();
			String deleteQuery = "DELETE FROM task WHERE taskId = ?";
			PreparedStatement prpStat = con.prepareStatement(deleteQuery);
			
			prpStat.setString(1, taskID);
			
			System.out.println("prpStat :" + prpStat.toString());
			prpStat.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("/ProjMag/CreateTask.jsp");
			rd.include(request, response);
			out.println("<h4 style='color:red;margin-left:400px;margin-top:-190px;'>" +taskID+ " Deleted Successfully!</h4>");
			
			prpStat.close();
			con.close();
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



	}
