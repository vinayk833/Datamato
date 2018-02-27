package com.login.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.util.DBConnection;

/**
 * Servlet implementation class SendMailApproval
 */
@WebServlet("/SendMailApproval")
public class SendMailApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendMailApproval() {
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
		// TODO Auto-generated method stub

		try {

			Connection con = null;
			con = DBConnection.createConnection();
			System.out.println("connected!.....");
			String employeeID  = (String) request.getSession().getAttribute("Admin");
			String date = request.getParameter("date");
			request.setAttribute(date, "dateValue");
			SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String reformattedStr = null;
			try {

				reformattedStr = myFormat.format(fromUser.parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			//String q = "";
			
			
			String query = "select date,ProjName,proid,TaskCat,description,hours from task "
					+ "where date='" + reformattedStr + "' AND EmployeeID='" + employeeID + "' ";
			System.out.println("query " + query);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				System.out.println(rs.getString(1) + "/"+rs.getString(2) + "/"+rs.getString(3) + "/"+rs.getString(4) + "/"+
						rs.getString(5) + "/" + rs.getString(6));
			}


			sendMail(rs,employeeID,date, request);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void sendMail(ResultSet rs, String employeeID, String date, HttpServletRequest request ) {
		// TODO Auto-generated method stub
		// Recipient's email ID needs to be mentioned.
		String to = "jyoti.kumari@datamato.com";
		String cc = "priyanka.jogdand@datamato.com";

		// Sender's email ID needs to be mentioned
		String from = "mathew.flicker123@gmail.com";
		final String username = "mathewflicker123@gmail.com";//change accordingly
		final String password = "flicker12345";//change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

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

			    
			    //System.out.println(baseUrl);
			// Send the actual HTML message, as big as you like
			message.setContent(
					"<h1><a href=\"" + baseUrl + "/ApprovalChecker?approval=yes&empid="+employeeID+"&date="+date+"\">Approve</a> task</h1>"
					 +"\n" +
					"<h1><a href=\"" + baseUrl + "/ApprovalChecker?approval=no&empid="+employeeID+"&date="+date+"\">Reject</a> task</h1>",
					"text/html");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}
