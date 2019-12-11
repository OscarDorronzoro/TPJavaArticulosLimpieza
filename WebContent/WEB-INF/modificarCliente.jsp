<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%Cliente c = (Cliente)request.getSession().getAttribute("cliente"); %>
		<%if(!(c!=null && c.isAdmin())){
		response.sendRedirect("../iniciarSesion.jsp");
		return;
		}%>
		<title>Modificar Cliente</title>
		<link rel="icon" href="../png/favicon.ico">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">	
		<%	Cliente cli = (Cliente)request.getAttribute("cli");
			Cliente cliModificado = (Cliente)request.getAttribute("cliModificado");		
		%>
	</head>
	
	<body>
		<%@include file="../header.jsp" %>
		
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Modificar Cliente</h1>
						<form action="ModificarClienteServlet/Modificar" method="get">		
							<div class="form-group">
								<label for="username" class="control-label">Usuario</label>
								<input class="form-control" value="<%=cli.getUsername() %>" readonly name="username" id="username"/>
							</div>		
							<div class="form-group">
								<label for="nombre" class="control-label">Nombre</label>
								<input class="form-control" value="<%=cli.getNombre() %>" readonly name="nombre" id="nombre"/>
							</div>
							<div class="form-group">
								<label for="apellido" class="control-label">Apellido</label>
								<input class="form-control" value="<%=cli.getApellido() %>" readonly name="apellido" id="apellido"/>
							</div>
							<div class="form-group">
								<label for="DNI" class="control-label">DNI</label>
								<input class="form-control" value="<%=cli.getDNI() %>" readonly name="DNI" id="DNI"/>
							</div>
							<div class="form-group">
								<label for="isAdmin" class="control-label">Es administrador </label>
								<input class="form-control" <%if(cli.isAdmin()){ %> checked <% } %> name="isAdmin" id="isAdmin" type="checkbox"/>
							</div>	
							<div class="form-group">
								<input type="submit" class="btn btn-success btn-block"  title="Presione para modificar cliente" value="OK"/>
							</div>
						</form>
					</section>
					<%if(cliModificado!=null){ %>
						<div>
							<span>El cliente <%=cliModificado.getUsername()%> ha sido modificado</span>
						</div>
					<%} %>
				</div>
			</div>
		</div>
		
		
		<%@include file="../footer.jsp" %>
	
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/popper.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
		<script src="bootstrap/js/miJavaScript.js"></script>
	</body>
</html>