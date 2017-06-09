-- CREATE DATABASE pleiadian;

-- CREATE USER 'pleiadianuser'@'localhost' IDENTIFIED BY 'pleiadianuserH1Li8';
-- GRANT ALL PRIVILEGES ON *.* TO 'pleiadianuser'@'localhost';


-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: vcluess
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `account_type`
--

DROP TABLE IF EXISTS `account_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_type` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(30) NOT NULL,
  `version` smallint(1) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_type`
--

LOCK TABLES `account_type` WRITE;
/*!40000 ALTER TABLE `account_type` DISABLE KEYS */;
INSERT INTO `account_type` VALUES (1,'Partner',0,1,NULL,'2017-02-24 08:43:57'),(2,'Merchant',0,1,NULL,'2017-02-24 08:43:57'),(3,'Client',0,1,NULL,'2017-02-24 08:43:57'),(4,'User',0,1,NULL,'2017-02-24 08:43:57');
/*!40000 ALTER TABLE `account_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `version` smallint(1) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'ROLE_ANONYMOUS',0,1,NULL,'2017-02-24 08:45:54'),(2,'ROLE_USER',0,1,NULL,'2017-02-24 08:45:54'),(3,'ROLE_ADMIN',0,1,NULL,'2017-02-24 08:45:54');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business`
--

DROP TABLE IF EXISTS `business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `business_key` varchar(100) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `dba` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `industry` varchar(100) DEFAULT NULL,
  `processor_gateway` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `address_2` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `active` smallint(1) DEFAULT '1',
  `version` smallint(1) DEFAULT '0',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business`
--

LOCK TABLES `business` WRITE;
/*!40000 ALTER TABLE `business` DISABLE KEYS */;
INSERT INTO `business` VALUES (1,'RVGzILeTOQyQdFxTRqDr',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,'2017-02-24 08:59:58'),(5,'DLzySOaOdrUccQSBecOU','all business llc','www.khimung.com','all business llc','3108640201','cars','paypal','kim2kim@gmail.com','675 Tasman Dr,, Apt 2108','Apt 2108','sunnyvale','CA','United States','94089',0,1,NULL,'2017-02-27 03:15:40'),(7,'khEdrMfCQeSuNSGybzgw',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,'2017-02-27 03:23:04'),(9,'VihZLsmdfMZVWswwJyAR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,'2017-02-27 03:25:16'),(11,'kAlIKePYdZtYNfiPKvVC','Bob\'s Surf Boards','Bobssurfboards.com','Surf\'s Up','6502703002','Retail','Chase','Roberteng.sn@gmail.com','1 Main St.','','Los Angeles','CA','USA','90210',0,1,NULL,'2017-02-27 03:37:46');
/*!40000 ALTER TABLE `business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `story`
--

DROP TABLE IF EXISTS `story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `story` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  
  `title` varchar(100) DEFAULT NULL,
  `synapsis` varchar(250) DEFAULT NULL,
  `answer` varchar(250) DEFAULT NULL,
  `video` varchar(250),
  `audio` varchar(250),
  `image` varchar(250) DEFAULT NULL,
  `size` int(11) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `version` smallint(1) DEFAULT '0',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
/*!40000 ALTER TABLE `story` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (13),(13),(13),(13),(13),(13);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `scene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scene` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `story_id` bigint(11) NOT NULL,
  `image` varchar(250) NULL,
  `text` varchar(250) NULL,
  `sound` varchar(250) NULL,
  `position` int(11) DEFAULT '0',
  `video` varchar(100),
  `audio` varchar(100),
  `image` varchar(100) DEFAULT NULL,  
  `active` smallint(1) DEFAULT '1',
  `version` smallint(1) DEFAULT '0',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

	
--
-- Dumping data for table `scene`
--

LOCK TABLES `scene` WRITE;
/*!40000 ALTER TABLE `scene` DISABLE KEYS */;
/*!40000 ALTER TABLE `scene` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `script`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `script` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `scene_id` bigint(11) NOT NULL,
  `cast_id` bigint(11) NOT NULL,\
  `text` varchar(250) NULL,
  `sound` varchar(100) NULL,
  `secret` varchar(250) NULL,
  `video` varchar(250),
  `audio` varchar(250),
  `image` varchar(250) DEFAULT NULL,  
  `position` int(11) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `version` smallint(1) DEFAULT '0',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `hint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hint` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `scene_id` bigint(11) NOT NULL,
  `text` varchar(250) NULL,
  `position` int(11) DEFAULT '0',
  `video` varchar(250),
  `audio` varchar(250),
  `image` varchar(250) DEFAULT NULL,  
  `active` smallint(1) DEFAULT '1',
  `version` smallint(1) DEFAULT '0',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `cast`
--

DROP TABLE IF EXISTS `cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cast` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `story_id` bigint(11) NOT NULL,
  `role` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `description` varchar(250) NOT NULL,
  `video` varchar(250),
  `audio` varchar(250),
  `image` varchar(250) DEFAULT NULL,  
  `position` int(11) default '0',
  `version` smallint(1) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

	
--
-- Dumping data for table `cast`
--

LOCK TABLES `cast` WRITE;
/*!40000 ALTER TABLE `cast` DISABLE KEYS */;
-- INSERT INTO `cast` VALUES (1,'OPEN',0,1,NULL,'2017-02-24 08:43:57'),(2,'PENDING',0,1,NULL,'2017-02-24 08:43:57'),(3,'IN_PROGRESS',0,1,NULL,'2017-02-24 08:43:57'),(4,'RESOLVED',0,1,NULL,'2017-02-24 08:43:57'),(5,'DECLINED',0,1,NULL,'2017-02-24 08:43:57'),(6,'COMPLETED',0,1,NULL,'2017-02-24 08:43:57');
/*!40000 ALTER TABLE `cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `permissions` bigint(11) DEFAULT '0',
  `phone` varchar(15) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `activated` smallint(1) DEFAULT '0',
  `activation_key` varchar(40) DEFAULT NULL,
  `reset_password_key` varchar(40) DEFAULT NULL,
  `authorities` varchar(40) DEFAULT NULL,
  `version` smallint(1) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `account_type` varchar(255) DEFAULT NULL,
  `parent_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,1,4095,NULL,'khim','ung','kim2kim@gmail.com','$2a$10$gySpXxWzGqdgVD08v0IRSOCY9GdJKnrbbtLAOMIx0.sp3GOKOImCu',1,'oAuPjfdhJlHnTNfKSZkB',NULL,NULL,0,1,NULL,'2017-02-24 08:59:58','Partner',NULL),(3,1,292,NULL,NULL,NULL,'khim.ung@yahoo.com','$2a$10$AP3Wa0dLoDpgYE.3Lc5DA.sHwZyn7PEUogepXFx4j.l36.EU95eBG',0,NULL,NULL,NULL,0,0,NULL,'2017-02-26 12:45:28','User',NULL),(4,1,292,NULL,NULL,NULL,'khimung.collabtive@gmail.com','$2a$10$/iIWFc9Dlyu33B7ELzJbG.2qGRVdieKUE.bZg8T2qb4L6j4irBM5O',1,NULL,NULL,NULL,1,0,NULL,'2017-02-26 12:45:38','User',NULL),(6,5,4095,NULL,NULL,NULL,'genieloungecafe@gmail.com','$2a$10$fxmSmPDAW05Zq1dp2inv0uHPHgpP6PV5NEMcjth3ZduSR8x3OZwsW',1,'mSOoGEQKbPDpFpziDKHS',NULL,NULL,1,0,NULL,'2017-02-27 03:15:09','Partner',NULL),(8,7,4095,NULL,NULL,NULL,'guptamanav@gmail.com','$2a$10$p73EzT2hmIyRIqZolcoOlupD0TcVM.xPeeohFZW7xx2n8hHAt95N2',1,'pwdqOCDEOoXaOahxkCSf',NULL,NULL,1,0,NULL,'2017-02-27 03:23:04','User',NULL),(10,9,4095,NULL,NULL,NULL,'mgupta249@gmail.com','$2a$10$TG1fPWDLZa3anWMDgf.pbuS3HC4i1RIkcCw.X7WPFfVeKfCQOPa02',1,'daMIshdCIRRgsjJZSjxC',NULL,NULL,1,0,NULL,'2017-02-27 03:25:16','Partner',NULL),(12,11,4095,NULL,NULL,NULL,'Roberteng.sn@gmail.com','$2a$10$CnYjDRTvIUhi2Dw9zOz3/undqpuOxQLZTcXgPI7FdvJne8wKB9TDy',0,'JWTRtNbFXaSXMmqaArqL',NULL,NULL,0,0,NULL,'2017-02-27 03:32:30','Merchant',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_authority` (
  `email` bigint(11) NOT NULL,
  `authority` bigint(10) NOT NULL,
  PRIMARY KEY (`email`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES (2,3),(3,2),(4,2),(6,2),(8,2),(10,3),(12,2);
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-26 22:50:49
