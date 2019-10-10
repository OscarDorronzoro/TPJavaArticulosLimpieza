<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Doña Mary Limpieza</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
	</head>
	
	<body class="bg-light contenedor-centrado">
		
		<%@include file="header.jsp" %>
		
		<div class="error-box">
			<div class="error-title">
				Lo sentimos, ha ocurrido un problema
			</div>
			<div class="error-message">	
				<%=session.getAttribute("mensaje") %>
			</div>
		</div>
		
	</body>
</html>