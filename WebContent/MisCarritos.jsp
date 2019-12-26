
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<%if(request.getSession().getAttribute("cliente")==null){
		response.sendRedirect("iniciarSesion.jsp?pagina=MisCarritos.jsp");
		return;
	}%>
	
	<title>Mi Carrito</title>
	<link rel="shortcut icon" href="png/favicon.ico">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
	</head>
	<body class="bg-light">
		<%@include file="header.jsp" %>	
		
		
	<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 bg-sidebar">
			    <div class="row">
			    	<div class="col-xs-12 col-sm-12 col-md-12">
			    		 <div class="sidebar-header">
					         <h3>Mis carritos</h3>
					     </div>		
			    	</div>
			    	<div class="col-xs-1 col-sm-1 col-md-1">
			    		<ul class="list-unstyled components menu-icon">
					            <li>
									<img src="png/limpieza/todo.png">
					            </li>
					            <li>
									<img src="png/limpieza/cocina.png">
					            </li>
					            <li>
									<img src="png/limpieza/banio.png">
					            </li>
					            <li>
									<img src="png/limpieza/muebles.png">
					            </li>            
					        </ul>
			    	</div>
			    	<div class="col-xs-10 col-sm-10 col-md-10">
					    <nav>
					        <ul class="list-unstyled components menu-text">
					            <li>
									<a href="#">Compra Actual</a>
					            </li>
					            <li>
									<a href="#">Favoritos</a>
					            </li>
					            <li>
									<a href="#">Lista de deseos</a>
					            </li>
					            <li>
									<a href="#">Presupuesto</a>
					            </li>	            
					        </ul>
					    </nav>
				    </div>
			    </div>
		</div>
		
		<div class="col-xs-12 col-md-8">
		<%@page import="entities.Articulo"%>
		<%@page import="entities.Linea"%>
		<%@page import="java.util.ArrayList"%>
		<%@page import="logic.ABMCLineaCarrito"%>	
		<%!ArrayList<Linea> lineas;%>
		<%!double total=0; %>
		<%
			total=0;
			Cliente cli=(Cliente)request.getSession().getAttribute("cliente");
			lineas = cli.getMiCarrito().getLineas();
		%>
		<%
			for( Linea linea : lineas){ 
				total+=linea.getArticulo().getPrecio().getValor()*linea.getCantidad();
		%>
			<div class="row bg-articulo">
				<div class="col-md-2"><img class="imagen-articulo" src=<%=linea.getArticulo().getUrlImagen()%>></div>
				<div class="col-md-7">
					<div class="row">
						<div class="col-md-8">
							<ul class="list-unstyled">							
								<li>Descripción: <%=linea.getArticulo().getDescripcion()%></li>
								<li>Precio unitario: $<%=linea.getArticulo().getPrecio().getValor()%></li>
								<li>Subtotal: $<%=linea.getArticulo().getPrecio().getValor()*linea.getCantidad()%></li>
							</ul>
						</div>
						<div class="col-md-4">
							<div class="row">
								<a href=<%="EliminarDeCarritoServlet?codArticulo="+linea.getArticulo().getCodArticulo()%> class="btn btn-danger">Eliminar del carrito</a><br/>
								<form action="ModificarCarritoServlet/"<%=linea.getArticulo().getCodArticulo()%> method="get">
									
									<div class="form-group">
										<input name="codArticulo" type="hidden" value="<%=linea.getArticulo().getCodArticulo()%>">
										<label class="label-control">Cantidad:</label>
										<input name="cantidad" value="<%=linea.getCantidad() %>"  class="form-control" maxlength="3" pattern="[1-9][0-9]*" size=2px>
									</div>
									<div class="form-group">
										<input type="submit" value="Modificar" name="btnmodificar" class="btn btn-primary btn-block">
									</div>
									
								</form>								
								
							</div>
					 	</div>
					</div>
				</div>
			</div>
			<br><br>				
		<%} 
		if(!lineas.isEmpty()){
		%>
		<div class="row">
			<div class="col-xs-10"></div>
			<div class="col-xs-2">
				<span>Total: <%=total %></span>
				<a href="ComprarServlet" class="btn btn-success">Comprar ahora</a>
			</div>	
		</div>
		<%} else{ %>
			<span>Vaya, esto esta vacio, ve a comprar articulos</span>
		<%} %>
		
		</div>
	</div>
			
		<%@include file="footer.jsp" %>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>	
	</body>
</html>