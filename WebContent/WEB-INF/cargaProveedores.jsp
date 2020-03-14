<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%Cliente c = (Cliente)request.getSession().getAttribute("cliente"); %>
		<%if(!(c!=null && c.isAdmin())){
		response.sendRedirect("iniciarSesion.jsp?pagina=CargaProveedoresServlet");
		return;
		}%>
		<title>Proveedores</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		
		<%@page import="entities.Proveedor" %>
		<%@page import="java.util.ArrayList" %>
		<% 
			@SuppressWarnings("unchecked")	
			ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>) request.getAttribute("proveedores"); 
		%>
	</head>
	<body>
		<%@include file="../header.jsp" %>
	
		<%@include file="../footer.jsp" %>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
		<script src="bootstrap/js/miJavaScript.js"></script>	
	</body>
</html>