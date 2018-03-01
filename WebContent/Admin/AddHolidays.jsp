<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AddHolidays</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<!-- for date picker -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<!-- for date picker -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<!-- for date picker -->
<style type="text/css">
input[type=reset] {
	background-color: #007BC0;
	color: white;
}

input[type=submit] {
	background-color: #007BC0;
	color: white;
}

h1 {
	font-family: Calibri;
	color: #106E9B;
}

body {
	font-family: Calibri;
}
#datepicker{
       background-repeat: no-repeat;
       background:  url(https://i.imgur.com/u6upaAs.png) right no-repeat;
       padding-right: 10px;
          
            }
</style>

<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : "mm/dd/yy"
		}).val()
	});
	function validate() {
		var sdate = document.frm.date.value;
		var sdateAr = sdate.split("/");
		if (document.frm.date.value == "") {

			alert("Please enter the date.");
			document.frm.date.focus();
			return false;
		}
		if (sdateAr.length != 3) {
			alert("Please enter valid Date in  mm/dd/yy format.");
			document.frm.date.value = "";
			document.frm.date.focus();
			return false;
		}
		if (document.frm.Description.value == "") {
			alert("Please enter description.");
			document.frm.Description.focus();
			return false;
		}
		return true;

	}
</script>
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
	<div style="align: center; height: 100%;">
			<center>
				<article>
				<form method="post" name="frm" action="<%=request.getContextPath()%>/AddHolidays" onsubmit="return validate(this);">
					<table border="1" bordercolor="#C0C0C0" cellspacing="4" cellpadding="4" width="25%" align="center">
						<h1>Add Holiday</h1>
						<tr></tr>
						<tr>
							<td><b>Date:</b></td>
							<td><input type="text" name="date" id="datepicker"
								style="width: 190px" />
						<tr>
							<td><b>Description:</b></td>
							<td><textarea name="Description" rows="1"
									style="width: 200px"></textarea>
						</tr>
					</table>
					<br> <span><input type="submit" name="submit" value="Add"></span>&nbsp;
					 

				</form>
				</article>
			</center>
		</div>
	</div>
</body>
</html>