<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<% Cliente currentUser = (Cliente)session.getAttribute("cliente"); %>
		<% if (currentUser == null || !currentUser.isAdmin()) {
			response.sendRedirect("../iniciarSesion.jsp?pagina=RegistrarPagoServlet");
			return;
		} %>
		<title>Registrar pago</title>
		<link rel="shortcut icon" href="../png/favicon.ico">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">
		<%
			@SuppressWarnings("unchecked")	
			ArrayList<Venta> ventas = (ArrayList<Venta>) request.getAttribute("ventas");
			Venta venta = (Venta) request.getAttribute("venta");
			String username = (String) request.getAttribute("username");
		%>	
	</head>
	<body>
		<%@include file="../header.jsp" %>
		
		<form class="form-inline" method="get" action="../RegistrarPagoServlet/Buscar">
			<div class="form-group">
				<label class="control-label" for="username">Ingrese username del cliente:</label>
				<input class="form-control" type="text" id="username" name="username" 
					value="<%=username != null ? username : "" %>">
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-success" value="Buscar pagos pendientes" title="Click para ver pagos pendientes">
			</div>
		<br><br>
		
		</form>
		
		<% if (ventas != null && !ventas.isEmpty()){ %>
			<table class="table table-striped table-hover">
				<%@page import="java.util.ArrayList"%>
				<%@page import="entities.Venta"%>
						
				<thead>
					<tr>
						<td>Numero de Venta</td>
						<td>Fecha de Emision</td>
						<td>Fecha de Pago</td>
						<td>Fecha de Retiro</td>
						<td>Fecha de Cancelacion</td>
						<td>Total</td>
					</tr>
				</thead>
						
				<tbody>
				<%for( Venta vta : ventas){%>
											
					<tr>
						<td><%=vta.getNroVenta()%></td>	
						<td><%=vta.getfEmision()%></td>
						<td><%=vta.getfPago() == null ? "-" : vta.getfPago()%></td>
						<td><%=vta.getfRetiro() == null ? "-" : vta.getfRetiro()%></td>
						<td><%=vta.getfCancelacion() == null ? "-" : vta.getfCancelacion()%></td>
						<td><%=vta.getImporte()%></td>
						
						<td>
							<form action="../RegistrarPagoServlet/RegistrarPago?sellNumber=<%=vta.getNroVenta()%>" method="post">
								<input type="submit" name="paid" value="Pagado" class="btn btn-success">
								<input type="submit" name="notPaid" value="No pagado" class="btn btn-primary">
							</form>
						</td>
					</tr>							
							
				<%} %>
				</tbody>
			</table>
		<%} else { 
				if (ventas != null && ventas.isEmpty()) { %>
					<span>No tiene pagos pendientes</span>
			 <% }
		  } %>
		
		<% if (venta != null) { %>
			<span>Se ha <%= venta.getfPago() == null ? "cancelado" : "registrado" %> el pago de la venta <%=venta.getNroVenta() %></span>
		<%} %>
		
		<%@include file="../footer.jsp"%>
		<script src="../bootstrap/js/jquery-3.4.1.js"></script>
		<script src="../bootstrap/js/popper.js"></script>
		<script src="../bootstrap/js/bootstrap.js"></script>
		<script src="../bootstrap/js/miJavaScript.js"></script>	
	</body>
</html>