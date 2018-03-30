//LoginDao.java
package com.login.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.login.bean.LoginBean;
import com.login.util.DBConnection;
public class LoginDao {
public String authenticateUser(LoginBean loginBean)
{
String EmployeeID = loginBean.getUserName();
//String EmployeeName = loginBean.getEmployeeName();
String password = loginBean.getPassword();
Connection con = null;
Statement statement = null;
ResultSet resultSet = null;
String userNameDB = "";
String EmployeeNameDB = "";
String passwordDB = "";
String roleDB = "";
try
{
con = DBConnection.createConnection();
statement = con.createStatement();
resultSet = statement.executeQuery("select EmployeeID,password,role from users");
while(resultSet.next())
{
userNameDB = resultSet.getString("EmployeeID");
//EmployeeNameDB = resultSet.getString("EmployeeName");
passwordDB = resultSet.getString("password");
roleDB = resultSet.getString("role");
if(EmployeeID.equals(userNameDB)  && password.equals(passwordDB) && roleDB.equals("Admin") )
return "Admin_Role";
else if(EmployeeID.equals(userNameDB) && password.equals(passwordDB) && (roleDB.equals("Manager") || roleDB.equals("Product Manager")) )
return "Manager_Role";
else if(EmployeeID.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("User"))
return "User_Role";
else if(EmployeeID.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Director"))
return "Director_Role";
}
resultSet.close();
statement.close();
con.close();

}

catch(SQLException e)
{
e.printStackTrace();
}
return "Invalid user credentials";
}

}