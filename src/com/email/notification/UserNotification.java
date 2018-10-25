package com.email.notification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.login.util.DBConnection;

public class UserNotification {
	
	
	GMailServer sender =new GMailServer(Constants.setFrom, Constants.setPassword);
	
public void UserMail() {
	
	System.out.println("\\ Notification to Users triggered...");	
		//String userid[] = new String[200];
		//String user[] = new String[200];
		String mailDate[] = new String[7];
		String date[] = new String[7];
		try{
			String Connectstring = Constants.ConnectionString;
			System.out.println(Connectstring);
			new DBConnection();
			/*Class.forName(Constants.CONNECTIONDRIVER);
			Connection con = DriverManager.getConnection(Constants.ConnectionString,
					Constants.DBUSER, Constants.DBPASSWORD);*/
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			//Create your SQL query here
			//String query = "select EmployeeID,EMAIL,EmployeeName from customers.users";
			//Execute the query
			ResultSet rs = st.executeQuery("select * from customers.users");
			/*int usercount=1;
			while(rs.next()){
				userid[usercount] = rs.getString("EMAIL");
				user[usercount] = rs.getString("EmployeeName");     
				usercount++;   
				System.out.println(rs.getString("EMAIL"));
			}
			System.out.println(usercount);*/
			String[] days = new String[7];
			getWeekDate(days);
			
			for(int i=0;i<7;i++){
				System.out.println(days[i]);
			}
			int w=1;
			int status=1;
			System.out.println(status);
			System.out.println("date "+ days[0]);
			//for(int i=1;i<usercount;i++){
			while(rs.next()){
				System.out.println(rs.getString("EMAIL"));
				String email = rs.getString("EMAIL");
				System.out.println(email);
				System.out.println(rs.getString("EmployeeName"));
				String name = rs.getString("EmployeeName");
				System.out.println(name);
				String datequery = "select distinct date from task where date BETWEEN '" + days[0] + "' AND '" + days[4] +"' AND EmployeeID=(select EmployeeID from users where EmployeeName='" + name + "')";
				//String datequery ="select distinct date from task where date BETWEEN '2018-02-19' AND '2018-03-23' AND EmployeeID=(select EmployeeID from users where EmployeeName='Akshay')";
				String sub= "Task Submit Reminder";
				ResultSet res = st.executeQuery(datequery);
				
				System.out.println(datequery);
				while(res.next()){
					System.out.println(res.getString("date"));
					date[w] = res.getString("date");
				}
				//System.out.println(w);
				int y=1;
				for(int e=0;e<5;e++) {
					for(int j=0;j<w;j++) {
						if(days[e]!=date[j]) {
							mailDate[y]=days[e];
							System.out.println(mailDate[y]);
							y++;
							status = 0;
							System.out.println(status);
						}
					}
				}
				/*Statement s = con.createStatement();
  			ResultSet r = s.executeQuery("select EmployeeName from users where ")*/
				if(status == 0)
				{
					String Mailbody = "Hello " + name + ",<br>You have forgot to fill your daily report status on following days:";
					for(int s=1;s<y;s++) {
						if(s!=y-1) {
							Mailbody += " " + mailDate[s] + ",";
						}else {
							Mailbody += " and "+ mailDate[s];
						}

					}
					//System.out.println(Mailbody);
					
					System.out.println(email);
					sender.sendMail(sub,Mailbody,Constants.setFrom,email);
					status=1;
					System.out.println(status);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

//fetch weekly dates
	public static void getWeekDate(String[] days){
		System.out.println("inside get weeks");
		Calendar now = Calendar.getInstance();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String[] daysarray = new String[7];
		int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
		now.add(Calendar.DAY_OF_MONTH, delta );
		for (int i = 0; i < 7; i++)
		{
			daysarray[i] = format.format(now.getTime());
			now.add(Calendar.DAY_OF_MONTH, 1);
			//System.out.println(daysarray[i]);
			days[i]=daysarray[i];
		}

		//System.out.println(Arrays.toString(days));
	}


}
