package com.email.notification;
import java.util.TimerTask;

public class Testing  extends TimerTask
{
	
	
	
	public void run(){
		
		UserNotification UN=new UserNotification();
		
		ManagerNotification MN=new ManagerNotification();
		System.out.println("running in testing");
		UN.UserMail();
		MN.ManagerMail();
	}
	
	
	

	
}