package com.login.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;



public class InitServlet extends HttpServlet
{
	

	public void init(ServletConfig sc)
	{
		ServletContext ctx = sc.getServletContext();
		InputStream fis = ctx.getResourceAsStream(sc.getInitParameter("config"));
		Properties props = new Properties();
		
		try
		{
			props.load(fis);
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		DBConnection dobject = new DBConnection();
		dobject.setProperties(props);
		try
		{
			
		}
		catch (NullPointerException npe)
		{
			System.out.println("could not load ============================");
		}
	}
}
