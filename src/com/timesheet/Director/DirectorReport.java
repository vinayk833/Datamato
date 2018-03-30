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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	  protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		  
		  String errormsg = null; 
    	  String Ref="MyTeamReport";
          String home = System.getProperty("user.home");
  		  File excelpath = new File(home+"/Downloads/" +Ref+".xls"); 
  		  
  		  int exportstatus=0;
  		 int rowCount = 1;
  		 String startDate = request.getParameter("startdate");
         String endDate = request.getParameter("enddate");
         
  		  System.out.println("MySQL Connect Example.");
  		
  		SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String reformattedStr = null;
		String reformattedStr1 = null;
		try {
			System.out.println();
		    reformattedStr = myFormat.format(fromUser.parse(startDate));
		    reformattedStr1 = myFormat.format(fromUser.parse(endDate));
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
      			  Statement st=con.createStatement();
                  Statement st1=con.createStatement();
                  Statement st2=con.createStatement();
                  Statement st3=con.createStatement();
                 
                  //String query1 = "Select * from times2";
                 
                  //String query1="SELECT sum(hours) total, pn FROM demo group by pn";
                 
                  //String query1="SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID ,task.date,sum(task.hours)hours,task.TaskCat FROM customers.task  INNER JOIN customers.users ON users.EmployeeID= task.EmployeeID WHERE task.date BETWEEN '"+ reformattedStr +"' AND '" + reformattedStr1 +"' AND ";  
                  String query1 = "SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID,task.date,task.TaskCat,SUM(hours)hours FROM customers.task INNER JOIN customers.users ON users.EmployeeID=task.EmployeeID WHERE task.date BETWEEN '"+reformattedStr+"' AND '"+reformattedStr1+"' AND ProjName IN (";
                  
                  String query2 = "SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID,task.date,task.TaskCat,sum(task.hours)hours FROM customers.task INNER JOIN customers.users ON users.EmployeeID=task.EmployeeID  WHERE task.date BETWEEN '"+ reformattedStr +"' AND '"+ reformattedStr1 +"' AND EmployeeName IN (";
                  //String query2="SELECT distinct task.ProjName,task.proid,users.EmployeeName,users.EmployeeID ,task.date,task.TaskCat,sum(task.hours)hours FROM customers.task  INNER JOIN customers.users ON users.EmployeeID=task.EmployeeID ";
                 
                  //String query3 ="SELECT distinct myproject.ProjName,myproject.ID,myproject.CustomerName,myproject.StartDate,myproject.EndDate,sum(task.hours)hours FROM customers.myproject  INNER JOIN customers.task ON myproject.ProjName=task.ProjName ";
                  String query3 = "SELECT  myproject.ProjName,myproject.ID,myproject.CustomerName,myproject.StartDate,myproject.EndDate,SUM(hours)hours From myproject INNER JOIN task on task.ProjName=myproject.ProjName WHERE myproject.StartDate >='"+reformattedStr+"' AND myproject.EndDate <='"+reformattedStr1+"'AND CustomerName IN (";
                  
                  //String option = request.getParameter("opts");
                  String name = request.getParameter("opts");
                  String sessionId  = (String) request.getSession().getAttribute("Admin");
                  //System.out.println("value "+option);
                  System.out.println("name="+ name);
                  
                  String[] dropdownValues = request.getParameterValues("projectReport");
                  String[] dropdownValues1 = request.getParameterValues("empreport");
                  String[] dropdownValues2 = request.getParameterValues("customerreport");
                  ResultSet rs1 =null;
                  ResultSet rs2 =null;
                  ResultSet rs3 =null;
                  ResultSet rs4 =null;
                  //ResultSet res1 = null;
                  
                  	  //Project report
                	  if(name.equalsIgnoreCase("1"))
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
                      ResultSet res1=st1.executeQuery(query1);
                      if(res1.next()==true) {
                    	  exportstatus=1;
                    	  rs1=st1.executeQuery(query1);
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
                      //rs1=st1.executeQuery(query1);
                      }
                     //Employee report
                      if (name.equalsIgnoreCase("3"))
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
                            ResultSet res2=st.executeQuery(query2);
                            if(res2.next()==true) {
                          	  exportstatus=1;
                          	  Statement stt = con.createStatement();
                          	rs2=stt.executeQuery(query2);
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
                            //rs2=st1.executeQuery(query1);
                      }
                     
                      if (name.equalsIgnoreCase("2"))
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
                            ResultSet res3=st.executeQuery(query3);
                            if(res3.next()==true) {
                          	  exportstatus=1;
                          	  Statement stt = con.createStatement();
                          	rs3=stt.executeQuery(query3);
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
                            //rs3=st1.executeQuery(query1);
                      }
         
                    if(name.equalsIgnoreCase("4")) {
                    	String query4 = "Select * from task where EmployeeID='" + sessionId + "' AND task.date BETWEEN '"+ reformattedStr +"' AND '" + reformattedStr1 + "'" ;
                    	System.out.println(sessionId);
                    	ResultSet res4=st3.executeQuery(query4);
                    	if(res4.next()==true) {
                      	  exportstatus=1;
                      	rs4=st3.executeQuery(query4);
                      	//rs1=st1.executeQuery(query1);
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
                     }else {
                    	 System.out.println("no file to export");
                    	 errormsg="No Data found to export file";
                    	 request.setAttribute("errormsg", errormsg);
                    	 RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Director/DirectorReport.jsp");
                 		requestDispatcher.include(request, res);
                 		System.out.println("status==========>" + exportstatus);
                 			//out.println("<h4 style='color:red;margin-left:600px;margin-top:10px;'>No Data found to export</h4>");
                 	
                     }
                      
                      
                      dropdownValues=null;
                      dropdownValues1=null;
                      dropdownValues2=null;
                      con.close();
            }
            catch (Exception e2) {
                  System.out.println(e2);
            }
 
      }
 
}
 