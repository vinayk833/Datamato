package com.timesheet.Manager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Servlet implementation class ApproveStatus
 */
@WebServlet("/ApproveStatus")
public class ApproveStatus extends HttpServlet {
private static final long serialVersionUID = 1L;
String query = null;
String employeeid=null;
String sdate=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
System.out.println("inside Approval status");
String selectopt = request.getParameter("select");
System.out.println(selectopt);
//String taskID = request.getParameter("taskid");
//System.out.println(taskID);
String startdate =request.getParameter("startdate");
System.out.println(startdate);
SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
    String reformattedStr = null;
    try {
    System.out.println();
        reformattedStr = myFormat.format(fromUser.parse(startdate));
        //reformattedStr1 = myFormat.format(fromUser.parse(endDate));
        System.out.println( "Date" +reformattedStr);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    
String[] list= request.getParameterValues("list");
List l =  Arrays.asList(list); 
  //request.setAttribute("names", l);
  //System.out.println(Arrays.toString(l.toArray()));
System.out.println(l.size());
  for(int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
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
      System.out.println("-------"+eid+"-----");
      int c=0;
      for (String temp: eid){

                    System.out.println(temp);
                    if(c==0){
                    query ="Update task set approval='Approved' where EmployeeID='"+temp+"'";
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
String textbody="On "+sdate+" Task is Aproved.";      
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


//
//@@@@@@@@@@@
     
     
      st.executeUpdate(query);
      System.out.println(query);
     
   // st.executeUpdate("Update task set approval='Approve' where taskid="+l.get(i));
      }
     
      //request.getRequestDispatcher("DisplayApproval").forward(request, response);
      break;
     
      case "reject": System.out.println("reject case");
     
   
     
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
String textbody="On "+sdate+" Task is Rejected.";      
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
      case "pending": System.out.println("Pending case");
/*for(int i=0;i<l.size();i++) {
Statement st =con.createStatement();
st.executeUpdate("Update task set approval='yes' where taskid="+l.get(i));
}*/
//request.getRequestDispatcher("DisplayApproval").forward(request, response);
      break;
      }
      request.getRequestDispatcher("DisplayApproval").forward(request, response);
     
     
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




