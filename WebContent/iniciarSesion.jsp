<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
	</head>
	<body class="bg-primary">
		<nav role="navigation" class="navbar navbar-default navbar-fixed-top navbar-inverse">
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
						<li><a href="main.jsp" class="navbar-brand">Do�a Mary Limpieza</a></li>
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
								<li><a href="#">Ba�o</a></li>
								<li><a href="#">Cocina</a></li>
							</ul>
						</li> -->
						<li><a href="listadoArticulos.jsp">Articulos</a></li>
						<li><a href="MisCarritos.jsp">Mi Carrito</a></li>
						<li><a href="formCliente.jsp">Registrarse</a></li>
						<li class="active"><a href="iniciarSesion.jsp">Iniciar Sesion</a></li>
					</ul>
				</div>
			</div>
		</nav>
		
		<br><br><br><br>
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Iniciar Sesion</h1>
						<form action="LogInServlet" method="get">		
							<div class="form-group">
								<label>Usuario</label>
								<input class="form-control" name="username"/>
							</div>
							<div class="form-group">
								<label>Contrase�a</label>
								<input class="form-control" name="password" type="password"/>
							</div>		
							<input class="btn btn-success btn-block"  title="Presione para iniciar sesion" value="OK" type="submit"/>
						</form>
					</section>
				</div>
			</div>
		</div>
		
<body>

</body>
</html>