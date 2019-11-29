<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%Cliente c = (Cliente)session.getAttribute("cliente"); %>
		<%if(!(c!=null && c.isAdmin())){
		response.sendRedirect("iniciarSesion.jsp");
		return;
		}%>
		<title>Registrar pago</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">	
	</head>
	<body>
		<%@include file="../header.jsp" %>
		
		<form class="form-inline" method="get" action="RegistrarPagoServlet"> <!-- crear servlet -->
		
			<div class="form-group">
				<label class="control-label" for="username">Ingrese username del cliente:</label>
				<input class="form-control" type="text" id="username" name="username">
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-success" value="Buscar" title="Click para ver pagos pendientes">
			</div>
		
		</form>
		
		
		
		<%@include file="../footer.jsp"%>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
		<script src="bootstrap/js/miJavaScript.js"></script>	
	</body>
</html>