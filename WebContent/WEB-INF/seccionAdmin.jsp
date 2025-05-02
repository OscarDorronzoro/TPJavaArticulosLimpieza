<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<% Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente"); %>
		<% if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp?pagina=SeccionAdminServlet");
			return;
		} %>
		<title>Site management</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
		
	</head>
	
	<body class="bg-light">
		
		<%@include file="../header.jsp" %>
		
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 bg-sidebar">
			    <div class="row">
			    	<div class="col-xs-12 col-sm-12 col-md-12">
			    		 <div class="sidebar-header">
					         <h3>Management</h3>
					     </div>		
			    	</div>
			    	<div class="col-xs-1 col-sm-1 col-md-1">
			    		<ul class="list-unstyled components menu-icon">
					            <li>
									<img src="png/limpieza/cocina.png">
					            </li>
					            <li>
									<img src="png/limpieza/banio.png">
					            </li>
					            <li>
									<img src="png/proveedores.png">
					            </li>
					            <li>
									<img src="png/limpieza/muebles.png">
					            </li>
					            <li>
									<img src="png/limpieza/categorias.png">
					            </li>
					            <li>
									<img src="png/limpieza/categorias.png">
					            </li>
					        </ul>
			    	</div>
			    	<div class="col-xs-10 col-sm-10 col-md-10">
					    <nav>
					        <ul class="list-unstyled components menu-text">
					            <li>
									<a href="ListadoArticulosEdicionServlet">List of articles</a>
					            </li>
					            <li>
									<a href="ListadoClientesServlet/todo">List of customers</a>
					            </li>
					            <li>
									<a href="ListadoProveedoresServlet">List of providers</a>
					            </li>
					            <li>
									<a href="RegistrarPagoServlet/IniciarRegistro">Register payment</a>
					            </li>
					            <li>
									<a href="ListadoCategoriasServlet">List of categories</a>
					            </li>
					            <li>
									<a href="ListCompletedSalesServlet/all">List of completed sales</a>
					            </li>
					        </ul>
					    </nav>
				    </div>
			    </div>
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-8 bg-articulos">
			
			<!-- main section -->
			
			</div>
			
		</div>
		
		<%@include file="../footer.jsp"%>
		
		<script src="custom/custom-scripts.js"></script>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>