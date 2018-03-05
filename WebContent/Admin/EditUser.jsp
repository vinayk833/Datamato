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
<title>UpdateUsers</title>
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
        alert('Invalid Email Address');
        return false;
    }

    return true;
}
		</script>
	
</head>
<body>
<% 
   try{
   	Connection con = null;
		response.setContentType("text/html");
	con = DBConnection.createConnection();

   %>
<div class="container">
<header><img src="${pageContext.request.contextPath}/images/logo.png" alt="Avatar" class="avatar">
<tm style="font-family:calibri">Timesheet Management System</tm>
  <user><%
		if (session != null) {
			if (session.getAttribute("Admin") != null) {
				String name = (String) session.getAttribute("Admin");
				session.setAttribute("Admin",name);

				out.print("Welcome " + name );
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
<form method="post" name="frm"  onsubmit="return validate();"><table border="1" cellspacing="5" cellpadding="5" width="50%"  align="center" style="height:80%;" >
<h1>Update Users</h1>
<tr bordercolor=" #C0C0C0"><td align="center"><b><label for="txtNo">Employee ID:</label></b></td><td><input type="text" name="EmployeeID"  value="<%=request.getAttribute("EmployeeID")%>" placeholder="Enter Employee ID"   style="width:200px" readonly="readonly"></td></tr>
<tr bordercolor=" #C0C0C0"><td align="center"><b><label for="txtName">Employee Name:</label></b></td><td><input type="text" name="EmployeeName" value="<%=request.getAttribute("EmployeeName")%>" placeholder="Enter Employee Name"  style="width:200px" readonly="readonly"></td></tr>
<tr bordercolor=" #C0C0C0"><td align="center"><b><label for="txtEmail">Email ID:</label></b></td><td><input type="text" name="EMAIL" value="<%=request.getAttribute("EMAIL")%>" placeholder="@mindacorporation.com"  onblur="validateEmail(this)" style="width:200px"></td></tr>
<%-- <tr bordercolor=" #C0C0C0"><td align="center"><b><label for="txtName">Password:</label></b></td><td><input type="password" name="PASSWORD" value="<%=request.getAttribute("PASSWORD")%>" style="width:200px" readonly="readonly"></td></tr> --%>
<tr bordercolor=" #C0C0C0"><td align="center"><b>Role:<span class="required">*</span></b></td><td><select name="ROLE" style="width:205px" >
<OPTION value="<%=request.getAttribute("ROLE")%>"><%=request.getAttribute("ROLE")%></OPTION>
 <%
                   Statement statement = con.createStatement() ;

                	resultset =statement.executeQuery("select distinct role from users") ;
      while(resultset.next()){ %>
            <option ><%= resultset.getString(1)%></option>
          
        <% } %></select></td></tr>
<tr bordercolor=" #C0C0C0"><td align="center"><b>Department:<span class="required">*</span></b></td><td><select name="Department" style="width:205px">
<OPTION value="<%=request.getAttribute("Department")%>"><%=request.getAttribute("Department")%></OPTION>
    <%
                    Statement statement1 = con.createStatement() ;
                    resultset1 =statement1.executeQuery("select distinct Department from department") ;
   while(resultset1.next()){ %>
            <option ><%= resultset1.getString(1)%></option>
           
        <% } %></select></td></tr>
        <tr bordercolor=" #C0C0C0"><td align="center" ><b>Approver:<span class="required">*</span></b></td><td><select name="Approver" style="width:202px">
        <OPTION value="<%=request.getAttribute("Approver")%>"><%=request.getAttribute("Approver")%></OPTION>
  <% 
  
                    Statement statement2 = con.createStatement() ;
                    resultset2 =statement2.executeQuery("select distinct EmployeeName from users") ;
   while(resultset2.next()){ %>
            <option><%= resultset2.getString(1)%></option>
           
        <% } %></select></td></tr></table>
       
        <table>
    <tr bordercolor=" #C0C0C0"><td align="center"><input type="submit" value="Update" id="submit" onclick="form.action='<%=request.getContextPath()%>/EditUser';" ></td></tr>
 </table>
 <%
//**Should I input the codes here?**
       }
       catch(Exception e)
       {
            out.println("wrong entry"+e);
       }
%>
</form>
</article></center>
 </div>
</div>
</body>
</html>