<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<title>Iniciar Sesión</title>
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
						<h1>Iniciar Sesión</h1>
						<form action="LogInServlet" method="post">		
							<div class="form-group">
								<label>Usuario</label>
								<input class="form-control" name="username" id="username" required/>
							</div>
							<div class="form-group">
								<label>Contraseña</label>
								<input class="form-control" name="password" type="password" required/>
							</div>	
							<a onclick="recoverPassword()">Olvide mi contraseña</a>	
							<input class="btn btn-success btn-block"  title="Presione para iniciar sesion" value="OK" type="submit"/>
						</form>
					</section>
				</div>
			</div>
		</div>
		
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		
		<%@include file="footer.jsp" %>
		
		<script src="custom/custom-scripts.js"></script>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>