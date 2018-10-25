package com.timesheet.Director;

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
 * Servlet implementation class DirectorResubmitTask
 */
@WebServlet("/DirectorResubmitTask")
public class DirectorResubmitTask extends HttpServlet {
private static final long serialVersionUID = 1L;

    
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectorResubmitTask() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
response.getWriter().append("Served at: ").append(request.getContextPath());
}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
doGet(request, response);
response.setContentType("text/html"); 
        PrintWriter out = response.getWriter(); 
        Connection con = null; 
    con = DBConnection.createConnection(); 
    System.out.println("connected!....."); 
    try { 
        Statement st=null; 
      
        String employeeID  = (String) request.getSession().getAttribute("Director"); 
  System.out.println("Here Employee ID is........>>" +employeeID);
        String EmployeeName = request.getParameter("EmpName"); 
          String startDate = request.getParameter("startdate"); 
             request.setAttribute("startdate",startDate); 
             SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy"); 
      SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd"); 
      String reformattedStr1 = null; 
try { 
        reformattedStr1 = myFormat.format(fromUser.parse(startDate)); 
       
        System.out.println( reformattedStr1); 
    } catch (ParseException e) { 
        e.printStackTrace(); 
    } 
             ArrayList al = null; 
             ArrayList pid_list = new ArrayList(); 
           
           String query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval from task where approval='Rejected' AND EmployeeID='" + employeeID + "'"; 
             if((reformattedStr1!=null && !reformattedStr1.equals(""))){ 
         
             query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval FROM task WHERE approval='Rejected' AND date =" +"'" + reformattedStr1 +"'"  + " AND "
                   + "EmployeeID='" + employeeID +"'"; 
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
                 al.add(rs.getString(6)); 
                 al.add(rs.getString(7)); 
                 al.add(rs.getString(8)); 
                 al.add(rs.getString(9)); 
                 System.out.println("al :: " + al); 
                 pid_list.add(al); 
             } 
            request.setAttribute("piList", pid_list); 
             System.out.println(pid_list); 

             Statement statement1 = con.createStatement(); 
             String que2= "select distinct sum from task where date='" +  reformattedStr1  + "' AND EmployeeID='" + employeeID + "' " ; 
             System.out.println(que2); 
         ResultSet r2=statement1.executeQuery( que2); 
         String sum = null; 
while(r2.next()) { 
sum = r2.getString("sum"); 
System.out.println(sum); 
  
  }
request.setAttribute("Totalhour",sum); 
              
             RequestDispatcher view = request.getRequestDispatcher("/Director/DirectorResubmit.jsp"); 
             view.forward(request, response); 
             rs.close(); 
             st.close();
             
             
    }
    catch (Exception e) { 
             e.printStackTrace(); 
         } 
          finally{ 
          try { 
con.close(); 
System.out.println("Connection close....In Finally Block"); 
} catch (SQLException e) { 
// TODO Auto-generated catch block 
e.printStackTrace(); 
}
          }

}
}

