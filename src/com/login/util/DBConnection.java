//DBConnection.java
package com.login.util;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
public static Connection createConnection()
{
Connection con = null;
String url = "jdbc:mysql://192.168.30.134:3306/customers";
String username = "priyanka";
String password = "Datamato@123";
//String password = "root";
try
{
try
{
Class.forName("com.mysql.jdbc.Driver");
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
}