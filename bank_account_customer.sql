CREATE DATABASE  IF NOT EXISTS `bank_account` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bank_account`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: bank_account
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `identification` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `UKrhpv87q3057rohwm2cawtfr3e` (`identification`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'456 New St',31,'Male','01234567','John Doe Updated','+1234567891','newsecurepassword22',_binary '\0'),(2,'123 Main St',30,'Male','012345678','John Doe','1234567890','password123',_binary '\0'),(3,'123 Main St',30,'Male','0123456789','John Doe','1234567890','password123',_binary ''),(4,'123 Main St',30,'Male','0123456788','John Doe','1234567890','password123',_binary ''),(5,'123 Main St',30,'Male','0123456787','John Doe','1234567890','password123',_binary ''),(6,'456 New St',31,'Male','01234567000','John Doe Updated','+1234567891','$2a$10$PU6VDs0n1WvrETX1MwpjW.NIcpfZxSjxf9LXkDN/ngR7RujWE/B0e',_binary '\0'),(7,'123 Main St',30,'Male','0123456785','John Doe','1234567890','password123',_binary ''),(8,'123 Main St',30,'Male','0123456784','John Doe','1234567890','$2a$10$cPyAbJ10o4BDcQZDpFfB4OlxIX1lSB1XCwMBB/vYAGwqRiviVPOJC',_binary ''),(9,'123 Main St',30,'Male','0123456780','John Doe','1234567890','$2a$10$E1YCCN5xugiN4cD4ViuRtukFlVhY8CfM2dszGZhRZiDDMOvried7K',_binary ''),(10,'123 Main St',30,'Male','0123456782','John Doe','1234567890','$2a$10$ll3qWlJikFnGPLWNpd/nw.xThPbsMbNy.HGxCbFzh7LmvP0g7MHI.',_binary ''),(11,'123 Main St',30,'Male','012345600','John Doe','1234567890','$2a$10$PF6X5kpXqlcNL0dC9QRSvuQqTr1SZe5F7kDMYfj6T9lJfPhKRBRsm',_binary ''),(12,'123 Main St',30,'Male','012345610','John Doe','1234567890','$2a$10$8/FFcoa6Y3I52r4vdHjPfOCFwEIaMXo43Z.VmRvyrm1MQnXg2c95a',_binary ''),(13,'123 Main St',30,'Male','012345611','John Doe','1234567890','$2a$10$.d6jhnP3GlwFPtTI0WgUx.UGFtsd69awSBjpQYjdOYuM8E90bz68G',_binary '');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-16 18:13:29
