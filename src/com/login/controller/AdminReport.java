package com.login.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

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
 * Servlet implementation class AdminReport
 */
@WebServlet("/AdminReport")
public class AdminReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String Ref="Timesheet";
		File excelpath=new File("F:\\IBM\\"+Ref+".xls");
		
		try {
			HSSFWorkbook hwb=new HSSFWorkbook();
			HSSFSheet sheet =  hwb.createSheet("new sheet");

			HSSFRow rowhead=   sheet.createRow((short)0);
			rowhead.createCell((short) 0).setCellValue("project name");
			rowhead.createCell((short) 1).setCellValue("Employee ID");
			rowhead.createCell((short) 2).setCellValue("Date");
			rowhead.createCell((short) 3).setCellValue("Task Category");
			rowhead.createCell((short) 4).setCellValue("Total hours");
			Connection con = null;
			con = DBConnection.createConnection();
			Statement st=con.createStatement();
			String query = "Select * from task";
			System.out.println(query);
			String[] dropdownValues = request.getParameterValues("projectReport");
			
			for(int i =0;i<dropdownValues.length;i++){
				if (i==0){
					query = query + " WHERE ProjName=" + "\"" + dropdownValues[i] +"\"";
				}else{
					query = query + " OR ProjName=" + "\"" + dropdownValues[i] +"\"";
				}
			}
			System.out.println(query);
			ResultSet rs=st.executeQuery(query);
			int rowCount = 1;
			while(rs.next()){
				HSSFRow row=   sheet.createRow((short)rowCount);
				row.createCell((short) 0).setCellValue(rs.getString("ProjName"));
				row.createCell((short) 1).setCellValue(rs.getString("EmployeeID"));
				row.createCell((short) 2).setCellValue(rs.getString("date"));
				row.createCell((short) 3).setCellValue(rs.getString("TaskCat"));
				row.createCell((short) 4).setCellValue(rs.getString("sum"));
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


