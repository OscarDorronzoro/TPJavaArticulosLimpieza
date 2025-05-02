<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<%
			 if (request.getSession().getAttribute("cliente") == null) {
				response.sendRedirect("iniciarSesion.jsp?pagina=editarPerfil.jsp");
				return;
			 }
		%>
		<title>Edit Profile</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
		<%
			Customer modifiedCustomer = (Customer) request.getAttribute("cliModificado");
		%>
	</head>
	<body>
		<%@include file="../header.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Edit Profile</h1>
						<form action="EditarPerfilServlet" method="post">		
							<div class="form-group">
								<label for="username" class="control-label">Username</label>
								<input class="form-control" value="<%=currentCustomer.getUsername() %>" readonly name="username" id="username"/>
							</div>	
							<div class="form-group">
								<label for="nombre" class="control-label">Name</label>
								<input class="form-control" value="<%=currentCustomer.getNombre() %>" name="nombre" id="nombre"/>
							</div>
							<div class="form-group">
								<label for="apellido" class="control-label">Last name</label>
								<input class="form-control" value="<%=currentCustomer.getApellido() %>" name="apellido" id="apellido"/>
							</div>
							<div class="form-group">
								<label for="DNI" class="control-label">DNI</label>
								<input class="form-control" value="<%=currentCustomer.getDNI() %>" name="DNI" id="DNI"/>
							</div>	
							<div class="form-group">
								<input type="submit" class="btn btn-success btn-block"  title="Click to edit profile" value="Update Profile"/>
							</div>
						</form>
					</section>
					<% if (modifiedCustomer != null) { %>
						<div>
							<span>Your profile has been updated!</span>
						</div>
					<%} %>
				</div>
			</div>
		</div>
		
		<%@include file="../footer.jsp" %>
		
		<script src="custom/custom-scripts.js"></script>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>