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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resubmit</title>

		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
                       
                        <!-- date time piv -->
                        <script type="text/javascript">
                            $(document).ready(
                                    function() {
                                        $("#startdate").datepicker({
                                            maxDate : "0",
                                            changeMonth : true,
                                            changeYear : true,
                                            firstDay : 1,
                                            dateFormat : 'mm/dd/yy',
                                        })
                                        var x=document.getElementById("startdate").value;
                                        //alert(x);
                                        if(x == "null"){
                                        	//alert(x);
                                        	$( "#startdate" ).datepicker( "setDate", new Date());	
                                        } 
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
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
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
 function validation2(){
     var x = document.getElementById("Totalhour").value;
        if (x<8) {
            alert ('Total hours must be more than 8 Hours');
            return false;
        }
        else if(x=="null"){
        	
        	alert ('Please Select date and View Records then click on Resubmit');
            return false;
        }
           
        else if(x>=10){
        	
        	alert ('Are you sure to add more than 10 Hours');
        	 return true;
        }
           
        return true;
       
}
 </script>

</head>
<body>
<div class="container">
<header><img src="${pageContext.request.contextPath}/images/logo.png" alt="Avatar" class="avatar">
<tm style="font-family:calibri">TimeSheet Management System</tm>
  <user><%
		if (session != null) {
			if (session.getAttribute("User") != null) {
				String name = (String) session.getAttribute("User");
				session.setAttribute("User",name);
				Connection con = null;
				con = DBConnection.createConnection();
				System.out.println("connected!.....");
				PreparedStatement pst=con.prepareStatement("SELECT employeename FROM users where employeeid=?");
				pst.setString(1, name);
				ResultSet rs=pst.executeQuery();
				rs.next();
				String ename=rs.getString(1);
				out.print("Welcome " + ename);
				con.close();
				System.out.println("Connection closed");
				//out.print("Welcome " + name+"   User" );
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
       <a href="${pageContext.request.contextPath}/JSP/Resubmit.jsp">Resubmit</a>
      </div>
  </li>
 <li> <a href="${pageContext.request.contextPath}/JSP/UserReport.jsp">My Report</a></li>
  <li style="float:right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></li>
</ul>
</div>
 <div style="margin-left:5%;padding:1px 16px;height:60%;margin-top:10px;">
<center>
<article>

<br>
 <form method="post" name="frm">
    <table border="1" cellspacing="4" cellpadding="4" width="40%" align="center">
    <tr ><td  style="display:none"><b>Totalhour</b></td><td style="display:none"><input type="text" name="Totalhour" id="Totalhour" value="<%=request.getAttribute("Totalhour") %>" placeholder="  mm/dd/yy" style="display:none";/>
    
    
<tr><td style="width:70px"><b>Date:</b></td><td style="width:80px"><input type="text" name="startdate" id="startdate" value="<%=request.getAttribute("startdate") %>" placeholder="  mm/dd/yy" style="width:130px";/>
 

<td border="0" align="center" style="width:50px"><span><input  type="submit" name="show" value="View" onclick="form.action='<%=request.getContextPath()%>/ResubmitTask';"></span></td>
  
    </table><br><br>
    
  
    
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
       <td><b>Approval Status</b></td> 
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
                  <td><%=pList.get(8)%></td>
                  
                  
<td><a href="<%=request.getContextPath()%>/UserUpdateTask2?taskid=<%=pList.get(0)%>"><input type="button" value="Update" style="margin-left: 0%;width:80px;height:32px;background-color:#007BC0;color:white" /></a></td>
  <td><a href="<%=request.getContextPath()%>/UserDeleteTask2?taskid=<%=pList.get(0)%>&date=<%=pList.get(2)%>"><input type="button" name= "Delete" value="Delete" style="margin-left: 0%;width:80px;height:32px;background-color:#007BC0;color:white" onclick="if (confirm('Are you sure you want to delete?')){form.action='<%=request.getContextPath()%>/UserDeleteTask'}else { return false; };" /></a></td>
                  
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
            
            
    </table> 
    <br><br>
    
    	<input type="submit" name= "Reject" id="submitt" value="Resubmit" style="margin-left: 0%;width:80px;height:32px;background-color:#007BC0;color:white" onclick="form.action='<%=request.getContextPath()%>/UserSendMailApproval';return validation2(this)"/>
    
  
     </article></center>
 </div>
</div>

  </form>
</body>
</html>