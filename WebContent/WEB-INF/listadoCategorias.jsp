<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<%
			Customer currentUser = (Customer) request.getSession().getAttribute("cliente");
		%>
		<%
			if (currentUser == null || !currentUser.isAdmin()) {
				response.sendRedirect("iniciarSesion.jsp?pagina=ListadoCategoriasServlet");
				return;
			}
		%>
		<title>List of categories</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
		
		<%@ page import="java.net.URLEncoder" %>
		<% 
			@SuppressWarnings("unchecked")	
			ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categorias");
		%>
	</head>
	<body>
		<%@include file="../header.jsp" %>
		
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 bg-sidebar">
			    <div class="row">
			    	<div class="col-xs-12 col-sm-12 col-md-12">
			    		 <div class="sidebar-header">
					         <h3>Articles' categories</h3>
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
									<a href="#">All</a>
					            </li>
					            <li>
									<a href="#">Kitchen</a>
					            </li>
					            <li>
									<a href="#">Bathroom</a>
					            </li>
					            <li>
									<a href="#">Furniture</a>
					            </li>	            
					        </ul>
					    </nav>
				    </div>
			    </div>
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-8">
				<%@page import="entities.Category"%>
				<%@page import="java.util.ArrayList"%>
				
				<div>
					<a href="CargaCategoriaServlet" class="btn btn-success"><img src="png/plus-2x.png"> New category</a>
				</div>
				
				<table class="table table-striped table-hover">					
					<thead>
						<tr>
						    <td>Name</td>
							<td>Description</td>
						</tr>
					</thead>
					
					<tbody>
					<%
						for( Category cat : categories) {
					%>
												
						<tr>	
							<td><%=cat.getNombre() %></td>
							<td><%=cat.getDescripcion()%></td>
							
							<td><a class="btn btn-primary" href="ModificarCategoriaServlet?name=<%=URLEncoder.encode(cat.getNombre(), "ISO-8859-1") %>">Modificar</a></td>
							<td><a class="btn btn-danger" onclick="confirmarEIrA('EliminarCategoriaServlet?name='+'<%=URLEncoder.encode(cat.getNombre(), "ISO-8859-1") %>')">Eliminar</a></td>
						</tr>							
								
					<%} %>
					</tbody>
				</table>
				
			</div>
		</div>
		
		<%@include file="../footer.jsp" %>
		
		<script src="custom/custom-scripts.js"></script>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>