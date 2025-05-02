<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<title>Artículos de Limpieza</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
	</head>
	<body class="bg-light">
	
		<%@include file="header.jsp" %>
		
		<div class="main-container">
	        <div class="page-header">
	            <h1>Productos de Limpieza</h1>
	            <p class="lead">Todo lo que necesitas para mantener tu hogar impecable</p>
	        </div>
	        
	        <!-- Featured Products -->
	        <h3 class="category-header text-primary">
	            <span class="glyphicon glyphicon-star"></span> Productos Destacados
	        </h3>
	        <div class="row">
	            <div class="col-md-4">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/escoba-premium.jpg" alt="Escoba Premium">
	                    <h4>Escoba de Paja Premium</h4>
	                    <p>Escoba resistente con cerdas naturales para una limpieza profunda.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$1,299</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-4">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/aspiradora.jpg" alt="Aspiradora Moderna">
	                    <h4>Aspiradora 2000W</h4>
	                    <p>Potente aspiradora con filtro HEPA y accesorios para todo tipo de superficies.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$5,999</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-4">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/detergente.jpg" alt="Detergente Concentrado">
	                    <h4>Detergente Concentrado</h4>
	                    <p>Paquete de 3 litros de detergente biodegradable para todo tipo de ropa.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$899</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	        </div>
	        
	        <!-- Cleaning Tools -->
	        <h3 class="category-header text-success">
	            <span class="glyphicon glyphicon-wrench"></span> Herramientas de Limpieza
	        </h3>
	        <div class="row">
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/escoba-paja_.jpg" alt="Escoba de Paja">
	                    <h4>Escoba de Paja</h4>
	                    <p>Escoba tradicional para barrer eficientemente.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$799</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/recogedor.jpg" alt="Recogedor de Basura">
	                    <h4>Recogedor de Basura</h4>
	                    <p>Recogedor metálico resistente para acompañar tu escoba.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$450</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/trapeador.jpg" alt="Trapeador de Microfibra">
	                    <h4>Trapeador Microfibra</h4>
	                    <p>Cabezal extraíble y lavable para pisos brillantes.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$1,150</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/cubeta.jpg" alt="Cubeta para Limpieza">
	                    <h4>Cubeta con Escurridor</h4>
	                    <p>Incluye escurridor integrado para trapeadores.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$650</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	        </div>
	        
	        <!-- Cleaning Products -->
	        <h3 class="category-header text-info">
	            <span class="glyphicon glyphicon-tint"></span> Productos Químicos
	        </h3>
	        <div class="row">
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/cloro.jpg" alt="Cloro Concentrado">
	                    <h4>Cloro Concentrado</h4>
	                    <p>Botella de 1 litro para desinfección profunda.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$120</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/desengrasante.jpg" alt="Desengrasante Industrial">
	                    <h4>Desengrasante Industrial</h4>
	                    <p>Elimina grasa difícil en cocinas y talleres.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$320</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/jabon.jpg" alt="Jabón Líquido Multiusos">
	                    <h4>Jabón Multiusos</h4>
	                    <p>Limpia todo tipo de superficies sin dañarlas.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$280</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/aromatizante.jpg" alt="Aromatizante Ambiental">
	                    <h4>Aromatizante Lavanda</h4>
	                    <p>Fragancia relajante para todo el hogar.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$390</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Añadir
	                        </button>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
		
		<%@include file="footer.jsp" %>
		
		<script src="bootstrap/js/jquery-3.4.1.js"></script>
		<script src="bootstrap/js/bootstrap.js"></script>
	</body>
</html>