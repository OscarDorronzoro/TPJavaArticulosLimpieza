<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<%Cliente c = (Cliente)request.getSession().getAttribute("cliente"); %>
		<%if(!(c!=null && c.isAdmin())){
		response.sendRedirect("iniciarSesion.jsp");
		return;
		}%>
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
			
			
			
			<div class="col-xs-12 col-sm-12 col-md-8">
				<table class="table table-striped table-hover">
					<%@page import="entities.Articulo"%>
					<%@page import="java.util.ArrayList"%>
					
					<thead>
						<tr>
							<td>Codigo</td>
							<td>Descripcion</td>
							<td>Stock</td>
							<td>Precio</td>
							<td>Cant. a Pedir</td>
							<td>Punto de Pedido</td>
							<td>URL Imagen</td>
						</tr>
					</thead>
					
					<tbody>
					<%for( Articulo art : articulos){%>
												
						<tr>	
							<td><%=art.getCodArticulo()%></td>
							<td><%=art.getDescripcion()%></td>
							<td><%=art.getStock()%></td>
							<td><%=art.getPrecio().getValor()%></td>
							<td><%=art.getCantAPedir()%></td>
							<td><%=art.getPuntoPedido()%></td>
							<td><%=art.getUrlImagen()%></td>
							
							<td><a class="btn btn-primary" href="ModificarArticuloServlet/<%=art.getCodArticulo()%>">Modificar</a></td>
							<td><a class="btn btn-danger" onclick="confirmarEIrA('EliminarArticuloServlet/'+'<%=art.getCodArticulo()%>')">Eliminar</a></td>
						</tr>							
								
					<%} %>
					</tbody>
				</table>
			</div>
		</div>	
			
		<%@include file="../footer.jsp"%>
		<script src="../bootstrap/js/jquery-3.4.1.js"></script>
		<script src="../bootstrap/js/popper.js"></script>
		<script src="../bootstrap/js/bootstrap.js"></script>
		<script src="../bootstrap/js/miJavaScript.js"></script>	
	</body>
</html>