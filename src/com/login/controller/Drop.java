package com.login.controller;
 
import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.login.util.DBConnection;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;
 
@WebServlet("/Drop")
public class Drop extends HttpServlet {
      private static final long serialVersionUID = 1L;
 
 
      /**
      * @see HttpServlet#HttpServlet()
      */
      public Drop() {
            super();
            // TODO Auto-generated constructor stub
      }
 
      /**
      * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
      */
      protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
      }
 
      /**
      * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
      */
     
      void  employeeReport(HttpServletRequest request, HttpServletResponse res)
      {
           
      }
      protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
 
    	  String Ref="MyTeamReport";
          String home = System.getProperty("user.home");
  		  File excelpath = new File(home+"/Downloads/" +Ref+".xls"); 

  		  String startdate = request.getParameter("startdate");
  		  String enddate = request.getParameter("enddate");
  		  System.out.println(startdate);
  		  System.out.println(enddate);
  	
 		

            String duplicate = null;

            try {
                  HSSFWorkbook wb=new HSSFWorkbook();
                  HSSFSheet sheet =  wb.createSheet("new sheet");
                 
                 
                  Connection con = null;
      			  con = DBConnection.createConnection();
      			  Statement st=con.createStatement();
                  Statement st1=con.createStatement();
                  Statement st2=con.createStatement();
                  //Statement st3=con.createStatement();
                 
                  //String query1 = "Select * from times2";
                 
                  //String query1="SELECT sum(hours) total, pn FROM demo group by pn";
                 
                  String query1="SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID ,task.date,sum(task.hours)hours,task.TaskCat FROM customers.task  INNER JOIN customers.users ON users.EmployeeID= task.EmployeeID   ";  
                 
                  String query2="SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID ,task.date,task.TaskCat,sum(task.hours)hours FROM customers.task  INNER JOIN customers.users ON users.EmployeeID=task.EmployeeID ";
                 
                  String query3 ="SELECT distinct myproject.ProjName,myproject.ID,myproject.CustomerName,myproject.StartDate,myproject.EndDate,sum(task.hours)hours FROM customers.myproject  INNER JOIN customers.task ON myproject.ProjName=task.ProjName ";
                  
                  //String option = request.getParameter("opts");
                  String name = request.getParameter("optsname");
                  String sessionId  = (String) request.getSession().getAttribute("Admin");
                  //System.out.println("value "+option);
                  System.out.println("name="+ name);
                  
                  String[] dropdownValues = request.getParameterValues("projectReport");
                  String[] dropdownValues1 = request.getParameterValues("empreport");
                  String[] dropdownValues2 = request.getParameterValues("customerreport");
                
                	  if(name.equalsIgnoreCase("1"))
                      {
                    	  
                    	  
                    	  System.out.println("projname");
                      for(int i =0;i<dropdownValues.length;i++){
                            if (i==0){
                                  //query1 = query1 + " WHERE pn='" + "\"" + dropdownValues[i] +"\"";
                                  System.out.println("test");
                                  query1 = query1 + " WHERE ProjName='" + dropdownValues[i] +"'";
                            }else{
                                  //query1= query1 + " OR pn=" + "\"" + dropdownValues[i] +"\"";
                                  query1= query1 + " OR ProjName='" + dropdownValues[i] +"'";
                            }
                           
                            if(i==dropdownValues.length-1){
                                  query1 = query1 + " AND task.date BETWEEN '"+ startdate +"' AND '" + enddate + "' group by TaskCat,date";
                            }
                           
                      }
                      System.out.println(query1);
                      }
                     
                      else if (name.equalsIgnoreCase("3"))
                      {
                    	  
                    	  System.out.println("employee");
                            System.out.println(dropdownValues1.length);
                            for(int i =0;i<dropdownValues1.length;i++){
                                  if (i==0){
                                        query2 = query2 + " WHERE EmployeeName='" + dropdownValues1[i] + "'";
                                  }else{
                                        query2 = query2 + " OR EmployeeName='" + dropdownValues1[i] +"'";
                                  }
                                  if(i==dropdownValues1.length-1){
                                        query2 = query2 + " AND task.date BETWEEN '"+ startdate +"' AND '" + enddate + "' group by TaskCat,date";
                                  }
                            }
                            System.out.println(query2);
                           
                      }
                     
                      else if (name.equalsIgnoreCase("2"))
                      {
                    	 
                    	  
                    	  System.out.println("cust");
                            for(int i =0;i<dropdownValues2.length;i++){
                                  if (i==0){
                                        query3 = query3 + " WHERE CustomerName='" + dropdownValues2[i] + "'";
                                  }else{
                                        query3 = query3 + " OR CustomerName='" + dropdownValues2[i] +"'";
                                  }
                                  if(i==dropdownValues2.length-1){
                                        query3 = query3 + " AND task.date BETWEEN '"+ startdate +"' AND '" + enddate + "' group by projName";
                                  }
                            }
                            System.out.println(query3);
                           
                      }
         
                      ResultSet rs1=st1.executeQuery(query1);
                      ResultSet rs2=st.executeQuery(query2);
                      ResultSet rs3=st2.executeQuery(query3);
                     
                     
                     
                      int rowCount = 1;
                     
                      if(name.equalsIgnoreCase("1"))
                      {
                      while(rs1.next()){
                           
                            duplicate = rs1.getString(1);
                           
                            System.out.println(duplicate);
                           
                                  //int c=(rs1.getInt("sum"));
                                  // sum=sum+c;
                                 
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
                            HSSFRow row =   sheet.createRow((short)rowCount);
                           
                            row.createCell((short) 0).setCellValue(rs1.getString("ProjName"));
                            row.createCell((short) 1).setCellValue(rs1.getString("proid"));
                            row.createCell((short) 2).setCellValue(rs1.getString("EmployeeName"));
                            row.createCell((short) 3).setCellValue(rs1.getString("EmployeeID"));
                            row.createCell((short) 4).setCellValue(rs1.getString("Date"));
                            row.createCell((short) 5).setCellValue(rs1.getString("TaskCat"));
                            row.createCell((short) 6).setCellValue(rs1.getString("hours"));
                           
                            rowCount++;
                            FileOutputStream fileOut =  new FileOutputStream(excelpath);
                            wb.write(fileOut);
                            fileOut.close();
                           
                      }
                     
                }    
                      if(name.equalsIgnoreCase("3"))
                      {
                           
                      while(rs2.next()){
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
                            row.createCell((short) 0).setCellValue(rs2.getString("EmployeeName"));
                            row.createCell((short) 1).setCellValue(rs2.getString("EmployeeID"));
                            row.createCell((short) 2).setCellValue(rs2.getString("ProjName"));
                            row.createCell((short) 3).setCellValue(rs2.getString("date"));
                            row.createCell((short) 4).setCellValue(rs2.getString("TaskCat"));
                            row.createCell((short) 5).setCellValue(rs2.getString("hours"));
                     
                            rowCount++;
                            FileOutputStream fileOut =  new FileOutputStream(excelpath);
                            wb.write(fileOut);
                            fileOut.close();
                           
                      }
                     
                }
                     
                      if(name.equalsIgnoreCase("2"))
                      {
                           
                      while(rs3.next()){
                           
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
                            row.createCell((short) 0).setCellValue(rs3.getString("CustomerName"));
                            row.createCell((short) 1).setCellValue(rs3.getString("ProjName"));
                            row.createCell((short) 2).setCellValue(rs3.getString("ID"));
                            row.createCell((short) 3).setCellValue(rs3.getString("StartDate"));
                            row.createCell((short) 4).setCellValue(rs3.getString("EndDate"));
                            row.createCell((short) 5).setCellValue(rs3.getString("hours"));
                           
                            rowCount++;
                            FileOutputStream fileOut =  new FileOutputStream(excelpath);
                            wb.write(fileOut);
                            fileOut.close();
                           
                      }
                     
                   }
                    if(name.equalsIgnoreCase("4")) {
                    	String query4 = "Select * from task where EmployeeID='" + sessionId + "' AND task.date BETWEEN '"+ startdate +"' AND '" + enddate + "'" ;
                    	System.out.println(sessionId);
                    	ResultSet rs4=st2.executeQuery(query4);
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
                   
                        while(rs4.next()){
                              //System.out.println(rs.getString(1).toString());
                              HSSFRow row =   sheet.createRow((short)rowCount);
                              row.createCell((short) 0).setCellValue(rs4.getInt("taskId"));
                              row.createCell((short) 1).setCellValue(rs4.getString("EmployeeID"));
                              row.createCell((short) 2).setCellValue(rs4.getString("date"));
                              row.createCell((short) 3).setCellValue(rs4.getString("ProjName"));
                              row.createCell((short) 4).setCellValue(rs4.getString("proid"));
                              row.createCell((short) 5).setCellValue(rs4.getString("TaskCat"));
                              row.createCell((short) 6).setCellValue(rs4.getString("description"));
                              row.createCell((short) 7).setCellValue(rs4.getString("hours"));            
                              rowCount++;
             
                        }
                        
                        FileOutputStream fileOut =  new FileOutputStream(excelpath);
                        wb.write(fileOut);
                        fileOut.close();

                    }
                    
                     
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
            }
            catch (Exception e2) {
                  System.out.println(e2);
            }
 
      }
 
}
 