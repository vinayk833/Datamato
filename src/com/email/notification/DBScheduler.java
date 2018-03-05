package com.email.notification;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class DBScheduler  {
	
	public void callScheduler() throws Exception
	 {
	 
	 System.out.println("Scheduler Starterd ...\n");
	
	 ReadPropertiesFile.readConfig();
	 Timer timer = new Timer();
	 Calendar date = Calendar.getInstance();
	 System.out.println("Email Scheduled on "+Constants.day+" at "+Constants.TimeHour+":"+Constants.TimeMinute+"\n");
	 
	 
	
	 if(Constants.day.equalsIgnoreCase("sunday"))
	 {
		 date.set(
	 			 Calendar.DAY_OF_WEEK,
	 	         Calendar.SUNDAY);
		 
	 }else if(Constants.day.equalsIgnoreCase("monday"))
	 {
		 date.set(
	 			 Calendar.DAY_OF_WEEK,
	 	         Calendar.MONDAY);
		 
	 }else if(Constants.day.equalsIgnoreCase("tuesday")){
		 date.set(
	 			 Calendar.DAY_OF_WEEK,
	 	         Calendar.TUESDAY);
		 
	 }else if(Constants.day.equalsIgnoreCase("wednesday")){
		 date.set(
	 			 Calendar.DAY_OF_WEEK,
	 	         Calendar.WEDNESDAY);
		 
	 }else if(Constants.day.equalsIgnoreCase("thursday")){
		 date.set(
	 			 Calendar.DAY_OF_WEEK,
	 	         Calendar.THURSDAY);
		 
	 }else if(Constants.day.equalsIgnoreCase("friday")){
		 date.set(
	 			 Calendar.DAY_OF_WEEK,
	 	         Calendar.FRIDAY);
		 
	 }else if(Constants.day.equalsIgnoreCase("saturday")){
		 date.set(
	 			 Calendar.DAY_OF_WEEK,
	 	         Calendar.SATURDAY);
		 
	 }
		 
	 
	    date.set(Calendar.HOUR_OF_DAY,Constants.TimeHour);
	    date.set(Calendar.MINUTE,Constants.TimeMinute);
	    date.set(Calendar.SECOND, 0);
	    date.set(Calendar.MILLISECOND, 0);
	 
	 //timer.scheduleAtFixedRate(new Testing(), getTimePrecision(Constants.delay), getTimePrecision(Constants.timetoquery));
	    
	    //timer.schedule(new Testing(),date.getTime());
		//Testing testObj = new Testing();
	//	 GMailServer sender = new GMailServer(Constants.setFrom, Constants.setPassword);   
		timer.schedule(new Testing(),date.getTime());
		
	//	 testObj.UserMail(sender);
	    
	 }
	 
	 /*public long getTimePrecision(String value) throws Exception
	 {
		
	 long  l = 0;
	 String val="";
	 try
	 {
	 if(value.endsWith("d") || value.endsWith("D"))
	 {
	 val  = value.substring(0,value.length()-1);
	 l = Long.parseLong(val) * 24 * 60 * 60 * 1000;
	 }
	 
	 else if(value.endsWith("h") || value.endsWith("H"))
	 {
	 
	 val  = value.substring(0,value.length()-1);
	 l = Long.parseLong(val) * 60 * 60 * 1000;
	 
	 }
	 else if(value.endsWith("m") || value.endsWith("M"))
	 {
	 val  = value.substring(0,value.length()-1);
	 l = Long.parseLong(val) * 60 * 1000;
	 }
	 else if(value.endsWith("s") || value.endsWith("S"))
	 {
	 
	 val  = value.substring(0,value.length()-1);
	 l = Long.parseLong(val) * 1000;
	 }
	 else
	 {
	 
	 l = Long.parseLong(value);
	 }
	 
	 }
	 catch(Exception e)
	 {
	 
	             throw new Exception(e);
	 }
	 
	 return l;
	 }*/
	/* public static void main(String a[]) throws Exception
	 {
	 DBScheduler dbs = new DBScheduler();
	 dbs.callScheduler();
	 }
*/
	/*public void run() {
		// TODO Auto-generated method stub
		System.out.println("inside testing");
		Testing testObj = new Testing();
		 GMailServer sender = new GMailServer(Constants.setFrom, Constants.setPassword);   
		 testObj.ManagerMail(sender);
		 testObj.UserMail(sender);
	}
*/
}
