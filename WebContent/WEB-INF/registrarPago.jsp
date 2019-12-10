<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%Cliente c = (Cliente)session.getAttribute("cliente"); %>
		<%if(!(c!=null && c.isAdmin())){
		response.sendRedirect("../iniciarSesion.jsp");
		return;
		}%>
		<title>Registrar pago</title>
		<link rel="shortcut icon" href="../png/favicon.ico">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">   <%//por tener "servlet/*" toma las direcciones relativas al servlet %>
		<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">
		<%
			@SuppressWarnings("unchecked")	
			ArrayList<Venta> ventas=(ArrayList<Venta>)request.getAttribute("ventas");
		%>	
	</head>
	<body>
		<%@include file="../header.jsp" %>
		
		<form class="form-inline" method="get" action="../RegistrarPagoServlet/buscar"> <!-- crear servlet -->
		
			<div class="form-group">
				<label class="control-label" for="username">Ingrese username del cliente:</label>
				<input class="form-control" type="text" id="username" name="username" <%if(ventas!=null){ %><%="value=\""+ventas.get(0).getCliente().getUsername()+"\""%><%} %>>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-success" value="Buscar pagos pendientes" title="Click para ver pagos pendientes">
			</div>
		
		</form>
		
		<%if(ventas!=null){ %>
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
						<td><%=vta.getfPago()%></td>
						<td><%=vta.getfRetiro()%></td>
						<td><%=vta.getfCancelacion()%></td>
						<td><%=vta.getImporte()%></td>
						
						<td><a class="btn btn-primary" onclick="confirmarEIrA('../RegistrarPagoServlet/RegistrarPagado?nroVenta=<%= vta.getNroVenta()%>')">Pagado</a></td>
						<td><a class="btn btn-danger" onclick="confirmarEIrA('../RegistrarPagoServlet/RegistrarNoPagado?nroVenta=<%= vta.getNroVenta()%>')">No pagado</a></td>
					</tr>							
							
				<%} %>
				</tbody>
			</table>
		<%} %>
		
		<%@include file="../footer.jsp"%>
		<script src="../bootstrap/js/jquery-3.4.1.js"></script>
		<script src="../bootstrap/js/popper.js"></script>
		<script src="../bootstrap/js/bootstrap.js"></script>
		<script src="../bootstrap/js/miJavaScript.js"></script>	
	</body>
</html>