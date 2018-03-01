<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>AdminDashboard</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
	
	 <link href='${pageContext.request.contextPath }/css/fullcalendar.min.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath }/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<link href='${pageContext.request.contextPath }/css/bootstrap-combined.min.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/js/moment.min.js'></script>
<script src='${pageContext.request.contextPath }/js/lib/jquery.min.js'></script>
<script src='${pageContext.request.contextPath }/js/fullcalendar.min.js'></script>
<script src='${pageContext.request.contextPath }/js/bootstrap.min.js'></script>
<script src='${pageContext.request.contextPath }/js/gcal.js'></script>
	 
<script type="text/javascript">
$(document).ready(function() {
	  $('#calendar').fullCalendar({
	    header: {
	      left: 'prev,next today',
	      center: 'title',
	      right: 'month,basicWeek,basicDay',
	      defaultAllDay: true,
	    },
	    dayClick: function(date, jsEvent, view) {
	        var clickeddate = date.format();
	    	//alert(clickeddate);
	        window.location.href="${pageContext.request.contextPath}/Admin/AddTask.jsp";

	    },
	     
	     //editable: false,
	     /*lazyFetching: false,
	    defaultDate: '$today',
	    editable: false,
	    eventLimit: 10,
	    weekMode: 'liquid',
	    dayPopoverFormat: 'DD/MM/YYYY', */

	    eventSources: [
	             {
	            	 url: '<%=request.getContextPath()%>/CalendarServlet',
	            	 color: 'green',
	            	 textColor:'white',
	             },
	             {
	            	 url: '<%=request.getContextPath()%>/TaskServlet',
	            	 color: '#007BC0',
	            	 textColor:'white',
	             }
	             ],
	             eventRender: function (event, element) { 
	            	
	             	element.popover({
	             		
	             		title:event.title,
	             		placement: event.start.day()>3?'left':'right',
	             		html:true,
	             		trigger: 'hover',
	             		content: event.msg
	             	});
	          },
	    	
	    	 /* eventClick: function(event, element) {

	            event.title = "CLICKED!";

	          //  $('#calendar').fullCalendar('updateEvent', event);
	            window.location.href="${pageContext.request.contextPath}/Admin/AddTask.jsp";

	        },  */
	          

	     
	  });
	 
	});
</script>
<style>

  .fc-sun {
  	color: #190502;
  	background-color: #D17D6F;
  }
  .fc-sat{
  	color: #190502;
  	background-color: #D17D6F;
  }
  body {
    margin: 40px 10px;
    padding: 0;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

</style>
<script type="text/javascript">
$(function(){
    var $page = jQuery.url.attr("file");
    $('HorizontalNav li a').each(function(){
        var $href = $(this).attr('href');
        if ( ($href == $page) || ($href == '') ) {
            $(this).addClass('on');
        } else {
            $(this).removeClass('on');
        }
    });
});
</script>
    
    
</head>
<body>

<form name="form" action="<%=request.getContextPath()%>/AdminDashboard" method="post">
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
 <div style="padding:1px 16px;height:100%;margin-top:30px;">
<center>
<div id="calendar"></div>
<table align="left" cellpadding="3" cellspacing="3" width="100%" border="0">
<tr>
<td><div class="box red"></td><td>Weekly Off</div></article></center></td>&nbsp;
<td><div class="box darkGreen"></td><td>Paid Holiday</td></div>&nbsp;
<td><div class="box blue"></td><td>Current date</td></div>&nbsp;
<td><div class="box white"></td><td>Working Days</td></div>&nbsp;

</tr>
</table>
</center>
 </div>
</div>
</form>
</body>
</html>