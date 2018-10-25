package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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


@WebServlet("/UpdateHoliday")
public class UpdateHoliday extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateHoliday() {
        super();
        // TODO Auto-generated constructor stub
    }


		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con = null;
   	 con = DBConnection.createConnection();
   	 System.out.println("connected!.....");
          try {
        	Statement st=null;
        	
             String date = request.getParameter("date");
             request.setAttribute(date, "date");
             System.out.println(date);
           SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
				String reformattedStr = null;
				try {

				    reformattedStr = myFormat.format(fromUser.parse(date));
				} catch (ParseException e) {
				    e.printStackTrace();
				}
             ArrayList al = null;
             ArrayList pid_list = new ArrayList();
            
           //String query = "select * from holidays where DATE_FORMAT(date, '%Y') = YEAR (CURDATE()) limit 15";
             String query = "select * from holidays order by date(date=(CURDATE()));";
            if(date!=null && !date.equals("")){
                 query = "select * from holidays where date='" + reformattedStr + "' ";
             }
             System.out.println("query " + query);
              st = con.createStatement();
             ResultSet rs = st.executeQuery(query);

             while (rs.next()) {
                 al = new ArrayList();
                 al.add(rs.getString(1));
                 al.add(rs.getString(2));
                 System.out.println("al :: " + al);
                 pid_list.add(al);
             }

             request.setAttribute("piList", pid_list);
             System.out.println(pid_list);
             RequestDispatcher view = request.getRequestDispatcher("/Admin/UpdateHoliday.jsp");
             view.forward(request, response);
             st.close();
             System.out.println("Disconnected!");
           
         } catch (Exception e) {
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

     @Override
     public String getServletInfo() {
         return "getting records from database through servlet controller";
     }// </editor-fold>
	}


