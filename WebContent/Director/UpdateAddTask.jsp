<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="java.sql.*"%>
    <%@page language="java"%>    
     <%@ page import="java.util.*" %>
    <%@ page import="com.login.util.DBConnection" %>
<%ResultSet resultset1 =null;%>
<%ResultSet resultset2 =null;%>
<%ResultSet resultset =null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="utf-8">
<title>AddTask</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/JSFormValidation.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src='http://code.jquery.com/jquery.min.js'></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
    href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
    src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
    </style>
<SCRIPT language="javascript">
    var date = new Date();
    document.getElementById("demo").value = (date.getMonth() + 1) + '/'
            + date.getDate() + '/' + date.getFullYear();
    function addRow(tableID) {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var colCount = table.rows[0].cells.length;
        for (var i = 0; i < colCount; i++) {
            var newcell = row.insertCell(i);
            newcell.innerHTML = table.rows[0].cells[i].innerHTML;
            //alert(newcell.childNodes);
            switch (newcell.childNodes[0].type) {
            case "text":
                newcell.childNodes[0].value = "";
                break;
            case "checkbox":
                newcell.childNodes[0].checked = false;
                break;
            case "select-one":
                newcell.childNodes[0].selectedIndex = 0;
                break;
            }
        }
    }
    function deleteRow(tableID) {
        try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            for (var i = 0; i < rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if (null != chkbox && true == chkbox.checked) {
                    if (rowCount <= 1) {
                        alert("Cannot delete all the Task.");
                        break;
                    }
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
            }
        } catch (e) {
            alert(e);
        }
    }
    //Calender Date
    $('#cmd')
            .click(
                    function() {
                        $('#content')
                                .append(
                                        '<br>a datepicker <input class="datepicker_recurring_start"/>');
                    });
    $('body').on('focus', ".datepicker_recurring_start", function() {
        $(this).datepicker();
    });
    //cal sum
    $(document).ready(function() {
        //iterate through each textboxes and add keyup
        //handler to trigger sum event
        $(".code").each(function() {
            $(this).keyup(function() {
                calculateSum();
            });
        });
    });
    function calculateSum() {
        var sum = 0;
        //iterate through each textboxes and add the values
        $(".code").each(function() {
            //add only if the value is number
            if (!isNaN(this.value) && this.value.length != 0) {
                sum += parseFloat(this.value);
            }
        });
        //.toFixed() method will roundoff the final sum to 2 decimal places
        $("#sum").html(sum.toFixed(2));
    }
    //set date
    function myFunction() {
        var d = new Date();
        var n = d.getDate();
        document.getElementById("demo").innerHTML = n;
    }
    //Check all checkboxes
function toggle(source) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i] != source)
            checkboxes[i].checked = source.checked;
    }
}
    // Dropdown project name with respective proid
    function populateCustomerId(){
	    var selectBox = document.getElementById('selectBox');

	    /* selected value of dropdown */
	    var selectedCustomerId = selectBox.options[selectBox.selectedIndex].value;

	    /* selected value set to input field */
	    document.getElementById('proId').value = selectedCustomerId; 
	} 
 
 
 
</SCRIPT>
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
</head>
<body>
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
	resultMap.put(resultset1.getString(2),resultset1.getString(3));
}
Set<String> keys = resultMap.keySet();
%>
    <form name="form"  method="post">
   <div class="container">
<header><img src="${pageContext.request.contextPath}/images/logo.png" alt="Avatar" class="avatar">
<tm style="font-family:calibri">Timesheet Management System</tm>
  <user><%
		if (session != null) {
			if (session.getAttribute("Director") != null) {
				String name = (String) session.getAttribute("Director");
				session.setAttribute("Director",name);

				out.print("Welcome " + name+"   Director" );
			} else {
				response.sendRedirect("/TimeSheet/"); 
			}
		}
	%></user>
  </header>
  <div class="HorizontalNav">
<ul><li>
<a class="active" href="${pageContext.request.contextPath}/DirectorDashboard">Home</a></li>
  <li class="dropdown">
    <a href="${pageContext.request.contextPath}/Director/DirectorTask.jsp" class="dropbtn">Task</a>
    <div class="dropdown-content">
      <a href="${pageContext.request.contextPath}/Director/DirectorTask.jsp">Create Task</a>
      <a href="${pageContext.request.contextPath}/Director/ViewDirTask.jsp">Display Task </a>
      </div>
  </li>
   <li><a href="${pageContext.request.contextPath}/Director/Approval.jsp">Approval</a></li>
    <li><a  href="${pageContext.request.contextPath}/Director/DirectorReport.jsp">Reports</a></li>
     <li style="float:right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></li>
</ul>
</div>
            <div style="  height: 100%; margin-top: 40px;">
                <center>
                    <article>
                   
 
 	<TABLE id="dataTable11" width="1160px" border="1" bordercolor="#DCDCDC" style="height:40px"  >
	<TR>
	
	<th id="pn"style="width:222px;fontfamily:Calibri;color:#007BC0;bordercolor:rgb(211,211,211)">Project Name : <span title= "This field is mandatory" style="color:red">*</span></th>
    <th id="pi" style="width:111px;fontfamily:Calibri;color:#007BC0;bordercolor:rgb(211,211,211)">Project ID :</th>
    <th id="tc"style="fontfamily:Calibri;width:159px;color:#007BC0;bordercolor:rgb(211,211,211)">Task Category :<span style="color:red">*</span></th>
    <th id="td"style="fontfamily:Calibri;width:224px;color:#007BC0;bordercolor:rgb(211,211,211)">Task Description :</th>
    <th id="d"style="width:160px;fontfamily:Calibri;color:#007BC0;bordercolor:rgb(211,211,211)">Date :<span title= "This field is mandatory" style="color:red">*</span></th>
    <th id="h"style="color:#007BC0;bordercolor:#007BC0 ;width:80px;">Hours :<span title= "This field is mandatory" style="color:red">*</span></th>
  </tr>
	</TABLE>
 <br>
 
	<TABLE id="dataTable" width="1160px" border="1" bordercolor="#DCDCDC"  >
	
		<TR>

			 <TD><select id="selectBox" class="slt_prod" name="proname" onchange="populateCustomerId();"style="width:222px;fontfamily:Calibri"required name="title">
                        <OPTION value="<%=request.getAttribute("pname")%>"><%=request.getAttribute("pname")%></OPTION>
		    
		      	<% for (String key : keys){ %>
		      			<OPTION value=<%=key %>><%=resultMap.get(key) %></OPTION>
		      	<% } %>
   			 </select></TD>
   			 <TD><input id="proId" class="txt_prod" type="text" value="<%=request.getAttribute("projId")%>" name="proId" style="width:111px;fontfamily:Calibri; background-color:#F5F5F5" readonly="readonly"/></TD>
                     <td> <SELECT name="values"  style="fontfamily:Calibri;width:159px">
                      <OPTION value="<%=request.getAttribute("taskCat")%>"><%=request.getAttribute("taskCat")%></OPTION>
				<%
    try{
    	 
		
        resultset =statement.executeQuery("SELECT taskCategory FROM taskcatlist;") ;
%>
				  <%  while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
           
        <% } %>
        <%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>
</select></td>		
			
			<TD> <input type ="text"  name="description" value="<%=request.getAttribute("tdes")%>" style="width:224px;fontfamily:Calibri"></textarea></TD>
			<TD><input type="text" name="date" id="startdate" value="<%=request.getAttribute("date")%> " style="width:160px;" /></TD>
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
			<TD><input type="text" name="hours" value="<%=request.getAttribute("hours")%> " style="width:80px;"/></TD>
			
		</TR>
	</TABLE>
	<br>

<br><br>
<input type="submit" value="Update" style="margin-left: 0%;width:80px;height:32px;background-color:#007BC0;color:white" onclick="form.action='<%=request.getContextPath()%>/DirectorUpdateTask';" />

                    </article>
                </center>
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
    </form>
</body>
</html>