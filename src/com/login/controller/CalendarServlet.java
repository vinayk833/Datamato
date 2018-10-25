package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.*;
import com.login.util.DBConnection;

/**
 * Servlet implementation class testserve
 **/
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private PrintWriter pw;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//public static void main(String arg[]) {	
	System.out.println("inside test serve");
	// Db connection
	 Connection con = null;
	 con = DBConnection.createConnection();

        try
        {
        	
        	 Statement stt = con.createStatement();
           
            /**
             * Query
             **/
            ResultSet res = stt.executeQuery("SELECT * FROM holidays");
            
            JSONArray array = new JSONArray();
            
            while (res.next())
            {
            	JSONObject jObj = new JSONObject();
            	 
                 String date_json = res.getString("date");
                 String description_json = res.getString("Description");
                 
                 
                 jObj.put("title", description_json);
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
        finally{
        	
            try {
				con.close();
				System.out.println(" Db Closed");
				System.out.println("In Finally Block");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
        	
        }
	}

}