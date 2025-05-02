<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<title>Recuperar Contraseña</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">	
	</head>
	<body class="bg-light">
		<%@include file="header.jsp" %>
		
		<form action="RecuperarContraseniaServlet" method="post">
			<div class="form-group">
				<label class="label-control" for="codigo">Ingrese el codigo enviado a su e-mail</label>
				<input class="form-control" name="codigo" type="password" id="codigo"/>
			</div>
			<div class="form-group">
				<input class="btn btn-success" type="submit" value="OK">
			</div>
		</form>
		
		<%@include file="footer.jsp" %>
		
		<script src="custom/custom-scripts.js"></script>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>