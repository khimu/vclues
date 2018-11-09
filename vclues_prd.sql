-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: vclues
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.16.04.1

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
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cast`
--

LOCK TABLES `cast` WRITE;
/*!40000 ALTER TABLE `cast` DISABLE KEYS */;
INSERT INTO `cast` VALUES (127,125,'guest','Harry Potter','male','1. You stand apart from others in your extreme courage and refusal to backdown from the truth.\r\n2. You are inquisitive and curious and exceptionally loyal\r\n3. You back your best friend Ron in all he does towards others, but can excuse your own personal biases and grudges against him.\r\n4. You love quidditch and everything about the Wizarding World, but have insecurities about your background, upbringing, and all things to do with the Malfoy family.\r\n5. You have little sympathy for anyone that acts disloyal and are quick to accuse.\r\n\r\nPhysical Appearance \r\nglasses, lightning bolt scar, cloak, Hogwarts uniform, gryffindor colors and broom',0,0,NULL,'2018-10-31 05:45:02',NULL,'','','http://www.vclues.com/stories/story3/cast/harrypotter.png'),(128,125,'guest','Hermione Granger','female','1. You\'re exceptionally disciplined, clever, and knowledgable\r\n2. You are loyal to your best friend Harry and your boyfriend Ron\r\n3. Your weaknesses lie in your emotions, the bias of pure blood vs mood blood\r\n4. You are not afraid to speak your mind & stand for what you hold true, to anyone\r\n\r\nPhysical Appearance\r\nCloak, Hogwarts uniform, gryffindor colors, time turner necklace, books, frizzy hair, and broom',0,0,NULL,'2018-10-31 06:05:54',NULL,'','','http://www.vclues.com/stories/story3/cast/Hermione.png'),(129,125,'guest','Luna Lovegood','female','1. You stand apart from others in your extreme styles and interests\r\n2. You are inquisitive with great courage and refusal to backdown from the truth\r\nYou treasure the friendships you have found and longed for until you came to Hogwarts, but always wonder if you are considered second rate in their eyes\r\n4. you are in love with Neville Longbottom and are working to win him over\r\n5. You are a supportive and sympathetic friend, but often overlooked and unheard\r\n\r\nPhysical Appearance\r\nLong flowing hair, eccentric accessories, Hogwarts uniform, cloak, Gryffindor colors, gibbler and broom',0,0,NULL,'2018-10-31 06:12:25',NULL,'','','http://www.vclues.com/stories/story3/cast/lunalovegood.png'),(130,125,'guest','Ginny Weasley','female','1. You appear quiet, yet you are bold and honest with great courage when called upon\r\n2. You voice your opinions as you refuse to backdown from the truth\r\n3. You treasure the friendships and your relationship with Harry Potter, but can get jealous of Hermione and all the time and attention Harry gives to her\r\n4. you know you fall in the shadows of three leaders, Harry, Hermione, & Ron, but you find comfort in your friendships with Luna and are loyal to her t the end\r\n5. Your weaknesses lie in your insecurities from the Tom Riddle Diary and your place in the shadows.  You are determined.  Speak up and show Harry what you an handle\r\n\r\nPhysical Appearance\r\nStraight red hair pulled off your face, cloak, Hogwarts uniform, Gryffindor colors & broom',0,0,NULL,'2018-10-31 06:17:27',NULL,'','','http://www.vclues.com/stories/story3/cast/ginny.jpg'),(131,125,'guest','Severus Snape','male','1. You are known for your work as a cold and hard professor at Hogwarts & your desire to teach defense against the dark arts\r\n2. Others can\'t forget your past as a death eater & your loyalty is always in question\r\n3. You hide the inner desire for the love of Lilly you lost as a youth\r\n4. You hold grudges & your many insecurities leave you bitter and quick to judge others\r\n5. The double life you lead with the two sides requires you to continually prove your loyalty to each side, yet all the lies might crash down at anytime\r\n\r\nPhysical Appearance\r\nCloak, black attire, dark stringy hair and pale complexion, the dark mark, rings with dark symbols & broom',0,0,NULL,'2018-10-31 06:21:32',NULL,'','','http://www.vclues.com/stories/story3/cast/severus_snape.jpg'),(132,125,'guest','Ron Weasley','male','1. You are known for your friendship with Harry Potter and your relationship with Hermione, yet you feel others don\'t recognize your contributions to adventures you three take on\r\n2. You\'re known for your funny humor, slight awkwardness, & your easy going nature\r\n3. Yet you long to prove yourself on the field, to your family, & especially Hermione\r\n4. Your weaknesses lie in your easily ruffled feathers, your insecurities, and your stubborn temper.\r\n\r\nPhysical Appearance\r\n\r\nCloak, Hogwarts uniform, Gryffindor colors, homemade sweaters and scarfs & broom',0,0,NULL,'2018-10-31 06:24:42',NULL,'','','http://www.vclues.com/stories/story3/cast/ron.png'),(133,125,'guest','Gilderoy Lockheart','male','1. A renowned author, sadly sent to st. Mungo s. Luckily by some talented doctors your memory has been restored and now you are back, continuing to claim your stories & heroic adventures\r\n2. Yet you live in guilt and constant worry that you will be exposed by the annoying Potter, Weasley & granger trio\r\n3. you\'re known for your suave character and charisma with the women.  You love to butter them up with false flattery while boasting of all your amazing achievements\r\n4. You have little care for anyone else and look at others as a means to an end\r\n5. You\'re weaknesses lie in your past and your need hide the truth and maintain your reputation & your fear that others will see your many phobias and fears.\r\n\r\nPhysical Appearance\r\n\r\nCurly golden locks, flamboyant cloak, bow tie or scarf, and decorative accessories & broom',0,0,NULL,'2018-10-31 06:28:50',NULL,'','','http://www.vclues.com/stories/story3/cast/gilderoy_lockhart.png'),(134,125,'guest','Rita Skeeter','female','1. As a talented reporter you are always looking for a good story and no sacrifice is too great\r\n2. You love to scheme and sweet talk as you dig for dirt in every conversation while twisting the story in your favor\r\n3. Business in the press hasn\'t been as lucrative lately, and you are in need of a hit seller\r\n4. You\'re one promised lead has just fallen through and your job is at risk and you now seek revenge\r\n\r\nPhysical Appearance\r\nBroom, flashy business attire, glasses, red lipstick, briefcase, no need for a cloak, but pen at the ready',0,0,NULL,'2018-10-31 06:32:03',NULL,'','','http://www.vclues.com/stories/story3/cast/ritaskeeter.jpg'),(135,125,'guest','Mundungus Fletcher','male','1. You are known for your conniving skills and your trading under the table\r\n2. You love a good find and know how to wheel and deal for personal profit\r\n3. No one seems to appreciate your work, yet they come to you when they are in need\r\n4. You have no need for manners and in terms of thieving you lack a conscience\r\n5. Your weaknesses lie in your lack of courage and your reputation\r\n\r\nPhysical Appearance\r\n\r\nSilky colorful shirts, gold chain necklaces, dirty cloak, broom, and a sack of tradable treasures',0,0,NULL,'2018-10-31 06:36:22',NULL,'','','http://www.vclues.com/stories/story3/cast/mundungus_fletcher.png'),(136,125,'guest','Draco Malfoy','male','1. You are known for your slytherin heritage, love for the dark arts\r\n2. Your proud and pompous attitude gives no sympathy to others and all are beneath you\r\n3. Your jealousy of Harry Potter\'s accomplishments only adds to your hatred for him and his crew\r\n4. There is nothing you wouldn\'t do to get back at Harry and his pathetic friends, particularly Ron and Neville.  Such vags should not be getting such recognition\r\n5. Your weaknesses lie in your lack of courage, your insecurities, and your jealousy\r\n\r\nPhysical Appearance\r\n\r\nSlicked hair, cloak, Hogwarts uniform, slytherin colors, broom & distasteful glare',0,0,NULL,'2018-10-31 06:40:19',NULL,'','','http://www.vclues.com/stories/story3/cast/draco_malfoy.png'),(137,125,'guest','Bellatrix Lestrange','female','1. You are known for your love of evil and dark ways\r\n2. You find pleasure and thrill in hurting others\r\n3. With a flaring temper, no one dare cross you without fearing for their lives\r\n4. And when wronged you will stop at nothing to seek your revenge\r\n5. Yet you\'re consumed by personal insecurities and failures and hide behind your dark ways\r\n\r\nPhysical Appearance\r\n\r\nCloak, broom, dark sultry attire, wild curly hair, the dark mark, and rings with dark symbols',0,0,NULL,'2018-10-31 06:42:32',NULL,'','','http://www.vclues.com/stories/story3/cast/bellatrix_lestrange.png'),(138,125,'guest','Dolores Umbridge','female','1. You are known for your work at the Ministry of Magic and your harsh ways\r\n2. Through your many connections with crooked politicians you got released early from Aykaban and are now working your way back up in the ministry\r\n3. To rebuild your reputation you are in dire search for any dark magic practices your can call out\r\n4. You still have your precise procedures and syrupy talk with blunt comments\r\n5. But your great weakness lies in your insecurities and your willingness to put down others as your rebuild your reputation\r\n\r\nPhysical Appearance\r\n\r\nPink, bows, floral broaches, perfectly pinned up hair, cats, clipboard & broom',0,0,NULL,'2018-10-31 06:45:25',NULL,'','','http://www.vclues.com/stories/story3/cast/delores_umbridge.jpg'),(139,125,'extra','Filch the Caretaker','male','Performs the duties of a butler',0,0,NULL,'2018-10-31 15:26:03',NULL,'','','http://www.vclues.com/stories/story1/cast/SUSPECT_003_FADED.png'),(141,125,'host','Professor McGonagall','male','Professor McGonagall is the Host.  Remind the guests to speak one at a time, and loud and clear. Repeat any\r\ncomments aloud that the group that might have been missed, ask guest to repeat what\r\nthey said. AND keep all guests inline',0,0,NULL,'2018-10-31 15:42:16',NULL,'','','http://www.vclues.com/stories/story3/cast/mcgonagall.jpg');
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
INSERT INTO `hibernate_sequence` VALUES (287),(287),(287),(287),(287),(287);
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
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hint`
--

LOCK TABLES `hint` WRITE;
/*!40000 ALTER TABLE `hint` DISABLE KEYS */;
INSERT INTO `hint` VALUES (164,158,'Evidence 1',0,0,NULL,'2018-10-31 16:10:21',2,'','','http://www.vclues.com/stories/story3/evidence/evidence1_scene_1.png'),(170,158,'Evidence 2',0,0,NULL,'2018-10-31 16:13:15',2,'','','http://www.vclues.com/stories/story3/evidence/evidence2_scene1'),(171,158,'Evidence 3',0,0,NULL,'2018-10-31 16:13:39',2,'','','http://www.vclues.com/stories/story3/evidence/evidence3_scene1.png'),(172,158,'Evidence 4',0,0,NULL,'2018-10-31 16:14:07',2,'','','http://www.vclues.com/stories/story3/evidence/evidence4_scene1.png'),(173,158,'Evidence 5',0,0,NULL,'2018-10-31 16:14:22',2,'','','http://www.vclues.com/stories/story3/evidence/evidence5_scene1.png'),(174,158,'Evidence 6',0,0,NULL,'2018-10-31 16:14:37',2,'','','http://www.vclues.com/stories/story3/evidence/evidence6_scene1.png'),(191,207,'Evidence 1',0,0,NULL,'2018-10-31 16:25:01',3,'','','http://www.vclues.com/stories/story3/evidence/evidence1_scene2.png'),(192,207,'Evidence 2',0,0,NULL,'2018-10-31 16:25:20',3,'','','http://www.vclues.com/stories/story3/evidence/evidence2_scene2.png'),(193,207,'Evidence 3',0,0,NULL,'2018-10-31 16:25:40',3,'','','http://www.vclues.com/stories/story3/evidence/evidence3_scene3.png'),(194,207,'Evidence 4',0,0,NULL,'2018-10-31 16:25:50',3,'','','http://www.vclues.com/stories/story3/evidence/evidence4_scene2.png');
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
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scene`
--

LOCK TABLES `scene` WRITE;
/*!40000 ALTER TABLE `scene` DISABLE KEYS */;
INSERT INTO `scene` VALUES (140,125,'','Guests Arrives In Hogsmeade / Appetizers and Drinks',NULL,0,0,NULL,'2018-10-31 15:36:42',1,'',''),(158,125,'','Interdepartmental Memos / SALAD COURSE',NULL,0,0,NULL,'2018-10-31 16:05:32',4,'',''),(187,125,'','Parchments 1 / PALATE CLEANSER COURSE',NULL,0,0,NULL,'2018-10-31 16:23:18',14,'',''),(207,125,'','Parchment 2: / ENTREE COURSE',NULL,0,0,NULL,'2018-10-31 16:30:27',17,'',''),(226,125,'','Parchment 3 / DESSERT COURSE',NULL,0,0,NULL,'2018-10-31 16:41:45',28,'',''),(245,125,'','FINAL',NULL,0,0,NULL,'2018-10-31 16:48:17',32,'',''),(252,125,'','Intro Host 1',NULL,0,0,NULL,'2018-11-01 07:28:35',2,'',''),(253,125,'','Intro Host 2',NULL,0,0,NULL,'2018-11-01 07:28:42',3,'',''),(255,125,'','Host Solo 1',NULL,0,0,NULL,'2018-11-01 07:33:08',5,'',''),(256,125,'','Host Solo 2',NULL,0,0,NULL,'2018-11-01 07:33:19',6,'',''),(257,125,'','Host Solo 3',NULL,0,0,NULL,'2018-11-01 07:33:30',7,'',''),(258,125,'','Host Solo 4',NULL,0,0,NULL,'2018-11-01 07:33:38',8,'',''),(259,125,'','Host Solo 5',NULL,0,0,NULL,'2018-11-01 07:33:44',9,'',''),(260,125,'','Host Solo 6',NULL,0,0,NULL,'2018-11-01 07:34:30',10,'',''),(261,125,'','Host Solo 7',NULL,0,0,NULL,'2018-11-01 07:34:41',11,'',''),(262,125,'','Host Solo 8',NULL,0,0,NULL,'2018-11-01 07:34:48',12,'',''),(263,125,'','Host Solo 9',NULL,0,0,NULL,'2018-11-01 07:34:55',13,'',''),(264,125,'','Host Solo 10',NULL,0,0,NULL,'2018-11-01 07:39:47',15,'',''),(265,125,'','Host Solo 11',NULL,0,0,NULL,'2018-11-01 07:40:05',16,'',''),(266,125,'','Host Solo 12',NULL,0,0,NULL,'2018-11-01 07:43:37',18,'',''),(267,125,'','Host Solo 14',NULL,0,0,NULL,'2018-11-01 07:43:48',20,'',''),(268,125,'','Host Solo 13',NULL,0,0,NULL,'2018-11-01 07:43:59',19,'',''),(269,125,'','Host Solo 15',NULL,0,0,NULL,'2018-11-01 07:44:14',21,'',''),(270,125,'','Host Solo 16',NULL,0,0,NULL,'2018-11-01 07:48:55',22,'',''),(271,125,'','Host Solo 17',NULL,0,0,NULL,'2018-11-01 07:49:13',23,'',''),(272,125,'','Host Solo 18',NULL,0,0,NULL,'2018-11-01 07:49:34',24,'',''),(273,125,'','Host Solo 19',NULL,0,0,NULL,'2018-11-01 07:50:00',25,'',''),(274,125,'','Host Solo 20',NULL,0,0,NULL,'2018-11-01 07:50:13',26,'',''),(275,125,'','Host Solo 21',NULL,0,0,NULL,'2018-11-01 07:50:35',27,'',''),(276,125,'','Host Solo 22',NULL,0,0,NULL,'2018-11-01 07:54:33',29,'',''),(277,125,'','Host Solo 23',NULL,0,0,NULL,'2018-11-01 07:54:38',30,'',''),(278,125,'','Pre-Finale',NULL,0,0,NULL,'2018-11-01 07:54:50',31,'','');
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
) ENGINE=InnoDB AUTO_INCREMENT=287 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `script`
--

LOCK TABLES `script` WRITE;
/*!40000 ALTER TABLE `script` DISABLE KEYS */;
INSERT INTO `script` VALUES (142,140,141,'','(clears throat) This is no time for chit chat. Neville Longbottom was found dead. Murdered last night. This is not the time or place to discuss the details, so each of you, please follow me. I will escort you to Hogwarts where we will reconvene in the dinning hall. Everyone follow me.',NULL,'',0,0,NULL,'2018-10-31 15:49:08',1,'',''),(143,252,141,'','Oh, I notice there are some Interdepartmental memos to be retrieved. (direct guests? attention to the “interdepartmental memos”/airplanes) Grab them and any necessary belongings quickly the Train leaves momentarily.',NULL,'',0,0,NULL,'2018-10-31 15:50:03',1,'',''),(144,253,141,'','Welcome to Hogwarts, as you enter Filch, our Caretaker, will be checking your cloaks and all that you might be carrying. After which, please join us in the Dinning Hall.',NULL,'',0,0,NULL,'2018-10-31 15:56:49',1,'',''),(145,140,139,'','Hassle each guest and check their pockets.',NULL,'',0,0,NULL,'2018-10-31 15:58:17',1,'',''),(146,140,127,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:58:50',1,'',''),(147,140,128,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:58:55',1,'',''),(148,140,129,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:59:01',1,'',''),(149,140,130,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:59:08',1,'',''),(150,140,131,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:59:15',1,'',''),(151,140,132,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:59:21',1,'',''),(152,140,133,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:59:28',1,'',''),(153,140,134,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:59:34',1,'',''),(154,140,135,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:59:40',1,'',''),(155,140,136,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:59:46',1,'',''),(156,140,137,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 15:59:52',1,'',''),(157,140,138,'','Enjoy drinks and appetizers and mingle and chat amongst each other.',NULL,'',0,0,NULL,'2018-10-31 16:00:01',1,'',''),(159,158,141,'','(Clear Throat) Please take your seats and any memos you might have received, now would be the time to read them, as dinner will begin shortly. You must remember, no murder can be solved if you fail to uphold your responsibilities. It is important you understand the tasks you are given and fulfill them! I will warn you to read them carefully. (Give a few minutes for reading)',NULL,'',0,0,NULL,'2018-10-31 16:06:13',2,'',''),(160,255,141,'','(Tap glass to call attention) I would like to begin by thanking each of you for joining us. As we are in unnerving times, for all of our sides (emphasized to the dark side characters), this murder is one that affects us all. Neville Longbottom?s murder was no accident and signs point to each of you. So we have gathered this night to discover which one of us in this very room is guilty.',NULL,'',0,0,NULL,'2018-10-31 16:06:26',2,'',''),(161,256,141,'','The facts we know. - Neville was died - He was killed by a poison - His wand is missing',NULL,'',0,0,NULL,'2018-10-31 16:06:55',2,'',''),(162,257,141,'','As we are all interested in the outcome, please do not hesitate to discuss what you might know. Time is of the essence and we have a murder to solve. Does anyone have anything they might like to share? ',NULL,'(Feel free to call on Characters to encourage discussion and participation. Example: “Mr. Draco Malfoy lets begin/hear from you... It seems rather peculiar that you... And where were you ...?” etc)',0,0,NULL,'2018-10-31 16:07:52',2,'',''),(163,258,141,'','Although, much of this information is necessary, we have some important evidence to discuss. Upon your arrival into Hogwarts, Filch collected a few personal items you carried along he has been sorting through what he found, and brought a few things to my attention. Some of which are rather unnerving.',NULL,'',0,0,NULL,'2018-10-31 16:08:36',2,'',''),(165,259,141,'','First, Draco Malfoy...it appears you brought along this Remembrall. It wouldn?t perhaps be the same Remembrall, Ginny mentioned? The same Remembrall, that Neville once received in this very hall?',NULL,'',0,0,NULL,'2018-10-31 16:11:03',2,'',''),(166,260,141,'','Next! Miss Ginny Weasley. Not that you should be looking so smug! Filch found this wand inside your robes. This wand was Neville?s wand. How might you have come about it. Hmmph!',NULL,'',0,0,NULL,'2018-10-31 16:11:17',2,'',''),(167,261,141,'','Now, for Miss Granger! I am sorry to say, that we have indeed found these love notes. Love notes between you and Neville. This is most surprising.',NULL,'',0,0,NULL,'2018-10-31 16:11:28',2,'',''),(168,262,141,'','And finally, Mr. Mundungus, this is not so surprising. This photograph, here. Isn?t this you, circled...and what might this inscription say? “Mundungus, I know who you gave this too, and I will tell.” Signed, Neville? Not that most of your trades aren?t questionable enough!',NULL,'(Encourage lots of discussion here...) Discuss Parchments, clues, refer back to evidence, etc. (McGonagall, remember to facilitate discussion, remind guests to speak one at a time and loud and clear...and repeat anything that was not heard)',0,0,NULL,'2018-10-31 16:12:07',2,'',''),(169,263,141,'','This is no simple crime and we are not to the bottom of it yet! None, of you are off the hook...Filch is still at work, sorting through what he found. But we will continue this discussion with our next course. Mrs. Weasley, we are ready for the Palate Cleanser.',NULL,'',0,0,NULL,'2018-10-31 16:12:25',2,'',''),(175,158,127,'','You recently noticed Neville sneaking around, telling lies, and being disloyal to all of you friends. And particularly towards Ron. As Neville has shown greater and greater interest towards Hermione, you have been dumbfounded at his gall to betray Ron. Neville has been spending more and more time with Hermione and sneaking to visit her whenever Ron isn?t around. You are not going to stand for that and you will willingly call Hermione out on the issue and back Ron for the decent guy he is. And with the news of Neville?s death you can?t help but suspect Malfoy & his gang were behind this. Those gits always hated Neville and the fact that he amounted to something, when they still can?t do anything right. Yet then again, Bellatrix has had it out for Neville as long as you can remember. They are definitely worth confronting.',NULL,'Things you must say and do during this course: Accuse Malfoy of Neville?s death and question his whereabout for the murder. Confront Bellatrix as well. Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:18:49',2,'',''),(176,158,132,'','You constantly noticed Neville sneaking around, telling lies, and being disloyal to all of you friends, with the exception of Hermione. In fact, you have noticed Neville?s growing feelings towards Hermione and you are not hiding your jealousy nor your anger. Not only is Hermione your girl friend, but Neville calls himself one of your close friends as well. Who does he think he is. He isn?t that suave! It?s been obvious how he continually waits around then sneaks in to see Hermione the moment you leave. It has been more than once that they have met each other at late hours in deserted locations. You are very concerned that Hermione is reciprocating his feelings. You notice she seems more distant lately and returns from her outings with Neville happy and elated. You would call Hermione out, but you haven?t found the right time, nor the courage yet. But when the time arrives, you wont hesitate, even if this new crush of hers did just die. Plus you have a feeling Malfoy were behind this. Those to gits always hated Neville and the fact that he amounted to something, when they still can?t do anything right. And you don?t miss a chance to remind them either.',NULL,'Things you must say and do during this course: Call Hermione out on her attraction to Neville and out of jealousy accuse her of his murder. Regardless of what she says, you will not listen you have your pride to take care of. But assuming she isn?t guilty, join Harry when he accuses Malfoy. Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:19:07',2,'',''),(177,158,128,'','You are stunned by the news that Neville died. You two have grown so close lately and done so much together, it doesn?t seem possible. You now are confused on what to do with the secret Neville has entrusted you with and even more so with all of the Love notes you two have written. Now seems like the wrong time to share that information and you are on the verge of tears. It is hard to make sense of everything at the moment and no one will understand. The whole situation is just tragic. Yet, you are determined to uncover the truth of his death and you feel Bellatrix is to blame. She always hated Neville, mocked his doof-like ways, and flaunted the torture she inflicted upon his parents. She has had it out for him ever since he stood up to her.',NULL,'Things you must say and do during this course: Be short and defensive if accused by Ron or anyone about your relationship with Neville. They wouldn?t understand. Ask lots of inquisitive questions to everyone and openly discuss clues and theories you come up with. Be extra accusatory when questioning Bellatrix, Malfoy, and Snape. Turn to Ginny and Luna for support. Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:19:22',2,'',''),(178,158,129,'','You could not be more devastated. You have suppressed your deep love for Neville for what feels like an eternity. And now what are you to do. You Neville is gone. You?re on the verge of tears, but for the honor of who he was and his reputation you will fight for his cause. You wonder if it was due to the Nargles, along with many other mysteries your father taught you about, yet you must educate those in attendance as they seem to have overlooked these serious possibilities...or was it just fate. Was this his destined time? It is worth consideration. Yet, you feel so sad for Neville. Leading up to his death, Snape was constantly irritated with Neville & lashing out at even his smallest mistakes. Even if Neville is gone, you must at least make Snape apologize for this. You owe it to Neville.',NULL,'Things you must say and do during this course: Express your devastation, lightly hint at your attraction to Neville, but be shy and modest about the issue. When others accuse Hermione of a relationship with him, express that you are shocked and jealous. And ask Hermione how she could hurt her good friends. (meaning you and Ron). Tell the group of other possibilities for Neville?s death such as fate, Nargles, etc. taught to you by your father. Voice your concerns of Snapes recent behavior and treatment of Neville and tell him he owes an apology to Neville. Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:19:40',2,'',''),(179,158,130,'','You recently noticed Neville sneaking around, telling lies, and being disloyal to all of you friends. And particularly towards Luna. As Neville has shown greater and greater interest towards Hermione, you have been dumbfounded at his gall to hurt Luna. Either Neville was rude enough to lead Luna on, or he is going behind her back to pursue Hermione. Neville has been spending more and more time with Hermione and sneaking to visit her whenever Ron isn?t around. Luna has been longing and waiting for Neville to step up and make things into a serious relationship, and she is unaware of his long visits and constant attachment to Hermione. Luna needs someone to voice her  side, and you are up for the task. Yet, for the death of Neville you are a bit boggled. Who would want to kill Neville? Luna?s love wouldn?t have driven her to such an end...but you & Luna have noticed Snape?s constant irritation with Neville and his continual lashing out at the smallest mistakes Neville makes. And on top of everything else, you saw Malfoy and Crabbe ransacking Nevilles room and even stealing some of his things. Find out what he has to say about that. And don?t let anyone know you have Neville?s wand. They don?t need to know why you have it.',NULL,'Things you must say and do during this course: Voice your concerns about Hermione and the relationship she has been building with Neville. Explain how often you see them together, sneaking off when others aren?t around. Tell Hermione, that you may be friends, but Ron is still your brother and he deserves to be treated with respect. Support accusations against Snape. You and Luna saw his harsh treatment of Neville. And Most of all, be sure to point the finger at Malfoy and tell everyone how you saw him and Crabbe ransacking Neville\'s room. And that he stole Neville?s Remembrall!Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:20:07',2,'',''),(180,158,136,'','You?re less than sorry Neville died. You never liked the kid nor his awkward & annoying personality. And your hatred towards him has only increased lately, due to his growing fame and attention. Of all people, you will not stand in the shadow of Neville Longbottom. And since some of his recent acts have insulted your family?s reputation, you had been waiting for the change to teach him a lesson. And now you will remind everyone just how much he had this coming, but you wont mention the plan of attack you, Crabbe, & Goyle did to his room & belongings, nor your plans to attack him. Why would you help these people solve any crimes. ',NULL,'Things you must say and do during this course: Act pleased & smug about Neville?s death, rub it in to Harry and his dumb pals. Complain about him & the mess he has made for your family...and let everyone know that with his behavior, there were lots of people wanting him gone. Team up with those that are on your side. Strength lies in numbers. When Ron accuses you of Neville?s murder, accuse him & Harry of killing Neville out of jealousy, since Neville was stealing Ron?s girl (Hermione). Be relentless. And even mention, that Ginny or Luna are probably just as guilty due to their jealousy...since Luna has been in love with Neville and Ginny would do anything to keep Harry happy.',0,0,NULL,'2018-10-31 16:20:27',2,'',''),(181,158,131,'','You never liked Neville. His awkward, clumsy ways have always irritated you. And as of late, you believe Neville has been stealing ingredients from your potions supplies. Such disrespect is unnerving. And that annoying little twit had his last chance. You have no reason to hide your feelings, no one can pin you to the crime and you are tired of holding your tongue. Dumbledore isn?t here and this is not a “school issue” so you have the right to make sure others understand the truth about this boy.',NULL,'Things you must say and do during this course: Remind everyone what Neville was really like & the dishonest things he did. When recognizing his clumsiness, the slow boy could have killed himself on accident. He was far from smooth. When you are  accused with the crime, scoff at the notion. And tell them, “Anything I did to that boy, he deserved.” When Harry or any of his goodie-goodie friends are accused, support the ideas, since they are always up to mischief. Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:20:52',2,'',''),(182,158,135,'',' First and foremost, you are not happy to be here and you are looking for any chance to leave. Who cares about this dumb kid and what happened to him, and you have work you need to be doing. With all the commotion there are plenty of mourning, gullible customers roaming the streets, so let?s get this over with. And on top of everything else, you hate that little Longbottom kid anyways. The little conniver lost your a huge profit when he blackmailed you. He coerced you into giving him a valuable stone, a loss you will never recuperate. So you couldn?t be more happy about his death, you wanted him gone.',NULL,'During this course Remember to: Continually voice your support of any accusations made, you are just wanting them to hurry up and end this whole meeting. Make mention your annoyances with Longbottom and even his blackmailing of you, but avoid mentioning what he blackmailed you about, you don?t need anyone connecting you to the murder. Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:21:12',2,'',''),(183,158,133,'','You are thrilled with the news of Neville?s death, but true to your amazing sympathetic nature, you will use this as a time to voice your oozing, syrupy sadness and remorse, but you will also use this opportunity to remind everyone that regardless of the terrible situation, they need not fear they can look to you. You have been through greater troubles than this in all of your many adventures. Throughout the meal, find moments to draw attention to your heroic adventures, but whenever accusations are made, be quick to support and “hustle the process along,” everyone needs to know that you have much more important things to do with your time than be here. But you are also trying to keep your recent experiences with Neville a secret. That conniving boy is blackmailing you about some of “your” published adventures and looking for money. You do not need this slipping out now, after you have just begun your reclaim of fame.',NULL,'Things you must say and do during this course: Voice your “genuine” sadness for Neville, yet use it to bring the attention to your great accomplishments. Hustle the decisions along, by agreeing to accusations from all sides & remind others that you have important places to be other than here. Keep the focus of the murder on others, you do not want your reputation mixed up in this nonsense. Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:21:36',2,'',''),(184,158,134,'','Any news is great news for an amazing journalist as yourself...and Neville?s death is another great way to make headlines. Be sure to “interview” and question everyone around. You want to collect all the details possible & an event like this, with so many famous people, this will be the article of the year for sure. Who knew the death of pathetic conniving Neville could actually benefit you so well. And this only makes you more happy, since you have had it out for that boy since his sneaky little fingers  snatched the story for your next book. That boy deserved to pay for his rotten ways, and you will be sure to use this opportunity to your advantage. As everyone talks, questions, and accuses one another keep notes and interject lots of prying and personal questions. You don?t want to miss any angle.',NULL,'Things you must say and do during this course: Fulfill your role as a journalist, questioning and writing down all of the important details. Support all accusations made, and dig for the dirt on each. There is no need to worry about offending anyone, you have a story and a big paycheck that are of much greater importance. And voice your negative opinions, you have plenty, of the annoying boy and how he was practically a thief and stole your story. Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:21:54',2,'',''),(185,158,138,'',' My now isn?t this whole thing just ironic? Neville Longbottom has died, and for what? Ha, you couldn?t be more happy. That little vermin and his family deserved worse than this. And you will make sure everyone knows just how you feel. “Maybe if the Longbottoms had thought more of the Ministry of Magic?s work and remembered to keep their allegiances in proper order, they would not have had such a tragic ending.” That crummy little boy, deserved more than this if you could have got your hands on him. His mischievous ways cost you plenty of frustration at Hogwarts and with the Ministry, but most of all he interrupted your latest plan and your possession of the Amorin Stone. That was an unforgivable mistake he made! You needed its powers and the fame it would bring you. ',NULL,'Things you must say and do during this course: Remind everyone that Neville was not a sweet nor innocent boy. He loved to break rules and rebel against authority, so this death is not such a shock. Remind everyone that, putting your faith in the Ministry is where our focus should always be and less on these trivial matters, such as Neville?s death. “Maybe if the Longbottoms had thought more of the Ministry of Magic?s work and remembered to keep their allegiances in proper order, they would not have had such a tragic ending.” Others should use this as a reminder to check their own allegiances. Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:22:12',2,'',''),(186,158,137,'','Ha, Ha, Ha! Isn?t this a joyous occasion! Little Neville Longbottom is dead! You couldn?t be happier! Yet you are less than thrilled to join this vile company to solve anything. The boy is dead and who cares how it happened, plus you have plenty to hide that they don?t need to know. And, that little Longbottom had it coming. That rat is just like his parents, good for nothing. He has humiliated you too often and now he knows who is boss, he, is dead! Now if only you could do the same to his parents once and for all. Yet, while you are in attendance, you are not going to help them out. Every time someone dares accuse you or any of the Dark Lord?s Followers, you scoff at them and any of their ideas. But you will greatly encourage the blame upon “Dumbledore?s little friends”. They deserve a nice visit to Azkaban to remind them where their allegiance should be.',NULL,'Things you must say and do during this course: Openly mock Neville?s death and all of his little friends. Remind them that all real power comes from the Dark Lord. Use your glares, hissing, and cackling to your advantage. Argue with any accusations against the Dark Lord?s followers, but make and support all accusations against Harry Potter and any of those he associates with. Team up with those that are on your side. Strength lies in numbers.',0,0,NULL,'2018-10-31 16:22:28',2,'',''),(188,187,141,'','(while handing out the parchments) Here is your next parchment. Just as you did with your in department Memos, please take a moment to read these carefully. Take your time. You are responsible to understand and fulfill each detail explained. There is no need for rushing, we do not want any clue to be missed. Once everyone?s parchments are down I will begin our discussion.',NULL,'Give time to read.',0,0,NULL,'2018-10-31 16:23:47',3,'',''),(189,264,141,'','Well, each of you has read your important role, you are welcome to refer back to your parchments as often as needed. Now, does anyone have anything they would like to say?',NULL,'Discuss Parchments, clues, refer back to evidence, etc. (McGonagall, remember to facilitate discussion, remind guests to speak one at a time and loud and clear...and repeat anything that was not heard)',0,0,NULL,'2018-10-31 16:24:02',3,'',''),(190,265,141,'','We will continue this discussion over our meal. Dear Mrs. Weasley we are ready for the Entree.',NULL,'Once, everyone has shared their portions and the discussion is closing',0,0,NULL,'2018-10-31 16:24:21',3,'',''),(195,187,127,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: When Ron tells you to, question Hermione about the love letters to Neville. Ask her why and when this happened, and why can?t she explain herself? Still be on your toes and ask others any questions that might help you to solve this murder.',0,0,NULL,'2018-10-31 16:26:40',3,'',''),(196,187,132,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: Ask Ginny about Neville?s wand & make Harry ask Hermione about the love notes, add comments of your disgust towards Hermione and her disloyalty. Make a few sly comments at Hermione?s expense whenever possible. Still be on your toes and ask others any questions that might help you to solve this murder.',0,0,NULL,'2018-10-31 16:26:55',3,'',''),(197,187,128,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: When accused about the love notes, get frustrated and speechless. Tell Harry, to tell Ron that Ron should trust you more than that and know you better. Explain to Everyone that Neville needed you and you were doing your duty as a friend, but you just can?t share everything. Accuse Draco again, and have him explain why he has the Remembrall. Still be on your toes and ask others any questions that might help you to solve this murder.',0,0,NULL,'2018-10-31 16:27:45',3,'',''),(198,187,129,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: Be saddened by the news of the love notes. Shed a few tears as you ask Hermione about them and if it is true. Look to Ginny for answers about Neville?s wand. Make plenty of thoughtful and whimsical comments about abnormal theories.',0,0,NULL,'2018-10-31 16:28:01',3,'',''),(199,187,130,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: Support Luna as she asks Hermione about the love letters. Pressure Malfoy again about messing up Neville?s room and why he was there. Tell him you know he must have been looking for something. What did he hope do find? And did he find it? Still be on your toes and ask others any questions that might help you to solve this murder.',0,0,NULL,'2018-10-31 16:28:17',3,'',''),(200,187,136,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: When confronted about the Remembrall and messing with Neville?s room, tell them he had it coming and it was just for fun. Tell them you only took the Remembrall to give it to Crabbe & Goyle, since they need all the help they can get. And be sure to rub in the facts that Ginny and Hermione look guilty. Question and accuse them all about Neville?s wand and the love letters.',0,0,NULL,'2018-10-31 16:28:32',3,'',''),(201,187,131,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: Ask Mundungus what was meant in the picture? Keep your eye on him, he is up to something. Still be on your toes and ask others any questions that might help you to solve this murder.',0,0,NULL,'2018-10-31 16:28:48',3,'',''),(202,187,135,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: Guiltily avoid talking about the picture. Tell them, It meant nothing! You don?t need to explain it. It isn?t their business, when pushed too far, mention that Neville took a picture because he was desperate to find an old jewel or stone of some kind.',0,0,NULL,'2018-10-31 16:29:02',3,'',''),(203,187,133,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: Continue to seek for fans and attention. Look to “lead the way” on far fetched ideas to solve the murder.',0,0,NULL,'2018-10-31 16:29:18',3,'',''),(204,187,134,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: Ask questions to each person, continuing to get the latest scoop. But put the pressure on Mundungus about the picture. Don?t let him get off easily and be very intrigued by each of his answers. What was it from and why was Neville using it?',0,0,NULL,'2018-10-31 16:29:30',3,'',''),(205,187,138,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: Support Malfoy?s search of Neville?s things, but do push for an answer on what he was looking for there? When Mundungus Mentions a stone, make a loud gasp of disgusted surprise. Still be on your toes and ask others any questions that might help you to solve this murder.',0,0,NULL,'2018-10-31 16:29:45',3,'',''),(206,187,137,'','You are a bit shocked by some of this recent news. There are sides to people you never expected and secrets you never knew. Who do you trust? Now is the time to find out more. ',NULL,'Things you must say and do during this course: Support Malfoy?s search of Neville?s things, but do push for an answer on what he was looking for there? Be very skeptical of Mundungus, but still be on your toes and ask others any questions that might help you to solve this murder.',0,0,NULL,'2018-10-31 16:29:58',3,'',''),(208,207,141,'','(while handing out the parchments) As you are being served, you will also receive your next parchment. As always, read very carefully and follow all instructions with exactness. Take your time, I will know you are ready to begin as your parchments are put down.',NULL,'Give time for reading.',0,0,NULL,'2018-10-31 16:31:10',4,'',''),(209,266,141,'','First before anymore discussion, Filch has brought our last and final items of evidence.',NULL,'',0,0,NULL,'2018-10-31 16:31:58',4,'',''),(210,268,141,'','First most interestingly, Hedwig came looking for Mr. Potter with this owl post. It appears Hedwig got a bit lost. Sent from Mr. Potter & Mr. Weasley to Neville himself, and it reads: “Neville, we know what you are up to and it is wrong. You are setting yourself up for misery. If you don?t end it, we will. Signed Harry & Ron”.',NULL,'Discuss Parchments, clues, refer back to evidence, etc. (McGonagall, remember to facilitate discussion, remind guests to speak one at a time and loud and clear...and repeat anything that was not heard)',0,0,NULL,'2018-10-31 16:34:14',4,'',''),(211,274,141,'','When you see \"Mr Malfoy stand up\", say MR. MALFOY! SIT down this instant. There is no need for loosing our tempers now. There has already been one murder and we do not need to cause another! (let Malfoy talk) Thank you Mr. Malfoy”.',NULL,'**When Draco Malfoy bursts out say:',0,0,NULL,'2018-10-31 16:34:33',4,'',''),(212,275,141,'','Well, from all of this discussion, I presume you can see why we are here. Let me interrupt as we prepare for dessert. Mrs. Weasley, would you please serve our guests the dessert course?',NULL,'Once, everyone has shared their portions and the discussion is closing',0,0,NULL,'2018-10-31 16:34:47',4,'',''),(213,207,127,'','There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to solve this mystery.',NULL,'Things you must say and do during this course: When your and Ron?s Owl Post is found, show your shock and surprise. When questioned tell everyone it was not meant that way. You were only standing up for Ron, since he was scared to do it for himself.',0,0,NULL,'2018-10-31 16:36:07',4,'',''),(214,207,132,'','There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to solve this mystery.',NULL,'Things you must say and do during this course: When your and Harry?s Owl Post is found, show your shock and surprise. When questioned tell everyone it was not meant that way. You just wanted to fight for Hermione. Tell everyone you didn?t want to go out without a fight.',0,0,NULL,'2018-10-31 16:36:21',4,'',''),(215,207,128,'','There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to solve this mystery.',NULL,'Things you must say and do during this course: Tell Harry and Ron what they did was dumb and thoughtless, but show in your face and actions that you are thrilled Ron was jealous and trying to win you back. Show your hurt and anger when Malfoy dares call you a mudblood. Tell him that you would rather be a mudblood than any pure blood is polluted with Dark Magic.',0,0,NULL,'2018-10-31 16:36:34',4,'',''),(216,207,129,'','There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to solve this mystery.',NULL,'Things you must say and do during this course: Show you are embarrassed by the love notes found in your robes. But still tell everyone some things are just destined for another time. Tell Gilderoy how sweet he is for helping Neville?s parents with their medical expenses, what a giving man he is.',0,0,NULL,'2018-10-31 16:36:49',4,'',''),(217,207,130,'',' There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to solve this mystery.',NULL,'Things you must say and do during this course: Ask Malfoy why he carries around a family tree and does it have anything to do with his search of Neville?s things? You know he must be up to something.',0,0,NULL,'2018-10-31 16:37:03',4,'',''),(218,207,136,'','There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to solve this mystery.',NULL,'Things you must say and do during this course: So what you had a family tree in your pocket. Tell them it isn?t their business, but if they must know, it is for your homework. And rub in the fact that you are proud of your strong wizarding blood & that you aren?t a fifthly mudblood! But when Bellatrix, flips out about someone stealing HER stone from her vault. Jump up in anger, and yell at Bellatrix, “You have the Amorine stone!?” Then after Professor McGonagall has you calm down, tell her and everyone that the Amorine Stone was to be passed down to you for your 18th birthday, but somehow it went missing!',0,0,NULL,'2018-10-31 16:37:24',4,'',''),(219,207,131,'',' There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to solve this mystery.',NULL,'Things you must say and do during this course: Question Gilderoy and his “sweet” donations to the Longbottom family. You know he doesn?t care for anyone other than himself, so find out what was behind these payments. Why did he do it? Who told him to and what was in it for him? Pressure him for answers and make sly comments at his expense. A few comments at Harry and Ron ?s expense are always welcomed as well. When told about the missing potion ingredients, tell everyone “I knew that Rotten Neville was stealing from my supplies. And this isn?t the first time!”',0,0,NULL,'2018-10-31 16:37:43',4,'',''),(220,207,135,'','There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to find an end this mystery so you can get out of here.',NULL,'Things you must say and do during this course: You are relieved that the attention is now on others, encourage questioning and accusing of Rita and Dolores, those two are always conniving their way to fame.',0,0,NULL,'2018-10-31 16:37:58',4,'',''),(221,207,133,'','There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to find an end this mystery so you can get out of here.',NULL,'Things you must say and do during this course: When the medical bills you have been paying are found, show your shock. But try and cover it with a quick excuse, something suave like helping the poor family is in your thoughtful nature. But when questioned by Snape and others, show your concern and search for anything but the truth. Then break and tell them, that nasty Neville boy made  you do it. His family couldn?t afford the bills anymore so, Neville blackmailed you and was going to ruin your reputation, everything you worked for. So you paid that rotten kid to leave you alone.',0,0,NULL,'2018-10-31 16:38:28',4,'',''),(222,207,134,'',' There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to find an end this mystery so you can get out of here.',NULL,'Things you must say and do during this course: You are absolutely shocked that they found the postcard Dolores sent you! This was private! Deny ever receiving it, even to Dolores! Then when Dolores proves you had it, tell everyone it was all her idea. She wanted to get the stone so you would write a book about her. Tell them how Dolores only wanted the fame and fortune that would come.',0,0,NULL,'2018-10-31 16:38:45',4,'',''),(223,207,138,'','There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to find an end this mystery so you can get out of here.',NULL,'Things you must say and do during this course: Show you are so upset they found your postcard and tell Rita she is dumb to have it on her. When she denies it, tell everyone that she did to read it & even sent a return owl with accepting the offer. And that you can prove it, by bringing her response in. Make Rita admit it, then tell them it was not for fame or fortune, you have always wanted the stone. And tell everyone it is a known fact that the stone makes others fall subject to you, others will be devoted and follow your orders. Then be embarrassed for having shared too much information. Stumble over your words as you say, “Oh, it meant nothing.”',0,0,NULL,'2018-10-31 16:39:02',4,'',''),(224,207,137,'',' There is so much information and so many twisted stories you are trying to make sense of and you are finding yourself mixed into it all. Question everyone, and seize the chance to find an end this mystery so you can get out of here.',NULL,'Things you must say and do during this course: You are furious about all the talk of this stone, and that someone would DARE try to take it from your vault! Tell everyone, “How Dare that rotten Neville be interested in my stone!” And tell off Dolores Umbridge and Rita Skeeter, for thinking they could steal your stone and use it for some pathetic book! Tell everyone, that stone has been in your family for generations and it belongs to you.',0,0,NULL,'2018-10-31 16:39:28',4,'',''),(225,207,141,'','**McGonagall: MR. MALFOY! SIT down this instant. There is no need for loosing our tempers now. There has already been one murder and we do not need to cause another! (let Malfoy talk) Thank you Mr. Malfoy.',NULL,'',0,0,NULL,'2018-10-31 16:41:21',4,'',''),(227,226,141,'','(while handing out the parchments & pen) As you are being served, you will also receive your final parchments and a pen to cast your vote. As we speak, Dementors are waiting outside the gates to bring the guilty to Azkaban, following our voting. Each of you clearly has some reason and some evidence against you in the murder of Neville Longbottom. So now is your last chance to clear yourselves. Take a moment, we will begin momentarily.  Included in your final parchments are you voting slips, please save these for after this last discussion. I encourage each of you to voice any last concerns, you now may share any last thoughts:',NULL,'Discuss Parchments, clues, refer back to evidence, etc. (McGonagall, remember to facilitate discussion, remind guests to speak one at a time and loud and clear...and repeat anything that was not heard)',0,0,NULL,'2018-10-31 16:42:17',5,'',''),(228,277,141,'','Well, if there is nothing more. Please take a minute to write the name of who you believe is guilty. Once everyone has finished, we will ask for your vote.',NULL,'Once, everyone has shared their portions and the discussion is closing: McGonagall: Well, if there is nothing more. Please take a minute to write',0,0,NULL,'2018-10-31 16:42:32',5,'',''),(233,226,127,'','Take advantage of this last chance to explain or accuse, but include: I would never hurt Neville. He is my friend. Things just got mixed up when he went after Hermione behind Ron?s back.',NULL,'',0,0,NULL,'2018-10-31 16:45:17',5,'',''),(234,226,132,'','Take advantage of this last chance to explain or accuse, but include: You know I couldn?t have done it...it?s Neville! Yeah, he should have known better than to try for Hermione, but you know I wouldn?t...',NULL,'',0,0,NULL,'2018-10-31 16:45:26',5,'',''),(235,226,128,'','Take advantage of this last chance to explain or accuse, but include: I wasn?t in love with Neville. I don?t know how to tell you, and it shouldn?t concern any of you. I only was helping Neville. We spent time together writing love notes to Luna. He just needed my help, I swear!',NULL,'',0,0,NULL,'2018-10-31 16:45:39',5,'',''),(236,226,129,'',' Take advantage of this last chance to explain or accuse, but include: (dreamily) I loved Neville and I will always love him. Anything I do is out of love for him.',NULL,'',0,0,NULL,'2018-10-31 16:45:49',5,'',''),(237,226,130,'','Take advantage of this last chance to explain or accuse, but include: Neville was my friend. I wouldn?t hurt him. I didn?t take his wand. I just wanted to give it to Luna...when the time was right. I felt she should have it.',NULL,'',0,0,NULL,'2018-10-31 16:45:59',5,'',''),(238,226,136,'','Take advantage of this last chance to explain or accuse, but include: I didn?t do anything. It?s a disgrace to he associated with this whole situation. I don?t need to defend myself. My father will take care of this, just you wait.',NULL,'',0,0,NULL,'2018-10-31 16:46:12',5,'',''),(239,226,131,'','Take advantage of this last chance to explain or accuse, but include: Regardless of the victim or the use of poison, I did not do it.',NULL,'',0,0,NULL,'2018-10-31 16:46:26',5,'',''),(240,226,135,'','Take advantage of this last chance to explain or accuse, but include: I?m not a part of any of this. I mind my own business and you all should mind yours.',NULL,'',0,0,NULL,'2018-10-31 16:46:36',5,'',''),(241,226,133,'','Take advantage of this last chance to explain or accuse, but include: I do believe I have solved it! (claim the recognition and the fame, but never give a full answer)',NULL,'',0,0,NULL,'2018-10-31 16:46:47',5,'',''),(242,226,134,'',' Take advantage of this last chance to explain or accuse, but include: Ask around for any last statements, words to be remembered by, etc. Remind everyone that you will make them Headliners.',NULL,'',0,0,NULL,'2018-10-31 16:46:58',5,'',''),(243,226,138,'','Take advantage of this last chance to explain or accuse, but include: This is not the correct order for such matters and I will not stand for it. ',NULL,'',0,0,NULL,'2018-10-31 16:47:15',5,'',''),(244,226,137,'','Take advantage of this last chance to explain or accuse, but include: I would be happy to kill Neville! And I?m not scared of no Azkaban, but you can?t get me there if you tried!!! (Cackle)',NULL,'',0,0,NULL,'2018-10-31 16:47:25',5,'',''),(246,278,139,'','Professor McGonagall! It was the Wormwood! Madam Pompfrey found, It was too much Wormwood that killed Neville Longbottom!',NULL,'Runs in',0,0,NULL,'2018-10-31 16:48:55',6,'',''),(247,278,141,'','As I open each vote, I will call your name and announce the person you believe to be the murderer. Then please stand and tell us why you suspect them.',NULL,'(Collect votes & slide Snape and Luna the FINAL parchments)',0,0,NULL,'2018-10-31 16:49:20',6,'',''),(249,245,141,'','Well, we can hold off on the Dementors...this appears to be accidental. But we must inform Dumbledor and the Ministry. Filch, contact them immediately!',NULL,'',0,0,NULL,'2018-10-31 16:49:59',6,'',''),(250,245,131,'','That?s the ingredient in a Love Potion!',NULL,'',0,0,NULL,'2018-10-31 16:50:22',6,'',''),(251,245,129,'','(concerned and confused) My love potion? So I....?  I killed my sweet, adoring Neville!!?? Oh, what fate is this!? (bursting into tears)',NULL,'',0,0,NULL,'2018-10-31 16:50:41',6,'',''),(279,267,141,'','Along with this Owl Post, Filch found this Medical Bill Receipt. Mr. Lockheart, it appears you haven?t been sharing everything! Why, I might ask, have you been paying for Mr. & Mrs. Longbottom?s medical bills as of late? There appears more connection between you and Neville than you let on. In fact, you have been paying a substantial price here. We will hear more.',NULL,'',0,0,NULL,'2018-11-01 08:32:45',20,'',''),(280,269,141,'','Miss Lovegood: As sweet as your intentions were, I must still present these love notes. Here is a small collection of love notes found in your robes. All written by you to Neville. ',NULL,'',0,0,NULL,'2018-11-01 08:33:28',21,'',''),(281,270,141,'','And now for Draco, you are in the hot seat again Mr. Malfoy. Here we find a family tree. This raises some suspicions. Why would you carry around records of your family tree?  ',NULL,'',0,0,NULL,'2018-11-01 08:34:11',22,'',''),(282,271,141,'','But it does not end there, here quite interestingly, is a Post Card. One written from you, Dolores Umbridge to none other than Rita Skeeter. It says, “Dear Rita, I have found the Amorin Stone, but have yet to get possession of it. I know its location. It is in Bellatrix Lestrange?s vault at Gringotts. But, I do have a means of collecting it. Soon you will have your story. If you are still interested in our deal, please notify me by post. Sincerely, Dolores Umbridge” Well now ladies, it appears the two of you have some explaining to do. ',NULL,'',0,0,NULL,'2018-11-01 08:34:36',23,'',''),(283,272,141,'','And it just occurred to me that Professor Snape\'s office was previously broken into and after a careful search, potion ingredients were found missing. This may be more than just a coincidence.  ',NULL,'',0,0,NULL,'2018-11-01 08:35:15',24,'',''),(284,273,141,'','With all of this new knowledge, let us discuss. Who would like to begin? Anyone have any questions they might ask?',NULL,'',0,0,NULL,'2018-11-01 08:35:51',25,'',''),(285,274,136,'','Get up to say something disruptive',NULL,'',0,0,NULL,'2018-11-01 08:38:11',26,'',''),(286,276,141,'','You may now guess the killer by clicking on the character you think killed Neville.  I encourage each of you to voice any last concerns, you now may share any last thoughts',NULL,'Discuss Parchments, clues, refer back to evidence, etc. (McGonagall, remember to facilitate discussion, remind guests to speak one at a time and loud and clear...and repeat anything that was not heard)',0,0,NULL,'2018-11-01 08:49:32',29,'','');
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
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
INSERT INTO `story` VALUES (125,'','Harry Potter Murder Mystery','Your attendance is requested.  There is a mystery to be solved.  Time is of the essence and silence is essential.  Due to dire circumstances amidst the Wizarding World and the collapse of the Ministry of Magic, a private meeting is to be held and your participation is vital.\r\n\r\nYour immediate response of acceptance is crucial, or your position will be filled.','Wormwood',14,0,0,NULL,'2018-10-31 05:12:41','','');
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

-- Dump completed on 2018-11-02  4:58:28
