package com.login.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
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

import com.email.notification.Constants;
import com.login.util.DBConnection;

/**
 * Servlet implementation class PasswordMail
 */
@WebServlet("/PasswordMail")
public class PasswordMail extends HttpServlet {
	public String Erroremailid;
	public String emailid;
	
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("inside PasswordMail");
		Connection con = null;
		con = DBConnection.createConnection();
		try{
			
			Statement st = con.createStatement();
			String email = request.getParameter("email");
			ResultSet rs = st.executeQuery("select EmployeeID,EMAIL from users where EMAIL='" + email +"'");
			
			
			if(!rs.next()){
				System.out.println("no data");
				Erroremailid = "Email ID doesn't exists";
				request.setAttribute(Erroremailid, rs);
				request.getRequestDispatcher("/JSP/forgotpasswordmail.jsp").forward(request, response);
			}else{
				System.out.println("data");
				String employeeID = rs.getString("EmployeeID");
				sendMail(employeeID, email, request);
				request.getRequestDispatcher("/JSP/resetmail.jsp").forward(request,response);
				
			}
			rs.close();
			st.close();
			
			
		}catch(Exception e){
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
	
	
	public void sendMail(String employeeID, String emailid, HttpServletRequest request ) {
		// TODO Auto-generated method stub
		// Recipient's email ID needs to be mentioned.
		String to = emailid;
		

		// Sender's email ID needs to be mentioned
		String from = Constants.setFrom;
		final String username = Constants.setFrom;//change accordingly
		final String password =  Constants.setPassword;//change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = Constants.mailhost;

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
			message.setSubject("Password Reset Mail");
			String baseUrl = null;
			baseUrl =request.getScheme() + "://" +
			        request.getServerName() + ":" + request.getServerPort() +
			        request.getContextPath();
			
			String textbody = "Click the following link to reset your password"+
			"<a href=" + baseUrl + "/Reset?empid="+employeeID+">Click here to Reset your Password\r\n";
			    System.out.println(textbody);
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
