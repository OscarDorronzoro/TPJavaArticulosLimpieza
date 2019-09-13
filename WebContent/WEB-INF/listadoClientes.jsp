<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, user-scalable=no">
		<title>Clientes</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">	
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
					        </ul>
			    	</div>
			    	<div class="col-xs-10 col-sm-10 col-md-10">
					    <nav>
					        <ul class="list-unstyled components menu-text">
					            <li>
									<a href="#">Todo</a>
					            </li>
					            <li>
									<a href="#">No Administradores</a>
					            </li>
					            <li>
									<a href="#">Administradores</a>
					            </li>            
					        </ul>
					    </nav>
				    </div>
			    </div>
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-8 bg-articulos">
				<%@page import="entities.Cliente"%>
				<%@page import="java.util.ArrayList"%>
				<%for( Cliente cli : clientes){%>

					<div class="row bg-articulo" id=<%="cliente"+cli.getUsername()%>>														
						<div class="col-xs-6 col-md-3">
							<span>Nombre de Usuario: <%=cli.getUsername()%></span>
						</div>	
						<div class="col-xs-6 col-md-3">
							<span>Nombre: <%=cli.getNombre()%></span>
						</div>
						<div class="col-xs-6 col-md-3">
							<span>Apellido: $<%=cli.getApellido()%></span>
						</div>
						<div class="col-xs-6 col-md-3">
							<input type="checkbox" name="isAdmin" value="Check Value" readonly="readonly" checked onclick="javascript: return false;"/>
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