<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<% Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente"); %>
		<% if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("iniciarSesion.jsp?pagina=CargaCategoriaServlet");
			return;
		} %>
		<title>Carga Categoría</title>
		<link rel="icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
	</head>
	<body class=bg-light>
		<%@include file="../header.jsp"%>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Ingrese una categoría</h1>
						<form action="CargaCategoriaServlet" method="post">		
							<div class="form-group">
								<label for="nombre">Nombre</label>
								<input class="form-control" name="nombre" required id="nombre"/>
							</div>
							<div class="form-group">
								<label for="desc">Descripción</label>
								<input class="form-control" name="descripcion" required id="desc"/>
							</div>	
							<input type="submit" class="btn btn-success btn-block"  title="Presione para registrar categoria" value="Guardar categoria"/>
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