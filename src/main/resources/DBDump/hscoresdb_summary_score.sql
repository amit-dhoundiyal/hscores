-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hscoresdb
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `summary_score`
--

DROP TABLE IF EXISTS `summary_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `summary_score` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `add_date` datetime(6) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `physical_limitation_score` double DEFAULT NULL,
  `quality_of_life_score` double DEFAULT NULL,
  `social_limitation_score` double DEFAULT NULL,
  `summary_score` double DEFAULT NULL,
  `symptom_frequency_score` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summary_score`
--

LOCK TABLES `summary_score` WRITE;
/*!40000 ALTER TABLE `summary_score` DISABLE KEYS */;
INSERT INTO `summary_score` VALUES (1,'2022-12-27 19:09:45.396000','c@c.com',75,75,100,82.291667,79.166667),(2,'2022-12-27 19:10:16.521000','c@c.com',100,75,100,86.458333,70.833333),(3,'2022-12-28 13:47:09.031000','f@f.com',100,75,100,88.541667,79.166667),(4,'2022-12-28 16:35:00.130000','f@f.com',25,25,25,23.958333,20.833333),(5,'2022-12-28 16:38:13.526000','f@f.com',75,75,75,73.958333,70.833333),(6,'2023-01-03 00:41:04.624000','f@f.com',25,75,0,35.9375,43.75),(7,'2023-01-14 15:41:31.670000','f@f.com',50,50,41.666667,47.916667,50),(8,'2023-01-14 15:48:00.229000','f@f.com',50,50,41.666667,47.916667,50),(9,'2023-01-14 15:48:39.767000','f@f.com',125,100,125,112.5,100),(10,'2023-01-16 09:51:40.295000','g@g.com',0,25,25,17.708333,20.833333),(11,'2023-01-16 10:07:20.416000','h@h.com',75,50,50,56.25,50),(12,'2023-02-09 09:16:28.246000','f@f.com',0,0,0,0,0);
/*!40000 ALTER TABLE `summary_score` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-06 22:52:26
