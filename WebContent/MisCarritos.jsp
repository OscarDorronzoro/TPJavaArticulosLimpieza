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
	<body>
		<%@include file="header.jsp" %>	
		
		<div>
		<%@page import="entities.Articulo"%>
		<%@page import="entities.Cliente"%>
		<%@page import="entities.LineaCarrito"%>
		<%@page import="java.util.ArrayList"%>
		<%@page import="logic.ABMCLineaCarrito"%>
		<%@page import="javax.servlet.http.HttpServletRequest"%>		
		<%! ArrayList<LineaCarrito> lineas= ((Cliente) request.getSession().getAttribute("cliente")).getMiCarrito().getLineas();%>
		<%for( LineaCarrito linea : lineas){%>
			<div class="row">
				<div class="col-md-2"><img height=150 src=<%=linea.getArticulo().getUrlImagen()%>></div>
				<div class="col-md-7">
					<div class="row">
						<div class="col-md-8">
							<ul class="list-unstyled">							
								<li>Descripción: <%=linea.getArticulo().getDescripcion()%></li>
								<li>Cantidad: <%=linea.getCantidad()%></li>
								<li>Precio: <%=linea.getArticulo().getPrecio()%></li>
							</ul>
						</div>
						<div class="col-md-4">
							<div class="row">
								<button value="Comprar ahora" name=<%="comprar"+linea.getArticulo().getCodArticulo()%> class="btn btn-success"></button>
								<br>
								<button value="Eliminar der carrito" name=<%="carrito"+linea.getArticulo().getCodArticulo()%> class="btn btn-danger"></button>
								<br>
							</div>
					 	</div>
					</div>
				</div>
			</div>
			<br><br>				
		<%} %>
		</div>
			
		<%@include file="footer.jsp" %>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>	
	</body>
</html>