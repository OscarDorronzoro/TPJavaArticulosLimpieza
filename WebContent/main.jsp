<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, user-scalable=yes">
		<title>Cleaning Supplies</title>
		<link rel="shortcut icon" href="png/favicon.ico">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
		<link rel="stylesheet" href="custom/custom-styles.css">
	</head>
	<body class="bg-light">
	
		<%@include file="header.jsp" %>
		
		<div class="main-container">
	        <div class="page-header">
	            <h1>Cleaning Products</h1>
	            <p class="lead">Everything you need to keep your home spotless</p>
	        </div>
	        
	        <!-- Featured Products -->
	        <h3 class="category-header text-primary">
	            <span class="glyphicon glyphicon-star"></span> Featured Products
	        </h3>
	        <div class="row">
	            <div class="col-md-4">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/escoba-premium.jpg" alt="Escoba Premium">
	                    <h4>Premium Straw Broom</h4>
	                    <p>Heavy-duty broom with natural bristles for deep cleaning.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$1,299</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-4">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/aspiradora.jpg" alt="Aspiradora Moderna">
	                    <h4>2000W vacuum cleaner</h4>
	                    <p>Powerful vacuum cleaner with HEPA filter and accessories for all types of surfaces.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$5,999</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-4">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/detergente.jpg" alt="Detergente Concentrado">
	                    <h4>Concentrated Detergent</h4>
	                    <p>3-liter pack of biodegradable detergent for all types of clothing.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$899</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
	                        </button>
	                    </div>
	                </div>
	            </div>
	        </div>
	        
	        <!-- Cleaning Tools -->
	        <h3 class="category-header text-success">
	            <span class="glyphicon glyphicon-wrench"></span> Cleaning Tools
	        </h3>
	        <div class="row">
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/escoba-paja_.jpg" alt="Escoba de Paja">
	                    <h4>Straw Broom</h4>
	                    <p>Traditional broom for efficient sweeping.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$799</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/recogedor.jpg" alt="Recogedor de Basura">
	                    <h4>Garbage Collector</h4>
	                    <p>Sturdy metal garbage collector to go with your broom.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$450</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/trapeador.jpg" alt="Trapeador de Microfibra">
	                    <h4>Trapeador de microfibra</h4>
	                    <p>Removable and washable head for shiny floors.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$1,150</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/cubeta.jpg" alt="Cubeta para Limpieza">
	                    <h4>Bucket with Drainer</h4>
	                    <p>Includes integrated wringer for mops.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$650</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
	                        </button>
	                    </div>
	                </div>
	            </div>
	        </div>
	        
	        <!-- Cleaning Products -->
	        <h3 class="category-header text-info">
	            <span class="glyphicon glyphicon-tint"></span> Chemical Products
	        </h3>
	        <div class="row">
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/cloro.jpg" alt="Cloro Concentrado">
	                    <h4>Concentrated Chlorine</h4>
	                    <p>1 liter bottle for deep disinfection.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$120</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/desengrasante.jpg" alt="Desengrasante Industrial">
	                    <h4>Industrial Degreaser</h4>
	                    <p>Removes tough grease in kitchens and workshops.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$320</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/jabon.jpg" alt="Jabón Líquido Multiusos">
	                    <h4>Multipurpose Soap</h4>
	                    <p>Cleans all types of surfaces without damaging them.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$280</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
	                        </button>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="col-md-3 col-sm-6">
	                <div class="product-card">
	                    <img class="img-responsive product-img center-block" src="img-articulos/aromatizante.jpg" alt="Aromatizante Ambiental">
	                    <h4>Lavender Air Freshener</h4>
	                    <p>Relaxing fragrance for the entire home.</p>
	                    <div class="clearfix">
	                        <span class="price-tag pull-left">$390</span>
	                        <button class="btn btn-success btn-sm pull-right add-to-cart-btn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> Add
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