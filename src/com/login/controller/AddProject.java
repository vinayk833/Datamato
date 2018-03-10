package com.login.controller;

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
 * Servlet implementation class AddProject//l
 */
@WebServlet("/AddProject")
public class AddProject extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public AddProject() {
        super();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
	
    // TODO Auto-generated method stub
         PrintWriter out = response.getWriter();
          String CustomerName = request.getParameter("CustomerName");
          String ID = request.getParameter("ID");
          String ProjName = request.getParameter("ProjName");
          String Description = request.getParameter("Description");
          String Type = request.getParameter("Type");
          String Manager = request.getParameter("Product Manager");
          String StartDate = request.getParameter("StartDate");
          String EndDate = request.getParameter("EndDate");
       // validate given input
		  if (CustomerName.isEmpty()|| ID.isEmpty() || ProjName.isEmpty()  ||Type.isEmpty() || Manager.isEmpty() || StartDate.isEmpty()|| EndDate.isEmpty()) {
		   RequestDispatcher rd = request.getRequestDispatcher("/Admin/AddProjects.jsp");
		///   out.println("<font color=red>Please fill all the fields</font>");
		   rd.include(request, response);
		  } else {


           try {
               Connection con = null;
                response.setContentType("text/html");
            con = DBConnection.createConnection();
                String query = "insert into myproject values(?,?,?,?,?,?,?,?)";
             PreparedStatement ps = con.prepareStatement(query); // generates sql query
            ps.setString(1, CustomerName);
            ps.setString(2,ID);
            ps.setString(3,ProjName);
            ps.setString(4, Description);
            ps.setString(5, Type);
            ps.setString(6, Manager);
            ps.setString(7,StartDate);
            ps.setString(8,EndDate);

            int i= ps.executeUpdate();
	           if(i!=0)    {
	        	   RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddProjects.jsp");
					rd.include(request, response);
					out.println("<h4 style='color:red;margin-left:400px;margin-top:-70px;'>Added " +ProjName+ " Successfully...</h4>");
            }
            else    {
         	   RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddProjects.jsp");
					rd.include(request, response);
                out.println("<h4 style='color:red;margin-left:400px;margin-top:-70px;'>Saved " +ProjName+ " failed</h4>");
            }
            System.out.println("Successfully inserted into DB");        
            con.close();
            System.out.println("Disconnected from database");
        } catch (Exception e) {
        e.printStackTrace();
        
        RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddProjects.jsp");
		rd.include(request, response);
		out.println("<h4 style='color:red;margin-left:400px;margin-top:-70px;'>" +ProjName+ " Already Exist</h4>");
        }
    }
    
}


}