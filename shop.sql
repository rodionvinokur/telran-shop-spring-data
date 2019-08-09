-- MySQL dump 10.16  Distrib 10.3.9-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	10.3.9-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES ('d420c50f-b3a8-490e-afa6-04515975169f','Leptop'),('f19936ba-b708-4b6a-a0e8-28e5dd25cbfd','PC'),('e006b8a1-7b06-47d4-a567-ae045ffb0bfe','Phone');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` varchar(255) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKml4dss6fai1c8icav9hoq47o8` (`owner_id`),
  CONSTRAINT `FKml4dss6fai1c8icav9hoq47o8` FOREIGN KEY (`owner_id`) REFERENCES `users` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('c3952067-970a-4976-a764-c3c326df0272','2019-08-03 18:18:45.000000','DONE','vasya@mail.com'),('c4e64c3b-5635-4c24-895f-eaa380947f17','2019-08-03 11:57:58.000000','DONE','vasya@mail.com');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_order`
--

DROP TABLE IF EXISTS `product_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_order` (
  `id` varchar(255) NOT NULL,
  `count` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `shoping_cart_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo7dl8saqqnhuc1e5pkoabjr1a` (`category_id`),
  KEY `FKjwsik4uvq2sdqtb7x6h1o5f0v` (`order_id`),
  KEY `FKanm84fvvnojr4batohvdqhixw` (`shoping_cart_id`),
  CONSTRAINT `FKanm84fvvnojr4batohvdqhixw` FOREIGN KEY (`shoping_cart_id`) REFERENCES `shopping_carts` (`id`),
  CONSTRAINT `FKjwsik4uvq2sdqtb7x6h1o5f0v` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKo7dl8saqqnhuc1e5pkoabjr1a` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_order`
--

LOCK TABLES `product_order` WRITE;
/*!40000 ALTER TABLE `product_order` DISABLE KEYS */;
INSERT INTO `product_order` VALUES ('88388bb3-2cad-401a-a396-36e5bb73c1a8',2,'MacBook Pro i9',8700.00,'58960bc0-4c90-4160-bf30-85a65a7c25d0','f19936ba-b708-4b6a-a0e8-28e5dd25cbfd','c3952067-970a-4976-a764-c3c326df0272',NULL),('fc15ecff-b0bf-4f16-a786-294a8299146d',3,'Samsumg S10+',3000.00,'238ab7eb-a6fe-42fc-9356-b9cea386462e','e006b8a1-7b06-47d4-a567-ae045ffb0bfe','c4e64c3b-5635-4c24-895f-eaa380947f17',NULL);
/*!40000 ALTER TABLE `product_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('238ab7eb-a6fe-42fc-9356-b9cea386462e','Samsumg S10+',3000.00,'e006b8a1-7b06-47d4-a567-ae045ffb0bfe'),('58960bc0-4c90-4160-bf30-85a65a7c25d0','MacBook Pro i9',8700.00,'f19936ba-b708-4b6a-a0e8-28e5dd25cbfd');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_carts`
--

DROP TABLE IF EXISTS `shopping_carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopping_carts` (
  `id` varchar(255) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_carts`
--

LOCK TABLES `shopping_carts` WRITE;
/*!40000 ALTER TABLE `shopping_carts` DISABLE KEYS */;
INSERT INTO `shopping_carts` VALUES ('e9cb3869-9868-4899-a359-1042e3f8c87d','2019-08-03 11:54:03.000000');
/*!40000 ALTER TABLE `shopping_carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES ('admin@mail.com','$2a$10$bHBvXSAFW1kPuCMKzMsC0.w1gMm4SwA0D6vPCpxxdRdFdzQJD2He2'),('misha@mail.com','$2a$10$Ea/MAR/gx8kBteTlQDivaufSTR4BNny4UfBgcrt7WWQsYNYjuSIA.'),('vasya@mail.com','$2a$10$soAUHgADcndxf3wlRHuOIOvOy7iF.YKtMWrs37cSnbEUHgvZLZnRe');
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `email` varchar(255) NOT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `shopping_cart_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `UK_du5v5sr43g5bfnji4vb8hg5s3` (`phone`),
  KEY `FKpit3woesw8x1062syim3kei69` (`shopping_cart_id`),
  CONSTRAINT `FKpit3woesw8x1062syim3kei69` FOREIGN KEY (`shopping_cart_id`) REFERENCES `shopping_carts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('vasya@mail.com',3600.00,'Vasya','05555555','e9cb3869-9868-4899-a359-1042e3f8c87d');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_email` varchar(255) NOT NULL,
  `user_role` varchar(255) NOT NULL,
  KEY `FKdphtr6edh8xot9y8hr6227ldu` (`user_role`),
  KEY `FK8blj61sja4vp8e1m8ddx0guyu` (`user_email`),
  CONSTRAINT `FK8blj61sja4vp8e1m8ddx0guyu` FOREIGN KEY (`user_email`) REFERENCES `user_details` (`email`),
  CONSTRAINT `FKdphtr6edh8xot9y8hr6227ldu` FOREIGN KEY (`user_role`) REFERENCES `roles` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES ('admin@mail.com','ROLE_ADMIN'),('vasya@mail.com','ROLE_USER'),('misha@mail.com','ROLE_USER');
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-04 16:29:07
