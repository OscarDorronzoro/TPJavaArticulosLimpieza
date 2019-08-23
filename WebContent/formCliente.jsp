<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registrar Cliente</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
	</head>
	<body>
		<%@include file="header.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Registrarse</h1>
						<form action="FormularioClienteServlet" method="get">		
							<div class="form-group">
								<label for="username" class="control-label">Usuario</label>
								<input class="form-control" name="username" id="username"/>
							</div>
							<div class="form-group">
								<label for="password" class="control-label">Contraseña</label>
								<input class="form-control" name="password" id="password" type="password"/>
							</div>		
							<div class="form-group">
								<label for="nombre" class="control-label">Nombre</label>
								<input class="form-control" name="nombre" id="nombre"/>
							</div>
							<div class="form-group">
								<label for="apellido" class="control-label">Apellido</label>
								<input class="form-control" name="apellido" id="apellido"/>
							</div>
							<div class="form-group">
								<label for="DNI" class="control-label">DNI</label>
								<input class="form-control" name="DNI" id="DNI"/>
							</div>	
							<div class="form-group">
								<input type="submit" class="btn btn-success btn-block"  title="Presione para registrarse" value="OK"/>
							</div>
						</form>
					</section>
				</div>
			</div>
		</div>
		
		<%@include file="footer.jsp" %>	
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="bootstrap/js/miJavaSript.js"></script>
	</body>
</html>