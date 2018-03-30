<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>UserDashboard</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">

<script type="text/javascript" src='${pageContext.request.contextPath }/js/jquery-1.8.3.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath }/js/jquery-ui-1.10.2.custom.js'></script>
<link type="text/css" href='${pageContext.request.contextPath}/css/jquery-ui-1.10.2.custom.css' rel='stylesheet' />





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
             background-repeat: no-repeat;
             padding-right: 17px;
            }
 #enddate
        {
          background:  url(https://i.imgur.com/u6upaAs.png) right no-repeat;
             background-repeat: no-repeat;
             padding-right: 17px;
            }
    </style>
</head>
<body>
 <div class="container">
<header><img src="${pageContext.request.contextPath}/images/logo.png" alt="Avatar" class="avatar">
<tm style="font-family:calibri">Timesheet Management System</tm>
  <user><%
		if (session != null) {
			if (session.getAttribute("User") != null) {
				String name = (String) session.getAttribute("User");
				session.setAttribute("User",name);

				out.print("Welcome " + name+"   User");
			} else {
				response.sendRedirect("/TimeSheet/");  			}
		}
	%></user>
  </header>
  <div class="HorizontalNav">
<ul><li>
<a class="active" href="${pageContext.request.contextPath}/UserDashboard">Home</a></li>
   <li class="dropdown">
    <a href="${pageContext.request.contextPath}/JSP/emp_event.jsp" class="dropbtn">Task</a>
    <div class="dropdown-content">
      <a href="${pageContext.request.contextPath}/JSP/emp_event.jsp">Create Task</a>
      <a href="${pageContext.request.contextPath}/JSP/viewevent.jsp">Display Task </a>
      </div>
  </li>
 <li> <a href="${pageContext.request.contextPath}/JSP/UserReport.jsp">My Report</a></li>
  <li style="float:right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></li>
</ul>
</div>
 <div style="align:center;height:100%;">
<center>
<article>
 <form method="post" name="frm" >
    <table border="1" bordercolor="#C0C0C0" cellspacing="4" cellpadding="4" width="60%" align="center" >
    <h1>Export Reports</h1>
<tr></tr>
  
     <tr><td ><b>Start Date:</b></td><td><input type="text" name="startdate" id="startdate" placeholder="mm/dd/yy" style="width:150px" required name="title"/>
 <td ><b>End Date:</b></td><td><input type="text" name="enddate" id="enddate"  placeholder=" mm/dd/yy" style="width:150px" required name="title"/></td>
 
 
 
</script>
<script>
$(document).ready(function() {

	$( "#startdate,#enddate" ).datepicker({
		 maxDate : "0",
		 maxDate:"0",
	changeMonth: true,
	changeYear: true,
	firstDay: 1,
	dateFormat: 'mm/dd/yy',
	})

	$( "#startdate" ).datepicker({ dateFormat: 'mm/dd/yy' });
	$( "#enddate" ).datepicker({ dateFormat: 'mm/dd/yy' });

	$('#enddate').change(function() {
	var start = $('#startdate').datepicker('getDate');
	var end   = $('#enddate').datepicker('getDate');

	if(start==null){
		alert("Please Enter the Start Date")
		$('#startdate').val("");
		$('#enddate').val("");
		$('#days').val("");}
		
	   else if (start<=end) {
	 
		var days   = (end - start)/1000/60/60/24;
		$('#days').val(days)
		
	}

	 else {
		
	alert ("End Date must be later than Start Date!");
	$('#startdate').val("");
	$('#enddate').val("");
	$('#days').val("");
	}
	}
	); //end change function
	}); //end ready
	   </script>
											 
											 
							
 
    <td colspan=1 align="center">
    
 <INPUT TYPE="image" SRC="${pageContext.request.contextPath}/images/icon11.jpg" name="show" class="button1" onclick="form.action='<%=request.getContextPath()%>/UserReport';" style="height:40px;width:40px">
    </td></tr>
    </table>
    </form>
    </article></center>
 </div>
</div>
</body>
</html>