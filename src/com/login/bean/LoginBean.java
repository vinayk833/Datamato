//LoginBean.java
package com.login.bean;
public class LoginBean {
private String EmployeeID;
//private String EmployeeName;
private String password;
public String getUserName() {
return EmployeeID;
}
public void setUserName(String EmployeeID) {
this.EmployeeID = EmployeeID;
}
/*public String getEmployeeName() {
return EmployeeID;
}
public void setEmployeeName(String EmployeeName) {
this.EmployeeName = EmployeeName;
}*/
public String getPassword() {
return password;
}
public void setPassword(String password) {
this.password = password;
}
}