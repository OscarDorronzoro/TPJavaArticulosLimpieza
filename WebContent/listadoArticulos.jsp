<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<title>Articles</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
		<%
			@SuppressWarnings("unchecked")	
			ArrayList<Article> articles = (ArrayList<Article>) request.getAttribute("articulos");
		%>
	</head>
	<body class="bg-light">
	
		<%@include file="header.jsp" %>
		
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 bg-sidebar">
			    <div class="row">
			    	<div class="col-xs-12 col-sm-12 col-md-12">
			    		 <div class="sidebar-header">
					         <h3>Articles' categories</h3>
					     </div>		
			    	</div>
			    	<div class="col-xs-1 col-sm-1 col-md-1">
			    		<ul class="list-unstyled components menu-icon">
					            <li>
									<img src="png/limpieza/todo.png">
					            </li>
					            <li>
									<img src="png/limpieza/cocina.png">
					            </li>
					            <li>
									<img src="png/limpieza/banio.png">
					            </li>
					            <li>
									<img src="png/limpieza/muebles.png">
					            </li>            
					        </ul>
			    	</div>
			    	<div class="col-xs-10 col-sm-10 col-md-10">
					    <nav>
					        <ul class="list-unstyled components menu-text">
					            <li>
									<a href="#">All</a>
					            </li>
					            <li>
									<a href="#">Kitchen</a>
					            </li>
					            <li>
									<a href="#">Bathroom</a>
					            </li>
					            <li>
									<a href="#">Furniture</a>
					            </li>	            
					        </ul>
					    </nav>
				    </div>
			    </div>
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-8 bg-articulos">
				<%@page import="entities.Article"%>
				<%@page import="java.util.ArrayList"%>
				<%
					for( Article art : articles) {
				%>

					<div class="row bg-articulo" id=<%="articulo"+art.getCodArticulo() %> > 
						<div class="col-xs-3 col-md-3"><img class="imagen-articulo" src="<%=art.getUrlImagen()%>"></div>
						<div class="col-xs-9 col-md-9">
							<div class="row">
								<div class="col-xs-6 col-md-8">
									<ul class="list-unstyled">							
										<li class="atributo-link">Descripción: <%=art.getDescripcion()%></li>
										<li>Stock: <%=art.getStock()%></li>
										<li>Precio: $<%=art.getPrecio().getValor()%></li>
									</ul>
								</div>
								<div class="col-xs-6 col-md-4">
									<div class="row">
										<form action="CarritoServlet" method="post">
											<div class="form-group">
												<input type="hidden" name="articleCode" value=<%=art.getCodArticulo() %>>
												<label class="label-control">Cantidad:</label>
												<input name="amount" class="form-control" size=2px>
											</div>
											<div class="form-group">
												<input type="submit" value="Comprar" name="comprar" class="btn btn-success btn-block">
												<input type="submit" value="Añadir al carrito" name="carrito" class="btn btn-success btn-block">
											</div>
										</form>
										<br>
									</div>
								</div>
							</div>
						</div>
					</div>
									
				<% } %>
			</div>
		</div>
		
		<%@include file="footer.jsp"%>
		
		<script src="custom/custom-scripts.js"></script>
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>