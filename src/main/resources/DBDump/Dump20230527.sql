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
-- Table structure for table 'answer_option'
--

DROP TABLE IF EXISTS 'answer_option';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE 'answer_option' (
  'id' bigint NOT NULL AUTO_INCREMENT,
  'description' varchar(45) DEFAULT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 'answer_option'
--

LOCK TABLES 'answer_option' WRITE;
/*!40000 ALTER TABLE 'answer_option' DISABLE KEYS */;
INSERT INTO 'answer_option' VALUES (1,'First Option'),(2,'Second Option'),(3,'Third Option'),(4,'Fourth Option');
/*!40000 ALTER TABLE 'answer_option' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 'email'
--

DROP TABLE IF EXISTS 'email';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE 'email' (
  'id' bigint NOT NULL AUTO_INCREMENT,
  'email_address' varchar(255) DEFAULT NULL,
  'license_key' varchar(255) DEFAULT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 'email'
--

LOCK TABLES 'email' WRITE;
/*!40000 ALTER TABLE 'email' DISABLE KEYS */;
/*!40000 ALTER TABLE 'email' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 'latest_score'
--

DROP TABLE IF EXISTS 'latest_score';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE 'latest_score' (
  'add_date' datetime(6) DEFAULT NULL,
  'email_address' varchar(255) NOT NULL,
  'physical_limitation_score' double DEFAULT NULL,
  'quality_of_life_score' double DEFAULT NULL,
  'social_limitation_score' double DEFAULT NULL,
  'summary_score' double DEFAULT NULL,
  'symptom_frequency_score' double DEFAULT NULL,
  PRIMARY KEY ('email_address')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 'latest_score'
--

LOCK TABLES 'latest_score' WRITE;
/*!40000 ALTER TABLE 'latest_score' DISABLE KEYS */;
INSERT INTO 'latest_score' VALUES ('2023-02-09 09:16:28.246000','f@f.com',0,0,0,0,0),('2023-01-16 09:51:40.295000','g@g.com',0,25,25,17.708333,20.833333),('2023-01-16 10:07:20.416000','h@h.com',75,50,50,56.25,50);
/*!40000 ALTER TABLE 'latest_score' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 'license'
--

DROP TABLE IF EXISTS 'license';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE 'license' (
  'license_key' varchar(255) NOT NULL,
  'add_date' datetime(6) DEFAULT NULL,
  'clinic' varchar(255) DEFAULT NULL,
  'email_address' varchar(255) DEFAULT NULL,
  'expiration_date' datetime(6) DEFAULT NULL,
  'location' varchar(255) DEFAULT NULL,
  'pin' varchar(255) DEFAULT NULL,
  'role_id' varchar(255) DEFAULT NULL,
  PRIMARY KEY ('license_key')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 'license'
--

LOCK TABLES 'license' WRITE;
/*!40000 ALTER TABLE 'license' DISABLE KEYS */;
INSERT INTO 'license' VALUES ('B684p29M+Y3Q1k57z0dNgU0LHlLd4/X9G8F0QTSBn48=','2022-12-20 14:21:11.185000','A','b@b.com','2022-12-29 18:00:00.000000','A','$argon2id$v=19$m=15360,t=2,p=1$SJx+DAPE9V5SKNwdXGTtHeN8ICEDSJjkH7nYmePjiWY$O8X34e71nqnU+HWgrkZeFt5SD+/J9zuS2+rjB+s6LwJFx2MMsRyDVqoFkimpzjR2PfUxXxVzY86njBheucPV7g','rol_oPj4WHTzRqcVOMA3'),('lZ3DlK+Xgvy2tioqrn3qnBz9CQlodPB/czdpWBZHcbE=','2022-12-25 14:41:25.246000','TEST1','a@a.com','2022-12-30 18:00:00.000000','SOMETHING1','$argon2id$v=19$m=15360,t=2,p=1$8kto9fz1ceIY1o4c6wRTbbtXooUaOWvYeEP+kquVTkU$yNdFsjJMe1WH66a8GuYyYxFxrRwsGgl/NrcJckEaVV/m2sezzdZVS5miiP2zYESP2g3W0tovYTS+IVFPNdMDig','rol_3VrugOlW82mk1e63'),('MqjBot3tEWkjLPGp2uPl+wKyvPihjo8xX7HQxeFEO+c=','2022-12-28 13:30:51.525000','CLINIC121','e@e.com','2023-01-30 18:00:00.000000','WILDWOOD','$argon2id$v=19$m=15360,t=2,p=1$ThW9rXOF2XMac4yoI1XzG5B8XvlFzhV2LhzIreMiY30$Ybl2AXYg3qEJi18VWIM6i4hTz7Zp2oRVkr28xKkrlswQFWCIiaVD8a3Jjh8xOfR0x4xZNR46cVbiUWdotZGbSQ','rol_G66ER4N4YF80P6bA'),('y8qYXtMK3RpWQT0y687MiSFgHH5QksZyz1JEviMj9tw=','2022-12-27 19:19:09.349000','TEST2','d@d.com','2022-12-30 18:00:00.000000','CHESTERFIELD','$argon2id$v=19$m=15360,t=2,p=1$V7aXo4KCs5Kry64eqTNXcjzbZuMr7Giu2JEAIn6z058$qrXDWzTj+qKBeSfU6fWg2Lc35T+Osm5ALE0i23b+nChR0rNM52rVxcAy/23SVh4UZaLzbDTtBz8bDV+2kbRnUQ','rol_SZTDPo6IK97o8vGn');
/*!40000 ALTER TABLE 'license' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 'question'
--

DROP TABLE IF EXISTS 'question';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE 'question' (
  'question_id' bigint NOT NULL AUTO_INCREMENT,
  'questionaire_type' int DEFAULT NULL,
  'questionaire_section' int DEFAULT NULL,
  'type' int DEFAULT NULL,
  'desc' varchar(6000) DEFAULT NULL,
  'next' int DEFAULT NULL,
  'previous' int DEFAULT NULL,
  PRIMARY KEY ('question_id'),
  KEY 'questionaire_type_idx' ('questionaire_type'),
  KEY 'questionaire_sec_ref_idx' ('questionaire_section'),
  CONSTRAINT 'questionaire_secref' FOREIGN KEY ('questionaire_section') REFERENCES 'questionaire_section' ('section_id'),
  CONSTRAINT 'questionaire_typeref' FOREIGN KEY ('questionaire_type') REFERENCES 'questionaire_type' ('type_id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 'question'
--

LOCK TABLES 'question' WRITE;
/*!40000 ALTER TABLE 'question' DISABLE KEYS */;
INSERT INTO 'question' VALUES (1,1,1,1,'Test Question',2,0);
/*!40000 ALTER TABLE 'question' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 'question_options'
--

DROP TABLE IF EXISTS 'question_options';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE 'question_options' (
  'id' bigint NOT NULL AUTO_INCREMENT,
  'question_id' bigint DEFAULT NULL,
  'option_id' bigint DEFAULT NULL,
  PRIMARY KEY ('id'),
  KEY 'question_ref_idx' ('question_id'),
  KEY 'option_ref_idx' ('option_id'),
  CONSTRAINT 'option_ref' FOREIGN KEY ('option_id') REFERENCES 'answer_option' ('id'),
  CONSTRAINT 'question_ref' FOREIGN KEY ('question_id') REFERENCES 'question' ('question_id')
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 'question_options'
--

LOCK TABLES 'question_options' WRITE;
/*!40000 ALTER TABLE 'question_options' DISABLE KEYS */;
INSERT INTO 'question_options' VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4);
/*!40000 ALTER TABLE 'question_options' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 'questionaire_section'
--

DROP TABLE IF EXISTS 'questionaire_section';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE 'questionaire_section' (
  'section_id' int NOT NULL AUTO_INCREMENT,
  'questionaire_type_ref' int DEFAULT NULL,
  'section_name' varchar(255) DEFAULT NULL,
  'section_desc' varchar(255) DEFAULT NULL,
  'deleted' tinyint DEFAULT NULL,
  'created_date' timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  'updated_date' timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY ('section_id'),
  KEY 'questionaire_type_ref_idx' ('questionaire_type_ref'),
  CONSTRAINT 'questionaire_type_ref' FOREIGN KEY ('questionaire_type_ref') REFERENCES 'questionaire_type' ('type_id')
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 'questionaire_section'
--

LOCK TABLES 'questionaire_section' WRITE;
/*!40000 ALTER TABLE 'questionaire_section' DISABLE KEYS */;
INSERT INTO 'questionaire_section' VALUES (1,1,'physical_score',NULL,0,'2023-05-02 23:32:41','2023-05-02 23:32:41'),(2,1,'quality_of_life',NULL,0,'2023-05-02 23:32:41','2023-05-02 23:32:41'),(3,1,'social_limitation',NULL,0,'2023-05-02 23:32:41','2023-05-02 23:32:41'),(4,1,'symptom_frequency',NULL,0,'2023-05-02 23:32:41','2023-05-02 23:32:41');
/*!40000 ALTER TABLE 'questionaire_section' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 'questionaire_type'
--

DROP TABLE IF EXISTS 'questionaire_type';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE 'questionaire_type' (
  'type_id' int NOT NULL AUTO_INCREMENT,
  'type_name' varchar(255) DEFAULT NULL,
  'type_desc' varchar(1000) DEFAULT NULL,
  'deleted' tinyint DEFAULT NULL,
  'created_date' timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  'updated_date' timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY ('type_id')
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 'questionaire_type'
--

LOCK TABLES 'questionaire_type' WRITE;
/*!40000 ALTER TABLE 'questionaire_type' DISABLE KEYS */;
INSERT INTO 'questionaire_type' VALUES (1,'heart','Questionaire for Heart health',0,'2023-04-30 23:40:02','2023-04-30 23:40:02');
/*!40000 ALTER TABLE 'questionaire_type' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 'summary_score'
--

DROP TABLE IF EXISTS 'summary_score';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE 'summary_score' (
  'id' bigint NOT NULL AUTO_INCREMENT,
  'add_date' datetime(6) DEFAULT NULL,
  'email_address' varchar(255) DEFAULT NULL,
  'physical_limitation_score' double DEFAULT NULL,
  'quality_of_life_score' double DEFAULT NULL,
  'social_limitation_score' double DEFAULT NULL,
  'summary_score' double DEFAULT NULL,
  'symptom_frequency_score' double DEFAULT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 'summary_score'
--

LOCK TABLES 'summary_score' WRITE;
/*!40000 ALTER TABLE 'summary_score' DISABLE KEYS */;
INSERT INTO 'summary_score' VALUES (1,'2022-12-27 19:09:45.396000','c@c.com',75,75,100,82.291667,79.166667),(2,'2022-12-27 19:10:16.521000','c@c.com',100,75,100,86.458333,70.833333),(3,'2022-12-28 13:47:09.031000','f@f.com',100,75,100,88.541667,79.166667),(4,'2022-12-28 16:35:00.130000','f@f.com',25,25,25,23.958333,20.833333),(5,'2022-12-28 16:38:13.526000','f@f.com',75,75,75,73.958333,70.833333),(6,'2023-01-03 00:41:04.624000','f@f.com',25,75,0,35.9375,43.75),(7,'2023-01-14 15:41:31.670000','f@f.com',50,50,41.666667,47.916667,50),(8,'2023-01-14 15:48:00.229000','f@f.com',50,50,41.666667,47.916667,50),(9,'2023-01-14 15:48:39.767000','f@f.com',125,100,125,112.5,100),(10,'2023-01-16 09:51:40.295000','g@g.com',0,25,25,17.708333,20.833333),(11,'2023-01-16 10:07:20.416000','h@h.com',75,50,50,56.25,50),(12,'2023-02-09 09:16:28.246000','f@f.com',0,0,0,0,0);
/*!40000 ALTER TABLE 'summary_score' ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-27  0:05:36
