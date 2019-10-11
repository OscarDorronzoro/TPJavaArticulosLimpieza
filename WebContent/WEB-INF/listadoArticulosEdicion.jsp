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
		<%
			@SuppressWarnings("unchecked")	
			ArrayList<Articulo> articulos=(ArrayList<Articulo>)request.getAttribute("articulos");

		%>
	</head>
	<body class="bg-light">
	
		<%@include file="../header.jsp" %>
		

		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 bg-sidebar">
			    <div class="row">
			    	<div class="col-xs-12 col-sm-12 col-md-12">
			    		 <div class="sidebar-header">
					         <h3>Categorias de Articulos</h3>
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
			    </div>
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-8 bg-articulos">
				<%@page import="entities.Articulo"%>
				<%@page import="java.util.ArrayList"%>
				<%for( Articulo art : articulos){%>

					<div class="row bg-articulo" id=<%="articulo"+art.getCodArticulo() %> onclick='javascript: detalleArticulo("<%=art.getCodArticulo() %>")' onmouseover='javascript: ponerMano("<%=art.getCodArticulo() %>")' onmouseout='javascript: ponerFlechita("<%=art.getCodArticulo() %>")'> 
						<div class="col-xs-3 col-md-3"><img class="imagen-articulo" src=<%=art.getUrlImagen()%>></div>
						<div class="col-xs-9 col-md-9">
							<div class="row">
								<div class="col-xs-6 col-md-8">
									<ul class="list-unstyled">							
										<li class="atributo-link">Descripción: <%=art.getDescripcion()%></li>
										<li>Stock: <%=art.getStock()%></li>
										<li>Precio: $<%=art.getPrecio().getValor()%></li>
									</ul>
								</div>
								<div class="col-xs-6 col-md-4">
									<div class="row">
										<a href="ModificarArticuloServlet/<%=art.getCodArticulo() %>" class="btn-success">Modificar</a>
										<br><br>
										<a href="EliminarArticuloServlet/<%=art.getCodArticulo() %>" class="btn-danger">Eliminar</a>
										<br>
									</div>
								</div>
							</div>
						</div>
					</div>
									
				<%} %>
			</div>
		</div>
		<%@include file="../footer.jsp"%>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
		<script src="bootstrap/js/miJavaScript.js"></script>	
	</body>
</html>