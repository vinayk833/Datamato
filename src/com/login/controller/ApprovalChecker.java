package com.login.controller;

import java.io.IOException;
import java.math.BigInteger;
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
		BigInteger bi=null;
		  String bigInt=null;
		  try{
			  bi = new BigInteger(employeeId);
			  System.out.println(bi);
			  bigInt=bi.toString();
			  System.out.println(bigInt);
		  }catch(Exception e){
			  System.out.println("Error in converting String to BIG INT");
		  }
		//int empID = Integer.parseInt(employeeId);
		String Mdate = request.getParameter("date");
		 SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String MDate = null;
			try {

			    MDate = myFormat.format(fromUser.parse(Mdate));
			} catch (ParseException e) {
			    e.printStackTrace();
			}
		//String employeeId = request.getParameter("empid");
		System.out.println(approvalStatus);
		System.out.println(bigInt);
		try {
			Connection con = null;
			con = DBConnection.createConnection();
			System.out.println("connected!.....");
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("SELECT ROLE,EmployeeName FROM users WHERE EmployeeName=(SELECT Approver FROM users WHERE EmployeeID='"+employeeId+"')");
			String approverRole = null;
			String approver = null;
			while(res.next()) {
				approverRole = res.getString("ROLE");
				approver = res.getString("EmployeeName");
			}
			System.out.println(approverRole);
			switch (approverRole) {
			case "Manager":System.out.println("Manager");
						request.setAttribute("MDate",MDate);
				           
				           ArrayList al = null;
				           ArrayList pid_list = new ArrayList();
				           String myquery = "SELECT users.EmployeeName FROM customers.users where users.EmployeeID=" + employeeId;
				           
				           Statement st1 = con.createStatement();
				           ResultSet rs1 = st1.executeQuery(myquery);
				           String var=null;
				           while(rs1.next()){
				        	   var= rs1.getString("EmployeeName"); 
				        	   System.out.println(var);
				           }
				           System.out.println("query ==========" + myquery);
				       //  String query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval from task where EmployeeID='" + employeeID + "'";
				           //String query = "SELECT distinct task.taskid,task.EmployeeID,users.EmployeeName,task.date,task.ProjName,task.proid,task.TaskCat,task.description,task.hours,task.approval FROM customers.users  INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where task.approval='Pending' AND task.hours>8 AND users.Approver='"+var+"' AND MONTH(task.date)=MONTH(CURRENT_DATE()) AND YEAR(task.date) = YEAR(CURRENT_DATE())";
				           String query = "SELECT task.taskid,task.EmployeeID,users.EmployeeName,task.date,task.ProjName,task.proid,task.TaskCat,task.description,task.hours,task.approval FROM customers.users INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where users.Approver='"+approver+"' AND date=(SELECT DISTINCT a.date FROM(SELECT date FROM task GROUP BY date,EmployeeID HAVING SUM(hours)>8)a WHERE date='"+MDate+"' AND task.approval='Pending' OR 'emailsent')";
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
				           
				           RequestDispatcher view = request.getRequestDispatcher("/ProjMag/Approval.jsp");
				           view.forward(request, response);
				           
				           res.close();
				           st.close();
				           con.close();
						   break;
				
			case "Director":System.out.println("Director");
							request.setAttribute("MDate",MDate);
					         
					           ArrayList all = null;
					           ArrayList pid_list1 = new ArrayList();
					          
					           String myquery1 = "SELECT users.EmployeeName FROM customers.users where users.EmployeeID=" + employeeId;
					           Statement st2 = con.createStatement();
					             ResultSet rs2 = st2.executeQuery(myquery1);
					             String var1=null;
					             while(rs2.next()){
					          	   var1= rs2.getString("EmployeeName"); 
					          	   System.out.println(var1);
					             }
					             System.out.println("query ==========" + myquery1);
					         //  String query = "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours,approval from task where EmployeeID='" + employeeID + "'";
					             //String query = "SELECT distinct task.taskid,task.EmployeeID,users.EmployeeName,task.date,task.ProjName,task.proid,task.TaskCat,task.description,task.hours,task.approval FROM customers.users  INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where task.approval='Pending' AND task.hours>8 AND users.Approver='"+var+"' AND MONTH(task.date)=MONTH(CURRENT_DATE()) AND YEAR(task.date) = YEAR(CURRENT_DATE())";
					        String query1 = "SELECT task.taskid,task.EmployeeID,users.EmployeeName,task.date,task.ProjName,task.proid,task.TaskCat,task.description,task.hours,task.approval FROM customers.users INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where users.Approver='"+approver+"' AND date=(SELECT DISTINCT a.date FROM(SELECT date FROM task GROUP BY date,EmployeeID HAVING SUM(hours)>8)a WHERE date='"+MDate+"' AND task.approval='Pending' OR 'emailsent')";  
					      /*  String query =  "SELECT taskId,EmployeeID,date,ProjName,proid,TaskCat,description,hours FROM task WHERE date BETWEEN " +"'" + startDate +"'" + " AND " + "'"+ endDate + "'" + " AND " 
					                + "EmployeeID= (SELECT EmployeeID FROM users WHERE EmployeeName=\""+ EmployeeName +"\")";*/
					           System.out.println("query " + query1);
					           Statement st3 = con.createStatement();
					          ResultSet rs3 = st.executeQuery(query1);
				
					           while (rs3.next()) {
					               al = new ArrayList();
					               al.add(rs3.getString(1));
					               al.add(rs3.getString(2));
					               al.add(rs3.getString(3));
					               al.add(rs3.getString(4));
					               al.add(rs3.getString(5));
					               al.add(rs3.getString(6));
					               al.add(rs3.getString(7));
					               al.add(rs3.getString(8));
					               al.add(rs3.getString(9));
					               al.add(rs3.getString(10));
					               System.out.println("al :: " + al);
					               pid_list1.add(al);
					           }
				
					           request.setAttribute("piList", pid_list1);
					           System.out.println(pid_list1);
					           
					           RequestDispatcher view1 = request.getRequestDispatcher("/Director/Approval.jsp");
					           view1.forward(request, response);
					           rs2.close();
					           st2.close();
					           con.close();
							break;

			default:
				break;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/*if(approvalStatus.equalsIgnoreCase("yes")){
			try {
				Connection con = null;
				con = DBConnection.createConnection();
				System.out.println("connected!.....");
				
				String query = "UPDATE task SET approval='yes' WHERE EmployeeID="+ bigInt + " AND " + "date='" +reformattedStr+"'";
				System.out.println(query);
				Statement stmt = null;
				stmt = con.createStatement();
				stmt.executeUpdate(query);
				RequestDispatcher view = request.getRequestDispatcher("/Admin/confrimApproval.jsp");
		        view.include(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if(approvalStatus.equalsIgnoreCase("no")){
			try {
				Connection con = null;
				con = DBConnection.createConnection();
				System.out.println("connected!.....");
				
				String query = "UPDATE task SET approval='no' WHERE EmployeeID="+ bigInt + " AND " + "date='" +reformattedStr+"'";
				System.out.println(query);
				Statement stmt = null;
				stmt = con.createStatement();
				stmt.executeUpdate(query);
				RequestDispatcher view = request.getRequestDispatcher("/Admin/rejectApproval.jsp");
		        view.include(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}*/
		RequestDispatcher view = request.getRequestDispatcher("/Director/Approval.jsp");
        view.include(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
