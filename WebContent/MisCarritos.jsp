<%if(request.getSession().getAttribute("cliente")==null){response.sendRedirect("iniciarSesion.jsp");} %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Mi Carrito</title>
	<link rel="shortcut icon" href="png/favicon.ico">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
	</head>
	<body class="bg-primary">
		<%@include file="header.jsp" %>	
	
		
		
		<nav role="navigation" class="navbar navbar-default navbar-fixed-bottom navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<ul class="nav navbar-nav">
						<li>contactenos</li>
					</ul>
				</div>
			</div>
		</nav>
	
	<script src="bootstrap/js/jquery-3.4.1.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>	
	</body>
</html>