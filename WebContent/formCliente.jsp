<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registrar Cliente</title>
	</head>
	<body>
		<form action="FormularioClienteServlet" method="get">
			<h1>Formulario</h1>
			<table>
				<tr>
					<td>Usuario</td>
					<td><input name="username" /></td>
				</tr>
				<tr>
					<td>Contraseña</td>
					<td><input name="password" /></td>
				</tr>
				<tr>
					<td>Nombre</td>
					<td><input name="nombre" /></td>
				</tr>
				<tr>
					<td>Apellido</td>
					<td><input name="apellido" /></td>
				</tr>
				<tr>
					<td>DNI</td>
					<td><input name="DNI" /></td>
				</tr>
			</table>
			<input type="submit"/>
		</form>
	</body>
</html>