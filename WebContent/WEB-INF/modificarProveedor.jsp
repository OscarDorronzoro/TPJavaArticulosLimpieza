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
				response.sendRedirect("iniciarSesion.jsp?pagina=ModificarProveedorServlet");
				return;
			 }
		%>
		<title>Edit Provider</title>
		<link rel="icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
		
		<%@ page import="entities.Provider" %>
		<%
		Provider provider = (Provider) request.getAttribute("provider");
		%>
	</head>
	<body class=bg-light>
		<%@include file="../header.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Edit Provider</h1>
						<form action="ModificarProveedorServlet" method="post">		
							<div class="form-group">
								<label for="cuit">CUIT</label>
								<input value="<%=provider.getCuit() %>" class="form-control" name="cuit" readonly required id="cuit"/>
							</div>
							<div class="form-group">
								<label for="businessName">Business name</label>
								<input value="<%=provider.getRazonSocial() %>" class="form-control" name="businessName" required id="businessName"/>
							</div>
							<div class="form-group">
								<label for="address">Address</label>
								<input value="<%=provider.getDireccion() %>" class="form-control" name="address" required id="address"/>
							</div>
							<div class="form-group">
								<label for="phoneNumber">Phone number</label>
								<input value="<%=provider.getTelefono() %>" class="form-control" name="phoneNumber" required id="phoneNumber"/>
							</div>
							<div class="form-group">
								<label for="mail">E-Mail</label>
								<input value="<%=provider.getMail() %>" class="form-control" name="mail" required id="mail"/>
							</div>
							<input type="submit" class="btn btn-success btn-block"  title="Click to save provider" value="Save Provider"/>
						</form>
					</section>
				</div>
			</div>
		</div>
		
		<%@include file="../footer.jsp" %>
		
		<script src="../bootstrap/js/jquery-3.4.1.js"></script>
		<script src="../bootstrap/js/bootstrap.js"></script>
	</body>
</html>
