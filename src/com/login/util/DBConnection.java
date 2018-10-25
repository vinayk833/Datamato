//DBConnection.java
package com.login.util;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
public class DBConnection {
	static Properties prop = new Properties();
	static InputStream input = null;
	
	public static Connection createConnection()
	{
		
		Connection con = null;
		try
		{
//			input = new FileInputStream("system.properties");
//			prop.load(input);
		//test comment
		
//		String url = "jdbc:mysql://localhost:3306/customers";//localhost
//		String username = "root";//root//priyanka//root
//		String password = "admin";//Dell@365//Datamato@123//root
//		String ConnectionDriver ="com.mysql.jdbc.Driver";
//		//String password = "root";
			
		String url = prop.getProperty("url");//localhost
		String username = prop.getProperty("duser");//root//priyanka//root
		String password = prop.getProperty("dpass");//Dell@365//Datamato@123//root
		String ConnectionDriver =prop.getProperty("driver");
		
			try
			{
				Class.forName(ConnectionDriver);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}

			con = DriverManager.getConnection(url, username, password);
			System.out.println("Post establishing a DB connection - "+con);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

	public void setProperties(Properties props) {
		// TODO Auto-generated method stub
		
		prop =props;
	}
}