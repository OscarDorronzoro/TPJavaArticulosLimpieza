<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
				response.sendRedirect("../iniciarSesion.jsp?pagina=ListadoClientesServlet/todo");
				return;
			 }
		%>
		<title>Customers</title>
		<link rel="icon" href="../png/favicon.ico">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="../custom/custom-styles.css">
		
		<%
			@SuppressWarnings("unchecked")	
			ArrayList<Customer> customers = (ArrayList<Customer>) request.getAttribute("clientes");
		%>
	</head>
	<body class="bg-light">
	
		<%@include file="../header.jsp" %>
		
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3 bg-sidebar">
			    <div class="row">
			    	<div class="col-xs-12 col-sm-12 col-md-12">
			    		 <div class="sidebar-header">
					         <h3>Type of user/customer</h3>
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
					        </ul>
			    	</div>
			    	<div class="col-xs-10 col-sm-10 col-md-10">
					    <nav>
					        <ul class="list-unstyled components menu-text">
					            <li>
									<a href="todo">All users</a>
					            </li>
					            <li>
									<a href="admin">Administrators</a>
					            </li>
					            <li>
									<a href="noadmin">Non Administrators</a>
					            </li>            
					        </ul>
					    </nav>
				    </div>
			    </div>
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-8">
				<table class="table table-striped table-hover">
					<%@page import="java.util.ArrayList"%>
					
					<thead>
						<tr>
							<td>Username</td>
							<td>Name</td>
							<td>Last name</td>
							<td>DNI</td>
							<td>Is administrator?</td>
						</tr>
					</thead>
					
					<tbody>
					<%
						for( Customer customer : customers) {
					%>
												
						<tr <%if(customer.isAdmin()){%>class="warning"<%} %>>	
							<td><%=customer.getUsername()%></td>
							<td><%=customer.getNombre()%></td>
							<td><%=customer.getApellido()%></td>
							<td><%=customer.getDNI()%></td>
							<td><input type="checkbox" name="isAdmin" value="Check Value" readonly="readonly" 
								<%if(customer.isAdmin()){%>checked<%} %> onclick="javascript: return false;"/>
							</td>
							<td><a class="btn btn-primary" href="../ModificarClienteServlet?username=<%=customer.getUsername()%>">Modify</a></td>
							<td><a class="btn btn-danger" onclick="confirmarEIrA('../EliminarClienteServlet?username=<%=customer.getUsername()%>')">Delete</a></td>
						</tr>							
								
					<%} %>
					</tbody>
				</table>
			</div>
		</div>
		
		<%@include file="../footer.jsp"%>
		
		<script src="../custom/custom-scripts.js"></script>
		<script src="../bootstrap/js/jquery-3.4.1.js"></script>
		<script src="../bootstrap/js/popper.js"></script>
		<script src="../bootstrap/js/bootstrap.js"></script>
	</body>
</html>