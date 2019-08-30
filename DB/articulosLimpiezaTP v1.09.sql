CREATE DATABASE  IF NOT EXISTS `articuloslimpiezadb` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `articuloslimpiezadb`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: articuloslimpiezadb
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articulo` (
  `cod_articulo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `cant_a_pedir` int(11) NOT NULL,
  `punto_pedido` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `url_imagen` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`cod_articulo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (1,'Escoba de paja',20,5,10,'img-articulos/escoba-paja.jpg'),(2,'Detergente magistral concentrado de limon 750ml',50,20,34,'img-articulos/detergente-magistral-concentrado-limon.jpg'),(3,'Papel higienico Higienol 4 rollos x 50mts',40,30,70,'img-articulos/papel-higienico-higienol4x50mts.jpg'),(4,'Jabon de mano Dove \"original\" 90gr',15,35,150,'img-articulos/jabon-dove-original-90gr.jpg'),(5,'Esponja ideal para facilitar tu dia a dia',15,35,150,'img-articulos/esponja-acanalada-amarilla-verde.jpg');
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulo_proveedor`
--

DROP TABLE IF EXISTS `articulo_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articulo_proveedor` (
  `cod_articulo` int(11) NOT NULL,
  `cuit` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`cod_articulo`,`cuit`),
  KEY `fk_cuit_proveedor_idx` (`cuit`),
  CONSTRAINT `fk_articulo_proveedor` FOREIGN KEY (`cod_articulo`) REFERENCES `articulo` (`cod_articulo`),
  CONSTRAINT `fk_cuit_proveedor` FOREIGN KEY (`cuit`) REFERENCES `proveedor` (`cuit`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo_proveedor`
--

LOCK TABLES `articulo_proveedor` WRITE;
/*!40000 ALTER TABLE `articulo_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrito`
--

DROP TABLE IF EXISTS `carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `carrito` (
  `nombre` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrito`
--

LOCK TABLES `carrito` WRITE;
/*!40000 ALTER TABLE `carrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cliente` (
  `username` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellido` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dni` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('oscar123','Oscar','Dorronzoro','41360767','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion_fiscal`
--

DROP TABLE IF EXISTS `informacion_fiscal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `informacion_fiscal` (
  `razon_social` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `direccion` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `cuit` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`razon_social`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion_fiscal`
--

LOCK TABLES `informacion_fiscal` WRITE;
/*!40000 ALTER TABLE `informacion_fiscal` DISABLE KEYS */;
INSERT INTO `informacion_fiscal` VALUES ('Doña Mary Limpieza','Molina 2168','+54-0341-15-232323','22-12121212-1');
/*!40000 ALTER TABLE `informacion_fiscal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_carrito`
--

DROP TABLE IF EXISTS `linea_carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `linea_carrito` (
  `nombre_carrito` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cod_articulo` int(11) NOT NULL,
  `cuit_proveedor` varchar(14) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`nombre_carrito`,`cod_articulo`,`cuit_proveedor`),
  KEY `fk_LineaCarrito_articuloProveedor` (`cod_articulo`,`cuit_proveedor`),
  CONSTRAINT `fk_LineaCarrito_articuloProveedor` FOREIGN KEY (`cod_articulo`, `cuit_proveedor`) REFERENCES `articulo_proveedor` (`cod_articulo`, `cuit`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_LineaCarrito_carrito` FOREIGN KEY (`nombre_carrito`) REFERENCES `carrito` (`nombre`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_carrito`
--

LOCK TABLES `linea_carrito` WRITE;
/*!40000 ALTER TABLE `linea_carrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `linea_carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_venta`
--

DROP TABLE IF EXISTS `linea_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `linea_venta` (
  `nro_venta` int(11) NOT NULL,
  `cod_articulo` int(11) NOT NULL,
  `cuit_proveedor` varchar(14) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`nro_venta`,`cod_articulo`,`cuit_proveedor`),
  KEY `fk_lineaVenta_articuloProveedor` (`cod_articulo`,`cuit_proveedor`),
  CONSTRAINT `fk_LineaVenta_articuloProveedor` FOREIGN KEY (`cod_articulo`, `cuit_proveedor`) REFERENCES `articulo_proveedor` (`cod_articulo`, `cuit`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_lineaVenta_Venta` FOREIGN KEY (`nro_venta`) REFERENCES `venta` (`nro_venta`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_venta`
--

LOCK TABLES `linea_venta` WRITE;
/*!40000 ALTER TABLE `linea_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `linea_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio`
--

DROP TABLE IF EXISTS `precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `precio` (
  `cod_articulo` int(11) NOT NULL,
  `fecha_desde` date NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`cod_articulo`,`fecha_desde`),
  CONSTRAINT `fk_precio_articulo` FOREIGN KEY (`cod_articulo`) REFERENCES `articulo` (`cod_articulo`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio`
--

LOCK TABLES `precio` WRITE;
/*!40000 ALTER TABLE `precio` DISABLE KEYS */;
INSERT INTO `precio` VALUES (1,'2019-07-15',300),(2,'2019-07-15',100),(3,'2019-08-14',120),(4,'2019-08-14',55),(5,'2019-08-14',80);
/*!40000 ALTER TABLE `precio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proveedor` (
  `cuit` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `direccion` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`cuit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `venta` (
  `nro_venta` int(11) NOT NULL AUTO_INCREMENT,
  `f_emision` date NOT NULL,
  `f_cancelacion` date DEFAULT NULL,
  `f_pago` date DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `f_retiro` date DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`nro_venta`),
  KEY `ads_idx` (`username`),
  CONSTRAINT `fk_username_cliente` FOREIGN KEY (`username`) REFERENCES `cliente` (`username`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-29 22:13:52
