package com.timesheet.Director;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
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
 * Servlet implementation class ApproveStatusDirector
 */
@WebServlet("/ApproveStatusDirector")
public class ApproveStatusDirector extends HttpServlet {
private static final long serialVersionUID = 1L;
String query = null;
String employeeid=null;
String sdate=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveStatusDirector() {
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
System.out.println("inside Approval status");
String selectopt = request.getParameter("select");
System.out.println(selectopt);
//String query = null;
// String[] values=req.getParameterValues("");
String[] list= request.getParameterValues("list");
List l =  Arrays.asList(list); 
  Connection con = null;
      con = DBConnection.createConnection();
try {
 
       
      switch(selectopt) {
      case "approve": System.out.println("Approve case");
      String a=null;
    String[] eid = null;
   
     
      for(int i=0;i<l.size();i++) {
      Statement st =con.createStatement();
      System.out.println(l.get(i));
        a = (String) l.get(i);
         System.out.println(a);
      eid = a.split(",");
      int c=0;
      for (String temp: eid){

                    System.out.println(temp);
                    if(c==0){
                    query ="Update task set approval='Approved' where EmployeeID='"+temp+"'";
employeeid=temp;
                    
                    
                    
                    
                    System.out.println("----------empid--------"+employeeid);
                    c=1;                     }   
                    else{
                    query+= " AND date='"+temp+"'";
                    sdate=temp;
                    System.out.println("-----------"+sdate);
                    c=0;
                   
                 
                    }
      }
      System.out.println(employeeid+"*****"+sdate);
      PreparedStatement pst=con.prepareStatement("SELECT EMAIL FROM users where employeeid=?");
      pst.setString(1, employeeid);
      ResultSet rs=pst.executeQuery();
      rs.next();
     

// sendmail
String to = rs.getString(1);
//String cc = "priyanka.jogdand@datamato.com";

// Sender's email ID needs to be mentioned
String from = Constants.setFrom;
final String username = Constants.setFrom;//change accordingly
final String password =  Constants.setPassword;//change accordingly
System.out.println(to+"----"+from+"-----"+password);
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
message.setSubject("Task Approval Status");
String baseUrl = null;
baseUrl =request.getScheme() + "://" +
        request.getServerName() + ":" + request.getServerPort() +
        request.getContextPath();
String textbody="on "+sdate+" Task is Aproved.";      
  System.out.println("----"+textbody);
    //System.out.println(baseUrl);
// Send the actual HTML message, as big as you like
message.setContent(textbody,"text/html");

// Send message
Transport.send(message);

System.out.println("Sent message successfully....");

} catch (Exception e) {
e.printStackTrace();
System.out.println(e);
}


      st.executeUpdate(query);
      System.out.println(query);
     
   // st.executeUpdate("Update task set approval='Approve' where taskid="+l.get(i));
      }
    //request.getRequestDispatcher("DisplayApproval").forward(request, response);
      break;
     
      case "reject": System.out.println("reject case");
     
      // String a=null;
    //String[] eid = null;
   
     
      for(int i=0;i<l.size();i++) {
      Statement st =con.createStatement();
      System.out.println(l.get(i));
        a = (String) l.get(i);
         System.out.println(a);
      eid = a.split(",");
      int c=0;
      for (String temp: eid){

                    System.out.println(temp);
                    if(c==0){
                    query ="Update task set approval='Rejected' where EmployeeID='"+temp+"'";
                   // query ="Update task set approval='Approve' where EmployeeID='"+temp+"'";
                employeeid=temp;
                                    
                                    
                                    
                                    
                                    System.out.println("----------empid--------"+employeeid);
                                    c=1;  

                    }else{
                    query+= " AND date='"+temp+"'";
                    sdate=temp;
                    System.out.println("-----------"+sdate);
                    c=0;
                   
                   
                 
                    }
      }
      System.out.println(employeeid+"*****"+sdate);
      PreparedStatement pst=con.prepareStatement("SELECT EMAIL FROM users where employeeid=?");
      pst.setString(1, employeeid);
      ResultSet rs=pst.executeQuery();
      rs.next();
     

// sendmail
String to = rs.getString(1);
//String cc = "priyanka.jogdand@datamato.com";

// Sender's email ID needs to be mentioned
String from = Constants.setFrom;
final String username = Constants.setFrom;//change accordingly
final String password =  Constants.setPassword;//change accordingly
System.out.println(to+"----"+from+"-----"+password);
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
message.setSubject("Task Approval Status");
String baseUrl = null;
baseUrl =request.getScheme() + "://" +
        request.getServerName() + ":" + request.getServerPort() +
        request.getContextPath();
String textbody="on "+sdate+" Task is Rejected.";      
  System.out.println("----"+textbody);
    //System.out.println(baseUrl);
// Send the actual HTML message, as big as you like
message.setContent(textbody,"text/html");

// Send message
Transport.send(message);

System.out.println("Sent message successfully....");

} catch (Exception e) {
e.printStackTrace();
System.out.println(e);
}
     
      st.executeUpdate(query);
      System.out.println(query);
     
   
   
   
   }
   //request.getRequestDispatcher("DisplayApproval").forward(request, response);
   break;
      case "pending": System.out.println("pending case");
/*for(int i=0;i<l.size();i++) {
Statement st =con.createStatement();
st.executeUpdate("Update task set approval='yes' where taskid="+l.get(i));
}*/
//request.getRequestDispatcher("DisplayApproval").forward(request, response);
break;
      }
      request.getRequestDispatcher("DisplayDirectorApproval").forward(request, response);
}catch(Exception e) {
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

}

