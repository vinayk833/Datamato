package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
       
    Connection con = null;
    response.setContentType("text/html");
    con = DBConnection.createConnection();

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
          SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
  			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
  		/*String reformattedStr = null;
  		String reformattedStr1 = null;*/
  		try {
  			System.out.println();
  			StartDate = myFormat.format(fromUser.parse(StartDate));
  			EndDate = myFormat.format(fromUser.parse(EndDate));
  		    System.out.println(StartDate);
  		    System.out.println(EndDate);
  		} catch (ParseException e) {
  		    e.printStackTrace();
  		}
       // validate given input
		  if (CustomerName.isEmpty()|| ID.isEmpty() || ProjName.isEmpty()  ||Type.isEmpty() || Manager.isEmpty() || StartDate.isEmpty()|| EndDate.isEmpty()) {
		   RequestDispatcher rd = request.getRequestDispatcher("/Admin/AddProjects.jsp");
		///   out.println("<font color=red>Please fill all the fields</font>");
		   rd.include(request, response);
		  } else {


           try {
             
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
            
            ps.close();
            
            System.out.println("Disconnected from database");
        } catch (Exception e) {
        e.printStackTrace();
        
        RequestDispatcher rd=request.getRequestDispatcher("/Admin/AddProjects.jsp");
		rd.include(request, response);
		out.println("<h4 style='color:red;margin-left:400px;margin-top:-70px;'>" +ProjName+ " Already Exist</h4>");
        }
           finally{
				try {
					con.close();
					System.out.println("Disconnected from Database.");
					System.out.println("In finally block");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    }
    
}


}