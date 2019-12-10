<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="ISO-8859-1">
		<%Cliente c = (Cliente)request.getSession().getAttribute("cliente"); %>
		<%if(!(c!=null && c.isAdmin())){
		response.sendRedirect("../iniciarSesion.jsp");
		return;
		}%>
		<title>Clientes</title>
		<link rel="icon" href="../png/favicon.ico">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">	
		<%
			@SuppressWarnings("unchecked")	
			ArrayList<Cliente> clientes=(ArrayList<Cliente>)request.getAttribute("clientes");

		%>
	</head>
	<body class="bg-light">
	
		<%@include file="../header.jsp" %>
		

		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 bg-sidebar">
			    <div class="row">
			    	<div class="col-xs-12 col-sm-12 col-md-12">
			    		 <div class="sidebar-header">
					         <h3>Tipos de clientes</h3>
					     </div>		
			    	</div>
			    	<div class="col-xs-1 col-sm-1 col-md-1">
			    		<ul class="list-unstyled components menu-icon">
					            <li>
									<img src="../png/limpieza/todo.png">
					            </li>
					            <li>
									<img src="../png/limpieza/cocina.png">
					            </li>
					            <li>
									<img src="../png/limpieza/banio.png">
					            </li>         
					        </ul>
			    	</div>
			    	<div class="col-xs-10 col-sm-10 col-md-10">
					    <nav>
					        <ul class="list-unstyled components menu-text">
					            <li>
									<a href="ListadoClienteServlet/todo">Todo</a>
					            </li>
					            <li>
									<a href="ListadoClienteServlet/admin">No Administradores</a>
					            </li>
					            <li>
									<a href="ListadoClienteServlet/noadmin">Administradores</a>
					            </li>            
					        </ul>
					    </nav>
				    </div>
			    </div>
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-8">
				<table class="table table-striped table-hover">
					<%@page import="java.util.ArrayList"%>
					
					<thead>
						<tr>
							<td>Nombre de Usuario</td>
							<td>Nombre</td>
							<td>Apellido</td>
							<td>DNI</td>
							<td>Es Administrador</td>
						</tr>
					</thead>
					
					<tbody>
					<%for( Cliente cli : clientes){%>
												
						<tr <%if(cli.isAdmin()){%>class="warning"<%} %>>	
							<td><%=cli.getUsername()%></td>
							<td><%=cli.getNombre()%></td>
							<td><%=cli.getApellido()%></td>
							<td><%=cli.getDNI()%></td>
							<td><input type="checkbox" name="isAdmin" value="Check Value" readonly="readonly" 
								<%if(cli.isAdmin()){%>checked<%} %> onclick="javascript: return false;"/>
							</td>
							<td><a class="btn btn-primary" href="ModificarClienteServlet">Modificar</a></td>
							<td><a class="btn btn-danger" onclick="confirmarEIrA('EliminarClienteServlet')">Eliminar</a></td>
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