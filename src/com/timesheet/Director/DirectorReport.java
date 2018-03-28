package com.timesheet.Director;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.login.util.DBConnection;

/**
 * Servlet implementation class DirectorReport
 */
@WebServlet("/DirectorReport")
public class DirectorReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectorReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*@Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	
    }*/
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	  protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		  
		  int exportstatus=0;
		  String errormsg=null;
    	  String Ref="MyTeamReport";
          String home = System.getProperty("user.home");
  		  File excelpath = new File(home+"/Downloads/" +Ref+".xls");
  		  String startdate = request.getParameter("startdate");
 		  String enddate = request.getParameter("enddate");
 		 String sessionId  = (String) request.getSession().getAttribute("Director");
 		  System.out.println(startdate);
 		  System.out.println(enddate);
 		 SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
 		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
 		String reformattedStr = null;
 		String reformattedStr1 = null;
 		try {
 		    reformattedStr = myFormat.format(fromUser.parse(startdate));
 		    reformattedStr1 = myFormat.format(fromUser.parse(enddate));
 		    System.out.println(reformattedStr);
 		    System.out.println(reformattedStr1);
 		} catch (ParseException e) {
 		    e.printStackTrace();
 		}
  		  String duplicate = null;
            try {
                  HSSFWorkbook wb=new HSSFWorkbook();
                  HSSFSheet sheet =  wb.createSheet("new sheet");
                 
                 
                  Connection con = null;
      			  con = DBConnection.createConnection();
      			  Statement stt=con.createStatement();
                  Statement stt1=con.createStatement();
                  Statement stt2=con.createStatement();
                  Statement stt3=con.createStatement();
                 
                  //String query1 = "Select * from times2";
                 
                  //String query1="SELECT sum(hours) total, pn FROM demo group by pn";
                 
                  /*String query1="SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID ,task.date,sum(task.hours)hours,task.TaskCat FROM customers.task  INNER JOIN customers.users ON users.EmployeeID= task.EmployeeID   ";  
                 
                  String query2="SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID ,task.date,task.TaskCat,sum(task.hours)hours FROM customers.task  INNER JOIN customers.users ON users.EmployeeID=task.EmployeeID ";
                 
                  String query3 ="SELECT distinct myproject.ProjName,myproject.ID,myproject.CustomerName,myproject.StartDate,myproject.EndDate,sum(task.hours)hours FROM customers.myproject  INNER JOIN customers.task ON myproject.ProjName=task.ProjName ";*/
                  String query1 = "SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID,task.date,task.TaskCat,SUM(hours)hours FROM customers.task INNER JOIN customers.users ON users.EmployeeID=task.EmployeeID WHERE task.date BETWEEN '"+reformattedStr+"' AND '"+reformattedStr1+"' AND ProjName IN (";
                  
                  String query2 = "SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID,task.date,task.TaskCat,sum(task.hours)hours FROM customers.task INNER JOIN customers.users ON users.EmployeeID=task.EmployeeID  WHERE task.date BETWEEN '"+ reformattedStr +"' AND '"+ reformattedStr1 +"' AND EmployeeName IN (";
                  //String query2="SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID ,task.date,task.TaskCat,sum(task.hours)hours FROM customers.task  INNER JOIN customers.users ON users.EmployeeID=task.EmployeeID ";
                 
                  //String query3 ="SELECT distinct myproject.ProjName,myproject.ID,myproject.CustomerName,myproject.StartDate,myproject.EndDate,sum(task.hours)hours FROM customers.myproject  INNER JOIN customers.task ON myproject.ProjName=task.ProjName ";
                  String query3 = "SELECT  myproject.ProjName,myproject.ID,myproject.CustomerName,myproject.StartDate,myproject.EndDate,SUM(hours)hours From myproject INNER JOIN task on task.ProjName=myproject.ProjName WHERE myproject.StartDate >='"+reformattedStr+"' AND myproject.EndDate <='"+reformattedStr1+"'AND CustomerName IN (";
                  
                  String option = request.getParameter("opts");
                  System.out.println(option);
                  String[] dropdownValues = request.getParameterValues("projectReport");
                  String[] dropdownValues1 = request.getParameterValues("empreport");
                  String[] dropdownValues2 = request.getParameterValues("customerreport");
                  ResultSet res1 =null;
                  ResultSet res2 =null;
                  ResultSet res3 =null;
                  ResultSet res4 =null;
                  
                  if(option.equalsIgnoreCase("1"))
                  {
                	  
                	  
                	  System.out.println("projname");
                      for(int i =0;i<dropdownValues.length;i++){
                            if (i==0){
                                  //query1 = query1 + " WHERE pn='" + "\"" + dropdownValues[i] +"\"";
                                  System.out.println("test");
                                  query1 = query1 + "'"+ dropdownValues[i] +"'";
                            }else{
                                  //query1= query1 + " OR pn=" + "\"" + dropdownValues[i] +"\"";
                                  query1= query1 + ",'" + dropdownValues[i] +"'";
                            }
                           
                            if(i==dropdownValues.length-1){
                                  query1 = query1 + ") group by ProjName,date,TaskCat";
                            }
                       
                  }
                  System.out.println(query1);
                  
                  
                  res1=stt1.executeQuery(query1);
                  
                  
                  if(res1.next()==true) {
                	  System.out.println("data available project");
                	  exportstatus=1;
                	  System.out.println(exportstatus);
                  }
                  }
                 
                  //Employee Report
                  if (option.equalsIgnoreCase("3"))
                  {
                	  
                	  System.out.println("employee");
                        System.out.println(dropdownValues1.length);
                        for(int i =0;i<dropdownValues1.length;i++){
                              if (i==0){
                                    query2 = query2 + "'" + dropdownValues1[i] + "'";
                              }else{
                                    query2 = query2 + ",'" + dropdownValues1[i] +"'";
                              }
                              if(i==dropdownValues1.length-1){
                                    query2 = query2 + ") group by EmployeeName,date";
                              }
                        }
                        System.out.println(query2);
                        res2=stt.executeQuery(query2);
                        if(res2.next()==true) {
                        	System.out.println("data available employee");
                        	exportstatus=1;
                        }   
                  }
                 
                  //Customer Report
                  if (option.equalsIgnoreCase("2"))
                  {

                	  
                	  System.out.println("cust");
                        for(int i =0;i<dropdownValues2.length;i++){
                              if (i==0){
                                    query3 = query3 + "'" + dropdownValues2[i] + "'";
                              }else{
                                    query3 = query3 + ",'" + dropdownValues2[i] +"'";
                              }
                              if(i==dropdownValues2.length-1){
                                    query3 = query3 + ") group by CustomerName,ProjName";
                              }
                        }
                        System.out.println(query3);
                        res3=stt2.executeQuery(query3);
                        if(res3.next()==true) {
                        	System.out.println("data available cust");
                        	exportstatus=1;
                        }
                  }
     
                  /*ResultSet res1=stt1.executeQuery(query1);
                  ResultSet res2=stt.executeQuery(query2);
                  ResultSet res3=stt2.executeQuery(query3);*/
                 
                 
                 
                  int rowCount = 1;
                 
                  if(option.equalsIgnoreCase("1"))
                  {
                      HSSFRow rowhead=   sheet.createRow((short)0);
                      
                      HSSFCellStyle style = wb.createCellStyle();
                      HSSFFont font = wb.createFont();
                      font.setFontName(HSSFFont.FONT_ARIAL);
                      font.setFontHeightInPoints((short)10);
                      font.setBold(true);
                      style.setFont(font);
                     
                      rowhead.createCell((short) 0).setCellValue("Project Name");
                      rowhead.createCell((short) 1).setCellValue("Project ID");
                      rowhead.createCell((short) 2).setCellValue("Employee Name");
                      rowhead.createCell((short) 3).setCellValue("Employee ID");
                      rowhead.createCell((short) 4).setCellValue("Date");
                      rowhead.createCell((short) 5).setCellValue("Task Category");
                      rowhead.createCell((short) 6).setCellValue("Total Hours");
                      for(int j = 0; j<=6; j++)
                          rowhead.getCell(j).setCellStyle(style);
                    
                    
                 
                     
                  while(res1.next()){
                       
                        duplicate = res1.getString(1);
                       
                       
                              //int c=(res1.getInt("sum"));
                              // sum=sum+c;
                             
          
                        HSSFRow row =   sheet.createRow((short)rowCount);
                       
                        row.createCell((short) 0).setCellValue(res1.getString("ProjName"));
                        row.createCell((short) 1).setCellValue(res1.getString("proid"));
                        row.createCell((short) 2).setCellValue(res1.getString("EmployeeName"));
                        row.createCell((short) 3).setCellValue(res1.getString("EmployeeID"));
                        row.createCell((short) 4).setCellValue(res1.getString("Date"));
                        row.createCell((short) 5).setCellValue(res1.getString("TaskCat"));
                        row.createCell((short) 6).setCellValue(res1.getString("hours"));
                       
                        rowCount++;
                        FileOutputStream fileOut =  new FileOutputStream(excelpath);
                        wb.write(fileOut);
                        fileOut.flush();
                        fileOut.close();
                        row = null;
                        System.gc();
                        
                        
                       
                  }
                 
            }    
                  if(option.equalsIgnoreCase("3"))
                  {
                       
                  while(res2.next()){
                        HSSFRow rowhead=   sheet.createRow((short)0);
                        HSSFCellStyle style = wb.createCellStyle();
                        HSSFFont font = wb.createFont();
                        font.setFontName(HSSFFont.FONT_ARIAL);
                        font.setFontHeightInPoints((short)10);
                        font.setBold(true);
                        style.setFont(font);
                       
                        rowhead.createCell((short) 0).setCellValue("Employee Name");
                        rowhead.createCell((short) 1).setCellValue("Employee ID");
                        rowhead.createCell((short) 2).setCellValue("Project Name");
                        rowhead.createCell((short) 3).setCellValue("Date");
                        rowhead.createCell((short) 4).setCellValue("Task Category");
                        rowhead.createCell((short) 5).setCellValue("Total Hours");
                 
                 
                        for(int j = 0; j<=5; j++)
                              rowhead.getCell(j).setCellStyle(style);
                 
                        HSSFRow row =   sheet.createRow((short)rowCount);
                        row.createCell((short) 0).setCellValue(res2.getString("EmployeeName"));
                        row.createCell((short) 1).setCellValue(res2.getString("EmployeeID"));
                        row.createCell((short) 2).setCellValue(res2.getString("ProjName"));
                        row.createCell((short) 3).setCellValue(res2.getString("date"));
                        row.createCell((short) 4).setCellValue(res2.getString("TaskCat"));
                        row.createCell((short) 5).setCellValue(res2.getString("hours"));
                 
                        rowCount++;
                        FileOutputStream fileOut =  new FileOutputStream(excelpath);
                        wb.write(fileOut);
                        fileOut.close();
                       
                  }
                 
            }
                 
                  if(option.equalsIgnoreCase("2"))
                  {
                       
                  while(res3.next()){
                       
                        HSSFRow rowhead=   sheet.createRow((short)0);
                       
                        HSSFCellStyle style = wb.createCellStyle();
                        HSSFFont font = wb.createFont();
                        font.setFontName(HSSFFont.FONT_ARIAL);
                        font.setFontHeightInPoints((short)10);
                        font.setBold(true);
                        style.setFont(font);
                       
                        
                        rowhead.createCell((short) 0).setCellValue("Customer Name");
                        rowhead.createCell((short) 1).setCellValue("Project Name");
                        rowhead.createCell((short) 2).setCellValue("Project ID");
                        rowhead.createCell((short) 3).setCellValue("Start Date");
                        rowhead.createCell((short) 4).setCellValue("End Date");
                        rowhead.createCell((short) 5).setCellValue("Total Hours");
                 
                        for(int j = 0; j<=5; j++)
                              rowhead.getCell(j).setCellStyle(style);
                        
                        HSSFRow row =   sheet.createRow((short)rowCount);
                        row.createCell((short) 0).setCellValue(res3.getString("CustomerName"));
                        row.createCell((short) 1).setCellValue(res3.getString("ProjName"));
                        row.createCell((short) 2).setCellValue(res3.getString("ID"));
                        row.createCell((short) 3).setCellValue(res3.getString("StartDate"));
                        row.createCell((short) 4).setCellValue(res3.getString("EndDate"));
                        row.createCell((short) 5).setCellValue(res3.getString("hours"));
                       
                        rowCount++;
                        FileOutputStream fileOut =  new FileOutputStream(excelpath);
                        wb.write(fileOut);
                        fileOut.close();
                       
                  }
                 
            }
                  if(option.equalsIgnoreCase("4")) {
                  	String query4 = "Select * from task where EmployeeID='" + sessionId + "' AND task.date BETWEEN '"+ startdate +"' AND '" + enddate + "'" ;
                  	System.out.println(sessionId);
                  	res4=stt2.executeQuery(query4);
                  	res4=stt3.executeQuery(query4);
                	if(res4.next()==true) {
                		System.out.println("data available myreport");
                		exportstatus=1;
                    }
                      HSSFRow rowhead=   sheet.createRow((short)0);
                      HSSFCellStyle style = wb.createCellStyle();
                      HSSFFont font = wb.createFont();
                      font.setFontName(HSSFFont.FONT_ARIAL);
                      font.setFontHeightInPoints((short)10);
                      font.setBold(true);
                      style.setFont(font);
           
                      rowhead.createCell((short) 0).setCellValue("taskId");
                      rowhead.createCell((short) 1).setCellValue("EmployeeID");
                      rowhead.createCell((short) 2).setCellValue("date");
                      rowhead.createCell((short) 3).setCellValue("ProjName");
                      rowhead.createCell((short) 4).setCellValue("proid");
                      rowhead.createCell((short) 5).setCellValue("TaskCat");
                      rowhead.createCell((short) 6).setCellValue("description");
                      rowhead.createCell((short) 7).setCellValue("hours");
                      
                      for(int j = 0; j<=7; j++)
                          rowhead.getCell(j).setCellStyle(style);
                 
                      while(res4.next()){
                            //System.out.println(rs.getString(1).toString());
                            HSSFRow row =   sheet.createRow((short)rowCount);
                            row.createCell((short) 0).setCellValue(res4.getInt("taskId"));
                            row.createCell((short) 1).setCellValue(res4.getString("EmployeeID"));
                            row.createCell((short) 2).setCellValue(res4.getString("date"));
                            row.createCell((short) 3).setCellValue(res4.getString("ProjName"));
                            row.createCell((short) 4).setCellValue(res4.getString("proid"));
                            row.createCell((short) 5).setCellValue(res4.getString("TaskCat"));
                            row.createCell((short) 6).setCellValue(res4.getString("description"));
                            row.createCell((short) 7).setCellValue(res4.getString("hours"));            
                            rowCount++;
           
                      }
                      
                      FileOutputStream fileOut =  new FileOutputStream(excelpath);
                      wb.write(fileOut);
                      fileOut.close();

                  }
                 
                  if(exportstatus==1) {
                  System.out.println(excelpath.getAbsolutePath());
                  System.out.println("Your excel file has been generated!");
                 
                  res.setContentType("application/csv");
                  res.setHeader("Content-Disposition",
                                 "attachment;filename="+excelpath.getName());
                  InputStream is = new FileInputStream(excelpath);
                  int read=0;
                  byte[] bytes = new byte[(int) excelpath.length()];
                  OutputStream os = res.getOutputStream();
 
                  while((read = is.read(bytes))!= -1){
                        os.write(bytes, 0, read);
                  }
                  os.flush();
                  os.close();
                  is.close();
                  wb.close();
                  dropdownValues=null;
                  dropdownValues1=null;
                  dropdownValues2=null;
                  }else {
                 	 System.out.println("no file to export");
                 	 errormsg="No Data found to export file";
                 	wb.close();
                    dropdownValues=null;
                    dropdownValues1=null;
                    dropdownValues2=null;
                 	 request.setAttribute("errormsg", errormsg);
                 	 RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Admin/AdminReport.jsp");
              		requestDispatcher.include(request, res);
              		System.out.println("status==========>" + exportstatus);
              			
                  }
            }
            catch (Exception e2) {
                  System.out.println(e2);
            }
 
      }
 
}
 