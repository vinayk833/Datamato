<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
    <%@page language="java"%>    
     <%@ page import="java.util.*" %>
      <%@ page import="java.sql.*" %>
    <%@ page import="com.login.util.DBConnection" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>UpdateProjects</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
  <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/> 
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/searchTextbox.css" /><!-- search textbox -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script><!-- search textbox -->
<script src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script><!-- search textbox -->
<script>
jQuery(function(){
$("#name").autocomplete("ProjectList.jsp");
});
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
	body {
  font-family: Calibri; 
} 
.search{
    width:280px;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
    background-color: white;
  background-image: url('http://icons.iconarchive.com/icons/gakuseisean/ivista-2/64/Start-Menu-Search-icon.png');
      background-position: 1px 1px; 
    background-repeat: no-repeat;
    padding: 20px 20px 12px 60px;
    -webkit-transition: width 0.4s ease-in-out;
    transition: width 0.4s ease-in-out;
}
</style>

	</head>
<body>

<form name="form"  method="post" >

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
       <a href="${pageContext.request.contextPath}/Admin/UpdateTask.jsp">Update Task </a>
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
<h2>Projects</h2>

<!-- <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search Project name" title="Type in a name" > -->
<input id="name" type="text" name="ProjName" class="search"  placeholder="Search Project name"  onkeyup="showState(this.value)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="Display" style="margin-left: 0%;width:80px;height:32px;background-color:#007BC0;color:white" onclick="form.action='<%=request.getContextPath()%>/SearchProject';"/>&nbsp;&nbsp;
<br><br>  
 <div id='country'>  <br><br><br><br>
 </div>  
<table align="left" cellpadding="1" cellspacing="1" width="100%" border="1">
<tr>
</tr>
	<tr style="color:#090C9B">
		<td><b>Customer Name</b></td>
		<td><b>Project ID</b></td>
		<td><b>Project Name</b></td>
		<td><b>Project Description</b></td>
		<td><b>Project type</b></td>
		<td><b>Product Managers</b></td>
		<td><b>Start Date</b></td>
		<td><b>End Date</b></td>
		<td><b>Edit</b></td>
		<td><b>Delete</b></td>
	</tr>
	 <%
                int count = 0;
                String color = "#F9EBB3";
                if (request.getAttribute("piList") != null) {
                    ArrayList al = (ArrayList) request.getAttribute("piList");
                    System.out.println(al);
                    Iterator itr = al.iterator();
                    while (itr.hasNext()) {

                        if ((count % 2) == 0) {
                            color = "#eeffee";
                        }
                        count++;
                        ArrayList pList = (ArrayList) itr.next();
            %>
           <tr style="background-color:<%=color%>;">
              <td><%=pList.get(0)%></td>
                <td><%=pList.get(1)%></td>
                 <td><%=pList.get(2)%></td>
                  <td><%=pList.get(3)%></td>
                   <td><%=pList.get(4)%></td>
                    <td><%=pList.get(5)%></td>
                     <td><%=pList.get(6)%></td>
                      <td><%=pList.get(7)%></td>
                     <td><a href="<%=request.getContextPath()%>/EditProject?ProjName=<%=pList.get(2)%>"><input type="button" value="Update" style="margin-left: 0%;width:80px;height:32px;background-color:#007BC0;color:white" /></a></td>
                    <td><a href="<%=request.getContextPath()%>/DeleteProject?ProjName=<%=pList.get(2)%>"><input  type="button" value="Delete" style="margin-left: 0%;width:80px;height:32px;background-color:#007BC0;color:white" onclick="if (confirm('Are you sure you want to delete?')){form.action='<%=request.getContextPath()%>/DeleteProject'}else { return false; };"/></a></td>
                 </tr>
            <%
                    }
                }
                if (count == 0) {
            %>
            <tr>
                <td colspan=4 align="center"
                    style="background-color:#eeffee"><b>No Record Found..</b></td>
            </tr>
            <%            }
            %>
	</table><br><br><br><br>
</article></center>
 </div>
</div>
</form>
</body>
</html>