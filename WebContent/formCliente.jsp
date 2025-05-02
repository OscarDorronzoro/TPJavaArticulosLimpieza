<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<title>Sign Up Customer</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
	</head>
	<body class="bg-light">
	
		<%@include file="header.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Sign Up Customer</h1>
						<form action="FormularioClienteServlet" method="post">		
							<div class="form-group">
								<label for="username" class="control-label">Username</label>
								<input class="form-control" name="username" id="username"/>
							</div>
							<div class="form-group">
								<label for="password" class="control-label">Password</label>
								<input class="form-control" name="password" id="password" type="password"/>
							</div>		
							<div class="form-group">
								<label for="name" class="control-label">Name</label>
								<input class="form-control" name="name" id="name"/>
							</div>
							<div class="form-group">
								<label for="lastname" class="control-label">Last name</label>
								<input class="form-control" name="lastname" id="lastname"/>
							</div>
							<div class="form-group">
								<label for="dni" class="control-label">DNI</label>
								<input class="form-control" name="dni" id="dni"/>
							</div>	
							<div class="form-group">
								<label for="email" class="control-label">E-Mail</label>
								<input class="form-control" name="email" id="email"/>
							</div>	
							<div class="form-group">
								<input type="submit" class="btn btn-success btn-block"  title="Click to sign up" value="Sign Up"/>
							</div>
						</form>
					</section>
				</div>
			</div>
		</div>
		
		<%@include file="footer.jsp" %>
		
		<script src="custom/custom-scripts.js"></script>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>