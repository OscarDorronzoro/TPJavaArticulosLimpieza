<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<%Cliente c = (Cliente)request.getSession().getAttribute("cliente"); %>
		<%if(!(c!=null && c.isAdmin())){
		response.sendRedirect("iniciarSesion.jsp");
		return;
		}%>
		<title>Carga Articulo</title>
		<link rel="icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
	</head>
	<body class=bg-light>
		<%@include file="../header.jsp"%>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Ingrese un articulo</h1>
						<form action="CargaArticuloServlet/cargado" method="post" enctype="multipart/form-data">		
							<div class="form-group">
								<label for="desc">Descripción</label>
								<input class="form-control" name="descripcion" required id="desc"/>
							</div>	
							<div class="form-group">
								<label for="pto_ped" title="indica el stock minimo">Punto de pedido</label>
								<input class="form-control" name="puntoPedido" required id="pto_ped"/>
							</div>
							<div class="form-group">
								<label for="cant_a_pedir" title="cuando se alcance el stock minimo">Cantidad a pedir</label>
								<input class="form-control" name="cantAPedir" required id="cant_a_pedir"/>
							</div>	
							<div class="form-group">
								<label for="stock">Stock</label>
								<input class="form-control" name="stock" required id="stock"/>
							</div>
							<div class="form-group">
								<label for="imagen">Imagen</label>
								<br>
								<label for="imagen" class="btn btn-primary">Seleccionar imagen</label>
								<input type="file" style="visibility:hidden;height:0;width:0;" name="imagen" id="imagen"/>
							</div>	
							<div class="form-group">
								<label for="precio">Precio</label>
								<input class="form-control" name="precio" required id="precio"/>
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