DROP DATABASE IF EXISTS `cleaning_supplies`;
CREATE DATABASE `cleaning_supplies` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cleaning_supplies`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


--
-- Table structure for table `articles` -----------------------------------------------------------
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articles` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount_to_order` int(11) NOT NULL,
  `order_limit` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `category_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `idx_category_in_articles` (`category_name`),
  CONSTRAINT `fk_articles_to_category` FOREIGN KEY (`category_name`) REFERENCES `categories` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` VALUES 
	(1,'Straw broom',20,5,10,'img-articles/escoba-paja.jpg','General',0)
	,(2,'Concentrated lemon magistral detergent 750ml',50,20,34,'img-articles/detergente-magistral-concentrado-limon.jpg','Kitchen',0)
	,(3,'Higienol toilet paper 4 rolls x 50 meters',40,30,70,'img-articles/papel-higienico-higienol4x50mts.jpg','Bathroom',0)
	,(4,'Dove hand soap 90gr',15,35,50,'img-articles/jabon-dove-original-90gr.jpg','Bathroom',0)
	,(5,'Ideal sponge to facilitate your daily life',15,35,50,'img-articles/esponja-acanalada-amarilla-verde.jpg','Kitchen',0)
  ,(6,'Wisky glass x6 pack', 50, 20, 31, 'img-articles/Vaso-de-whisky-x6.jpg', 'General', 0);
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `articles_providers` ----------------------------------------------------
--

DROP TABLE IF EXISTS `articles_providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articles_providers` (
  `article_code` int(11) NOT NULL,
  `cuit` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`article_code`,`cuit`),
  KEY `idx_provider_in_artpro` (`cuit`),
  CONSTRAINT `fk_artpro_to_articles` FOREIGN KEY (`article_code`) REFERENCES `articles` (`code`),
  CONSTRAINT `fk_artpro_to_providers` FOREIGN KEY (`cuit`) REFERENCES `providers` (`cuit`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles_providers`
--

LOCK TABLES `articles_providers` WRITE;
/*!40000 ALTER TABLE `articles_providers` DISABLE KEYS */;
INSERT INTO `articles_providers` VALUES
  (1,'11-11111111-1')
  ,(2,'11-11111111-1')
  ,(3,'11-11111111-1')
  ,(4,'11-11111111-1')
  ,(5,'11-11111111-1');
/*!40000 ALTER TABLE `articles_providers` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `carts` ----------------------------------------------------------
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`name`,`username`),
  KEY `idx_customer_in_carts` (`username`),
  CONSTRAINT `fk_carts_to_customers` FOREIGN KEY (`username`) REFERENCES `customers` (`username`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES
  ('currentPurchase','oscar123','Here there are articles added on last session')
	,('favorites', 'oscar123', 'Favorites articles')
  ,('wishList', 'oscar123', 'Articles that you want to buy')
  ,('budget', 'oscar123', 'Saved articles to calc budget')
  
  ,('currentPurchase','admin','Here there are articles added on last session')
	,('favorites', 'admin', 'Favorites articles')
  ,('wishList', 'admin', 'Articles that you want to buy')
  ,('budget', 'admin', 'Saved articles to calc budget')
  
  ,('currentPurchase','user1','Here there are articles added on last session')
	,('favorites', 'user1', 'Favorites articles')
  ,('wishList', 'user1', 'Articles that you want to buy')
  ,('budget', 'user1', 'Saved articles to calc budget')
  
  ,('currentPurchase','user2','Here there are articles added on last session')
	,('favorites', 'user2', 'Favorites articles')
  ,('wishList', 'user2', 'Articles that you want to buy')
  ,('budget', 'user2', 'Saved articles to calc budget');

/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `cart_lines` ----------------------------------------------------
--

DROP TABLE IF EXISTS `cart_lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_lines` (
  `cart_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `article_code` int(11) NOT NULL,
  `provider_cuit` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`cart_name`,`article_code`,`provider_cuit`,`username`),
  KEY `idx_artpro_in_cartlines` (`article_code`,`provider_cuit`),
  KEY `idx_cart_in_cartlines` (`cart_name`,`username`),
  CONSTRAINT `fk_cartlines_to_artpro` FOREIGN KEY (`article_code`, `provider_cuit`) REFERENCES `articles_providers` (`article_code`, `cuit`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cartlines_to_carts` FOREIGN KEY (`cart_name`, `username`) REFERENCES `carts` (`name`, `username`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_lines`
--

LOCK TABLES `cart_lines` WRITE;
/*!40000 ALTER TABLE `cart_lines` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_lines` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `categories` ----------------------------------------------------
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES
  ('Bathroom','Articles dedicated to cleaning and scenting bathrooms')
  ,('Kitchen','Items to easily clean kitchen grease')
  ,('General','General cleaning supplies')
  ,('Furniture','Furniture care items');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `customers` -----------------------------------------------------
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `dni` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `unq_customers_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES 
  ('user1','User1','User1','11111111','1000:e6cca869536387846ca210ebe471f09b:7cca449a410be93d22094a991daae8b09f49c16e15a81a9aa279f7324098bae26a6681abcacc4c9008369ca7c3d7c18bcae25d54882cb09bc678f0b1894089a5',0,'user1@doniamary.com'),
  ('user2','User2','User2','22222222','1000:e6cca869536387846ca210ebe471f09b:7cca449a410be93d22094a991daae8b09f49c16e15a81a9aa279f7324098bae26a6681abcacc4c9008369ca7c3d7c18bcae25d54882cb09bc678f0b1894089a5',0,'user2@doniamary.com'),
  ('admin','Admin', 'Istrator','66666666','1000:e6cca869536387846ca210ebe471f09b:7cca449a410be93d22094a991daae8b09f49c16e15a81a9aa279f7324098bae26a6681abcacc4c9008369ca7c3d7c18bcae25d54882cb09bc678f0b1894089a5',1,'admin@doniamary.com'),
  ('oscar123','Oscar','Dorronzoro','12345678','1000:e6cca869536387846ca210ebe471f09b:7cca449a410be93d22094a991daae8b09f49c16e15a81a9aa279f7324098bae26a6681abcacc4c9008369ca7c3d7c18bcae25d54882cb09bc678f0b1894089a5',1,'oscar@doniamary.com');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `sales` -----------------------------------------------
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `sale_number` int(11) NOT NULL AUTO_INCREMENT,
  `emission_date` datetime NOT NULL,
  `cancellation_date` datetime DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `sale_amount` double DEFAULT NULL,
  `withdrawal_date` datetime DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`sale_number`),
  KEY `idx_customer_in_sales` (`username`),
  CONSTRAINT `fk_sales_to_customers` FOREIGN KEY (`username`) REFERENCES `customers` (`username`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES
  (5,'2024-12-10 00:00:00',NULL,NULL,6000,NULL,'oscar123')
  ,(6,'2024-12-11 19:23:12',NULL,'2024-12-12 19:25:00',12000,'2024-12-12 19:25:00','oscar123');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `sale_lines` -------------------------------------------------
--

DROP TABLE IF EXISTS `sale_lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_lines` (
  `sale_number` int(11) NOT NULL,
  `article_code` int(11) NOT NULL,
  `provider_cuit` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`sale_number`,`article_code`,`provider_cuit`),
  KEY `fk_lineasales_articleprovider` (`article_code`,`provider_cuit`),
  CONSTRAINT `fk_Lineasales_articleprovider` FOREIGN KEY (`article_code`, `provider_cuit`) REFERENCES `articles_providers` (`article_code`, `cuit`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_lineasales_sales` FOREIGN KEY (`sale_number`) REFERENCES `sales` (`sale_number`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_lines`
--

LOCK TABLES `sale_lines` WRITE;
/*!40000 ALTER TABLE `sale_lines` DISABLE KEYS */;
INSERT INTO `sale_lines` VALUES
  (5, 1, '11-11111111-1', 2)
  ,(6, 1, '11-11111111-1', 4);
/*!40000 ALTER TABLE `sale_lines` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `prices` -----------------------------------------------------
--

DROP TABLE IF EXISTS `prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prices` (
  `article_code` int(11) NOT NULL,
  `date_from` datetime NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`article_code`,`date_from`),
  CONSTRAINT `fk_prices_to_articles` FOREIGN KEY (`article_code`) REFERENCES `articles` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES `prices` WRITE;
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` VALUES
  (1,'2019-07-15 12:00:00',300)
  ,(2,'2019-07-15 12:00:00',100)
  ,(3,'2019-08-14 12:00:00',120)
  ,(4,'2019-08-14 12:00:00',55)
  ,(5,'2019-08-14 12:00:00',80)
  ,(1,'2024-10-01 12:00:00',3000)
  ,(2,'2024-10-01 12:00:00',1000)
  ,(3,'2024-10-01 12:00:00',1200)
  ,(4,'2024-10-01 12:00:00',900)
  ,(5,'2024-10-01 12:00:00',500)
  ,(6,'2025-04-29 00:00:00',2800);
/*!40000 ALTER TABLE `prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `providers`
--

DROP TABLE IF EXISTS `providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `providers` (
  `cuit` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `business_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone_number` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`cuit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `providers`
--

LOCK TABLES `providers` WRITE;
/*!40000 ALTER TABLE `providers` DISABLE KEYS */;
INSERT INTO `providers` VALUES
  ('11-11111111-1','rioja 1111','Super Cleaning','341-111111','super_limpieza@gmail.com')
  ,('22-22222222-2','Mitre 2000','Pure chemistry','341-222222',NULL);
/*!40000 ALTER TABLE `providers` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `fiscal_information` --------------------------------------------------
--

DROP TABLE IF EXISTS `fiscal_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fiscal_information` (
  `business_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `cuit` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`business_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiscal_information`
--

LOCK TABLES `fiscal_information` WRITE;
/*!40000 ALTER TABLE `fiscal_information` DISABLE KEYS */;
INSERT INTO `fiscal_information` VALUES
  ('Mrs. Mary Cleaning','Siempre Viva Avenue 123','+54-0341-15-232323','22-12121212-1');
/*!40000 ALTER TABLE `fiscal_information` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

