<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
function checkPasswordMatch() {
    var password = $("#txtNewPassword").val();
    var confirmPassword = $("#txtConfirmPassword").val();

    if (password != confirmPassword)
        $("#divCheckPasswordMatch").html("Passwords do not match!");
    else
		$("#divCheckPasswordMatch").html("");
	}
	
	

$(document).ready(function () {
   $("#txtConfirmPassword").keyup(checkPasswordMatch);
});


function myFunction() {
    var x = document.getElementById("snackbar")
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}
</script>


<style>
.centered {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	font-size: 20px;
	padding: 5px;
	z-index: 100;
}

#snackbar {
    visibility: hidden;
    min-width: 250px;
    margin-left: -125px;
    background-color: #333;
    color: #fff;
    text-align: center;
    border-radius: 2px;
    padding: 16px;
    position: fixed;
    z-index: 1;
    left: 50%;
    bottom: 30px;
    font-size: 17px;
}

#snackbar.show {
    visibility: visible;
    -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
    animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@-webkit-keyframes fadein {
    from {bottom: 0; opacity: 0;} 
    to {bottom: 30px; opacity: 1;}
}

@keyframes fadein {
    from {bottom: 0; opacity: 0;}
    to {bottom: 30px; opacity: 1;}
}

@-webkit-keyframes fadeout {
    from {bottom: 30px; opacity: 1;} 
    to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
    from {bottom: 30px; opacity: 1;}
    to {bottom: 0; opacity: 0;}
}

</style>
</head>
<body>
	<div class="container centered">
		<div class="row">
			<div class="row">
				<div class="col-md-4 col-md-offset-3">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="text-center">
								<h3 class="text-center">Reset Password</h3>
								<div class="panel-body">

									<form class="form" method="post">
										<fieldset>
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon"> <!-- <i class="glyphicon glyphicon-wrench "> -->
														</i>
													</span>
													<!--New Password-->
													<input id="txtNewPassword" placeholder="New Password"
														class="form-control" type="password"
														onChange="checkPasswordMatch();" required="">
													<%-- <span style="color:red;font-size:12px"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span> --%>
												</div>
												<br>
												<div class="input-group">
													<span class="input-group-addon"> <!-- <i class="glyphicon glyphicon-envelope color-blue"></i> -->
													</span>
													<!--Confirm New Password-->
													<input id="txtConfirmPassword" name="newPassword"
														placeholder="Confirm New Password" class="form-control"
														type="password" required="">

												</div>
											</div>
											<div class="registrationFormAlert text-danger"
												id="divCheckPasswordMatch"></div>
								</div>
								<div class="form-group">
									<input class="btn btn-lg btn-primary btn-block"
										value="Reset My Password" type="submit"
										onclick="form.action='<%=request.getContextPath()%>/ResetPassword?empid=<%=request.getAttribute("empid") %>'">
										<div id="snackbar">Password Updated Successfully</div>
								</div>
								</fieldset>
								
								</form>
								
								<!--/end form-->
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
<span></span>


</body>
</html>