package com.email.notification;
import java.util.TimerTask;

public class Testing  extends TimerTask
{
	
	
	
	public void run(){
		
		UserNotification UN=new UserNotification();
		
		ManagerNotification MN=new ManagerNotification();
		System.out.println("\nRunning scheduled email...\n");
		UN.UserMail();
		MN.ManagerMail();
	}
	
	
	

	
}