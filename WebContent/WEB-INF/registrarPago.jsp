<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<%
		Customer currentUser = (Customer) session.getAttribute("cliente");
		%>
		<%
		if (currentUser == null || !currentUser.isAdmin()) {
				response.sendRedirect("../iniciarSesion.jsp?pagina=RegistrarPagoServlet");
				return;
			 }
		%>
		<title>Register Payment</title>
		<link rel="shortcut icon" href="../png/favicon.ico">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="../custom/custom-styles.css">
		
		<%
				@SuppressWarnings("unchecked")	
					ArrayList<Sale> sales = (ArrayList<Sale>) request.getAttribute("ventas");
					Sale sale = (Sale) request.getAttribute("venta");
					String username = (String) request.getAttribute("username");
				%>	
	</head>
	<body>
		<%@include file="../header.jsp" %>
		
		<form class="form-inline" method="get" action="../RegistrarPagoServlet/Buscar">
			<div class="form-group">
				<label class="control-label" for="username">Customer's username:</label>
				<input class="form-control" type="text" id="username" name="username" 
					value="<%=username != null ? username : ""%>">
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-success" value="Search pending sales" title="Click to see pending sales">
			</div>
		<br><br>
		
		</form>
		
		<%
				if (sales != null && !sales.isEmpty()){
				%>
			<table class="table table-striped table-hover">
				<%@page import="java.util.ArrayList"%>
				<%@page import="entities.Sale"%>
						
				<thead>
					<tr>
						<td>Sale number</td>
						<td>Emission date</td>
						<td>Payment date</td>
						<td>Withdrawal date</td>
						<td>Cancellation date</td>
						<td>Total</td>
					</tr>
				</thead>
						
				<tbody>
				<%
				for( Sale s : sales) {
				%>
											
					<tr>
						<td><%=s.getNroVenta()%></td>	
						<td><%=s.getfEmision()%></td>
						<td><%=s.getfPago() == null ? "-" : s.getfPago()%></td>
						<td><%=s.getfRetiro() == null ? "-" : s.getfRetiro()%></td>
						<td><%=s.getfCancelacion() == null ? "-" : s.getfCancelacion()%></td>
						<td><%=s.getImporte()%></td>
						
						<td>
							<form action="../RegistrarPagoServlet/RegistrarPago?saleNumber=<%=s.getNroVenta()%>" method="post">
								<input type="submit" name="paid" value="Paid" class="btn btn-success">
								<input type="submit" name="notPaid" value="Not paid" class="btn btn-primary">
							</form>
						</td>
					</tr>							
							
				<%} %>
				</tbody>
			</table>
		<%} else { 
				if (sales != null && sales.isEmpty()) { %>
					<span>Customer hasn't pending payments</span>
			 <% }
		  } %>
		
		<% if (sale != null) { %>
			<span>Sale (sale number: <%=sale.getNroVenta() %>) payment has been <%=sale.getfPago() == null ? "cancelled" : "registered" %></span>
		<%} %>
		
		<%@include file="../footer.jsp"%>
		
		<script src="../custom/custom-scripts.js"></script>
		<script src="../bootstrap/js/jquery-3.4.1.js"></script>
		<script src="../bootstrap/js/popper.js"></script>
		<script src="../bootstrap/js/bootstrap.js"></script>
	</body>
</html>