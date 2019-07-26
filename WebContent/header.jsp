<nav role="navigation" class="navbar navbar-default navbar-fixed-top bg-blue">
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
						<li><a href="main.jsp" class="navbar-brand">Doña Mary Limpieza</a></li> <!--class="navbar-text" agranda barra-->
						 <li>
							<form class="form-inline navbar-brand" action="BusquedaServlet" method="get">      
								<input class="form-control mr-sm-2" type="search" name="desc" placeholder="Search" aria-label="Search">
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
						
						<%@page import="javax.servlet.http.HttpServletRequest"%>
						<%@page import="javax.servlet.http.HttpSession"%>
						<%session=request.getSession(true);%>
						<%if(session.getAttribute("cliente")==null){ %>
						<li><a href="formCliente.jsp">Registrarse</a></li>
						<li><a href="iniciarSesion.jsp">Iniciar Sesion</a></li>
						<%}else{ %>
						<li><a href="MisCarritos.jsp">Mi Carrito</a></li>
						<li class="dropdown">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#"><img src="png/cog-2x.png"></a>
							<ul role="menu" class="dropdown-menu">
								<li><a href="#" onclick='logout()'>Cerrar Sesion</a></li>
							</ul>
						</li>
						<%} %>						
					</ul>
				</div>
			</div>
		</nav>
		<br><br><br><br>
		<script>
		function logout(){
		<%//equest.getSession().invalidate();
		  //response.sendRedirect("main.jsp");
		%>	
		}
		</script>