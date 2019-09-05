<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<%@page import="entities.Cliente"%>
		<%
		Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
		if(cliente==null){
			response.sendRedirect("iniciarSesion.jsp");
			return;	
		}
		else if(!cliente.isAdmin()){
			response.sendRedirect("iniciarSesion.jsp");
			return;
		}
		%>
		<title>Carga Articulo</title>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
	</head>
	<body>
		<%@include file="../header.jsp"%>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Ingrese un articulo</h1>
						<form action="CargaArticuloServlet" method="get">		
							<div class="form-group">
								<label for="desc">Descripción</label>
								<input class="form-control" name="descripcion" id="desc"/>
							</div>
							<div class="form-group">
								<label for="cant_a_pedir" title="cuando se alcance el stock minimo">Cantidad a pedir</label>
								<input class="form-control" name="cantAPedir" id="cant_a_pedir" type="password"/>
							</div>		
							<div class="form-group">
								<label for="pto_ped" title="indica el stock minimo">Punto de pedido</label>
								<input class="form-control" name="puntoPedido" id="pto_ped"/>
							</div>
							<div class="form-group">
								<label for="stock">Stock</label>
								<input class="form-control" name="stock" id="stock"/>
							</div>
							<div class="form-group">
								<label for="imagen">URL Imagen</label>
								<input class="form-control" name="imagen" id="imagen"/>
							</div>	
							<input type="submit" class="btn btn-success btn-block"  title="Presione para registrar articulo" value="Guardar artículo"/>
						</form>
					</section>
				</div>
			</div>
		</div>
		
		<%@include file="../footer.jsp" %>	
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>