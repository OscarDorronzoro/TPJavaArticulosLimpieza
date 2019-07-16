<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Articulos</title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<style>#imagenArt{
			min-height: 4px;
			min-weight: 4px;
			height: 200px;
			weight: 200px;
			background-image: url('img-articulos/escoba-paja.jpg');
			background-size: cover;
			background-position: center;
			padding-top: 100px;
			}
		</style>
		<style>#espacio{
		padding-top:20px;
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
						<li><a href="main.jsp">Inicio</a></li>
						<!-- <li class="dropdown">
							<a data-toggle="dropdown" class="dropdown-toggle" href="listadoArticulos.jsp">Categorias Articulos
								<b class="caret"></b> 
							</a>
							<ul role="menu" class="dropdown-menu">
								<li><a href="#">Baño</a></li>
								<li><a href="#">Cocina</a></li>
							</ul>
						</li> -->
						<li class="active"><a href="listadoArticulos.jsp">Articulos</a></li>
						<li><a href="MisCarritos.jsp">Mi Carrito</a></li>
						<li><a href="formCliente.jsp">Registrarse</a></li>
						<li><a href="iniciarSesion.jsp">Iniciar Sesion</a></li>
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
								<button class="btn btn-success">Comprar</button>
							</div>
							<br>
							<div class="row">
								<button class="btn btn-success">Añadir al carrito</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br><br>				
		<%} %>
		</body>
		
		
<footer class="page-footer font-small blue pt-4">
  <div class="container-fluid text-center text-md-left">
    <div class="row">
      <div class="col-md-6 mt-md-0 mt-3">
        <h5 class="text-uppercase">Acerca de nosotros</h5>
        <p>Somos una PyME dedicada a la venta de articulos de limpieza. chamuyo chamuyo chamuyo chamuyo 
        chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo 
        chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo 
        chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo 
        chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo 
        chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo 
        chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo chamuyo 
        </p>
      </div>
      <hr class="clearfix w-100 d-md-none pb-3">
      <div class="col-md-3 mb-md-0 mb-3">
        <h5 class="text-uppercase">Links</h5>
        <ul class="list-unstyled">
          <li>
            <a href="#!">Link 1</a>
          </li>
          <li>
            <a href="#!">Link 2</a>
          </li>
          <li>
            <a href="#!">Link 3</a>
          </li>
          <li>
            <a href="#!">Link 4</a>
          </li>
        </ul>
      </div>
      <div class="col-md-3 mb-md-0 mb-3">
        <h5 class="text-uppercase">Links</h5>
        <ul class="list-unstyled">
          <li>
            <a href="#!">Link 1</a>
          </li>
          <li>
            <a href="#!">Link 2</a>
          </li>
          <li>
            <a href="#!">Link 3</a>
          </li>
          <li>
            <a href="#!">Link 4</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <div class="footer-copyright text-center py-3"> © 2018 Copyright:
    <a href="https://mdbootstrap.com/education/bootstrap/"> MDBootstrap.com</a>
  </div>
</footer>
		
	
	<script src="bootstrap/js/jquery-3.4.1.js"></script>
	<script src="bootstrap/js/popper.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>