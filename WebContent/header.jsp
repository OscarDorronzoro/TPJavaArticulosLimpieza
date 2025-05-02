<div id="header" class="bg-blue container-fluid">
<nav role="navigation" class="navbar navbar-default navbar-fixed-top">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-md-7">		
				<div class="navbar-header">
					<div class="row nav navbar-nav"> <!-- navbar-text -->
						<div class="col-xs-2 col-sm-1"><a href="/TP_Articulos_Limpieza/main.jsp" class="navbar-brand m-5"><img height="30px" src="/TP_Articulos_Limpieza/png/favicon.ico"></a></div>
						<div class="col-xs-10 col-sm-5"><a href="/TP_Articulos_Limpieza/main.jsp" class="navbar-brand">Mrs. Mary Cleaning</a></div>
						 <div class="col-xs-12 col-sm-6">
							<form class="form-inline navbar-brand" action="BusquedaServlet" method="get">
								<div class="row">
									<div class="col-xs-8">
										<input class="form-control " type="search" name="descToSearch" placeholder="search article" aria-label="Search">
									</div>
									<div class="col-xs-4">
										<button class="btn btn-success " type="submit">Search</button>
									</div>
								</div>						
							</form>
						</div> 
					</div>
				</div>
		</div>	
			
		<div class="col-xs-12 col-sm-4 col-md-5">
					<div id="navbarCollapse" class="navbar-collapse navbar-right">
						<ul class="nav navbar-nav seccion">
							<li ><a href="/TP_Articulos_Limpieza/main.jsp">Home</a></li>
							<li><a href="/TP_Articulos_Limpieza/BusquedaServlet">Articles</a></li>														
							
							
							<%@page import="entities.Customer" %>
							<%
								Customer currentCustomer = (Customer) request.getSession().getAttribute("cliente");
							%>
							<% if (currentCustomer == null) { %>
								<li><a href="/TP_Articulos_Limpieza/formCliente.jsp">Sign up</a></li>
								<li><a href="/TP_Articulos_Limpieza/iniciarSesion.jsp">Log in</a></li>
							<% } else {
								if (currentCustomer.isAdmin()) { %>
									<li><a href="/TP_Articulos_Limpieza/SeccionAdminServlet">Site Management</a> </li>
							<% } %>		
							<li>
								<a href="/TP_Articulos_Limpieza/CarritoServlet/currentPurchase">My Cart <% if(currentCustomer.getMiCarrito().getLineas()!=null) { %> <%=currentCustomer.getMiCarrito().getLineas().size()%> <% } %> </a>  
							<li class="dropdown">
								<a data-toggle="dropdown" class="dropdown-toggle" href="#"><img src="/TP_Articulos_Limpieza/png/cog-2x.png"></a>
								<ul role="menu" class="dropdown-menu">
									<li><a href="/TP_Articulos_Limpieza/LogOutServlet">Log out</a></li>
									<li><a href="/TP_Articulos_Limpieza/EditarPerfilServlet">Edit profile</a></li>
								</ul>
							</li>
							<%} %>						
						</ul>
					</div>
				</div>
			</div>
	</nav>
</div>