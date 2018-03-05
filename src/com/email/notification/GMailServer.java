package com.email.notification;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class GMailServer extends javax.mail.Authenticator
{
    private String mailhost =Constants.mailhost; ; //"smtp.mail.yahoo.com"; //"smtp.gmail.com";
    private String smtp_port=Constants.smtp_port;
    private String user;
    private String password;
    private Session session;  
 
    public GMailServer(String user, String password) {
        this.user = user;
        this.password = password;  
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host",mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
       props.put("mail.smtp.socketFactory.port",smtp_port);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");  
 
        session = Session.getDefaultInstance(props, this);
        
        
    }  
    
    /*public void getID(String[] emailTo){
     String emailTO[] = new String[100] ;
        
       
    }*/
 
    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(user, password);
    }  
 
    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception
    {
    	
    	//Session session = Session.getDefaultInstance(props);
    	
    	MimeMessage message = new MimeMessage(session);
    	message.setFrom(new InternetAddress(sender));
    	message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipients));
    	message.setSubject(subject);
    	MimeMultipart multipart = new MimeMultipart();
    	BodyPart messageBodyPart = new MimeBodyPart();
    	//String textbody = "hello mail";
    	messageBodyPart.setContent(body, "text/html");
    	
    	multipart.addBodyPart(messageBodyPart); 
    	message.setContent(multipart);

    	//Conect to smtp server and send Email
    	Transport transport = session.getTransport("smtp"); 
    	transport.connect(mailhost, sender, password);
    	transport.sendMessage(message, message.getAllRecipients());
    	transport.close();
    	System.out.println("Mail sent successfully..."); 


    	
    	
    	
        /*MimeMessage message = new MimeMessage(session);
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
        message.setSender(new InternetAddress(sender));
        message.setSubject(subject);
        message.setDataHandler(handler);
        if (recipients.indexOf(',') > 0)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        else
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
        Transport.send(message);*/
    }  
 
    /*public class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;  
 
        public ByteArrayDataSource(byte[] data, String type) {
            super();
            this.data = data;
            this.type = type;
        }  
 
        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
        }  
 
        public void setType(String type) {
            this.type = type;
        }  
 
        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }  
 
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }  
 
        public String getName() {
            return "ByteArrayDataSource";
        }  
 
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }*/
}
