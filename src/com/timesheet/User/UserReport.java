package com.timesheet.User;

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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.login.util.DBConnection;

/**
 * Servlet implementation class UserReport
 */
@WebServlet("/UserReport")
public class UserReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Ref="Timesheet";
		String home = System.getProperty("user.home");
		File excelpath = new File(home+"/Downloads/" +Ref+".xls"); 
		//File excelpath=new File("F:\\Report\\"+Ref+".xls");
		String employeeID  = (String) request.getSession().getAttribute("User");
		 String startDate = request.getParameter("startdate");
         String endDate = request.getParameter("enddate");
         SimpleDateFormat fromUser = new SimpleDateFormat("mm/dd/yyyy");
 		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd");
 		String reformattedStr = null;
 		String reformattedStr1 = null;
 		
		try {
			reformattedStr = myFormat.format(fromUser.parse(startDate));
	 		reformattedStr1 = myFormat.format(fromUser.parse(endDate));
	 		System.out.println(reformattedStr);
	 		System.out.println(reformattedStr1);
	 		
			HSSFWorkbook hwb=new HSSFWorkbook();
			HSSFSheet sheet =  hwb.createSheet("new sheet");

			HSSFRow rowhead=   sheet.createRow((short)0);
			rowhead.createCell((short) 0).setCellValue("Date");
			rowhead.createCell((short) 1).setCellValue("Project Name");
			rowhead.createCell((short) 2).setCellValue("Hours");
			rowhead.createCell((short) 3).setCellValue("Task Category");
			rowhead.createCell((short) 4).setCellValue("Task Description");
			Connection con = null;
			con = DBConnection.createConnection();
			Statement st=con.createStatement();
			//String query = "Select * from task where EmployeeID="+employeeID+"";
			//String query = "Select * from task where  date BETWEEN " +"'" + reformattedStr +"'" + " AND " + "'"+ reformattedStr1 + "'" + " AND "+"' EmployeeID='"+employeeID;
            String query = "Select * from task where  date BETWEEN  " +"'" + reformattedStr +"'" + " AND " + "'"+ reformattedStr1 + "'"+ " AND  EmployeeID='" + employeeID + "'";

			System.out.println(query);
		
			
			ResultSet rs=st.executeQuery(query);
			int rowCount = 1;
			while(rs.next()){
				HSSFRow row=   sheet.createRow((short)rowCount);
				row.createCell((short) 0).setCellValue(rs.getString("date"));
				row.createCell((short) 1).setCellValue(rs.getString("ProjName"));
				row.createCell((short) 2).setCellValue(rs.getString("hours"));
				row.createCell((short) 3).setCellValue(rs.getString("TaskCat"));
				row.createCell((short) 4).setCellValue(rs.getString("description"));
				rowCount++;
				FileOutputStream fileOut =  new FileOutputStream(excelpath);
				hwb.write(fileOut);
				fileOut.close();
				
			}
			System.out.println(excelpath.getAbsolutePath());
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
			
			
		}
		catch (Exception e2) {
			System.out.println(e2);
		}

		}
	}


