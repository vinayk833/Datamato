package com.timesheet.Director;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class DisplayDirectorApproval
 */
@WebServlet("/DisplayDirectorApproval")
public class DisplayDirectorApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayDirectorApproval() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
		      	Statement st=null;
		     	Statement st1=null;
		      	 Connection con = null;
		      	 con = DBConnection.createConnection();
		      	 System.out.println("connected!.....");
		      	 String employeeID  = (String) request.getSession().getAttribute("Director");
		      	 String EmployeeName = request.getParameter("EmpName");
		           //String startDate = request.getParameter("startdate");
		           //String endDate = request.getParameter("enddate");
		      	String startDate = request.getParameter("startdate");
		      	String endDate = request.getParameter("enddate");
		           System.out.println(startDate);
		           System.out.println(endDate);
		           request.setAttribute("startdate",startDate);
		           request.setAttribute("enddate",endDate);
		           SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
		   		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		   		String reformattedStr = null;
		   		String reformattedStr1 = null;
		   		try {
		   			System.out.println();
		   		    reformattedStr = myFormat.format(fromUser.parse(startDate));
		   		    reformattedStr1 = myFormat.format(fromUser.parse(endDate));
		   		    System.out.println(reformattedStr);
		   		    System.out.println(reformattedStr1);
		   		} catch (ParseException e) {
		   		    e.printStackTrace();
		   		}
		           //request.setAttribute("MDate",MDate);
		         
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
		         //  String query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval from task where EmployeeID='" + employeeID + "'";
		             //String query = "SELECT distinct task.taskid,task.EmployeeID,users.EmployeeName,task.date,task.ProjName,task.proid,task.TaskCat,task.description,task.hours,task.approval FROM customers.users  INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where task.approval='Pending' AND task.hours>8 AND users.Approver='"+var+"' AND MONTH(task.date)=MONTH(CURRENT_DATE()) AND YEAR(task.date) = YEAR(CURRENT_DATE())";
		        //String query = "SELECT task.taskid,task.EmployeeID,users.EmployeeName,task.date,task.ProjName,task.proid,task.TaskCat,task.description,task.hours,task.approval FROM customers.users INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where users.Approver='"+var+"' AND date IN(SELECT DISTINCT a.date FROM(SELECT date FROM task GROUP BY date,EmployeeID HAVING SUM(hours)>8)a WHERE date BETWEEN '"+reformattedStr+"' AND '"+reformattedStr1+"' AND task.approval IN('Pending','emailsent'))";  
		        String query = "SELECT task.taskid,task.EmployeeID,users.EmployeeName,task.date,task.ProjName,task.proid,task.TaskCat,task.description,task.hours,task.approval FROM customers.users INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where users.Approver='"+var+"' AND date IN(SELECT DISTINCT a.date FROM(SELECT date FROM task GROUP BY date,EmployeeID HAVING SUM(hours)>8)a WHERE date";
		        if(endDate.isEmpty()) {
		        	   System.out.println("null enddate");
		        	   query += "='"+reformattedStr+"'";
		           }else {
		        	   reformattedStr1 = myFormat.format(fromUser.parse(endDate));
		        	   query += " BETWEEN '"+reformattedStr+"' AND '"+reformattedStr1 +"'";
		           }
		           query += " AND task.approval IN('Pending','emailsent'))";
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
		               al.add(rs.getString(10));
		               System.out.println("al :: " + al);
		               pid_list.add(al);
		           }

		           request.setAttribute("piList", pid_list);
		           System.out.println(pid_list);
		           
		           RequestDispatcher view = request.getRequestDispatcher("/Director/Approval.jsp");
		           view.forward(request, response);
		           con.close();
		           System.out.println("Disconnected!");
		         
		       } catch (Exception e) {
		           e.printStackTrace();
		       }
			}

		}
