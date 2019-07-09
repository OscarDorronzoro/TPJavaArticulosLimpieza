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
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Registrarse</h1>
						<form action="FormularioClienteServlet" method="get">		
							<div class="form-group">
								<label>Usuario</label>
								<input class="form-control" name="username"/>
							</div>
							<div class="form-group">
								<label>Contraseña</label>
								<input class="form-control" name="password" type="password"/>
							</div>		
							<div class="form-group">
								<label>Nombre</label>
								<input class="form-control" name="nombre"/>
							</div>
							<div class="form-group">
								<label>Apellido</label>
								<input class="form-control" name="apellido"/>
							</div>
							<div class="form-group">
								<label>DNI</label>
								<input class="form-control" name="DNI"/>
							</div>	
							<input class="btn btn-success btn-block"  title="Presione para registrarse" value="OK" type="submit"/>
						</form>
					</section>
				</div>
			</div>
		</div>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>