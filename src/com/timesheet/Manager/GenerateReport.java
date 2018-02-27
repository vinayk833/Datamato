package com.timesheet.Manager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 import java.text.ParseException;
import java.text.SimpleDateFormat;


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
* Servlet implementation class GenerateReport
*/
@WebServlet("/GenerateReport")
public class GenerateReport extends HttpServlet {
      private static final long serialVersionUID = 1L;
     // private static int sessionId;
      /**
      * @see HttpServlet#HttpServlet()
      */
      public GenerateReport() {
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
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub
    	    String sessionId  = (String) request.getSession().getAttribute("Manager");
    	    String startDate = request.getParameter("startdate");
            String endDate = request.getParameter("enddate");
            
            SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
    		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
    		String reformattedStr = null;
    		String reformattedStr1 = null;
    		try {

    		    reformattedStr = myFormat.format(fromUser.parse(startDate));
    		    reformattedStr1 = myFormat.format(fromUser.parse(endDate));
    		} catch (ParseException e) {
    		    e.printStackTrace();
    		}
            
            System.out.println(sessionId);
            String dropDownResult = request.getParameter("dropdown");
            System.out.println(dropDownResult);
            
            switch (dropDownResult) {
            case "My Report":
                  System.out.println("you selected my report");
                  try {
                        getMyReport(response,sessionId,reformattedStr,reformattedStr1);
                  } catch (ClassNotFoundException | SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                  }
                  break;
            case "Team's Report":
                  System.out.println("you selected Team report");
                  try {
                        getTeamReport(response,sessionId,reformattedStr,reformattedStr1);
                  } catch (ClassNotFoundException | SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                  }
 
                  break;
            default:
                  break;
            }
 
      }
 
      private void getTeamReport(HttpServletResponse response,String sessionId,String reformattedStr,String reformattedStr1) throws ClassNotFoundException, SQLException, IOException {
            // TODO Auto-generated method stub
            String Ref="MyTeamReport";
            String home = System.getProperty("user.home");
    		File excelpath = new File(home+"/Downloads/" +Ref+".xls"); 
            HSSFWorkbook wb=new HSSFWorkbook();
            HSSFSheet sheet =  wb.createSheet("new sheet");
            HSSFRow rowhead=   sheet.createRow((short)0);
            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short)10);
            font.setBold(true);
            style.setFont(font);
 
            rowhead.createCell((short) 0).setCellValue("EmployeeName");
            rowhead.createCell((short) 1).setCellValue("date");
            rowhead.createCell((short) 2).setCellValue("TaskCat");
            rowhead.createCell((short) 3).setCellValue("description");
            rowhead.createCell((short) 4).setCellValue("hours");
     
            for(int j = 0; j<=4; j++)
                  rowhead.getCell(j).setCellStyle(style);
 
            Connection con = null;
			con = DBConnection.createConnection();
            Statement st=con.createStatement();
 
            //Create your SQL query here
            String query = "SELECT distinct users.EmployeeName,task.date,task.TaskCat,task.description,task.hours,task.approval FROM customers.users  INNER JOIN customers.task ON users.EmployeeID=task.EmployeeID where task.approval=('yes' or 'no') and date BETWEEN '"+reformattedStr+"' AND '"+reformattedStr1+"' AND  Approver=(select EmployeeName from customers.users where EmployeeID='"+sessionId+"')";
; 
 
            //Execute the query
            ResultSet rs = st.executeQuery(query);
 
            int rowCount = 1;
            while(rs.next()){
                  //System.out.println(rs.getString(1).toString());
                  HSSFRow row =   sheet.createRow((short)rowCount);
                  row.createCell((short) 0).setCellValue(rs.getString("EmployeeName"));
                  row.createCell((short) 1).setCellValue(rs.getString("date"));
                  row.createCell((short) 2).setCellValue(rs.getString("TaskCat"));
                  row.createCell((short) 3).setCellValue(rs.getString("description"));
                  row.createCell((short) 4).setCellValue(rs.getString("hours"));
                 
                 
                  rowCount++;
 
            }
            FileOutputStream fileOut =  new FileOutputStream(excelpath);
            wb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");
 
            response.setContentType("application/csv");
            response.setHeader("Content-Disposition",
                        "attachment;filename="+excelpath.getName());
            InputStream is = new FileInputStream(excelpath);
            int read=0;
            byte[] bytes = new byte[(int) excelpath.length()];
            OutputStream os = response.getOutputStream();
 
            while((read = is.read(bytes))!= -1){
                  os.write(bytes, 0, read);
            }
            os.flush();
            os.close();
            is.close();
            wb.close();
      }
 
 
 
      private void getMyReport(HttpServletResponse response,String sessionId,String reformattedStr,String reformattedStr1) throws ClassNotFoundException, SQLException, IOException {
    	  	System.out.println(reformattedStr);
    	  	System.out.println(reformattedStr1);
    	  	 String Ref="Timesheet";
             File excelpath=new File("F:\\IBM\\"+Ref+".xls");
 
            HSSFWorkbook wb=new HSSFWorkbook();
            HSSFSheet sheet =  wb.createSheet("new sheet");
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
 
            Connection con = null;
			con = DBConnection.createConnection();
            Statement st=con.createStatement();
 
            //Create your SQL query here
           // String query = "SELECT * from task WHERE EmployeeID =" + sessionId;
            String query = "Select * from task where  date BETWEEN  " +"'" + reformattedStr +"'" + " AND " + "'"+ reformattedStr1 + "'"+ " AND  EmployeeID='" + sessionId + "'";
            System.out.println(query);
            //Execute the query
            ResultSet rs = st.executeQuery(query);
 
            int rowCount = 1;
            while(rs.next()){
                  //System.out.println(rs.getString(1).toString());
                  HSSFRow row =   sheet.createRow((short)rowCount);
                  row.createCell((short) 0).setCellValue(rs.getInt("taskId"));
                  row.createCell((short) 1).setCellValue(rs.getString("EmployeeID"));
                  row.createCell((short) 2).setCellValue(rs.getString("date"));
                  row.createCell((short) 3).setCellValue(rs.getString("ProjName"));
                  row.createCell((short) 4).setCellValue(rs.getString("proid"));
                  row.createCell((short) 5).setCellValue(rs.getString("TaskCat"));
                  row.createCell((short) 6).setCellValue(rs.getString("description"));
                  row.createCell((short) 7).setCellValue(rs.getString("hours"));
                 
                 
                  rowCount++;
 
            }
            FileOutputStream fileOut =  new FileOutputStream(excelpath);
            wb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");
 
            response.setContentType("application/csv");
            response.setHeader("Content-Disposition",
                        "attachment;filename="+excelpath.getName());
            InputStream is = new FileInputStream(excelpath);
            int read=0;
            byte[] bytes = new byte[(int) excelpath.length()];
            OutputStream os = response.getOutputStream();
 
            while((read = is.read(bytes))!= -1){
                  os.write(bytes, 0, read);
            }
            os.flush();
            os.close();
            is.close();
            wb.close();
      }
 
}
 