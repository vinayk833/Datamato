<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="java.sql.*"%>
    <%@page language="java"%>    
     <%@ page import="java.util.*" %>
    <%@ page import="com.login.util.DBConnection" %>
<%ResultSet resultset =null;%>
<%ResultSet resultset1 =null;%>
<%ResultSet resultset2 =null;%>
<%ResultSet resultset3 =null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AddUsers</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/JSFormValidation.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/JSFormValidation.js"></script>
<style type="text/css">
	
	input[type=reset] {
    background-color: #007BC0;
    color: white;
  
}
input[type=submit]{
	background-color: #007BC0;
    color: white;
	}
body {
  font-family: Calibri; 
} 
h1{
	font-family: Calibri; 
	color: #106E9B;
	}
	</style>
	<script>
	

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode< 48 || charCode>57)) {
        alert("Please Enter number only");
        return false;
    }
    return true;
}

function isAlfa(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 32 && (charCode< 65 || charCode >90) && (charCode< 97 || charCode >122)) {
        alert("Please Enter Letters Only");
        return false;
         }
    return true;
}
function validateEmail(emailField){
    var reg = /^([A-Za-z0-9_\-\.])+\@([mindacorporation])+\.(com)$/;

    if (reg.test(emailField.value) == false)
    {
        alert('Please Enter valid Email id');
        document.getElementById("id").value = "";
        return false;
    }

    return true;
}

function validate() {
    
    if (document.frm.EmployeeID.value == "")  {
        alert("Please Enter Employee ID.");
        document.frm.Description.focus();
        return false;
    }
  
     if (document.frm.EmployeeName.value == "") {
            alert("Please Enter Employee Name.");
            document.frm.Description.focus();
            return false;
        }
      
    
    
     if (document.frm.EMAIL.value == "") {
         alert("Please Enter Email ID.");
         document.frm.Description.focus();
         return false;
     }
    
    
    
    
     if (document.frm.PASSWORD.value == "") {
         alert("Please Enter Password");
         document.frm.Description.focus();
         return false;
     }
    
    
     if (document.frm.ROLE.value == "") {
         alert("Please Select Role");
         document.frm.Description.focus();
         return false;
     }
    
    
     if (document.frm.Department.value == null) {
         alert("Please Select Department");
         document.frm.Description.focus();
         return false;
     }
    
     if (document.frm.Approver.value == null) {
         alert("Please Select Approver");
         document.frm.Description.focus();
         return false;
     }
    
    
    
     return true;
    
    
    }
		</script>
	
</head>
<body>
<% 
Connection con = null;
con = DBConnection.createConnection();
   try{
   response.setContentType("text/html");

   %>
<div class="container">
<header><img src="${pageContext.request.contextPath}/images/logo.png" alt="Avatar" class="avatar">
<tm style="font-family:calibri">TimeSheet Management System</tm>
  <user><%
		if (session != null) {
			if (session.getAttribute("Admin") != null) {
				String name = (String) session.getAttribute("Admin");
				session.setAttribute("Admin",name);
				//Connection con1 = null;
				con = DBConnection.createConnection();
				System.out.println("connected!.....");
				PreparedStatement pst=con.prepareStatement("SELECT employeename FROM users where employeeid=?");
				pst.setString(1, name);
				ResultSet rs=pst.executeQuery();
				rs.next();
				String ename=rs.getString(1);
				out.print("Welcome " + ename );
			//	con.close();
			//	System.out.println("Connection closed");
				///out.print("Welcome " + name + "   Admin" );
			} else {
				response.sendRedirect("/TimeSheet/");  			}
		}
	%></user>
  </header>
  <div class="HorizontalNav">
<ul>
<li><a class="active" href="${pageContext.request.contextPath}/AdminDashboard">Home</a></li>
  <li class="dropdown">
    <a href="${pageContext.request.contextPath}/Admin/AddTask.jsp" class="dropbtn">Task</a>
    <div class="dropdown-content">
      <a href="${pageContext.request.contextPath}/Admin/AddTask.jsp">Create Task</a>
      <a href="${pageContext.request.contextPath}/Admin/ViewTask.jsp">Display Task </a>
     <a href="${pageContext.request.contextPath}/Admin/TaskCategory.jsp">Add Task Category</a>
       <a href="${pageContext.request.contextPath}/Admin/DeleteTaskCategory.jsp">Delete Task Category</a>
      </div>
  </li>
   <li class="dropdown">
    <a href="${pageContext.request.contextPath}/Admin/AddProjects.jsp" class="dropbtn">Project</a>
    <div class="dropdown-content">
      <a href="${pageContext.request.contextPath}/Admin/AddProjects.jsp">Add Project</a>
      <a href="${pageContext.request.contextPath}/Admin/UpdateProjects.jsp">Update Project</a>
       <a href="${pageContext.request.contextPath}/Admin/ProjectType.jsp">Add Project Type</a>
        <a href="${pageContext.request.contextPath}/Admin/DeleteProjectType.jsp">Delete Project Type</a>
       
     </div>
  </li>
   <li class="dropdown">
    <a href="${pageContext.request.contextPath}/Admin/AddUsers.jsp" class="dropbtn">User</a>
    <div class="dropdown-content">
      <a href="${pageContext.request.contextPath}/Admin/AddUsers.jsp">Add User</a>
     <a href="<%=request.getContextPath()%>/Admin/UpdateUsers.jsp">Display User</a>
      
     </div>
  </li>
   <li class="dropdown">
    <a href="${pageContext.request.contextPath}/Admin/AddDepartment.jsp" class="dropbtn">Department</a>
    <div class="dropdown-content">
       <a href="${pageContext.request.contextPath}/Admin/AddDepartment.jsp">Add Department</a>
      <a href="${pageContext.request.contextPath}/Admin/DeleteDepartment.jsp">Delete Department</a>
     </div></li>
     <li class="dropdown">
    <a href="${pageContext.request.contextPath}/Admin/AddHolidays.jsp" class="dropbtn">Holiday</a>
    <div class="dropdown-content">
      <a href="${pageContext.request.contextPath}/Admin/AddHolidays.jsp">Add Holiday</a>
      <a href="${pageContext.request.contextPath}/Admin/UpdateHoliday.jsp">Display Holiday</a>
     </div></li>
     <li><a href="${pageContext.request.contextPath}/Admin/AdminReport.jsp">Report</a></li>
     
       <li style="float:right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></li>
</ul>
</div>
<div style="align:center;height:100%;">
<center>
<article>
<form method="post" name="frm" action="<%=request.getContextPath()%>/AddUsers" onsubmit="return validate(this);"><table border="1" cellspacing="5" cellpadding="5" width="30%"  align="center" style="height:80%;" >
<h1>Add Users</h1>
<tr bordercolor=" #C0C0C0"><td align="center"><b><label for="txtNo">Employee ID:<span class="required">*</span></label></b></td><td><input type="text" name="EmployeeID" placeholder="Enter Employee ID"  onkeypress="return isNumber(event)"  style="width:200px"></td></tr>
<tr bordercolor=" #C0C0C0"><td align="center"><b><label for="txtName">Employee Name:<span class="required">*</span></label></b></td><td><input type="text" name="EmployeeName" placeholder="Enter Employee Name" onkeypress="return isAlfa(event)" style="width:200px"></td></tr>
<tr bordercolor=" #C0C0C0"><td align="center"><b><label for="txtEmail">Email ID:<span class="required">*</span></label></b></td><td><input type="text" id="id" name="EMAIL" placeholder="@mindacorporation.com"  onblur="validateEmail(this)" style="width:200px"></td></tr>
<tr bordercolor=" #C0C0C0"><td align="center"><b><label for="txtName">Password:<span class="required">*</span></label></b></td><td><input type="password" name="PASSWORD" style="width:200px"></td></tr>
<tr bordercolor=" #C0C0C0"><td align="center"><b>Role:<span class="required">*</span></b></td><td><select name="ROLE" style="width:205px">
 <%
                   Statement statement = con.createStatement() ;

                	resultset =statement.executeQuery("select distinct role from addrole") ;
      while(resultset.next()){ %>
            <option ><%= resultset.getString(1)%></option>
          
        <% } %></select></td></tr>
<tr bordercolor=" #C0C0C0"><td align="center"><b>Department:<span class="required">*</span></b></td><td><select name="Department" style="width:205px">
    <%
                    Statement statement1 = con.createStatement() ;
                    resultset1 =statement1.executeQuery("select distinct Department from department order by Department") ;
   while(resultset1.next()){ %>
            <option ><%= resultset1.getString(1)%></option>
           
        <% } %></select></td></tr>
        <tr bordercolor=" #C0C0C0"><td align="center" ><b>Approver:<span class="required">*</span></b></td><td><select name="Approver" style="width:202px">
  <% 
  
                    Statement statement2 = con.createStatement() ;
                    resultset2 =statement2.executeQuery("select distinct EmployeeName from users order by EmployeeName") ;
   while(resultset2.next()){ %>
            <option><%= resultset2.getString(1)%></option>
           
        <% } %></select></td></tr></table>
       
        <table><br>
    <tr bordercolor=" #C0C0C0"><td align="center"><input type="submit" value="Add" id="submit"  style="width:80px;height:32px;background-color:#007BC0;color:white"></td></tr>
 </table>
 <%
//**Should I input the codes here?**
       }
       catch(Exception e)
       {
            out.println("wrong entry"+e);
       }
   finally{
	   con.close();
	   System.out.println("Disconnected from Db in UI");
   }
%>
</article></center>
 </div>
</div>
</body>
</html>