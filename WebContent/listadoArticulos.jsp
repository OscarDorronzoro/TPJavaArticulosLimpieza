<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Articulos</title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<style>#imagenArt{
			min-height: 400px;
			background-image: url('img-articulos/escoba-paja.jpg');
			background-size: cover;
			background-position: center;
			padding-bottom: 200px;
			}
		</style>	
	</head>
	<body class="bg-primary">
		
				<nav role="navigation" class="navbar navbar-default navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
						<span class="sr-only">menu hamburguesa</span>
						<span class="icon-bar"></span> <!-- cada una de estas lineas hacen una linea del menu de hamburguesa -->
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<ul class="nav navbar-nav">
						<li><a href="main.jsp" class="navbar-brand m-5"><img src="png/home-4x.png"></a></li>
						<li><a href="main.jsp" class="navbar-brand">Doña Mary Limpieza</a></li>
						 <li>
							<form class="form-inline navbar-brand">      
								<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
								<button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
							</form>
						</li> 
					</ul>
				</div>
				
				<div id="navbarCollapse" class="navbar-collapse navbar-right">
					<ul class="nav navbar-nav">
						<li class=""><a href="main.jsp">Inicio</a></li>
						<!-- <li class="dropdown">
							<a data-toggle="dropdown" class="dropdown-toggle" href="listadoArticulos.jsp">Categorias Articulos
								<b class="caret"></b> 
							</a>
							<ul role="menu" class="dropdown-menu">
								<li><a href="#">Baño</a></li>
								<li><a href="#">Cocina</a></li>
							</ul>
						</li> -->
						<li><a href="listadoArticulos.jsp">Articulos</a></li>
						<li><a href="formCliente.jsp">Registrarse</a></li>
						<li><a href="MisCarritos.jsp">Mi Carrito</a></li>
					</ul>
				</div>
			</div>
		</nav>
		
		<body>
		<%@page import="entities.Articulo"%>
		<%@page import="java.util.ArrayList"%>
		<%@page import="logic.ABMCArticulo"%>		
		<%! ArrayList<Articulo> articulos= new ABMCArticulo().getAll();%>
		<%for( Articulo art : articulos){%>
			<div class="row">
				<div id="imagenArt" class=col-md-3>				
									
				</div>
				<div class="col-md-9">
					<ul>
						<li><%=art.getCodArticulo()%></li>
						<li><%=art.getDescripcion() %></li>
					</ul>
				</div>
			</div>				
		<%} %>
		</body>
		
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