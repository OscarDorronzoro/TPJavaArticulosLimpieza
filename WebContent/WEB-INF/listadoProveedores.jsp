<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%Cliente c = (Cliente)request.getSession().getAttribute("cliente"); %>
		<%if(!(c!=null && c.isAdmin())){
		response.sendRedirect("iniciarSesion.jsp?pagina=ListadoProveedoresServlet");
		return;
		}%>
		<title>Listado de proveedores</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		
		<% 
			@SuppressWarnings("unchecked")	
			ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>) request.getAttribute("proveedores"); 
		%>
	</head>
	<body>
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
				<%@page import="entities.Proveedor"%>
				<%@page import="java.util.ArrayList"%>
				
				<table class="table table-striped table-hover">					
					<thead>
						<tr>
						    <td>Razón social</td>
							<td>Cuit</td>
							<td>Dirección</td>
							<td>Teléfono</td>
							<td>Mail</td>
						</tr>
					</thead>
					
					<tbody>
					<%for( Proveedor prov : proveedores){%>
												
						<tr>	
							<td><%=prov.getRazonSocial() %></td>
							<td><%=prov.getCuit()%></td>
							<td><%=prov.getDireccion()%></td>
							<td><%=prov.getTelefono() %></td>
							<td><%=prov.getMail()==null?"-":prov.getMail() %></td>
							
							<td><a class="btn btn-primary" href="ModificarProveedorServlet?cuit=<%=prov.getCuit()%>">Modificar</a></td>
							<td><a class="btn btn-danger" onclick="confirmarEIrA('EliminarProveedorServlet?cuit='+'<%=prov.getCuit()%>')">Eliminar</a></td>
						</tr>							
								
					<%} %>
					</tbody>
				</table>
				
			</div>
		</div>
		
		
		<%@include file="../footer.jsp" %>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
		<script src="bootstrap/js/miJavaScript.js"></script>	
	</body>
</html>