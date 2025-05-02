<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<% Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente"); %>
		<% if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp?pagina=CargaProveedorServlet");
			return;
		} %>
		<title>Carga Proveedor</title>
		<link rel="icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
	</head>
	<body class=bg-light>
		<%@include file="../header.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Ingrese un proveedor</h1>
						<form action="CargaProveedorServlet" method="post">		
							<div class="form-group">
								<label for="cuit">CUIT</label>
								<input class="form-control" name="cuit" required id="cuit"/>
							</div>
							<div class="form-group">
								<label for="businessName">Razón social</label>
								<input class="form-control" name="businessName" required id="businessName"/>
							</div>
							<div class="form-group">
								<label for="address">Direción</label>
								<input class="form-control" name="address" required id="address"/>
							</div>
							<div class="form-group">
								<label for="phoneNumber">Teléfono</label>
								<input class="form-control" name="phoneNumber" required id="phoneNumber"/>
							</div>
							<div class="form-group">
								<label for="mail">E-Mail</label>
								<input class="form-control" name="mail" required id="mail"/>
							</div>
							<input type="submit" class="btn btn-success btn-block"  title="Presione para registrar proveedor" value="Guardar Proveedor"/>
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
