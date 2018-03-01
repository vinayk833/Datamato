package com.email.notification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.login.util.DBConnection;

public class ManagerNotification {
	
	GMailServer sender =new GMailServer(Constants.setFrom, Constants.setPassword);
	
	public void ManagerMail(){
		System.out.println("inside manager mail");
		String managerid[] = new String[100];
		String manager[] = new String[100];
		//sender.getID(managerid);
		try{
			/*Class.forName(Constants.CONNECTIONDRIVER);
			Connection con = DriverManager.getConnection(Constants.ConnectionString,
					Constants.DBUSER, Constants.DBPASSWORD);*/
			new DBConnection();
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			//Create your SQL query here
			String query = "select EMAIL,EmployeeName from users where ROLE='manager'";  
			//Execute the query
			ResultSet rs = st.executeQuery(query);
			//System.out.println(rs.getString("EMAIL"));
			int managercount=1;
			while(rs.next()){
				managerid[managercount] = rs.getString("EMAIL");
				manager[managercount] = rs.getString("EmployeeName");     
				managercount++;                 			
			}
			System.out.println(managercount);
			String[] days = new String[7];
			getWeekDate(days);
			//for sending mails to managers on weekly report
			for(int i=1;i<managercount;i++){
				String users_manager = "select * from task where date between '" + days[0] + "' AND '" + days[4] +"' AND EmployeeID IN(select EmployeeID from users where Approver='" + manager[i] + "') order by taskId";
				System.out.println(users_manager);
				ResultSet res = st.executeQuery(users_manager);
				String subject= "Weekly Report Details";
				String textbody="<!DOCTYPE html><html><head><style>\r\n" + 
						"table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%;}\r\n" + 
						"td, th {border: 1px solid #dddddd;text-align: left;padding: 8px;}\r\n" + 
						"tr:nth-child(even) {background-color: #dddddd;}\r\n" + 
						".button {background-color: #28ce25;border: none;color: white;padding: 16px 32px;text-align: center;font-size: 16px;\r\n" + 
						" margin: 4px 2px;opacity: 0.6;transition: 0.3s;display: inline-block;text-decoration: none;cursor: pointer;}\r\n" + 
						".button-success {background-color: #28ce25;border: none;color: white;padding: 16px 32px;text-align: center;\r\n" + 
						" font-size: 16px;margin: 4px 2px;opacity: 0.6;transition: 0.3s;display: inline-block;text-decoration: none;\r\n" + 
						" cursor: pointer;}\r\n" + 
						".button-danger {background-color: #d62613;border: none;color: white;padding: 16px 32px;text-align: center;\r\n" + 
						" font-size: 16px;margin: 4px 2px;opacity: 0.6;transition: 0.3s;display: inline-block;text-decoration: none;\r\n" + 
						" cursor: pointer;}\r\n" + 
						".button-success:hover {opacity: 1} .button-danger:hover {opacity: 1}\r\n" + 
						"</style></head><body><h2>Daily Report</h2>\r\n" + 
						"<table><tr><th>Company</th><th>Contact</th><th>Country</th></tr>\r\n";

				while(res.next()) {
					textbody += "<tr><td>" + res.getString("EmployeeID") + "</td><td>" + res.getString("date") + "</td><td>" + res.getString("ProjName") +"</td><td>" + res.getString("TaskCat") +"</td><td>" + res.getString("description") +"</td><td>" + res.getString("hours") +"</td></tr>\r\n";
				}

				textbody +="</table><br><br><center><button type=\"button\" class=\"button-success\">Success</button>\r\n" + 
						"<button type=\"button\" class=\"button-danger\">Danger</button></center></body></html>";
				sender.sendMail(subject,textbody,Constants.setFrom,managerid[i]);
			}       



		}catch (Exception e){
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
