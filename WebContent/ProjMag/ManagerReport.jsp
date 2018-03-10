<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
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
             padding-right: 10px;
            }
 #enddate
        {
           background:  url(https://i.imgur.com/u6upaAs.png) right no-repeat;
             background-repeat: no-repeat;
             padding-right: 10px;
            }
	</style>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js"></script>
<script>
  webshims.setOptions('waitReady', false);
  webshims.setOptions('forms-ext', {type: 'date'});
  webshims.setOptions('forms-ext', {type: 'time'});
  webshims.polyfill('forms forms-ext');
</script>
</head>
<body>
<div class="container">
<header><img src="${pageContext.request.contextPath}/images/logo.png" alt="Avatar" class="avatar">
<tm style="font-family:calibri">Timesheet Management System</tm>
  <user><%
		if (session != null) {
			if (session.getAttribute("Manager") != null) {
				String name = (String) session.getAttribute("Manager");
				session.setAttribute("Manager",name);

				out.print("Welcome " + name+"   Manager" );
			} else {
				response.sendRedirect("/TimeSheet/");  			}
		}
	%></user>
  </header>
  <div class="HorizontalNav">
<ul><li>
<a class="active" href="${pageContext.request.contextPath}/ManagerDashboard">Home</a></li>
  <li class="dropdown">
    <a href="${pageContext.request.contextPath}/ProjMag/CreateTask.jsp" class="dropbtn">Task</a>
    <div class="dropdown-content">
      <a href="${pageContext.request.contextPath}/ProjMag/CreateTask.jsp">Create Task</a>
      <a href="${pageContext.request.contextPath}/ProjMag/PmViewTask.jsp">Display Task </a>
      </div>
  </li>
  <li><a href="${pageContext.request.contextPath}/DisplayApproval">Approval</a></li>
 <li><a href="${pageContext.request.contextPath}/ProjMag/ManagerReport.jsp">Report</a></li>
 <li style="float:right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></li>
</ul>
</div>
 <div style="align:center;height:100%;">
<center>
<article>
    <form method="post" name="frm" action="empevent">
    <table border="1" bordercolor="#C0C0C0" cellspacing="4" cellpadding="4" width="55%" align="center" >
    <h1>Export Reports</h1>
<tr></tr>
   <tr><b><TD colspan=2 style="font-size:12pt;color:#00000; font-family:calibri(body)"><b>Report Type:</b>&nbsp;&nbsp;<SELECT name="dropdown">
					<OPTION value="My Report">My Report</OPTION>
					<OPTION value="Team's Report">Team's Report</OPTION>
					</SELECT>			
			</b></TD></tr>
     <tr><td ><b>Start Date:</b></td><td><input type="text" id="startdate" name="startdate" placeholder=" mm/dd/yy" style="width:160px" required name="title"/>
 <td ><b>End Date:</b></td><td><input type="text" id="enddate" name="enddate" placeholder=" mm/dd/yy" style="width:160px" required name="title"/></td>
 
 
 <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

<!-- for date picker -->
<script
    src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<!-- for date picker -->
<script
    src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<!-- for date picker -->
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
		
	   else if (start<end) {
	 
		var days   = (end - start)/1000/60/60/24;
		$('#days').val(days)
		
	}

	 else {
		
	alert ("End Date must be latter than Start Date!");
	$('#startdate').val("");
	$('#enddate').val("");
	$('#days').val("");
	}
	}
	); //end change function
	}); //end ready
	   </script>
											 
											 
							
 
 <td colspan=1 align="center">
<INPUT TYPE="image" SRC="${pageContext.request.contextPath}/images/icon11.jpg" name="show" class="button1" onclick="form.action='<%=request.getContextPath()%>/GenerateReport';" style="height:40px;width:40px">
    </td></tr>
    </table>
    </form>
</article></center>
 </div>
</div>
</body>
</html>