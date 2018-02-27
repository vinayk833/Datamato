<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="com.login.util.DBConnection" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; ">
<title>DeleteProject</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
 <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/> 
   <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script> 
   <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/JSFormValidation.css">
    
    <script>
           
            function validate() {
      
        if (document.frm.CustomerName.value == "")  {
            alert("Please Enter Customer Name.");
            document.frm.Description.focus();
            return false;
        }
      
         if (document.frm.ID.value == "") {
                alert("Please Enter Project ID.");
                document.frm.Description.focus();
                return false;
            }
          
        
        
         if (document.frm.ProjName.value == "") {
             alert("Please Enter Project Name.");
             document.frm.Description.focus();
             return false;
         }
        
        
        
        
         if (document.frm.Type.value == "") {
             alert("Please Select Project Type");
             document.frm.Description.focus();
             return false;
         }
        
        
         if (document.frm.ProductManager.value == "") {
             alert("Please Select Product Manager");
             document.frm.Description.focus();
             return false;
         }
        
        
         if (document.frm.StartDate.value == null) {
             alert("Please Enter Start Date");
             document.frm.Description.focus();
             return false;
         }
        
         if (document.frm.EndDate.value == null) {
             alert("Please Enter End Date");
             document.frm.Description.focus();
             return false;
         }
        
        
        
         return true;
        
        
        }
        </script>
    
    <style type="text/css">
    input[type=button]{
    background-color:  #007BC0;
    color: white;
    }
    input[type=submit]{
    background-color: #007BC0;
    color: white;
    }
    h1{
    font-family: Calibri;
    color: #106E9B;
    }
    body {
  font-family: Calibri;
}
#startdate
        {
             background:  url(https://i.imgur.com/u6upaAs.png) right no-repeat;
             
             padding-right: 10px;
            }
 #enddate
        {
           background:  url(https://i.imgur.com/u6upaAs.png) right no-repeat;
            
             padding-right: 10px;
            }
    </style>
    
   
</head>
<body>

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
      <a href="${pageContext.request.contextPath}/Admin/UpdateHoliday.jsp">Update Holiday</a>
     </div></li>
     <li><a href="${pageContext.request.contextPath}/Admin/AdminReport.jsp">Report</a></li>
     
       <li style="float:right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></li>
</ul>
</div>
 <div style="align:center;height:100%;">
<center>
<article>
<form method="post" name="frm" action="<%=request.getContextPath()%>/AddProject" onsubmit="return validate(this);">
<table border="1" bordercolor="#C0C0C0" cellspacing="4" cellpadding="4" width="40%" align="center" >
<h1>Delete Project </h1>
<tr bordercolor=" #C0C0C0"><th align="center" colspan="2" style="text-align: center;color: #106E9B; font-size: 20px !important;"><b>Customer</b></th></tr>
<tr bordercolor=" #C0C0C0"><td><b>Customer Name:<span class="required">*</span></b></td><td><input type="text" name="CustomerName" onkeypress="return isAlfa(event)" style="width:200px"></td></tr>
<tr  bordercolor=" #C0C0C0"><th align="center" colspan="2" style="text-align: center;color: #106E9B; bordercolor:#E0E0E0;font-size: 20px !important;"><b>Project</b></th></tr>
<tr  bordercolor=" #C0C0C0"><td ><b>Project ID:<span class="required">*</span></b></td><td><input type="text" name="ID" onkeypress="return isNumber2(event)" style="width:200px"></td></tr>
<tr  bordercolor=" #C0C0C0"><td><b>Project Name:<span class="required">*</span></b></td><td>
 <input type="text" name="ProjName" onkeypress="return isAlfa(event)" style="width:200px">
<tr  bordercolor=" #C0C0C0"><td style="width:200px"><b>Project Description:</b></td><td>
<textarea name="Description" rows="1" style="width:200px">
</textarea></td></tr>
<tr  bordercolor=" #C0C0C0"><td><b>Project Type:<span class="required">*</span></b></td><td>
 <input type="text" name="type"  style="width:200px">
 <tr  bordercolor=" #C0C0C0"><td><b>Product Managers:<span class="required">*</span></b></td><td>
 <input type="text"  name="Product Manager"  style="width:200px">
   
   <tr bordercolor=" #C0C0C0"><td ><b>Planned Start Date:<span class="required">*</span></b></td><td><input type="text" name="StartDate" id="startdate"  style="width:190px"  required name="title";/>
 <tr bordercolor=" #C0C0C0"> <td><b>Planned End Date:<span class="required">*</span></b></td><td><input type="text" name="EndDate" id="enddate"  style="width:190px"  required name="title";/>

 <tr  bordercolor=" #C0C0C0"><td colspan=2 align="center"><input type="submit" name="submit" value="Delete" onclick="if (confirm('Are you sure you want to delete?')){form.action='<%=request.getContextPath()%>/DeleteProject'}else { return false; };"/></td>
</tr>
</table>
<br><br><br><br></form>
</article></center>
 </div>
</div>
</body>
</html>