<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Articulos</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">	
	</head>
	<body>
		<%@include file="header.jsp" %>
		
		<div>
			<%@page import="entities.Articulo"%>
			<%@page import="java.util.ArrayList"%>
			<%@page import="logic.ABMCArticulo"%>		
			<%! ArrayList<Articulo> articulos= new ABMCArticulo().getAll();%>
			<%for( Articulo art : articulos){%>
				<div class="row">
					<div class="col-md-2"><img height=150 src=<%=art.getUrlImagen()%>></div>
					<div class="col-md-10">
						<div class="row">
							<div class="col-md-1"><%=art.getCodArticulo()%></div>
							<div class="col-md-8">
								<ul class="list-unstyled">							
									<li>Descripción: <%=art.getDescripcion()%></li>
									<li>Stock: <%=art.getStock()%></li>
									<li>Precio: <%=art.getPrecio()%></li>
								</ul>
							</div>
							<div class="col-md-3">
								<div class="row">
									<form action="comprarServlet" method="get">
										<label>Cantidad:</label>
										<input name="cantidad" size=2px> <br><br>
										<input type="submit" value="Comprar" class="btn btn-success"><br>
									</form>
									<br>
									<form action="aniadirCarritoServlet" method="get">
										<input type="submit" value="Añadir al carrito" class="btn btn-success">
									</form>
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
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>