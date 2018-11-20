<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta charset="UTF-8" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
<title>Login Page</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Login and Registration Form with HTML5 and CSS3" />
<meta name="keywords"
	content="html5, css3, form, switch, animation, :target, pseudo-class" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<style type="text/css">
html {
	overflow: auto;
}
</style>

</head>
<body>
	<div class="container">
		<!-- Codrops top bar -->
		<div class="codrops-top">
			<div class="clr"></div>
		</div>
		<!--/ Codrops top bar -->
		<header>
			<br>
			<br> <img border="0" src="images/logo2.png" alt="Pulpit rock" width="250" height="120" style="margin-left: 30%"><br>
			<h1 style="margin-left: 40%">
				<b><span style='color: red'>D</span><span style='color: #0000b2'>atamato</span>
					<span style='color: red'>T</span><span style='color: #0000b2'>echnologies</span> <span style='color: #0000b2'>(</span><span
					style='color: red'>20 November 6 pm</span><span style='color: #0000b2'>)</span></b>
			</h1>
			<br>
			<h2 style="margin-left: 30%">
				<b>TimeSheet Management System </b>
			</h2>
		</header>
		<section>
			<div>
				<table align="left" width="40%"
					style="margin-left: 0%; margin-top: -20%;">
					<tr border="1">
						<td><marquee behavior="slide" direction="right"
								scrollamount="50">
								<img src="images/icon.png" style="height: 500px">
							</marquee></td>
					</tr>
				</table>
				<br>
				<table align="left" width="20%"
					style="margin-left: -40%; margin-top: 12%">
					<tr border="1">
					<tr>
						<td><marquee behavior="slide" direction="right"
								scrolldelay="50">
								<img src="images/punchline.png"
									style="height: 50px; width: 250px; font-size: 1px; margin-left: 2%">
							</marquee></td>
					</tr>
				</table>
			</div>
			<div id="container_demo">
				<!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
				<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
					id="tologin"></a>
				<div id="wrapper">
					<div id="login" class="animate form">
						 <form name="form" action="<%=request.getContextPath()%>/LoginServlet" method="post">
							<p>
								<label for="username" class="uname" data-icon="u"
									style="color: black"><b> Employee ID: </b></label><br> <input
									id="username" name="EmployeeID" required="required" type="text" style="font-size:15px" />
							</p>
							<p>
								<br> <label for="password" class="youpasswd" data-icon="p"
									style="color: black"><b> Password :</b> </label><br> <input
									id="password" name="password" required="required"
									type="password" style="font-size:15px"/>
									<span style="color:red;font-size:12px"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
							</p>
							
							<p class="login button">
								<input type="submit" value="Login" />
							</p>
							<a style="color:blue" href="${pageContext.request.contextPath}/JSP/forgotpasswordmail.jsp">Forgot Password?</a>
						</form>

					</div>
					<!-- image scroll code put here -->
				</div>
			</div>
		</section>
	</div>
</body>
</html>