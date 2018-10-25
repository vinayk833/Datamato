package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

/**
 * Servlet implementation class SearchProject
 */
@WebServlet("/SearchProject")
public class SearchProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProject() {
        super();
        // TODO Auto-generated constructor stub
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Statement st=null;
   	 Connection con = null;
   	 con = DBConnection.createConnection();
   	 System.out.println("connected!.....");
        		  
        		  try {
        	
        	 String CustomerName = request.getParameter("CustomerName");
             String ID = request.getParameter("ID");
             String ProjName = request.getParameter("ProjName");
             String Description = request.getParameter("Description");
             String Type = request.getParameter("Type");
             String Manager = request.getParameter("Product Manager");
             String StartDate = request.getParameter("StartDate");
             String EndDate = request.getParameter("EndDate");
             request.setAttribute(ProjName, "ProjName");
             System.out.println(ProjName);
            
             ArrayList al = null;
             ArrayList pid_list = new ArrayList();
            
           //String query = "select * from holidays where DATE_FORMAT(date, '%Y') = YEAR (CURDATE()) limit 15";
             String query = "select * from myproject;";
            if(ProjName!=null && !ProjName.equals("")){
                 query = "select * from myproject where ProjName='" + ProjName + "' ";
             }
             System.out.println("query " + query);
              st = con.createStatement();
             ResultSet rs = st.executeQuery(query);

             while (rs.next()) {
                 al = new ArrayList();
                 al.add(rs.getString(1));
                 al.add(rs.getString(2));
                 al.add(rs.getString(3));
                 al.add(rs.getString(4));
                 al.add(rs.getString(5));
                 SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
         		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
         		String reformattedStr = null;
         		String reformattedStr1 = null;
         		try {
         			System.out.println();
         		    reformattedStr = myFormat.format(fromUser.parse(rs.getString(7)));
         		    reformattedStr1 = myFormat.format(fromUser.parse(rs.getString(8)));
         		    System.out.println(reformattedStr);
         		    System.out.println(reformattedStr1);
         		} catch (ParseException e) {
         		    e.printStackTrace();
         		}
                 al.add(rs.getString(6));
                 al.add(reformattedStr);
                 al.add(reformattedStr1);
                 System.out.println("al :: " + al);
                 pid_list.add(al);
             }
            
             request.setAttribute("piList", pid_list);
             System.out.println(pid_list);
             RequestDispatcher view = request.getRequestDispatcher("/Admin/UpdateProjects.jsp");
             view.forward(request, response);
            st.close();
            
             System.out.println("Disconnected!");
         } catch (Exception e) {
             e.printStackTrace();
         }
        		  finally{
        	             try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	  
        			  
        		  }
     }

     @Override
     public String getServletInfo() {
         return "getting records from database through servlet controller";
     }// </editor-fold>
	}


