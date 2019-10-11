<div id="header" class="bg-blue">
<nav role="navigation" class="navbar navbar-default navbar-fixed-top">
	<div class="row">
		<div class="col-xm-12 col-md-5">		
<!-- 			<div class="container"> -->
				<div class="navbar-header">
					<div class="row">
<!-- 					<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle"> -->
<!-- 						<span class="sr-only">menu hamburguesa</span> -->
<!-- 						<span class="icon-bar"></span> cada una de estas lineas hacen una linea del menu de hamburguesa -->
<!-- 						<span class="icon-bar"></span> -->
<!-- 						<span class="icon-bar"></span> -->
<!-- 					</button> -->
					<ul class="nav navbar-nav">
						<li><a href="main.jsp" class="navbar-brand m-5"><img src="png/home-4x.png"></a></li>
						<li><a href="main.jsp" class="navbar-brand">Doña Mary Limpieza</a></li> <!-- class="navbar-text" agranda barra -->
						 <li>
							<form class="form-inline navbar-brand" action="BusquedaServlet" method="get">      
								<input class="form-control mr-sm-2" type="search" name="descBusqueda" placeholder="Buscar un articulo" aria-label="Search">
								<button class="btn btn-success my-2 my-sm-0" type="submit">Buscar</button>
							</form>
						</li> 
					</ul>
					</div>
				</div>
<!-- 			</div> -->
		</div>	
			
		<div class="col-xs-12 col-md-6">
					<div id="navbarCollapse" class="navbar-collapse navbar-right">
						<ul class="nav navbar-nav seccion">
							<li ><a href="main.jsp">Inicio</a></li>
							<!-- <li class="dropdown">
								<a data-toggle="dropdown" class="dropdown-toggle" href="listadoArticulos.jsp">Categorias Articulos
									<b class="caret"></b> 
								</a>
								<ul role="menu" class="dropdown-menu">
									<li><a href="#">Baño</a></li>
									<li><a href="#">Cocina</a></li>
								</ul>
							</li> -->
							<li><a href="BusquedaServlet">Articulos</a></li>														
							
							
							<%@page import="entities.Cliente" %>
							<%Cliente cliente = (Cliente)request.getSession().getAttribute("cliente"); %>
							<%if(cliente==null){ %>
							<li><a href="formCliente.jsp">Registrarse</a></li>
							<li><a href="iniciarSesion.jsp">Iniciar Sesion</a></li>
							<%}else{
							if(cliente.isAdmin()){%>
								<li><a href="SeccionAdminServlet">Gestion del Sitio</a> </li>
							<%}%>		
							<li><a href="MisCarritos.jsp">Mi Carrito</a></li>
							<li class="dropdown">
								<a data-toggle="dropdown" class="dropdown-toggle" href="#"><img src="png/cog-2x.png"></a>
								<ul role="menu" class="dropdown-menu">
									<li><a href="LogOutServlet">Cerrar Sesion</a></li>
								</ul>
							</li>
							<%} %>						
						</ul>
					</div>
				</div>
			</div>
	</nav>
</div>