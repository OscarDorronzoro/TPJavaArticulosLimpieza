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
				response.sendRedirect("../iniciarSesion.jsp?pagina=CompletedSellsServlet");
				return;
			 }
		%>
		<title>Completed Sells</title>
		<link rel="shortcut icon" href="../png/favicon.ico">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="../custom/custom-styles.css">
		
		<%
				@SuppressWarnings("unchecked")
					ArrayList<Sale> sales = (ArrayList<Sale>) request.getAttribute("sales");
					String username = (String) request.getAttribute("username");
				%>	
	</head>
	<body>
		<%@include file="../header.jsp" %>
		
		<form class="form-inline" method="get" action="../ListCompletedSalesServlet/user">
			<div class="form-group">
				<label class="control-label" for="username">Customer's username:</label>
				<input class="form-control" type="text" id="username" name="username"
					placeholder="Empty for all customers" 
					value="<%=username != null ? username : ""%>">
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-success" value="Search" title="Click to search completed sales">
			</div>
			<br><br>
		</form>
		
		<%@page import="java.util.ArrayList"%>
		<%@page import="entities.Sale"%>
		
		<%!double total;%>
		<%
		total = 0;
		%>
		
		<%
				if (sales != null && !sales.isEmpty()){
				%>
			<table class="table table-striped table-hover">	
				<thead>
					<tr>
						<td>Sale number</td>
						<td>Emission date</td>
						<td>Payment date</td>
						<td>Withdrawal date</td>
						<td>Cancellation date</td>
						<td>Sale amount</td>
					</tr>
				</thead>
						
				<tbody>
				
				<%
								for( Sale s : sales){
								%>
											
					<tr>
						<td><%=s.getNroVenta()%></td>	
						<td><%=s.getfEmision()%></td>
						<td><%=s.getfPago() == null ? "-" : s.getfPago()%></td>
						<td><%=s.getfRetiro() == null ? "-" : s.getfRetiro()%></td>
						<td><%=s.getfCancelacion() == null ? "-" : s.getfCancelacion()%></td>
						<td><%=s.getImporte()%></td>
						
						<% total += s.getImporte(); %>
					</tr>							
							
				<%} %>
				</tbody>
			</table>
			
			<p>Total amount across sales: <%=total %></p>
			
		<%} else { 
				if (sales != null && sales.isEmpty()) { %>
					<span>There aren't completed sales</span>
			 <% }
		  } %>
		
		
		<%@include file="../footer.jsp"%>
		
		<script src="../custom/custom-scripts.js"></script>
		<script src="../bootstrap/js/jquery-3.4.1.js"></script>
		<script src="../bootstrap/js/popper.js"></script>
		<script src="../bootstrap/js/bootstrap.js"></script>
	</body>
</html>