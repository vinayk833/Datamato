package com.timesheet.Director;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.login.util.DBConnection;

/**
 * Servlet implementation class TaskDirectorServlet
 */
@WebServlet("/TaskDirectorServlet")
public class TaskDirectorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDirectorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside task servlet");
		try
	        {
	        	 Connection con = null;
	        	 con = DBConnection.createConnection();
	        	 Statement stt = con.createStatement();
	           
	            /**
	             * Query
	             **/
	        	 String employeeID  = (String) request.getSession().getAttribute("Director");
	        	 PreparedStatement statement = con.prepareStatement("select * from task where EmployeeID = ?");    
	        	 statement.setString(1, employeeID);    
	        	 ResultSet res = statement.executeQuery();
	          //  ResultSet res = stt.executeQuery("SELECT * FROM task where EmployeeID=+employeeID");
	            
	            JSONArray array = new JSONArray();
	            
	            while (res.next())
	            {
	            	JSONObject jObj = new JSONObject();
	            	 
	                 String date_json = res.getString("date");
	                 String sum_json = "Hours:" + res.getString("hours");
	                 
	                 
	                 jObj.put("title", sum_json);
	                 jObj.put("date", date_json);
	                 
	                 array.put(jObj);
	                 //System.out.println("print object");              
	            }
	            System.out.println(array);
	            //System.out.println(jObj);
	            
	            
	            JSONObject Ob = new JSONObject();
	            try {
	                Ob.put("result", array);
	            } catch (Exception e) {
	             
	            e.printStackTrace();
	            }
	            System.out.println(Ob);
	            response.setContentType("application/json");
	         // Get the printwriter object from response to write the required json object to the output stream      
	         PrintWriter out = response.getWriter();
	         // Assuming your json object is *jsonObject*, perform the following, it will return your json object  
	         out.print(array);
	         out.flush();
	            //PrintWriter pw = response.getWriter();
	            //pw.write(Ob.toString());
	            
	            
	         // Write JSON string.
	    		//response.setContentType("application/json");
	    		//response.setCharacterEncoding("UTF-8");
	    		//response.getWriter().pw(Ob);
	            
	            res.close();
	            stt.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	}

	

}
