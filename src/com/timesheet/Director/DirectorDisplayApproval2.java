package com.timesheet.Director;

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

@WebServlet("/DirectorDisplayApproval2")
public class DirectorDisplayApproval2 extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	public static String EmployeeID;
 public static String date;
       
    
	
	
    public DirectorDisplayApproval2() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		EmployeeID = request.getParameter("EmployeeId");
		date = request.getParameter("date");
		System.out.println("Here employee ID is......> " +EmployeeID);
		System.out.println("Here date is......> " +date);
		
		Statement st=null;
		Connection con = null;
		con = DBConnection.createConnection();
		System.out.println("connected!.....");
		String query1 = "SELECT * FROM task WHERE EmployeeID =" +EmployeeID;
		String query2 = "SELECT * FROM task WHERE date =" +date;
		
		System.out.println("Query 1 is------------>>" +query1);
		System.out.println("Query 2  is------------>>" +query2);
		Statement st1=null;
		
try{
      	
      	
      	
      	 String employeeID  = (String) request.getSession().getAttribute("Director");
      	 //String EmployeeName = request.getParameter("EmpName");
           //String startDate = request.getParameter("startdate");
           //String endDate = request.getParameter("enddate");
      	// String startDate = request.getParameter("startdate");
          
      	// System.out.println("selected date");
      	// System.out.println(startDate);
          
          // request.setAttribute("startdate",startDate);
           //SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
   		//SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
   		//String reformattedStr = null;
//   		try {
//   			System.out.println();
//   		    reformattedStr = myFormat.format(fromUser.parse(startDate));
//   		    //reformattedStr1 = myFormat.format(fromUser.parse(endDate));
//   		    System.out.println(reformattedStr);
//   		} catch (ParseException e) {
//   		    e.printStackTrace();
//   		}
   		
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
        //String query = "SELECT task.taskid,task.EmployeeID,users.EmployeeName,task.date,task.ProjName,task.proid,task.TaskCat,task.description,task.hours,task.approval FROM customers.users INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where users.Approver='"+var+"' AND date IN(SELECT DISTINCT a.date FROM(SELECT date FROM task GROUP BY date,EmployeeID HAVING SUM(hours)>8)a WHERE date BETWEEN '"+reformattedStr+"' AND '"+reformattedStr1 +"' AND task.approval IN('Pending','emailsent'))";
      //  String query = "SELECT task.taskid,task.EmployeeID,users.EmployeeName,task.date,task.ProjName,task.proid,task.TaskCat,task.description,task.hours,task.approval FROM customers.users INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where users.Approver='"+var+"' AND  users.EmployeeId= '"+EmployeeID+"' date IN(SELECT DISTINCT a.date FROM(SELECT date FROM task GROUP BY date,EmployeeID HAVING SUM(hours)>8)a WHERE date";
       
     	//   query += "='"+reformattedStr+"'";
                
        //query += " AND task.approval IN('Email sent'))";
        
        
        
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
        
        RequestDispatcher view = request.getRequestDispatcher("/Director/DirectorApproval.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
