package com.login.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int EmployeeID;
    private String EmployeeName;
    private String EMAIL;
    private String PASSWORD;
    private String ROLE;
    private String Department;
    private String Approver;
 
    public void setEmployeeID(int EmployeeID)
    { 
    
        this.EmployeeID = EmployeeID;
        
    }
    public int getEmployeeID()
    { 
        return (this.EmployeeID);
    }
    
    public void setEmployeeName(String EmployeeName) 
    { 
    
        this.EmployeeName = EmployeeName;
        
    }
    public String getEmployeeName() 
    { 
        return (this.EmployeeName);
    }
    
        
    public void setEMAIL(String EMAIL) 
    { 
    
        this.EMAIL = EMAIL;
        
    }
    public String getEMAIL() 
    { 
        return (this.EMAIL);
    }
    public void setPASSWORD(String PASSWORD) 
    { 
    
        this.PASSWORD = PASSWORD;
        
    }
    public String getPASSWORD() 
    { 
        return (this.PASSWORD);
    }
    public void setROLE(String ROLE) 
    { 
    
        this.ROLE = ROLE;
        
    }
    public String getROLE() 
    { 
        return (this.ROLE);
    }
    public void setDepartment(String Department) 
    { 
    
        this.Department = Department;
        
    }
    public String getDepartment() 
    { 
        return (this.Department);
    }
    public void setApprover(String Approver) 
    { 
    
        this.Approver = Approver;
        
    }
    public String getApprover() 
    { 
        return (this.Approver);
    }
    
    
    public User (ResultSet resultSet) throws SQLException
    {
        this.EmployeeID = resultSet.getInt("EmployeeID");
        this.EmployeeName = resultSet.getString("EmployeeName");
        this.EMAIL = resultSet.getString("EMAIL");
        this.PASSWORD = resultSet.getString("PASSWORD");
        this.ROLE = resultSet.getString("ROLE");
        this.Department = resultSet.getString("Department");
        this.Approver = resultSet.getString("Approver");
        
    }
}
