<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, user-scalable=no">
		<title>Articulos</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">	
		
	</head>
	<body class="bg-light">
	
		<%@include file="header.jsp" %>
		
<!-- 		<div class="row bg-sidebar"> -->
<!-- 			<div class="col-md-3"> -->
<!-- 				<ul class="list-unstyled"> -->
<!-- 					<li>opcion 1</li> -->
<!-- 					https://bootstrapious.com/p/bootstrap-sidebar -->
<!-- 					<li>opcion 2</li> -->
<!-- 				</ul> -->
<!-- 			</div>					 -->

		<div class="row">
			<div class="col-sm-0 col-md-3 bg-sidebar">
			    <!-- Sidebar  -->
			    <nav>
			        <div class="sidebar-header">
			            <h3>Categorias de Articulos</h3>
			        </div>		
			        <ul class="list-unstyled components">
			            <li>
							<a href="#">Todo</a>
			            </li>
			            <li>
							<a href="#">Cocina</a>
			            </li>
			            <li>
							<a href="#">Baño</a>
			            </li>
			            <li>
							<a href="#">Muebles</a>
			            </li>	            
			        </ul>
			        
			    </nav>
			</div>
			
			<div id="contenido" class="col-sm-12 col-md-9">
				<%@page import="entities.Articulo"%>
				<%@page import="java.util.ArrayList"%>
				<%@page import="logic.ABMCArticulo"%>		
				<%! ArrayList<Articulo> articulos= new ABMCArticulo().getAll();%>
				<%for( Articulo art : articulos){%>
					<div class="row bg-articulo">
						<div class="col-md-2"><img height=150 src=<%=art.getUrlImagen()%>></div>
						<div class="col-md-10">
							<div class="row">
								<div class="col-md-8">
									<ul class="list-unstyled">							
										<li>Descripción: <%=art.getDescripcion()%></li>
										<li>Stock: <%=art.getStock()%></li>
										<li>Precio: <%=art.getPrecio()%></li>
									</ul>
								</div>
								<div class="col-md-4">
									<div class="row">
										<form action="CarritoServlet" method="get">
											<label>Cantidad:</label>
											<input name="cantidad" size=2px> <br><br>
											<input type="submit" value="Comprar" name=<%="comprar"+art.getCodArticulo()%> class="btn btn-success"><br>
											<input type="submit" value="Añadir al carrito" name=<%="carrito"+art.getCodArticulo()%> class="btn btn-success">
										</form>
										<br>
									</div>
								</div>
							</div>
						</div>
					</div>
									
				<%} %>
			</div>
		</div>
		<%@include file="footer.jsp" %>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
		
	</body>
</html>