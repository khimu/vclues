-- MySQL dump 10.13  Distrib 5.5.25, for osx10.6 (i386)
--
-- Host: localhost    Database: vclues
-- ------------------------------------------------------
-- Server version	5.5.25-log

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

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `vclues` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `vclues`;


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
-- Table structure for table `balance`
--

DROP TABLE IF EXISTS `balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `balance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `amount` decimal(50,20) NOT NULL,
  `earned` decimal(50,20) NOT NULL,
  `spent` decimal(50,20) NOT NULL,
  `last_cash_out_amount` decimal(50,20) NOT NULL,
  `billable` decimal(50,20) NOT NULL,
  `last_billed_date` datetime DEFAULT NULL,
  `last_cash_out_date` datetime DEFAULT NULL,
  `transaction_count` int(200) DEFAULT '0',
  `spent_count` int(200) DEFAULT '0',
  `earned_count` int(200) DEFAULT '0',
  `list_item_count` int(200) DEFAULT '0',
  `version` smallint(1) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `balance_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balance`
--

LOCK TABLES `balance` WRITE;
/*!40000 ALTER TABLE `balance` DISABLE KEYS */;
/*!40000 ALTER TABLE `balance` ENABLE KEYS */;
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
  `description` text,
  `version` smallint(1) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `position` int(11) DEFAULT NULL,
  `video` varchar(250) DEFAULT NULL,
  `audio` varchar(250) DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cast_idx` (`story_id`),
  CONSTRAINT `FKh304bcalk1knemawxkrwjvvs0` FOREIGN KEY (`story_id`) REFERENCES `story` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cast`
--

LOCK TABLES `cast` WRITE;
/*!40000 ALTER TABLE `cast` DISABLE KEYS */;
INSERT INTO `cast` VALUES (92,19,'Model','Holly Harrison','Female','<ul><li>Tyler\'s girl friend.  A fashion model who wants to unseat kim kardashian</li></ul>',0,0,NULL,'2017-08-08 08:57:22',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_001_FADED.png'),(93,19,'Victim','Boris Polenko','Male','The victim.  A russian billionaire olygarch who financed arms and movies',0,0,NULL,'2017-08-08 08:59:30',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_004_FADED.png'),(94,19,'Captain','AnneBelle Hodges','Female','The stern but an accomplished ships captain.  also Boris\' ex-lover',0,0,NULL,'2017-08-08 09:01:13',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_011_FADED.png'),(95,19,'Therapist','Abbie Greene','Female','Says she is a therapist but is actually a very high-priced hooker',0,0,NULL,'2017-08-08 09:02:21',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_002_FADED.png'),(96,19,'','Bill Swinson','Male','The ship\'s purser who has been quietly embezzling the guests\' funds',0,0,NULL,'2017-08-08 09:04:16',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_003_FADED.png'),(97,19,'','Yuri','Female','Polenko\'s bodyguard who says he was sleeping during the murder but with who?',0,0,NULL,'2017-08-08 09:05:52',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_006_FADED.png'),(98,19,'Star','Amber Storm','Female','Movie star.  Face of revlon.  Body by gunther.  claws by henckel',0,0,NULL,'2017-08-08 09:06:52',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_002_FADED.png'),(99,19,'Friend','Eve Harrison','Female','Amber\'s friend from Australia who wants to live her life',0,0,NULL,'2017-08-08 09:08:21',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_002_FADED.png'),(100,19,'Nerd','Tyler Creed','Male','Internet visionary who has lost the vision.  Sold company for 6 billion.  Bored',0,0,NULL,'2017-08-08 09:09:26',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_004_FADED.png'),(101,19,'Powerful','Maurice Laturre','Male','One of the most powerful and successful film producers.  impotent',0,0,NULL,'2017-08-08 09:12:10',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_003_FADED.png'),(102,19,'Lottery Winner','Angus Coldwater','Male','Winner of the irish sweepstakes.  Soccer lover',0,0,NULL,'2017-08-08 09:13:53',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_009_FADED.png'),(103,19,'Fitness Coach','Gunther Voss','Male','The fitness coach.  Austrain.  Polenko promised to finance his girl',0,0,NULL,'2017-08-08 09:15:45',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_010_FADED.png'),(104,19,'','Miranda Gold','Female','Billionaire widdow.  A black wider.  She mates and kills',0,0,NULL,'2017-08-08 09:16:46',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_001_FADED.png'),(105,19,'','Marty Wemple','Male','The ships entertainment',0,0,NULL,'2017-08-08 09:17:44',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_001_FADED.png'),(106,19,'','Mandy Monarch','Female','A stow-away party girl',0,0,NULL,'2017-08-08 09:19:36',NULL,'','','/assets/images/stories/story1/cast/SUSPECT_012_FADED.png'),(107,74,'','test1','','A stow-away party girl',0,1,NULL,'2017-08-08 09:19:36',NULL,NULL,NULL,'/assets/images/stories/story1/cast/SUSPECT_012_FADED.png'),(108,74,'','test2','','bkjalkdsjfklasdf',0,1,NULL,'2017-09-15 04:02:42',NULL,NULL,NULL,'/assets/images/stories/story1/cast/SUSPECT_012_FADED.png');
/*!40000 ALTER TABLE `cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit`
--

DROP TABLE IF EXISTS `credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `amount` decimal(50,20) NOT NULL,
  `version` smallint(1) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `payment_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit`
--

LOCK TABLES `credit` WRITE;
/*!40000 ALTER TABLE `credit` DISABLE KEYS */;
/*!40000 ALTER TABLE `credit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debit`
--

DROP TABLE IF EXISTS `debit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `debit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `amount` decimal(50,20) NOT NULL,
  `version` smallint(1) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `payment_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debit`
--

LOCK TABLES `debit` WRITE;
/*!40000 ALTER TABLE `debit` DISABLE KEYS */;
/*!40000 ALTER TABLE `debit` ENABLE KEYS */;
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
INSERT INTO `hibernate_sequence` VALUES (125),(125),(125),(125),(125),(125);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hint`
--

DROP TABLE IF EXISTS `hint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hint` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `scene_id` bigint(11) NOT NULL,
  `text` text,
  `active` smallint(1) DEFAULT '1',
  `version` smallint(1) DEFAULT '0',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `position` int(11) DEFAULT NULL,
  `video` varchar(250) DEFAULT NULL,
  `audio` varchar(250) DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `game_hint_idx` (`scene_id`),
  CONSTRAINT `FKm4t639fom1es38fcy2404v04y` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hint`
--

LOCK TABLES `hint` WRITE;
/*!40000 ALTER TABLE `hint` DISABLE KEYS */;
INSERT INTO `hint` VALUES (108,37,'lipstick stain on champaign class',0,0,NULL,'2017-08-13 00:01:07',1,'','',''),(119,37,'hello',0,0,NULL,'2017-09-04 02:42:55',1,'','',''),(120,37,'test',0,0,NULL,'2017-09-04 02:43:20',1,'','','');
/*!40000 ALTER TABLE `hint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `amount` decimal(50,20) NOT NULL,
  `success` int(1) DEFAULT '0',
  `transaction_id` varchar(100) COLLATE utf8_bin NOT NULL,
  `transaction_message` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `invoice_number` varchar(100) COLLATE utf8_bin NOT NULL,
  `version` smallint(1) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `payment_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scene`
--

DROP TABLE IF EXISTS `scene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scene` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `story_id` bigint(11) NOT NULL,
  `image` varchar(250) DEFAULT NULL,
  `text` text,
  `sound` varchar(100) DEFAULT NULL,
  `active` smallint(1) DEFAULT '1',
  `version` smallint(1) DEFAULT '0',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `position` int(11) DEFAULT NULL,
  `video` varchar(250) DEFAULT NULL,
  `audio` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5wgvktdlgqbhklchxxvyknriu` (`story_id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scene`
--

LOCK TABLES `scene` WRITE;
/*!40000 ALTER TABLE `scene` DISABLE KEYS */;
INSERT INTO `scene` VALUES (36,20,NULL,'All about the girl',NULL,0,0,NULL,'2017-06-03 07:45:58',1,NULL,NULL),(37,19,NULL,'Bad boy scene',NULL,0,0,NULL,'2017-06-03 07:50:24',1,NULL,NULL),(38,19,NULL,'Bad girl scene',NULL,0,0,NULL,'2017-06-03 07:50:36',2,NULL,NULL),(39,19,NULL,'Blood everywhere',NULL,0,0,NULL,'2017-06-03 07:50:49',3,NULL,NULL),(43,19,NULL,'All about the suspects',NULL,0,0,NULL,'2017-06-03 09:09:37',4,NULL,NULL),(66,63,NULL,'Takes place at Poke Time',NULL,0,0,NULL,'2017-06-05 03:50:00',0,NULL,NULL),(67,63,NULL,'Takes place in Vancouver next door Pho Restaurant.',NULL,0,1,NULL,'2017-06-05 04:26:51',1,NULL,NULL),(76,74,NULL,'Cinai Medical',NULL,0,0,NULL,'2017-06-05 06:04:41',1,NULL,NULL);
/*!40000 ALTER TABLE `scene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `script`
--

DROP TABLE IF EXISTS `script`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `script` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `scene_id` bigint(11) NOT NULL,
  `cast_id` bigint(11) NOT NULL,
  `image` varchar(250) DEFAULT NULL,
  `text` text,
  `sound` varchar(100) DEFAULT NULL,
  `secret` text,
  `active` smallint(1) DEFAULT '1',
  `version` smallint(1) DEFAULT '0',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `position` int(11) DEFAULT NULL,
  `video` varchar(250) DEFAULT NULL,
  `audio` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhextbx8tumb6vm80pjh4m47fv` (`cast_id`),
  KEY `FKl9tlxsl8ra8aj4oif37ruhdxm` (`scene_id`),
  KEY `player_script_idx` (`scene_id`,`cast_id`),
  CONSTRAINT `FKhextbx8tumb6vm80pjh4m47fv` FOREIGN KEY (`cast_id`) REFERENCES `cast` (`id`),
  CONSTRAINT `FKl9tlxsl8ra8aj4oif37ruhdxm` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `script`
--

LOCK TABLES `script` WRITE;
/*!40000 ALTER TABLE `script` DISABLE KEYS */;
INSERT INTO `script` VALUES (107,37,92,'','show me the money',NULL,'mistress to someone',0,0,NULL,'2017-08-13 00:00:41',1,'',''),(109,37,93,'','duh duh duh',NULL,'lalalala',0,0,NULL,'2017-08-13 00:01:25',1,'',''),(110,37,94,'','You are hungry for attention.  Let other\'s know',NULL,'blond hair is dye',0,0,NULL,'2017-08-13 00:02:36',1,'',''),(111,37,95,'','Find a way to make some money',NULL,'Had her vision corrected but still wears glasses',0,0,NULL,'2017-08-13 00:03:25',1,'',''),(112,37,96,'','look for someone to sleep with',NULL,'gender confused',0,0,NULL,'2017-08-13 00:04:00',1,'',''),(113,37,97,'','is grouchy',NULL,'wouldn\'t hurt a fly',0,0,NULL,'2017-08-13 00:04:26',1,'',''),(114,37,98,'','try to be the center of attention',NULL,'is really insecure',0,0,NULL,'2017-08-13 00:04:55',1,'',''),(121,37,106,'','dododo',NULL,'blabhla',0,0,NULL,'2017-09-04 02:43:32',1,'',''),(122,38,92,'','bobobob',NULL,'balblabhab',0,0,NULL,'2017-09-04 02:44:01',2,'','');
/*!40000 ALTER TABLE `script` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `story`
--

DROP TABLE IF EXISTS `story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `story` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(250) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `synapsis` text,
  `answer` varchar(250) DEFAULT NULL,
  `size` int(11) DEFAULT '0',
  `active` smallint(1) DEFAULT '1',
  `version` smallint(1) DEFAULT '0',
  `updated` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `video` varchar(250) DEFAULT NULL,
  `audio` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
INSERT INTO `story` VALUES (19,NULL,'Murder She Wrote','mysterious hands','my hands',1,0,0,NULL,'2017-06-02 08:11:15',NULL,NULL),(20,NULL,'Sherlock Holmes','detective','the nurse',20,0,0,NULL,'2017-06-02 08:11:48',NULL,NULL),(63,NULL,'Rock The Craddle','Baby mysterious disappears.  Where did she go.','Grandmother had the baby all this time.lalala',5,0,1,NULL,'2017-06-05 05:49:06',NULL,NULL),(74,NULL,'Missing Girl','Not sure what to share here','No one did it.',33,0,0,NULL,'2017-06-05 06:03:45',NULL,NULL),(88,NULL,'Silk Road','No Where land','Los Angeles',3,0,0,NULL,'2017-06-06 07:32:57',NULL,NULL),(90,'https://www.google.com/search?q=models&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjNwb-Li6vUAhVXzGMKHWDWA6UQ_AUIBigB&biw=1117&bih=765#imgrc=weokerLhrW77HM:','Example','eee','aaa',5,0,0,NULL,'2017-06-07 06:43:32','https://www.youtube.com/watch?v=RsWCFGmpJHw','');
/*!40000 ALTER TABLE `story` ENABLE KEYS */;
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
  `user_type` int(10) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `user_idx` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (17,0,NULL,NULL,NULL,'kim2kim@gmail.com','$2a$10$30SI8wXQUQivoDcNOAu5peAwixlj4Hr4s3RXFfizoFb4gujXl0di.',1,'hpwcayYsIQBeoNRcaVtn',NULL,NULL,1,0,NULL,'2017-06-02 05:46:08',NULL,17,2),(84,0,NULL,NULL,NULL,'khimung@gmail.com','$2a$10$30SI8wXQUQivoDcNOAu5peAwixlj4Hr4s3RXFfizoFb4gujXl0di.',1,'GlNKVwWxdagzZPfdVWMl',NULL,NULL,0,0,NULL,'2017-06-06 06:44:30',NULL,84,2),(85,0,NULL,NULL,NULL,'imurdermystery@gmail.com','$2a$10$nPaiv6Hc0TpquYWiLDmCnus7H/SPLc9B3E5mU0nGZsKb7rjdDS0j.',0,'CAaHtqzdCYdycHhrmfgK',NULL,NULL,0,0,NULL,'2017-06-06 06:44:30',NULL,85,2),(86,0,NULL,NULL,NULL,'moreinvites@gmail.com','$2a$10$1cSx9yY2iR6Gc3KaQ4cNZ.RCKlJuSEGAsjvIs0EhDMZCoDq7joV7y',0,'qqNpIKnxOLlscCiqTIvD',NULL,NULL,0,0,NULL,'2017-06-06 06:53:01',NULL,86,2),(87,0,NULL,NULL,NULL,'moremoremore@gmail.com','$2a$10$nBEPAMYcNUtWxAtJX42KmOsKcj5XGnSVToWbxfaMLMfCJUp7rYSoG',0,'LbtagQnmEYUHaZGwmzzT',NULL,NULL,0,0,NULL,'2017-06-06 06:53:19',NULL,87,2),(91,0,NULL,NULL,NULL,'','$2a$10$t3Rq/nEIBRivjt3ugiUG2.or.V2rfsB3Nc1rtvVJ7baaZ0n5sHaee',0,'HEnePEUcqxiNgWVXgGFr',NULL,NULL,0,0,NULL,'2017-08-04 08:29:30',NULL,91,2),(115,0,NULL,NULL,NULL,'angelinajfoster@gmail.com','$2a$10$m2VWXD8zgB7wbrDUGjDnKu8fV0ORnjCS9q1T/kc6NxkZZPEjnJwZe',0,'NOoubuSEjdLydPAChcNq',NULL,NULL,0,0,NULL,'2017-08-27 23:20:56',NULL,115,2),(116,0,NULL,NULL,NULL,'literose@yahoo.com','$2a$10$pPSWcUhpSdV8kwpbdyuvLehTH9JxVEFODVwqP2zQQhT0fRflsVh9a',0,'AlJLIAOPdXnfytHCFIlR',NULL,NULL,0,0,NULL,'2017-08-27 23:26:50',NULL,116,2),(117,0,NULL,NULL,NULL,'ztmessier@yahoo.com','$2a$10$QgeljC71L7A0zVmVJd7bZ.8wvuxtg2qnAYVe4XoKJlu0qdwldjCGW',0,'OXdzwDswfjyuXywOWmIL',NULL,NULL,0,0,NULL,'2017-08-27 23:26:50',NULL,117,2),(118,0,NULL,NULL,NULL,'ztmessier@gmail.com','$2a$10$icPg8WquBJ7WGy0MhjzMCeAIlfihDrDC6m1hrPM0UCQ5cuRivb1Hm',0,'TTphVuXndXjOUKLeKWYo',NULL,NULL,0,0,NULL,'2017-08-27 23:29:37',NULL,118,2),(123,0,NULL,NULL,NULL,'vtmessier@yahoo.com','$2a$10$yKY11CQW4yDzkr1BmI37ZeWvs6vFY1d7HyqNYCrvOa560UNAgIUF6',0,'ABxWsozbMBITTWcesKie',NULL,NULL,0,0,NULL,'2017-09-15 09:48:07',NULL,123,2),(124,0,NULL,NULL,NULL,'genieloungecafe@gmail.com','$2a$10$CQYTnaEIKC.RFYVaj1CaWO2wzlnPsvAAxSa7dZChLSlDo0jTgSd7C',1,'DKxDmGNkKQEbOBoGYxpQ',NULL,NULL,1,0,NULL,'2017-09-20 07:21:21',NULL,124,0);
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
INSERT INTO `user_authority` VALUES (17,3),(84,2),(85,2),(86,2),(87,2),(91,2),(115,2),(116,2),(117,2),(118,2),(123,2),(124,2);
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

-- Dump completed on 2018-10-23 22:54:41
