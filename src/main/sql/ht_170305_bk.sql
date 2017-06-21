CREATE DATABASE  IF NOT EXISTS `security_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `security_db`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: security_db
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `user_role_details_table`
--

DROP TABLE IF EXISTS `user_role_details_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_details_table` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ROLE_UNIQUE` (`USER_ID`,`ROLE_ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `user_role_table` (`ID`),
  CONSTRAINT `USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user_table` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_details_table`
--

LOCK TABLES `user_role_details_table` WRITE;
/*!40000 ALTER TABLE `user_role_details_table` DISABLE KEYS */;
INSERT INTO `user_role_details_table` VALUES (1,1,1),(10,2,1),(2,2,2),(3,2,3),(4,2,4),(9,3,1),(6,4,1);
/*!40000 ALTER TABLE `user_role_details_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_table`
--

DROP TABLE IF EXISTS `user_role_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_table` (
  `ID` int(11) NOT NULL,
  `ROLE_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`ROLE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_table`
--

LOCK TABLES `user_role_table` WRITE;
/*!40000 ALTER TABLE `user_role_table` DISABLE KEYS */;
INSERT INTO `user_role_table` VALUES (1,'ADMIN'),(3,'DIRECTOR'),(2,'MANAGER'),(4,'USER');
/*!40000 ALTER TABLE `user_role_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_table`
--

DROP TABLE IF EXISTS `user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_table` (
  `ID` int(11) NOT NULL,
  `USER_NAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_table`
--

LOCK TABLES `user_table` WRITE;
/*!40000 ALTER TABLE `user_table` DISABLE KEYS */;
INSERT INTO `user_table` VALUES (1,'admin','admin'),(2,'haho','hoanhhao'),(3,'hao','hiep'),(4,'sd','hiep'),(6,'admin1','admin'),(7,'admin2','admin'),(8,'admin3','admin'),(9,'admin4','admin'),(12,'haho1','hoanhhao'),(13,'haho13','hoanhhao'),(14,'haho14','hoanhhao'),(15,'haho15','hoanhhao'),(16,'haho16','hoanhhao'),(17,'haho17','hoanhhao'),(18,'haho18','hoanhhao'),(19,'haho19','hoanhhao'),(20,'haho20','hoanhhao'),(21,'haho21','hoanhhao'),(22,'haho22','hoanhhao'),(23,'haho23','hoanhhao'),(24,'haho24','hoanhhao'),(25,'haho25','hoanhhao'),(26,'haho26','hoanhhao'),(27,'haho27','hoanhhao'),(28,'haho28','hoanhhao'),(29,'haho29','hoanhhao'),(30,'haho30','hoanhhao'),(31,'haho31','hoanhhao'),(32,'haho32','hoanhhao'),(33,'haho33','hoanhhao'),(34,'haho34','hoanhhao'),(36,'haho36','hoanhhao'),(37,'haho37','hoanhhao'),(38,'haho38','hoanhhao'),(41,'41','hoanhhao'),(42,'42','hoanhhao'),(46,'46','hoanhhao');
/*!40000 ALTER TABLE `user_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-05  7:39:01
