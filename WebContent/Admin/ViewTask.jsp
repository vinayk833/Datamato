<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="java.sql.*"%>
    <%@page language="java"%>    
     <%@ page import="java.util.*" %>
    <%@ page import="com.login.util.DBConnection" %>
<%ResultSet resultset1 =null;%>
<%ResultSet resultset2 =null;%>
<%ResultSet resultset =null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; ">
<title>ViewTask</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/searchTextbox.css" /><!-- search textbox -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script><!-- search textbox -->
<script src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script><!-- search textbox -->


<script type="text/javascript" src='${pageContext.request.contextPath }/js/jquery-1.8.3.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath }/js/jquery-ui-1.10.2.custom.js'></script>
<link type="text/css" href='${pageContext.request.contextPath}/css/jquery-ui-1.10.2.custom.css' rel='stylesheet' />

  
<script>
$.noConflict();
jQuery(function(){
	 // $("#name").autocomplete("http://localhost:9444/TimeSheet/Admin/UserList.jsp");
var getUrl = window.location;
var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1]+"/"+"Admin"+"/"+"UserList.jsp";
$("#name").autocomplete(baseUrl);		
		});
  // Code that uses jQuery's $ can follow here.
jQuery( document ).ready(function( $ ) {
  // Code that uses jQuery's $ can follow here.

    $("#startdate").datepicker();
    $("#enddate").datepicker();

  });
// Code that uses other library's $ can follow here.
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
.search {
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

#myUL li a {
  border: 1px solid #ddd;
  margin-top: -1px; /* Prevent double borders */
  background-color: #f6f6f6;
  padding: 12px;
  text-decoration: none;
  font-size: 18px;
  color: black;
  display: block
}

#myUL li a:hover:not(.header) {
  background-color: #eee;
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
 

  <script>
function myFunction() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";

        }
    }
}
</script>

  <script type="text/javascript">
 function do_this(){

     var checkboxes = document.getElementsByName('approve[]');
     var button = document.getElementById('toggle');

     if(button.value == 'Select'){
         for (var i in checkboxes){
             checkboxes[i].checked = 'FALSE';
         }
         button.value = 'Deselect'
     }else{
         for (var i in checkboxes){
             checkboxes[i].checked = '';
         }
         button.value = 'Select';
     }
 }
 function populateCustomerId(){
	    var selectBox = document.getElementById('selectBox');

	    /* selected value of dropdown */
	    var selectedCustomerId = selectBox.options[selectBox.selectedIndex].value;

	    /* selected value set to input field */
	    document.getElementById('proId').value = selectedCustomerId; 
	} 
 
 
 function TDate() {
     var UserDate = document.getElementById("startdate").value;
     var endDate = document.getElementById("enddate").value;

 

     if (new Date(UserDate).getTime() >= new Date(endDate).getTime()) {
           alert("End Date must be later than Start Date");
           return false;
      }
 

     
     return true;
 }
</script>
 
  
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

				out.print("Welcome " + name+"   Admin" );
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
 <div style="margin-left:10%;padding:1px 16px;height:60%;margin-top:10px;">
<center>
<article>
<%

try{
	 Connection con = null;
	 con = DBConnection.createConnection();
    Statement statement1 = con.createStatement() ;
    Statement statement2 = con.createStatement() ;
    Statement statement = con.createStatement() ;
    
   
    resultset1 =statement1.executeQuery("select * from myproject") ;   
    resultset2 =statement2.executeQuery("select * from myproject") ;  
LinkedHashMap<String,String> resultMap = new LinkedHashMap<String,String>();
while(resultset1.next()){
	resultMap.put(resultset1.getString(2),resultset1.getString(1));
}
Set<String> keys = resultMap.keySet();
%>
<br>
   <form name="form"  method="post" action="<%=request.getContextPath()%>/AdminEditViewTask" onsubmit="return TDate()" >
    <table border="1" cellspacing="4" cellpadding="4" width="50%" align="center">
    <tr> <input id="name" type="text" name="EmpName" class="search" placeholder="Search Employee name"  onkeyup="showState(this.value)" required name="title" ></tr>

<br><br>  
 <div id='country'> 
 </div>  <br><br>
<tr><td style="width:100px"><b>Start Date:</b></td><td  style="width:100px"><input  type="text" name="startdate" id="startdate" placeholder=" mm/dd/yy" style="width:150px" required name="title";/>
 <td style="width:100px"><b>End Date:</b></td><td style="width:100px"><input  type="text" name="enddate" id="enddate" placeholder=" mm/dd/yy" style="width:150px" required name="title";/></td>
 

<td border="0" align="center"><span><input  type="submit" name="show" value="View" onclick="form.action='<%=request.getContextPath()%>/ViewAdminTask';"></span></td>
  
    </table><br><br>
    </form>
   <!--   <span><input type="button" id="toggle" value="Select" onClick="do_this()" /></span>-->
    
    <table align="left"  cellpadding="2" cellspacing="2" width="100%" border="1">
<tr>
</tr><br>
    <tr style="color:#090C9B">
    	
        <td><b>Task ID</b></td>
       <td><b>Employee ID</b></td>
        <td><b>Date</b></td>
        <td><b>Project Name</b></td>
         <td><b>Project ID</b></td>
        <td><b>Task Category</b></td>
         <td><b>Task Description</b></td>
      	<td><b>hours</b></td> 
    	 <td><b>Update</b></td>
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
          
         <td><a href="<%=request.getContextPath()%>/UpdateTaskLink?taskid=<%=pList.get(0)%>"><input type="button" value="Update" style="margin-left: 0%;width:80px;height:32px;background-color:#007BC0;color:white" /></a></td>
                    <td><a href="<%=request.getContextPath()%>/AdminDeleteViewTask?taskid=<%=pList.get(0)%>"><input  type="submit" value="Delete" style="margin-left: 0%;width:80px;height:32px;background-color:#007BC0;color:white"/></td> 
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
  <%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>
</body>
</html>