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
-- Table structure for table `questionaire_section`
--

DROP TABLE IF EXISTS `questionaire_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionaire_section` (
  `section_id` int NOT NULL AUTO_INCREMENT,
  `questionaire_type_ref` int DEFAULT NULL,
  `section_name` varchar(255) DEFAULT NULL,
  `section_desc` varchar(255) DEFAULT NULL,
  `deleted` tinyint DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`section_id`),
  KEY `questionaire_type_ref_idx` (`questionaire_type_ref`),
  CONSTRAINT `questionaire_type_ref` FOREIGN KEY (`questionaire_type_ref`) REFERENCES `questionaire_type` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionaire_section`
--

LOCK TABLES `questionaire_section` WRITE;
/*!40000 ALTER TABLE `questionaire_section` DISABLE KEYS */;
INSERT INTO `questionaire_section` VALUES (1,1,'physical_score',NULL,0,'2023-05-02 23:32:41','2023-05-02 23:32:41'),(2,1,'quality_of_life',NULL,0,'2023-05-02 23:32:41','2023-05-02 23:32:41'),(3,1,'social_limitation',NULL,0,'2023-05-02 23:32:41','2023-05-02 23:32:41'),(4,1,'symptom_frequency',NULL,0,'2023-05-02 23:32:41','2023-05-02 23:32:41');
/*!40000 ALTER TABLE `questionaire_section` ENABLE KEYS */;
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
