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
			if(currentUser == null || !currentUser.isAdmin()) {
				response.sendRedirect("iniciarSesion.jsp?pagina=CargaArticuloServlet");
				return;
			}
		%>
		<title>Create Article</title>
		<link rel="icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
		<%@page import="entities.Category" %>
		<%@page import="java.util.ArrayList"%>
		<%
			@SuppressWarnings("unchecked")
			ArrayList<Category> categorias = (ArrayList<Category>) request.getAttribute("categorias");
		%>
	</head>
	<body class=bg-light>
		<%@include file="../header.jsp"%>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Create new article</h1>
						<form action="CargaArticuloServlet" method="post" enctype="multipart/form-data">		
							<div class="form-group">
								<label for="desc">Description</label>
								<input class="form-control" name="descripcion" required id="desc"/>
							</div>	
							<div class="form-group">
								<label for="pto_ped" title="indica el stock minimo">Order limit</label>
								<input class="form-control" name="puntoPedido" required id="pto_ped"/>
							</div>
							<div class="form-group">
								<label for="cant_a_pedir" title="cuando se alcance el stock minimo">Amount to order</label>
								<input class="form-control" name="cantAPedir" required id="cant_a_pedir"/>
							</div>	
							<div class="form-group">
								<label for="stock">Stock</label>
								<input class="form-control" name="stock" required id="stock"/>
							</div>
							<div class="form-group">
								<label for="imagen">Image</label>
								<br>
								<label for="imagen" class="btn btn-primary">Select image</label>
								<input type="file" style="visibility:hidden;height:0;width:0;" name="imagen" id="imagen"/>
							</div>	
							<div class="form-group">
								<label for="precio">Price</label>
								<input class="form-control" name="precio" required id="precio"/>
							</div>
							<div class="form-group">
								<label for="categoria">Category</label>
								<Select name = "categorias">
									<%
										for(Category cat : categorias) {
									%>
										<Option value = <%=cat.getNombre() %> > <%=cat.getNombre() %> </Option>
									<% } %>
								</Select>
							</div>									
							<input type="submit" class="btn btn-success btn-block"  title="Click to register article" value="Save article"/>
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