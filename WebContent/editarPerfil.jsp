<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<%if(request.getSession().getAttribute("cliente")==null){
			response.sendRedirect("../iniciarSesion.jsp");
			return;
		}%>
		<title>Editar perfil</title>
		<link rel="shortcut icon" href="../png/favicon.ico">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">
		<%// no es necesario definir cliente actual, ya esta en header.jsp
			Cliente cliModificado = (Cliente)request.getAttribute("cliModificado");
		%>
	</head>
	<body>
		<%@include file="../header.jsp" %>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<section>
						<h1>Editar Perfil</h1>
						<form action="../EditarPerfilServlet/modificar" method="get">		
							<div class="form-group">
								<label for="username" class="control-label">Usuario</label>
								<input class="form-control" value="<%=cliente.getUsername() %>" readonly name="username" id="username"/>
							</div>	
							<div class="form-group">
								<label for="nombre" class="control-label">Nombre</label>
								<input class="form-control" value="<%=cliente.getNombre() %>" name="nombre" id="nombre"/>
							</div>
							<div class="form-group">
								<label for="apellido" class="control-label">Apellido</label>
								<input class="form-control" value="<%=cliente.getApellido() %>" name="apellido" id="apellido"/>
							</div>
							<div class="form-group">
								<label for="DNI" class="control-label">DNI</label>
								<input class="form-control" value="<%=cliente.getDNI() %>" name="DNI" id="DNI"/>
							</div>	
							<div class="form-group">
								<input type="submit" class="btn btn-success btn-block"  title="Presione para editar perfil" value="OK"/>
							</div>
						</form>
					</section>
					<%if(cliModificado!=null){ %>
						<div>
							<span>Se ha editado su perfil</span>
						</div>
					<%} %>
				</div>
			</div>
		</div>
		
		<%@include file="../footer.jsp" %>	
		<script src="../bootstrap/js/jquery-3.4.1.js"></script>
		<script src="../bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="../bootstrap/js/miJavaSript.js"></script>
	
	</body>
</html>