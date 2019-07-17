<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registrar Cliente</title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
	</head>
	<body class="bg-primary">
		<%@include file="header.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Registrarse</h1>
						<form action="FormularioClienteServlet" method="get">		
							<div class="form-group">
								<label for="username">Usuario</label>
								<input class="form-control" name="username" id="username"/>
							</div>
							<div class="form-group">
								<label for="password">Contraseña</label>
								<input class="form-control" name="password" id="password" type="password"/>
							</div>		
							<div class="form-group">
								<label for="nombre">Nombre</label>
								<input class="form-control" name="nombre" id="nombre"/>
							</div>
							<div class="form-group">
								<label for="apellido">Apellido</label>
								<input class="form-control" name="apellido" id="apellido"/>
							</div>
							<div class="form-group">
								<label for="DNI">DNI</label>
								<input class="form-control" name="DNI" id="DNI"/>
							</div>	
							<input type="submit" class="btn btn-success btn-block"  title="Presione para registrarse" value="OK"/>
						</form>
					</section>
				</div>
			</div>
		</div>
		
		<%@include file="footer.jsp" %>	
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>