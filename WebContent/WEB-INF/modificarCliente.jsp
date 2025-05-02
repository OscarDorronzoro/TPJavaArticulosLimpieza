<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<% Cliente currentUser = (Cliente)request.getSession().getAttribute("cliente"); %>
		<% if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("../iniciarSesion.jsp?pagina=ModificarClienteServlet");
			return;
		} %>
		<title>Modificar Cliente</title>
		<link rel="icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
		
		<% Cliente client = (Cliente) request.getAttribute("client"); %>
	</head>
	
	<body>
		<%@include file="../header.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Modificar Cliente</h1>
						<form action="ModificarClienteServlet" method="post">		
							<div class="form-group">
								<label for="username" class="control-label">Usuario</label>
								<input class="form-control" value="<%=client.getUsername() %>" readonly name="username" id="username"/>
							</div>
							<div class="form-group">
								<label for="email" class="control-label">E-Mail</label>
								<input class="form-control" value="<%=client.getEmail() %>" name="email" id="email"/>
							</div>		
							<div class="form-group">
								<label for="nombre" class="control-label">Nombre</label>
								<input class="form-control" value="<%=client.getNombre() %>" name="nombre" id="nombre"/>
							</div>
							<div class="form-group">
								<label for="apellido" class="control-label">Apellido</label>
								<input class="form-control" value="<%=client.getApellido() %>" name="apellido" id="apellido"/>
							</div>
							<div class="form-group">
								<label for="DNI" class="control-label">DNI</label>
								<input class="form-control" value="<%=client.getDNI() %>" name="DNI" id="DNI"/>
							</div>
							<div class="form-group">
								<label for="isAdmin" class="control-label">Es administrador </label>
								<input class="form-control" <%if(client.isAdmin()){ %> checked <% } %> name="isAdmin" id="isAdmin" type="checkbox"/>
							</div>	
							<div class="form-group">
								<input type="submit" class="btn btn-success btn-block"  title="Presione para modificar cliente" value="OK"/>
							</div>
						</form>
					</section>
				</div>
			</div>
		</div>
			
		<%@include file="../footer.jsp" %>
	
		<script src="custom/custom-scripts.js"></script>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>