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
				response.sendRedirect("iniciarSesion.jsp?pagina=ModificarCategoriaServlet");
				return;
			}
		%>
		<title>Edit Provider</title>
		<link rel="icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
		
		<%@ page import="java.net.URLEncoder" %>
		<%@ page import="entities.Category" %>
		<%
			Category category = (Category) request.getAttribute("category");
		%>
	</head>
	<body class=bg-light>
		<%@include file="../header.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Edit Category</h1>
						<form action="ModificarCategoriaServlet" method="post">		
							<div class="form-group">
								<label for="name">Name</label>
								<input value="<%=category != null ? category.getNombre() : "" %>" class="form-control" name="name" readonly required id="name"/>
							</div>
							<div class="form-group">
								<label for="description">Description</label>
								<input value="<%=category != null ? category.getDescripcion() : "" %>" class="form-control" name="description" required id="description"/>
							</div>
							<input type="submit" class="btn btn-success btn-block"  title="Click to save category" value="Save Category"/>
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
