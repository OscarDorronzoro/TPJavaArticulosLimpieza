<div id="header" class="bg-blue container-fluid">
<nav role="navigation" class="navbar navbar-default navbar-fixed-top">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-md-7">		
<!-- 			<div class="container"> -->
				<div class="navbar-header">
					<div class="row nav navbar-nav">
<!-- 					<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle"> -->
<!-- 						<span class="sr-only">menu hamburguesa</span> -->
<!-- 						<span class="icon-bar"></span> cada una de estas lineas hacen una linea del menu de hamburguesa -->
<!-- 						<span class="icon-bar"></span> -->
<!-- 						<span class="icon-bar"></span> -->
<!-- 					</button> -->
<!-- 					<div class="nav navbar-nav"> -->
						<div class="col-xs-2 col-sm-1"><a href="main.jsp" class="navbar-brand m-5"><img height="50px" src="png/favicon.ico"></a></div>
						<div class="col-xs-10 col-sm-5"><a href="../main.jsp" class="navbar-brand">Doña Mary Limpieza</a></div> <!-- class="navbar-text" agranda barra -->
						 <div class="col-xs-12 col-sm-6">
							<form class="form-inline navbar-brand" action="BusquedaServlet" method="get">      
								<div class="row">
									<div class="col-xs-8">
										<input class="form-control " type="search" name="descBusqueda" placeholder="Buscar un articulo" aria-label="Search">
									</div>
									<div class="col-xs-4">
										<button class="btn btn-success " type="submit">Buscar</button>
									</div>
								</div>						
							</form>
						</div> 
<!-- 					</div> -->
					</div>
				</div>
<!-- 			</div> -->
		</div>	
			
		<div class="col-xs-12 col-sm-4 col-md-5">
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
							<li><a href="iniciarSesion.jsp">Iniciar Sesión</a></li>
							<%}else{
							if(cliente.isAdmin()){%>
								<li><a href="SeccionAdminServlet">Gestión del Sitio</a> </li>
							<%}%>		
							<li>
								<a href="MisCarritos.jsp">Mi Carrito <% if(cliente.getMiCarrito().getLineas()!=null) { %> <%=cliente.getMiCarrito().getLineas().size()%> <% } %> </a>  
							<li class="dropdown">
								<a data-toggle="dropdown" class="dropdown-toggle" href="#"><img src="png/cog-2x.png"></a>
								<ul role="menu" class="dropdown-menu">
									<li><a href="LogOutServlet">Cerrar Sesion</a></li>
									<li><a href="EditarPerfilServlet/iniciarModificacion">Editar mi perfil</a></li>
								</ul>
							</li>
							<%} %>						
						</ul>
					</div>
				</div>
			</div>
	</nav>
</div>