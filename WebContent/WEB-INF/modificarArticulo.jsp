<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<%
		 	Cliente currentUser = (Cliente) request.getSession().getAttribute("cliente");
		%>
		<% 
			 if (currentUser == null || !currentUser.isAdmin()) {
				response.sendRedirect("iniciarSesion.jsp?pagina=ModificarArticuloServlet");
				return;
			 }
		%>
		<title>Modificar Articulo</title>
		<link rel="icon" href="../png/favicon.ico">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="../custom/custom-styles.css">
		
		<%@page import="entities.Article" %>
		<%@page import="java.util.ArrayList" %>
		<%@page import="entities.Categoria" %>
		<%
			Article article = (Article) request.getAttribute("articulo");
		%>
		<% 
			@SuppressWarnings("unchecked")
			ArrayList<Categoria> categories = (ArrayList<Categoria>) request.getAttribute("categorias");
		%>
	</head>
	<body class=bg-light>
		<%@include file="../header.jsp"%>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Modifique los datos del articulo</h1>
						<form action="../ModificarArticuloServlet/Cargado" method="post" enctype="multipart/form-data">		
							<input value="<%=article.getCodArticulo() %>" name="codArticulo" type="hidden" />
							
							<div class="form-group">
								<label for="desc">Descripción</label>
								<input value="<%=article.getDescripcion() %>" class="form-control" name="descripcion" required id="desc"/>
							</div>
							<div class="form-group">
								<label for="categ">Categoría</label>
								<select class="form-control" name="categoria" id="categ">
									<%for (Categoria cat : categories) { %>
										<option <%=cat.getNombre().equals(article.getCategoria().getNombre())?"selected":"" %> value="<%=cat.getNombre() %>"><%=cat.getDescripcion() %></option>
									<%} %>
								</select>
							</div>
							<div class="form-group">
								<label for="pto_ped" title="indica el stock minimo">Punto de pedido</label>
								<input value="<%=article.getPuntoPedido() %>" class="form-control" name="puntoPedido" required id="pto_ped"/>
							</div>
							<div class="form-group">
								<label for="cant_a_pedir" title="cuando se alcance el stock minimo">Cantidad a pedir</label>
								<input value="<%=article.getCantAPedir() %>" class="form-control" name="cantAPedir" required id="cant_a_pedir"/>
							</div>
							<div class="form-group">
								<label for="stock">Stock</label>
								<input value="<%=article.getStock() %>" class="form-control" name="stock" required id="stock"/>
							</div>
							<div class="form-group">
								<label for="imagen">Imagen</label>
								<br>
								<label for="imagen" class="btn btn-primary">Seleccionar imagen</label>
								<input type="file" style="visibility:hidden;height:0;width:0;" name="imagen" id="imagen"/>
							</div>
							<div class="form-group">
								<label for="precio">Precio</label>
								<input value="<%=article.getPrecio().getValor() %>" class="form-control" name="precio" required id="precio"/>
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