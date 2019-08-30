<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doña Mary Limpieza</title>
</head>
<body class="bg-light">
	<%@page import="entities.Articulo" %>
	<% Articulo articulo= (Articulo)request.getAttribute("articulo"); %>
	<%=articulo.getDescripcion() %>
	<%=articulo.getPrecio() %>
	implementado en breve
</body>
</html>