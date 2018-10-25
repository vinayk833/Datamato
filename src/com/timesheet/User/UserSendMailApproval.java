package com.timesheet.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.email.notification.Constants;
import com.login.util.DBConnection;

/**
 * Servlet implementation class UserSendMailApproval
 */
@WebServlet("/UserSendMailApproval")
public class UserSendMailApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BigInteger bi=null;
	  String bigInt=null; 
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSendMailApproval() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Connection con = null;
		con = DBConnection.createConnection();
		System.out.println("connected for Both!.....");
		String Status= request.getParameter("Reject");
		System.out.println("Here the status is --------->>" +Status);
			if(Status==null)	{
		try {
			System.out.println("Here the status is --------->>" +Status);
			String employeeID  = (String) request.getSession().getAttribute("User");
			
			Statement statement = con.createStatement();
			ResultSet r = statement.executeQuery("Select EmployeeName from users where EmployeeID ='"+ employeeID+"'");
			String employeeName = null;
			while(r.next()) {
				employeeName = r.getString("EmployeeName");
			}
			try{
				  bi = new BigInteger(employeeID);
				  System.out.println(bi);
				  bigInt=bi.toString();
				  System.out.println(bigInt);
			  }catch(Exception e){
				  System.out.println("Error in converting String to BIG INT");
			  }
			String date = request.getParameter("date");
			//String employeeID = request;
			System.out.println(bigInt);
			request.setAttribute("date",date);
			System.out.println(date);
			SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String reformattedStr = null;
			String emailid = null;
			try {

				reformattedStr = myFormat.format(fromUser.parse(date));
				System.out.println(reformattedStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			Statement stt = con.createStatement();

			String squery = "select EMAIL from users where EmployeeName =(select Approver from users where EmployeeID='"+ bigInt+"')";

			ResultSet res = stt.executeQuery(squery);
			
			
			while(res.next()){
				emailid = res.getString("EMAIL");
				System.out.println(emailid);
			}
			
			String query = "select date,ProjName,proid,TaskCat,description,hours,approval from task "
					+ "where date='" + reformattedStr + "' AND EmployeeID='" + bigInt + "' ";
			System.out.println("query " + query);
			
			
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			
			String approve = null;
			int errorstatus = 0;
			while(rs.next()) {
				approve = rs.getString("approval");
			}
			System.out.println(approve);

			
			
		
			
			Statement st1 = con.createStatement();
			rs = st1.executeQuery(query);
			//check if mail is sent or not 
			if((approve.equals("Approved"))||(approve.equals("Rejected"))||(approve.equals("Email sent"))) {
				System.out.println("error");
				errorstatus=1;
			}else {
				//execute if mail is not sent
				sendMail(rs,employeeName,emailid,date,request);
				Statement s = con.createStatement();
				String sql = "Update task set approval='Email sent' where  date='" + reformattedStr + "' AND EmployeeID='" + bigInt + "' ";
				System.out.println(sql);
				s.executeUpdate(sql);
				System.out.println("updated successfully");
			}
			
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("/JSP/emp_event.jsp");
			//request.getRequestDispatcher("/Admin/AddTask.jsp").forward(request, response);
			requestDispatcher.include(request, response);
			if(errorstatus == 1) {
				out.println("<h4 style='color:red;margin-left:600px;margin-top:-230px;'>You have already send mail on this date</h4>");
			}
			else{
				out.println("<h4 style='color:red;margin-left:600px;margin-top:-230px;'>Sent mail Successfully</h4>");

			}
			rs.close();
			st.close();
			res.close();
			stt.close();
			
			r.close();
			statement.close();
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			try {
				con.close();
				System.out.println("db closed for status null..In finally block");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			}
		else{
System.out.println("Here the Status is---------->>" +Status);
            
			try {

				String employeeID  = (String) request.getSession().getAttribute("User");
				
				
				
				
				
				Statement statement = con.createStatement();
				ResultSet r = statement.executeQuery("Select EmployeeName from users where EmployeeID ='"+ employeeID+"'");
				String employeeName = null;
				while(r.next()) {
					employeeName = r.getString("EmployeeName");
				}
				try{
					  bi = new BigInteger(employeeID);
					  System.out.println(bi);
					  bigInt=bi.toString();
					  System.out.println(bigInt);
				  }catch(Exception e){
					  System.out.println("Error in converting String to BIG INT");
				  }
				String date = request.getParameter("startdate");
				
				System.out.println("After get parameter" +date);
				//String employeeID = request;
				System.out.println(bigInt);
				request.setAttribute("startdate",date);
				System.out.println("After set parameter" +date);
				
				SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
				String reformattedStr = null;
				String emailid = null;
				try {

					reformattedStr = myFormat.format(fromUser.parse(date));
					System.out.println(reformattedStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
//				///////////////////////////////////////
//				
//              Statement statement1 = con.createStatement();
//              ResultSet r2=statement1.executeQuery("select sum from task  date='" + reformattedStr + "' AND EmployeeID='" + bigInt + "' " );		
//
//              String sum = null;
//				while(r2.next()) {
//					employeeName = r.getString("sum");
//					System.out.println(sum);
//				}
//				
//				request.setAttribute("Totalhour",sum);
//				
//				
//				
//				
//				
//				
//				
//				
//			///////////////////////////////////////////////	
				
				Statement stt = con.createStatement();

				String squery = "select EMAIL from users where EmployeeName =(select Approver from users where EmployeeID='"+ bigInt+"')";

				ResultSet res = stt.executeQuery(squery);
				
				
				while(res.next()){
					emailid = res.getString("EMAIL");
					System.out.println(emailid);
				}
				
				String query = "select date,ProjName,proid,TaskCat,description,hours,approval from task "
						+ "where date='" + reformattedStr + "' AND EmployeeID='" + bigInt + "' ";
				System.out.println("query " + query);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				/*while(rs.next()){
					System.out.println(rs.getString(1) + "/"+rs.getString(2) + "/"+rs.getString(3) + "/"+rs.getString(4) + "/"+
							rs.getString(5) + "/" + rs.getString(6));
				}*/
				
				String approve = null;
				int errorstatus = 0;
				while(rs.next()) {
					approve = rs.getString("approval");
				}
				System.out.println(approve);
				Statement st1 = con.createStatement();
				rs = st1.executeQuery(query);
				//check if mail is sent or not  
				if((approve.equals("Approved"))||(approve.equals("Email sent"))) {
					System.out.println("error");
					errorstatus=1;
				}else {
				
					//execute if mail is not sent
					sendMail(rs,employeeName,emailid,date,request);
					Statement s = con.createStatement();
					String sql = "Update task set approval='Email sent' where approval='Rejected' AND date='" + reformattedStr + "' AND EmployeeID='" + bigInt + "' ";
					System.out.println(sql);
					s.executeUpdate(sql);
					System.out.println("updated successfully");
					
				}
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("/JSP/Resubmit.jsp");
				//request.getRequestDispatcher("/Admin/AddTask.jsp").forward(request, response);
				requestDispatcher.include(request, response);
				
				if(errorstatus == 1) {
					System.out.println("Here error status is" +errorstatus);
					out.println("<h4 style='color:red;margin-left:600px;margin-top:0px;'>You have already send mail on this date</h4>");
				}
				else{
					out.println("<h4 style='color:red;margin-left:600px;margin-top:0px;'>Sent mail Successfully</h4>");

				}				
				
				rs.close();
				st.close();
				res.close();
				stt.close();
				
				r.close();
				statement.close();
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			finally{
				try {
					con.close();
					System.out.println("db closed for status Reject..In finally block");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
	}

	private void sendMail(ResultSet rs, String employeeName, String emailid,
			String date, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String to = emailid;
		//String cc = "priyanka.jogdand@datamato.com";

		// Sender's email ID needs to be mentioned
		String from = Constants.setFrom;
		final String username = Constants.setFrom;//change accordingly
		final String password =  Constants.setPassword;//change accordingly
		
		System.out.println(username);
		System.out.println(password);
		// Assuming you are sending email through relay.jangosmtp.net
		String host = Constants.mailhost;
		System.out.println(host);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Task Approval");
			String baseUrl = null;
			baseUrl =request.getScheme() + "://" +
			        request.getServerName() + ":" + request.getServerPort() +
			        request.getContextPath();
			
			String textbody="<!DOCTYPE html><html><head><style>\r\n" + 
              		"table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%;}\r\n" + 
              		"td, th {border: 1px solid #dddddd;text-align: left;padding: 8px;}\r\n" + 
              		"tr:nth-child(even) {background-color: #dddddd;}\r\n" + 
              		".button {background-color: #28ce25;border: none;color: white;padding: 16px 32px;text-align: center;font-size: 16px;\r\n" + 
              		"  margin: 4px 2px;opacity: 0.6;transition: 0.3s;display: inline-block;text-decoration: none;cursor: pointer;}\r\n" + 
              		".button-success {background-color: #28ce25;border: none;color: white;padding: 16px 32px;text-align: center;\r\n" + 
              		"  font-size: 16px;margin: 4px 2px;opacity: 0.6;transition: 0.3s;display: inline-block;text-decoration: none;\r\n" + 
              		"  cursor: pointer;}\r\n" + 
              		".button-danger {background-color: #d62613;border: none;color: white;padding: 16px 32px;text-align: center;\r\n" + 
              		"  font-size: 16px;margin: 4px 2px;opacity: 0.6;transition: 0.3s;display: inline-block;text-decoration: none;\r\n" + 
              		"  cursor: pointer;}\r\n" + 
              		".button-success:hover {opacity: 1}  .button-danger:hover {opacity: 1}\r\n" + 

              		"</style></head><body><h2>Daily Report</h2>\r\n" +
              		"<h2>Employee Name: "+ employeeName +"</h2>"+
              		"<table><tr><th>Date</th><th>Poject Name</th><th>Project ID</th><th>Task Category</th><th>Descrption</th><th>Hours</th></tr>\r\n";
              		
					while(rs.next()) {
		      			textbody += "<tr><td>" + rs.getString("date") + "</td><td>" + rs.getString("ProjName") + "</td><td>" + rs.getString("proid")+ "</td><td>" + rs.getString("TaskCat") + "</td><td>" + rs.getString("description")+ "</td><td>" + rs.getString("hours") +"</td></tr>\r\n";
		      		}

					textbody +="\r\n</table><a href=" + baseUrl + "/ApprovalChecker?approval=Approve&empid="+bigInt+"&date="+date+"\">APPROVE OR REJECT </a>\r\n" /*+ 
		              		" OR " + "<a href=" + baseUrl + "/ApprovalChecker?approval=no&empid="+bigInt+"&date="+date+"\">REJECT</a></center></body></html>"*/;
              		/*textbody +="\r\n</table><a href=" + baseUrl + "/ApprovalChecker?approval=yes&empid="+bigInt+"&date="+date+"\">APPROVE </a>\r\n" + 
              				" OR " + "<a href=" + baseUrl + "/ApprovalChecker?approval=no&empid="+bigInt+"&date="+date+"\">REJECT</a></center></body></html>";*/
              		
              		/*textbody +="</table><center><a href="+ baseUrl + "/ApprovalChecker?approval=yes&empid="+ employeeID +"&date="+date+""><input type="button" value="Update" class="button-success" /></a>" +
                    "<a href="+baseUrl+"/ApprovalChecker?approval=no&empid="+employeeID+"&date="+date+><input type="button" value="Update" class="button-danger" /></a>";
*/			    System.out.println(textbody);
			    //System.out.println(baseUrl);
			// Send the actual HTML message, as big as you like
			message.setContent(textbody,"text/html");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");


		} catch (Exception e) {
			e.printStackTrace();
			
		}

		
	}

	}
