<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<%
		 	Customer currentUser = (Customer)request.getSession().getAttribute("cliente");
		%>
		<%
			 if (currentUser == null || !currentUser.isAdmin()) {
				response.sendRedirect("../iniciarSesion.jsp?pagina=ModificarClienteServlet");
				return;
			 }
		%>
		<title>Edit Customer</title>
		<link rel="icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
		
		<%
			 Customer customer = (Customer) request.getAttribute("client");
		%>
	</head>
	
	<body>
		<%@include file="../header.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Edit Customer</h1>
						<form action="ModificarClienteServlet" method="post">		
							<div class="form-group">
								<label for="username" class="control-label">Username</label>
								<input class="form-control" value="<%=customer.getUsername() %>" readonly name="username" id="username"/>
							</div>
							<div class="form-group">
								<label for="email" class="control-label">E-Mail</label>
								<input class="form-control" value="<%=customer.getEmail() %>" name="email" id="email"/>
							</div>		
							<div class="form-group">
								<label for="nombre" class="control-label">Name</label>
								<input class="form-control" value="<%=customer.getNombre() %>" name="nombre" id="nombre"/>
							</div>
							<div class="form-group">
								<label for="apellido" class="control-label">Last name</label>
								<input class="form-control" value="<%=customer.getApellido() %>" name="apellido" id="apellido"/>
							</div>
							<div class="form-group">
								<label for="DNI" class="control-label">DNI</label>
								<input class="form-control" value="<%=customer.getDNI() %>" name="DNI" id="DNI"/>
							</div>
							<div class="form-group">
								<label for="isAdmin" class="control-label">Is administrator? </label>
								<input class="form-control" <%if(customer.isAdmin()){ %> checked <% } %> name="isAdmin" id="isAdmin" type="checkbox"/>
							</div>	
							<div class="form-group">
								<input type="submit" class="btn btn-success btn-block"  title="Click to save customer" value="Save Customer"/>
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