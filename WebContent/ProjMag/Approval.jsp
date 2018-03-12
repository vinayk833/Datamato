<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="java.sql.*"%>
    <%@page language="java"%>    
     <%@ page import="java.util.*" %>
    <%@ page import="com.login.util.DBConnection" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<script src='http://code.jquery.com/jquery.min.js'></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script> -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!--  for datepicker -->
<script type="text/javascript" src='${pageContext.request.contextPath }/js/jquery-1.8.3.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath }/js/jquery-ui-1.10.2.custom.js'></script>
<link type="text/css" href='${pageContext.request.contextPath}/css/jquery-ui-1.10.2.custom.css' rel='stylesheet' />


<SCRIPT language="javascript">
 
//date picker 
$(document).ready(
        function() {
            $("#picker").datepicker({
                maxDate : "0",
                changeMonth : true,
                changeYear : true,
                firstDay : 1,
                dateFormat : 'yy-mm-dd', 
            })    
        });
        
//check if date is selected        
function IsEmpty(){ 

    if(document.form.date.value == "")
    {
      alert("Please select date");
      return false;     
    }else{
    	/* var acc = document.getElementsById("picker").value;
    	alert(acc); */
    	 return true;
    }
   
}

//Check all checkboxes
function toggle(source) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i] != source)
            checkboxes[i].checked = source.checked;
    }
}

//to check if any checkbox is selected or not
 function checkboxselect(){
	if ($("#formtest input:checkbox:checked").length > 0)
	{
	    return true;
	}
	else
	{
		alert("Please select atleast one task to be Approved or Rejected");
		return false;
	}	
} 

  </SCRIPT>
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

#picker
{
   background:  url(https://i.imgur.com/u6upaAs.png) right no-repeat;
   background-repeat: no-repeat;
   padding-right: 10px;
}

h1{
	font-family: Calibri; 
	color: #106E9B;
}
</style>
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
 <div style="padding:1px 16px;height:100%;margin-top:30px;">
<center>
<article>
 <br>
    <form name="form" id="formtest"  method="post">
    <span style=" margin-left:5px;margin-top:80px;width:222px;fontfamily:Calibri;color:#007BC0;bordercolor:rgb(211,211,211)">Date:</span> <input type="text" id="picker" name="date"/>
          <input type="submit" value="Display" style="margin-left: 0%;width:80px;height:32px;background-color:#007BC0;color:white" onClick="if(IsEmpty()){form.action='<%=request.getContextPath()%>/DisplayApproval'}else{return false};" /><br><br>
 <table id="table" align="left"  cellpadding="2" cellspacing="2" width="100%" border="1">
<tr>
</tr><br>
    <tr style="color:#090C9B">
    	 <!--<td><input type="button" id="toggle" value="Select" onClick="do_this()" /></td>
         <TD> <input type="checkbox" name="approve[]" value="1" style="width: 10px" /></TD>-->
							<td><b><input type="checkbox" name="allselect" onClick="toggle(this);">Select All</b></td>
							<td><b>Task ID</b></td>
							<td><b>Employee ID</b></td>
							<td><b>Employee Name</b></td>
							<td><b>Date</b></td>
							<td><b>Project Name</b></td>
							<td><b>Project ID</b></td>
							<td><b>Task Category</b></td>
							<td><b>Task Description</b></td>
							<td><b>hours</b></td>
							<td><b>Approved</b></td>
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
                <td><input type="checkbox" name="list" value="<%=pList.get(0)%>"></td>
                <td><%=pList.get(0)%></td>
                <td><%=pList.get(1)%></td>
                <td><%=pList.get(2)%></td>
                <td><%=pList.get(3)%></td>
                <td><%=pList.get(4)%></td>
                <td><%=pList.get(5)%></td>
                <td><%=pList.get(6)%></td>
                <td><%=pList.get(7)%></td>
                <td><%=pList.get(8)%></td>
                <td><%=pList.get(9)%></td>
                </tr>
               
            <%
                    }
                }
                if (count == 0) {
            %>
            <tr>
                <td colspan=4 align="center"
                    style="background-color:#eeffee"><b>No Task to be Approved or Rejected..</b></td>
            </tr>
            
            <%            }
            %>
    </table><br><br><br><br>
    <select name="select">
         <option value="Pending">Pending</option>
         <option value="approve">Approve</option>
         <option value="reject">Reject</option>
    </select> 
    <input type="submit" value="submit" class="messageCheckbox" onClick="if(checkboxselect()){form.action='<%=request.getContextPath()%>/ApproveStatus'}else{return false};"/>
    			
   </form>
</article></center>
 </div>
</div>
</body>
</html>