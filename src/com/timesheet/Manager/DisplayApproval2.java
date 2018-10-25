package com.timesheet.Manager;

import java.io.IOException;
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

import org.apache.poi.xssf.usermodel.TextVerticalOverflow;

import com.login.util.DBConnection;



/**
 * Servlet implementation class DisplayApproval2
 */
@WebServlet("/DisplayApproval2")
public class DisplayApproval2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String EmployeeID;

	public static String date;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayApproval2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		EmployeeID = request.getParameter("EmployeeId");
		System.out.println("Here employee ID is......> " +EmployeeID);
		date = request.getParameter("date");
		System.out.println("Here date is......> " +date);
		Statement st=null;
		Connection con = null;
		con = DBConnection.createConnection();
		System.out.println("connected!.....");
		String query1 = "SELECT * FROM task WHERE EmployeeID =" +EmployeeID;
		String query2 = "SELECT * FROM task WHERE date =" +date;
		System.out.println("Query is------------>>" +query1);
		
		System.out.println("Query is------------>>" +query2);
		Statement st1=null;
		
try{
      	
      	
      	
      	 String employeeID  = (String) request.getSession().getAttribute("Manager");
      	ArrayList al = null;
        ArrayList pid_list = new ArrayList();
        
        
        String myquery = "SELECT users.EmployeeName FROM customers.users where users.EmployeeID=" + employeeID;
      st1 = con.createStatement();
        ResultSet rs1 = st1.executeQuery(myquery);
        String var=null;
        while(rs1.next()){
     	   var= rs1.getString("EmployeeName"); 
     	   System.out.println(var);
        }
        System.out.println("query ==========" + myquery);
        
        String QUE=  "SELECT  task.taskid,task.ProjName,task.proid,task.TaskCat,task.description,task.hours FROM customers.users INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where users.Approver='"+var+"' AND  users.EmployeeId='"+EmployeeID +"' AND date='"+date +"' AND task.approval IN('Email sent')";
        
        System.out.println("query " + QUE);
        st = con.createStatement();
       ResultSet rs = st.executeQuery(QUE);

        while (rs.next()) {
            al = new ArrayList();
            al.add(rs.getString(1));
            al.add(rs.getString(2));
            al.add(rs.getString(3));
            al.add(rs.getString(4));
            al.add(rs.getString(5));
            al.add(rs.getString(6));
//            al.add(rs.getString(7));
//            al.add(rs.getString(8));
//            al.add(rs.getString(9));
//            al.add(rs.getString(10));
            System.out.println("al :: " + al);
            pid_list.add(al);
        }


        request.setAttribute("piList", pid_list);
        System.out.println(pid_list);
        
        RequestDispatcher view = request.getRequestDispatcher("/ProjMag/ManagerApproval.jsp");
        view.forward(request, response);
        
        st1.close();
        st.close();

	}

        catch (Exception e) {
            e.printStackTrace();
        }
	
	
		
		
   	 finally{
    	 try {
			con.close();
			 System.out.println("Disconnected!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



 	 }
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
     
	
	
     	 
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	