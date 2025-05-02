<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.nio.charset.Charset, java.net.URLDecoder" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<title>Doña Mary Limpieza</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
	</head>
	
	<body class="bg-light contenedor-centrado">
		
		<%@include file="header.jsp" %>
		
		<div class="error-box">
			<div class="error-title">
				Lo sentimos, ha ocurrido un problema
			</div>
			<div class="error-message">
				<%=request.getParameter("mensaje") %>
			</div>
		</div>
		
		<%@include file="footer.jsp" %>
		
	</body>
</html>