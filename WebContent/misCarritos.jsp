<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
	<%
		if(request.getSession().getAttribute("cliente") == null){
			response.sendRedirect("../iniciarSesion.jsp?pagina=misCarritos.jsp");
			return;
		}
	%>
	
	<title>My Carts</title>
	<link rel="shortcut icon" href="../png/favicon.ico">
	<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">
	<link rel="stylesheet" href="../custom/custom-styles.css">
</head>
<body class="bg-light">
	
	<%@include file="header.jsp" %>	
		
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-3 bg-sidebar">
			<div class="row">
			    <div class="col-xs-12 col-sm-12 col-md-12">
					<div class="sidebar-header">
						<h3>My Carts</h3>
					</div>		
				</div>
				<div class="col-xs-1 col-sm-1 col-md-1">
					<ul class="list-unstyled components menu-icon">
						<li>
							<img src="../png/limpieza/todo.png">
						</li>
						<li>
							<img src="../png/limpieza/cocina.png">
						</li>
						<li>
							<img src="../png/limpieza/banio.png">
						</li>
						<li>
							<img src="../png/limpieza/muebles.png">
						</li>            
					</ul>
				</div>
				<div class="col-xs-10 col-sm-10 col-md-10">
					<nav>
						<ul class="list-unstyled components menu-text">
							<li>
								<a href="../CarritoServlet/currentPurchase">Current Purchase (
									<% if(currentCustomer.getMiCarrito("currentPurchase") != null && currentCustomer.getMiCarrito("currentPurchase").getLineas() != null) {%> 
									<%=currentCustomer.getMiCarrito("currentPurchase").getLineas().size()%> 
									<% } %>)
								</a>
							</li>
							<li>
								<a href="../CarritoServlet/favorites">Favorites (
									<% if(currentCustomer.getMiCarrito("favorites") != null && currentCustomer.getMiCarrito("favorites").getLineas() != null) {%> 
									<%=currentCustomer.getMiCarrito("favorites").getLineas().size()%> 
									<% } %>)
								</a>
							</li>
							<li>
								<a href="../CarritoServlet/wishList">Wish List (
									<% if(currentCustomer.getMiCarrito("wishList") != null && currentCustomer.getMiCarrito("wishList").getLineas() != null) {%> 
									<%=currentCustomer.getMiCarrito("wishList").getLineas().size()%> 
									<% } %>)
								</a>
							</li>
							<li>
								<a href="../CarritoServlet/budget">Budget (
									<% if(currentCustomer.getMiCarrito("budget") != null && currentCustomer.getMiCarrito("budget").getLineas() != null) {%> 
									<%=currentCustomer.getMiCarrito("budget").getLineas().size()%> 
									<% } %>)
								</a>
							</li>	            
						</ul>
					</nav>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12 col-md-8">
		
		<%@page import="entities.Article"%>
		<%@page import="entities.Cart"%>
		<%@page import="entities.Line"%>
		<%@page import="java.util.ArrayList"%>
		<%@page import="logic.ABMCLineaCarrito"%>
		
		<%!Cart cart;%>
		<%!ArrayList<Line> lines;%>
		<%!double total = 0;%>
		
		<%
			Customer customer = (Customer) request.getSession().getAttribute("cliente");
			cart = (Cart) request.getAttribute("cart");
			lines = cart.getLineas();
			total = customer.getMiCarrito().getTotal();
		%>
		<%
			for( Line line : lines) {
		%>
			<div class="row bg-articulo">
				<div class="col-md-2"><img class="imagen-articulo" src="../<%=line.getArticulo().getUrlImagen()%>"></div>
				<div class="col-md-9">
					<div class="row">
						
						<!-- Article details -->
						<div class="col-md-8">
							<ul class="list-unstyled">							
								<li>Description: <%=line.getArticulo().getDescripcion() %></li>
								<li>Unit price: $<%=line.getArticulo().getPrecio().getValor() %></li>
								<li>Subtotal: $<%=line.getSubTotal() %></li>
							</ul>
						</div>
						
						<!-- Delete from cart / modify amount of articles in cart -->
						<div class="col-md-2">
							<div class="row">
								<a href=<%="../EliminarDeCarritoServlet?articleCode="+line.getArticulo().getCodArticulo()+"&cartType="+cart.getNombre()%> class="btn btn-danger">Delete from cart</a><br/>
								<form action="../ModificarCarritoServlet" method="post">
									<div class="form-group">
										<input name="cartType" type="hidden" value="<%=cart.getNombre()%>">
										<input name="articleCode" type="hidden" value="<%=line.getArticulo().getCodArticulo()%>">
										<label class="label-control">Amount:</label>
										<input name="amount" value="<%=line.getCantidad() %>"  class="form-control" maxlength="3" pattern="[1-9][0-9]*" size=2px>
									</div>
									<div class="form-group">
										<input type="submit" value="Modify" name="btnmodificar" class="btn btn-primary btn-block">
									</div>
									
								</form>								
								
							</div>
					 	</div>
					 	
					 	<!-- Move cart line to other cart (i.e: current purchase, wish list) -->
					 	<div class="col-md-2">
							<div class="row">
								<form action="../SwitchCartForLineServlet" method="post">
									<div class="form-group">
										<input name="currentCartType" type="hidden" value="<%=cart.getNombre()%>">
										<input name="articleCode" type="hidden" value="<%=line.getArticulo().getCodArticulo()%>">
										<label class="label-control">Cart's name:</label>
										<select name="newCartType">
											<option value="currentPurchase" <%=cart.getNombre().equals("currentPurchase") ? "selected" : "" %>>Current Purchase</option>
											<option value="favorites" <%=cart.getNombre().equals("favorites") ? "selected" : "" %>>Favorites</option>
											<option value="wishList" <%=cart.getNombre().equals("wishList") ? "selected" : "" %>>Wish List</option>
											<option value="budget" <%=cart.getNombre().equals("budget") ? "selected" : "" %>>Budget</option>
										</select>
									</div>
									<div class="form-group">
										<input type="submit" value="Switch" name="btnSwitchCart" class="btn btn-primary btn-block">
									</div>
									
								</form>								
								
							</div>
					 	</div>
					 	
					</div>
				</div>
			</div>
			<br><br>				
		<%} 
		if(!lines.isEmpty()){
		%>
		<div class="row">
			<div class="col-xs-10"></div>
			<div class="col-xs-2">
				<span>Total: <%=total %></span>
				<a href="../ComprarServlet" class="btn btn-success">Buy now</a>
			</div>	
		</div>
		<%} else{ %>
			<span>Oh, this is empty, go buy some articles</span>
		<%} %>
		
		</div>
	</div>
			
	<%@include file="footer.jsp" %>
	
	<script src="bootstrap/js/jquery-3.4.1.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>	
</body>
</html>