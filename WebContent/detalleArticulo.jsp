<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<title>Article's detail</title>
		<link rel="stylesheet" href="custom/custom-styles.css">
	</head>
	
	<body class="bg-light">
		<%@page import="entities.Article" %>
		<%
		 	Article article = (Article) request.getAttribute("article");
		%>
		<%=article.getDescripcion() %>
		<%=article.getPrecio().getValor() %>
		<p>Not fully implemented</p>
	</body>
</html>