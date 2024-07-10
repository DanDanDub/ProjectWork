CREATE DATABASE  IF NOT EXISTS `project_work` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `project_work`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: project_work
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `UKqsstlki7ni5ovaariyy9u8y79` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Napoli','Napoli_pw'),(2,'Salerno','Salerno_pw');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `id_city` int(32) NOT NULL,
  `id_district` int(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`id_city`,`id_district`) USING BTREE,
  UNIQUE KEY `UKha32et0t80ccb16aav72sklm5` (`name`,`id_city`,`id_district`),
  KEY `device_city` (`id_city`),
  KEY `device_district` (`id_district`),
  CONSTRAINT `device_city` FOREIGN KEY (`id_city`) REFERENCES `city` (`id`),
  CONSTRAINT `device_district` FOREIGN KEY (`id_district`) REFERENCES `district` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,1,1,'Device_001'),(2,2,2,'Device_001'),(7,2,3,'Device_001'),(6,2,2,'Device_002'),(8,2,3,'Device_002'),(9,2,3,'Device_003');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `district` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `id_city` int(32) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_city` (`id_city`,`address`) USING BTREE,
  UNIQUE KEY `UKkiw3v9mt2elb6tb5h43qqdr5d` (`id_city`,`address`),
  CONSTRAINT `district_city` FOREIGN KEY (`id_city`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,1,'Mugnano'),(2,2,'Acerno'),(3,2,'Nocera Superiore');
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `overview_v1`
--

DROP TABLE IF EXISTS `overview_v1`;
/*!50001 DROP VIEW IF EXISTS `overview_v1`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `overview_v1` AS SELECT 
 1 AS `city`,
 1 AS `city_id`,
 1 AS `district`,
 1 AS `district_id`,
 1 AS `device`,
 1 AS `device_id`,
 1 AS `status_id`,
 1 AS `timestamp`,
 1 AS `co2_level`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_city` int(32) NOT NULL,
  `id_district` int(32) NOT NULL,
  `id_device` int(32) NOT NULL,
  `timestamp` datetime(6) NOT NULL,
  `co2_level` int(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `status_city` (`id_city`),
  KEY `status_device` (`id_device`),
  KEY `status_district` (`id_district`),
  CONSTRAINT `status_city` FOREIGN KEY (`id_city`) REFERENCES `city` (`id`),
  CONSTRAINT `status_device` FOREIGN KEY (`id_device`) REFERENCES `device` (`id`),
  CONSTRAINT `status_district` FOREIGN KEY (`id_district`) REFERENCES `district` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,2,2,2,'2024-07-10 11:37:35.000000',105),(2,2,2,2,'2024-07-10 11:56:16.000000',103),(3,2,2,2,'2024-07-10 12:00:13.000000',103),(4,2,2,2,'2024-07-10 12:00:42.000000',104),(5,1,1,1,'2024-07-10 12:06:14.000000',104),(6,1,1,1,'2024-07-10 12:06:20.000000',123),(7,1,1,1,'2024-07-10 12:06:28.000000',159),(10,2,3,8,'2024-07-10 12:07:58.000000',1529),(11,2,3,8,'2024-07-10 12:08:01.000000',1521),(12,2,3,8,'2024-07-10 12:08:04.000000',1522),(13,2,3,9,'2024-07-10 12:08:09.000000',23),(14,2,3,9,'2024-07-10 12:08:11.000000',235);
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'project_work'
--

--
-- Dumping routines for database 'project_work'
--
/*!50003 DROP PROCEDURE IF EXISTS `CLEAR_ALL` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`pw_user`@`%` PROCEDURE `CLEAR_ALL`()
BEGIN
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE `status`;
TRUNCATE `device`;
TRUNCATE `district`;
TRUNCATE `city`;

SET FOREIGN_KEY_CHECKS = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `overview_v1`
--

/*!50001 DROP VIEW IF EXISTS `overview_v1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`pw_user`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `overview_v1` AS select `city`.`name` AS `city`,`city`.`id` AS `city_id`,`district`.`address` AS `district`,`district`.`id` AS `district_id`,`device`.`name` AS `device`,`device`.`id` AS `device_id`,`status`.`id` AS `status_id`,`status`.`timestamp` AS `timestamp`,`status`.`co2_level` AS `co2_level` from (((`status` left join `city` on(`city`.`id` = `status`.`id_city`)) left join `district` on(`district`.`id` = `status`.`id_district`)) left join `device` on(`device`.`id` = `status`.`id_device`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-10 12:17:03
